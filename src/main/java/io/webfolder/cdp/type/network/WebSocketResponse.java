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
package io.webfolder.cdp.type.network;

import java.util.HashMap;
import java.util.Map;

import com.vimeo.stag.UseStag;

/**
 * WebSocket response data
 */
@UseStag
public class WebSocketResponse {
    private Integer status;

    private String statusText;

    private Map<String, Object> headers = new HashMap<>();

    private String headersText;

    private Map<String, Object> requestHeaders = new HashMap<>();

    private String requestHeadersText;

    /**
     * HTTP response status code.
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * HTTP response status code.
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * HTTP response status text.
     */
    public String getStatusText() {
        return statusText;
    }

    /**
     * HTTP response status text.
     */
    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }

    /**
     * HTTP response headers.
     */
    public Map<String, Object> getHeaders() {
        return headers;
    }

    /**
     * HTTP response headers.
     */
    public void setHeaders(Map<String, Object> headers) {
        this.headers = headers;
    }

    /**
     * HTTP response headers text.
     */
    public String getHeadersText() {
        return headersText;
    }

    /**
     * HTTP response headers text.
     */
    public void setHeadersText(String headersText) {
        this.headersText = headersText;
    }

    /**
     * HTTP request headers.
     */
    public Map<String, Object> getRequestHeaders() {
        return requestHeaders;
    }

    /**
     * HTTP request headers.
     */
    public void setRequestHeaders(Map<String, Object> requestHeaders) {
        this.requestHeaders = requestHeaders;
    }

    /**
     * HTTP request headers text.
     */
    public String getRequestHeadersText() {
        return requestHeadersText;
    }

    /**
     * HTTP request headers text.
     */
    public void setRequestHeadersText(String requestHeadersText) {
        this.requestHeadersText = requestHeadersText;
    }
}
