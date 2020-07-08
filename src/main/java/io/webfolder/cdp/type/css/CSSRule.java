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

import java.util.List;

/**
 * CSS rule representation
 */
public class CSSRule {
    private String styleSheetId;

    private SelectorList selectorList;

    private StyleSheetOrigin origin;

    private CSSStyle style;

    private List<CSSMedia> media;

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
     * Rule selector data.
     */
    public SelectorList getSelectorList() {
        return selectorList;
    }

    /**
     * Rule selector data.
     */
    public void setSelectorList(SelectorList selectorList) {
        this.selectorList = selectorList;
    }

    /**
     * Parent stylesheet's origin.
     */
    public StyleSheetOrigin getOrigin() {
        return origin;
    }

    /**
     * Parent stylesheet's origin.
     */
    public void setOrigin(StyleSheetOrigin origin) {
        this.origin = origin;
    }

    /**
     * Associated style declaration.
     */
    public CSSStyle getStyle() {
        return style;
    }

    /**
     * Associated style declaration.
     */
    public void setStyle(CSSStyle style) {
        this.style = style;
    }

    /**
     * Media list array (for rules involving media queries). The array enumerates media queries
     * starting with the innermost one, going outwards.
     */
    public List<CSSMedia> getMedia() {
        return media;
    }

    /**
     * Media list array (for rules involving media queries). The array enumerates media queries
     * starting with the innermost one, going outwards.
     */
    public void setMedia(List<CSSMedia> media) {
        this.media = media;
    }
}
