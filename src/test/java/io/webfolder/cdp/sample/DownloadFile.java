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

import static io.webfolder.cdp.type.constant.DownloadBehavior.Allow;

import java.nio.file.Path;
import java.nio.file.Paths;

import io.webfolder.cdp.Launcher;
import io.webfolder.cdp.command.Page;
import io.webfolder.cdp.session.Session;
import io.webfolder.cdp.session.SessionFactory;

public class DownloadFile {

    public static void main(String[] args) {
        try (Launcher launcher = new Launcher();
                            SessionFactory factory = launcher.launch();
                            Session session = factory.create()) {
            session.navigateAndWait("https://www.chiark.greenend.org.uk/~sgtatham/putty/latest.html");
            session.getCommand().getNetwork().enable();
            Page page = session.getCommand().getPage();
            Path downloadPath = Paths.get(".").toAbsolutePath();
            page.setDownloadBehavior(Allow, downloadPath.toString());
            // link must be visible before downloading the file
            session.evaluate("document.querySelector(\"code\").scrollIntoView()");
            // click the download link
            session.click("code");
            session.wait(2000 * 200);
        }
    }
}
