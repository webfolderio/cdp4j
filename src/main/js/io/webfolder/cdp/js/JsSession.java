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
package io.webfolder.cdp.js;

import static io.webfolder.cdp.js.JsReturnValue.fail;
import static io.webfolder.cdp.js.JsReturnValue.success;

import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.koushikdutta.quack.JavaScriptObject;
import com.koushikdutta.quack.QuackJsonObject;

import io.webfolder.cdp.session.Option;
import io.webfolder.cdp.session.Session;
import io.webfolder.cdp.type.dom.BoxModel;
import io.webfolder.cdp.type.util.Point;

public class JsSession implements ISession {

    private static final JsHelper helper = new JsHelper();

    private static TypeToken<Map<String, String>> TYPE_TOKEN_MAP_STR = new TypeToken<Map<String, String>>() { };

    private final Session session;

    public JsSession(Session session) {
        this.session = session;
    }

    // ------------------------------------------------------------------------
    // Session
    // ------------------------------------------------------------------------

    @Override
    public JsReturnValue navigate(String url) {
        try {
            session.navigate(url);
        } catch (Throwable t) {
            return fail(t);
        }
        return success();
    }

    @Override
    public JsReturnValue waitDocumentReady() {
        try {
            session.waitDocumentReady();
        } catch (Throwable t) {
            return fail(t);
        }
        return success();
    }

    @Override
    public JsReturnValue waitDocumentReady(int timeout) {
        try {
            session.waitDocumentReady(timeout);
        } catch (Throwable t) {
            return fail(t);
        }
        return success();
    }

    @Override
    public JsReturnValue wait(int timeout) {
        try {
            session.wait(timeout);
        } catch (Throwable t) {
            return fail(t);
        }
        return success();
    }

    @Override
    public JsReturnValue activate() {
        try {
            session.activate();
            return success();
        } catch (Throwable t) {
            return fail(t);
        }
    }

    @Override
    public JsReturnValue getStringProperty(String selector, String propertyName) {
        try {
            String value = String.valueOf(session.getProperty(selector, propertyName));
            return success(value);
        } catch (Throwable t) {
            return fail(t);
        }
    }


    @Override
    public JsReturnValue enableConsoleLog() {
        try {
            session.enableConsoleLog();
            return success();
        } catch (Throwable t) {
            return fail(t);
        }
    }

    @Override
    public JsReturnValue enableDetailLog() {
        try {
            session.enableDetailLog();
            return success();
        } catch (Throwable t) {
            return fail(t);
        }
    }

    @Override
    public JsReturnValue enableNetworkLog() {
        try {
            session.enableNetworkLog();
            return success();
        } catch (Throwable t) {
            return fail(t);
        }
    }

    // ------------------------------------------------------------------------
    // Mouse
    // ------------------------------------------------------------------------

    @Override
    public JsReturnValue click(String selector) {
        try {
            session.click(selector);
        } catch (Throwable t) {
            return fail(t);
        }
        return success();
    }

    @Override
    public JsReturnValue move(double x, double y) {
        try {
            session.move(x, y);
        } catch (Throwable t) {
            return fail(t);
        }
        return success();
    }

    // ------------------------------------------------------------------------
    // Navigator
    // ------------------------------------------------------------------------

    @Override
    public JsReturnValue stop() {
        try {
            session.stop();
        } catch (Throwable t) {
            return fail(t);
        }
        return success();
    }

    @Override
    public JsReturnValue back() {
        try {
            session.back();
        } catch (Throwable t) {
            return fail(t);
        }
        return success();
    }

    @Override
    public JsReturnValue forward() {
        try {
            session.forward();
        } catch (Throwable t) {
            return fail(t);
        }
        return success();
    }

    @Override
    public JsReturnValue reload() {
        try {
            session.reload();
        } catch (Throwable t) {
            return fail(t);
        }
        return success();
    }

    @Override
    public JsReturnValue setUserAgent(String userAgent) {
        try {
            session.setUserAgent(userAgent);
        } catch (Throwable t) {
            return fail(t);
        }
        return success();
    }

    @Override
    public JsReturnValue getLocation() {
        try {
            String value = session.getLocation();
            return success(value);
        } catch (Throwable t) {
            return fail(t);
        }
    }

    @Override
    public JsReturnValue getPathname() {
        try {
            String value = session.getPathname();
            return success(value);
        } catch (Throwable t) {
            return fail(t);
        }
    }

