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

import static io.webfolder.cdp.logger.CdpLoggerType.Null;
import static io.webfolder.cdp.session.WaitUntil.DomReady;
import static java.nio.file.Files.readAllBytes;
import static java.nio.file.Paths.get;

import io.webfolder.cdp.Launcher;
import io.webfolder.cdp.Options;
import io.webfolder.cdp.command.Page;
import io.webfolder.cdp.session.Session;
import io.webfolder.cdp.session.SessionFactory;

// Remove noise and ads before extracting the content
// @see https://github.com/mozilla/readability
public class Readability {

    public static void main(String[] args) throws Exception {
        Options options = Options.builder()
                .loggerType(Null)
            .build();

        Launcher launcher = new Launcher(options);

        try (SessionFactory factory = launcher.launch();
                            Session session = factory.create()) {

            Page page = session.getCommand().getPage();
            // enable Page domain before using the addScriptToEvaluateOnNewDocument()
            page.enable();

            // addScriptToEvaluateOnNewDocument() must be called before Session.navigate()
            page.addScriptToEvaluateOnNewDocument(new String(readAllBytes(get("src/test/resources/readability.js"))));

            session.navigateAndWait("https://www.theregister.co.uk/2018/07/27/lower_saxony_to_dump_linux", DomReady);
   
            String content = (String) session.evaluate("new Readability(window.document).parse().content");
            System.out.println(content);            
        } finally {
            launcher.kill();
        }
    }
}
