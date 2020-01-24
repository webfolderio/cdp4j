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
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

import io.webfolder.cdp.session.Session;

public class JsSession implements ISession {

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
    public JsReturnValue click(String selector) {
        try {
            session.click(selector);
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
    public JsReturnValue getText(String selector) {
        try {
            String val = session.getText(selector);
            return success(val);
        } catch (Throwable t) {
            return fail(t);
        }
    }

    @Override
    public JsReturnValue getDOMSnapshot() {
        try {
            String val = session.getDOMSnapshot();
            return success(val);
        } catch (Throwable t) {
            return fail(t);
        }
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
            String val = String.valueOf(session.getProperty(selector, propertyName));
            return success(val);
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
    // Selector
    // ------------------------------------------------------------------------

    @Override
    public JsReturnValue matches(String selector) {
        try {
            boolean val = session.matches(selector);
            return success(val == true ? TRUE : FALSE);
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
}
