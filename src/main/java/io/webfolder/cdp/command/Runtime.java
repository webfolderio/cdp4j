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
package io.webfolder.cdp.command;

import io.webfolder.cdp.annotation.Domain;
import io.webfolder.cdp.annotation.Experimental;
import io.webfolder.cdp.annotation.Optional;
import io.webfolder.cdp.annotation.Returns;
import io.webfolder.cdp.type.runtime.*;

import java.util.List;

/**
 * Runtime domain exposes JavaScript runtime by means of remote evaluation and mirror objects
 * Evaluation results are returned as mirror object that expose object type, string representation
 * and unique identifier that can be used for further object reference
 * Original objects are
 * maintained in memory unless they are either explicitly released or are released along with the
 * other objects in their object group
 */
@Domain("Runtime")
public interface Runtime {
    /**
     * Add handler to promise with given promise object id.
     * 
     * @param promiseObjectId Identifier of the promise.
     * @param returnByValue Whether the result is expected to be a JSON object that should be sent by value.
     * @param generatePreview Whether preview should be generated for the result.
     * 
     * @return AwaitPromiseResult
     */
    AwaitPromiseResult awaitPromise(String promiseObjectId, @Optional Boolean returnByValue,
            @Optional Boolean generatePreview);

    /**
     * Calls function with given declaration on the given object. Object group of the result is
     * inherited from the target object.
     * 
     * @param functionDeclaration Declaration of the function to call.
     * @param objectId Identifier of the object to call function on. Either objectId or executionContextId should
     * be specified.
     * @param arguments Call arguments. All call arguments must belong to the same JavaScript world as the target
     * object.
     * @param silent In silent mode exceptions thrown during evaluation are not reported and do not pause
     * execution. Overrides <code>setPauseOnException</code> state.
     * @param returnByValue Whether the result is expected to be a JSON object which should be sent by value.
     * @param generatePreview Whether preview should be generated for the result.
     * @param userGesture Whether execution should be treated as initiated by user in the UI.
     * @param awaitPromise Whether execution should <code>await</code> for resulting value and return once awaited promise is
     * resolved.
     * @param executionContextId Specifies execution context which global object will be used to call function on. Either
     * executionContextId or objectId should be specified.
     * @param objectGroup Symbolic group name that can be used to release multiple objects. If objectGroup is not
     * specified and objectId is, objectGroup will be inherited from object.
     * 
     * @return CallFunctionOnResult
     */
    CallFunctionOnResult callFunctionOn(String functionDeclaration, @Optional String objectId,
            @Optional List<CallArgument> arguments, @Optional Boolean silent,
            @Optional Boolean returnByValue, @Experimental @Optional Boolean generatePreview,
            @Optional Boolean userGesture, @Optional Boolean awaitPromise,
            @Optional Integer executionContextId, @Optional String objectGroup);

    /**
     * Compiles expression.
     * 
     * @param expression Expression to compile.
     * @param sourceURL Source url to be set for the script.
     * @param persistScript Specifies whether the compiled script should be persisted.
     * @param executionContextId Specifies in which execution context to perform script run. If the parameter is omitted the
     * evaluation will be performed in the context of the inspected page.
     * 
     * @return CompileScriptResult
     */
    CompileScriptResult compileScript(String expression, String sourceURL, Boolean persistScript,
            @Optional Integer executionContextId);

    /**
     * Disables reporting of execution contexts creation.
     */
    void disable();

    /**
     * Discards collected exceptions and console API calls.
     */
    void discardConsoleEntries();

    /**
     * Enables reporting of execution contexts creation by means of <code>executionContextCreated</code> event.
     * When the reporting gets enabled the event will be sent immediately for each existing execution
     * context.
     */
    void enable();

