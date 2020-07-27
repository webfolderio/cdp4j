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

import com.koushikdutta.quack.JavaScriptObject;
import com.koushikdutta.quack.QuackJsonObject;

public interface ISession {

    // ------------------------------------------------------------------------
    // Session
    // ------------------------------------------------------------------------

    void navigate(String url);

    void waitDocumentReady();

    void waitDocumentReady(int timeout);

    void wait(int timeout);

    void activate();

    String getProperty(String selector, String propertyName);

    void enableConsoleLog();

    void enableDetailLog();

    void enableNetworkLog();

    // ------------------------------------------------------------------------
    // Mouse
    // ------------------------------------------------------------------------

    void click(String selector);

    void move(double x, double y);

    // ------------------------------------------------------------------------
    // Navigator
    // ------------------------------------------------------------------------

    void stop();

    void back();

    void forward();

    void reload();

    void setUserAgent(String userAgent);

    String getLocation();

    String getPathname();

    QuackJsonObject getQueryString();

    String getContent();

    String getTitle();

    boolean isDomReady();

    void clearCache();

    void clearCookies();

    // ------------------------------------------------------------------------
    // Selector
    // ------------------------------------------------------------------------

    boolean matches(String selector);

    // ------------------------------------------------------------------------
    // Keyboard
    // ------------------------------------------------------------------------

    void sendKeys(String text);

    void sendTab();

    void sendEnter();

    void sendBackspace();

    void sendLeftArrow();

    void sendUpArrow();

    void sendRightArrow();

    void sendDownArrow();

    void sendEsc();

    void sendKeyCode(int keyCode);

    // ------------------------------------------------------------------------
    // Dom
    // ------------------------------------------------------------------------

    String getText(String selector);

    String getDOMSnapshot();

    void selectInputText(String selector);

    void focus(String selector);

    int getSelectedIndex(String selector);

    void setSelectedIndex(String selector, int index);

    QuackJsonObject getOptions(String selector);

    void clearOptions(String selector);

    void setSelectedOptions(String selector, JavaScriptObject jsObjectIndexes);

    void setFiles(String selector, JavaScriptObject jsoFiles);

    boolean isDisabled(String selector);

    boolean isChecked(String selector);

    void setChecked(String selector, boolean checked);

    void setDisabled(String selector, boolean disabled);

    void setValue(String selector, Object value);

    String getValue(String selector);

    QuackJsonObject getAttributes(String selector);

    String getAttribute(String selector, String name);

    void setAttribute(String selector, String name, Object value);

    QuackJsonObject getBoxModel(String selector);

    String getOuterHtml(String selector);

    void scrollIntoViewIfNeeded(String selector);

    QuackJsonObject getClickablePoint(String selector);
}
