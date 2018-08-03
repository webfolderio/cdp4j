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
package io.webfolder.cdp.event.security;

import java.util.ArrayList;
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

    private List<SecurityStateExplanation> explanations = new ArrayList<>();

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
     * List of explanations for the security state. If the overall security state is <code>insecure</code> or
     * <code>warning</code>, at least one corresponding explanation should be included.
     */
    public List<SecurityStateExplanation> getExplanations() {
        return explanations;
    }

    /**
     * List of explanations for the security state. If the overall security state is <code>insecure</code> or
     * <code>warning</code>, at least one corresponding explanation should be included.
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
