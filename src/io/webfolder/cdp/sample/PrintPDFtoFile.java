package io.webfolder.cdp.sample;

import static java.awt.Desktop.getDesktop;
import static java.awt.Desktop.isDesktopSupported;
import static java.nio.file.Files.createTempFile;

import java.io.IOException;
import java.nio.file.Path;

import io.webfolder.cdp.Launcher;
import io.webfolder.cdp.Options;
import io.webfolder.cdp.session.Session;
import io.webfolder.cdp.session.SessionFactory;

public class PrintPDFtoFile {

    public static void main(String[] args) throws IOException {
        Path file = createTempFile("cdp4j", ".pdf");

        int timeout = 60_000; // 60 seconds

        Options options = Options.builder()
                                    .headless(true)
                                    .readTimeout(timeout)
                                .build();

        Launcher launcher = new Launcher(options);

        try (SessionFactory factory = launcher.launch()) {

            String context = factory.createBrowserContext();
            try (Session session = factory.create(context)) {

                session.navigate("https://docs.jboss.org/resteasy/docs/4.0.0.Final/userguide/html_single/index.html");
                session.waitDocumentReady(timeout);
                session.printToPDF(file);
            }

            factory.disposeBrowserContext(context);
        }

        if (isDesktopSupported()) {
            getDesktop().open(file.toFile());
        }

        launcher.kill();
    }
}
