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

import java.util.List;

import com.vimeo.stag.UseStag;

import io.webfolder.cdp.annotation.Experimental;
import io.webfolder.cdp.type.constant.ObjectSubtypeHint;
import io.webfolder.cdp.type.constant.ObjectType;

/**
 * Object containing abbreviated remote object value
 */
@Experimental
@UseStag
public class ObjectPreview {
    private ObjectType type;

    private ObjectSubtypeHint subtype;

    private String description;

    private Boolean overflow;

    private List<PropertyPreview> properties;

    private List<EntryPreview> entries;

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
     * Object subtype hint. Specified for <code>object</code> type values only.
     */
    public ObjectSubtypeHint getSubtype() {
        return subtype;
    }

    /**
     * Object subtype hint. Specified for <code>object</code> type values only.
     */
    public void setSubtype(ObjectSubtypeHint subtype) {
        this.subtype = subtype;
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
     * True iff some of the properties or entries of the original object did not fit.
     */
    public Boolean isOverflow() {
        return overflow;
    }

    /**
     * True iff some of the properties or entries of the original object did not fit.
     */
    public void setOverflow(Boolean overflow) {
        this.overflow = overflow;
    }

    /**
     * List of the properties.
     */
    public List<PropertyPreview> getProperties() {
        return properties;
    }

    /**
     * List of the properties.
     */
    public void setProperties(List<PropertyPreview> properties) {
        this.properties = properties;
    }

    /**
     * List of the entries. Specified for <code>map</code> and <code>set</code> subtype values only.
     */
    public List<EntryPreview> getEntries() {
        return entries;
    }

    /**
     * List of the entries. Specified for <code>map</code> and <code>set</code> subtype values only.
     */
    public void setEntries(List<EntryPreview> entries) {
        this.entries = entries;
    }
}
