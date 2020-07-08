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

/**
 * An explanation of an factor contributing to the security state
 */
public class SecurityStateExplanation {
    private SecurityState securityState;

    private String title;

    private String summary;

    private String description;

    private MixedContentType mixedContentType;

    private List<String> certificate;

    private List<String> recommendations;

    /**
     * Security state representing the severity of the factor being explained.
     */
    public SecurityState getSecurityState() {
        return securityState;
    }

    /**
     * Security state representing the severity of the factor being explained.
     */
    public void setSecurityState(SecurityState securityState) {
        this.securityState = securityState;
    }

    /**
     * Title describing the type of factor.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Title describing the type of factor.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Short phrase describing the type of factor.
     */
    public String getSummary() {
        return summary;
    }

    /**
     * Short phrase describing the type of factor.
     */
    public void setSummary(String summary) {
        this.summary = summary;
    }

    /**
     * Full text explanation of the factor.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Full text explanation of the factor.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * The type of mixed content described by the explanation.
     */
    public MixedContentType getMixedContentType() {
        return mixedContentType;
    }

    /**
     * The type of mixed content described by the explanation.
     */
    public void setMixedContentType(MixedContentType mixedContentType) {
        this.mixedContentType = mixedContentType;
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
     * Recommendations to fix any issues.
     */
    public List<String> getRecommendations() {
        return recommendations;
    }

    /**
     * Recommendations to fix any issues.
     */
    public void setRecommendations(List<String> recommendations) {
        this.recommendations = recommendations;
    }
}
