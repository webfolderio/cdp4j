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
package io.webfolder.cdp.type.browser;

import com.vimeo.stag.UseStag;

@UseStag
public class GetVersionResult {
    private String protocolVersion;

    private String product;

    private String revision;

    private String userAgent;

    private String jsVersion;

    public String getProtocolVersion() {
        return protocolVersion;
    }

    public String getProduct() {
        return product;
    }

    public String getRevision() {
        return revision;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public String getJsVersion() {
        return jsVersion;
    }

    public void setProtocolVersion(String protocolVersion) {
        this.protocolVersion = protocolVersion;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public void setRevision(String revision) {
        this.revision = revision;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public void setJsVersion(String jsVersion) {
        this.jsVersion = jsVersion;
    }
}
