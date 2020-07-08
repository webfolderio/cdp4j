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
import io.webfolder.cdp.type.runtime.AwaitPromiseResult;
import io.webfolder.cdp.type.runtime.CallArgument;
import io.webfolder.cdp.type.runtime.CallFunctionOnResult;
import io.webfolder.cdp.type.runtime.CompileScriptResult;
import io.webfolder.cdp.type.runtime.EvaluateResult;
import io.webfolder.cdp.type.runtime.GetHeapUsageResult;
import io.webfolder.cdp.type.runtime.GetPropertiesResult;
import io.webfolder.cdp.type.runtime.RemoteObject;
import io.webfolder.cdp.type.runtime.RunScriptResult;

public class RuntimeImpl implements Runtime {

	private static final Object[] EMPTY_VALUES = new Object[]{};
	private static final String[] EMPTY_ARGS = new String[]{};
	private final SessionInvocationHandler handler;
	private static final TypeToken<List<String>> GLOBAL_LEXICAL_SCOPE_NAMES = new TypeToken<List<String>>() { };

	public RuntimeImpl(SessionInvocationHandler handler) {
		this.handler = handler;
	}

	@Override
	public AwaitPromiseResult awaitPromise(String promiseObjectId, Boolean returnByValue, Boolean generatePreview) {
		return (AwaitPromiseResult) handler.invoke("Runtime", "awaitPromise", "Runtime.awaitPromise", null,
				AwaitPromiseResult.class, null, false, false, false,
				new String[]{"promiseObjectId", "returnByValue", "generatePreview"},
				new Object[]{promiseObjectId, returnByValue, generatePreview});
	}

	@Override
	public CallFunctionOnResult callFunctionOn(String functionDeclaration, String objectId,
			List<CallArgument> arguments, Boolean silent, Boolean returnByValue, Boolean generatePreview,
			Boolean userGesture, Boolean awaitPromise, Integer executionContextId, String objectGroup) {
		return (CallFunctionOnResult) handler.invoke("Runtime", "callFunctionOn", "Runtime.callFunctionOn", null,
				CallFunctionOnResult.class, null, false, false, false,
				new String[]{"functionDeclaration", "objectId", "arguments", "silent", "returnByValue",
						"generatePreview", "userGesture", "awaitPromise", "executionContextId", "objectGroup"},
				new Object[]{functionDeclaration, objectId, arguments, silent, returnByValue, generatePreview,
						userGesture, awaitPromise, executionContextId, objectGroup});
	}

	@Override
	public CompileScriptResult compileScript(String expression, String sourceURL, Boolean persistScript,
			Integer executionContextId) {
		return (CompileScriptResult) handler.invoke("Runtime", "compileScript", "Runtime.compileScript", null,
				CompileScriptResult.class, null, false, false, false,
				new String[]{"expression", "sourceURL", "persistScript", "executionContextId"},
				new Object[]{expression, sourceURL, persistScript, executionContextId});
	}

	@Override
	public void disable() {
		handler.invoke("Runtime", "disable", "Runtime.disable", null, void.class, null, true, false, true, EMPTY_ARGS,
				EMPTY_VALUES);
	}

	@Override
	public void discardConsoleEntries() {
		handler.invoke("Runtime", "discardConsoleEntries", "Runtime.discardConsoleEntries", null, void.class, null,
				true, false, false, EMPTY_ARGS, EMPTY_VALUES);
	}

	@Override
	public void enable() {
		handler.invoke("Runtime", "enable", "Runtime.enable", null, void.class, null, true, true, false, EMPTY_ARGS,
				EMPTY_VALUES);
	}

	@Override
	public EvaluateResult evaluate(String expression, String objectGroup, Boolean includeCommandLineAPI, Boolean silent,
			Integer contextId, Boolean returnByValue, Boolean generatePreview, Boolean userGesture,
			Boolean awaitPromise, Boolean throwOnSideEffect, Double timeout, Boolean disableBreaks, Boolean replMode,
			Boolean allowUnsafeEvalBlockedByCSP) {
		return (EvaluateResult) handler.invoke("Runtime", "evaluate", "Runtime.evaluate", null, EvaluateResult.class,
				null, false, false, false,
				new String[]{"expression", "objectGroup", "includeCommandLineAPI", "silent", "contextId",
						"returnByValue", "generatePreview", "userGesture", "awaitPromise", "throwOnSideEffect",
						"timeout", "disableBreaks", "replMode", "allowUnsafeEvalBlockedByCSP"},
				new Object[]{expression, objectGroup, includeCommandLineAPI, silent, contextId, returnByValue,
						generatePreview, userGesture, awaitPromise, throwOnSideEffect, timeout, disableBreaks, replMode,
						allowUnsafeEvalBlockedByCSP});
	}

