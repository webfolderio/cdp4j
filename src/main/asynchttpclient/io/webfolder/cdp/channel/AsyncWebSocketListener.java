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

import org.asynchttpclient.ws.WebSocket;
import org.asynchttpclient.ws.WebSocketListener;

import io.webfolder.cdp.session.MessageHandler;
import io.webfolder.cdp.session.SessionFactory;

public class AsyncWebSocketListener implements WebSocketListener {

    private final SessionFactory factory;

    private final MessageHandler handler;

    public AsyncWebSocketListener(SessionFactory factory, MessageHandler handler) {
        this.factory = factory;
        this.handler = handler;
    }

    @Override
    public void onTextFrame(String payload, boolean finalFragment, int rsv) {
        handler.process(payload);
    }

    @Override
    public void onOpen(WebSocket websocket) {
        // ignore
    }

    @Override
    public void onClose(WebSocket websocket, int code, String reason) {
        factory.close();
    }

    @Override
    public void onError(Throwable t) {
        // ignore
    }
}
