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
package io.webfolder.cdp.event.network;

import io.webfolder.cdp.annotation.Domain;
import io.webfolder.cdp.annotation.EventName;
import io.webfolder.cdp.type.network.Initiator;

/**
 * Fired upon WebSocket creation
 */
@Domain("Network")
@EventName("webSocketCreated")
public class WebSocketCreated {
    private String requestId;

    private String url;

    private Initiator initiator;

    /**
     * Request identifier.
     */
    public String getRequestId() {
        return requestId;
    }

    /**
     * Request identifier.
     */
    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    /**
     * WebSocket request URL.
     */
    public String getUrl() {
        return url;
    }

    /**
     * WebSocket request URL.
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Request initiator.
     */
    public Initiator getInitiator() {
        return initiator;
    }

    /**
     * Request initiator.
     */
    public void setInitiator(Initiator initiator) {
        this.initiator = initiator;
    }
}
