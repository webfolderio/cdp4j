package io.webfolder.cdp.libuv;

import static io.webfolder.cdp.libuv.Libuv.CDP4J_UV_SUCCESS;
import static io.webfolder.cdp.libuv.Libuv.SIGKILL;
import static io.webfolder.cdp.libuv.Libuv.UV_CREATE_PIPE;
import static io.webfolder.cdp.libuv.Libuv.UV_IGNORE;
import static io.webfolder.cdp.libuv.Libuv.UV_INHERIT_FD;
import static io.webfolder.cdp.libuv.Libuv.UV_PROCESS_WINDOWS_VERBATIM_ARGUMENTS;
import static io.webfolder.cdp.libuv.Libuv.UV_READABLE_PIPE;
import static io.webfolder.cdp.libuv.Libuv.UV_WRITABLE_PIPE;
import static io.webfolder.cdp.libuv.Libuv.WINDOWS;
import static io.webfolder.cdp.libuv.Libuv.cdp4j_spawn_process;
import static io.webfolder.cdp.libuv.Libuv.cdp4j_start_read;
import static io.webfolder.cdp.libuv.Libuv.cdp4j_write_pipe;
import static io.webfolder.cdp.libuv.Libuv.objectHandles;
import static io.webfolder.cdp.libuv.Libuv.uv_disable_stdio_inheritance;
import static io.webfolder.cdp.libuv.Libuv.uv_err_name;
import static io.webfolder.cdp.libuv.Libuv.uv_process_kill;
import static io.webfolder.cdp.libuv.UvLogger.debug;
import static org.graalvm.nativeimage.UnmanagedMemory.free;
import static org.graalvm.nativeimage.UnmanagedMemory.malloc;
import static org.graalvm.nativeimage.c.struct.SizeOf.get;
import static org.graalvm.nativeimage.c.type.CTypeConversion.toCString;
import static org.graalvm.nativeimage.c.type.CTypeConversion.toJavaString;
import static org.graalvm.word.WordFactory.nullPointer;

import org.graalvm.nativeimage.ObjectHandle;
import org.graalvm.nativeimage.StackValue;
import org.graalvm.nativeimage.c.struct.SizeOf;
import org.graalvm.nativeimage.c.type.CCharPointerPointer;
import org.graalvm.nativeimage.c.type.CTypeConversion.CCharPointerHolder;

import io.webfolder.cdp.libuv.Libuv.async;
import io.webfolder.cdp.libuv.Libuv.context_write;
import io.webfolder.cdp.libuv.Libuv.process;
import io.webfolder.cdp.libuv.Libuv.process_options;
import io.webfolder.cdp.libuv.Libuv.stdio_container;

public class UvProcess {

    private final UvLoop loop;

    private process process;

    private UvPipe inPipe;

    private UvPipe outPipe;

    private CCharPointerHolder file;

    private CCharPointerPointer args;

    private CCharPointerHolder[] argsHolder;

    private int argsLength;

    public UvProcess(UvLoop loop) {
        debug("-> UvProcess()");
        this.loop = loop;
        process = malloc(get(process.class));
        debug("<- UvProcess()");
    }

