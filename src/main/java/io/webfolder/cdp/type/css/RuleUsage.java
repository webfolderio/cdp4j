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
package io.webfolder.cdp.type.css;

import com.vimeo.stag.UseStag;

/**
 * CSS coverage information
 */
@UseStag
public class RuleUsage {
    private String styleSheetId;

    private Double startOffset;

    private Double endOffset;

    private Boolean used;

    /**
     * The css style sheet identifier (absent for user agent stylesheet and user-specified
     * stylesheet rules) this rule came from.
     */
    public String getStyleSheetId() {
        return styleSheetId;
    }

    /**
     * The css style sheet identifier (absent for user agent stylesheet and user-specified
     * stylesheet rules) this rule came from.
     */
    public void setStyleSheetId(String styleSheetId) {
        this.styleSheetId = styleSheetId;
    }

    /**
     * Offset of the start of the rule (including selector) from the beginning of the stylesheet.
     */
    public Double getStartOffset() {
        return startOffset;
    }

    /**
     * Offset of the start of the rule (including selector) from the beginning of the stylesheet.
     */
    public void setStartOffset(Double startOffset) {
        this.startOffset = startOffset;
    }

    /**
     * Offset of the end of the rule body from the beginning of the stylesheet.
     */
    public Double getEndOffset() {
        return endOffset;
    }

    /**
     * Offset of the end of the rule body from the beginning of the stylesheet.
     */
    public void setEndOffset(Double endOffset) {
        this.endOffset = endOffset;
    }

    /**
     * Indicates whether the rule was actually used by some element in the page.
     */
    public Boolean isUsed() {
        return used;
    }

    /**
     * Indicates whether the rule was actually used by some element in the page.
     */
    public void setUsed(Boolean used) {
        this.used = used;
    }
}
