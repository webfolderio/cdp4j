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
package io.webfolder.cdp.session;

import static io.webfolder.cdp.SelectorEngine.Playwright;
import static io.webfolder.cdp.session.Constant.DOM_PROPERTIES;
import static io.webfolder.cdp.session.Constant.EMPTY_ARGS;
import static io.webfolder.cdp.session.Constant.EMPTY_NODE_ID;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static java.lang.Integer.parseInt;
import static java.lang.String.format;
import static java.lang.String.valueOf;
import static java.util.Collections.emptyList;
import static java.util.Locale.ENGLISH;

import java.util.ArrayList;
import java.util.List;

import io.webfolder.cdp.command.DOM;
import io.webfolder.cdp.command.Runtime;
import io.webfolder.cdp.exception.CdpException;
import io.webfolder.cdp.exception.ElementNotFoundException;
import io.webfolder.cdp.type.dom.Node;
import io.webfolder.cdp.type.runtime.CallArgument;
import io.webfolder.cdp.type.runtime.CallFunctionOnResult;
import io.webfolder.cdp.type.runtime.EvaluateResult;
import io.webfolder.cdp.type.runtime.ExceptionDetails;
import io.webfolder.cdp.type.runtime.GetPropertiesResult;
import io.webfolder.cdp.type.runtime.PropertyDescriptor;
import io.webfolder.cdp.type.runtime.RemoteObject;

public interface Selector {

    /**
     * This method returns <code>true</code> if the element would be selected by the specified selector string;
     * otherwise, returns <code>false</code>.
     * 
     * @param selector css or xpath selector
     * 
     * @return <code>true</code> if the element selected by the specified selector
     */
    default boolean matches(final String selector) {
        return matches(selector, EMPTY_ARGS);
    }

    /**
     * This method returns <code>true</code> if the element would be selected by the specified selector string;
     * otherwise, returns <code>false</code>.
     * 
     * @param selector css or xpath selector
     * @param args format string
     * 
     * @return <code>true</code> if the element selected by the specified selector
     */
    default boolean matches(
                    final String selector,
                    final Object ...args) {
        return matches(null, selector, args);
    }

    /**
     * This method returns <code>true</code> if the element would be selected by
     * the specified selector string; otherwise, returns <code>false</code>.
     * 
     * @param selector
     *            css or xpath selector
     * @param args
     *            format string
     * 
     * @return <code>true</code> if the element selected by the specified
     *         selector
     */
    default boolean matches(final Integer contextId, final String selector, final Object... args) {
        Integer nodeId = null;
        try {
            nodeId = getThis().getNodeId(contextId, selector, args);
        } catch (CdpException e) {
            boolean notFound = e.getMessage() != null &&
                               e.getMessage()
                                .toLowerCase(ENGLISH)
                                .contains("could not find node with given id");
            if (notFound) {
                return false;
            } else {
                throw e;
            }
        }
        if (nodeId == null || EMPTY_NODE_ID.equals(nodeId)) {
            return false;
        }
        boolean retValue = nodeId.intValue() > 0;
        getThis().logExit("matches", format(selector, args), retValue);
        return retValue;
    }


    /**
     * Gets the property value of the matched element
     * 
     * @param selector css or xpath selector
     * @param propertyName property name
     * 
     * @return property value
     */
    default Object getProperty(
                        final String selector,
                        final String propertyName) {
        return getProperty(selector, propertyName, EMPTY_ARGS);
    }

    /**
     * Gets the property value of the matched element
     * 
     * @param selector css or xpath selector
     * @param propertyName property name
     * @param args format string
     * 
     * @return property value
     */
    default Object getProperty(
                        final String selector,
                        final String propertyName,
                        final Object ...args) {
        String objectId = getObjectId(selector, args);
        if (objectId == null) {
            throw new ElementNotFoundException(format(selector, args));
        }
        Object value = getPropertyByObjectId(objectId, propertyName);
        releaseObject(objectId);
        if ( ! DOM_PROPERTIES.contains(propertyName) ) {
            getThis().logExit("getProperty", format(selector, args) + "\", \"" + propertyName,
                                    valueOf(value).replace("\n", "").replace("\r", ""));
        }
        return value;
    }

