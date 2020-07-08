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
package io.webfolder.cdp.type.webauthn;

public class VirtualAuthenticatorOptions {
    private AuthenticatorProtocol protocol;

    private AuthenticatorTransport transport;

    private Boolean hasResidentKey;

    private Boolean hasUserVerification;

    private Boolean automaticPresenceSimulation;

    private Boolean isUserVerified;

    public AuthenticatorProtocol getProtocol() {
        return protocol;
    }

    public void setProtocol(AuthenticatorProtocol protocol) {
        this.protocol = protocol;
    }

    public AuthenticatorTransport getTransport() {
        return transport;
    }

    public void setTransport(AuthenticatorTransport transport) {
        this.transport = transport;
    }

    /**
     * Defaults to false.
     */
    public Boolean isHasResidentKey() {
        return hasResidentKey;
    }

    /**
     * Defaults to false.
     */
    public void setHasResidentKey(Boolean hasResidentKey) {
        this.hasResidentKey = hasResidentKey;
    }

    /**
     * Defaults to false.
     */
    public Boolean isHasUserVerification() {
        return hasUserVerification;
    }

    /**
     * Defaults to false.
     */
    public void setHasUserVerification(Boolean hasUserVerification) {
        this.hasUserVerification = hasUserVerification;
    }

    /**
     * If set to true, tests of user presence will succeed immediately.
     * Otherwise, they will not be resolved. Defaults to true.
     */
    public Boolean isAutomaticPresenceSimulation() {
        return automaticPresenceSimulation;
    }

    /**
     * If set to true, tests of user presence will succeed immediately.
     * Otherwise, they will not be resolved. Defaults to true.
     */
    public void setAutomaticPresenceSimulation(Boolean automaticPresenceSimulation) {
        this.automaticPresenceSimulation = automaticPresenceSimulation;
    }

    /**
     * Sets whether User Verification succeeds or fails for an authenticator.
     * Defaults to false.
     */
    public Boolean isIsUserVerified() {
        return isUserVerified;
    }

    /**
     * Sets whether User Verification succeeds or fails for an authenticator.
     * Defaults to false.
     */
    public void setIsUserVerified(Boolean isUserVerified) {
        this.isUserVerified = isUserVerified;
    }
}
