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
package io.webfolder.cdp.sample;

import static io.webfolder.cdp.session.WaitUntil.DomContentLoad;
import static java.nio.file.Paths.get;
import static java.util.Arrays.asList;

import java.util.List;

import io.webfolder.cdp.JsFunction;
import io.webfolder.cdp.Launcher;
import io.webfolder.cdp.session.Session;
import io.webfolder.cdp.session.SessionFactory;

public class InvokeJsFromJava {

    public static interface MyJsFunction {

        @JsFunction("let attributes = []; document.querySelectorAll(selector).forEach(e => { attributes.push(e.getAttribute(attributeName)); }); return attributes;")
        List<String> listAttributes(String selector, String attributeName);

        @JsFunction("")
        void dummy();

        @JsFunction("console.error(message);")
        void consoleError(String message);

        @JsFunction("return a + b")
        int sum(int a, int b);

        @JsFunction("return str1 + str2")
        String concat(String str1, String str2);

        @JsFunction("let list = []; numbers.forEach(i => list.push(i + inc)); return list;")
        List<Double> increment(List<Integer> numbers, int inc);
    }

    public static void main(String[] args) {
        Launcher launcher = new Launcher();

        String uri = get("src/test/resources/js-function-test.html").toAbsolutePath().toUri().toString();
        
        try (SessionFactory factory = launcher.launch(); Session session = factory.create()) {
            // Important!
            // Register the JsFunction before the navigate method
            session.registerJsFunction(MyJsFunction.class);
            session.enableConsoleLog();
            session.navigateAndWait(uri, DomContentLoad);
            MyJsFunction utility = session.getJsFunction(MyJsFunction.class);
            List<String> attributes = utility.listAttributes("img", "src");
            System.out.println(attributes);

            utility.dummy();
            utility.consoleError("panic!");

            System.out.println(utility.sum(2, 2));

            System.out.println(utility.concat("foo", "bar"));

            List<Double> list = utility.increment(asList(0, 1, 2, 3), 1);
            System.out.println(list);
            session.wait(500);
        } finally {
            launcher.kill();
        }
    }
}
