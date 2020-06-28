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

import static io.webfolder.cdp.ProcessExecutor.LibUv;
import static java.lang.ProcessHandle.of;
import static java.lang.Runtime.getRuntime;
import static java.lang.String.format;
import static java.lang.System.getProperty;
import static java.nio.file.Paths.get;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Locale.ENGLISH;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ProcessHandle.Info;
import java.net.ServerSocket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Stream;

import io.webfolder.cdp.channel.ChannelFactory;
import io.webfolder.cdp.channel.Connection;
import io.webfolder.cdp.channel.JreWebSocketFactory;
import io.webfolder.cdp.channel.LibUvChannelFactory;
import io.webfolder.cdp.channel.WebSocketConnection;
import io.webfolder.cdp.exception.CdpException;
import io.webfolder.cdp.session.SessionFactory;

public class Launcher {

    private static final String  OS_NAME  = getProperty("os.name").toLowerCase(ENGLISH);

    private static final boolean WINDOWS  = OS_NAME.startsWith("windows");

    private static final boolean OSX      = OS_NAME.startsWith("mac");

    private final Options options;

    private final ChannelFactory channelFactory;

	private Instant startTime;

	private String command;

	private long pid;

    public Launcher(Options options, ChannelFactory channelFactory) {
        this.options = options;
        this.channelFactory = channelFactory;
    }

    public Launcher(ChannelFactory channelFactory) {
        this(Options.builder().build(), channelFactory);
    }

    public Launcher(Options options) {
        this(options, LibUv.equals(options.processExecutor()) ?
                                    new LibUvChannelFactory() :
                                    new JreWebSocketFactory());
    }

    public Launcher() {
        this(Options.builder().build(),
                new JreWebSocketFactory());
    }

    protected String findChrome() {
        String executablePath = options.browserExecutablePath();
        if ( executablePath != null && ! executablePath.trim().isEmpty() ) {
            return executablePath;
        } else {
            if (WINDOWS) {
                return findChromeWinPath();
            } else if (OSX) {
                return findChromeOsxPath();
            }
            return "google-chrome";
        }
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
        Browser browser = options.getBrowser();
        List<String> prefixes = asList("%localappdata%",
                                       "%programfiles%",
                                       "%programfiles(x86)%");
        List<String> suffixes = emptyList();
        switch (browser) {
            case Any:
                suffixes = asList(
                            "\\Google\\Chrome Dev\\Application\\chrome.exe", // Chrome Dev
                            "\\Google\\Chrome SxS\\Application\\chrome.exe", // Chrome Canary
                            "\\Google\\Chrome\\Application\\chrome.exe",     // Chrome
                            "\\Microsoft\\Edge\\Application\\msedge.exe");   // Microsoft Edge
            default:
            case Chrome:
                suffixes = asList("\\Google\\Chrome\\Application\\chrome.exe");
            break;
            case ChromeCanary:
                suffixes = asList("\\Google\\Chrome SxS\\Application\\chrome.exe");
            break;
            case ChromeDev:
                suffixes = asList("\\\\Google\\\\Chrome Dev\\\\Application\\\\chrome.exe");
            break;
            case MicrosoftEdge:
                suffixes = asList("\\Microsoft\\Edge\\Application\\msedge.exe");
            break;
        }
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
            case LibUv:
                arguments.add("--remote-debugging-pipe");
                factory = launchWithLibUv(arguments);
            break;
            case ProcessBuilder:
                arguments.add("--remote-debugging-port=0");
                factory = launchWithProcessBuilder(arguments);
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

    private SessionFactory launchWithLibUv(List<String> arguments) {
        LibUvChannelFactory libUvChannelFactory = (LibUvChannelFactory) channelFactory;
        Path chromePath = get(arguments.get(0));
        libUvChannelFactory.spawn(chromePath, arguments);
        SessionFactory factory = new SessionFactory(options,
                                                    channelFactory,
                                                    (Connection) channelFactory);
        return factory;
    }

    private SessionFactory launchWithProcessBuilder(List<String> arguments) {
        Connection connection = null;
        ProcessBuilder builder = new ProcessBuilder(arguments);

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

            ProcessHandle handle = process.toHandle();
            Info info = handle.info();
            startTime = info.startInstant().get();
            command = info.command().get();
            pid = handle.pid();
        } catch (IOException e) {
            throw new CdpException(e);
        }

        SessionFactory factory = new SessionFactory(options,
                                                    channelFactory,
                                                    connection);
        return factory;
    }

    public boolean kill() {
        ProcessExecutor executor = options.processExecutor();
        switch (executor) {
            case LibUv:
                ((LibUvChannelFactory) channelFactory).kill();
            default:
                Optional<ProcessHandle> process = of(pid);
                if (process.isPresent()) {
                    ProcessHandle handle = process.get();
                    Info info = handle.info();
                    if (handle.isAlive() &&
                            info.startInstant().isPresent() &&
                            info.startInstant().get().equals(startTime) &&
                            info.command().isPresent() &&
                            info.command().get().equals(command)) {
                        try (Stream<ProcessHandle> descendants = handle.descendants()) {
                            descendants.forEach(ph -> {
                                try {
                                    if (ph.isAlive()) {
                                        ph.destroyForcibly();
                                    }
                                } catch (Exception ignored) {
                                    
                                }
                            });
                        }
                        boolean success = handle.destroyForcibly();
                        if (success) {
                            handle.onExit().join();
                        }
                        return success;
                    }
                }
                return false;
        }
    }

    public Options getOptions() {
        return options;
    }
}
