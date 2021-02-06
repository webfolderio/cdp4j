package io.webfolder.cdp.sample;

import static java.util.Arrays.asList;

import java.net.URL;

import io.webfolder.cdp.Launcher;
import io.webfolder.cdp.session.Session;
import io.webfolder.cdp.session.SessionFactory;

public class MultiSelect {

    public static void main(String[] args) {
        URL url = MultiSelect.class.getResource("/multi-select.html");

        Launcher launcher = new Launcher();

        try (SessionFactory factory = launcher.launch();
                            Session session = factory.create()) {
            session.navigate(url.toString());
            session.waitDocumentReady();

            System.out.println("Selected options:");
            session.getOptions("select").stream().forEach(o -> {
                if (o.isSelected()) {
                    System.out.println(o);
                }
            });

            session.wait(1000);
            session.clearOptions("select");

            System.out.println("Selected Option count: " +
            session.getOptions("select").stream().filter(o -> o.isSelected()).count());

            session.wait(1000);
            session.setSelectedOptions("select", asList(0, 1, 2));
            System.out.println("Selected options:");
            session.getOptions("select").stream().forEach(o -> {
                if (o.isSelected()) {
                    System.out.println(o);
                }
            });

            System.out.println("Selected Option count: " +
            session.getOptions("select").stream().filter(o -> o.isSelected()).count());

            session.wait(1000);
        } finally {
            launcher.kill();
        }
    }
}
