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
package io.webfolder.cdp.libuv;

import static io.webfolder.cdp.libuv.Libuv.CDP4J_UV_SUCCESS;
import static io.webfolder.cdp.libuv.Libuv.UV_RUN_DEFAULT;
import static io.webfolder.cdp.libuv.Libuv.cdp4j_async_close_loop;
import static io.webfolder.cdp.libuv.Libuv.cdp4j_write_async;
import static io.webfolder.cdp.libuv.Libuv.uv_loop_init;
import static io.webfolder.cdp.libuv.Libuv.uv_run;
import static io.webfolder.cdp.libuv.UvLogger.debug;
import static org.graalvm.nativeimage.UnmanagedMemory.free;
import static org.graalvm.nativeimage.UnmanagedMemory.malloc;
import static org.graalvm.nativeimage.c.struct.SizeOf.get;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

import org.graalvm.nativeimage.CurrentIsolate;
import org.graalvm.nativeimage.IsolateThread;

import io.webfolder.cdp.libuv.Libuv.loop;

public class UvLoop {

    private static int counter;

    private loop loop;

    private IsolateThread currentThread;

    private UvProcess process;

    private AtomicBoolean running = new AtomicBoolean(false);

    private ArrayBlockingQueue<String> writeQueue = new ArrayBlockingQueue<>(1024 * 4 , true);

    public boolean init() {
        if ( running.get() == false ) {
            debug("-> UvLoop.init()");
            loop = malloc(get(loop.class));
            if (uv_loop_init(getPeer()) != CDP4J_UV_SUCCESS()) {
                free(loop);
                debug("<- UvLoop.init(): false");
                return false;
            }
            debug("<- UvLoop.init(): true");
            return true;
        }
        debug("<- UvLoop.init(): false");
        return false;
    }

    UvPipe createPipe() {
        debug("-> UvLoop.createPipe()");
        UvPipe pipe = new UvPipe(this);
        if (pipe.init()) {
            debug("<- UvLoop.createPipe()");
            return pipe;
        }
        debug("<- UvLoop.createPipe(): null");
        return null;
    }

    public UvProcess createProcess() {
        if ( process != null ) {
            throw new IllegalStateException();
        }
        debug("-> UvLoop.createProcess()");
        process = new UvProcess(this);
        debug("<- UvLoop.createProcess()");
        return process;
    }

    loop getPeer() {
        return loop;
    }

    public void start(Runnable runnable) {
        Thread thread = new Thread(() -> {
            if (init()) {
                if (running.compareAndSet(false, true)) {
                    UvLoop.this.currentThread = CurrentIsolate.getCurrentThread();
                    runnable.run();
                    UvLoop.this.run();
                }
            }
        });
        thread.setDaemon(true);
        thread.setName("cdp4j-libuv-" + (++counter));
        thread.start();
    }

    public void close() {
    	debug("-> UvLoop.close()");
    	if (running.compareAndSet(true, false) && loop.isNonNull()) {
            cdp4j_async_close_loop(loop);
            process.dispose();
            free(loop);
    	}
    	debug("<- UvLoop.close()");
    }

    IsolateThread getCurrentThread() {
        return currentThread;
    }

    void run() {
    	debug("-> UvLoop.run()");
    	uv_run(loop, UV_RUN_DEFAULT());
    	debug("<- UvLoop.run()");
    }

    void writeAsync(String payload) {
		writeQueue.offer(payload);
		cdp4j_write_async(loop, currentThread);
    }

    void writeSync() {
		String payload = null;
		while ( ( payload = writeQueue.poll() ) != null ) {
			process.write(payload);
		}
    }

    public boolean isRunning() {
        return running.get();
    }

    public UvProcess getProcess() {
        return process;
    }
}
