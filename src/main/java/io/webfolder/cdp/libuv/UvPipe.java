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
import static io.webfolder.cdp.libuv.Libuv.uv_pipe_init;
import static io.webfolder.cdp.libuv.Libuv.uv_stream_set_blocking;
import static io.webfolder.cdp.libuv.UvLogger.debug;
import static org.graalvm.nativeimage.UnmanagedMemory.free;
import static org.graalvm.nativeimage.UnmanagedMemory.malloc;
import static org.graalvm.nativeimage.c.struct.SizeOf.get;

import io.webfolder.cdp.libuv.Libuv.pipe;

class UvPipe {

    private final UvLoop loop;

    private final pipe pipe;

    UvPipe(UvLoop loop) {
        this.loop = loop;
        pipe = malloc(get(pipe.class));
    }

    boolean init() {
        debug("-> UvPipe.init()");
        if ( uv_pipe_init(loop.getPeer(), pipe, 0) != CDP4J_UV_SUCCESS() ) {
            debug("<- UvPipe.init(): false");
            return false;
        }
        debug("-> uv_stream_set_blocking()");
        int ret = uv_stream_set_blocking(pipe, 1);
        debug("<- uv_stream_set_blocking(): " + ret);
        debug("<- UvPipe.init(): true");
        return true;
    }

    pipe getPeer() {
        return pipe;
    }

    void dispose() {
        if (pipe.isNonNull()) {
            free(pipe);
        }
    }
}
