package io.webfolder.cdp.sample;

import java.net.URL;

import io.webfolder.cdp.Launcher;
import io.webfolder.cdp.session.Session;
import io.webfolder.cdp.session.SessionFactory;

public class CheckBox {

    public static void main(String[] args) {
        Launcher launcher = new Launcher();

        URL url = CheckBox.class.getResource("/checkbox.html");

        try (SessionFactory factory = launcher.launch();
                            Session session = factory.create()) {

            session.navigate(url.toString());
            session.waitDocumentReady();
            System.out.println("Checked: " + session.isChecked("input[name='red']"));
            session.setChecked("input[name='red']", true);
            System.out.println("Checked: " + session.isChecked("input[name='red']"));
            session.wait(1000);
            session.setChecked("input[name='red']", false);
            System.out.println("Checked: " + session.isChecked("input[name='red']"));
            session.wait(1000);
        } finally {
            launcher.kill();
        }
    }
}
