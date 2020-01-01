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

import static java.lang.String.format;

import java.net.URL;
import java.util.List;

import io.webfolder.cdp.Launcher;
import io.webfolder.cdp.command.Profiler;
import io.webfolder.cdp.session.Command;
import io.webfolder.cdp.session.Session;
import io.webfolder.cdp.session.SessionFactory;
import io.webfolder.cdp.type.profiler.CoverageRange;
import io.webfolder.cdp.type.profiler.FunctionCoverage;
import io.webfolder.cdp.type.profiler.ScriptCoverage;

public class CodeCoverage {

    public static void main(String[] args) {

        URL url = CodeCoverage.class.getResource("/code-coverage.html");

        Launcher launcher = new Launcher();

        try (SessionFactory factory = launcher.launch();
                            Session session = factory.create()) {
            Command command = session.getCommand();
            Profiler profiler = command.getProfiler();
            
            session.navigate(url.toString());
            session.waitDocumentReady();

            profiler.enable();

            profiler.start();
            session.callFunction("fibonacci", Double.class, 20);
            profiler.stop();

            List<ScriptCoverage> list = profiler.getBestEffortCoverage();

            for (ScriptCoverage coverage : list) {
                
                if ( ! coverage.getUrl().endsWith("test-lib.js") ) {
                    continue;
                }

                String libName = coverage.getUrl().substring(coverage.getUrl().lastIndexOf("/") + 1, coverage.getUrl().length());

                List<FunctionCoverage> functions = coverage.getFunctions();
                System.out.println("");
                System.out.println("======================================================================");
                System.out.println(String.format(" %-40s %s %s", "[Function]", "[Start Offset]", "[End Offset]"));
                System.out.println("======================================================================");
                for (FunctionCoverage functionCoverage : functions) {
                    if (functionCoverage.getFunctionName().isEmpty()) {
                        continue;
                    }
                    for (CoverageRange range : functionCoverage.getRanges()) {
                        System.out.println(format(" %-46s %-11d %d",
                                        libName + "/" + functionCoverage.getFunctionName(),
                                        range.getStartOffset(),
                                        range.getEndOffset()));
                    }
                }
                System.out.println("");
            }

            profiler.disable();
        } finally {
            launcher.kill();
        }
    }
}
