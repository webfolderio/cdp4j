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
