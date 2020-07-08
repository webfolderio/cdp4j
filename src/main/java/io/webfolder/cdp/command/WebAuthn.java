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
package io.webfolder.cdp.command;

import io.webfolder.cdp.annotation.Domain;
import io.webfolder.cdp.annotation.Experimental;
import io.webfolder.cdp.annotation.Returns;
import io.webfolder.cdp.type.webauthn.Credential;
import io.webfolder.cdp.type.webauthn.VirtualAuthenticatorOptions;
import java.util.List;

/**
 * This domain allows configuring virtual authenticators to test the WebAuthn
 * API
 */
@Experimental
@Domain("WebAuthn")
public interface WebAuthn {
    /**
     * Enable the WebAuthn domain and start intercepting credential storage and
     * retrieval with a virtual authenticator.
     */
    void enable();

    /**
     * Disable the WebAuthn domain.
     */
    void disable();

    /**
     * Creates and adds a virtual authenticator.
     *
     */
    @Returns("authenticatorId")
    String addVirtualAuthenticator(VirtualAuthenticatorOptions options);

    /**
     * Removes the given authenticator.
     *
     */
    void removeVirtualAuthenticator(String authenticatorId);

    /**
     * Adds the credential to the specified authenticator.
     *
     */
    void addCredential(String authenticatorId, Credential credential);

    /**
     * Returns a single credential stored in the given virtual authenticator that
     * matches the credential ID.
     *
     */
    @Returns("credential")
    Credential getCredential(String authenticatorId, String credentialId);

    /**
     * Returns all the credentials stored in the given virtual authenticator.
     *
     */
    @Returns("credentials")
    List<Credential> getCredentials(String authenticatorId);

    /**
     * Removes a credential from the authenticator.
     *
     */
    void removeCredential(String authenticatorId, String credentialId);

    /**
     * Clears all the credentials from the specified device.
     *
     */
    void clearCredentials(String authenticatorId);

    /**
     * Sets whether User Verification succeeds or fails for an authenticator.
     * The default is true.
     *
     */
    void setUserVerified(String authenticatorId, Boolean isUserVerified);
}