    @Override
    public JsReturnValue getQueryString() {
        try {
            String json = (String) session.evaluate("JSON.stringify(Array.from(new URLSearchParams(document.location.search)))");
            return success(json == null || json.trim().isEmpty() ?
                                    new QuackJsonObject("{}") :
                                    new QuackJsonObject(json));
        } catch (Throwable t) {
            return fail(t);
        }
    }

    @Override
    public JsReturnValue getContent() {
        try {
            String value = session.getContent();
            return success(value);
        } catch (Throwable t) {
            return fail(t);
        }
    }

    @Override
    public JsReturnValue getTitle() {
        try {
            String value = session.getTitle();
            return success(value);
        } catch (Throwable t) {
            return fail(t);
        }
    }

    @Override
    public JsReturnValue isDomReady() {
        try {
            boolean value = session.isDomReady();
            return success(value);
        } catch (Throwable t) {
            return fail(t);
        }
    }

    @Override
    public JsReturnValue clearCache() {
        try {
            clearCache();
            return success();
        } catch (Throwable t) {
            return fail(t);
        }
    }

    @Override
    public JsReturnValue clearCookies() {
        try {
            clearCookies();
            return success();
        } catch (Throwable t) {
            return fail(t);
        }
    }

    // ------------------------------------------------------------------------
    // Selector
    // ------------------------------------------------------------------------

    @Override
    public JsReturnValue matches(String selector) {
        try {
            boolean value = session.matches(selector);
            return success(value);
        } catch (Throwable t) {
            return fail(t);
        }
    }

    // ------------------------------------------------------------------------
    // Keyboard
    // ------------------------------------------------------------------------

    @Override
    public JsReturnValue sendKeys(String text) {
        try {
            session.sendKeys(text);
            return success();
        } catch (Throwable t) {
            return fail(t);
        }
    }

    @Override
    public JsReturnValue sendTab() {
        try {
            session.sendTab();
            return success();
        } catch (Throwable t) {
            return fail(t);
        }
    }

    @Override
    public JsReturnValue sendEnter() {
        try {
            session.sendEnter();
            return success();
        } catch (Throwable t) {
            return fail(t);
        }
    }

    @Override
    public JsReturnValue sendBackspace() {
        try {
            session.sendBackspace();
            return success();
        } catch (Throwable t) {
            return fail(t);
        }
    }

    @Override
    public JsReturnValue sendLeftArrow() {
        try {
            session.sendLeftArrow();
            return success();
        } catch (Throwable t) {
            return fail(t);
        }
    }

    @Override
    public JsReturnValue sendUpArrow() {
        try {
            session.sendUpArrow();
            return success();
        } catch (Throwable t) {
            return fail(t);
        }
    }

    @Override
    public JsReturnValue sendRightArrow() {
        try {
            session.sendRightArrow();
            return success();
        } catch (Throwable t) {
            return fail(t);
        }
    }

    @Override
    public JsReturnValue sendDownArrow() {
        try {
            session.sendDownArrow();
            return success();
        } catch (Throwable t) {
            return fail(t);
        }
    }

    @Override
    public JsReturnValue sendEsc() {
        try {
            session.sendEsc();
            return success();
        } catch (Throwable t) {
            return fail(t);
        }
    }

    @Override
    public JsReturnValue sendKeyCode(int keyCode) {
        try {
            session.sendKeyCode(keyCode);
            return success();
        } catch (Throwable t) {
            return fail(t);
        }
    }

    // ------------------------------------------------------------------------
    // Dom
    // ------------------------------------------------------------------------

    @Override
    public JsReturnValue getText(String selector) {
        try {
            String value = session.getText(selector);
            return success(value);
        } catch (Throwable t) {
            return fail(t);
        }
    }

    @Override
    public JsReturnValue getDOMSnapshot() {
        try {
            String value = session.getDOMSnapshot();
            return success(value);
        } catch (Throwable t) {
            return fail(t);
        }
    }

    @Override
    public JsReturnValue selectInputText(String selector) {
        try {
            session.selectInputText(selector);
            return success();
        } catch (Throwable t) {
            return fail(t);
        }
    }

    @Override
    public JsReturnValue focus(String selector) {
        try {
            focus(selector);
            return success();
        } catch (Throwable t) {
            return fail(t);
        }
    }

    @Override
    public JsReturnValue getSelectedIndex(String selector) {
        try {
            int value = session.getSelectedIndex(selector);
            return success(value);
        } catch (Throwable t) {
            return fail(t);
        }
    }

