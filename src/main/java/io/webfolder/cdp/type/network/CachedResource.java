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

import com.vimeo.stag.UseStag;

/**
 * Information about the cached resource
 */
@UseStag
public class CachedResource {
    private String url;

    private ResourceType type;

    private Response response;

    private Double bodySize;

    /**
     * Resource URL. This is the url of the original network request.
     */
    public String getUrl() {
        return url;
    }

    /**
     * Resource URL. This is the url of the original network request.
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Type of this resource.
     */
    public ResourceType getType() {
        return type;
    }

    /**
     * Type of this resource.
     */
    public void setType(ResourceType type) {
        this.type = type;
    }

    /**
     * Cached response data.
     */
    public Response getResponse() {
        return response;
    }

    /**
     * Cached response data.
     */
    public void setResponse(Response response) {
        this.response = response;
    }

    /**
     * Cached response body size.
     */
    public Double getBodySize() {
        return bodySize;
    }

    /**
     * Cached response body size.
     */
    public void setBodySize(Double bodySize) {
        this.bodySize = bodySize;
    }
}
