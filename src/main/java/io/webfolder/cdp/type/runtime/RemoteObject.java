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

import io.webfolder.cdp.type.constant.ObjectSubtypeHint;
import io.webfolder.cdp.type.constant.ObjectType;

/**
 * Mirror object referencing original JavaScript object
 */
public class RemoteObject {
    private ObjectType type;

    private ObjectSubtypeHint subtype;

    private String className;

    private Object value;

    private String unserializableValue;

    private String description;

    private String objectId;

    private ObjectPreview preview;

    private CustomPreview customPreview;

    /**
     * Object type.
     */
    public ObjectType getType() {
        return type;
    }

    /**
     * Object type.
     */
    public void setType(ObjectType type) {
        this.type = type;
    }

    /**
     * Object subtype hint. Specified for object or wasm type values only.
     */
    public ObjectSubtypeHint getSubtype() {
        return subtype;
    }

    /**
     * Object subtype hint. Specified for object or wasm type values only.
     */
    public void setSubtype(ObjectSubtypeHint subtype) {
        this.subtype = subtype;
    }

    /**
     * Object class (constructor) name. Specified for object type values only.
     */
    public String getClassName() {
        return className;
    }

    /**
     * Object class (constructor) name. Specified for object type values only.
     */
    public void setClassName(String className) {
        this.className = className;
    }

    /**
     * Remote object value in case of primitive values or JSON values (if it was requested).
     */
    public Object getValue() {
        return value;
    }

    /**
     * Remote object value in case of primitive values or JSON values (if it was requested).
     */
    public void setValue(Object value) {
        this.value = value;
    }

    /**
     * Primitive value which can not be JSON-stringified does not have value, but gets this
     * property.
     */
    public String getUnserializableValue() {
        return unserializableValue;
    }

    /**
     * Primitive value which can not be JSON-stringified does not have value, but gets this
     * property.
     */
    public void setUnserializableValue(String unserializableValue) {
        this.unserializableValue = unserializableValue;
    }

    /**
     * String representation of the object.
     */
    public String getDescription() {
        return description;
    }

    /**
     * String representation of the object.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Unique object identifier (for non-primitive values).
     */
    public String getObjectId() {
        return objectId;
    }

    /**
     * Unique object identifier (for non-primitive values).
     */
    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    /**
     * Preview containing abbreviated property values. Specified for object type values only.
     */
    public ObjectPreview getPreview() {
        return preview;
    }

    /**
     * Preview containing abbreviated property values. Specified for object type values only.
     */
    public void setPreview(ObjectPreview preview) {
        this.preview = preview;
    }

    public CustomPreview getCustomPreview() {
        return customPreview;
    }

    public void setCustomPreview(CustomPreview customPreview) {
        this.customPreview = customPreview;
    }
}