    @Override
    public JsReturnValue setSelectedIndex(String selector, int index) {
        try {
            session.setSelectedIndex(selector, index);
            return success();
        } catch (Throwable t) {
            return fail(t);
        }
    }

    @Override
    public JsReturnValue getOptions(String selector) {
        try {
            List<Option> value = session.getOptions(selector);
            Gson gson = session.getGson();
            String json = gson.toJson(value, Option.TYPE_TOKEN.getType());
            return success(new QuackJsonObject(json));
        } catch (Throwable t) {
            return fail(t);
        }
    }

    @Override
    public JsReturnValue clearOptions(String selector) {
        try {
            session.clearOptions(selector);
            return success();
        } catch (Throwable t) {
            return fail(t);
        }
    }

    @Override
    public JsReturnValue setSelectedOptions(String selector, JavaScriptObject jsoIndexes) {
        try {
            List<Integer> indexes = helper.toIntegerList(jsoIndexes);
            session.setSelectedOptions(selector, indexes);
            return success();
        } catch (Throwable t) {
            return fail(t);
        }
    }

    @Override
    public JsReturnValue setFiles(String selector, JavaScriptObject jsoFiles) {
        try {
            List<String> files = helper.toStringList(jsoFiles);
            session.setFiles(selector, files.toArray(new String[] { }));
            return success();
        } catch (Throwable t) {
            return fail(t);
        }
    }

    @Override
    public JsReturnValue isDisabled(String selector) {
        try {
            boolean value = session.isDisabled(selector);
            return success(value);
        } catch (Throwable t) {
            return fail(t);
        }
    }

    @Override
    public JsReturnValue isChecked(String selector) {
        try {
            boolean value = session.isChecked(selector);
            return success(value);
        } catch (Throwable t) {
            return fail(t);
        }
    }

    @Override
    public JsReturnValue setChecked(String selector, boolean checked) {
        try {
            session.setChecked(selector, checked);
            return success(checked);
        } catch (Throwable t) {
            return fail(t);
        }
    }

    @Override
    public JsReturnValue setDisabled(String selector, boolean disabled) {
        try {
            session.setChecked(selector, disabled);
            return success();
        } catch (Throwable t) {
            return fail(t);
        }
    }

    @Override
    public JsReturnValue setValue(String selector, Object value) {
        try {
            session.setValue(selector, value);
            return success();
        } catch (Throwable t) {
            return fail(t);
        }
    }

    @Override
    public JsReturnValue getValue(String selector) {
        try {
            String value = session.getValue(selector);
            return success(value);
        } catch (Throwable t) {
            return fail(t);
        }
    }

    @Override
    public JsReturnValue getAttributes(String selector) {
        try {
            Map<String, String> value = session.getAttributes(selector);
            Gson gson = session.getGson();
            String json = gson.toJson(value, TYPE_TOKEN_MAP_STR.getType());
            return success(new QuackJsonObject(json));
        } catch (Throwable t) {
            return fail(t);
        }
    }

    @Override
    public JsReturnValue getAttribute(String selector, String name) {
        try {
            String value = session.getAttribute(selector, name);
            return success(value);
        } catch (Throwable t) {
            return fail(t);
        }
    }

    @Override
    public JsReturnValue setAttribute(String selector, String name, Object value) {
        try {
            session.setAttribute(selector, name, value);
            return success();
        } catch (Throwable t) {
            return fail(t);
        }
    }

    @Override
    public JsReturnValue getBoxModel(String selector) {
        try {
            BoxModel value = session.getBoxModel(selector);
            Gson gson = session.getGson();
            String json = gson.toJson(value);
            return success(new QuackJsonObject(json));
        } catch (Throwable t) {
            return fail(t);
        }
    }

    @Override
    public JsReturnValue getOuterHtml(String selector) {
        try {
            String value = session.getOuterHtml(selector);
            return success(value);
        } catch (Throwable t) {
            return fail(t);
        }
    }

    @Override
    public JsReturnValue scrollIntoViewIfNeeded(String selector) {
        try {
            session.scrollIntoViewIfNeeded(selector);
            return success();
        } catch (Throwable t) {
            return fail(t);
        }
    }

    @Override
    public JsReturnValue getClickablePoint(String selector) {
        try {
            Point value = session.getClickablePoint(selector);
            Gson gson = session.getGson();
            String json = gson.toJson(value);
            return success(new QuackJsonObject(json));
        } catch (Throwable t) {
            return fail(t);
        }
    }
}
