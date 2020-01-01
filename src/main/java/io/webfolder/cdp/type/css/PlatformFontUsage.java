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
 * Information about amount of glyphs that were rendered with given font
 */
@UseStag
public class PlatformFontUsage {
    private String familyName;

    private Boolean isCustomFont;

    private Double glyphCount;

    /**
     * Font's family name reported by platform.
     */
    public String getFamilyName() {
        return familyName;
    }

    /**
     * Font's family name reported by platform.
     */
    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    /**
     * Indicates if the font was downloaded or resolved locally.
     */
    public Boolean isIsCustomFont() {
        return isCustomFont;
    }

    /**
     * Indicates if the font was downloaded or resolved locally.
     */
    public void setIsCustomFont(Boolean isCustomFont) {
        this.isCustomFont = isCustomFont;
    }

    /**
     * Amount of glyphs that were rendered with this font.
     */
    public Double getGlyphCount() {
        return glyphCount;
    }

    /**
     * Amount of glyphs that were rendered with this font.
     */
    public void setGlyphCount(Double glyphCount) {
        this.glyphCount = glyphCount;
    }

    public Boolean getIsCustomFont() {
        return isCustomFont;
    }
}
