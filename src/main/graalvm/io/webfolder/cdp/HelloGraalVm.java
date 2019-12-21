/**
 * cdp4j Commercial License
 *
 * Copyright 2017, 2019 WebFolder OÜ
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

import static io.webfolder.cdp.CustomTypeAdapter.Generated;
import static io.webfolder.cdp.logger.CdpLoggerType.Console;
import static java.util.Arrays.asList;

import java.io.IOException;

import io.webfolder.cdp.channel.LibuvChannelFactory;
import io.webfolder.cdp.session.Session;
import io.webfolder.cdp.session.SessionFactory;

public class HelloGraalVm {

    public static void main(String[] args) throws IOException, InterruptedException {
        LibuvChannelFactory libuvFactory = new LibuvChannelFactory();
        Options options = Options.builder()
                                 .arguments(asList("--remote-debugging-pipe"))
                                 .useCustomTypeAdapter(Generated)
                                 .loggerType(Console)
                                 .processManager(new LibuvProcessManager(libuvFactory))
                                 .build();
        Launcher launcher = new Launcher(options, libuvFactory);
        try (SessionFactory factory = launcher.launch()) {
            Session session = factory.create();
            session.navigate("https://webfolder.io");
            session.waitDocumentReady();
            System.out.println(session.getText("body"));
        }
    }
}
