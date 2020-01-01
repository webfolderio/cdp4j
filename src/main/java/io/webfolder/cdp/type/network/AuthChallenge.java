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

import io.webfolder.cdp.annotation.Experimental;
import io.webfolder.cdp.type.constant.AuthChallengeSource;

/**
 * Authorization challenge for HTTP status code 401 or 407
 */
@Experimental
@UseStag
public class AuthChallenge {
    private AuthChallengeSource source;

    private String origin;

    private String scheme;

    private String realm;

    /**
     * Source of the authentication challenge.
     */
    public AuthChallengeSource getSource() {
        return source;
    }

    /**
     * Source of the authentication challenge.
     */
    public void setSource(AuthChallengeSource source) {
        this.source = source;
    }

    /**
     * Origin of the challenger.
     */
    public String getOrigin() {
        return origin;
    }

    /**
     * Origin of the challenger.
     */
    public void setOrigin(String origin) {
        this.origin = origin;
    }

    /**
     * The authentication scheme used, such as basic or digest
     */
    public String getScheme() {
        return scheme;
    }

    /**
     * The authentication scheme used, such as basic or digest
     */
    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    /**
     * The realm of the challenge. May be empty.
     */
    public String getRealm() {
        return realm;
    }

    /**
     * The realm of the challenge. May be empty.
     */
    public void setRealm(String realm) {
        this.realm = realm;
    }
}
