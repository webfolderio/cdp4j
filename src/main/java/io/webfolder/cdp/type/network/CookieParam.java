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
 * Cookie parameter object
 */
@UseStag
public class CookieParam {
    private String name;

    private String value;

    private String url;

    private String domain;

    private String path;

    private Boolean secure;

    private Boolean httpOnly;

    private CookieSameSite sameSite;

    private Double expires;

    /**
     * Cookie name.
     */
    public String getName() {
        return name;
    }

    /**
     * Cookie name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Cookie value.
     */
    public String getValue() {
        return value;
    }

    /**
     * Cookie value.
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * The request-URI to associate with the setting of the cookie. This value can affect the
     * default domain and path values of the created cookie.
     */
    public String getUrl() {
        return url;
    }

    /**
     * The request-URI to associate with the setting of the cookie. This value can affect the
     * default domain and path values of the created cookie.
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Cookie domain.
     */
    public String getDomain() {
        return domain;
    }

    /**
     * Cookie domain.
     */
    public void setDomain(String domain) {
        this.domain = domain;
    }

    /**
     * Cookie path.
     */
    public String getPath() {
        return path;
    }

    /**
     * Cookie path.
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * True if cookie is secure.
     */
    public Boolean isSecure() {
        return secure;
    }

    /**
     * True if cookie is secure.
     */
    public void setSecure(Boolean secure) {
        this.secure = secure;
    }

    /**
     * True if cookie is http-only.
     */
    public Boolean isHttpOnly() {
        return httpOnly;
    }

    /**
     * True if cookie is http-only.
     */
    public void setHttpOnly(Boolean httpOnly) {
        this.httpOnly = httpOnly;
    }

    /**
     * Cookie SameSite type.
     */
    public CookieSameSite getSameSite() {
        return sameSite;
    }

    /**
     * Cookie SameSite type.
     */
    public void setSameSite(CookieSameSite sameSite) {
        this.sameSite = sameSite;
    }

    /**
     * Cookie expiration date, session cookie if not set
     */
    public Double getExpires() {
        return expires;
    }

    /**
     * Cookie expiration date, session cookie if not set
     */
    public void setExpires(Double expires) {
        this.expires = expires;
    }
}
