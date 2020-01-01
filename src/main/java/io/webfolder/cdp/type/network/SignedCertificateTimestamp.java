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

/**
 * Details of a signed certificate timestamp (SCT)
 */
@UseStag
public class SignedCertificateTimestamp {
    private String status;

    private String origin;

    private String logDescription;

    private String logId;

    private Double timestamp;

    private String hashAlgorithm;

    private String signatureAlgorithm;

    private String signatureData;

    /**
     * Validation status.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Validation status.
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Origin.
     */
    public String getOrigin() {
        return origin;
    }

    /**
     * Origin.
     */
    public void setOrigin(String origin) {
        this.origin = origin;
    }

    /**
     * Log name / description.
     */
    public String getLogDescription() {
        return logDescription;
    }

    /**
     * Log name / description.
     */
    public void setLogDescription(String logDescription) {
        this.logDescription = logDescription;
    }

    /**
     * Log ID.
     */
    public String getLogId() {
        return logId;
    }

    /**
     * Log ID.
     */
    public void setLogId(String logId) {
        this.logId = logId;
    }

    /**
     * Issuance date.
     */
    public Double getTimestamp() {
        return timestamp;
    }

    /**
     * Issuance date.
     */
    public void setTimestamp(Double timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Hash algorithm.
     */
    public String getHashAlgorithm() {
        return hashAlgorithm;
    }

    /**
     * Hash algorithm.
     */
    public void setHashAlgorithm(String hashAlgorithm) {
        this.hashAlgorithm = hashAlgorithm;
    }

    /**
     * Signature algorithm.
     */
    public String getSignatureAlgorithm() {
        return signatureAlgorithm;
    }

    /**
     * Signature algorithm.
     */
    public void setSignatureAlgorithm(String signatureAlgorithm) {
        this.signatureAlgorithm = signatureAlgorithm;
    }

    /**
     * Signature data.
     */
    public String getSignatureData() {
        return signatureData;
    }

    /**
     * Signature data.
     */
    public void setSignatureData(String signatureData) {
        this.signatureData = signatureData;
    }
}
