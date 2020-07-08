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

import static io.webfolder.cdp.session.Option.TYPE_TOKEN;
import static java.lang.Boolean.TRUE;
import static java.lang.String.format;
import static java.lang.String.valueOf;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.emptyMap;
import static java.util.stream.Collectors.toList;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import com.google.gson.Gson;

import io.webfolder.cdp.command.DOM;
import io.webfolder.cdp.command.DOMSnapshot;
import io.webfolder.cdp.exception.ElementNotFoundException;
import io.webfolder.cdp.type.dom.BoxModel;
import io.webfolder.cdp.type.domsnapshot.DOMNode;
import io.webfolder.cdp.type.domsnapshot.GetSnapshotResult;
import io.webfolder.cdp.type.runtime.CallFunctionOnResult;
import io.webfolder.cdp.type.runtime.ExceptionDetails;
import io.webfolder.cdp.type.runtime.PropertyDescriptor;
import io.webfolder.cdp.type.runtime.RemoteObject;
import io.webfolder.cdp.type.util.Point;

/**
 * Provides the interfaces for the Document Object Model (DOM).
 */
public interface Dom {

    /**
     * textContent property represents the text content of a node and its descendants.
     * 
     * textContent returns null if the element is a document, a document type, or a notation.
     * To grab all of the text and CDATA data for the whole document, one could use document.documentElement.textContent.
     * 
     * @param selector css or xpath selector
     * 
     * @return textContent returns the concatenation of the textContent property value of every child node,<br>
     * excluding comments and processing instruction nodes. This is an empty string if the node has no children.
     */
    default String getText(final String selector) {
        return getText(selector, Constant.EMPTY_ARGS);
    }

    /**
     * textContent property represents the text content of a node and its descendants.
     * 
     * textContent returns null if the element is a document, a document type, or a notation.
     * To grab all of the text and CDATA data for the whole document, one could use document.documentElement.textContent.
     * 
     * @param selector css or xpath selector
     * @param args format string
     * 
     * @return textContent returns the concatenation of the textContent property value of every child node,<br>
     * excluding comments and processing instruction nodes. This is an empty string if the node has no children.
     */
    default String getText(final String selector, final Object ...args) {
        return (String) getThis().getProperty(selector, "textContent", args);
    }

    /**
     * The HTMLInputElement.select() method selects all the text in a &lt;textarea&gt; element<br>
     * or an &lt;input&gt; element with a text field.
     * 
     * @param selector css or xpath selector
     * 
     * @return this
     */
    default Session selectInputText(final String selector) {
        return selectInputText(selector, Constant.EMPTY_ARGS);
    }

    /**
     * The HTMLInputElement.select() method selects all the text in a &lt;textarea&gt; element<br>
     * or an &lt;input&gt; element with a text field.
     * 
     * @param selector css or xpath selector
     * @param args format string
     * 
     * @return this
     */
    default Session selectInputText(final String selector, final Object ...args) {
        getThis().logEntry("selectInputText", format(selector, args));
        String objectId = getThis().getObjectId(selector, args);
        if (objectId == null) {
            throw new ElementNotFoundException(format(selector, args));
        }
        CallFunctionOnResult functionResult = getThis()
            .getCommand().getRuntime()
            .callFunctionOn("function() { this.select(); }", objectId, null, null, null, null,
                                                                       null, null, null, null);
        if (functionResult != null) {
            RemoteObject result = functionResult.getResult();
            if (result != null) {
                getThis().releaseObject(result.getObjectId());
            }
            ExceptionDetails exceptionDetails = functionResult.getExceptionDetails();
            if (exceptionDetails != null) {
                RemoteObject exception = exceptionDetails.getException();
                if (exception != null) {
                    getThis().error(exception.getDescription());
                }
            }
        }
        getThis().releaseObject(objectId);
        return getThis();
    }

