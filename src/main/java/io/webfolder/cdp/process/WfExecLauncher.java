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
package io.webfolder.cdp.process;

import static java.lang.Long.toHexString;
import static java.lang.String.format;
import static java.lang.System.getProperty;
import static java.lang.Thread.sleep;
import static java.util.Locale.ENGLISH;
import static java.util.concurrent.ThreadLocalRandom.current;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.google.devtools.build.lib.shell.JavaSubprocessFactory;
import com.google.devtools.build.lib.shell.Subprocess;
import com.google.devtools.build.lib.shell.SubprocessBuilder;
import com.google.devtools.build.lib.windows.WindowsSubprocessFactory;
import com.google.devtools.build.lib.windows.jni.WindowsJniLoader;

import io.webfolder.cdp.Options;
import io.webfolder.cdp.channel.ChannelFactory;
import io.webfolder.cdp.channel.Connection;
import io.webfolder.cdp.channel.WebSocketConnection;
import io.webfolder.cdp.exception.CdpException;
import io.webfolder.cdp.session.SessionFactory;

public class WfExecLauncher {

    private static final String  OS_NAME  = getProperty("os.name").toLowerCase(ENGLISH);

    private static final boolean WINDOWS  = OS_NAME.startsWith("windows");

    private static final File WORKING_DIR = new File(".");

    static {
        if (WINDOWS) {
            WindowsJniLoader.loadJni();
        }
    }

    public static SessionFactory launchWithWfExec(
                Options        options,
                ChannelFactory channelFactory,
                List<String>   arguments) {
        Connection connection = null;

        SubprocessBuilder builder = new SubprocessBuilder(WINDOWS ?
                                                          WindowsSubprocessFactory.INSTANCE :
                                                          JavaSubprocessFactory.INSTANCE);
        builder.setWorkingDirectory(WORKING_DIR);

        String cdp4jId = toHexString(current().nextLong());
        arguments.add(format("--cdp4jId=%s", cdp4jId));

        builder.setArgv(arguments);

        Map<String, String> env = new LinkedHashMap<>(1);
        env.put("CDP4J_ID", cdp4jId);
        builder.setEnv(env);

        try {
            Subprocess process = builder.start();
            try (Scanner scanner = new Scanner(process.getErrorStream())) {
                while (scanner.hasNext()) {
                    String line = scanner.nextLine().trim();
                    if (line.isEmpty()) {
                        continue;
                    }
                    if (line.toLowerCase(ENGLISH).startsWith("devtools listening on")) {
                        int start = line.indexOf("ws://");
                        connection = new WebSocketConnection(line.substring(start, line.length()));
                        break;
                    }
                }
                if (connection == null) {
                    close(process);
                    throw new CdpException("WebSocket connection url is required!");
                }
            }

            if (process.finished()) {
                close(process);
                throw new CdpException("No process: the chrome process is not alive.");
            }

            options.processManager()
                    .setProcess(new CdpProcess(process, cdp4jId));
        } catch (IOException e) {
            throw new CdpException(e);
        }

        SessionFactory factory = new SessionFactory(options,
                                                    channelFactory,
                                                    connection);
        return factory;
    }


    private static void close(Subprocess subProcess) {
        if ( ! subProcess.finished() ) {
            subProcess.destroy();
            while ( ! subProcess.finished() ) {
                try {
                    sleep(1);
                } catch (InterruptedException e) {
                    // ignore
                }
            }
        }
        if (subProcess.finished()) {
            subProcess.close();
        }
    }
}
