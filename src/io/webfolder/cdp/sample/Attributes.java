package io.webfolder.cdp.sample;

import java.net.URL;

import io.webfolder.cdp.Launcher;
import io.webfolder.cdp.session.Session;
import io.webfolder.cdp.session.SessionFactory;

public class Attributes {

    public static void main(String[] args) {
        URL url = Attributes.class.getResource("/attributes.html");

        Launcher launcher = new Launcher();

        try (SessionFactory factory = launcher.launch();
                            Session session = factory.create()) {
            session.navigate(url.toString());
            session.waitDocumentReady();
            String value = session.getAttribute("input", "value");
            System.out.println("Old Value: " + value);
            session.setAttribute("input", "value", "bar");
            value = session.getAttribute("input", "value");
            System.out.println("New Value: " + value);
        } finally {
            launcher.kill();
        }
    }
}
