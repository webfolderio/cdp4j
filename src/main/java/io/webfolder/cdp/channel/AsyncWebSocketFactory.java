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

import static java.lang.Integer.MAX_VALUE;
import static org.asynchttpclient.Dsl.asyncHttpClient;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClientConfig;
import org.asynchttpclient.netty.ws.NettyWebSocket;
import org.asynchttpclient.ws.WebSocketUpgradeHandler;

import io.webfolder.cdp.session.MessageHandler;
import io.webfolder.cdp.session.SessionFactory;

public class AsyncWebSocketFactory implements ChannelFactory, AutoCloseable {

    private static final int CONNECTION_TIMEOUT = 10_000; // 10 seconds

    private final AsyncHttpClient client;

    public AsyncWebSocketFactory() {
        this(CONNECTION_TIMEOUT);
    }

    public AsyncWebSocketFactory(int connectionTimeout) {
        this(connectionTimeout, 1, "cdp4j-netty");
    }

    public AsyncWebSocketFactory(int connectionTimeout,
                                        int ioThreadsCount,
                                        String threadPoolName) {
        DefaultAsyncHttpClientConfig config = new DefaultAsyncHttpClientConfig.Builder()
                                                    .setWebSocketMaxFrameSize(MAX_VALUE)
                                                    .setConnectTimeout(connectionTimeout)
                                                    .setThreadPoolName(threadPoolName)
                                                    .setIoThreadsCount(ioThreadsCount)
                                                .build();
        client = asyncHttpClient(config);
    }

    public AsyncWebSocketFactory(AsyncHttpClient client) {
        this.client = client;
    }

    @Override
    public Channel createChannel(Connection connection, SessionFactory factory, MessageHandler messageHandler) {
        String url = ((WebSocketConnection) connection).getUrl();
        AsyncWebSocketListener messageAdapter = new AsyncWebSocketListener(factory, messageHandler);
        WebSocketUpgradeHandler upgradeHandler = new WebSocketUpgradeHandler.Builder()
                                                                            .addWebSocketListener(messageAdapter)
                                                                            .build();
        CompletableFuture<NettyWebSocket> future = client.prepareGet(url)
                                                         .execute(upgradeHandler)
                                                         .toCompletableFuture();
        AsyncWebSocketChannel channel = new AsyncWebSocketChannel(future);
        return channel;
    }

    @Override
    public void close() {
        if ( client != null && ! client.isClosed() ) {
            try {
                client.close();
            } catch (IOException e) {
                // ignore
            }
        }
    }
}
