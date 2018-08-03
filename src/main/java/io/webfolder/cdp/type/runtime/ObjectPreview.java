/**
 * cdp4j - Chrome DevTools Protocol for Java
 * Copyright © 2017, 2018 WebFolder OÜ (support@webfolder.io)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package io.webfolder.cdp.type.runtime;

import io.webfolder.cdp.annotation.Experimental;
import io.webfolder.cdp.type.constant.ObjectSubtypeHint;
import io.webfolder.cdp.type.constant.ObjectType;
import java.util.ArrayList;
import java.util.List;

/**
 * Object containing abbreviated remote object value
 */
@Experimental
public class ObjectPreview {
    private ObjectType type;

    private ObjectSubtypeHint subtype;

    private String description;

    private Boolean overflow;

    private List<PropertyPreview> properties = new ArrayList<>();

    private List<EntryPreview> entries = new ArrayList<>();

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
