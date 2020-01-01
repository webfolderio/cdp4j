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
package io.webfolder.cdp.type.security;

import java.util.List;

import com.vimeo.stag.UseStag;

import io.webfolder.cdp.annotation.Experimental;

/**
 * Details about the security state of the page certificate
 */
@Experimental
@UseStag
public class CertificateSecurityState {
    private String protocol;

    private String keyExchange;

    private String keyExchangeGroup;

    private String cipher;

    private String mac;

    private List<String> certificate;

    private String subjectName;

    private String issuer;

    private Double validFrom;

    private Double validTo;

    private String certificateNetworkError;

    private Boolean certificateHasWeakSignature;

    private Boolean certificateHasSha1Signature;

    private Boolean modernSSL;

    private Boolean obsoleteSslProtocol;

    private Boolean obsoleteSslKeyExchange;

    private Boolean obsoleteSslCipher;

    private Boolean obsoleteSslSignature;

    /**
     * Protocol name (e.g. "TLS 1.2" or "QUIC").
     */
    public String getProtocol() {
        return protocol;
    }

    /**
     * Protocol name (e.g. "TLS 1.2" or "QUIC").
     */
    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    /**
     * Key Exchange used by the connection, or the empty string if not applicable.
     */
    public String getKeyExchange() {
        return keyExchange;
    }

    /**
     * Key Exchange used by the connection, or the empty string if not applicable.
     */
    public void setKeyExchange(String keyExchange) {
        this.keyExchange = keyExchange;
    }

    /**
     * (EC)DH group used by the connection, if applicable.
     */
    public String getKeyExchangeGroup() {
        return keyExchangeGroup;
    }

    /**
     * (EC)DH group used by the connection, if applicable.
     */
    public void setKeyExchangeGroup(String keyExchangeGroup) {
        this.keyExchangeGroup = keyExchangeGroup;
    }

    /**
     * Cipher name.
     */
    public String getCipher() {
        return cipher;
    }

    /**
     * Cipher name.
     */
    public void setCipher(String cipher) {
        this.cipher = cipher;
    }

    /**
     * TLS MAC. Note that AEAD ciphers do not have separate MACs.
     */
    public String getMac() {
        return mac;
    }

    /**
     * TLS MAC. Note that AEAD ciphers do not have separate MACs.
     */
    public void setMac(String mac) {
        this.mac = mac;
    }

    /**
     * Page certificate.
     */
    public List<String> getCertificate() {
        return certificate;
    }

    /**
     * Page certificate.
     */
    public void setCertificate(List<String> certificate) {
        this.certificate = certificate;
    }

    /**
     * Certificate subject name.
     */
    public String getSubjectName() {
        return subjectName;
    }

    /**
     * Certificate subject name.
     */
    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    /**
     * Name of the issuing CA.
     */
    public String getIssuer() {
        return issuer;
    }

    /**
     * Name of the issuing CA.
     */
    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    /**
     * Certificate valid from date.
     */
    public Double getValidFrom() {
        return validFrom;
    }

    /**
     * Certificate valid from date.
     */
    public void setValidFrom(Double validFrom) {
        this.validFrom = validFrom;
    }

    /**
     * Certificate valid to (expiration) date
     */
    public Double getValidTo() {
        return validTo;
    }

    /**
     * Certificate valid to (expiration) date
     */
    public void setValidTo(Double validTo) {
        this.validTo = validTo;
    }

    /**
     * The highest priority network error code, if the certificate has an error.
     */
    public String getCertificateNetworkError() {
        return certificateNetworkError;
    }

    /**
     * The highest priority network error code, if the certificate has an error.
     */
    public void setCertificateNetworkError(String certificateNetworkError) {
        this.certificateNetworkError = certificateNetworkError;
    }

    /**
     * True if the certificate uses a weak signature aglorithm.
     */
    public Boolean isCertificateHasWeakSignature() {
        return certificateHasWeakSignature;
    }

    /**
     * True if the certificate uses a weak signature aglorithm.
     */
    public void setCertificateHasWeakSignature(Boolean certificateHasWeakSignature) {
        this.certificateHasWeakSignature = certificateHasWeakSignature;
    }

    /**
     * True if the certificate has a SHA1 signature in the chain.
     */
    public Boolean isCertificateHasSha1Signature() {
        return certificateHasSha1Signature;
    }

    /**
     * True if the certificate has a SHA1 signature in the chain.
     */
    public void setCertificateHasSha1Signature(Boolean certificateHasSha1Signature) {
        this.certificateHasSha1Signature = certificateHasSha1Signature;
    }

    /**
     * True if modern SSL
     */
    public Boolean isModernSSL() {
        return modernSSL;
    }

    /**
     * True if modern SSL
     */
    public void setModernSSL(Boolean modernSSL) {
        this.modernSSL = modernSSL;
    }

    /**
     * True if the connection is using an obsolete SSL protocol.
     */
    public Boolean isObsoleteSslProtocol() {
        return obsoleteSslProtocol;
    }

    /**
     * True if the connection is using an obsolete SSL protocol.
     */
    public void setObsoleteSslProtocol(Boolean obsoleteSslProtocol) {
        this.obsoleteSslProtocol = obsoleteSslProtocol;
    }

    /**
     * True if the connection is using an obsolete SSL key exchange.
     */
    public Boolean isObsoleteSslKeyExchange() {
        return obsoleteSslKeyExchange;
    }

    /**
     * True if the connection is using an obsolete SSL key exchange.
     */
    public void setObsoleteSslKeyExchange(Boolean obsoleteSslKeyExchange) {
        this.obsoleteSslKeyExchange = obsoleteSslKeyExchange;
    }

    /**
     * True if the connection is using an obsolete SSL cipher.
     */
    public Boolean isObsoleteSslCipher() {
        return obsoleteSslCipher;
    }

    /**
     * True if the connection is using an obsolete SSL cipher.
     */
    public void setObsoleteSslCipher(Boolean obsoleteSslCipher) {
        this.obsoleteSslCipher = obsoleteSslCipher;
    }

    /**
     * True if the connection is using an obsolete SSL signature.
     */
    public Boolean isObsoleteSslSignature() {
        return obsoleteSslSignature;
    }

    /**
     * True if the connection is using an obsolete SSL signature.
     */
    public void setObsoleteSslSignature(Boolean obsoleteSslSignature) {
        this.obsoleteSslSignature = obsoleteSslSignature;
    }
}
