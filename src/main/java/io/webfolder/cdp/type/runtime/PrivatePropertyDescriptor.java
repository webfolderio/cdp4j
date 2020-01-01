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

import com.vimeo.stag.UseStag;

import io.webfolder.cdp.annotation.Experimental;

/**
 * Object private field descriptor
 */
@Experimental
@UseStag
public class PrivatePropertyDescriptor {
    private String name;

    private RemoteObject value;

    private RemoteObject get;

    private RemoteObject set;

    /**
     * Private property name.
     */
    public String getName() {
        return name;
    }

    /**
     * Private property name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * The value associated with the private property.
     */
    public RemoteObject getValue() {
        return value;
    }

    /**
     * The value associated with the private property.
     */
    public void setValue(RemoteObject value) {
        this.value = value;
    }

    /**
     * A function which serves as a getter for the private property,
     * or `undefined` if there is no getter (accessor descriptors only).
     */
    public RemoteObject getGet() {
        return get;
    }

    /**
     * A function which serves as a getter for the private property,
     * or `undefined` if there is no getter (accessor descriptors only).
     */
    public void setGet(RemoteObject get) {
        this.get = get;
    }

    /**
     * A function which serves as a setter for the private property,
     * or `undefined` if there is no setter (accessor descriptors only).
     */
    public RemoteObject getSet() {
        return set;
    }

    /**
     * A function which serves as a setter for the private property,
     * or `undefined` if there is no setter (accessor descriptors only).
     */
    public void setSet(RemoteObject set) {
        this.set = set;
    }
}
