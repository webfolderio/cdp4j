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
 * Database with an array of object stores
 */
public class DatabaseWithObjectStores {
    private String name;

    private Double version;

    private List<ObjectStore> objectStores;

    /**
     * Database name.
     */
    public String getName() {
        return name;
    }

    /**
     * Database name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Database version (type is not 'integer', as the standard
     * requires the version number to be 'unsigned long long')
     */
    public Double getVersion() {
        return version;
    }

    /**
     * Database version (type is not 'integer', as the standard
     * requires the version number to be 'unsigned long long')
     */
    public void setVersion(Double version) {
        this.version = version;
    }

    /**
     * Object stores in this database.
     */
    public List<ObjectStore> getObjectStores() {
        return objectStores;
    }

    /**
     * Object stores in this database.
     */
    public void setObjectStores(List<ObjectStore> objectStores) {
        this.objectStores = objectStores;
    }
}
