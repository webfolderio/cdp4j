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

import static io.webfolder.cdp.ConnectionMode.Pipe;
import static java.lang.Boolean.TRUE;

import com.koushikdutta.quack.JavaScriptObject;
import com.koushikdutta.quack.QuackObject;

import io.webfolder.cdp.Launcher;
import io.webfolder.cdp.Options;
import io.webfolder.cdp.Options.Builder;
import io.webfolder.cdp.session.SessionFactory;

public class JsLauncher implements QuackObject, ILauncher {
    
    private final JsHelper helper = new JsHelper();

    private Launcher launcher;

    private JsSessionFactory jsSessionFactory;

    @Override
    public Object construct(Object... args) {
        Builder options = Options.builder();
        options.connectionMode(Pipe);
        boolean headless = false;
        if ( args != null && args.length == 1 ) {
            JavaScriptObject arg = (JavaScriptObject) args[0];
            headless = TRUE.equals(helper.getBoolean(arg, "headless"));
            options.headless(headless);
        }
        launcher = new Launcher(options.build());
        return this;
    }

    @Override
    public ISessionFactory launch() {
        if ( jsSessionFactory != null ) {
            return jsSessionFactory;
        }
        if ( launcher != null ) {
            SessionFactory sessionFactory = launcher.launch();
            if ( sessionFactory != null ) {
                jsSessionFactory = new JsSessionFactory(sessionFactory);
            }
        }
        return jsSessionFactory;
    }

    @Override
    public boolean kill() {
        if ( launcher != null ) {
            return launcher.kill();
        }
        return false;
    }
}
