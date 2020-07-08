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
package io.webfolder.cdp.type.audits;

public class ContentSecurityPolicyIssueDetails {
    private String blockedURL;

    private String violatedDirective;

    private ContentSecurityPolicyViolationType contentSecurityPolicyViolationType;

    private AffectedFrame frameAncestor;

    /**
     * The url not included in allowed sources.
     */
    public String getBlockedURL() {
        return blockedURL;
    }

    /**
     * The url not included in allowed sources.
     */
    public void setBlockedURL(String blockedURL) {
        this.blockedURL = blockedURL;
    }

    /**
     * Specific directive that is violated, causing the CSP issue.
     */
    public String getViolatedDirective() {
        return violatedDirective;
    }

    /**
     * Specific directive that is violated, causing the CSP issue.
     */
    public void setViolatedDirective(String violatedDirective) {
        this.violatedDirective = violatedDirective;
    }

    public ContentSecurityPolicyViolationType getContentSecurityPolicyViolationType() {
        return contentSecurityPolicyViolationType;
    }

    public void setContentSecurityPolicyViolationType(
            ContentSecurityPolicyViolationType contentSecurityPolicyViolationType) {
        this.contentSecurityPolicyViolationType = contentSecurityPolicyViolationType;
    }

    public AffectedFrame getFrameAncestor() {
        return frameAncestor;
    }

    public void setFrameAncestor(AffectedFrame frameAncestor) {
        this.frameAncestor = frameAncestor;
    }
}