    public boolean spawn(String   exe,
                         String[] arguments,
                         boolean  redirectOut,
                         boolean  redirectErr) {
    	uv_disable_stdio_inheritance();

        debug("-> UvProcess.spawn()");

        inPipe = loop.createPipe();
        if (inPipe == null) {
            debug("<- UvProcess.spawn()[inPipe]: false");
            return false;
        }
        outPipe = loop.createPipe();
        if (outPipe == null) {
            debug("<- UvProcess.spawn()[outPipe]: false");
            return false;
        }
        outPipe.getPeer().data(loop.getCurrentThread());
        inPipe.getPeer().data(loop.getCurrentThread());

        stdio_container container = StackValue.get(5, get(stdio_container.class));

        container.addressOf(0).flags(UV_IGNORE()); // stdin
        container.addressOf(1).flags(redirectOut ? UV_INHERIT_FD() : UV_IGNORE()); // stdout
        container.addressOf(2).flags(redirectErr ? UV_INHERIT_FD() : UV_IGNORE()); // stderr
        container.addressOf(3).flags(UV_CREATE_PIPE() | UV_WRITABLE_PIPE() | UV_READABLE_PIPE()); // fd(3)
        container.addressOf(4).flags(UV_CREATE_PIPE() | UV_READABLE_PIPE() | UV_WRITABLE_PIPE()); // fd(4)

        if (redirectOut) {
            container.addressOf(1).data().fd(1);
        }
        if (redirectErr) {
            container.addressOf(2).data().fd(2);
        }

        container.addressOf(3).data().stream(inPipe.getPeer());
        container.addressOf(4).data().stream(outPipe.getPeer());

        if (redirectOut) {
            container.addressOf(1).data().fd(1);
        }
        if (redirectErr) {
            container.addressOf(2).data().fd(2);
        }

        this.file = toCString(exe);

        process_options options = malloc(get(process_options.class));
        options.stdio_count(5);
        options.stdio(container);
        options.file(this.file.get());

        if (WINDOWS) {
            options.flags(UV_PROCESS_WINDOWS_VERBATIM_ARGUMENTS());
        }

        // inherit from parent process
        options.cwd(nullPointer());
        // inherit from parent process
        options.env(nullPointer());

        argsLength = arguments.length + 1;
        args = malloc(get(CCharPointerPointer.class) * argsLength);
        argsHolder = new CCharPointerHolder[argsLength];
        for (int i = 0; i < arguments.length; i++) {
            CCharPointerHolder arg = toCString(arguments[i]);
            argsHolder[i] = arg;
            args.addressOf(i)
                .write(arg.get());
        }
        args.addressOf(argsLength - 1)
            .write(null);

        options.args(args);

        process.data(loop.getCurrentThread());

        int ret = cdp4j_spawn_process(loop.getPeer(),
                                      process,
                                      options);

        file.close();
        for (int i = 0; i < argsLength - 1; i++) {
            argsHolder[i].close();
        }
        free(args);

        if ( ret != CDP4J_UV_SUCCESS() ) {
            debug("<- UvProcess.spawn()[cdp4j_spawn_process()]: false, " +
                        toJavaString(uv_err_name(ret)));
            return false;
        }

        if ( cdp4j_start_read(outPipe.getPeer())
                              != CDP4J_UV_SUCCESS() ) {
            debug("<- UvProcess.spawn()[cdp4j_start_read()]: false");
            return false;
        }
        debug("<- UvProcess.spawn(): true");

        return true;
    }

    public boolean kill() {
        if ( inPipe != null ) {
            inPipe.dispose();
        }
        if ( outPipe != null ) {
            outPipe.dispose();
        }
        if ( loop != null ) {
            loop.dispose();
        }
        int ret = uv_process_kill(process, SIGKILL());
        free(process);
        return ret == CDP4J_UV_SUCCESS();
    }

    public void writeAsync(String payload) {
        int ret = CDP4J_UV_SUCCESS() - 1;
        context_write context = nullPointer();
        async async = nullPointer();
        try {
            context = malloc(SizeOf.get(context_write.class));
            context.pipe(inPipe.getPeer());

            CCharPointerHolder cstring = toCString(payload);
            ObjectHandle pinnedPayload = objectHandles.create(cstring);
            context.len(payload.length() + 1);
            context.data(cstring.get());
            context.pinned_payload(pinnedPayload);

            async = malloc(SizeOf.get(async.class));
            async.data(context);

            ret = cdp4j_write_pipe(getLoop().getPeer(),
                                       async,
                                       context);
        } finally {
            if ( ret != CDP4J_UV_SUCCESS() ) {
                if (context.isNonNull()) {
                    if (context.data().isNonNull()) {
                    	CCharPointerHolder cstring = objectHandles.get(context.pinned_payload());
                    	if ( cstring != null ) {
                    		cstring.close();
                    	}
                    	objectHandles.destroy(context.pinned_payload());
                    }
                    free(context);
                }
                if (async.isNonNull()) {
                    free(async);
                }
            }
        }
    }

    public UvLoop getLoop() {
        return loop;
    }

    public void dispose() {
    	if ( outPipe != null ) {
    		outPipe.dispose();
    	}
    	if ( inPipe != null ) {
    		inPipe.dispose();
    	}
        if (process.isNonNull()) {
            free(process);
        }
    }
}