    /**
     * Evaluates expression on global object.
     * 
     * @param expression Expression to evaluate.
     * @param objectGroup Symbolic group name that can be used to release multiple objects.
     * @param includeCommandLineAPI Determines whether Command Line API should be available during the evaluation.
     * @param silent In silent mode exceptions thrown during evaluation are not reported and do not pause
     * execution. Overrides <code>setPauseOnException</code> state.
     * @param contextId Specifies in which execution context to perform evaluation. If the parameter is omitted the
     * evaluation will be performed in the context of the inspected page.
     * @param returnByValue Whether the result is expected to be a JSON object that should be sent by value.
     * @param generatePreview Whether preview should be generated for the result.
     * @param userGesture Whether execution should be treated as initiated by user in the UI.
     * @param awaitPromise Whether execution should <code>await</code> for resulting value and return once awaited promise is
     * resolved.
     * @param throwOnSideEffect Whether to throw an exception if side effect cannot be ruled out during evaluation.
     * @param timeout Terminate execution after timing out (number of milliseconds).
     * 
     * @return EvaluateResult
     */
    EvaluateResult evaluate(String expression, @Optional String objectGroup,
            @Optional Boolean includeCommandLineAPI, @Optional Boolean silent,
            @Optional Integer contextId, @Optional Boolean returnByValue,
            @Experimental @Optional Boolean generatePreview, @Optional Boolean userGesture,
            @Optional Boolean awaitPromise, @Experimental @Optional Boolean throwOnSideEffect,
            @Experimental @Optional Double timeout);

    /**
     * Returns the isolate id.
     * 
     * @return The isolate id.
     */
    @Experimental
    @Returns("id")
    String getIsolateId();

    /**
     * Returns the JavaScript heap usage.
     * It is the total usage of the corresponding isolate not scoped to a particular Runtime.
     * 
     * @return GetHeapUsageResult
     */
    @Experimental
    GetHeapUsageResult getHeapUsage();

    /**
     * Returns properties of a given object. Object group of the result is inherited from the target
     * object.
     * 
     * @param objectId Identifier of the object to return properties for.
     * @param ownProperties If true, returns properties belonging only to the element itself, not to its prototype
     * chain.
     * @param accessorPropertiesOnly If true, returns accessor properties (with getter/setter) only; internal properties are not
     * returned either.
     * @param generatePreview Whether preview should be generated for the results.
     * 
     * @return GetPropertiesResult
     */
    GetPropertiesResult getProperties(String objectId, @Optional Boolean ownProperties,
            @Experimental @Optional Boolean accessorPropertiesOnly,
            @Experimental @Optional Boolean generatePreview);

    /**
     * Returns all let, const and class variables from global scope.
     * 
     * @param executionContextId Specifies in which execution context to lookup global scope variables.
     */
    @Returns("names")
    List<String> globalLexicalScopeNames(@Optional Integer executionContextId);

    @Returns("objects")
    RemoteObject queryObjects(String prototypeObjectId, @Optional String objectGroup);

    /**
     * Releases remote object with given id.
     * 
     * @param objectId Identifier of the object to release.
     */
    void releaseObject(String objectId);

    /**
     * Releases all remote objects that belong to a given group.
     * 
     * @param objectGroup Symbolic object group name.
     */
    void releaseObjectGroup(String objectGroup);

    /**
     * Tells inspected instance to run if it was waiting for debugger to attach.
     */
    void runIfWaitingForDebugger();

    /**
     * Runs script with given id in a given context.
     * 
     * @param scriptId Id of the script to run.
     * @param executionContextId Specifies in which execution context to perform script run. If the parameter is omitted the
     * evaluation will be performed in the context of the inspected page.
     * @param objectGroup Symbolic group name that can be used to release multiple objects.
     * @param silent In silent mode exceptions thrown during evaluation are not reported and do not pause
     * execution. Overrides <code>setPauseOnException</code> state.
     * @param includeCommandLineAPI Determines whether Command Line API should be available during the evaluation.
     * @param returnByValue Whether the result is expected to be a JSON object which should be sent by value.
     * @param generatePreview Whether preview should be generated for the result.
     * @param awaitPromise Whether execution should <code>await</code> for resulting value and return once awaited promise is
     * resolved.
     * 
     * @return RunScriptResult
     */
    RunScriptResult runScript(String scriptId, @Optional Integer executionContextId,
            @Optional String objectGroup, @Optional Boolean silent,
            @Optional Boolean includeCommandLineAPI, @Optional Boolean returnByValue,
            @Optional Boolean generatePreview, @Optional Boolean awaitPromise);

