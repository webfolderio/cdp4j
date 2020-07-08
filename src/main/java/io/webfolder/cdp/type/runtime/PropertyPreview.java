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
package io.webfolder.cdp.type.runtime;

import io.webfolder.cdp.annotation.Experimental;
import io.webfolder.cdp.type.constant.ObjectSubtypeHint;
import io.webfolder.cdp.type.constant.PropertyPreviewType;

@Experimental
public class PropertyPreview {
    private String name;

    private PropertyPreviewType type;

    private String value;

    private ObjectPreview valuePreview;

    private ObjectSubtypeHint subtype;

    /**
     * Property name.
     */
    public String getName() {
        return name;
    }

    /**
     * Property name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Object type. Accessor means that the property itself is an accessor property.
     */
    public PropertyPreviewType getType() {
        return type;
    }

    /**
     * Object type. Accessor means that the property itself is an accessor property.
     */
    public void setType(PropertyPreviewType type) {
        this.type = type;
    }

    /**
     * User-friendly property value string.
     */
    public String getValue() {
        return value;
    }

    /**
     * User-friendly property value string.
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Nested value preview.
     */
    public ObjectPreview getValuePreview() {
        return valuePreview;
    }

    /**
     * Nested value preview.
     */
    public void setValuePreview(ObjectPreview valuePreview) {
        this.valuePreview = valuePreview;
    }

    /**
     * Object subtype hint. Specified for object type values only.
     */
    public ObjectSubtypeHint getSubtype() {
        return subtype;
    }

    /**
     * Object subtype hint. Specified for object type values only.
     */
    public void setSubtype(ObjectSubtypeHint subtype) {
        this.subtype = subtype;
    }
}
