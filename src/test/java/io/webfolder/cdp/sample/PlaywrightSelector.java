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

import static io.webfolder.cdp.SelectorEngine.Playwright;

import io.webfolder.cdp.Launcher;
import io.webfolder.cdp.Options;
import io.webfolder.cdp.session.Session;
import io.webfolder.cdp.session.SessionFactory;

public class PlaywrightSelector {

    public static void main(String[] args) throws InterruptedException {
        Launcher launcher = new Launcher(Options.builder()
                                                // Use Playwright selector instead of native
                                                // See: https://playwright.dev/#version=v1.2.1&path=docs%2Fselectors.md
                                                .selectorEngine(Playwright)
                                            .build());

        try (SessionFactory factory = launcher.launch();
                            Session session = factory.create()) {
            session.navigate("https://news.ycombinator.com");
            session.waitDocumentReady();
            // use the text selector
            session.click("text=submit");
            session.waitDocumentReady();
            // combine the text selector and the xpath selector
            session.setValue("text=username >> xpath=following-sibling::*[1]/input", "foobar123456789");
            session.setValue("text=password >> xpath=following-sibling::*[1]/input", "bar");
            // use the css selector
            session.click("css=[value=login]");
            session.waitDocumentReady();
            session.wait(2_000);
        } finally {
            launcher.kill();
        }
    }
}
