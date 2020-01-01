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
package io.webfolder.cdp.test;

import static io.webfolder.cdp.session.WaitUntil.DomContentLoad;
import static java.lang.Double.valueOf;
import static java.nio.file.Paths.get;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import io.webfolder.cdp.Launcher;
import io.webfolder.cdp.Options;
import io.webfolder.cdp.exception.CdpReadTimeoutException;
import io.webfolder.cdp.session.Session;
import io.webfolder.cdp.session.SessionFactory;

public class JsFunctionTest {

    @Test
    public void test() throws Exception {
        String uri = get("src/test/resources/js-function-test.html").toAbsolutePath().toUri().toString();

        Launcher launcher = new Launcher(Options.builder().headless(true).build());

        try (SessionFactory factory = launcher.launch(); Session session = factory.create()) {
            // Important!
            // Register the JsFunction before the navigate method
            session.registerJsFunction(MyJsFunction.class);
            session.enableConsoleLog();
            session.navigateAndWait(uri, DomContentLoad);
            MyJsFunction utility = session.getJsFunction(MyJsFunction.class);
            List<String> attributes = utility.listAttributes("img", "src");
            assertEquals(2, attributes.size());
            assertEquals("image1.png", attributes.get(0));
            assertEquals("image2.png", attributes.get(1));
            utility.dummy();
            utility.consoleError("panic!");
            assertEquals(4, utility.sum(2, 2));
            assertEquals("foobar", utility.concat("foo", "bar"));
            List<Double> list = utility.increment(asList(0, 1, 2, 3), 1);
            assertEquals(4, list.size());
            assertEquals(valueOf("1"), list.get(0));
            assertEquals(valueOf("2"), list.get(1));
            assertEquals(valueOf("3"), list.get(2));
            assertEquals(valueOf("4"), list.get(3));
            session.wait(500);
        } catch (CdpReadTimeoutException e) {
            // ignore
        } finally {
            launcher.kill();
        }
    }
}
