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
package io.webfolder.cdp.command;

import java.util.List;

import com.google.gson.reflect.TypeToken;

import io.webfolder.cdp.session.SessionInvocationHandler;
import io.webfolder.cdp.type.constant.InstrumentationName;
import io.webfolder.cdp.type.constant.PauseOnExceptionState;
import io.webfolder.cdp.type.debugger.BreakLocation;
import io.webfolder.cdp.type.debugger.EvaluateOnCallFrameResult;
import io.webfolder.cdp.type.debugger.Location;
import io.webfolder.cdp.type.debugger.RestartFrameResult;
import io.webfolder.cdp.type.debugger.ScriptPosition;
import io.webfolder.cdp.type.debugger.SearchMatch;
import io.webfolder.cdp.type.debugger.SetBreakpointByUrlResult;
import io.webfolder.cdp.type.debugger.SetBreakpointResult;
import io.webfolder.cdp.type.debugger.SetScriptSourceResult;
import io.webfolder.cdp.type.runtime.CallArgument;
import io.webfolder.cdp.type.runtime.StackTrace;
import io.webfolder.cdp.type.runtime.StackTraceId;

public class DebuggerImpl implements Debugger {

    private static final String[] EMPTY_ARGS = new String[] {};
    private static final Object[] EMPTY_VALUES = new Object[] {};
    private static final TypeToken<List<BreakLocation>> GET_POSSIBLE_BREAKPOINTS = new TypeToken<List<BreakLocation>>() {
    };
    private static final TypeToken<List<SearchMatch>> SEARCH_IN_CONTENT = new TypeToken<List<SearchMatch>>() {
    };
    private final SessionInvocationHandler handler;

    public DebuggerImpl(SessionInvocationHandler handler) {
        this.handler = handler;
    }

    @Override
    public void continueToLocation(Location location) {
        handler.invoke("Debugger", "continueToLocation", "Debugger.continueToLocation", null, void.class, null, true,
                false, false, new String[] { "location" }, new Object[] { location });
    }

    @Override
    public void continueToLocation(Location location, Location targetCallFrames) {
        handler.invoke("Debugger", "continueToLocation", "Debugger.continueToLocation", null, void.class, null, true,
                false, false, new String[] { "location", "targetCallFrames" },
                new Object[] { location, targetCallFrames });
    }

    @Override
    public void disable() {
        handler.invoke("Debugger", "disable", "Debugger.disable", null, void.class, null, true, false, true, EMPTY_ARGS,
                EMPTY_VALUES);
    }

    @Override
    public String enable() {
        return (String) handler.invoke("Debugger", "enable", "Debugger.enable", "debuggerId", String.class, null, false,
                false, false, EMPTY_ARGS, EMPTY_VALUES);
    }

    @Override
    public String enable(Double maxScriptsCacheSize) {
        return (String) handler.invoke("Debugger", "enable", "Debugger.enable", "debuggerId", String.class, null, false,
                false, false, new String[] { "maxScriptsCacheSize" }, new Object[] { maxScriptsCacheSize });
    }

    @Override
    public EvaluateOnCallFrameResult evaluateOnCallFrame(String callFrameId, String expression) {
        return (EvaluateOnCallFrameResult) handler.invoke("Debugger", "evaluateOnCallFrame",
                "Debugger.evaluateOnCallFrame", null, EvaluateOnCallFrameResult.class, null, false, false, false,
                new String[] { "callFrameId", "expression" }, new Object[] { callFrameId, expression });
    }

    @Override
    public EvaluateOnCallFrameResult evaluateOnCallFrame(String callFrameId, String expression, String objectGroup,
            Boolean includeCommandLineAPI, Boolean silent, Boolean returnByValue, Boolean generatePreview,
            Boolean throwOnSideEffect, Double timeout) {
        return (EvaluateOnCallFrameResult) handler.invoke("Debugger", "evaluateOnCallFrame",
                "Debugger.evaluateOnCallFrame", null, EvaluateOnCallFrameResult.class, null, false, false, false,
                new String[] { "callFrameId", "expression", "objectGroup", "includeCommandLineAPI", "silent",
                        "returnByValue", "generatePreview", "throwOnSideEffect", "timeout" },
                new Object[] { callFrameId, expression, objectGroup, includeCommandLineAPI, silent, returnByValue,
                        generatePreview, throwOnSideEffect, timeout });
    }

    @Override
    @java.lang.SuppressWarnings("unchecked")
    public List<BreakLocation> getPossibleBreakpoints(Location start) {
        return (List<BreakLocation>) handler.invoke("Debugger", "getPossibleBreakpoints",
                "Debugger.getPossibleBreakpoints", "locations", List.class, GET_POSSIBLE_BREAKPOINTS.getType(), false,
                false, false, new String[] { "start" }, new Object[] { start });
    }

