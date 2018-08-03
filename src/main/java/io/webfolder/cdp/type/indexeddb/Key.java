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

import io.webfolder.cdp.type.constant.KeyType;

import java.util.ArrayList;
import java.util.List;

/**
 * Key
 */
public class Key {
    private KeyType type;

    private Double number;

    private String string;

    private Double date;

    private List<Key> array = new ArrayList<>();

    /**
     * Key type.
     */
    public KeyType getType() {
        return type;
    }

    /**
     * Key type.
     */
    public void setType(KeyType type) {
        this.type = type;
    }

    /**
     * Number value.
     */
    public Double getNumber() {
        return number;
    }

    /**
     * Number value.
     */
    public void setNumber(Double number) {
        this.number = number;
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
     * Date value.
     */
    public Double getDate() {
        return date;
    }

    /**
     * Date value.
     */
    public void setDate(Double date) {
        this.date = date;
    }

    /**
     * Array value.
     */
    public List<Key> getArray() {
        return array;
    }

    /**
     * Array value.
     */
    public void setArray(List<Key> array) {
        this.array = array;
    }
}
