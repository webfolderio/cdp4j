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

import io.webfolder.cdp.Launcher;
import io.webfolder.cdp.session.Session;
import io.webfolder.cdp.session.SessionFactory;

public class Logging {

    public static void main(String[] args) {
        try (Launcher launcher = new Launcher();
                            SessionFactory factory = launcher.launch();
                            Session session = factory.create()) {
            session.navigateAndWait("about:blank");
            session.waitDocumentReady();

            // logs javascript messages
            session.enableConsoleLog();

            session.evaluate("console.info('info message')");
            session.evaluate("console.error('error message')");
            session.evaluate("console.warn('warning message')");

            // logs newtwork, violation, security, storage and deprecation messages
            session.enableDetailLog();

            session.evaluate("fetch('https://google.com')");
            session.wait(2000);
        }
    }
}
