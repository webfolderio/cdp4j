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

import io.webfolder.cdp.annotation.Experimental;

/**
 * Information about a signed exchange signature
 * https://wicg
 * github
 * io/webpackage/draft-yasskin-httpbis-origin-signed-exchanges-impl
 * html#rfc
 * section
 * 3
 * 1
 */
@Experimental
public class SignedExchangeSignature {
    private String label;

    private String signature;

    private String integrity;

    private String certUrl;

    private String certSha256;

    private String validityUrl;

    private Integer date;

    private Integer expires;

    private List<String> certificates;

    /**
     * Signed exchange signature label.
     */
    public String getLabel() {
        return label;
    }

    /**
     * Signed exchange signature label.
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * The hex string of signed exchange signature.
     */
    public String getSignature() {
        return signature;
    }

    /**
     * The hex string of signed exchange signature.
     */
    public void setSignature(String signature) {
        this.signature = signature;
    }

    /**
     * Signed exchange signature integrity.
     */
    public String getIntegrity() {
        return integrity;
    }

    /**
     * Signed exchange signature integrity.
     */
    public void setIntegrity(String integrity) {
        this.integrity = integrity;
    }

    /**
     * Signed exchange signature cert Url.
     */
    public String getCertUrl() {
        return certUrl;
    }

    /**
     * Signed exchange signature cert Url.
     */
    public void setCertUrl(String certUrl) {
        this.certUrl = certUrl;
    }

    /**
     * The hex string of signed exchange signature cert sha256.
     */
    public String getCertSha256() {
        return certSha256;
    }

    /**
     * The hex string of signed exchange signature cert sha256.
     */
    public void setCertSha256(String certSha256) {
        this.certSha256 = certSha256;
    }

    /**
     * Signed exchange signature validity Url.
     */
    public String getValidityUrl() {
        return validityUrl;
    }

    /**
     * Signed exchange signature validity Url.
     */
    public void setValidityUrl(String validityUrl) {
        this.validityUrl = validityUrl;
    }

    /**
     * Signed exchange signature date.
     */
    public Integer getDate() {
        return date;
    }

    /**
     * Signed exchange signature date.
     */
    public void setDate(Integer date) {
        this.date = date;
    }

    /**
     * Signed exchange signature expires.
     */
    public Integer getExpires() {
        return expires;
    }

    /**
     * Signed exchange signature expires.
     */
    public void setExpires(Integer expires) {
        this.expires = expires;
    }

    /**
     * The encoded certificates.
     */
    public List<String> getCertificates() {
        return certificates;
    }

    /**
     * The encoded certificates.
     */
    public void setCertificates(List<String> certificates) {
        this.certificates = certificates;
    }
}
