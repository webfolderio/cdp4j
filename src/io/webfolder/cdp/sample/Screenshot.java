package io.webfolder.cdp.sample;

import static java.awt.Desktop.getDesktop;
import static java.awt.Desktop.isDesktopSupported;
import static java.nio.file.Files.createTempFile;
import static java.nio.file.Files.write;

import java.io.IOException;
import java.nio.file.Path;

import io.webfolder.cdp.Launcher;
import io.webfolder.cdp.session.Session;
import io.webfolder.cdp.session.SessionFactory;

public class Screenshot {

    public static void main(String[] args) throws IOException, InterruptedException {
        Launcher launcher = new Launcher();

        Path file = createTempFile("screenshot", ".png");

        try (SessionFactory factory = launcher.launch();
                            Session session = factory.create()) {
            session.navigate("https://news.ycombinator.com");
            session.waitDocumentReady();
            // activate the tab/session before capturing the screenshot
            session.activate();
            byte[] data = session.captureScreenshot();
            write(file, data);
        } finally {
            launcher.kill();
        }

        if (isDesktopSupported()) {
            getDesktop().open(file.toFile());
        }
    }
}
