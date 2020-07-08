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

/**
 * Information about insecure content on the page
 */
public class InsecureContentStatus {
    private Boolean ranMixedContent;

    private Boolean displayedMixedContent;

    private Boolean containedMixedForm;

    private Boolean ranContentWithCertErrors;

    private Boolean displayedContentWithCertErrors;

    private SecurityState ranInsecureContentStyle;

    private SecurityState displayedInsecureContentStyle;

    /**
     * Always false.
     */
    public Boolean isRanMixedContent() {
        return ranMixedContent;
    }

    /**
     * Always false.
     */
    public void setRanMixedContent(Boolean ranMixedContent) {
        this.ranMixedContent = ranMixedContent;
    }

    /**
     * Always false.
     */
    public Boolean isDisplayedMixedContent() {
        return displayedMixedContent;
    }

    /**
     * Always false.
     */
    public void setDisplayedMixedContent(Boolean displayedMixedContent) {
        this.displayedMixedContent = displayedMixedContent;
    }

    /**
     * Always false.
     */
    public Boolean isContainedMixedForm() {
        return containedMixedForm;
    }

    /**
     * Always false.
     */
    public void setContainedMixedForm(Boolean containedMixedForm) {
        this.containedMixedForm = containedMixedForm;
    }

    /**
     * Always false.
     */
    public Boolean isRanContentWithCertErrors() {
        return ranContentWithCertErrors;
    }

    /**
     * Always false.
     */
    public void setRanContentWithCertErrors(Boolean ranContentWithCertErrors) {
        this.ranContentWithCertErrors = ranContentWithCertErrors;
    }

    /**
     * Always false.
     */
    public Boolean isDisplayedContentWithCertErrors() {
        return displayedContentWithCertErrors;
    }

    /**
     * Always false.
     */
    public void setDisplayedContentWithCertErrors(Boolean displayedContentWithCertErrors) {
        this.displayedContentWithCertErrors = displayedContentWithCertErrors;
    }

    /**
     * Always set to unknown.
     */
    public SecurityState getRanInsecureContentStyle() {
        return ranInsecureContentStyle;
    }

    /**
     * Always set to unknown.
     */
    public void setRanInsecureContentStyle(SecurityState ranInsecureContentStyle) {
        this.ranInsecureContentStyle = ranInsecureContentStyle;
    }

    /**
     * Always set to unknown.
     */
    public SecurityState getDisplayedInsecureContentStyle() {
        return displayedInsecureContentStyle;
    }

    /**
     * Always set to unknown.
     */
    public void setDisplayedInsecureContentStyle(SecurityState displayedInsecureContentStyle) {
        this.displayedInsecureContentStyle = displayedInsecureContentStyle;
    }
}
