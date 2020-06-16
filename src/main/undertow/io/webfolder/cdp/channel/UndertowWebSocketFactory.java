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

import static io.undertow.websockets.client.WebSocketClient.connectionBuilder;
import static org.xnio.OptionMap.EMPTY;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.xnio.IoFuture;
import org.xnio.Xnio;
import org.xnio.XnioWorker;

import io.undertow.connector.ByteBufferPool;
import io.undertow.server.DefaultByteBufferPool;
import io.undertow.websockets.client.WebSocketClient.ConnectionBuilder;
import io.undertow.websockets.core.WebSocketChannel;
import io.webfolder.cdp.exception.CdpException;
import io.webfolder.cdp.session.MessageHandler;
import io.webfolder.cdp.session.SessionFactory;

public class UndertowWebSocketFactory implements ChannelFactory, AutoCloseable {

    private static final int DEFAULT_POOL_BUFFER_SIZE = 8192;

    private final XnioWorker worker;

    private final ByteBufferPool pool;

    public UndertowWebSocketFactory() {
        try {
            worker = Xnio.getInstance().createWorker(EMPTY);
        } catch (IllegalArgumentException | IOException e) {
            throw new CdpException(e);
        }
        pool = new DefaultByteBufferPool(false, DEFAULT_POOL_BUFFER_SIZE);
    }

    public UndertowWebSocketFactory(XnioWorker worker, ByteBufferPool pool) {
        this.worker = worker;
        this.pool = pool;
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
        ConnectionBuilder builder = connectionBuilder(worker, pool, uri);
        IoFuture<WebSocketChannel> future = builder.connect();
        return new UndertowWebSocketChannel(future, factory, handler);
    }

    @Override
    public void close() {
        if ( worker != null ) {
            worker.shutdownNow();
        }
        if ( pool != null ) {
            pool.close();
        }
    }
}
