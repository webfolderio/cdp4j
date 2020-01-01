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
package io.webfolder.cdp.sample;

import java.util.concurrent.CountDownLatch;

import io.webfolder.cdp.Launcher;
import io.webfolder.cdp.event.Events;
import io.webfolder.cdp.event.page.WindowOpen;
import io.webfolder.cdp.session.Session;
import io.webfolder.cdp.session.SessionFactory;
import io.webfolder.cdp.type.target.TargetInfo;

public class SwitchTab {

    public static void main(String[] args) throws Exception {
        Launcher launcher = new Launcher();

        try (SessionFactory factory = launcher.launch()) {
            TargetInfo found = null;

            try (Session session = factory.create()) {
                session.navigate("data:text/html, <a href=\"https://www.google.com/\" target=\"blank\">Google</a>");
                session.waitDocumentReady();
                
                CountDownLatch latch = new CountDownLatch(1);

                StringBuilder url = new StringBuilder();
                
                session.addEventListener((event, value) -> {
                    if (Events.PageWindowOpen.equals(event)) {
                        WindowOpen wo = (WindowOpen) value;
                        url.append(wo.getUrl());
                        latch.countDown();
                    }
                });

                session.click("a");
                // wait the new page
                latch.await();

                for (TargetInfo next : session.getCommand().getTarget().getTargets()) {
                    System.out.println(next.getUrl());
                    if (next.getUrl().equals(url.toString())) {
                        found = next;
                    }
                }
            }

            if (found != null) {
                try (Session session = factory.connect(found.getTargetId())) {
                    session.navigate("https://bing.com");
                }
            }
        } finally {
            launcher.kill();
        }
    }
}
