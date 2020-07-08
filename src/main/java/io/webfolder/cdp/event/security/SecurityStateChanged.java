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
package io.webfolder.cdp.event.security;

import java.util.List;

import io.webfolder.cdp.annotation.Domain;
import io.webfolder.cdp.annotation.EventName;
import io.webfolder.cdp.type.security.InsecureContentStatus;
import io.webfolder.cdp.type.security.SecurityState;
import io.webfolder.cdp.type.security.SecurityStateExplanation;

/**
 * The security state of the page changed
 */
@Domain("Security")
@EventName("securityStateChanged")
public class SecurityStateChanged {
    private SecurityState securityState;

    private Boolean schemeIsCryptographic;

    private List<SecurityStateExplanation> explanations;

    private InsecureContentStatus insecureContentStatus;

    private String summary;

    /**
     * Security state.
     */
    public SecurityState getSecurityState() {
        return securityState;
    }

    /**
     * Security state.
     */
    public void setSecurityState(SecurityState securityState) {
        this.securityState = securityState;
    }

    /**
     * True if the page was loaded over cryptographic transport such as HTTPS.
     */
    public Boolean isSchemeIsCryptographic() {
        return schemeIsCryptographic;
    }

    /**
     * True if the page was loaded over cryptographic transport such as HTTPS.
     */
    public void setSchemeIsCryptographic(Boolean schemeIsCryptographic) {
        this.schemeIsCryptographic = schemeIsCryptographic;
    }

    /**
     * List of explanations for the security state. If the overall security state is insecure or
     * warning, at least one corresponding explanation should be included.
     */
    public List<SecurityStateExplanation> getExplanations() {
        return explanations;
    }

    /**
     * List of explanations for the security state. If the overall security state is insecure or
     * warning, at least one corresponding explanation should be included.
     */
    public void setExplanations(List<SecurityStateExplanation> explanations) {
        this.explanations = explanations;
    }

    /**
     * Information about insecure content on the page.
     */
    public InsecureContentStatus getInsecureContentStatus() {
        return insecureContentStatus;
    }

    /**
     * Information about insecure content on the page.
     */
    public void setInsecureContentStatus(InsecureContentStatus insecureContentStatus) {
        this.insecureContentStatus = insecureContentStatus;
    }

    /**
     * Overrides user-visible description of the state.
     */
    public String getSummary() {
        return summary;
    }

    /**
     * Overrides user-visible description of the state.
     */
    public void setSummary(String summary) {
        this.summary = summary;
    }
}
