package io.webfolder.cdp.sample;

import java.net.URL;
import java.util.Optional;

import io.webfolder.cdp.Launcher;
import io.webfolder.cdp.session.Option;
import io.webfolder.cdp.session.Session;
import io.webfolder.cdp.session.SessionFactory;

public class Select {

    public static void main(String[] args) {
        URL url = Select.class.getResource("/select.html");

        Launcher launcher = new Launcher();

        try (SessionFactory factory = launcher.launch();
                            Session session = factory.create()) {
            session.navigate(url.toString());
            session.waitDocumentReady();
            int selectedIndex = session.getSelectedIndex("select");
            System.out.println("Selected  index : " + selectedIndex);
            System.out.println("options         : " + session.getOptions("select"));
            session.wait(2000);
            session.setSelectedIndex("select", 2);
            session.wait(2000);
            selectedIndex = session.getSelectedIndex("select");
            System.out.println("Selected  index : " + selectedIndex);
            Optional<Option> selected = session.getOptions("select").stream().filter(o -> o.isSelected()).findFirst();
            System.out.println("Selected        : " + selected.get().getText());
        } finally {
            launcher.kill();
        }

    }
}
