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
import io.webfolder.cdp.session.SessionFactory;

public class JsSessionFactory implements ISessionFactory {

    private final SessionFactory sessionFactory;

    public JsSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public ISession create() {
        Session session = sessionFactory.create();
        if (session == null) {
            return null;
        }
        ISession jsSession = create(session);
        return jsSession;
    }

    @Override
    public ISession createPrivate() {
        String context = sessionFactory.createBrowserContext();
        if (context == null) {
            return null;
        }
        Session session = sessionFactory.create(context);
        if (session == null) {
            return null;
        }
        ISession jsSession = create(session);
        return jsSession;
    }

    protected ISession create(Session session) {
        return new JsSession(session);
    }
}
