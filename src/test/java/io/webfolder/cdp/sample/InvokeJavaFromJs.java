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

import static io.webfolder.cdp.event.Events.RuntimeBindingCalled;
import static io.webfolder.cdp.session.WaitUntil.DomContentLoad;
import static java.lang.String.format;

import io.webfolder.cdp.Launcher;
import io.webfolder.cdp.command.Page;
import io.webfolder.cdp.event.runtime.BindingCalled;
import io.webfolder.cdp.session.Session;
import io.webfolder.cdp.session.SessionFactory;

public class InvokeJavaFromJs {

    public static void main(String[] args) {
        Launcher launcher = new Launcher();

        try (SessionFactory factory = launcher.launch();
                                Session session = factory.create()) {
            session.enableConsoleLog();

            Page page = session.getCommand().getPage();
            // enable Page domain before using the addScriptToEvaluateOnNewDocument()
            page.enable();

            // addScriptToEvaluateOnNewDocument() must be called before Session.navigate()
            page.addScriptToEvaluateOnNewDocument("sendMessage = send");
            session.getCommand().getRuntime().addBinding("send");

            session.navigateAndWait("about:blank", DomContentLoad);

            session.addEventListener((event, value) -> {
                if (RuntimeBindingCalled.equals(event)) {
                    BindingCalled binding = (BindingCalled) value;
                    System.out.println(format("name: [%s] payload: [%s]",
                                            binding.getName(), binding.getPayload()));
                }
            });

            session.evaluate("sendMessage(JSON.stringify({ 'foo' : 'bar' }));");
            session.evaluate("sendMessage(JSON.stringify({ 'hello' : 'world' }));");
        } finally {
            launcher.kill();
        }
    }
}
