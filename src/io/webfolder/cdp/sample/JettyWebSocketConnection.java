package io.webfolder.cdp.sample;

import io.webfolder.cdp.Launcher;
import io.webfolder.cdp.channel.JettyWebSocketFactory;
import io.webfolder.cdp.session.Session;
import io.webfolder.cdp.session.SessionFactory;

public class JettyWebSocketConnection {

    public static void main(String[] args) {
        JettyWebSocketFactory jettyWebSocketFactory = new JettyWebSocketFactory();

        Launcher launcher = new Launcher(jettyWebSocketFactory);

        try (SessionFactory factory = launcher.launch();
                            Session session = factory.create()) {
            session.navigate("https://webfolder.io?cdp4j");
            session.waitDocumentReady();
            String content = session.getContent();
            System.out.println(content);
        } finally {
            jettyWebSocketFactory.close();
            launcher.kill();
        }
    }
}
