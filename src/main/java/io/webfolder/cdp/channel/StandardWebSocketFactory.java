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

import static javax.websocket.ClientEndpointConfig.Builder.create;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.websocket.DeploymentException;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

import io.webfolder.cdp.exception.CdpException;
import io.webfolder.cdp.session.MessageHandler;
import io.webfolder.cdp.session.SessionFactory;

public class StandardWebSocketFactory implements ChannelFactory {

    private final WebSocketContainer webSocketContainer;

    public StandardWebSocketFactory(WebSocketContainer webSocketContainer) {
        this.webSocketContainer = webSocketContainer;
    }

    @Override
    public Channel createChannel(Connection connection, SessionFactory factory, MessageHandler handler) {
        String url = ((WebSocketConnection) connection).getUrl();
        URI uri;
        try {
            uri = new URI(url);
        } catch (URISyntaxException e) {
            throw new CdpException(e);
        }
        try {
            StandardWebSocketListener listener = new StandardWebSocketListener(factory, handler);
            Session session = webSocketContainer.connectToServer(listener,
                                                                 create().build(),
                                                                 uri);
            session.addMessageHandler(listener);
            return new StandardWebSocketChannel(session);
        } catch (DeploymentException | IOException e) {
            throw new CdpException(e);
        }
    }
}
