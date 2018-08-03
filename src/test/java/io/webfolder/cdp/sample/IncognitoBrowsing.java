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

import static java.util.Arrays.asList;

import io.webfolder.cdp.AdaptiveProcessManager;
import io.webfolder.cdp.Launcher;
import io.webfolder.cdp.session.Session;
import io.webfolder.cdp.session.SessionFactory;

public class IncognitoBrowsing {

    // Requires Headless Chrome
    // https://chromium.googlesource.com/chromium/src/+/lkgr/headless/README.md
    public static void main(String[] args) {
        Launcher launcher = new Launcher();
        launcher.setProcessManager(new AdaptiveProcessManager());

        try (SessionFactory factory = launcher.launch(asList("--headless", "--disable-gpu"))) {

            String firstContext = null;

            try (Session firstSession = factory.create()) {
                firstContext = firstSession.getBrowserContextId();
                firstSession.navigateAndWait("https://httpbin.org/cookies/set?SESSION_ID=1");
                firstSession.waitDocumentReady();
                String session1Content = (String) firstSession.evaluate("window.document.body.textContent");
                System.err.println(session1Content);
            }

            // firstSession & anotherSession share same SESSSION_ID value

            try (Session anotherSession = factory.create(firstContext)) {
                anotherSession.navigateAndWait("https://httpbin.org/cookies");
                anotherSession.waitDocumentReady();
                String anotherSessionContent = (String) anotherSession.evaluate("window.document.body.textContent");
                System.err.println(anotherSessionContent);
            }

            String  secondContext = factory.createBrowserContext();
            try (Session secondSession = factory.create(secondContext)) {
                secondSession.navigateAndWait("https://httpbin.org/cookies");
                secondSession.waitDocumentReady();
                String session2Content = (String) secondSession.evaluate("window.document.body.textContent");
                System.err.println(session2Content); 
            }

            // Dispose first context
            factory.disposeBrowserContext(firstContext);

            // Dispose second context
            factory.disposeBrowserContext(secondContext);

            launcher.getProcessManager().kill();
        }
    }
}
