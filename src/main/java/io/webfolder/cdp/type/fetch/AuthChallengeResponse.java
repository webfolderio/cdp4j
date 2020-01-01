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
package io.webfolder.cdp.type.fetch;

import com.vimeo.stag.UseStag;

import io.webfolder.cdp.annotation.Experimental;
import io.webfolder.cdp.type.constant.AuthResponse;

/**
 * Response to an AuthChallenge
 */
@Experimental
@UseStag
public class AuthChallengeResponse {
    private AuthResponse response;

    private String username;

    private String password;

    /**
     * The decision on what to do in response to the authorization challenge.  Default means
     * deferring to the default behavior of the net stack, which will likely either the Cancel
     * authentication or display a popup dialog box.
     */
    public AuthResponse getResponse() {
        return response;
    }

    /**
     * The decision on what to do in response to the authorization challenge.  Default means
     * deferring to the default behavior of the net stack, which will likely either the Cancel
     * authentication or display a popup dialog box.
     */
    public void setResponse(AuthResponse response) {
        this.response = response;
    }

    /**
     * The username to provide, possibly empty. Should only be set if response is
     * ProvideCredentials.
     */
    public String getUsername() {
        return username;
    }

    /**
     * The username to provide, possibly empty. Should only be set if response is
     * ProvideCredentials.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * The password to provide, possibly empty. Should only be set if response is
     * ProvideCredentials.
     */
    public String getPassword() {
        return password;
    }

    /**
     * The password to provide, possibly empty. Should only be set if response is
     * ProvideCredentials.
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
