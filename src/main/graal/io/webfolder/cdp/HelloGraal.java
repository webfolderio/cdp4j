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
package io.webfolder.cdp;

import static io.webfolder.cdp.ConnectionMode.Pipe;
import static io.webfolder.cdp.logger.CdpLoggerType.Console;

import java.io.IOException;

import io.webfolder.cdp.session.Session;
import io.webfolder.cdp.session.SessionFactory;

public class HelloGraal {

    // before compile execute main method using native-image-agent
    // -agentlib:native-image-agent=config-output-dir=src/main/resources/META-INF/native-image/io.webfolder/cdp4j
    public static void main(String[] args) throws IOException, InterruptedException {
        Options options = Options.builder()
                                 .connectionMode(Pipe)
                                 .loggerType(Console)
                             .build();

        Launcher launcher = new Launcher(options);
        try (SessionFactory factory = launcher.launch();
                Session session = factory.create()) {
            session.navigate("https://webfolder.io");
            session.waitDocumentReady();
            System.out.println(session.getText("body"));
        } finally {
            launcher.kill();
        }
    }
}
