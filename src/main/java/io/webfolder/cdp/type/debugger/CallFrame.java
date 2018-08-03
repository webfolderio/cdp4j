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
package io.webfolder.cdp.type.debugger;

import io.webfolder.cdp.type.runtime.RemoteObject;

import java.util.ArrayList;
import java.util.List;

/**
 * JavaScript call frame
 * Array of call frames form the call stack
 */
public class CallFrame {
    private String callFrameId;

    private String functionName;

    private Location functionLocation;

    private Location location;

    private String url;

    private List<Scope> scopeChain = new ArrayList<>();
    private RemoteObject that;

    private RemoteObject returnValue;

    /**
     * Call frame identifier. This identifier is only valid while the virtual machine is paused.
     */
    public String getCallFrameId() {
        return callFrameId;
    }

    /**
     * Call frame identifier. This identifier is only valid while the virtual machine is paused.
     */
    public void setCallFrameId(String callFrameId) {
        this.callFrameId = callFrameId;
    }

    /**
     * Name of the JavaScript function called on this call frame.
     */
    public String getFunctionName() {
        return functionName;
    }

    /**
     * Name of the JavaScript function called on this call frame.
     */
    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    /**
     * Location in the source code.
     */
    public Location getFunctionLocation() {
        return functionLocation;
    }

    /**
     * Location in the source code.
     */
    public void setFunctionLocation(Location functionLocation) {
        this.functionLocation = functionLocation;
    }

    /**
     * Location in the source code.
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Location in the source code.
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * JavaScript script name or url.
     */
    public String getUrl() {
        return url;
    }

    /**
     * JavaScript script name or url.
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Scope chain for this call frame.
     */
    public List<Scope> getScopeChain() {
        return scopeChain;
    }

    /**
     * Scope chain for this call frame.
     */
    public void setScopeChain(List<Scope> scopeChain) {
        this.scopeChain = scopeChain;
    }

    /**
     * <code>this</code> object for this call frame.
     */
    public RemoteObject getThat() {
        return that;
    }

    /**
     * <code>this</code> object for this call frame.
     */
    public void setThat(RemoteObject that) {
        this.that = that;
    }

    /**
     * The value being returned, if the function is at return point.
     */
    public RemoteObject getReturnValue() {
        return returnValue;
    }

    /**
     * The value being returned, if the function is at return point.
     */
    public void setReturnValue(RemoteObject returnValue) {
        this.returnValue = returnValue;
    }
}