    /**
     * The HTMLElement.focus() method sets focus on the specified element, if it can be focused.
     * 
     * @param selector css or xpath selector
     * 
     * @return this
     */
    default Session focus(final String selector) {
        return focus(null, selector);
    }

    /**
     * The HTMLElement.focus() method sets focus on the specified element, if it
     * can be focused.
     * 
     * @param selector
     *            css or xpath selector
     * @param contextId
     *            Context id of the frame
     * @return this
     */
    default Session focus(final Integer contextId, final String selector) {
        return focus(contextId, selector, Constant.EMPTY_ARGS);
    }

    /**
     * The HTMLElement.focus() method sets focus on the specified element, if it
     * can be focused.
     * 
     * @param selector
     *            css or xpath selector
     * @param args
     *            format string
     * @param contextId
     *            Context id of the frame
     * @return this
     */
    default Session focus(final Integer contextId, final String selector, final Object... args) {
        getThis().logEntry("focus", format(selector, args));
        Integer nodeId = getThis().getNodeId(contextId, selector, contextId, args);
        if (nodeId == null || Constant.EMPTY_NODE_ID.equals(nodeId)) {
            throw new ElementNotFoundException(format(selector, args));
        }
        DOM dom = getThis().getCommand().getDOM();
        dom.focus(nodeId, null, null);
        return getThis();
    }

    /**
     * The HTMLSelectElement.selectedIndex() is a int that reflects the index of the first selected &lt;option&gt; element.
     * The selectedIndex property returns <strong>-1</strong> if a select object does not contain any selected items.
     * 
     * @param selector css or xpath selector
     * 
     * 
     * @return selected index of the first &lt;option&gt; element.
     */
    default int getSelectedIndex(final String selector) {
        return getSelectedIndex(selector, Constant.EMPTY_ARGS);
    }

    /**
     * The HTMLSelectElement.selectedIndex() is a int that reflects the index of the first selected &lt;option&gt; element.
     * The selectedIndex property returns <strong>-1</strong> if a select object does not contain any selected items.
     *
     * @param selector css or xpath selector
     * @param args format string
     * 
     * @return selected index of the first &lt;option&gt; element.
     */
    default int getSelectedIndex(final String selector, final Object ...args) {
        String objectId = getThis().getObjectId(selector, args);
        if (objectId == null) {
            throw new ElementNotFoundException(format(selector, args));
        }
        Double selectedIndex = (Double) getThis().getPropertyByObjectId(objectId, "selectedIndex");
        getThis().releaseObject(objectId);
        if (selectedIndex == null) {
            selectedIndex = -1D;
        }
        getThis().logExit("getSelectedIndex", format(selector, args), selectedIndex.intValue());
        return selectedIndex.intValue();
    }

    /**
     * Set selectedIndex of &lt;option&gt; element.
     * 
     * When you set the selectedIndex property, the display of the select object updates immediately.
     * This method most useful when used with select objects that support selecting only one item at a time.
     * Use {@link #setSelectedOptions(String, List)} method if the multiple attribute is specified for a &lt;select&gt; element.
     * 
     * @param selector css or xpath selector
     * @param index he index of the first selected &lt;option&gt; element.
     * 
     * @return this
     */
    default Session setSelectedIndex(final String selector, final int index) {
        return setSelectedIndex(selector, index, Constant.EMPTY_ARGS);
    }

