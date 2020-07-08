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
package io.webfolder.cdp.type.indexeddb;

import java.util.List;

import io.webfolder.cdp.type.constant.KeyType;

/**
 * Key
 */
public class Key {
    private KeyType type;

    private Double number;

    private String string;

    private Double date;

    private List<Key> array;

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
