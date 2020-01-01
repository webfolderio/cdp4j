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
 * Default font sizes
 */
@Experimental
@UseStag
public class FontSizes {
    private Integer standard;

    private Integer fixed;

    /**
     * Default standard font size.
     */
    public Integer getStandard() {
        return standard;
    }

    /**
     * Default standard font size.
     */
    public void setStandard(Integer standard) {
        this.standard = standard;
    }

    /**
     * Default fixed font size.
     */
    public Integer getFixed() {
        return fixed;
    }

    /**
     * Default fixed font size.
     */
    public void setFixed(Integer fixed) {
        this.fixed = fixed;
    }
}