    /**
     * Set selectedIndex of &lt;option&gt; element.
     * 
     * When you set the selectedIndex property, the display of the select object updates immediately.
     * This method is most useful when used with select objects that support selecting only one item at a time.
     * Use {@link #setSelectedOptions(String, List, Object...)} method if the multiple attribute is specified for a &lt;select&gt; element.
     * 
     * @param selector css or xpath selector
     * @param index he index of the first selected &lt;option&gt; element.
     * 
     * @return this
     */
    default Session setSelectedIndex(
                            final String selector,
                            final int index,
                            final Object ...args) {
        if (index < -1) {
            return getThis();
        }
        if (index != -1) {
            getThis().logEntry("setSelectedIndex", format(selector, args) + "\", \"" + index);
        }
        String objectId = getThis().getObjectId(selector, args);
        if (objectId == null) {
            throw new ElementNotFoundException(format(selector, args));
        }
        CallFunctionOnResult result = getThis().getCommand().getRuntime().callFunctionOn(
                                                    format("function() { this.selectedIndex = %d }", index),
                                                    objectId,
                                                    null, null, null, null,
                                                    null, null, null, null);
        if (result != null && result.getResult() != null) {
            getThis().releaseObject(result.getResult().getObjectId());
        }
        getThis().releaseObject(objectId);
        return getThis();
    }

    /**
     * The list of options for a &lt;select&gt; element consists of all the option element children of the select element,
     * and all the &lt;option&gt; element children of all the &lt;optgroup&gt; element children of the &lt;select&gt; element.
     * 
     * @param selector css or xpath selector
     * 
     * @return list of HTML &lt;option&gt; elements (in document order).
     */
    default List<Option> getOptions(final String selector) {
        return getOptions(selector, Constant.EMPTY_ARGS);
    }

    /**
     * The list of options for a &lt;select&gt; element consists of all the option element children of the select element,
     * and all the &lt;option&gt; element children of all the &lt;optgroup&gt; element children of the &lt;select&gt; element.
     * 
     * @param selector css or xpath selector
     * @param args format string
     * 
     * @return list of HTML &lt;option&gt; elements (in document order).
     */
    default List<Option> getOptions(
                            final String selector,
                            final Object ...args) {
        String objectId = getThis().getObjectId(selector, args);
        if (objectId == null) {
            throw new ElementNotFoundException(format(selector, args));
        }
        PropertyDescriptor pd = getThis().getPropertyDescriptor(objectId, "options");
        if ( pd != null && pd.getValue() != null ) {
            Double length = (Double) getThis().getPropertyByObjectId(pd.getValue().getObjectId(), "length");
            List<Option> list = emptyList();
            if (length != null) {
                if (length.intValue() <= 0) {
                    getThis().releaseObject(objectId);
                } else {
                    CallFunctionOnResult result = getThis().getCommand().getRuntime().callFunctionOn(
                            "function() { let options = []; for (let i = 0; i < this.length; i++) " +
                            "{ options.push({ index : this[i].index, selected: this[i].selected, " +
                            "value: this[i].value, text: this[i].textContent, group: this[i].parentElement.tagName" +
                            "=== 'OPTGROUP' ? this[i].parentElement.getAttribute('label') : null }); } return JSON.stringify(options); }",
                            pd.getValue().getObjectId(),
                            null, null, null, null,
                            null, null, null, null);
                    if (result != null && result.getResult() != null) {
                        String json = (String) result.getResult().getValue();
                        getThis().releaseObject(result.getResult().getObjectId());
                        Gson gson = getThis().getGson();
                        list = gson.fromJson(json, TYPE_TOKEN.getType());
                    }
                }
            }
            getThis().releaseObject(pd.getValue().getObjectId());
            getThis().releaseObject(objectId);
            return list;
        }
        getThis().releaseObject(objectId);
        return emptyList();
    }

    /**
     * Clears any existing selected items of &lt;select&gt; element.
     * 
     * @param selector css or xpath selector
     * 
     * @return this
     */
    public default Session clearOptions(final String selector) {
        return clearOptions(selector, -1, Constant.EMPTY_ARGS);
    }

    /**
     * Clears any existing selected items of &lt;select&gt; element.
     * 
     * @param selector css or xpath selector
     * @args format string
     * 
     * @return this
     */
    public default Session clearOptions(final String selector, Object ...args) {
        getThis().logEntry("clearOptions", format(selector, args));
        return setSelectedIndex(selector, -1, args);
    }

