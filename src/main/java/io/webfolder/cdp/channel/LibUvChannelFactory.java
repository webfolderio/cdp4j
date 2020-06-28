package io.webfolder.cdp.channel;

import static com.oracle.libuv.LibUV.loadJni;
import static com.oracle.libuv.ProcessHandle.ProcessFlags.SETGID;
import static com.oracle.libuv.ProcessHandle.ProcessFlags.SETUID;
import static com.oracle.libuv.ProcessHandle.ProcessFlags.WINDOWS_VERBATIM_ARGUMENTS;
import static com.oracle.libuv.StdioOptions.StdioType.CREATE_PIPE;
import static com.oracle.libuv.StdioOptions.StdioType.IGNORE;
import static com.oracle.libuv.StdioOptions.StdioType.INHERIT_FD;
import static java.lang.System.arraycopy;
import static java.lang.System.getProperty;
import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.EnumSet.of;
import static java.util.Locale.ENGLISH;

import java.nio.ByteBuffer;
import java.nio.file.Path;
import java.util.EnumSet;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;

import com.oracle.libuv.ContextProvider;
import com.oracle.libuv.DefaultHandleFactory;
import com.oracle.libuv.IdleCallback;
import com.oracle.libuv.IdleHandle;
import com.oracle.libuv.LoopHandle;
import com.oracle.libuv.PipeHandle;
import com.oracle.libuv.ProcessCloseCallback;
import com.oracle.libuv.ProcessExitCallback;
import com.oracle.libuv.ProcessHandle;
import com.oracle.libuv.ProcessHandle.ProcessFlags;
import com.oracle.libuv.StdioOptions;
import com.oracle.libuv.StreamReadCallback;

import io.webfolder.cdp.exception.CdpException;
import io.webfolder.cdp.session.MessageHandler;
import io.webfolder.cdp.session.SessionFactory;

public class LibUvChannelFactory implements
                                    StreamReadCallback,
                                    ContextProvider,
                                    Runnable,
                                    IdleCallback,
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
    
    private final DefaultHandleFactory handleFactory;

    private final Queue<Runnable> queue = new ConcurrentLinkedQueue<>();

    private final AtomicBoolean connected = new AtomicBoolean(false);

    private PipeHandle inPipe;
    
    private PipeHandle outPipe;
    
    private byte[] remaining;
    
    private IdleHandle idleHandle;
    
    private MessageHandler handler;

    private ProcessHandle process;

    private LoopHandle loop;

    static {
        loadJni();
    }

    public LibUvChannelFactory() {
        loop = new LoopHandle();
        handleFactory = new DefaultHandleFactory(loop);
        Thread thread = new Thread(this);
        thread.setDaemon(true);
        thread.start();
    }

    public void spawn(Path path, List<String> arguments) {
        submit(() -> {
            inPipe = handleFactory.newPipeHandle(false);
            outPipe = handleFactory.newPipeHandle(false);

            outPipe.setReadCallback(LibUvChannelFactory.this);

            StdioOptions[] stdio = new StdioOptions[5];
            stdio[0] = new StdioOptions(IGNORE, null, 0);         // stdin
            stdio[1] = new StdioOptions(INHERIT_FD, null, 1);     // stdout
            stdio[2] = new StdioOptions(INHERIT_FD, null, 2);     // stderr
            stdio[3] = new StdioOptions(CREATE_PIPE, inPipe, 3);  // input
            stdio[4] = new StdioOptions(CREATE_PIPE, outPipe, 4); // output

            String executable = path.getFileName().toString();
            List<String> args = arguments.subList(1, arguments.size());
            args.add(0, executable);
            String[] env = new String[] { };
            String dir = path.toAbsolutePath()
                             .getParent()
                             .toString();

            EnumSet<ProcessFlags> flags = WINDOWS ? of(WINDOWS_VERBATIM_ARGUMENTS) : of(SETUID, SETGID);

            process = handleFactory.newProcessHandle();
            process.setExitCallback(this);
            process.setCloseCallback(this);

            String program = path.resolve(executable)
                                 .toAbsolutePath()
                                 .toString();

            process.spawn(program,
                          args.toArray(new String[] { }),
                          env,
                          dir,
                          flags,
                          stdio,
                          -1,
                          -1);

            outPipe.readStart();

            connected.set(true);
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
        idleHandle = handleFactory.newIdleHandle();
        idleHandle.setIdleCallback(this);
        idleHandle.start();
        try {
            loop.run();
            connected.compareAndSet(true, false);
        } catch (Throwable e) {
            connected.compareAndSet(true, false);
            throw new CdpException(e);
        }
    }

    @Override
    public void onIdle(int status) throws Exception {
        Runnable runnable = queue.poll();
        if (runnable != null) {
            runnable.run();
        }
    }

    public void submit(Runnable runnable) {
        queue.add(runnable);
    }

    @Override
    public Channel createChannel(Connection connection,
                                 SessionFactory factory,
                                 MessageHandler handler) {
        this.handler = handler;
        return this;
    }

    @Override
    public boolean isOpen() {
        return connected.get();
    }

    @Override
    public void disconnect() {
        submit(() -> process.close());
        submit(() -> loop.close());
        submit(() -> loop.destroy());
    }

    @Override
    public void sendText(String message) {
        if (connected.get()) {
            submit(() -> {
                inPipe.write(message);
                inPipe.write(MESSAGE_SEPERATOR_STR);
            });
        }
    }

    @Override
    public void connect() {
        // no op
    }

    @Override
    public void onExit(int status,
                       int signal,
                       Exception error) throws Exception {
        connected.compareAndSet(true, false);
    }

    @Override
    public void onClose() throws Exception {
        connected.compareAndSet(true, false);
    }

    public boolean kill() {
        if (connected.get()) {
            process.kill(SIGTERM);
            return true;
        }
        return false;
    }
}