    /**
     * Sets the property value of the matched element
     * 
     * @param selector css or xpath selector
     * @param propertyName property name
     * @param value property value
     */
    default void setProperty(
            final String selector,
            final String propertyName,
            final Object value) {
        setProperty(selector, propertyName, value, EMPTY_ARGS);
    }

    /**
     * Sets the property value of the matched element
     * 
     * @param selector css or xpath selector
     * @param propertyName property name
     * @param value property value
     * @param args format string
     */
    default void setProperty(
                    final String selector,
                    final String propertyName,
                    final Object value,
                    final Object ...args) {
        if ( ! DOM_PROPERTIES.contains(propertyName) ) {
            getThis().logEntry("setProperty", format(selector) + "\", \"" + propertyName + "\", \"" + value);
        }
        String objectId = getObjectId(selector, args);
        if (objectId == null) {
            throw new ElementNotFoundException(format(selector, args));
        }
        List<CallArgument> arguments = new ArrayList<>(2);
        CallArgument prp = new CallArgument();
        prp.setValue(propertyName);
        CallArgument val = new CallArgument();
        val.setValue(value);
        arguments.add(prp);
        arguments.add(val);
        CallFunctionOnResult callFunctionOn = getThis()
                                                .getCommand()
                                                .getRuntime()
                                                .callFunctionOn(
                                                        "function(property, value) { function index(obj, property, value) { " +
                                                        "if (typeof property == 'string') return index(obj, property.split('.'), value); " +
                                                        "else if (property.length == 1 && value !== undefined) return obj[property[0]] = value; " +
                                                        "else if (property.length == 0) return obj; " +
                                                        "else return index(obj[property[0]], property.slice(1), value); }" +
                                                        "return index(this, property, value); }",
                                                        objectId,
                                                        arguments,
                                                        FALSE, TRUE, FALSE, FALSE, FALSE, null, null);
        String error = null;
        if (callFunctionOn != null) {
            RemoteObject result = callFunctionOn.getResult();
            if (result != null) {
                getThis().releaseObject(result.getObjectId());
            }
            if (callFunctionOn.getExceptionDetails() != null) {
                RemoteObject exception = callFunctionOn.getExceptionDetails().getException();
                if (exception != null) {
                    error = exception.getDescription();
                }
            }
        }
        getThis().releaseObject(objectId);
        if ( error != null ) {
            throw new CdpException(error);
        }
    }

    default PropertyDescriptor getPropertyDescriptor(
                final String objectId,
                final String name) {
        Runtime runtime = getThis().getCommand().getRuntime();
        GetPropertiesResult properties = runtime.getProperties(objectId);
        if (properties == null) {
            return null;
        }
        if (properties.getResult() == null ||
                        properties.getResult().isEmpty()) {
            return null;
        }
        for (PropertyDescriptor next : properties.getResult()) {
            if (name.equals(next.getName())) {
                return next;
            }
        }
        return null;
    }

