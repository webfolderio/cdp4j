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
package io.webfolder.cdp.type.debugger;

import io.webfolder.cdp.type.constant.ScopeType;
import io.webfolder.cdp.type.runtime.RemoteObject;

/**
 * Scope description
 */
public class Scope {
    private ScopeType type;

    private RemoteObject object;

    private String name;

    private Location startLocation;

    private Location endLocation;

    /**
     * Scope type.
     */
    public ScopeType getType() {
        return type;
    }

    /**
     * Scope type.
     */
    public void setType(ScopeType type) {
        this.type = type;
    }

    /**
     * Object representing the scope. For global and with scopes it represents the actual
     * object; for the rest of the scopes, it is artificial transient object enumerating scope
     * variables as its properties.
     */
    public RemoteObject getObject() {
        return object;
    }

    /**
     * Object representing the scope. For global and with scopes it represents the actual
     * object; for the rest of the scopes, it is artificial transient object enumerating scope
     * variables as its properties.
     */
    public void setObject(RemoteObject object) {
        this.object = object;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Location in the source code where scope starts
     */
    public Location getStartLocation() {
        return startLocation;
    }

    /**
     * Location in the source code where scope starts
     */
    public void setStartLocation(Location startLocation) {
        this.startLocation = startLocation;
    }

    /**
     * Location in the source code where scope ends
     */
    public Location getEndLocation() {
        return endLocation;
    }

    /**
     * Location in the source code where scope ends
     */
    public void setEndLocation(Location endLocation) {
        this.endLocation = endLocation;
    }
}
