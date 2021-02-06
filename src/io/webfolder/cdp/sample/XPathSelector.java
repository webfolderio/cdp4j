package io.webfolder.cdp.sample;

import io.webfolder.cdp.Launcher;
import io.webfolder.cdp.session.Session;
import io.webfolder.cdp.session.SessionFactory;

public class XPathSelector {

    public static void main(String[] args) {
        Launcher launcher = new Launcher();

        try (SessionFactory factory = launcher.launch();
                            Session session = factory.create()) {
            session
                .navigate("https://webfolder.io")
                .waitDocumentReady();

            String title = session.getText("//title");

            System.out.println(title);
        } finally {
            launcher.kill();
        }
    }
}
