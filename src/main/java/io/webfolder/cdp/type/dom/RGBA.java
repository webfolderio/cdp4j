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
package io.webfolder.cdp.type.dom;

import com.vimeo.stag.UseStag;

/**
 * A structure holding an RGBA color
 */
@UseStag
public class RGBA {
    private Integer r;

    private Integer g;

    private Integer b;

    private Double a;

    /**
     * The red component, in the [0-255] range.
     */
    public Integer getR() {
        return r;
    }

    /**
     * The red component, in the [0-255] range.
     */
    public void setR(Integer r) {
        this.r = r;
    }

    /**
     * The green component, in the [0-255] range.
     */
    public Integer getG() {
        return g;
    }

    /**
     * The green component, in the [0-255] range.
     */
    public void setG(Integer g) {
        this.g = g;
    }

    /**
     * The blue component, in the [0-255] range.
     */
    public Integer getB() {
        return b;
    }

    /**
     * The blue component, in the [0-255] range.
     */
    public void setB(Integer b) {
        this.b = b;
    }

    /**
     * The alpha component, in the [0-1] range (default: 1).
     */
    public Double getA() {
        return a;
    }

    /**
     * The alpha component, in the [0-1] range (default: 1).
     */
    public void setA(Double a) {
        this.a = a;
    }
}
