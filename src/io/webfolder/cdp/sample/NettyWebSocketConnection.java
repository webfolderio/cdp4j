package io.webfolder.cdp.sample;

import io.webfolder.cdp.Launcher;
import io.webfolder.cdp.channel.AsyncWebSocketFactory;
import io.webfolder.cdp.session.Session;
import io.webfolder.cdp.session.SessionFactory;

public class NettyWebSocketConnection {

    public static void main(String[] args) {
        AsyncWebSocketFactory asyncWebSocketFactory = new AsyncWebSocketFactory();

        Launcher launcher = new Launcher(asyncWebSocketFactory);

        try (SessionFactory factory = launcher.launch();
                            Session session = factory.create()) {
            session.navigate("https://webfolder.io?cdp4j");
            session.waitDocumentReady();
            String content = session.getContent();
            System.out.println(content);
        } finally {
            asyncWebSocketFactory.close();
            launcher.kill();
        }
    }
}
