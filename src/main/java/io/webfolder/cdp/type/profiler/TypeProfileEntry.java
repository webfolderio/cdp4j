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
package io.webfolder.cdp.type.profiler;

import io.webfolder.cdp.annotation.Experimental;
import java.util.ArrayList;
import java.util.List;

/**
 * Source offset and types for a parameter or return value
 */
@Experimental
public class TypeProfileEntry {
    private Integer offset;

    private List<TypeObject> types = new ArrayList<>();

    /**
     * Source offset of the parameter or end of function for return values.
     */
    public Integer getOffset() {
        return offset;
    }

    /**
     * Source offset of the parameter or end of function for return values.
     */
    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    /**
     * The types for this parameter or return value.
     */
    public List<TypeObject> getTypes() {
        return types;
    }

    /**
     * The types for this parameter or return value.
     */
    public void setTypes(List<TypeObject> types) {
        this.types = types;
    }
}
