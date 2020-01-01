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
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.Future;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.client.WebSocketClient;

import io.webfolder.cdp.exception.CdpException;
import io.webfolder.cdp.session.MessageHandler;
import io.webfolder.cdp.session.SessionFactory;

public class JettyWebSocketFactory implements ChannelFactory, AutoCloseable {

    private final WebSocketClient client;

    public JettyWebSocketFactory() {
        client = new WebSocketClient();
        try {
            client.start();
        } catch (Exception e) {
            throw new CdpException(e);
        }
    }

    public JettyWebSocketFactory(WebSocketClient client) {
        this.client = client;
    }

    @Override
    public Channel createChannel(Connection connection, SessionFactory factory, MessageHandler handler) {
        String url = ((WebSocketConnection) connection).getUrl();
        Future<Session> future = null;
        try {
            future = client.connect(new JettyWebSocketListener(factory, handler), new URI(url));
        } catch (IOException | URISyntaxException e) {
            throw new CdpException(e);
        }
        return new JettyWebSocketChannel(future);
    }

    @Override
    public void close() {
        if ( client != null && ! client.isStopped() ) {
            try {
                client.stop();
            } catch (Exception e) {
                // ignore
            }
        }
    }
}