    /**
     * Set selected indices of &lt;select&gt; element.
     * 
     * @param selector css or xpath selector
     * @param indexes indices of selected items.
     * 
     * @return this
     */
    public default Session setSelectedOptions(
                                final String selector,
                                final List<Integer> indexes) {
        return setSelectedOptions(selector, indexes, Constant.EMPTY_ARGS);
    }

    /**
     * Set selected indices of &lt;select&gt; element.
     * 
     * @param selector css or xpath selector
     * @param indexes indices of selected items.
     * @param args format string
     * 
     * @return this
     */
    public default Session setSelectedOptions(
                                final String selector,
                                final List<Integer> indexes,
                                final Object ...args) {
        if (indexes == null) {
            return getThis();
        }
        String objectId = getThis().getObjectId(selector, args);
        if (objectId == null) {
            throw new ElementNotFoundException(format(selector, args));
        }
        getThis().logEntry("setSelectOptions", format(selector, args) + "\", \"" + Arrays.toString(indexes.toArray(new Integer[] { })));
        PropertyDescriptor pd = getThis().getPropertyDescriptor(objectId, "options");
        if (pd != null) {
            if (pd.getValue() != null) {
                Double length = (Double) getThis().getPropertyByObjectId(pd.getValue().getObjectId(), "length");
                if (length != null) {
                    if (length.intValue() <= 0) {
                        getThis().releaseObject(objectId);
                    } else {
                        for (Integer index : indexes) {
                            if (index > length.intValue() || index < 0) {
                                getThis().error("invalid index value [{}]", index.intValue());
                                continue;
                            }
                            CallFunctionOnResult result = getThis().getCommand().getRuntime().callFunctionOn(
                                    format("function() { this[%d].selected = true }", index),
                                    pd.getValue().getObjectId(),
                                    null, null, null, null,
                                    null, null, null, null);
                            if (result != null && result.getResult() != null) {
                                getThis().releaseObject(result.getResult().getObjectId());
                            }
                        }
                    }
                }
                getThis().releaseObject(pd.getValue().getObjectId());
                getThis().releaseObject(objectId);
                return getThis();
            }
        }
        getThis().releaseObject(objectId);
        return getThis();
    }

    /**
     * Sets files for the given file input element.
     * 
     * @param selector css or xpath selector
     * @param files list of file paths to set
     * 
     * @return this
     */
    default Session setFiles(
                        final String selector,
                        final Path... files) {
        if (files == null || files.length == 0) {
            return getThis();
        }
        List<String> list = new ArrayList<>();
        for (Path file : files) {
            list.add(file.toString());
        }
        return setFiles(selector, list.toArray(new String[] { }));
    }

    /**
     * Sets files for the given file input element.
     * 
     * @param selector css or xpath selector
     * @param files list of file paths to set
     * 
     * @return this
     */
    default Session setFiles(
                        final String selector,
                        final String... files) {
        if (files == null || files.length == 0) {
            return getThis();
        }
        Integer nodeId = getThis().getNodeId(selector);
        if (nodeId == null || Constant.EMPTY_NODE_ID.equals(nodeId)) {
            throw new ElementNotFoundException(format(selector));
        }
        getThis().logEntry("setFiles", format(selector) + "\", \"" + Arrays.toString(files));
        DOM dom = getThis().getCommand().getDOM();
        dom.setFileInputFiles(asList(files), nodeId, null, null);
        return getThis();
    }

    /**
     * Indicates whether the &lt;input&gt; element is disabled or not.
     * 
     * @param selector css or xpath selector
     * 
     * @return <code>true</code> if &lt;input&gt; element is disabled
     */
    default boolean isDisabled(final String selector) {
        return isDisabled(selector, Constant.EMPTY_ARGS);
    }

