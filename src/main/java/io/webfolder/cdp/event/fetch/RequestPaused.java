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
package io.webfolder.cdp.event.fetch;

import java.util.List;

import com.vimeo.stag.UseStag;

import io.webfolder.cdp.annotation.Domain;
import io.webfolder.cdp.annotation.EventName;
import io.webfolder.cdp.type.fetch.HeaderEntry;
import io.webfolder.cdp.type.network.ErrorReason;
import io.webfolder.cdp.type.network.Request;
import io.webfolder.cdp.type.network.ResourceType;

/**
 * Issued when the domain is enabled and the request URL matches the
 * specified filter
 * The request is paused until the client responds
 * with one of continueRequest, failRequest or fulfillRequest
 * The stage of the request can be determined by presence of responseErrorReason
 * and responseStatusCode -- the request is at the response stage if either
 * of these fields is present and in the request stage otherwise
 */
@Domain("Fetch")
@EventName("requestPaused")
@UseStag
public class RequestPaused {
    private String requestId;

    private Request request;

    private String frameId;

    private ResourceType resourceType;

    private ErrorReason responseErrorReason;

    private Integer responseStatusCode;

    private List<HeaderEntry> responseHeaders;

    private String networkId;

    /**
     * Each request the page makes will have a unique id.
     */
    public String getRequestId() {
        return requestId;
    }

    /**
     * Each request the page makes will have a unique id.
     */
    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    /**
     * The details of the request.
     */
    public Request getRequest() {
        return request;
    }

    /**
     * The details of the request.
     */
    public void setRequest(Request request) {
        this.request = request;
    }

    /**
     * The id of the frame that initiated the request.
     */
    public String getFrameId() {
        return frameId;
    }

    /**
     * The id of the frame that initiated the request.
     */
    public void setFrameId(String frameId) {
        this.frameId = frameId;
    }

    /**
     * How the requested resource will be used.
     */
    public ResourceType getResourceType() {
        return resourceType;
    }

    /**
     * How the requested resource will be used.
     */
    public void setResourceType(ResourceType resourceType) {
        this.resourceType = resourceType;
    }

    /**
     * Response error if intercepted at response stage.
     */
    public ErrorReason getResponseErrorReason() {
        return responseErrorReason;
    }

    /**
     * Response error if intercepted at response stage.
     */
    public void setResponseErrorReason(ErrorReason responseErrorReason) {
        this.responseErrorReason = responseErrorReason;
    }

    /**
     * Response code if intercepted at response stage.
     */
    public Integer getResponseStatusCode() {
        return responseStatusCode;
    }

    /**
     * Response code if intercepted at response stage.
     */
    public void setResponseStatusCode(Integer responseStatusCode) {
        this.responseStatusCode = responseStatusCode;
    }

    /**
     * Response headers if intercepted at the response stage.
     */
    public List<HeaderEntry> getResponseHeaders() {
        return responseHeaders;
    }

    /**
     * Response headers if intercepted at the response stage.
     */
    public void setResponseHeaders(List<HeaderEntry> responseHeaders) {
        this.responseHeaders = responseHeaders;
    }

    /**
     * If the intercepted request had a corresponding Network.requestWillBeSent event fired for it,
     * then this networkId will be the same as the requestId present in the requestWillBeSent event.
     */
    public String getNetworkId() {
        return networkId;
    }

    /**
     * If the intercepted request had a corresponding Network.requestWillBeSent event fired for it,
     * then this networkId will be the same as the requestId present in the requestWillBeSent event.
     */
    public void setNetworkId(String networkId) {
        this.networkId = networkId;
    }
}
