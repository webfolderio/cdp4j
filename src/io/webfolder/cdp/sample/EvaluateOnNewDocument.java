package io.webfolder.cdp.sample;

import static io.webfolder.cdp.logger.CdpLoggerType.Console;
import static io.webfolder.cdp.logger.CdpConsoleLogggerLevel.Info;

import java.net.URL;

import io.webfolder.cdp.Launcher;
import io.webfolder.cdp.Options;
import io.webfolder.cdp.command.Page;
import io.webfolder.cdp.session.Session;
import io.webfolder.cdp.session.SessionFactory;

public class EvaluateOnNewDocument {

    public static void main(String[] args) {
        Launcher launcher = new Launcher(Options.builder()
                                            .consoleLoggerLevel(Info)
                                            .loggerType(Console)
                                           .build());

        URL url = Select.class.getResource("/inject-script.html");

        try (SessionFactory factory = launcher.launch();
                            Session session = factory.create()) {

            Page page = session.getCommand().getPage();

            // addScriptToEvaluateOnNewDocument() must be called before Session.navigate()
            page.addScriptToEvaluateOnNewDocument("window.dummyMessage = 'hello, world!'");

            session.enableConsoleLog();

            session.navigate(url.toString());

            session.wait(1000);
        } finally {
            launcher.kill();
        }
    }
}
