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
package io.webfolder.cdp.type.audits;

/**
 * This information is currently necessary, as the front-end has a difficult
 * time finding a specific cookie
 * With this, we can convey specific error
 * information without the cookie
 */
public class SameSiteCookieIssueDetails {
    private AffectedCookie cookie;

    private SameSiteCookieWarningReason cookieWarningReasons;

    private SameSiteCookieExclusionReason cookieExclusionReasons;

    private SameSiteCookieOperation operation;

    private String siteForCookies;

    private String cookieUrl;

    private AffectedRequest request;

    public AffectedCookie getCookie() {
        return cookie;
    }

    public void setCookie(AffectedCookie cookie) {
        this.cookie = cookie;
    }

    public SameSiteCookieWarningReason getCookieWarningReasons() {
        return cookieWarningReasons;
    }

    public void setCookieWarningReasons(SameSiteCookieWarningReason cookieWarningReasons) {
        this.cookieWarningReasons = cookieWarningReasons;
    }

    public SameSiteCookieExclusionReason getCookieExclusionReasons() {
        return cookieExclusionReasons;
    }

    public void setCookieExclusionReasons(SameSiteCookieExclusionReason cookieExclusionReasons) {
        this.cookieExclusionReasons = cookieExclusionReasons;
    }

    /**
     * Optionally identifies the site-for-cookies and the cookie url, which
     * may be used by the front-end as additional context.
     */
    public SameSiteCookieOperation getOperation() {
        return operation;
    }

    /**
     * Optionally identifies the site-for-cookies and the cookie url, which
     * may be used by the front-end as additional context.
     */
    public void setOperation(SameSiteCookieOperation operation) {
        this.operation = operation;
    }

    public String getSiteForCookies() {
        return siteForCookies;
    }

    public void setSiteForCookies(String siteForCookies) {
        this.siteForCookies = siteForCookies;
    }

    public String getCookieUrl() {
        return cookieUrl;
    }

    public void setCookieUrl(String cookieUrl) {
        this.cookieUrl = cookieUrl;
    }

    public AffectedRequest getRequest() {
        return request;
    }

    public void setRequest(AffectedRequest request) {
        this.request = request;
    }
}
