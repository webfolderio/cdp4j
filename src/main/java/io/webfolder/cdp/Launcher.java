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

import static java.lang.Long.toHexString;
import static java.lang.Runtime.getRuntime;
import static java.lang.String.format;
import static java.lang.System.getProperty;
import static java.lang.Thread.sleep;
import static java.nio.file.Paths.get;
import static java.util.Arrays.asList;
import static java.util.Locale.ENGLISH;
import static java.util.concurrent.ThreadLocalRandom.current;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.ServerSocket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.google.devtools.build.lib.shell.JavaSubprocessFactory;
import com.google.devtools.build.lib.shell.Subprocess;
import com.google.devtools.build.lib.shell.SubprocessBuilder;
import com.google.devtools.build.lib.windows.WindowsSubprocessFactory;
import com.google.devtools.build.lib.windows.jni.WindowsJniLoader;

import io.webfolder.cdp.channel.ChannelFactory;
import io.webfolder.cdp.channel.Connection;
import io.webfolder.cdp.channel.WebSocketConnection;
import io.webfolder.cdp.exception.CdpException;
import io.webfolder.cdp.session.SessionFactory;

public class Launcher {

    private static final boolean JAVA_8   = getProperty("java.version").startsWith("1.8.");

    private static final String  OS_NAME  = getProperty("os.name").toLowerCase(ENGLISH);

    private static final boolean WINDOWS  = OS_NAME.startsWith("windows");

    private static final boolean OSX      = OS_NAME.startsWith("mac");

    private static final File WORKING_DIR = new File(".");

    private final Options options;

    private final ChannelFactory channelFactory;

    public Launcher(Options options, ChannelFactory channelFactory) {
        this.options = options;
        this.channelFactory = channelFactory;
    }

    public Launcher(ChannelFactory channelFactory) {
        this(Options.builder().build(), channelFactory);
    }

    public Launcher(Options options) {
        this(options, createChannelFactory());
    }

    public Launcher() {
        this(Options.builder().build(),
                createChannelFactory());
    }

    protected String findChrome() {
        if (WINDOWS) {
            return findChromeWinPath();
        } else if (OSX) {
            return findChromeOsxPath();
        }
        return "google-chrome";
    }

    protected String findChromeWinPath() {
        try {
            for (String path : getChromeWinPaths()) {
                final Process process = getRuntime().exec(new String[] {
                        "cmd", "/c", "echo", path
                });
                final int exitCode = process.waitFor();
                if (exitCode == 0) {
                    try (InputStream is = process.getInputStream()) {
                        String location = toString(is).trim().replace("\"", "");
                        File chrome = new File(location);
                        if (chrome.exists() && chrome.canExecute()) {
                            return chrome.toString();
                        }
                    }
                }
            }
        } catch (Throwable e) {
            // ignore
        }
        return null;
    }

    /**
     * Tests whether chrome/chromium is installed.
     * 
     * @return {@code true} if browser is found on predefined paths
     */
    public boolean isChromeInstalled() {
        return findChrome() != null ? true : false;
    }

    protected List<String> getChromeWinPaths() {
        List<String> prefixes = asList("%localappdata%",
                                       "%programfiles%",
                                       "%programfiles(x86)%");
        List<String> suffixes = asList(
                "\\Google\\Chrome Dev\\Application\\chrome.exe", // Chrome Dev
                "\\Google\\Chrome SxS\\Application\\chrome.exe", // Chrome Canary
                "\\Google\\Chrome\\Application\\chrome.exe");    // Chrome
        List<String> installations = new ArrayList<String>(prefixes.size() * suffixes.size());
        for (String prefix : prefixes) {
            for (String suffix : suffixes) {
                installations.add(prefix + suffix);
            }
        }
        return installations;
    }

    protected String findChromeOsxPath() {
        for (String path : getChromeOsxPaths()) {
            final File chrome = new File(path);
            if (chrome.exists() && chrome.canExecute()) {
                return chrome.toString();
            }
        }
        return null;
    }

    protected List<String> getChromeOsxPaths() {
        return asList(
                "/Applications/Google Chrome Canary.app/Contents/MacOS/Google Chrome Canary", // Chrome Canary
                "/Applications/Google Chrome.app/Contents/MacOS/Google Chrome"                // Chrome Stable
        );
    }

