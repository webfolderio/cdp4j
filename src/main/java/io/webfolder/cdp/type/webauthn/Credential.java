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

public class Credential {
    private String credentialId;

    private Boolean isResidentCredential;

    private String rpId;

    private String privateKey;

    private String userHandle;

    private Integer signCount;

    public String getCredentialId() {
        return credentialId;
    }

    public void setCredentialId(String credentialId) {
        this.credentialId = credentialId;
    }

    public Boolean isIsResidentCredential() {
        return isResidentCredential;
    }

    public void setIsResidentCredential(Boolean isResidentCredential) {
        this.isResidentCredential = isResidentCredential;
    }

    /**
     * Relying Party ID the credential is scoped to. Must be set when adding a
     * credential.
     */
    public String getRpId() {
        return rpId;
    }

    /**
     * Relying Party ID the credential is scoped to. Must be set when adding a
     * credential.
     */
    public void setRpId(String rpId) {
        this.rpId = rpId;
    }

    /**
     * The ECDSA P-256 private key in PKCS#8 format.
     */
    public String getPrivateKey() {
        return privateKey;
    }

    /**
     * The ECDSA P-256 private key in PKCS#8 format.
     */
    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    /**
     * An opaque byte sequence with a maximum size of 64 bytes mapping the
     * credential to a specific user.
     */
    public String getUserHandle() {
        return userHandle;
    }

    /**
     * An opaque byte sequence with a maximum size of 64 bytes mapping the
     * credential to a specific user.
     */
    public void setUserHandle(String userHandle) {
        this.userHandle = userHandle;
    }

    /**
     * Signature counter. This is incremented by one for each successful
     * assertion.
     * See https://w3c.github.io/webauthn/#signature-counter
     */
    public Integer getSignCount() {
        return signCount;
    }

    /**
     * Signature counter. This is incremented by one for each successful
     * assertion.
     * See https://w3c.github.io/webauthn/#signature-counter
     */
    public void setSignCount(Integer signCount) {
        this.signCount = signCount;
    }
}
