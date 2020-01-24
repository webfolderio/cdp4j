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

public interface ISession {

    // ------------------------------------------------------------------------
    // Session
    // ------------------------------------------------------------------------

    JsReturnValue navigate(String url);

    JsReturnValue waitDocumentReady();

    JsReturnValue waitDocumentReady(int timeout);

    JsReturnValue click(String selector);

    JsReturnValue wait(int timeout);

    JsReturnValue getDOMSnapshot();

    JsReturnValue getText(String selector);

    JsReturnValue activate();

    JsReturnValue getStringProperty(String selector, String propertyName);

    JsReturnValue enableConsoleLog();

    JsReturnValue enableDetailLog();

    JsReturnValue enableNetworkLog();

    // ------------------------------------------------------------------------
    // Selector
    // ------------------------------------------------------------------------

    JsReturnValue matches(String selector);

    // ------------------------------------------------------------------------
    // Keyboard
    // ------------------------------------------------------------------------

    JsReturnValue sendKeys(String text);

    JsReturnValue sendTab();

    JsReturnValue sendEnter();

    JsReturnValue sendBackspace();

    JsReturnValue sendLeftArrow();

    JsReturnValue sendUpArrow();

    JsReturnValue sendRightArrow();

    JsReturnValue sendDownArrow();

    JsReturnValue sendEsc();

    JsReturnValue sendKeyCode(int keyCode);
}
