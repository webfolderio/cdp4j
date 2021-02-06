package io.webfolder.cdp.sample;

import io.webfolder.cdp.Launcher;
import io.webfolder.cdp.channel.NvWebSocketFactory;
import io.webfolder.cdp.session.Session;
import io.webfolder.cdp.session.SessionFactory;

public class NvWebSocketConnection {

    public static void main(String[] args) {
        NvWebSocketFactory nvWebSocketFactory = new NvWebSocketFactory();

        Launcher launcher = new Launcher(nvWebSocketFactory);

        try (SessionFactory factory = launcher.launch();
                            Session session = factory.create()) {
            session.navigate("https://webfolder.io?cdp4j");
            session.waitDocumentReady();
            String content = session.getContent();
            System.out.println(content);
        } finally {
            nvWebSocketFactory.close();
            launcher.kill();
        }
    }
}
