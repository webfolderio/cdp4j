package io.webfolder.cdp.sample;

import java.net.URL;

import io.webfolder.cdp.Launcher;
import io.webfolder.cdp.event.Events;
import io.webfolder.cdp.event.page.JavascriptDialogOpening;
import io.webfolder.cdp.listener.EventListener;
import io.webfolder.cdp.session.Session;
import io.webfolder.cdp.session.SessionFactory;
import io.webfolder.cdp.type.page.DialogType;

public class Dialog {

    public static void main(String[] args) {
        Launcher launcher = new Launcher();

        URL url = Attributes.class.getResource("/alert.html");

        try (SessionFactory factory = launcher.launch();
                            Session session = factory.create()) {
            session.navigate(url.toString());
            session.waitDocumentReady();
            
            session.addEventListener(new EventListener() {

                @Override
                public void onEvent(Events event, Object value) {
                    if (Events.PageJavascriptDialogOpening.equals(event)) {
                        JavascriptDialogOpening jdo = (JavascriptDialogOpening) value;
                        if (DialogType.Beforeunload.equals(jdo.getType())) {
                            session.getCommand()
                                .getPage()
                                .handleJavaScriptDialog(true);
                        }
                    }
                }
            });

            session.click("a");
            session.waitDocumentReady();
            // This must print google.com
            System.out.println(session.getLocation());
        } finally {
            launcher.kill();
        }
    }
}
