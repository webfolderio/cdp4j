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

/**
 * Represents function call argument
 * Either remote object id objectId, primitive value,
 * unserializable primitive value or neither of (for undefined) them should be specified
 */
public class CallArgument {
    private Object value;

    private String unserializableValue;

    private String objectId;

    /**
     * Primitive value or serializable javascript object.
     */
    public Object getValue() {
        return value;
    }

    /**
     * Primitive value or serializable javascript object.
     */
    public void setValue(Object value) {
        this.value = value;
    }

    /**
     * Primitive value which can not be JSON-stringified.
     */
    public String getUnserializableValue() {
        return unserializableValue;
    }

    /**
     * Primitive value which can not be JSON-stringified.
     */
    public void setUnserializableValue(String unserializableValue) {
        this.unserializableValue = unserializableValue;
    }

    /**
     * Remote object handle.
     */
    public String getObjectId() {
        return objectId;
    }

    /**
     * Remote object handle.
     */
    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }
}
