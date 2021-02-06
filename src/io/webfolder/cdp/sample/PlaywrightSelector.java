package io.webfolder.cdp.sample;

import static io.webfolder.cdp.SelectorEngine.Playwright;

import io.webfolder.cdp.Launcher;
import io.webfolder.cdp.Options;
import io.webfolder.cdp.session.Session;
import io.webfolder.cdp.session.SessionFactory;

public class PlaywrightSelector {

    public static void main(String[] args) throws InterruptedException {
        Launcher launcher = new Launcher(Options.builder()
                                                // Use Playwright selector instead of native
                                                // See: https://playwright.dev/#version=v1.2.1&path=docs%2Fselectors.md
                                                .selectorEngine(Playwright)
                                            .build());

        try (SessionFactory factory = launcher.launch();
                            Session session = factory.create()) {
            session.navigate("https://news.ycombinator.com");
            session.waitDocumentReady();
            // use the text selector
            session.click("text=submit");
            session.waitDocumentReady();
            // combine the text selector and the xpath selector
            session.setValue("text=username >> xpath=following-sibling::*[1]/input", "foobar123456789");
            session.setValue("text=password >> xpath=following-sibling::*[1]/input", "bar");
            // use the css selector
            session.click("css=[value=login]");
            session.waitDocumentReady();
            session.wait(2_000);
        } finally {
            launcher.kill();
        }
    }
}
