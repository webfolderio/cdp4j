package io.webfolder.cdp.sample;

import io.webfolder.cdp.Launcher;
import io.webfolder.cdp.session.Option;
import io.webfolder.cdp.session.Session;
import io.webfolder.cdp.session.SessionFactory;

public class BingTranslator {

    public static void main(String[] args) {
        Launcher launcher = new Launcher();

        try (SessionFactory factory = launcher.launch();
                            Session session = factory.create()) {
            session
                .navigate("https://www.bing.com/translator")
                .waitDocumentReady()
                .enableConsoleLog()
                .enableDetailLog()
                .enableNetworkLog();

            Option en = session
                        .getOptions("#tta_srcsl")
                        .stream()
                        .filter(p -> "en".equals(p.getValue()))
                        .findFirst()
                        .get();
            
            Option et = session
                        .getOptions("#tta_tgtsl")
                        .stream()
                        .filter(p -> "et".equals(p.getValue()))
                        .findFirst()
                        .get();

            session
                .click("#tta_srcsl") // click source language
                .wait(500)
                .setSelectedIndex("#tta_srcsl", en.getIndex()) // choose English
                .wait(500)
                .click("#tta_tgtsl") // click destination language
                .wait(500)
                .setSelectedIndex("#tta_tgtsl", et.getIndex()) // choose Estonian
                .wait(500);

            session.focus("#tta_input_ta")
                    .wait(100)
                    .sendKeys("hello world")
                    .wait(1000);

            System.out.println(session.getValue("#tta_output_ta"));
        } finally {
            launcher.kill();
        }
    }
}
