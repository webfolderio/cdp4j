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

import java.net.URI;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import io.webfolder.cdp.session.MessageHandler;
import io.webfolder.cdp.session.SessionFactory;

public class TooTallNateWebSocketListener extends WebSocketClient {

    private final SessionFactory factory;

    private final MessageHandler handler;

    public TooTallNateWebSocketListener(URI serverUri, SessionFactory factory, MessageHandler handler) {
        super(serverUri);
        this.factory = factory;
        this.handler = handler;
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        // ignore
    }

    @Override
    public void onMessage(String message) {
        handler.process(message);
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        factory.close();
    }

    @Override
    public void onError(Exception ex) {
        // ignore
    }
}
