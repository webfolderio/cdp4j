/**
 * cdp4j - Chrome DevTools Protocol for Java
 * Copyright © 2017, 2018 WebFolder OÜ (support@webfolder.io)
 * <p>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * <p>
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package io.webfolder.cdp.sample;

import io.webfolder.cdp.Launcher;
import io.webfolder.cdp.session.Session;
import io.webfolder.cdp.session.SessionFactory;
import io.webfolder.cdp.session.WaitUntil;

public class SharedSession {

    public static void main(String[] args) {
        Launcher launcher = new Launcher();

        try (SessionFactory factory = launcher.launch();
             Session session = factory.create()) {

            try (Session firstSession = factory.create()) {
                firstSession.navigateAndWait("https://httpbin.org/cookies/set?SESSION_ID=1",WaitUntil.Load);
                firstSession.wait(1000);
                String session1 = (String) firstSession.evaluate("window.document.body.textContent");
                System.out.println("Session1: " + session1);
            }

            try (Session secondSession = factory.create()) {
                secondSession.navigateAndWait("https://httpbin.org/cookies", WaitUntil.Load);
                String session2 = (String) secondSession.evaluate("window.document.body.textContent");
                secondSession.wait(1000);
                System.out.println("Session2: " + session2);
            }
        }
    }
}
