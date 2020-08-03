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
    public void navigate(String url) {
        session.navigate(url);
    }

    @Override
    public void waitDocumentReady() {
        session.waitDocumentReady();
    }

    @Override
    public void waitDocumentReady(int timeout) {
        session.waitDocumentReady(timeout);
    }

    @Override
    public void wait(int timeout) {
        session.wait(timeout);
    }

    @Override
    public void activate() {
        session.activate();
    }

    @Override
    public String getProperty(String selector, String propertyName) {
        Object value = session.getProperty(selector, propertyName);
        return value == null ? null : String.valueOf(value);
    }


    @Override
    public void enableConsoleLog() {
        session.enableConsoleLog();
    }

    @Override
    public void enableDetailLog() {
        session.enableDetailLog();
    }

    @Override
    public void enableNetworkLog() {
        session.enableNetworkLog();
    }

    // ------------------------------------------------------------------------
    // Mouse
    // ------------------------------------------------------------------------

    @Override
    public void click(String selector) {
        session.click(selector);
    }

    @Override
    public void move(double x, double y) {
        session.move(x, y);
    }

    // ------------------------------------------------------------------------
    // Navigator
    // ------------------------------------------------------------------------

    @Override
    public void stop() {
        session.stop();
    }

    @Override
    public void back() {
        session.back();
    }

    @Override
    public void forward() {
        session.forward();
    }

    @Override
    public void reload() {
        session.reload();
    }

    @Override
    public void setUserAgent(String userAgent) {
        session.setUserAgent(userAgent);
    }

    @Override
    public String getLocation() {
        return session.getLocation();
    }

    @Override
    public String getPathname() {
        return session.getPathname();
    }

    @Override
    public QuackJsonObject getQueryString() {
        String json = (String) session.evaluate("JSON.stringify(Array.from(new URLearchParams(document.location.search)))");
        return json == null || json.trim().isEmpty() ?
                                new QuackJsonObject("{}") :
                                new QuackJsonObject(json);

    }

    @Override
    public String getContent() {
        return session.getContent();
    }

    @Override
    public String getTitle() {
        return session.getTitle();
    }

    @Override
    public boolean isDomReady() {
        return session.isDomReady();
    }

    @Override
    public void clearCache() {
        session.clearCache();
    }

    @Override
    public void clearCookies() {
        session.clearCookies();
    }

    // ------------------------------------------------------------------------
    // Selector
    // ------------------------------------------------------------------------

    @Override
    public boolean matches(String selector) {
        return session.matches(selector);
    }

    // ------------------------------------------------------------------------
    // Keyboard
    // ------------------------------------------------------------------------

    @Override
    public void sendKeys(String text) {
        session.sendKeys(text);
    }

    @Override
    public void sendTab() {
        session.sendTab();
    }

    @Override
    public void sendEnter() {
        session.sendEnter();
    }

    @Override
    public void sendBackspace() {
        session.sendBackspace();
    }

    @Override
    public void sendLeftArrow() {
        session.sendLeftArrow();
    }

    @Override
    public void sendUpArrow() {
        session.sendUpArrow();
    }

    @Override
    public void sendRightArrow() {
        session.sendRightArrow();
    }

    @Override
    public void sendDownArrow() {
        session.sendDownArrow();
    }

    @Override
    public void sendEsc() {
        session.sendEsc();
    }

    @Override
    public void sendKeyCode(int keyCode) {
        session.sendKeyCode(keyCode);
    }

    // ------------------------------------------------------------------------
    // Dom
    // ------------------------------------------------------------------------

    @Override
    public String getText(String selector) {
        return session.getText(selector);
    }

    @Override
    public String getDOMSnapshot() {
        return session.getDOMSnapshot();
    }

    @Override
    public void selectInputText(String selector) {
        session.selectInputText(selector);
    }

    @Override
    public void focus(String selector) {
        focus(selector);
    }

    @Override
    public int getSelectedIndex(String selector) {
        return session.getSelectedIndex(selector);
    }

    @Override
    public void setSelectedIndex(String selector, int index) {
        session.setSelectedIndex(selector, index);
    }

    @Override
    public QuackJsonObject getOptions(String selector) {
        List<Option> value = session.getOptions(selector);
        Gson gson = session.getGson();
        String json = gson.toJson(value, Option.TYPE_TOKEN.getType());
        return new QuackJsonObject(json);
    }

    @Override
    public void clearOptions(String selector) {
        session.clearOptions(selector);
    }

    @Override
    public void setSelectedOptions(String selector, JavaScriptObject jsoIndexes) {
        List<Integer> indexes = helper.toIntegerList(jsoIndexes);
        session.setSelectedOptions(selector, indexes);
    }

    @Override
    public void setFiles(String selector, JavaScriptObject jsoFiles) {
        List<String> files = helper.toStringList(jsoFiles);
        session.setFiles(selector, files.toArray(new String[] { }));
    }

    @Override
    public boolean isDisabled(String selector) {
        return session.isDisabled(selector);
    }

    @Override
    public boolean isChecked(String selector) {
        return session.isChecked(selector);
    }

    @Override
    public void setChecked(String selector, boolean checked) {
        session.setChecked(selector, checked);
    }

    @Override
    public void setDisabled(String selector, boolean disabled) {
        session.setChecked(selector, disabled);
    }

    @Override
    public void setValue(String selector, Object value) {
        session.setValue(selector, value);
    }

    @Override
    public String getValue(String selector) {
        return session.getValue(selector);
    }

    @Override
    public QuackJsonObject getAttributes(String selector) {
        Map<String, String> value = session.getAttributes(selector);
        Gson gson = session.getGson();
        String json = gson.toJson(value, TYPE_TOKEN_MAP_STR.getType());
        return new QuackJsonObject(json);
    }

    @Override
    public String getAttribute(String selector, String name) {
        return session.getAttribute(selector, name);
    }

    @Override
    public void setAttribute(String selector, String name, Object value) {
        session.setAttribute(selector, name, value);
    }

    @Override
    public QuackJsonObject getBoxModel(String selector) {
        BoxModel value = session.getBoxModel(selector);
        Gson gson = session.getGson();
        String json = gson.toJson(value);
        return new QuackJsonObject(json);
    }

    @Override
    public String getOuterHtml(String selector) {
        return session.getOuterHtml(selector);
    }

    @Override
    public void scrollIntoViewIfNeeded(String selector) {
        session.scrollIntoViewIfNeeded(selector);
    }

    @Override
    public QuackJsonObject getClickablePoint(String selector) {
        Point value = session.getClickablePoint(selector);
        Gson gson = session.getGson();
        String json = gson.toJson(value);
        return new QuackJsonObject(json);
    }

    @Override
    public Session getCdpSession() {
        return session;
    }
}