    @Override
    @java.lang.SuppressWarnings("unchecked")
    public List<BreakLocation> getPossibleBreakpoints(Location start, Location end, Boolean restrictToFunction) {
        return (List<BreakLocation>) handler.invoke("Debugger", "getPossibleBreakpoints",
                "Debugger.getPossibleBreakpoints", "locations", List.class, GET_POSSIBLE_BREAKPOINTS.getType(), false,
                false, false, new String[] { "start", "end", "restrictToFunction" },
                new Object[] { start, end, restrictToFunction });
    }

    @Override
    public String getScriptSource(String scriptId) {
        return (String) handler.invoke("Debugger", "getScriptSource", "Debugger.getScriptSource", "scriptSource",
                String.class, null, false, false, false, new String[] { "scriptId" }, new Object[] { scriptId });
    }

    @Override
    public StackTrace getStackTrace(StackTraceId stackTraceId) {
        return (StackTrace) handler.invoke("Debugger", "getStackTrace", "Debugger.getStackTrace", "stackTrace",
                StackTrace.class, null, false, false, false, new String[] { "stackTraceId" },
                new Object[] { stackTraceId });
    }

    @Override
    public void pause() {
        handler.invoke("Debugger", "pause", "Debugger.pause", null, void.class, null, true, false, false, EMPTY_ARGS,
                EMPTY_VALUES);
    }

    @Override
    public void pauseOnAsyncCall(StackTraceId parentStackTraceId) {
        handler.invoke("Debugger", "pauseOnAsyncCall", "Debugger.pauseOnAsyncCall", null, void.class, null, true, false,
                false, new String[] { "parentStackTraceId" }, new Object[] { parentStackTraceId });
    }

    @Override
    public void removeBreakpoint(String breakpointId) {
        handler.invoke("Debugger", "removeBreakpoint", "Debugger.removeBreakpoint", null, void.class, null, true, false,
                false, new String[] { "breakpointId" }, new Object[] { breakpointId });
    }

    @Override
    public RestartFrameResult restartFrame(String callFrameId) {
        return (RestartFrameResult) handler.invoke("Debugger", "restartFrame", "Debugger.restartFrame", null,
                RestartFrameResult.class, null, false, false, false, new String[] { "callFrameId" },
                new Object[] { callFrameId });
    }

    @Override
    public void resume() {
        handler.invoke("Debugger", "resume", "Debugger.resume", null, void.class, null, true, false, false, EMPTY_ARGS,
                EMPTY_VALUES);
    }

    @Override
    @java.lang.SuppressWarnings("unchecked")
    public List<SearchMatch> searchInContent(String scriptId, String query) {
        return (List<SearchMatch>) handler.invoke("Debugger", "searchInContent", "Debugger.searchInContent", "result",
                List.class, SEARCH_IN_CONTENT.getType(), false, false, false, new String[] { "scriptId", "query" },
                new Object[] { scriptId, query });
    }

    @Override
    @java.lang.SuppressWarnings("unchecked")
    public List<SearchMatch> searchInContent(String scriptId, String query, Boolean caseSensitive, Boolean isRegex) {
        return (List<SearchMatch>) handler.invoke("Debugger", "searchInContent", "Debugger.searchInContent", "result",
                List.class, SEARCH_IN_CONTENT.getType(), false, false, false,
                new String[] { "scriptId", "query", "caseSensitive", "isRegex" },
                new Object[] { scriptId, query, caseSensitive, isRegex });
    }

    @Override
    public void setAsyncCallStackDepth(Integer maxDepth) {
        handler.invoke("Debugger", "setAsyncCallStackDepth", "Debugger.setAsyncCallStackDepth", null, void.class, null,
                true, false, false, new String[] { "maxDepth" }, new Object[] { maxDepth });
    }

    @Override
    public void setBlackboxedRanges(String scriptId, List<ScriptPosition> positions) {
        handler.invoke("Debugger", "setBlackboxedRanges", "Debugger.setBlackboxedRanges", null, void.class, null, true,
                false, false, new String[] { "scriptId", "positions" }, new Object[] { scriptId, positions });
    }

    @Override
    public void setBlackboxPatterns(List<String> patterns) {
        handler.invoke("Debugger", "setBlackboxPatterns", "Debugger.setBlackboxPatterns", null, void.class, null, true,
                false, false, new String[] { "patterns" }, new Object[] { patterns });
    }

    @Override
    public SetBreakpointResult setBreakpoint(Location location) {
        return (SetBreakpointResult) handler.invoke("Debugger", "setBreakpoint", "Debugger.setBreakpoint", null,
                SetBreakpointResult.class, null, false, false, false, new String[] { "location" },
                new Object[] { location });
    }

    @Override
    public SetBreakpointResult setBreakpoint(Location location, String condition) {
        return (SetBreakpointResult) handler.invoke("Debugger", "setBreakpoint", "Debugger.setBreakpoint", null,
                SetBreakpointResult.class, null, false, false, false, new String[] { "location", "condition" },
                new Object[] { location, condition });
    }

    @Override
    public SetBreakpointByUrlResult setBreakpointByUrl(Integer lineNumber) {
        return (SetBreakpointByUrlResult) handler.invoke("Debugger", "setBreakpointByUrl",
                "Debugger.setBreakpointByUrl", null, SetBreakpointByUrlResult.class, null, false, false, false,
                new String[] { "lineNumber" }, new Object[] { lineNumber });
    }

