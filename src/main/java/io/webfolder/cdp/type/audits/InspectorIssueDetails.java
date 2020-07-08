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

/**
 * This struct holds a list of optional fields with additional information
 * specific to the kind of issue
 * When adding a new issue code, please also
 * add a new optional field to this type
 */
public class InspectorIssueDetails {
    private SameSiteCookieIssueDetails sameSiteCookieIssueDetails;

    private MixedContentIssueDetails mixedContentIssueDetails;

    private BlockedByResponseIssueDetails blockedByResponseIssueDetails;

    private HeavyAdIssueDetails heavyAdIssueDetails;

    private ContentSecurityPolicyIssueDetails contentSecurityPolicyIssueDetails;

    public SameSiteCookieIssueDetails getSameSiteCookieIssueDetails() {
        return sameSiteCookieIssueDetails;
    }

    public void setSameSiteCookieIssueDetails(
            SameSiteCookieIssueDetails sameSiteCookieIssueDetails) {
        this.sameSiteCookieIssueDetails = sameSiteCookieIssueDetails;
    }

    public MixedContentIssueDetails getMixedContentIssueDetails() {
        return mixedContentIssueDetails;
    }

    public void setMixedContentIssueDetails(MixedContentIssueDetails mixedContentIssueDetails) {
        this.mixedContentIssueDetails = mixedContentIssueDetails;
    }

    public BlockedByResponseIssueDetails getBlockedByResponseIssueDetails() {
        return blockedByResponseIssueDetails;
    }

    public void setBlockedByResponseIssueDetails(
            BlockedByResponseIssueDetails blockedByResponseIssueDetails) {
        this.blockedByResponseIssueDetails = blockedByResponseIssueDetails;
    }

    public HeavyAdIssueDetails getHeavyAdIssueDetails() {
        return heavyAdIssueDetails;
    }

    public void setHeavyAdIssueDetails(HeavyAdIssueDetails heavyAdIssueDetails) {
        this.heavyAdIssueDetails = heavyAdIssueDetails;
    }

    public ContentSecurityPolicyIssueDetails getContentSecurityPolicyIssueDetails() {
        return contentSecurityPolicyIssueDetails;
    }

    public void setContentSecurityPolicyIssueDetails(
            ContentSecurityPolicyIssueDetails contentSecurityPolicyIssueDetails) {
        this.contentSecurityPolicyIssueDetails = contentSecurityPolicyIssueDetails;
    }
}
