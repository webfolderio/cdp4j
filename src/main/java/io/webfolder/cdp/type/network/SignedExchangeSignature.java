/**
 * cdp4j - Chrome DevTools Protocol for Java
 * Copyright © 2017, 2018 WebFolder OÜ (support@webfolder.io)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package io.webfolder.cdp.type.network;

import io.webfolder.cdp.annotation.Experimental;
import java.util.ArrayList;
import java.util.List;

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

    private List<String> certificates = new ArrayList<>();

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
