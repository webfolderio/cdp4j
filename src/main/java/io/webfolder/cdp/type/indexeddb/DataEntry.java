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

import io.webfolder.cdp.type.runtime.RemoteObject;

/**
 * Data entry
 */
@UseStag
public class DataEntry {
    private RemoteObject key;

    private RemoteObject primaryKey;

    private RemoteObject value;

    /**
     * Key object.
     */
    public RemoteObject getKey() {
        return key;
    }

    /**
     * Key object.
     */
    public void setKey(RemoteObject key) {
        this.key = key;
    }

    /**
     * Primary key object.
     */
    public RemoteObject getPrimaryKey() {
        return primaryKey;
    }

    /**
     * Primary key object.
     */
    public void setPrimaryKey(RemoteObject primaryKey) {
        this.primaryKey = primaryKey;
    }

    /**
     * Value object.
     */
    public RemoteObject getValue() {
        return value;
    }

    /**
     * Value object.
     */
    public void setValue(RemoteObject value) {
        this.value = value;
    }
}
