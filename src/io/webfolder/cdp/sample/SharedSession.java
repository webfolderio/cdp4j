package io.webfolder.cdp.sample;

import io.webfolder.cdp.Launcher;
import io.webfolder.cdp.Options;
import io.webfolder.cdp.logger.CdpLoggerType;
import io.webfolder.cdp.session.Session;
import io.webfolder.cdp.session.SessionFactory;
import io.webfolder.cdp.session.WaitUntil;

public class SharedSession {

    public static void main(String[] args) {
        Options options = Options.builder().loggerType(CdpLoggerType.Slf4j).build();

        Launcher launcher = new Launcher(options);

        try (SessionFactory factory = launcher.launch();
                            Session session = factory.create()) {

            try (Session firstSession = factory.create()) {
                firstSession.navigateAndWait("https://httpbin.org/cookies/set?SESSION_ID=1", WaitUntil.NetworkIdle);
                String session1 = (String) firstSession.evaluate("window.document.body.textContent");
                System.out.println(session1);
            }

            try (Session secondSession = factory.create()) {
                secondSession.navigateAndWait("https://httpbin.org/cookies", WaitUntil.NetworkIdle);
                String session2 = (String) secondSession.evaluate("window.document.body.textContent");
                System.out.println(session2);
            }
        } finally {
            launcher.kill();
        }
    }
}
