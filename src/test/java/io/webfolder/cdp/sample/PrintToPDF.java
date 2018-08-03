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
package io.webfolder.cdp.sample;

import static java.awt.Desktop.getDesktop;
import static java.awt.Desktop.isDesktopSupported;
import static java.nio.file.Files.createTempFile;
import static java.nio.file.Files.write;
import static java.util.Arrays.asList;

import java.io.IOException;
import java.nio.file.Path;

import io.webfolder.cdp.AdaptiveProcessManager;
import io.webfolder.cdp.Launcher;
import io.webfolder.cdp.session.Session;
import io.webfolder.cdp.session.SessionFactory;

public class PrintToPDF {

    // Requires Headless Chrome
    // https://chromium.googlesource.com/chromium/src/+/lkgr/headless/README.md
    public static void main(String[] args) throws IOException {
        Launcher launcher = new Launcher();
        launcher.setProcessManager(new AdaptiveProcessManager());

        Path file = createTempFile("cdp4j", ".pdf");

        try (SessionFactory factory = launcher.launch(asList("--disable-gpu", "--headless"))) {

            String context = factory.createBrowserContext();
            try (Session session = factory.create(context)) {

                session.navigateAndWait("https://webfolder.io/cdp4j.html");

                byte[] content = session
                                    .getCommand()
                                    .getPage()
                                    .printToPDF();

                write(file, content);
            }

            factory.disposeBrowserContext(context);
        }

        if (isDesktopSupported()) {
            getDesktop().open(file.toFile());
        }

        launcher.getProcessManager().kill();
    }
}
