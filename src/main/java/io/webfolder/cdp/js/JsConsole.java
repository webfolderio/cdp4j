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

import static java.lang.String.valueOf;

import java.io.PrintStream;
import java.util.StringJoiner;

import com.koushikdutta.quack.JavaScriptObject;

public class JsConsole implements IConsole {

    private final JsHelper helper = new JsHelper();

    private final PrintStream stdout;

    private final PrintStream stderr;

    public JsConsole(PrintStream stdout, PrintStream stderr) {
        this.stdout = stdout;
        this.stderr = stderr;
    }

    @Override
    public void info(Object... messages) {
        print(stdout, messages);
    }

    @Override
    public void error(Object... messages) {
        print(stderr, messages);
    }

    public void log(Object... messages) {
        print(stdout, messages);
    }

    public void warn(Object... messages) {
        print(stdout, messages);
    }

    protected void print(PrintStream ps, Object... messages) {
        StringJoiner joiner = new StringJoiner(" ");
        for (Object message : messages) {
            if (message instanceof JavaScriptObject) {
                JavaScriptObject jso = (JavaScriptObject) message;
                boolean hasConstructor = jso.has("constructor");
                if (hasConstructor) {
                    JavaScriptObject constructor = (JavaScriptObject) jso.get("constructor");
                    if ( constructor != null ) {
                        String constructorName = String.valueOf(constructor.get("name"));
                        boolean isJsError = "Error".equals(constructorName);
                        if (isJsError) {
                            String name = helper.getString(jso, "name");
                            String msg = helper.getString(jso, "message");
                            joiner.add(name + " " + msg);
                        }
                    }
                }
                joiner.add(jso.stringify());
            } else {
                joiner.add(message instanceof String ? (String) message : valueOf(message));
            }
        }
        String msg = joiner.toString();
        ps.println(msg);
    }
}