	@Override
	public String getIsolateId() {
		return (String) handler.invoke("Runtime", "getIsolateId", "Runtime.getIsolateId", "id", String.class, null,
				false, false, false, EMPTY_ARGS, EMPTY_VALUES);
	}

	@Override
	public GetHeapUsageResult getHeapUsage() {
		return (GetHeapUsageResult) handler.invoke("Runtime", "getHeapUsage", "Runtime.getHeapUsage", null,
				GetHeapUsageResult.class, null, false, false, false, EMPTY_ARGS, EMPTY_VALUES);
	}

	@Override
	public GetPropertiesResult getProperties(String objectId, Boolean ownProperties, Boolean accessorPropertiesOnly,
			Boolean generatePreview) {
		return (GetPropertiesResult) handler.invoke("Runtime", "getProperties", "Runtime.getProperties", null,
				GetPropertiesResult.class, null, false, false, false,
				new String[]{"objectId", "ownProperties", "accessorPropertiesOnly", "generatePreview"},
				new Object[]{objectId, ownProperties, accessorPropertiesOnly, generatePreview});
	}

	@Override
	@java.lang.SuppressWarnings("unchecked")
	public List<String> globalLexicalScopeNames(Integer executionContextId) {
		return (List<String>) handler.invoke("Runtime", "globalLexicalScopeNames", "Runtime.globalLexicalScopeNames",
				"names", List.class, GLOBAL_LEXICAL_SCOPE_NAMES.getType(), false, false, false,
				new String[]{"executionContextId"}, new Object[]{executionContextId});
	}

	@Override
	public RemoteObject queryObjects(String prototypeObjectId, String objectGroup) {
		return (RemoteObject) handler.invoke("Runtime", "queryObjects", "Runtime.queryObjects", "objects",
				RemoteObject.class, null, false, false, false, new String[]{"prototypeObjectId", "objectGroup"},
				new Object[]{prototypeObjectId, objectGroup});
	}

	@Override
	public void releaseObject(String objectId) {
		handler.invoke("Runtime", "releaseObject", "Runtime.releaseObject", null, void.class, null, true, false, false,
				new String[]{"objectId"}, new Object[]{objectId});
	}

	@Override
	public void releaseObjectGroup(String objectGroup) {
		handler.invoke("Runtime", "releaseObjectGroup", "Runtime.releaseObjectGroup", null, void.class, null, true,
				false, false, new String[]{"objectGroup"}, new Object[]{objectGroup});
	}

	@Override
	public void runIfWaitingForDebugger() {
		handler.invoke("Runtime", "runIfWaitingForDebugger", "Runtime.runIfWaitingForDebugger", null, void.class, null,
				true, false, false, EMPTY_ARGS, EMPTY_VALUES);
	}

	@Override
	public RunScriptResult runScript(String scriptId, Integer executionContextId, String objectGroup, Boolean silent,
			Boolean includeCommandLineAPI, Boolean returnByValue, Boolean generatePreview, Boolean awaitPromise) {
		return (RunScriptResult) handler.invoke("Runtime", "runScript", "Runtime.runScript", null,
				RunScriptResult.class, null, false, false, false,
				new String[]{"scriptId", "executionContextId", "objectGroup", "silent", "includeCommandLineAPI",
						"returnByValue", "generatePreview", "awaitPromise"},
				new Object[]{scriptId, executionContextId, objectGroup, silent, includeCommandLineAPI, returnByValue,
						generatePreview, awaitPromise});
	}

	@Override
	public void setAsyncCallStackDepth(Integer maxDepth) {
		handler.invoke("Runtime", "setAsyncCallStackDepth", "Runtime.setAsyncCallStackDepth", null, void.class, null,
				true, false, false, new String[]{"maxDepth"}, new Object[]{maxDepth});
	}

