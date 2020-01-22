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
import io.webfolder.cdp.Options;
import io.webfolder.cdp.ProcessExecutor;
import io.webfolder.cdp.WfProcessManager;
import io.webfolder.cdp.session.Session;
import io.webfolder.cdp.session.SessionFactory;

public class WfExecSample {

    // Unlike standard Java ProcessBuilder
    // WfExec kills child process when parent dies.
    // wf-exec uses Windows Job Objects to manage group of process.
    //
    // wf-exec: https://github.com/webfolderio/wf-exec
    // Job Objects: https://docs.microsoft.com/en-us/windows/win32/procthread/job-objects
    public static void main(String[] args) {
        Options options = Options.builder()
                                // this feature available for only Windows platform
                                .processExecutor(ProcessExecutor.WfExec)
                                // its mandatory to use WfProcessManager
                                .processManager(new WfProcessManager())
                            .build();

        Launcher launcher = new Launcher(options);

        try (SessionFactory factory = launcher.launch();
                            Session session = factory.create()) {
            session.navigate("https://webfolder.io?cdp4j");
            session.waitDocumentReady();
            String content = session.getContent();
            System.out.println(content);
        } finally {
            launcher.kill();
        }
    }
}
