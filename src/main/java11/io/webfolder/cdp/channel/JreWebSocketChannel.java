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

import java.net.http.WebSocket;
import java.util.concurrent.CompletableFuture;

public class JreWebSocketChannel implements Channel {

    private final CompletableFuture<WebSocket> future;

    private WebSocket webSocket;

    public JreWebSocketChannel(CompletableFuture<WebSocket> future) {
        this.future = future;
    }

    @Override
    public boolean isOpen() {
        return ! webSocket.isInputClosed() &&
               ! webSocket.isOutputClosed();
    }

    @Override
    public void disconnect() {
        if (isOpen()) {
            webSocket.sendClose(CLOSE_STATUS_CODE, CLOSE_REASON_TEXT);
            webSocket.abort();
        }
    }

    @Override
    public void sendText(String message) {
        webSocket.sendText(message, true);
    }

    @Override
    public void connect() {
        webSocket = future.join();
    }
}
