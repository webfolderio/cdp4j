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
 * Properties of a web font: https://www
 * w3
 * org/TR/2008/REC-CSS2-20080411/fonts
 * html#font-descriptions
 */
@UseStag
public class FontFace {
    private String fontFamily;

    private String fontStyle;

    private String fontVariant;

    private String fontWeight;

    private String fontStretch;

    private String unicodeRange;

    private String src;

    private String platformFontFamily;

    /**
     * The font-family.
     */
    public String getFontFamily() {
        return fontFamily;
    }

    /**
     * The font-family.
     */
    public void setFontFamily(String fontFamily) {
        this.fontFamily = fontFamily;
    }

    /**
     * The font-style.
     */
    public String getFontStyle() {
        return fontStyle;
    }

    /**
     * The font-style.
     */
    public void setFontStyle(String fontStyle) {
        this.fontStyle = fontStyle;
    }

    /**
     * The font-variant.
     */
    public String getFontVariant() {
        return fontVariant;
    }

    /**
     * The font-variant.
     */
    public void setFontVariant(String fontVariant) {
        this.fontVariant = fontVariant;
    }

    /**
     * The font-weight.
     */
    public String getFontWeight() {
        return fontWeight;
    }

    /**
     * The font-weight.
     */
    public void setFontWeight(String fontWeight) {
        this.fontWeight = fontWeight;
    }

    /**
     * The font-stretch.
     */
    public String getFontStretch() {
        return fontStretch;
    }

    /**
     * The font-stretch.
     */
    public void setFontStretch(String fontStretch) {
        this.fontStretch = fontStretch;
    }

    /**
     * The unicode-range.
     */
    public String getUnicodeRange() {
        return unicodeRange;
    }

    /**
     * The unicode-range.
     */
    public void setUnicodeRange(String unicodeRange) {
        this.unicodeRange = unicodeRange;
    }

    /**
     * The src.
     */
    public String getSrc() {
        return src;
    }

    /**
     * The src.
     */
    public void setSrc(String src) {
        this.src = src;
    }

    /**
     * The resolved platform font family
     */
    public String getPlatformFontFamily() {
        return platformFontFamily;
    }

    /**
     * The resolved platform font family
     */
    public void setPlatformFontFamily(String platformFontFamily) {
        this.platformFontFamily = platformFontFamily;
    }
}
