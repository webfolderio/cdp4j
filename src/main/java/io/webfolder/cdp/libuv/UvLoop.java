package io.webfolder.cdp.libuv;

import static io.webfolder.cdp.libuv.Libuv.CDP4J_UV_SUCCESS;
import static io.webfolder.cdp.libuv.Libuv.UV_RUN_DEFAULT;
import static io.webfolder.cdp.libuv.Libuv.uv_loop_init;
import static io.webfolder.cdp.libuv.Libuv.uv_run;
import static io.webfolder.cdp.libuv.Libuv.uv_stop;
import static io.webfolder.cdp.libuv.UvLogger.debug;
import static org.graalvm.nativeimage.UnmanagedMemory.free;
import static org.graalvm.nativeimage.UnmanagedMemory.malloc;
import static org.graalvm.nativeimage.c.struct.SizeOf.get;

import org.graalvm.nativeimage.CurrentIsolate;
import org.graalvm.nativeimage.IsolateThread;

import io.webfolder.cdp.libuv.Libuv.loop;

public class UvLoop {

    private loop loop;

    protected IsolateThread currentThread;

    private static int counter;

    public UvLoop() {
        debug("-> UvLoop()");
        this.loop = malloc(get(loop.class));
        debug("<- UvLoop()");
    }

    public boolean init() {
        debug("-> UvLoop.init()");
        if ( uv_loop_init(getPeer()) != CDP4J_UV_SUCCESS() ) {
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
        debug("-> UvProcess.createProcess()");
        UvProcess process = new UvProcess(this);
        debug("<- UvProcess.createProcess()");
        return process;
    }

    loop getPeer() {
        return loop;
    }

    public void start(Runnable runnable) {
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                UvLoop.this.currentThread = CurrentIsolate.getCurrentThread();
                runnable.run();
                UvLoop.this.run();
            }
        });
        thread.setDaemon(true);
        thread.setName("cdp4j-libuv-thread-" + (++counter));
        thread.start();
    }

    public void stop() {
        uv_stop(loop);
        free(loop);
    }

    IsolateThread getCurrentThread() {
        return currentThread;
    }

    void run() {
        uv_run(loop, UV_RUN_DEFAULT());
    }
}