    /**
     * Indicates whether the &lt;input&gt; element is disabled or not.
     * 
     * @param selector css or xpath selector
     * @param args format string
     * 
     * @return <code>true</code> if &lt;input&gt; element is disabled
     */
    default boolean isDisabled(
                        final String selector,
                        final Object ...args) {
        Boolean property = TRUE.equals(getThis().getProperty(selector, "disabled", args));
        getThis().logExit("isDisabled", format(selector, args), property.booleanValue());
        return property.booleanValue();
    }

    /**
     * Indicates whether the &lt;input&gt; element is checked or not.
     * 
     * This method is useful for only <strong>radio</strong> and <strong>checkbox</strong> element.
     * 
     * @param selector css or xpath selector
     * 
     * @return <code>true</code> if <strong>radio</strong> or <strong>checkbox</strong> is selected
     */
    default boolean isChecked(final String selector) {
        return isChecked(selector, Constant.EMPTY_ARGS);
    }

    /**
     * Indicates whether the &lt;input&gt; element is checked or not.
     * 
     * This method is useful for only <strong>radio</strong> and <strong>checkbox</strong> element.
     * 
     * @param selector css or xpath selector
     * @param args format string
     * 
     * @return <code>true</code> if <strong>radio</strong> or <strong>checkbox</strong> is selected
     */
    default boolean isChecked(
                        final String selector,
                        final Object ...args) {
        Boolean property = TRUE.equals(getThis().getProperty(selector, "checked", args));
        getThis().logExit("isChecked", format(selector, args), property.booleanValue());
        return property.booleanValue();
    }

    /**
     * Sets the value of the checked property.
     * 
     * type attribute must be set to <strong>checkbox</strong> or <strong>radio</strong> for this method to have any effect.
     * 
     * @param selector css or xpath selector
     * @param checked the new checked state
     * 
     * @return this
     */
    default Session setChecked(
                        final String selector,
                        final boolean checked) {
        return setChecked(selector, checked, Constant.EMPTY_ARGS);
    }

    /**
     * Sets the value of the checked property.
     * 
     * type attribute must be set to <strong>checkbox</strong> or <strong>radio</strong> for this method to have any effect.
     * 
     * @param selector css or xpath selector
     * @param checked the new checked state
     * @param args format string
     * 
     * @return this
     */
    default Session setChecked(
                        final String selector,
                        final boolean checked,
                        final Object ...args) {
        getThis().logEntry("setChecked", format(selector) + "\", \"" + checked);
        getThis().setProperty(selector, "checked", checked, args);
        return getThis();
    }

    /**
     * Sets the value of the disabled property.
     * 
     * @param selector css or xpath selector
     * @param disabled the new disabled state
     * 
     * @return this
     */
    default Session setDisabled(
                        final String selector,
                        final boolean disabled) {
        return setDisabled(selector, disabled, Constant.EMPTY_ARGS);
    }

    /**
     * Sets the value of the disabled property.
     * 
     * @param selector css or xpath selector
     * @param disabled the new disabled state
     * @param args format string
     * 
     * @return this
     */
    default Session setDisabled(
                        final String selector,
                        final boolean disabled,
                        final Object ...args) {
        getThis().logEntry("setDisabled", format(selector) + "\", \"" + disabled);
        getThis().setProperty(selector, "disabled", disabled, args);
        return getThis();
    }

    /**
     * Sets the value of the &lt;input&gt; input control.
     * 
     * @param selector css or xpath selector
     * @param value the new value
     * 
     * @return this
     */
    default Session setValue(
                        final String selector,
                        final Object value) {
        return setValue(selector, value, Constant.EMPTY_ARGS);
    }

    /**
     * Sets the value of the &lt;input&gt; control.
     * 
     * @param selector css or xpath selector
     * @param value the new value
     * @param args format string
     * 
     * @return this
     */
    default Session setValue(
                        final String selector,
                        final Object value,
                        final Object ...args) {
        return setAttribute(selector, "value", value, args);
    }

