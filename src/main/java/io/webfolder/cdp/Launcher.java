/**
 * cdp4j - Chrome DevTools Protocol for Java
 * Copyright © 2017, 2018 WebFolder OÜ (support@webfolder.io)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package io.webfolder.cdp;

import static io.webfolder.cdp.session.SessionFactory.DEFAULT_HOST;
import static java.lang.Long.toHexString;
import static java.lang.Runtime.getRuntime;
import static java.lang.String.format;
import static java.lang.System.getProperty;
import static java.nio.file.Paths.get;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Locale.ENGLISH;
import static java.util.concurrent.ThreadLocalRandom.current;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

import io.webfolder.cdp.exception.CdpException;
import io.webfolder.cdp.session.SessionFactory;

public class Launcher extends AbstractLauncher {

    private static final String OS = getProperty("os.name").toLowerCase(ENGLISH);

    private static final boolean WINDOWS = OS.startsWith("windows");

    private static final boolean OSX = OS.startsWith("mac");

    private ProcessManager processManager = new NullProcessManager();

    private static class NullProcessManager extends ProcessManager {

        @Override
        public void setProcess(CdpProcess process) {
        }

        @Override
        public boolean kill() {
            return false;
        }
    }

    public Launcher() {
        this(new SessionFactory());
    }

    public Launcher(int port) {
        this(new SessionFactory(port));
    }

    public Launcher(final SessionFactory factory) {
        super(factory);
    }

    private String getCustomChromeBinary() {
        String chromeBinary = getProperty("chrome_binary");
        if (chromeBinary != null) {
            File chromeExecutable = new File(chromeBinary);
            if (chromeExecutable.exists() && chromeExecutable.canExecute()) {
                return chromeExecutable.getAbsolutePath();
            }
        }
        return null;
    }

    @Override
    public String findChrome() {
        String chromeExecutablePath = null;
        chromeExecutablePath = getCustomChromeBinary();
        if (chromeExecutablePath == null && WINDOWS) {
            chromeExecutablePath = findChromeWinPath();
        }
        if (chromeExecutablePath == null && OSX) {
            chromeExecutablePath = findChromeOsxPath();
        }
        if (chromeExecutablePath == null && !WINDOWS) {
            chromeExecutablePath = "google-chrome";
        }
        return chromeExecutablePath;
    }

    public String findChromeWinPath() {
        try {
            for (String path : getChromeWinPaths()) {
                final Process process = getRuntime().exec(new String[]{
                        "cmd", "/c", "echo", path
                });
                final int exitCode = process.waitFor();
                if (exitCode == 0) {
                    final String location = toString(process.getInputStream()).trim().replace("\"", "");
                    final File chrome = new File(location);
                    if (chrome.exists() && chrome.canExecute()) {
                        return chrome.toString();
                    }
                }
            }
            throw new CdpException("Unable to find chrome.exe");
        } catch (Throwable e) {
            // ignore
        }
        return null;
    }

    protected List<String> getChromeWinPaths() {
        return asList(
                "%localappdata%\\Google\\Chrome SxS\\Application\\chrome.exe", // Chrome Canary
                "%programfiles%\\Google\\Chrome\\Application\\chrome.exe",     // Chrome Stable 64-bit
                "%programfiles(x86)%\\Google\\Chrome\\Application\\chrome.exe" // Chrome Stable 32-bit
        );
    }

    public String findChromeOsxPath() {
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

    public SessionFactory launch(Path chromeExecutablePath, List<String> arguments) {
        return launch(chromeExecutablePath.toString(), arguments);
    }

    public SessionFactory launch(Path chromeExecutablePath) {
        return launch(chromeExecutablePath, emptyList());
    }

    @Override
    protected void internalLaunch(List<String> list, List<String> arguments) {
        boolean foundUserDataDir = arguments.stream().anyMatch(arg -> arg.startsWith("--user-data-dir="));

        if (!foundUserDataDir) {
            Path remoteProfileData = get(getProperty("java.io.tmpdir")).resolve("remote-profile");
            list.add(format("--user-data-dir=%s", remoteProfileData.toString()));
        }

        if (!DEFAULT_HOST.equals(factory.getHost())) {
            list.add(format("--remote-debugging-address=%s", factory.getHost()));
        }

        try {
            String cdp4jId = toHexString(current().nextLong());
            list.add("--cdp4jId=" + cdp4jId);
            ProcessBuilder builder = new ProcessBuilder(list);
            builder.environment().put("CDP4J_ID", cdp4jId);
            Process process = builder.start();

            process.getOutputStream().close();
            process.getInputStream().close();
            process.getErrorStream().close();

            if (!process.isAlive()) {
                throw new CdpException("No process: the chrome process is not alive.");
            }

            processManager.setProcess(new CdpProcess(process, cdp4jId));
        } catch (IOException e) {
            throw new CdpException(e);
        }
    }

    protected String toString(InputStream is) {
        try (Scanner scanner = new Scanner(is)) {
            scanner.useDelimiter("\\A");
            return scanner.hasNext() ? scanner.next() : "";
        }
    }

    public void setProcessManager(ProcessManager processManager) {
        this.processManager = processManager;
    }

    public ProcessManager getProcessManager() {
        return processManager;
    }

    @Override
    public void kill() {
        getProcessManager().kill();
    }
}
