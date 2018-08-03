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
package io.webfolder.cdp.event.debugger;

import io.webfolder.cdp.annotation.Domain;
import io.webfolder.cdp.annotation.EventName;
import io.webfolder.cdp.type.constant.PauseReason;
import io.webfolder.cdp.type.debugger.CallFrame;
import io.webfolder.cdp.type.runtime.StackTrace;
import io.webfolder.cdp.type.runtime.StackTraceId;

import java.util.ArrayList;
import java.util.List;

/**
 * Fired when the virtual machine stopped on breakpoint or exception or any other stop criteria
 */
@Domain("Debugger")
@EventName("paused")
public class Paused {
    private List<CallFrame> callFrames = new ArrayList<>();

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
     * Just scheduled async call will have this stack trace as parent stack during async execution.
     * This field is available only after <code>Debugger.stepInto</code>call with<code>breakOnAsynCall</code> flag.
     */
    public StackTraceId getAsyncCallStackTraceId() {
        return asyncCallStackTraceId;
    }

    /**
     * Just scheduled async call will have this stack trace as parent stack during async execution.
     * This field is available only after <code>Debugger.stepInto</code>call with<code>breakOnAsynCall</code> flag.
     */
    public void setAsyncCallStackTraceId(StackTraceId asyncCallStackTraceId) {
        this.asyncCallStackTraceId = asyncCallStackTraceId;
    }
}
