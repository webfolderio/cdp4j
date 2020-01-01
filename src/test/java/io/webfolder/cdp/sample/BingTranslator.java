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

import io.webfolder.cdp.Launcher;
import io.webfolder.cdp.session.Option;
import io.webfolder.cdp.session.Session;
import io.webfolder.cdp.session.SessionFactory;

public class BingTranslator {

    public static void main(String[] args) {
        Launcher launcher = new Launcher();

        try (SessionFactory factory = launcher.launch();
                            Session session = factory.create()) {
            session
                .navigate("https://www.bing.com/translator")
                .waitDocumentReady()
                .enableConsoleLog()
                .enableDetailLog()
                .enableNetworkLog();

            Option en = session
                        .getOptions("#tta_srcsl")
                        .stream()
                        .filter(p -> "en".equals(p.getValue()))
                        .findFirst()
                        .get();
            
            Option et = session
                        .getOptions("#tta_tgtsl")
                        .stream()
                        .filter(p -> "et".equals(p.getValue()))
                        .findFirst()
                        .get();

            session
                .click("#tta_srcsl") // click source language
                .wait(500)
                .setSelectedIndex("#tta_srcsl", en.getIndex()) // choose English
                .wait(500)
                .click("#tta_tgtsl") // click destination language
                .wait(500)
                .setSelectedIndex("#tta_tgtsl", et.getIndex()) // choose Estonian
                .wait(500);

            session.focus("#tta_input")
                    .wait(100)
                    .sendKeys("hello world")
                    .wait(1000);

            System.out.println(session.getValue("#tta_output"));
        } finally {
            launcher.kill();
        }
    }
}
