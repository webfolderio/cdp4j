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

import com.vimeo.stag.UseStag;

/**
 * Object store index
 */
@UseStag
public class ObjectStoreIndex {
    private String name;

    private KeyPath keyPath;

    private Boolean unique;

    private Boolean multiEntry;

    /**
     * Index name.
     */
    public String getName() {
        return name;
    }

    /**
     * Index name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Index key path.
     */
    public KeyPath getKeyPath() {
        return keyPath;
    }

    /**
     * Index key path.
     */
    public void setKeyPath(KeyPath keyPath) {
        this.keyPath = keyPath;
    }

    /**
     * If true, index is unique.
     */
    public Boolean isUnique() {
        return unique;
    }

    /**
     * If true, index is unique.
     */
    public void setUnique(Boolean unique) {
        this.unique = unique;
    }

    /**
     * If true, index allows multiple entries for a key.
     */
    public Boolean isMultiEntry() {
        return multiEntry;
    }

    /**
     * If true, index allows multiple entries for a key.
     */
    public void setMultiEntry(Boolean multiEntry) {
        this.multiEntry = multiEntry;
    }
}
