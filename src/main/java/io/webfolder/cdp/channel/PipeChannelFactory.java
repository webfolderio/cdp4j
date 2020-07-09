/**
 * cdp4j Commercial License
 *
 * Copyright 2017, 2020 WebFolder OÜ
 *
 * Permission  is hereby  granted,  to "____" obtaining  a  copy of  this software  and
 * associated  documentation files  (the "Software"), to deal in  the Software  without
 * restriction, including without limitation  the rights  to use, copy, modify,  merge,
 * publish, distribute  and sublicense  of the Software,  and to permit persons to whom
 * the Software is furnished to do so, subject to the following conditions:
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR  IMPLIED,
 * INCLUDING  BUT NOT  LIMITED  TO THE  WARRANTIES  OF  MERCHANTABILITY, FITNESS  FOR A
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL  THE AUTHORS  OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package io.webfolder.cdp.channel;

import static com.oracle.libuv.LibUV.disableStdioInheritance;
import static com.oracle.libuv.LibUV.loadJni;
import static com.oracle.libuv.ProcessHandle.ProcessFlags.NONE;
import static com.oracle.libuv.ProcessHandle.ProcessFlags.WINDOWS_VERBATIM_ARGUMENTS;
import static com.oracle.libuv.StdioOptions.StdioType.CREATE_PIPE;
import static com.oracle.libuv.StdioOptions.StdioType.IGNORE;
import static com.oracle.libuv.StdioOptions.StdioType.INHERIT_FD;
import static java.io.File.separator;
import static java.lang.System.arraycopy;
import static java.lang.System.getProperty;
import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.EnumSet.of;
import static java.util.Locale.ENGLISH;
import static java.util.concurrent.TimeUnit.SECONDS;

import java.nio.ByteBuffer;
import java.nio.file.Path;
import java.util.EnumSet;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import com.oracle.libuv.ContextProvider;
import com.oracle.libuv.DefaultHandleFactory;
import com.oracle.libuv.LoopHandle;
import com.oracle.libuv.PipeHandle;
import com.oracle.libuv.ProcessCloseCallback;
import com.oracle.libuv.ProcessExitCallback;
import com.oracle.libuv.ProcessHandle;
import com.oracle.libuv.ProcessHandle.ProcessFlags;
import com.oracle.libuv.StdioOptions;
import com.oracle.libuv.StreamReadCallback;
import com.oracle.libuv.TimerCallback;
import com.oracle.libuv.TimerHandle;

import io.webfolder.cdp.exception.CdpException;
import io.webfolder.cdp.session.MessageHandler;
import io.webfolder.cdp.session.SessionFactory;

public class PipeChannelFactory implements
                                    StreamReadCallback,
                                    ContextProvider,
                                    Runnable,
                                    TimerCallback,
                                    ChannelFactory,
                                    Channel,
                                    Connection,
                                    ProcessExitCallback,
                                    ProcessCloseCallback {

    private static final char    MESSAGE_SEPERATOR      = '\0';

    private static final String  MESSAGE_SEPERATOR_STR  = "\0";

    private static final String  OS_NAME                = getProperty("os.name").toLowerCase(ENGLISH);

    private static final boolean WINDOWS                = OS_NAME.startsWith("windows");

    private static final int     SIGTERM                = 15;

    // inherit the environment from the parent process
    private static final String[]  DEFAULT_ENV          = null;

    private static final AtomicInteger THREAD_COUNTER   = new AtomicInteger(0);

    private static final long INTERVAL                  = 1; // ms

    private final DefaultHandleFactory handleFactory;

    private final Queue<Runnable> queue = new ConcurrentLinkedQueue<>();

    private PipeHandle outPipe;

    private PipeHandle inPipe;

    private byte[] remaining;

    private TimerHandle timerHandle;

    private MessageHandler handler;

    private ProcessHandle process;

    private LoopHandle loop;

    private SessionFactory sessionFactory;

    private enum State { nil, running, closed, closing };

    private volatile State loopState = State.nil;

    private volatile State processState = State.nil;

    static {
        loadJni();
        disableStdioInheritance();
    }

    public PipeChannelFactory() {
        loop = new LoopHandle();
        handleFactory = new DefaultHandleFactory(loop);
        Thread thread = new Thread(this);
        thread.setName("cdp4j-libuv-" + THREAD_COUNTER.incrementAndGet());
        thread.setDaemon(true);
        thread.start();
    }

    public void spawn(Path path, List<String> arguments) {
        submit(() -> {
            outPipe = handleFactory.newPipeHandle(false);
            inPipe = handleFactory.newPipeHandle(false);

            inPipe.setReadCallback(PipeChannelFactory.this);

            boolean inheritStdioFd = "true".equals(System.getProperty("cdp4j.libuv.inherit.stdio.fd", "false"));
 
            StdioOptions[] stdio = new StdioOptions[5];
            stdio[0] = new StdioOptions(IGNORE, null, 0);                               // stdin
            stdio[1] = new StdioOptions(inheritStdioFd ? INHERIT_FD : IGNORE, null, 1); // stdout
            stdio[2] = new StdioOptions(inheritStdioFd ? INHERIT_FD : IGNORE, null, 2); // stderr
            stdio[3] = new StdioOptions(CREATE_PIPE, outPipe, 3);                       // input
            stdio[4] = new StdioOptions(CREATE_PIPE, inPipe, 4);                        // output

            String executable = path.getFileName().toString();
            List<String> args = arguments.subList(1, arguments.size());
            args.add(0, executable);
            String dir = path.toAbsolutePath()
                             .getParent()
                             .toString();

            EnumSet<ProcessFlags> flags = WINDOWS ? of(WINDOWS_VERBATIM_ARGUMENTS) : of(NONE);

            process = handleFactory.newProcessHandle();
            process.setExitCallback(this);

            String program = ! executable.contains(separator) ?
                                  executable :
                                  path.resolve(executable)
                                      .toAbsolutePath()
                                      .toString();
    
            process.spawn(program,
                          args.toArray(new String[] { }),
                          DEFAULT_ENV,
                          dir,
                          flags,
                          stdio,
                          -1,
                          -1);

            inPipe.readStart();

            setProcessState(State.running);
        });
    }

    @Override
    public void onRead(ByteBuffer response) throws Exception {
        if (response == null) {
            return;
        }
        byte[] data = new byte[response.remaining()];
        response.get(data);
        int start = 0;
        int end = 0;
        while (end < data.length) {
            if (data[end] == MESSAGE_SEPERATOR) {
                int len = end - start;
                if ( remaining != null ) {
                    byte[] msg = new byte[remaining.length + len];
                    arraycopy(remaining, 0, msg, 0, remaining.length);
                    arraycopy(data, start, msg, remaining.length, len);
                    handler.process(new String(msg, UTF_8));
                    remaining = null;
                } else {
                    handler.process(new String(data, start, len, UTF_8));
                }
                start = end + 1;
            }
            end += 1;
        }
        int remainingLength = end - start;
        if (remainingLength > 0) {
            int srcPos = remainingLength == data.length ? 0 : start;
            if (remaining == null) {
                if (srcPos == 0) {
                    remaining = data;
                } else {
                    byte[] buffer = new byte[remainingLength];
                    arraycopy(data, srcPos, buffer, 0, remainingLength);
                    remaining = buffer;
                }
            } else {
                byte[] buffer = new byte[remaining.length + remainingLength];
                arraycopy(remaining, 0, buffer, 0, remaining.length);
                arraycopy(data, srcPos, buffer, remaining.length, remainingLength);
                remaining = buffer;
            }
        }
    }

    @Override
    public Object getContext() {
        return this;
    }

    @Override
    public void run() {
        timerHandle = handleFactory.newTimerHandle();
        timerHandle.setTimerFiredCallback(this);
        timerHandle.start(INTERVAL, INTERVAL);
        try {
            setLoopState(State.running);
            loop.run();
            loop.close();
            loop.destroy();
            queue.clear();
        } catch (Throwable e) {
            throw new CdpException(e);
        } finally {
            setLoopState(State.closed);
        }
    }

    @Override
    public void onTimer(int status) throws Exception {
        Runnable runnable = null;
        while ( ( runnable = queue.poll() ) != null) {
            runnable.run();
        }
    }

    public void submit(Runnable runnable) {
        if (loopState == State.running) {
            queue.add(runnable);
        }
    }

    @Override
    public Channel createChannel(Connection connection,
                                 SessionFactory factory,
                                 MessageHandler handler) {
        this.handler = handler;
        this.sessionFactory = factory;
        return this;
    }

    @Override
    public boolean isOpen() {
        return processState == State.running;
    }

    @Override
    public synchronized void disconnect() {
        submit(() -> close());
    }

    @Override
    public void sendText(String message) {
        submit(() -> {
            outPipe.write(message);
            outPipe.write(MESSAGE_SEPERATOR_STR);
        });
    }

    @Override
    public void connect() {
        // no op
    }

    @Override
    public void onExit(int       status,
                       int       signal,
                       Exception error) throws Exception {
        onClose();
    }

    @Override
    public void onClose() throws Exception {
        if (processState == State.running) {
            setProcessState(State.closed);
            sessionFactory.close();
        }
    }

    public boolean kill() {
        AtomicBoolean flag = new AtomicBoolean(false);
        if (processState == State.running) {
            setProcessState(State.closing);
            CountDownLatch latch = new CountDownLatch(1);
            submit(() -> {
                int ret = process.kill(SIGTERM);
                flag.compareAndSet(false, ret == 0 ? true : false);
                latch.countDown();
            });
            try {
                latch.await(1, SECONDS);
            } catch (InterruptedException e) {
                // no op
            }
        }
        return flag.get();
    }

    private void close() {
        if (loopState == State.running) {
            outPipe.closeWrite();
            outPipe.close();
            outPipe.unref();
            inPipe.readStop();
            inPipe.close();
            inPipe.unref();
            setProcessState(State.closing);
            process.kill(SIGTERM);
            process.close();
            timerHandle.stop();
            timerHandle.close();
        }
    }

    private void setLoopState(State sate) {
        this.loopState = sate;
    }

    private void setProcessState(State sate) {
        this.processState = sate;
    }
}
