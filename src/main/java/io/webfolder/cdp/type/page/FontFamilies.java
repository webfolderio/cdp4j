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
package io.webfolder.cdp.type.page;

import com.vimeo.stag.UseStag;

import io.webfolder.cdp.annotation.Experimental;

/**
 * Generic font families collection
 */
@Experimental
@UseStag
public class FontFamilies {
    private String standard;

    private String fixed;

    private String serif;

    private String sansSerif;

    private String cursive;

    private String fantasy;

    private String pictograph;

    /**
     * The standard font-family.
     */
    public String getStandard() {
        return standard;
    }

    /**
     * The standard font-family.
     */
    public void setStandard(String standard) {
        this.standard = standard;
    }

    /**
     * The fixed font-family.
     */
    public String getFixed() {
        return fixed;
    }

    /**
     * The fixed font-family.
     */
    public void setFixed(String fixed) {
        this.fixed = fixed;
    }

    /**
     * The serif font-family.
     */
    public String getSerif() {
        return serif;
    }

    /**
     * The serif font-family.
     */
    public void setSerif(String serif) {
        this.serif = serif;
    }

    /**
     * The sansSerif font-family.
     */
    public String getSansSerif() {
        return sansSerif;
    }

    /**
     * The sansSerif font-family.
     */
    public void setSansSerif(String sansSerif) {
        this.sansSerif = sansSerif;
    }

    /**
     * The cursive font-family.
     */
    public String getCursive() {
        return cursive;
    }

    /**
     * The cursive font-family.
     */
    public void setCursive(String cursive) {
        this.cursive = cursive;
    }

    /**
     * The fantasy font-family.
     */
    public String getFantasy() {
        return fantasy;
    }

    /**
     * The fantasy font-family.
     */
    public void setFantasy(String fantasy) {
        this.fantasy = fantasy;
    }

    /**
     * The pictograph font-family.
     */
    public String getPictograph() {
        return pictograph;
    }

    /**
     * The pictograph font-family.
     */
    public void setPictograph(String pictograph) {
        this.pictograph = pictograph;
    }
}
