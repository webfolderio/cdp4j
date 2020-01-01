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

import java.util.List;

import com.google.gson.annotations.SerializedName;
import com.vimeo.stag.UseStag;

import io.webfolder.cdp.type.runtime.RemoteObject;

/**
 * JavaScript call frame
 * Array of call frames form the call stack
 */
@UseStag
public class CallFrame {
    private String callFrameId;

    private String functionName;

    private Location functionLocation;

    private Location location;

    private String url;

    private List<Scope> scopeChain;

    @SerializedName("this")
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
