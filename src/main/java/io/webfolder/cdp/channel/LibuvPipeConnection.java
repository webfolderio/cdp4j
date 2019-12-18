/**
 * cdp4j Commercial License
 *
 * Copyright 2017, 2019 WebFolder OÜ
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

import static java.lang.System.arraycopy;
import static java.nio.charset.StandardCharsets.UTF_8;

import io.webfolder.cdp.libuv.UvLoop;
import io.webfolder.cdp.libuv.UvProcess;
import io.webfolder.cdp.session.MessageHandler;

public class LibuvPipeConnection implements Connection {

    private static final char MESSAGE_SEPERATOR = '\0';

    private static final ThreadLocal<LibuvPipeConnection> INSTANCE = new ThreadLocal<>();

    private final UvLoop loop;

    private final UvProcess process;

    private byte[] remaining;

    private MessageHandler handler;

    public LibuvPipeConnection(UvLoop loop, UvProcess process) {
        this.loop = loop;
        this.process = process;
    }

    public void onResponse(byte[] data) {
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

    public UvLoop getLoop() {
        return loop;
    }

    public UvProcess getProcess() {
        return process;
    }

    public static void setPipeConnection(LibuvPipeConnection connection) {
        INSTANCE.set(connection);
    }

    public static LibuvPipeConnection getPipeConnection() {
        return INSTANCE.get();
    }

    public void setMessageHandler(MessageHandler handler) {
        this.handler = handler;
    }
}
