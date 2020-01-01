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
package io.webfolder.cdp.type.cachestorage;

import java.util.List;

import com.vimeo.stag.UseStag;

/**
 * Data entry
 */
@UseStag
public class DataEntry {
    private String requestURL;

    private String requestMethod;

    private List<Header> requestHeaders;

    private Double responseTime;

    private Integer responseStatus;

    private String responseStatusText;

    private CachedResponseType responseType;

    private List<Header> responseHeaders;

    /**
     * Request URL.
     */
    public String getRequestURL() {
        return requestURL;
    }

    /**
     * Request URL.
     */
    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    /**
     * Request method.
     */
    public String getRequestMethod() {
        return requestMethod;
    }

    /**
     * Request method.
     */
    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    /**
     * Request headers
     */
    public List<Header> getRequestHeaders() {
        return requestHeaders;
    }

    /**
     * Request headers
     */
    public void setRequestHeaders(List<Header> requestHeaders) {
        this.requestHeaders = requestHeaders;
    }

    /**
     * Number of seconds since epoch.
     */
    public Double getResponseTime() {
        return responseTime;
    }

    /**
     * Number of seconds since epoch.
     */
    public void setResponseTime(Double responseTime) {
        this.responseTime = responseTime;
    }

    /**
     * HTTP response status code.
     */
    public Integer getResponseStatus() {
        return responseStatus;
    }

    /**
     * HTTP response status code.
     */
    public void setResponseStatus(Integer responseStatus) {
        this.responseStatus = responseStatus;
    }

    /**
     * HTTP response status text.
     */
    public String getResponseStatusText() {
        return responseStatusText;
    }

    /**
     * HTTP response status text.
     */
    public void setResponseStatusText(String responseStatusText) {
        this.responseStatusText = responseStatusText;
    }

    /**
     * HTTP response type
     */
    public CachedResponseType getResponseType() {
        return responseType;
    }

    /**
     * HTTP response type
     */
    public void setResponseType(CachedResponseType responseType) {
        this.responseType = responseType;
    }

    /**
     * Response headers
     */
    public List<Header> getResponseHeaders() {
        return responseHeaders;
    }

    /**
     * Response headers
     */
    public void setResponseHeaders(List<Header> responseHeaders) {
        this.responseHeaders = responseHeaders;
    }
}
