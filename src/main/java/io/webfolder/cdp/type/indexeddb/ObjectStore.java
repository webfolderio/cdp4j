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

/**
 * Object store
 */
public class ObjectStore {
    private String name;

    private KeyPath keyPath;

    private Boolean autoIncrement;

    private List<ObjectStoreIndex> indexes;

    /**
     * Object store name.
     */
    public String getName() {
        return name;
    }

    /**
     * Object store name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Object store key path.
     */
    public KeyPath getKeyPath() {
        return keyPath;
    }

    /**
     * Object store key path.
     */
    public void setKeyPath(KeyPath keyPath) {
        this.keyPath = keyPath;
    }

    /**
     * If true, object store has auto increment flag set.
     */
    public Boolean isAutoIncrement() {
        return autoIncrement;
    }

    /**
     * If true, object store has auto increment flag set.
     */
    public void setAutoIncrement(Boolean autoIncrement) {
        this.autoIncrement = autoIncrement;
    }

    /**
     * Indexes in this object store.
     */
    public List<ObjectStoreIndex> getIndexes() {
        return indexes;
    }

    /**
     * Indexes in this object store.
     */
    public void setIndexes(List<ObjectStoreIndex> indexes) {
        this.indexes = indexes;
    }
}
