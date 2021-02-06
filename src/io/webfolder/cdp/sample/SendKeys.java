package io.webfolder.cdp.sample;

import io.webfolder.cdp.Launcher;
import io.webfolder.cdp.session.Session;
import io.webfolder.cdp.session.SessionFactory;

public class SendKeys {

    public static void main(String[] args) {
        Launcher launcher = new Launcher();
        
        try (SessionFactory factory = launcher.launch();
                            Session session = factory.create()) {
            session.getCommand().getNetwork().enable();
            session.navigate("https://google.com");
            session.waitDocumentReady();
            session.sendKeys("webfolder.io");
            session.sendEnter();
            session.wait(2000);
        } finally {
            launcher.kill();
        }
    }
}