	@Override
	public void setCustomObjectFormatterEnabled(Boolean enabled) {
		handler.invoke("Runtime", "setCustomObjectFormatterEnabled", "Runtime.setCustomObjectFormatterEnabled", null,
				void.class, null, true, false, false, new String[]{"enabled"}, new Object[]{enabled});
	}

	@Override
	public void setMaxCallStackSizeToCapture(Integer size) {
		handler.invoke("Runtime", "setMaxCallStackSizeToCapture", "Runtime.setMaxCallStackSizeToCapture", null,
				void.class, null, true, false, false, new String[]{"size"}, new Object[]{size});
	}

	@Override
	public void terminateExecution() {
		handler.invoke("Runtime", "terminateExecution", "Runtime.terminateExecution", null, void.class, null, true,
				false, false, EMPTY_ARGS, EMPTY_VALUES);
	}

	@Override
	public void addBinding(String name, Integer executionContextId) {
		handler.invoke("Runtime", "addBinding", "Runtime.addBinding", null, void.class, null, true, false, false,
				new String[]{"name", "executionContextId"}, new Object[]{name, executionContextId});
	}

	@Override
	public void removeBinding(String name) {
		handler.invoke("Runtime", "removeBinding", "Runtime.removeBinding", null, void.class, null, true, false, false,
				new String[]{"name"}, new Object[]{name});
	}

	@Override
	public AwaitPromiseResult awaitPromise(String promiseObjectId) {
		return (AwaitPromiseResult) handler.invoke("Runtime", "awaitPromise", "Runtime.awaitPromise", null,
				AwaitPromiseResult.class, null, false, false, false, new String[]{"promiseObjectId"},
				new Object[]{promiseObjectId});
	}

	@Override
	public CallFunctionOnResult callFunctionOn(String functionDeclaration) {
		return (CallFunctionOnResult) handler.invoke("Runtime", "callFunctionOn", "Runtime.callFunctionOn", null,
				CallFunctionOnResult.class, null, false, false, false, new String[]{"functionDeclaration"},
				new Object[]{functionDeclaration});
	}

	@Override
	public CompileScriptResult compileScript(String expression, String sourceURL, Boolean persistScript) {
		return (CompileScriptResult) handler.invoke("Runtime", "compileScript", "Runtime.compileScript", null,
				CompileScriptResult.class, null, false, false, false,
				new String[]{"expression", "sourceURL", "persistScript"},
				new Object[]{expression, sourceURL, persistScript});
	}

	@Override
	public EvaluateResult evaluate(String expression) {
		return (EvaluateResult) handler.invoke("Runtime", "evaluate", "Runtime.evaluate", null, EvaluateResult.class,
				null, false, false, false, new String[]{"expression"}, new Object[]{expression});
	}

	@Override
	public GetPropertiesResult getProperties(String objectId) {
		return (GetPropertiesResult) handler.invoke("Runtime", "getProperties", "Runtime.getProperties", null,
				GetPropertiesResult.class, null, false, false, false, new String[]{"objectId"}, new Object[]{objectId});
	}

	@Override
	@java.lang.SuppressWarnings("unchecked")
	public List<String> globalLexicalScopeNames() {
		return (List<String>) handler.invoke("Runtime", "globalLexicalScopeNames", "Runtime.globalLexicalScopeNames",
				"names", List.class, GLOBAL_LEXICAL_SCOPE_NAMES.getType(), false, false, false, EMPTY_ARGS,
				EMPTY_VALUES);
	}

	@Override
	public RemoteObject queryObjects(String prototypeObjectId) {
		return (RemoteObject) handler.invoke("Runtime", "queryObjects", "Runtime.queryObjects", "objects",
				RemoteObject.class, null, false, false, false, new String[]{"prototypeObjectId"},
				new Object[]{prototypeObjectId});
	}

	@Override
	public RunScriptResult runScript(String scriptId) {
		return (RunScriptResult) handler.invoke("Runtime", "runScript", "Runtime.runScript", null,
				RunScriptResult.class, null, false, false, false, new String[]{"scriptId"}, new Object[]{scriptId});
	}

	@Override
	public void addBinding(String name) {
		handler.invoke("Runtime", "addBinding", "Runtime.addBinding", null, void.class, null, true, false, false,
				new String[]{"name"}, new Object[]{name});
	}
}