    protected List<String> getCommonParameters(String chromeExecutablePath, List<String> arguments) {
        List<String> list = new ArrayList<>();
        list.add(chromeExecutablePath);
        // Disable built-in Google Translate service
        list.add("--disable-features=TranslateUI");
        // Disable all chrome extensions entirely
        list.add("--disable-extensions");
        // Disable various background network services, including extension updating,
        // safe browsing service, upgrade detector, translate, UMA
        list.add("--disable-background-networking");
        // Disable fetching safebrowsing lists, likely redundant due to disable-background-networking
        list.add("--safebrowsing-disable-auto-update");
        // Disable syncing to a Google account
        list.add("--disable-sync");
        // Disable reporting to UMA, but allows for collection
        list.add("--metrics-recording-only");
        // Disable installation of default apps on first run
        list.add("--disable-default-apps");
        // Mute any audio
        list.add("--mute-audio");
        // Skip first run wizards
        list.add("--no-first-run");
        list.add("--no-default-browser-check");
        list.add("--disable-plugin-power-saver");
        list.add("--disable-popup-blocking");
        if ( ! arguments.isEmpty() ) {
            list.addAll(arguments);
        }
        return list;
    }

    protected String toString(InputStream is) {
        try (Scanner scanner = new Scanner(is)) {
            scanner.useDelimiter("\\A");
            return scanner.hasNext() ? scanner.next() : "";
        }
    }

    public SessionFactory launch() {
        List<String> arguments = getCommonParameters(findChrome(), options.arguments());
        if (arguments.contains("--remote-debugging-pipe")) {
            arguments.remove("--remote-debugging-port=0");
        } else {
            arguments.add("--remote-debugging-port=0");            
        }

        Path userDataDir = options.userDataDir();
        if (options.userDataDir() == null) {
            userDataDir = get(getProperty("java.io.tmpdir")).resolve("remote-profile");
        }

        arguments.add(format("--user-data-dir=%s", userDataDir.toString()));

        boolean inUse = isInUse(userDataDir);
        if (inUse) {
            throw new CdpException("--user-data-dir [" + userDataDir.toString() + "] is used by another process.");
        }

        if (options.headless()) {
            arguments.add("--headless");
        }

        SessionFactory factory = null;
        switch (options.processExecutor()) {
            case ProcessBuilder:
                factory = launchWithProcessBuilder(arguments);
            break;
            case WfExec:
                if ( ! WINDOWS ) {
                    throw new CdpException("WfExec supports only Windows.");
                }
                factory = launchWithWfExec(arguments);
            break;
        }

        return factory;
    }

    private boolean isInUse(Path userDataDir) {
        Path devToolsActivePort = userDataDir.resolve("DevToolsActivePort");
        if (Files.exists(devToolsActivePort)) {
            List<String> lines = Collections.emptyList();
            try {
                lines = Files.readAllLines(devToolsActivePort);
            } catch (IOException e) {
                throw new CdpException(e);
            }
            if (lines.size() >= 1) {
                int port = Integer.parseInt(lines.get(0));
                if (port > 0) {
                    try (ServerSocket ignored = new ServerSocket(port)) {
                        return false;
                    } catch (IOException e) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private SessionFactory launchWithWfExec(List<String> arguments) {
        Connection connection = null;

        if (WINDOWS) {
            WindowsJniLoader.loadJni();
        }

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

            options.processManager().setProcess(new CdpProcess(process, cdp4jId));
        } catch (IOException e) {
            throw new CdpException(e);
        }

        SessionFactory factory = new SessionFactory(options,
                                                    channelFactory,
                                                    connection);
        return factory;
    }

    private SessionFactory launchWithProcessBuilder(List<String> arguments) {
        Connection connection = null;
        ProcessBuilder builder = new ProcessBuilder(arguments);

        String cdp4jId = toHexString(current().nextLong());
        arguments.add(format("--cdp4jId=%s", cdp4jId));
        builder.environment().put("CDP4J_ID", cdp4jId);
        try {
            Process process = builder.start();
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
                    throw new CdpException("WebSocket connection url is required!");
                }
            }

            if ( ! process.isAlive() ) {
                throw new CdpException("No process: the chrome process is not alive.");
            }

            options.processManager().setProcess(new CdpProcess(process, cdp4jId));
        } catch (IOException e) {
            throw new CdpException(e);
        }

        SessionFactory factory = new SessionFactory(options,
                                                    channelFactory,
                                                    connection);
        return factory;
    }

    private void close(Subprocess subProcess) {
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

    protected static ChannelFactory createChannelFactory() {
        try {
            Class<?> klass = null;
            if ( ! JAVA_8 ) {
                klass = Launcher.class.getClassLoader().loadClass("io.webfolder.cdp.channel.JreWebSocketFactory");
            } else {
                klass = Launcher.class.getClassLoader().loadClass("io.webfolder.cdp.channel.NvWebSocketFactory");
            }
            Constructor<?> constructor = klass.getConstructor();
            return (ChannelFactory) constructor.newInstance();
        } catch (ClassNotFoundException |
                 InstantiationException | IllegalAccessException |
                 NoSuchMethodException  | SecurityException |
                 IllegalArgumentException | InvocationTargetException e) {
            throw new CdpException(e);
        }
    }

    public boolean kill() {
        return options.processManager().kill();
    }

    public Options getOptions() {
        return options;
    }
}
