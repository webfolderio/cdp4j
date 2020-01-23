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
    public void navigate(String url) {
        if (session == null) {
            return;
        }
        session.navigate(url);
    }

    @Override
    public void waitDocumentReady() {
        if (session == null) {
            return;
        }
        session.waitDocumentReady();
    }

    @Override
    public void waitDocumentReady(int timeout) {
        if (session == null) {
            return;
        }
        session.waitDocumentReady(timeout);
    }

    @Override
    public void click(String selector) {
        session.click(selector);
    }

    @Override
    public void wait(int timeout) {
        if (session == null) {
            return;
        }
        session.wait(timeout);
    }

    @Override
    public String getText(String selector) {
        if (session == null) {
            return null;
        }
        return session.getText(selector);
    }

    @Override
    public String getDOMSnapshot() {
        if (session == null) {
            return null;
        }
        return session.getDOMSnapshot();
    }

    @Override
    public void activate() {
        if (session == null) {
            return;
        }
        session.activate();
    }

    @Override
    public String getStringProperty(String selector, String propertyName) {
        if (session == null) {
            return null;
        }
        return String.valueOf(session.getProperty(selector, propertyName));
    }

    // ------------------------------------------------------------------------
    // Selector
    // ------------------------------------------------------------------------

    @Override
    public boolean matches(String selector) {
        if (session == null) {
            return false;
        }
        try {
            return session.matches(selector);
        } catch (Throwable t) {
            // never throw Exception
            return false;
        }
    }

    // ------------------------------------------------------------------------
    // Keyboard
    // ------------------------------------------------------------------------

    @Override
    public void sendKeys(String text) {
        if (session == null || text == null) {
            return;
        }
        session.sendKeys(text);
    }

    @Override
    public void sendTab() {
        if (session == null) {
            return;
        }
        session.sendTab();
    }

    @Override
    public void sendEnter() {
        if (session == null) {
            return;
        }
        session.sendEnter();
    }

    @Override
    public void sendBackspace() {
        if (session == null) {
            return;
        }
        session.sendBackspace();
    }

    @Override
    public void sendLeftArrow() {
        if (session == null) {
            return;
        }
        session.sendLeftArrow();
    }

    @Override
    public void sendUpArrow() {
        if (session == null) {
            return;
        }
        session.sendUpArrow();
    }

    @Override
    public void sendRightArrow() {
        if (session == null) {
            return;
        }
        session.sendRightArrow();
    }

    @Override
    public void sendDownArrow() {
        if (session == null) {
            return;
        }
        session.sendDownArrow();
    }

    @Override
    public void sendEsc() {
        if (session == null) {
            return;
        }
        session.sendEsc();
    }

    @Override
    public void sendKeyCode(int keyCode) {
        if (session == null) {
            return;
        }
        session.sendKeyCode(keyCode);
    }
}