    /**
     * Gets the property value of the matched element
     * 
     * @param objectId
     * @param propertyName property name
     * 
     * @return property value
     */
    default Object getPropertyByObjectId(
                            final String objectId,
                            final String propertyName) {
        if (propertyName == null || propertyName.trim().isEmpty()) {
            return null;
        }
        if (objectId == null || propertyName == null) {
            return null;
        }
        List<CallArgument> arguments = new ArrayList<>(1);
        CallArgument argProperty = new CallArgument();
        argProperty.setValue(propertyName);
        arguments.add(argProperty);
        CallFunctionOnResult callFunctionOn = getThis()
                                                .getCommand()
                                                .getRuntime()
                                                .callFunctionOn(
                                                        "function(property) { return property.split('.').reduce((o, i) => o[i], this); }",
                                                        objectId,
                                                        arguments,
                                                        FALSE, TRUE,
                                                        FALSE, FALSE,
                                                        FALSE, null, null);
        Object value = null;
        String error = null;
        if (callFunctionOn != null) {
            RemoteObject result = callFunctionOn.getResult();
            if (result != null) {
                value = callFunctionOn.getResult().getValue();
                if ( result != null ) {
                    getThis().releaseObject(result.getObjectId());
                }
            }
            if (callFunctionOn.getExceptionDetails() != null) {
                RemoteObject exception = callFunctionOn.getExceptionDetails().getException();
                if (exception != null) {
                    error = exception.getDescription();
                }
            }
        }
        if ( error != null ) {
            throw new CdpException(error);
        }
        return value;
    }

    // getObjects() requires additional WebSocket call to get RemoteObject
    // Performance of getObjectId() is better than getObjects()
    default List<String> getObjectIds(
                    final String selector,
                    final Object ...args) {
        final DOM     dom       = getThis().getCommand().getDOM();
        final boolean xpath     = isXPath(selector);
        List<String>  objectIds = new ArrayList<>();
        if (xpath) {
            final Runtime  runtime       = getThis().getCommand().getRuntime();
            final String   func          = "$x(\"%s\")";
            final String   expression    = format(func, format(selector.replace("\"", "\\\""), args));
            final Boolean  includeCmdApi = TRUE;
            EvaluateResult result        = runtime.evaluate(expression, null, includeCmdApi,
                                                                null, getThis().getExecutionContextId(), null,
                                                                null, null, null,
                                                                null, null,
                                                                null, null, null);
            if (result == null) {
                return null;
            }
            GetPropertiesResult properties = runtime.getProperties(result.getResult().getObjectId(), true, false, false);
            if ( properties != null ) {
                for (PropertyDescriptor next : properties.getResult()) {
                    if ( ! next.isEnumerable() ) {
                        continue;
                    }
                    int index = parseInt(next.getName());
                    RemoteObject remoteObject = next.getValue();
                    objectIds.add(index, remoteObject.getObjectId());
                }
            }
        } else {
            Integer rootNodeId = dom.getDocument().getNodeId();
            if (rootNodeId == null) {
                return null;
            }
            List<Integer> nodeIds = dom.querySelectorAll(rootNodeId, format(selector, args));
            if (nodeIds == null || nodeIds.isEmpty()) {
                return emptyList();
            }
            for (Integer next : nodeIds) {
                RemoteObject remoteObject = dom.resolveNode(next, null, null, getThis().getExecutionContextId());
                if (remoteObject == null) {
                    return null;
                }
                String objectId = remoteObject.getObjectId();
                if (objectId != null) {
                    objectIds.add(objectId);
                }
            }
        }
        return objectIds;
    }

    default List<String> getObjectIds(final String selector) {
        return getObjectIds(selector, EMPTY_ARGS);
    }

    default String getObjectId(final Integer contextId, final String selector, final Object... args) {
        return getObjectIdWithContext(contextId, selector, args);
    }

    default String getObjectId(final String selector, final Object... args) {
        return getObjectIdWithContext(null, selector, args);
    }

