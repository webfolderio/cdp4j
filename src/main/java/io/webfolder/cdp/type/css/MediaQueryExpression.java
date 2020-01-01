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
 * Media query expression descriptor
 */
@UseStag
public class MediaQueryExpression {
    private Double value;

    private String unit;

    private String feature;

    private SourceRange valueRange;

    private Double computedLength;

    /**
     * Media query expression value.
     */
    public Double getValue() {
        return value;
    }

    /**
     * Media query expression value.
     */
    public void setValue(Double value) {
        this.value = value;
    }

    /**
     * Media query expression units.
     */
    public String getUnit() {
        return unit;
    }

    /**
     * Media query expression units.
     */
    public void setUnit(String unit) {
        this.unit = unit;
    }

    /**
     * Media query expression feature.
     */
    public String getFeature() {
        return feature;
    }

    /**
     * Media query expression feature.
     */
    public void setFeature(String feature) {
        this.feature = feature;
    }

    /**
     * The associated range of the value text in the enclosing stylesheet (if available).
     */
    public SourceRange getValueRange() {
        return valueRange;
    }

    /**
     * The associated range of the value text in the enclosing stylesheet (if available).
     */
    public void setValueRange(SourceRange valueRange) {
        this.valueRange = valueRange;
    }

    /**
     * Computed length of media query expression (if applicable).
     */
    public Double getComputedLength() {
        return computedLength;
    }

    /**
     * Computed length of media query expression (if applicable).
     */
    public void setComputedLength(Double computedLength) {
        this.computedLength = computedLength;
    }
}
