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

import static com.neovisionaries.ws.client.DualStackMode.IPV4_ONLY;

import java.io.IOException;

import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketFactory;

import io.webfolder.cdp.exception.CdpException;
import io.webfolder.cdp.session.MessageHandler;
import io.webfolder.cdp.session.SessionFactory;

public class NvWebSocketFactory implements ChannelFactory, AutoCloseable {

    private static final int CONNECTION_TIMEOUT = 10_000; // 10 seconds

    private final WebSocketFactory factory = new WebSocketFactory();

    private WebSocket webSocket;

    public NvWebSocketFactory() {
        this(CONNECTION_TIMEOUT);
    }

    public NvWebSocketFactory(int connectionTimeout) {
        factory.setConnectionTimeout(connectionTimeout);
        factory.setDualStackMode(IPV4_ONLY);
        factory.setVerifyHostname(false);
    }

    @Override
    public Channel createChannel(Connection connection, SessionFactory factory, MessageHandler handler) {
        if ( webSocket != null ) {
            throw new IllegalStateException();
        }
        String url = ((WebSocketConnection) connection).getUrl();
        try {
            webSocket = this.factory.createSocket(url);
        } catch (IOException e) {
            throw new CdpException(e);
        }
        webSocket.addListener(new NvWebSocketListener(factory, handler));
        return new NvWebSocketChannel(webSocket);
    }

    @Override
    public void close() {
        if ( webSocket != null && webSocket.isOpen() ) {
            webSocket.disconnect();
        }
    }
}
