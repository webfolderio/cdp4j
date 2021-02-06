package io.webfolder.cdp.sample;

import io.webfolder.cdp.Launcher;
import io.webfolder.cdp.channel.UndertowWebSocketFactory;
import io.webfolder.cdp.session.Session;
import io.webfolder.cdp.session.SessionFactory;

public class UndertowWebSocketConnection {

    public static void main(String[] args) {
        UndertowWebSocketFactory undertowWebSocketFactory = new UndertowWebSocketFactory();

        Launcher launcher = new Launcher(undertowWebSocketFactory);

        try (SessionFactory factory = launcher.launch();
                            Session session = factory.create()) {
            session.navigate("https://webfolder.io?cdp4j");
            session.waitDocumentReady();
            String content = session.getContent();
            System.out.println(content);
        } finally {
            undertowWebSocketFactory.close();
            launcher.kill();
        }
    }
}