    /**
     * Gets the value of the &lt;input&gt; control.
     * 
     * @param selector css or xpath selector
     * 
     * @return value of &lt;input&gt; control
     */
    default String getValue(
                        final String selector) {
        return getValue(selector, Constant.EMPTY_ARGS);
    }

    /**
     * Gets the value of the &lt;input&gt; control.
     * 
     * @param selector css or xpath selector
     * @param args format string
     * 
     * @return value of &lt;input&gt; control
     */
    default String getValue(
                    final String selector,
                    final Object ...args) {
        String objectId = getThis().getObjectId(selector, args);
        if (objectId == null) {
            throw new ElementNotFoundException(format(selector, args));
        }
        String value = (String) getThis().getPropertyByObjectId(objectId, "value");
        getThis().releaseObject(objectId);
        if (value == null) {
            return null;
        }
        getThis().logExit("getValue", format(selector, args), value);
        return value;
    }

    /**
     * Gets attributes of the node or {@link Collections#emptyMap()} otherwise.
     * 
     * @param selector css or xpath selector
     * 
     * @return returns all attribute nodes registered to the specified node. 
     */
    default Map<String, String> getAttributes(final String selector) {
        return getAttributes(selector, Constant.EMPTY_ARGS);
    }

    /**
     * Gets attributes of the node or {@link Collections#emptyMap()} otherwise.
     * 
     * @param selector
     *            css or xpath selector
     * @param args
     *            format string
     * 
     * @return returns all attribute nodes registered to the specified node.
     */
    default Map<String, String> getAttributes(final String selector, final Object... args) {
        return getAttributes(null, selector, args);
    }

    /**
     * Gets attributes of the node or {@link Collections#emptyMap()} otherwise.
     * 
     * @param selector
     *            css or xpath selector
     * @param args
     *            format string
     * @param contextId
     *            Frame context id
     * @return returns all attribute nodes registered to the specified node.
     */
    default Map<String, String> getAttributes(
                                final Integer contextId,
                                final String selector,
                                final Object ...args) {
        Integer nodeId = getThis().getNodeId(contextId, selector, args);
        if (nodeId != null && nodeId.intValue() > 0) {
            DOM dom = getThis().getCommand().getDOM();
            List<String> attributes = dom.getAttributes(nodeId);
            if (attributes.isEmpty()) {
                return emptyMap();
            }
            Map<String, String> map = new LinkedHashMap<>(attributes.size() / 2);
            for (int i = 0; i < attributes.size(); i += 2) {
                String attrName  = attributes.get(i);
                String attrValue = attributes.get(i + 1);
                map.put(attrName, attrValue);
            }
            return map;
        }
        return emptyMap();
    }

    /**
     * Retrieves an attribute value by name.
     * 
     * @param selector css or xpath selector
     * @param name the name of the attribute to retrieve
     * 
     * @return the value of attribute or <code>null</code> if there is no such attribute.
     */
    default String getAttribute(final String selector, final String name) {
        return getAttribute(selector, name, Constant.EMPTY_ARGS);
    }

    /**
     * Retrieves an attribute value by name.
     * 
     * @param selector css or xpath selector
     * @param name the name of the attribute to retrieve
     * @param args format string
     * 
     * @return the value of attribute or <code>null</code> if there is no such attribute.
     */
    default String getAttribute(
            final String selector, final String name, final Object... args) {
        return getAttribute(null, selector, name, args);
    }

    /**
     * Retrieves an attribute value by name.
     * 
     * @param selector
     *            css or xpath selector
     * @param name
     *            the name of the attribute to retrieve
     * @param args
     *            format string
     * 
     * @return the value of attribute or <code>null</code> if there is no such
     *         attribute.
     */
    default String getAttribute(
                        final Integer contextId,
                        final String selector,
                        final String name,
                        final Object ...args) {
        if (name == null || name.trim().isEmpty()) {
            return null;
        }
        String value = getAttributes(contextId, format(selector, args)).get(name);
        getThis().logExit("getAttribute", format(selector, args) + "\", \"" + name, value);
        return value;
    }

