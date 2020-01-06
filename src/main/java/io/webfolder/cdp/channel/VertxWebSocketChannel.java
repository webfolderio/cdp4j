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

import static java.util.concurrent.TimeUnit.MINUTES;

import java.util.concurrent.Semaphore;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.WebSocket;
import io.vertx.core.http.WebSocketConnectOptions;
import io.webfolder.cdp.exception.CdpException;
import io.webfolder.cdp.session.MessageHandler;
import io.webfolder.cdp.session.SessionFactory;

class VertxWebSocketChannel implements Channel {

    private final SessionFactory factory;

    private final MessageHandler handler;

    private final HttpClient httpClient;

    private final WebSocketConnectOptions options;

    private WebSocket webSocket;

    /**
     * 1000;
     * <i>
     * 1000 indicates a normal closure, meaning that the purpose for
     * which the connection was established has been fulfilled.
     * </i>
     */
    private static final short NORMAL = 1000;

    public VertxWebSocketChannel(SessionFactory          factory,
                                 HttpClient              httpClient,
                                 MessageHandler          handler,
                                 WebSocketConnectOptions options) {
        this.factory = factory;
        this.httpClient = httpClient;
        this.handler = handler;
        this.options = options;
    }

    @Override
    public boolean isOpen() {
        return ! webSocket.isClosed();
    }

    @Override
    public void disconnect() {
        webSocket.close(NORMAL, event -> factory.close());
    }

    @Override
    public void sendText(String message) {
        webSocket.writeTextMessage(message, null);
    }

    @Override
    public void connect() {
        Semaphore semaphore = new Semaphore(0);
        httpClient.webSocket(options, (Handler<AsyncResult<WebSocket>>) event -> {
            if (event.succeeded()) {
                webSocket = event.result();
                webSocket.textMessageHandler(content -> handler.process(content));
                webSocket.closeHandler(onCloseEvent -> factory.close());
            }
            semaphore.release();
        });
        try {
            semaphore.tryAcquire(1, MINUTES);
        } catch (InterruptedException e) {
            throw new CdpException(e);
        }
        if (webSocket == null) {
            throw new IllegalStateException();
        }
    }
}
