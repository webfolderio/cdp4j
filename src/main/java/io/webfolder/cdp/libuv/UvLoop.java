package io.webfolder.cdp.libuv;

import static io.webfolder.cdp.libuv.Libuv.CDP4J_UV_SUCCESS;
import static io.webfolder.cdp.libuv.Libuv.UV_RUN_NOWAIT;
import static io.webfolder.cdp.libuv.Libuv.cdp4j_close_loop;
import static io.webfolder.cdp.libuv.Libuv.uv_loop_init;
import static io.webfolder.cdp.libuv.Libuv.uv_run;
import static io.webfolder.cdp.libuv.UvLogger.debug;
import static org.graalvm.nativeimage.UnmanagedMemory.malloc;
import static org.graalvm.nativeimage.c.struct.SizeOf.get;

import java.util.concurrent.LinkedBlockingQueue;
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

    private LinkedBlockingQueue<String> writeQueue = new LinkedBlockingQueue<>();

    public UvLoop() {
        debug("-> UvLoop()");
        this.loop = malloc(get(loop.class));
        debug("<- UvLoop()");
    }

    public boolean init() {
        debug("-> UvLoop.init()");
        if (uv_loop_init(getPeer()) != CDP4J_UV_SUCCESS()) {
            debug("<- UvLoop.init(): false");
            return false;
        }
        debug("<- UvLoop.init(): true");
        return true;
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
        debug("-> UvProcess.createProcess()");
        process = new UvProcess(this);
        debug("<- UvProcess.createProcess()");
        return process;
    }

    loop getPeer() {
        return loop;
    }

    public void start(Runnable runnable) {
        Thread thread = new Thread(() -> {
            if (running.compareAndSet(false, true)) {
                UvLoop.this.currentThread = CurrentIsolate.getCurrentThread();
                runnable.run();
                UvLoop.this.run();
            }
        });
        thread.setDaemon(true);
        thread.setName("cdp4j-libuv-thread-" + (++counter));
        thread.start();
    }

    public void dispose() {
        if (loop.isNonNull()) {
            debug("-> UvLoop.dispose()");
            running.compareAndSet(true, false);
            debug("<- UvLoop.dispose()");
        }
    }

    IsolateThread getCurrentThread() {
        return currentThread;
    }

    void run() {
        while (running.get()) {
            uv_run(loop, UV_RUN_NOWAIT());
            String payload = writeQueue.poll();
            if ( payload != null ) {
                process._writeAsync(payload);
            }
        }
        cdp4j_close_loop(loop);
        process.dispose();
    }

    public void add(String payload) {
        writeQueue.offer(payload);
    }
}
