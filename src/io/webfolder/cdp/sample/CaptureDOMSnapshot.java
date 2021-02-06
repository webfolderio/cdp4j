package io.webfolder.cdp.sample;

import io.webfolder.cdp.Launcher;
import io.webfolder.cdp.session.Session;
import io.webfolder.cdp.session.SessionFactory;

public class CaptureDOMSnapshot {

    public static void main(String[] args) {
        Launcher launcher = new Launcher();

        try (SessionFactory factory = launcher.launch();
                            Session session = factory.create()) {
            session.navigate("http://webfolder.io?cdp4j");
            session.waitDocumentReady();
            // Returns a document snapshot, including the full DOM tree of the root node (including iframes,
            // template contents, and imported documents).
            String snapshot = session.getDOMSnapshot();
            System.out.println(snapshot);
        } finally {
            launcher.kill();
        }
    }
}
