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

import static io.webfolder.cdp.logger.CdpLoggerType.Console;
import static io.webfolder.cdp.logger.CdpConsoleLogggerLevel.Info;

import io.webfolder.cdp.Launcher;
import io.webfolder.cdp.Options;
import io.webfolder.cdp.session.Session;
import io.webfolder.cdp.session.SessionFactory;

public class Logging {

    public static void main(String[] args) {
        Launcher launcher = new Launcher(Options.builder()
                                            .consoleLoggerLevel(Info)
                                            .loggerType(Console)
                                           .build());

        try (SessionFactory factory = launcher.launch();
                            Session session = factory.create()) {
            session.navigate("about:blank");
            session.waitDocumentReady();

            // logs javascript messages
            session.enableConsoleLog();

            session.evaluate("console.info('info message')");
            session.evaluate("console.error('error message')");
            session.evaluate("console.warn('warning message')");

            // logs newtwork, violation, security, storage and deprecation messages
            session.enableDetailLog();

            session.evaluate("fetch('https://google.com')");
            session.wait(2000);
        } finally {
            launcher.kill();
        }
    }
}
