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
package io.webfolder.cdp.type.indexeddb;

import io.webfolder.cdp.type.constant.KeyPathType;
import java.util.ArrayList;
import java.util.List;

/**
 * Key path
 */
public class KeyPath {
    private KeyPathType type;

    private String string;

    private List<String> array = new ArrayList<>();

    /**
     * Key path type.
     */
    public KeyPathType getType() {
        return type;
    }

    /**
     * Key path type.
     */
    public void setType(KeyPathType type) {
        this.type = type;
    }

    /**
     * String value.
     */
    public String getString() {
        return string;
    }

    /**
     * String value.
     */
    public void setString(String string) {
        this.string = string;
    }

    /**
     * Array value.
     */
    public List<String> getArray() {
        return array;
    }

    /**
     * Array value.
     */
    public void setArray(List<String> array) {
        this.array = array;
    }
}