    /**
     * Sets attribute for an element
     * 
     * @param selector css or xpath selector
     * @param name the name of the attribute to create or alter
     * @param value value to set in string form
     * @param args format string
     * 
     * @return this
     */
    default Session setAttribute(
                        final String selector,
                        final String name,
                        final Object value,
                        final Object ...args) {
        return setAttribute(null, selector, name, value, args);
    }

    /**
     * Sets attribute for an element
     * 
     * @param selector
     *            css or xpath selector
     * @param name
     *            the name of the attribute to create or alter
     * @param value
     *            value to set in string form
     * @param args
     *            format string
     * 
     * @return this
     */
    default Session setAttribute(final Integer contextId, final String selector, final String name, final Object value,
            final Object... args) {
        Integer nodeId = getThis().getNodeId(contextId, selector, args);
        if (nodeId == null || Constant.EMPTY_NODE_ID.equals(nodeId)) {
            throw new ElementNotFoundException(format(selector, args));
        }
        getThis().logEntry("setAttribute", format(selector) + "\", \"" + name + "\", \"" + value);
        getThis()
            .getCommand()
            .getDOM()
            .setAttributeValue(nodeId, name, value == null ? null : valueOf(value));
        return getThis();
    }

    /**
     * Sets attribute for an element
     * 
     * @param selector css or xpath selector
     * @param name the name of the attribute to create or alter
     * @param value value to set in string form
     * 
     * @return this
     */
    default Session setAttribute(
                        final String selector,
                        final String name,
                        final Object value) {
        return setAttribute(selector, name, value, Constant.EMPTY_ARGS);
    }
    /**
     * Gets box model of an element
     * 
     * Box model hold the height, width and coordinate of the element
     * 
     * @param selector css or xpath selector
     * @param args fromat string
     * 
     * @return Box model of element or <code>null</code> otherwise
     */
    default BoxModel getBoxModel(final String selector, final Object ...args) {
        return getBoxModel(null, selector, args);
    }

    /**
     * Gets box model of an element
     * 
     * Box model hold the height, width and coordinate of the element
     * 
     * @param selector
     *            css or xpath selector
     * @param args
     *            fromat string
     * 
     * @return Box model of element or <code>null</code> otherwise
     */
    default BoxModel getBoxModel(Integer contextId, final String selector, Object... args) {
        Integer nodeId = getThis().getNodeId(contextId, selector, args);
        if (nodeId == null || Constant.EMPTY_NODE_ID.equals(nodeId)) {
            throw new ElementNotFoundException(format(selector, args));
        }
        return getThis().getCommand().getDOM().getBoxModel(nodeId, null, null);
    }

    /**
     * Returns node's HTML markup.
     * 
     * @param selector css or xpath selector
     * 
     * @return Outer HTML markup.
     */
    default String getOuterHtml(String selector) {
        return getOuterHtml(selector, Constant.EMPTY_ARGS);
    }

    /**
     * Returns node's HTML markup.
     * 
     * @param selector css or xpath selector
     * @param args fromat string
     * 
     * @return Outer HTML markup.
     */
    default String getOuterHtml(String selector, Object... args) {
        Integer nodeId = getThis().getNodeId(null, selector, args);
        return getThis().getCommand().getDOM().getOuterHTML(nodeId, null, null);
    }

    /**
     * Scrolls the the given node into view if not already visible.
     */
    default void scrollIntoViewIfNeeded(String selector) {
        scrollIntoViewIfNeeded(selector, Constant.EMPTY_ARGS);
    }

    /**
     * Scrolls the the given node into view if not already visible.
     */
    default void scrollIntoViewIfNeeded(String selector, Object... args) {
        Integer contextId = getThis().getExecutionContextId();
        Integer nodeId = getThis().getNodeId(contextId, selector, args);
        getThis()
            .getCommand()
            .getDOM()
            .scrollIntoViewIfNeeded(nodeId, null, null, null);
    }