    /**
     * Enables or disables async call stacks tracking.
     * 
     * @param maxDepth Maximum depth of async call stacks. Setting to <code>0</code> will effectively disable collecting async
     * call stacks (default).
     */
    void setAsyncCallStackDepth(Integer maxDepth);

    @Experimental
    void setCustomObjectFormatterEnabled(Boolean enabled);

    @Experimental
    void setMaxCallStackSizeToCapture(Integer size);

    /**
     * Terminate current or next JavaScript execution.
     * Will cancel the termination when the outer-most script execution ends.
     */
    @Experimental
    void terminateExecution();

    /**
     * If executionContextId is empty, adds binding with the given name on the
     * global objects of all inspected contexts, including those created later,
     * bindings survive reloads.
     * If executionContextId is specified, adds binding only on global object of
     * given execution context.
     * Binding function takes exactly one argument, this argument should be string,
     * in case of any other input, function throws an exception.
     * Each binding function call produces Runtime.bindingCalled notification.
     * 
     */
    @Experimental
    void addBinding(String name, @Optional Integer executionContextId);

    /**
     * This method does not remove binding function from global object but
     * unsubscribes current runtime agent from Runtime.bindingCalled notifications.
     * 
     */
    @Experimental
    void removeBinding(String name);

    /**
     * Add handler to promise with given promise object id.
     * 
     * @param promiseObjectId Identifier of the promise.
     * 
     * @return AwaitPromiseResult
     */
    AwaitPromiseResult awaitPromise(String promiseObjectId);

    /**
     * Calls function with given declaration on the given object. Object group of the result is
     * inherited from the target object.
     * 
     * @param functionDeclaration Declaration of the function to call.
     * 
     * @return CallFunctionOnResult
     */
    CallFunctionOnResult callFunctionOn(String functionDeclaration);

    /**
     * Compiles expression.
     * 
     * @param expression Expression to compile.
     * @param sourceURL Source url to be set for the script.
     * @param persistScript Specifies whether the compiled script should be persisted.
     * 
     * @return CompileScriptResult
     */
    CompileScriptResult compileScript(String expression, String sourceURL, Boolean persistScript);

    /**
     * Evaluates expression on global object.
     * 
     * @param expression Expression to evaluate.
     * 
     * @return EvaluateResult
     */
    EvaluateResult evaluate(String expression);

    /**
     * Returns properties of a given object. Object group of the result is inherited from the target
     * object.
     * 
     * @param objectId Identifier of the object to return properties for.
     * 
     * @return GetPropertiesResult
     */
    GetPropertiesResult getProperties(String objectId);

    /**
     * Returns all let, const and class variables from global scope.
     */
    @Returns("names")
    List<String> globalLexicalScopeNames();

    @Returns("objects")
    RemoteObject queryObjects(String prototypeObjectId);

    /**
     * Runs script with given id in a given context.
     * 
     * @param scriptId Id of the script to run.
     * 
     * @return RunScriptResult
     */
    RunScriptResult runScript(String scriptId);

    /**
     * If executionContextId is empty, adds binding with the given name on the
     * global objects of all inspected contexts, including those created later,
     * bindings survive reloads.
     * If executionContextId is specified, adds binding only on global object of
     * given execution context.
     * Binding function takes exactly one argument, this argument should be string,
     * in case of any other input, function throws an exception.
     * Each binding function call produces Runtime.bindingCalled notification.
     * 
     */
    @Experimental
    void addBinding(String name);
}
