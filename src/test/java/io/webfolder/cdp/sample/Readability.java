package io.webfolder.cdp.sample;

import static io.webfolder.cdp.session.WaitUntil.DomReady;
import static java.nio.file.Files.readAllBytes;
import static java.nio.file.Paths.get;

import io.webfolder.cdp.Launcher;
import io.webfolder.cdp.command.Page;
import io.webfolder.cdp.logger.CdpLoggerType;
import io.webfolder.cdp.session.Session;
import io.webfolder.cdp.session.SessionFactory;

// Remove noise and ads before extracting the content
// @see https://github.com/mozilla/readability
public class Readability {

    public static void main(String[] args) throws Exception {
        try (Launcher launcher = new Launcher(CdpLoggerType.Null);
                            SessionFactory factory = launcher.launch();
                            Session session = factory.create()) {

            Page page = session.getCommand().getPage();
            // enable Page domain before using the addScriptToEvaluateOnNewDocument()
            page.enable();

            // addScriptToEvaluateOnNewDocument() must be called before Session.navigate()
            page.addScriptToEvaluateOnNewDocument(new String(readAllBytes(get("src/test/resources/readability.js"))));

            session.navigateAndWait("https://www.theregister.co.uk/2018/07/27/lower_saxony_to_dump_linux", DomReady);
   
            String content = (String) session.evaluate("new Readability(window.document).parse().content");
            System.out.println(content);            
        }
    }
}
