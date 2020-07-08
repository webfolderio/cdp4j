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

import java.util.List;
import java.util.Map;

import io.webfolder.cdp.annotation.Domain;
import io.webfolder.cdp.annotation.EventName;
import io.webfolder.cdp.annotation.Experimental;
import io.webfolder.cdp.type.network.BlockedSetCookieWithReason;

/**
 * Fired when additional information about a responseReceived event is available from the network
 * stack
 * Not every responseReceived event will have an additional responseReceivedExtraInfo for
 * it, and responseReceivedExtraInfo may be fired before or after responseReceived
 */
@Experimental
@Domain("Network")
@EventName("responseReceivedExtraInfo")
public class ResponseReceivedExtraInfo {
    private String requestId;

    private List<BlockedSetCookieWithReason> blockedCookies;

    private Map<String, Object> headers;

    private String headersText;

    /**
     * Request identifier. Used to match this information to another responseReceived event.
     */
    public String getRequestId() {
        return requestId;
    }

    /**
     * Request identifier. Used to match this information to another responseReceived event.
     */
    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    /**
     * A list of cookies which were not stored from the response along with the corresponding
     * reasons for blocking. The cookies here may not be valid due to syntax errors, which
     * are represented by the invalid cookie line string instead of a proper cookie.
     */
    public List<BlockedSetCookieWithReason> getBlockedCookies() {
        return blockedCookies;
    }

    /**
     * A list of cookies which were not stored from the response along with the corresponding
     * reasons for blocking. The cookies here may not be valid due to syntax errors, which
     * are represented by the invalid cookie line string instead of a proper cookie.
     */
    public void setBlockedCookies(List<BlockedSetCookieWithReason> blockedCookies) {
        this.blockedCookies = blockedCookies;
    }

    /**
     * Raw response headers as they were received over the wire.
     */
    public Map<String, Object> getHeaders() {
        return headers;
    }

    /**
     * Raw response headers as they were received over the wire.
     */
    public void setHeaders(Map<String, Object> headers) {
        this.headers = headers;
    }

    /**
     * Raw response header text as it was received over the wire. The raw text may not always be
     * available, such as in the case of HTTP/2 or QUIC.
     */
    public String getHeadersText() {
        return headersText;
    }

    /**
     * Raw response header text as it was received over the wire. The raw text may not always be
     * available, such as in the case of HTTP/2 or QUIC.
     */
    public void setHeadersText(String headersText) {
        this.headersText = headersText;
    }
}
