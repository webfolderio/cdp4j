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
package io.webfolder.cdp.event.debugger;

import java.util.List;

import io.webfolder.cdp.annotation.Domain;
import io.webfolder.cdp.annotation.EventName;
import io.webfolder.cdp.type.constant.PauseReason;
import io.webfolder.cdp.type.debugger.CallFrame;
import io.webfolder.cdp.type.runtime.StackTrace;
import io.webfolder.cdp.type.runtime.StackTraceId;

/**
 * Fired when the virtual machine stopped on breakpoint or exception or any other stop criteria
 */
@Domain("Debugger")
@EventName("paused")
public class Paused {
    private List<CallFrame> callFrames;

    private PauseReason reason;

    private Object data;

    private List<String> hitBreakpoints;

    private StackTrace asyncStackTrace;

    private StackTraceId asyncStackTraceId;

    private StackTraceId asyncCallStackTraceId;

    /**
     * Call stack the virtual machine stopped on.
     */
    public List<CallFrame> getCallFrames() {
        return callFrames;
    }

    /**
     * Call stack the virtual machine stopped on.
     */
    public void setCallFrames(List<CallFrame> callFrames) {
        this.callFrames = callFrames;
    }

    /**
     * Pause reason.
     */
    public PauseReason getReason() {
        return reason;
    }

    /**
     * Pause reason.
     */
    public void setReason(PauseReason reason) {
        this.reason = reason;
    }

    /**
     * Object containing break-specific auxiliary properties.
     */
    public Object getData() {
        return data;
    }

    /**
     * Object containing break-specific auxiliary properties.
     */
    public void setData(Object data) {
        this.data = data;
    }

    /**
     * Hit breakpoints IDs
     */
    public List<String> getHitBreakpoints() {
        return hitBreakpoints;
    }

    /**
     * Hit breakpoints IDs
     */
    public void setHitBreakpoints(List<String> hitBreakpoints) {
        this.hitBreakpoints = hitBreakpoints;
    }

    /**
     * Async stack trace, if any.
     */
    public StackTrace getAsyncStackTrace() {
        return asyncStackTrace;
    }

    /**
     * Async stack trace, if any.
     */
    public void setAsyncStackTrace(StackTrace asyncStackTrace) {
        this.asyncStackTrace = asyncStackTrace;
    }

    /**
     * Async stack trace, if any.
     */
    public StackTraceId getAsyncStackTraceId() {
        return asyncStackTraceId;
    }

    /**
     * Async stack trace, if any.
     */
    public void setAsyncStackTraceId(StackTraceId asyncStackTraceId) {
        this.asyncStackTraceId = asyncStackTraceId;
    }

    /**
     * Never present, will be removed.
     */
    public StackTraceId getAsyncCallStackTraceId() {
        return asyncCallStackTraceId;
    }

    /**
     * Never present, will be removed.
     */
    public void setAsyncCallStackTraceId(StackTraceId asyncCallStackTraceId) {
        this.asyncCallStackTraceId = asyncCallStackTraceId;
    }
}