    @Override
    public SetBreakpointByUrlResult setBreakpointByUrl(Integer lineNumber, String url, String urlRegex,
            String scriptHash, Integer columnNumber, String condition) {
        return (SetBreakpointByUrlResult) handler.invoke("Debugger", "setBreakpointByUrl",
                "Debugger.setBreakpointByUrl", null, SetBreakpointByUrlResult.class, null, false, false, false,
                new String[] { "lineNumber", "url", "urlRegex", "scriptHash", "columnNumber", "condition" },
                new Object[] { lineNumber, url, urlRegex, scriptHash, columnNumber, condition });
    }

    @Override
    public String setBreakpointOnFunctionCall(String objectId) {
        return (String) handler.invoke("Debugger", "setBreakpointOnFunctionCall",
                "Debugger.setBreakpointOnFunctionCall", "breakpointId", String.class, null, false, false, false,
                new String[] { "objectId" }, new Object[] { objectId });
    }

    @Override
    public String setBreakpointOnFunctionCall(String objectId, String condition) {
        return (String) handler.invoke("Debugger", "setBreakpointOnFunctionCall",
                "Debugger.setBreakpointOnFunctionCall", "breakpointId", String.class, null, false, false, false,
                new String[] { "objectId", "condition" }, new Object[] { objectId, condition });
    }

    @Override
    public void setBreakpointsActive(Boolean active) {
        handler.invoke("Debugger", "setBreakpointsActive", "Debugger.setBreakpointsActive", null, void.class, null,
                true, false, false, new String[] { "active" }, new Object[] { active });
    }

    @Override
    public String setInstrumentationBreakpoint(InstrumentationName instrumentation) {
        return (String) handler.invoke("Debugger", "setInstrumentationBreakpoint",
                "Debugger.setInstrumentationBreakpoint", "breakpointId", String.class, null, false, false, false,
                new String[] { "instrumentation" }, new Object[] { instrumentation });
    }

    @Override
    public void setPauseOnExceptions(PauseOnExceptionState state) {
        handler.invoke("Debugger", "setPauseOnExceptions", "Debugger.setPauseOnExceptions", null, void.class, null,
                true, false, false, new String[] { "state" }, new Object[] { state });
    }

    @Override
    public void setReturnValue(CallArgument newValue) {
        handler.invoke("Debugger", "setReturnValue", "Debugger.setReturnValue", null, void.class, null, true, false,
                false, new String[] { "newValue" }, new Object[] { newValue });
    }

    @Override
    public SetScriptSourceResult setScriptSource(String scriptId, String scriptSource) {
        return (SetScriptSourceResult) handler.invoke("Debugger", "setScriptSource", "Debugger.setScriptSource", null,
                SetScriptSourceResult.class, null, false, false, false, new String[] { "scriptId", "scriptSource" },
                new Object[] { scriptId, scriptSource });
    }

    @Override
    public SetScriptSourceResult setScriptSource(String scriptId, String scriptSource, Boolean dryRun) {
        return (SetScriptSourceResult) handler.invoke("Debugger", "setScriptSource", "Debugger.setScriptSource", null,
                SetScriptSourceResult.class, null, false, false, false,
                new String[] { "scriptId", "scriptSource", "dryRun" }, new Object[] { scriptId, scriptSource, dryRun });
    }

    @Override
    public void setSkipAllPauses(Boolean skip) {
        handler.invoke("Debugger", "setSkipAllPauses", "Debugger.setSkipAllPauses", null, void.class, null, true, false,
                false, new String[] { "skip" }, new Object[] { skip });
    }

    @Override
    public void setVariableValue(Integer scopeNumber, String variableName, CallArgument newValue, String callFrameId) {
        handler.invoke("Debugger", "setVariableValue", "Debugger.setVariableValue", null, void.class, null, true, false,
                false, new String[] { "scopeNumber", "variableName", "newValue", "callFrameId" },
                new Object[] { scopeNumber, variableName, newValue, callFrameId });
    }

    @Override
    public void stepInto() {
        handler.invoke("Debugger", "stepInto", "Debugger.stepInto", null, void.class, null, true, false, false,
                EMPTY_ARGS, EMPTY_VALUES);
    }

    @Override
    public void stepInto(Boolean breakOnAsyncCall) {
        handler.invoke("Debugger", "stepInto", "Debugger.stepInto", null, void.class, null, true, false, false,
                new String[] { "breakOnAsyncCall" }, new Object[] { breakOnAsyncCall });
    }

    @Override
    public void stepOut() {
        handler.invoke("Debugger", "stepOut", "Debugger.stepOut", null, void.class, null, true, false, false,
                EMPTY_ARGS, EMPTY_VALUES);
    }

    @Override
    public void stepOver() {
        handler.invoke("Debugger", "stepOver", "Debugger.stepOver", null, void.class, null, true, false, false,
                EMPTY_ARGS, EMPTY_VALUES);
    }
}