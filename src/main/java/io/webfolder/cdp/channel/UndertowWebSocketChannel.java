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

import java.io.IOException;
import java.util.concurrent.CancellationException;

import org.xnio.IoFuture;

import io.undertow.websockets.core.WebSocketChannel;
import io.undertow.websockets.core.WebSockets;
import io.webfolder.cdp.exception.CdpException;
import io.webfolder.cdp.session.MessageHandler;
import io.webfolder.cdp.session.SessionFactory;

public class UndertowWebSocketChannel implements Channel {

    private final IoFuture<WebSocketChannel> future;

    private final SessionFactory factory;

    private final MessageHandler handler;

    private WebSocketChannel webSocket;

    public UndertowWebSocketChannel(IoFuture<WebSocketChannel> future,
                                    SessionFactory factory,
                                    MessageHandler handler) {
        this.future = future;
        this.factory = factory;
        this.handler = handler;
    }

    @Override
    public boolean isOpen() {
        return webSocket.isOpen();
    }

    @Override
    public void disconnect() {
        if (webSocket.isOpen()) {
            WebSockets.sendClose(CLOSE_STATUS_CODE, CLOSE_REASON_TEXT, webSocket, null);
            try {
                webSocket.close();
            } catch (IOException e) {
                // ignore
            }
        }
    }

    @Override
    public void sendText(String message) {
        WebSockets.sendText(message, webSocket, null);
    }

    @Override
    public void connect() {
        try {
            webSocket = future.get();
        } catch (CancellationException | IOException e) {
            throw new CdpException(e);
        }
        try {
            webSocket.getReceiveSetter().set(new UndertowWebSocketListener(factory, handler));
            webSocket.resumeReceives();
        } catch (CancellationException e) {
            throw new CdpException(e);
        }
    }
}
