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
package io.webfolder.cdp.type.accessibility;

/**
 * A single source for a computed AX property
 */
public class AXValueSource {
    private AXValueSourceType type;

    private AXValue value;

    private String attribute;

    private AXValue attributeValue;

    private Boolean superseded;

    private AXValueNativeSourceType nativeSource;

    private AXValue nativeSourceValue;

    private Boolean invalid;

    private String invalidReason;

    /**
     * What type of source this is.
     */
    public AXValueSourceType getType() {
        return type;
    }

    /**
     * What type of source this is.
     */
    public void setType(AXValueSourceType type) {
        this.type = type;
    }

    /**
     * The value of this property source.
     */
    public AXValue getValue() {
        return value;
    }

    /**
     * The value of this property source.
     */
    public void setValue(AXValue value) {
        this.value = value;
    }

    /**
     * The name of the relevant attribute, if any.
     */
    public String getAttribute() {
        return attribute;
    }

    /**
     * The name of the relevant attribute, if any.
     */
    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    /**
     * The value of the relevant attribute, if any.
     */
    public AXValue getAttributeValue() {
        return attributeValue;
    }

    /**
     * The value of the relevant attribute, if any.
     */
    public void setAttributeValue(AXValue attributeValue) {
        this.attributeValue = attributeValue;
    }

    /**
     * Whether this source is superseded by a higher priority source.
     */
    public Boolean isSuperseded() {
        return superseded;
    }

    /**
     * Whether this source is superseded by a higher priority source.
     */
    public void setSuperseded(Boolean superseded) {
        this.superseded = superseded;
    }

    /**
     * The native markup source for this value, e.g. a <label> element.
     */
    public AXValueNativeSourceType getNativeSource() {
        return nativeSource;
    }

    /**
     * The native markup source for this value, e.g. a <label> element.
     */
    public void setNativeSource(AXValueNativeSourceType nativeSource) {
        this.nativeSource = nativeSource;
    }

    /**
     * The value, such as a node or node list, of the native source.
     */
    public AXValue getNativeSourceValue() {
        return nativeSourceValue;
    }

    /**
     * The value, such as a node or node list, of the native source.
     */
    public void setNativeSourceValue(AXValue nativeSourceValue) {
        this.nativeSourceValue = nativeSourceValue;
    }

    /**
     * Whether the value for this property is invalid.
     */
    public Boolean isInvalid() {
        return invalid;
    }

    /**
     * Whether the value for this property is invalid.
     */
    public void setInvalid(Boolean invalid) {
        this.invalid = invalid;
    }

    /**
     * Reason for the value being invalid, if it is.
     */
    public String getInvalidReason() {
        return invalidReason;
    }

    /**
     * Reason for the value being invalid, if it is.
     */
    public void setInvalidReason(String invalidReason) {
        this.invalidReason = invalidReason;
    }
}
