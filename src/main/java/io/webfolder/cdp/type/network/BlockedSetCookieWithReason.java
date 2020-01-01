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

import java.util.List;

import com.vimeo.stag.UseStag;

import io.webfolder.cdp.annotation.Experimental;

/**
 * A cookie which was not stored from a response with the corresponding reason
 */
@Experimental
@UseStag
public class BlockedSetCookieWithReason {
    private List<Cookie> blockedReasons;

    private String cookieLine;

    private Cookie cookie;

    /**
     * The reason(s) this cookie was blocked.
     */
    public List<Cookie> getBlockedReasons() {
        return blockedReasons;
    }

    /**
     * The reason(s) this cookie was blocked.
     */
    public void setBlockedReasons(List<Cookie> blockedReasons) {
        this.blockedReasons = blockedReasons;
    }

    /**
     * The string representing this individual cookie as it would appear in the header.
     * This is not the entire "cookie" or "set-cookie" header which could have multiple cookies.
     */
    public String getCookieLine() {
        return cookieLine;
    }

    /**
     * The string representing this individual cookie as it would appear in the header.
     * This is not the entire "cookie" or "set-cookie" header which could have multiple cookies.
     */
    public void setCookieLine(String cookieLine) {
        this.cookieLine = cookieLine;
    }

    /**
     * The cookie object which represents the cookie which was not stored. It is optional because
     * sometimes complete cookie information is not available, such as in the case of parsing
     * errors.
     */
    public Cookie getCookie() {
        return cookie;
    }

    /**
     * The cookie object which represents the cookie which was not stored. It is optional because
     * sometimes complete cookie information is not available, such as in the case of parsing
     * errors.
     */
    public void setCookie(Cookie cookie) {
        this.cookie = cookie;
    }
}
