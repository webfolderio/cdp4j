package io.webfolder.cdp.sample;

import static io.webfolder.cdp.Browser.MicrosoftEdge;

import java.io.IOException;

import io.webfolder.cdp.Launcher;
import io.webfolder.cdp.Options;
import io.webfolder.cdp.session.Session;
import io.webfolder.cdp.session.SessionFactory;

public class MicrosoftEdge {

    public static void main(String[] args) throws IOException {
        Options options = Options.builder()
                                 .browser(MicrosoftEdge)
                                 .build();

        Launcher launcher = new Launcher(options);

        try (SessionFactory factory = launcher.launch();
                            Session session = factory.create()) {
            session.navigate("https://webfolder.io?cdp4j");
            session.waitDocumentReady();
            String content = session.getContent();
            System.out.println(content);
        } finally {
            launcher.kill();
        }
    }
}
