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

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.webfolder.cdp.exception.CdpException;
import io.webfolder.cdp.logger.CdpLogger;
import io.webfolder.cdp.logger.CdpLoggerFactory;
import io.webfolder.cdp.logger.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

import static java.io.File.pathSeparator;
import static java.lang.Integer.compare;
import static java.lang.Integer.parseInt;
import static java.lang.Math.round;
import static java.lang.String.format;
import static java.lang.String.valueOf;
import static java.lang.System.getProperty;
import static java.lang.Thread.sleep;
import static java.nio.file.Files.*;
import static java.nio.file.Paths.get;
import static java.util.Locale.ENGLISH;

public class ChromiumDownloader implements Downloader {

    private static final String OS            = getProperty("os.name").toLowerCase(ENGLISH);

    private static final boolean WINDOWS      = ";".equals(pathSeparator);

    private static final boolean MAC          = OS.contains("mac");

    private static final boolean LINUX        = OS.contains("linux");

    private static final String DOWNLOAD_HOST = "https://storage.googleapis.com/chromium-browser-snapshots";

    private static final String VERSION_URL   = "https://raw.githubusercontent.com/GoogleChrome/puppeteer/master/package.json";

    private static final int TIMEOUT = 10 * 1000; // 10 seconds

    private final CdpLogger logger;

    public ChromiumDownloader() {
        this(new CdpLoggerFactory());
    }

    public ChromiumDownloader(LoggerFactory loggerFactory) {
        this.logger = loggerFactory.getLogger("cdp4j.downloader");
    }

    @Override
    public Path download() {
        return download(getLatestVersion());
    }

    public static ChromiumVersion getLatestVersion() {
        try {
            URL u = new URL(VERSION_URL);

            HttpURLConnection conn = (HttpURLConnection) u.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(TIMEOUT);
            conn.setReadTimeout(TIMEOUT);

            if (conn.getResponseCode() != 200) {
                throw new CdpException(conn.getResponseCode() + " - " + conn.getResponseMessage());
            }

            Scanner s = new Scanner(conn.getInputStream()).useDelimiter("\\A");
            String replyString = s.hasNext() ? s.next() : "";

            JsonNode json = (new ObjectMapper()).readTree(replyString);

            int version = json.get("puppeteer").get("chromium_revision").asInt();

            return new ChromiumVersion(version);
        } catch (IOException e) {
            throw new CdpException(e);
        }
    }

    public static Path getChromiumPath(ChromiumVersion version) {
        Path destinationRoot = get(getProperty("user.home"))
                                .resolve(".cdp4j")
                                .resolve("chromium-" + valueOf(version.getRevision()));
        return destinationRoot;
    }

    public static Path getExecutable(ChromiumVersion version) {
        Path destinationRoot = getChromiumPath(version);
        Path executable = destinationRoot.resolve("chrome.exe");
        if ( LINUX ) {
            executable = destinationRoot.resolve("chrome");
        } else if ( MAC ) {
            executable = destinationRoot.resolve("Chromium.app/Contents/MacOS/Chromium");
        }
        return executable;
    }

    public Path download(ChromiumVersion version) {
        final Path destinationRoot = getChromiumPath(version);
        final Path executable = getExecutable(version);

        String url;
        if ( WINDOWS ) {
            url = format("%s/Win_x64/%d/chrome-win32.zip", DOWNLOAD_HOST, version.getRevision());
        } else if ( LINUX ) {
            url = format("%s/Linux_x64/%d/chrome-linux.zip", DOWNLOAD_HOST, version.getRevision());
        } else if ( MAC ) {
            url = format("%s/Mac/%d/chrome-mac.zip", DOWNLOAD_HOST, version.getRevision());
        } else {
            throw new CdpException("Unsupported OS found - " + OS);
        }

        try {
            URL u = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) u.openConnection();
            conn.setRequestMethod("HEAD");
            conn.setConnectTimeout(TIMEOUT);
            conn.setReadTimeout(TIMEOUT);
            if ( conn.getResponseCode() != 200 ) {
                throw new CdpException(conn.getResponseCode() + " - " + conn.getResponseMessage());
            }
            long contentLength = conn.getHeaderFieldLong("x-goog-stored-content-length", 0);
            String fileName = url.substring(url.lastIndexOf("/") + 1, url.lastIndexOf(".")) + "-r" + version.getRevision() + ".zip";
            Path archive = get(getProperty("java.io.tmpdir")).resolve(fileName);
            if ( exists(archive) && contentLength != size(archive) ) {
                delete(archive);
            }
            if ( ! exists(archive) ) {
                logger.info("Downloading Chromium [revision=" + version.getRevision() + "] 0%");
                u = new URL(url);
                if ( conn.getResponseCode() != 200 ) {
                    throw new CdpException(conn.getResponseCode() + " - " + conn.getResponseMessage());
                }
                conn = (HttpURLConnection) u.openConnection();
                conn.setConnectTimeout(TIMEOUT);
                conn.setReadTimeout(TIMEOUT);
                Thread thread = null;
                AtomicBoolean halt = new AtomicBoolean(false);
                Runnable progress = () -> {
                    try {
                        long fileSize = size(archive);
                        logger.info("Downloading Chromium [revision={}] {}%",
                                version.getRevision(), round((fileSize * 100L) / contentLength));
                    } catch (IOException e) {
                        // ignore
                    }
                };
                try (InputStream is = conn.getInputStream()) {
                    logger.info("Download location: " + archive.toString());
                    thread = new Thread(() -> {
                        while (true) {
                            try {
                                if (halt.get()) {
                                    break;
                                }
                                progress.run();
                                sleep(1000);
                            } catch (Throwable e) {
                                // ignore
                            }
                        }
                    });
                    thread.setName("cdp4j");
                    thread.setDaemon(true);
                    thread.start();
                    copy(conn.getInputStream(), archive);
                } finally {
                    if ( thread != null ) {
                        progress.run();
                        halt.set(true);
                    }
                }
            }
            logger.info("Extracting to: " + destinationRoot.toString());
            if (exists(archive)) {
                createDirectories(destinationRoot);
                ZipUtils.unpack(archive.toFile(), destinationRoot.toFile());
            }
        } catch (IOException e) {
            throw new CdpException(e);
        }
        return executable;
    }

    public static List<ChromiumVersion> getInstalledVersions() {
        Path chromiumRootPath = get(getProperty("user.home")).resolve(".cdp4j");
        if ( ! Files.exists(chromiumRootPath) ) {
            return Collections.emptyList();
        }
        try {
            List<ChromiumVersion> list = list(chromiumRootPath)
                                            .filter(p -> isDirectory(p))
                                            .filter(p -> p.getFileName().toString().startsWith("chromium-"))
                                            .map(p -> new ChromiumVersion(parseInt(p.getFileName().toString().split("-")[1])))
                                        .collect(Collectors.toList());
            list.sort((o1, o2) -> compare(o2.getRevision(), o1.getRevision()));
            return list;
        } catch (IOException e) {
            throw new CdpException(e);
        }
    }

    public static ChromiumVersion getLatestInstalledVersion() {
        List<ChromiumVersion> versions = getInstalledVersions();
        return ! versions.isEmpty() ? versions.get(0) : null;
    }
}
