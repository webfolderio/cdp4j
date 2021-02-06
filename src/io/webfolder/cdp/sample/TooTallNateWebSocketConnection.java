package io.webfolder.cdp.sample;

import io.webfolder.cdp.Launcher;
import io.webfolder.cdp.channel.TooTallNateWebSocketFactory;
import io.webfolder.cdp.session.Session;
import io.webfolder.cdp.session.SessionFactory;

public class TooTallNateWebSocketConnection {

    public static void main(String[] args) {
        TooTallNateWebSocketFactory tooTallNateWebSocketFactory = new TooTallNateWebSocketFactory();

        Launcher launcher = new Launcher(tooTallNateWebSocketFactory);

        try (SessionFactory factory = launcher.launch();
                            Session session = factory.create()) {
            session.navigate("https://webfolder.io?cdp4j");
            session.waitDocumentReady();
            String content = session.getContent();
            System.out.println(content);
        } finally {
            tooTallNateWebSocketFactory.close();
            launcher.kill();
        }
    }
}
