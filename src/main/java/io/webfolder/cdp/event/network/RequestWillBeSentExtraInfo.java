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
import io.webfolder.cdp.type.network.BlockedCookieWithReason;

/**
 * Fired when additional information about a requestWillBeSent event is available from the
 * network stack
 * Not every requestWillBeSent event will have an additional
 * requestWillBeSentExtraInfo fired for it, and there is no guarantee whether requestWillBeSent
 * or requestWillBeSentExtraInfo will be fired first for the same request
 */
@Experimental
@Domain("Network")
@EventName("requestWillBeSentExtraInfo")
public class RequestWillBeSentExtraInfo {
    private String requestId;

    private List<BlockedCookieWithReason> associatedCookies;

    private Map<String, Object> headers;

    /**
     * Request identifier. Used to match this information to an existing requestWillBeSent event.
     */
    public String getRequestId() {
        return requestId;
    }

    /**
     * Request identifier. Used to match this information to an existing requestWillBeSent event.
     */
    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    /**
     * A list of cookies potentially associated to the requested URL. This includes both cookies sent with
     * the request and the ones not sent; the latter are distinguished by having blockedReason field set.
     */
    public List<BlockedCookieWithReason> getAssociatedCookies() {
        return associatedCookies;
    }

    /**
     * A list of cookies potentially associated to the requested URL. This includes both cookies sent with
     * the request and the ones not sent; the latter are distinguished by having blockedReason field set.
     */
    public void setAssociatedCookies(List<BlockedCookieWithReason> associatedCookies) {
        this.associatedCookies = associatedCookies;
    }

    /**
     * Raw request headers as they will be sent over the wire.
     */
    public Map<String, Object> getHeaders() {
        return headers;
    }

    /**
     * Raw request headers as they will be sent over the wire.
     */
    public void setHeaders(Map<String, Object> headers) {
        this.headers = headers;
    }
}