    default Point getClickablePoint(String selector) {
        return getClickablePoint(selector, Constant.EMPTY_ARGS);
    }

    default Point getClickablePoint(String selector, final Object... args) {
        DOM dom = getThis().getCommand().getDOM();
        Integer nodeId = getThis().getNodeId(format(selector, args));
        if (nodeId == null || Constant.EMPTY_NODE_ID.equals(nodeId)) {
            throw new ElementNotFoundException(format(selector, args));
        }
        // Compute sum of all directed areas of adjacent triangles
        // https://en.wikipedia.org/wiki/Polygon#Simple_polygons
        Function<List<Point>, Double> computeQuadArea = quad -> {
            Double area = 0D;
            for (int i = 0; i < quad.size(); ++i) {
                final Point p1 = quad.get(i);
                final Point p2 = quad.get((i + 1) % quad.size());
                area += (p1.x * p2.y - p2.x * p1.y) / 2;
            }
            return area;
        };
        List<List<Double>> quads = dom.getContentQuads(nodeId, null, null);
        // Filter out quads that have too small area to click into
        List<List<Point>> clickableQuads = quads.stream()
                                                .map(quad -> {
                                                    List<Point> list = new ArrayList<>();
                                                    list.add(new Point(quad.get(0), quad.get(1)));
                                                    list.add(new Point(quad.get(2), quad.get(3)));
                                                    list.add(new Point(quad.get(4), quad.get(5)));
                                                    list.add(new Point(quad.get(6), quad.get(7)));
                                                    return list;
                                                }).filter(t -> computeQuadArea.apply(t) > 1)
                                                .collect(toList());
        // Return the middle point of the first quad
        List<Point> quad = clickableQuads.get(0);
        Double x = 0D;
        Double y = 0D;
        for(Point next : quad) {
            x += next.x;
            y += next.y;
        }
        x = x / 4;
        y = y / 4;
        return new Point(x, y);
    }

    /**
     * Returns a document snapshot, including the full DOM tree of the root node (including iframes,
     * template contents, and imported documents).
     */
    public default String getDOMSnapshot() {
        // DOMSnapshot.getSnapshot() returns flatten list.
        // Convert the flatten list to tree and return the html string.
        DOMSnapshot snapshot = getThis().getCommand().getDOMSnapshot();
        GetSnapshotResult result = snapshot.getSnapshot(new ArrayList<String>(0));
        List<DOMNode> nodes = result.getDomNodes();
        final int size = nodes.size();
        int[] childParentIndexes = new int[size];
        for (int parentIndex = 0; parentIndex < size; parentIndex++) {
            DOMNode node = nodes.get(parentIndex);
            List<Integer> childNodeIndexes = node.getChildNodeIndexes();
            if ( childNodeIndexes != null ) {
                for (Integer childIndex : childNodeIndexes) {
                    childParentIndexes[childIndex] = parentIndex;
                }
            }
        }
        DOMNode root = nodes.get(0);
        int rootLength = root.getChildNodeIndexes() != null ? root.getChildNodeIndexes().size() : 0;
        TreeNode tree = new TreeNode(root, rootLength);
        for (int i = 1; i < size; i++) {
            int parentIndex = childParentIndexes[i];
            DOMNode node = nodes.get(i);
            List<Integer> childNodeIndexes = node.getChildNodeIndexes();
            int childLength = childNodeIndexes == null ? 0 : childNodeIndexes.size();
            if (parentIndex > 0) {
                DOMNode parent = nodes.get(parentIndex);
                if ( parent != null ) {
                    TreeNode parentNode = tree.find(parent);
                    if ( parentNode != null ) {
                        parentNode.add(new TreeNode(node, childLength));
                    }
                }
            } else {
                tree.add(new TreeNode(node, childLength));
            }
        }
        return tree.toString();
    }

    Session getThis();
}