    default String getObjectIdWithContext(
                final Integer contextId,
                final String selector,
                final Object ...args) {
        final DOM     dom    = getThis().getCommand().getDOM();
        final boolean xpath  = isXPath(selector);
        if (xpath || Playwright.equals(getThis().getSelectorEngine())) {

            Node document = dom.getDocument();
            if (document == null) {
                return null;
            }

            RemoteObject docObjectId = dom.resolveNode(document.getNodeId(), null,
                                                       null, contextId == null ? getThis().getExecutionContextId() : contextId);

            List<CallArgument> arguments = new ArrayList<>(2);

            CallArgument argDoc = new CallArgument();
            argDoc.setObjectId(docObjectId.getObjectId());
            arguments.add(argDoc);

            CallArgument argExpression = new CallArgument();
            argExpression.setValue(format(selector, args));
            arguments.add(argExpression);

            Runtime runtime = getThis().getCommand().getRuntime();
            String func = xpath ? "function(doc, expression) { return doc.evaluate(expression, doc, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue; }"
                                : "function(doc, expression) { return cdp4j.querySelector(doc, expression); }";

            CallFunctionOnResult result = runtime.callFunctionOn(func, docObjectId != null ? docObjectId.getObjectId() : null,
                                                                arguments, FALSE,
                                                                FALSE, FALSE,
                                                                FALSE, FALSE,
                                                                contextId,
                                                                null);

            if ( docObjectId != null ) {
                releaseObject(docObjectId.getObjectId());
            }

            if (result == null) {
                return null;
            }

            ExceptionDetails ex = result.getExceptionDetails();
            if ( ex != null && ex.getException() != null ) {
                if ( result.getResult() != null && result.getResult().getObjectId() != null ) {
                    releaseObject(result.getResult().getObjectId());
                }
                if ( ex.getException().getObjectId() != null ) {
                    releaseObject(ex.getException().getObjectId());
                }
                throw new CdpException(ex.getException().getDescription());
            }
            RemoteObject remoteObject = result.getResult();
            if (remoteObject == null) {
                return null;
            }
            if (result.getResult() == null) {
                return null;
            }
            String objectId = result.getResult().getObjectId();
            return ex == null ? objectId : null;
        } else {
            Node document = dom.getDocument();
            if (document == null) {
                return null;
            }
            Integer rootNodeId = document.getNodeId();
            if (rootNodeId == null) {
                return null;
            }
            Integer nodeId = dom.querySelector(rootNodeId, format(selector, args));
            if (nodeId == null || nodeId.intValue() == 0) {
                return null;
            }
            RemoteObject remoteObject = dom.resolveNode(nodeId, null, null, getThis().getExecutionContextId());
            if (remoteObject == null) {
                return null;
            }
            String objectId = remoteObject.getObjectId();
            if (objectId == null) {
                return null;
            }
            return objectId;
        }
    }

    default String getObjectId(final String selector) {
        return getObjectId(selector, EMPTY_ARGS);
    }

    default Integer getNodeId(
                final Integer context,
                final String selector,
                final Object ...args) {
        if (selector == null || selector.trim().isEmpty()) {
            return EMPTY_NODE_ID;
        }
        Integer nodeId = EMPTY_NODE_ID;
        DOM dom = getThis().getCommand().getDOM();
        final boolean xpath = isXPath(selector);
        if (xpath || Playwright.equals(getThis().getSelectorEngine())) {
            String objectId = getThis().getObjectId(context, format(selector, args));
            if ( objectId != null ) {
                nodeId = dom.requestNode(objectId);
                getThis().releaseObject(objectId);
            }
        } else {
            Node document = dom.getDocument();
            if ( document != null ) {
                Integer documentNodeId = document.getNodeId();
                if (documentNodeId != null) {
                    try {
                        nodeId = dom.querySelector(documentNodeId, format(selector, args));
                    } catch (CdpException e) {
                        throw new CdpException(
                                    format("Method invoke error: querySelector(%s). %s",
                                            format(selector, args),
                                            e.getMessage()));
                    }
                }
            }
        }
        return nodeId;
    }

    default Integer getNodeId(final String selector) {
        return getNodeId(null, selector, EMPTY_ARGS);
    }

    default Session releaseObject(final String objectId) {
        if (objectId != null) {
            try {
                getThis().getCommand().getRuntime().releaseObject(objectId);
            } catch (CdpException e) {
                throw e;
            }
        }
        return getThis();
    }

    static boolean isXPath(String selector) {
        return selector.charAt(0) == '/' || selector.charAt(0) == '(';
    }

    Session getThis();
}
