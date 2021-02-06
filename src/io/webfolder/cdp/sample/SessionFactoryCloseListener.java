package io.webfolder.cdp.sample;

import io.webfolder.cdp.Launcher;
import io.webfolder.cdp.session.CloseListener;
import io.webfolder.cdp.session.Session;
import io.webfolder.cdp.session.SessionFactory;

public class SessionFactoryCloseListener {

    public static void main(String[] args) {
        Launcher launcher = new Launcher();

        try (SessionFactory factory = launcher.launch();
                            Session session = factory.create()) {

            // this event listener triggered when
            // SessionFactory is closed or Launcher.kill() method is called
            factory.addCloseListener(new CloseListener() {

                @Override
                public void closed() {
                    System.out.println("SessionFactory closed");
                }
            });

            // this event listener triggered when
            // session destroyed, detached or crashed
            session.addCloseListener(new CloseListener() {

                @Override
                public void closed() {
                    System.out.println("session closed");
                }
            });

            session.navigate("https://bing.com");
            session.waitDocumentReady();
        } finally {
            launcher.kill();
        }
    }
}
