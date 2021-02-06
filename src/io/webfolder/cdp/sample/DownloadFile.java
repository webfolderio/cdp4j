package io.webfolder.cdp.sample;

import static io.webfolder.cdp.type.constant.DownloadBehavior.Allow;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import io.webfolder.cdp.Launcher;
import io.webfolder.cdp.command.Page;
import io.webfolder.cdp.session.Session;
import io.webfolder.cdp.session.SessionFactory;

public class DownloadFile {

    public static void main(String[] args) throws IOException {
        Launcher launcher = new Launcher();

        try (SessionFactory factory = launcher.launch();
                            Session session = factory.create()) {
            session.navigate("https://www.chiark.greenend.org.uk/~sgtatham/putty/latest.html");
            session.waitDocumentReady();
            Page page = session.getCommand().getPage();
            Path tempDir = Files.createTempDirectory("download").toAbsolutePath();
            page.setDownloadBehavior(Allow, tempDir.toString());
            // link must be visible before downloading the file
            session.evaluate("document.querySelector(\"code\").scrollIntoView()");
            // click the download link
            session.click("code");
            // There is an "Event.PageDownloadWillBegin" to detect if download is begin.
            // Unfortunately DevTools protocol does not come with DownloadEnd event.
            // It might better use a directory listener to detect if download is finished or not
            session.wait(10000);
        } finally {
            launcher.kill();
        }
    }
}
