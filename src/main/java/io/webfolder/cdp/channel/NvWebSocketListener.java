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

import com.neovisionaries.ws.client.ThreadType;
import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketAdapter;
import com.neovisionaries.ws.client.WebSocketFrame;

import io.webfolder.cdp.session.MessageHandler;
import io.webfolder.cdp.session.SessionFactory;

import static java.nio.charset.StandardCharsets.UTF_8;

public class NvWebSocketListener extends WebSocketAdapter {

    private final SessionFactory factory;

    private final MessageHandler handler;

    public NvWebSocketListener(SessionFactory factory, MessageHandler handler) {
        this.factory = factory;
        this.handler = handler;
    }

    @Override
    public void onTextMessage(WebSocket websocket, byte[] data) throws Exception {
        handler.process(new String(data, 0, data.length, UTF_8));
    }

    @Override
    public void onDisconnected(WebSocket websocket,
                               WebSocketFrame serverCloseFrame,
                               WebSocketFrame clientCloseFrame,
                               boolean closedByServer) throws Exception {
        factory.close();
    }

    @Override
    public void onThreadCreated(WebSocket websocket,
                                ThreadType threadType,
                                Thread thread) throws Exception {
        thread.setName("cdp4j-WebSocket-" + thread.getName());
    }
}
