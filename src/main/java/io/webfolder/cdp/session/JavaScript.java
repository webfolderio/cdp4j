/**
 * cdp4j - Chrome DevTools Protocol for Java
 * Copyright © 2017, 2018 WebFolder OÜ (support@webfolder.io)
 * <p>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * <p>
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package io.webfolder.cdp.session;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.webfolder.cdp.command.Runtime;
import io.webfolder.cdp.exception.CdpException;
import io.webfolder.cdp.json.JsonResponse;
import io.webfolder.cdp.type.constant.ObjectType;
import io.webfolder.cdp.type.runtime.CallArgument;
import io.webfolder.cdp.type.runtime.CallFunctionOnResult;
import io.webfolder.cdp.type.runtime.EvaluateResult;
import io.webfolder.cdp.type.runtime.RemoteObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringJoiner;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static java.lang.String.format;
import static java.lang.String.valueOf;
import static java.util.Arrays.asList;

public interface JavaScript {

    /**
     * Evaluates JavaScript expression in global scope.
     *
     * @param expression JavaScript expression
     *
     * @return execution result
     */
    default Object evaluate(String expression) {
        Runtime runtime = getThis().getCommand().getRuntime();
        Integer contextId = getThis().getExecutionContextId();
        EvaluateResult result = runtime.evaluate(expression, null, null,
                null, contextId, null,
                null, null, null, null, null);
        if (result == null) {
            return null;
        }
        RemoteObject remoteObject = result.getResult();
        if (remoteObject == null) {
            return null;
        }
        String objectId = result.getResult().getObjectId();
        if (objectId != null) {
            getThis().releaseObject(objectId);
        }
        Object value = remoteObject.getValue();
        getThis().logExit("evaluate", expression, value);
        return value;
    }

    /**
     * Calls JavaScript function.
     *
     * <p>
     * Function must be declared at the global {@literal (window object)} scope.
     * You can use <strong>dot notation</strong> for function name.
     * </p>
     *
     * @param name function name
     */
    default void callFunction(String name) {
        callFunction(name, void.class, Constant.EMPTY_ARGS);
    }

    default <T> T callFunction(String name, Class<T> returnType) {
        return callFunction(name, returnType, Constant.EMPTY_ARGS);
    }

    /**
     * Calls JavaScript function.
     *
     * <p>
     * Function must be declared at the global {@literal (window object)} scope.
     * You can use <strong>dot notation</strong> for function name.
     * </p>
     *
     * @param name function name
     * @param returnType return type of function
     * @param arguments function arguments
     *
     * @return function result
     */
    @SuppressWarnings("unchecked")
    default <T> T callFunction(String name, Class<T> returnType, Object... arguments) {
        CallArgument objArgument = new CallArgument();
        objArgument.setValue(name);

        CallFunctionOnResult funcObj = getThis()
                .getCommand()
                .getRuntime()
                .callFunctionOn("function(functionName) { return functionName.split('.').reduce((o, i) => o[i], this); }",
                        null,
                        asList(objArgument),
                        FALSE, FALSE,
                        FALSE, FALSE,
                        FALSE, getThis().getExecutionContextId(), null);

        if (funcObj.getExceptionDetails() != null &&
                funcObj.getExceptionDetails().getException() != null) {
            getThis().releaseObject(funcObj.getExceptionDetails().getException().getObjectId());
            throw new CdpException(funcObj.getExceptionDetails().getException().getDescription());
        }

        if (ObjectType.Undefined.equals(funcObj.getResult().getType())) {
            getThis().releaseObject(funcObj.getResult().getObjectId());
            throw new CdpException(format("Function [%s] is not defined", name));
        }

        StringJoiner argNames = new StringJoiner(",");

        List<CallArgument> argsFunc = new ArrayList<>(arguments.length);

        int i = 0;
        if (arguments.length > 0) {
            for (Object argument : arguments) {
                CallArgument ca = new CallArgument();
                argsFunc.add(ca);
                if (argument != null) {
                    if (getThis().isPrimitive(argument.getClass())) {
                        ca.setValue(argument);
                    } else {
                        try {
                            ca.setValue(getThis().getJackson().writeValueAsString(argument));
                        } catch (JsonProcessingException e) {
                            getThis().error("Error converting callFunction arguments", e);
                        }
                    }
                }
                argNames.add("arg" + (i + 1));
            }
        }

        CallFunctionOnResult func = getThis()
                .getCommand()
                .getRuntime()
                .callFunctionOn(format("function(%s) { const result = this.apply(this, Array.prototype.slice.call(arguments)); " +
                                "return typeof result === 'undefined' ? undefined : JSON.stringify({ result : result }); }",
                        argNames.toString()),
                        funcObj.getResult().getObjectId(),
                        argsFunc,
                        FALSE, TRUE,
                        FALSE, FALSE,
                        FALSE, null, null);

        getThis().releaseObject(funcObj.getResult().getObjectId());
        getThis().releaseObject(func.getResult().getObjectId());

        if (func.getExceptionDetails() != null &&
                func.getExceptionDetails().getException() != null) {
            getThis().releaseObject(func.getExceptionDetails().getException().getObjectId());
            throw new CdpException(func.getExceptionDetails().getException().getDescription());
        }

        Object value = null;
        if (ObjectType.String.equals(func.getResult().getType()) && !returnType.equals(void.class)) {
            try {
                String json = valueOf(func.getResult().getValue());
                ObjectNode object = getThis().getJackson().readValue(json, ObjectNode.class);
                JsonNode result = object.get("result");
                if (getThis().isPrimitive(returnType)) {
                    value = getThis().getJackson().treeToValue(result, returnType);
                } else {
                    if (result.isValueNode()) {
                        value = getThis().getJackson().treeToValue(result, returnType);
                    }
                }
            } catch (Exception ignored) {
            }
        } else if (ObjectType.Undefined.equals(func.getResult().getType())) {
            value = void.class;
        }

        StringJoiner joiner = new StringJoiner("\", \"");
        for (Object o : arguments) {
            joiner.add(valueOf(o));
        }

        getThis().logExit("callFunction",
                name + (arguments == null || arguments.length == 0 ? "" : "\", " + joiner.toString()),
                valueOf(value).replace("\n", "").replace("\r", ""));

        return !void.class.equals(value) ? (T) value : null;
    }

    /**
     * Gets JavaScript variable.
     *
     * <p>
     * Variable must be declared at the global {@literal (window object)} scope.
     * You can use <strong>dot notation</strong> for variable name.
     * </p>
     *
     * @param name variable name
     * @param returnType variable type
     *
     * @return variable value
     */
    @SuppressWarnings("unchecked")
    public default <T> T getVariable(String name, Class<T> returnType) {
        CallArgument objArgument = new CallArgument();
        objArgument.setValue(name);

        CallFunctionOnResult obj = getThis()
                .getCommand()
                .getRuntime()
                .callFunctionOn(
                        "function(functionName) { const result = functionName.split('.').reduce((o, i) => o[i], this); " +
                                "return typeof result === 'undefined' ? undefined : JSON.stringify({ result : result }); }",
                        null,
                        Collections.singletonList(objArgument),
                        FALSE, FALSE,
                        FALSE, FALSE,
                        FALSE, getThis().getExecutionContextId(), null);

        if (obj.getExceptionDetails() != null &&
                obj.getExceptionDetails().getException() != null) {
            getThis().releaseObject(obj.getExceptionDetails().getException().getObjectId());
            throw new CdpException(obj.getExceptionDetails().getException().getDescription());
        }

        if (ObjectType.Undefined.equals(obj.getResult().getType())) {
            getThis().releaseObject(obj.getResult().getObjectId());
            throw new CdpException(format("Variable [%s] is not defined", name));
        }

        Object value = null;
        if (ObjectType.String.equals(obj.getResult().getType()) && !returnType.equals(void.class)) {
            try {
                String json = valueOf(obj.getResult().getValue());
                JsonResponse response = getThis().getJackson().readValue(json, JsonResponse.class);
                value = getThis().getJackson().treeToValue(response.getResult(), returnType);
            } catch (IOException e) {
                getThis().error("Error reading response at getVariable", e);
            }
        } else if (ObjectType.Undefined.equals(obj.getResult().getType())) {
            value = void.class;
        }

        getThis().releaseObject(obj.getResult().getObjectId());

        return (T) value;
    }

    /**
     * Sets JavaScript variable.
     *
     * <p>
     * Variable must be declared at the global {@literal (window object)} scope.
     * You can use <strong>dot notation</strong> for variable name.
     * </p>
     *
     * @param name variable name
     * @param newValue value
     */
    public default void setVariable(String name, Object newValue) {
        EvaluateResult windowResult = getThis().getCommand().getRuntime().evaluate("window");

        if (windowResult == null) {
            return;
        }

        if (windowResult.getExceptionDetails() != null &&
                windowResult.getExceptionDetails().getException() != null) {
            getThis().releaseObject(windowResult.getExceptionDetails().getException().getObjectId());
            throw new CdpException(windowResult.getExceptionDetails().getException().getDescription());
        }

        CallArgument argVariableName = new CallArgument();
        argVariableName.setValue(name);
        CallArgument argVariableValue = new CallArgument();
        if (newValue != null) {
            if (getThis().isPrimitive(newValue.getClass())) {
                argVariableValue.setValue(newValue);
            } else {
                try {
                    argVariableValue.setValue(getThis().getJackson().writeValueAsString(newValue));
                } catch (JsonProcessingException e) {
                    getThis().error("Error setting variable", e);
                }
            }
        }

        CallFunctionOnResult obj = getThis()
                .getCommand()
                .getRuntime()
                .callFunctionOn("function(is, value) { function index(obj, is, value) { if (typeof is == 'string') return index(obj, is.split('.'), value); " +
                                "else if (is.length === 1 && value !== undefined) return obj[is[0]] = value; else if (is.length === 0) " +
                                "return obj; else return index(obj[is[0]], is.slice(1), value); } index(window, is, value); }", windowResult.getResult().getObjectId(),
                        asList(argVariableName, argVariableValue),
                        FALSE, FALSE,
                        FALSE, FALSE,
                        FALSE, null, null);

        getThis().releaseObject(windowResult.getResult().getObjectId());

        if (obj.getExceptionDetails() != null &&
                obj.getExceptionDetails().getException() != null) {
            getThis().releaseObject(obj.getExceptionDetails().getException().getObjectId());
            throw new CdpException(obj.getExceptionDetails().getException().getDescription());
        }

        getThis().releaseObject(obj.getResult().getObjectId());
    }

    public Session getThis();
}
