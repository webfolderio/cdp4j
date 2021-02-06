package io.webfolder.cdp.sample;

import static io.webfolder.cdp.logger.CdpLoggerType.Console;
import static io.webfolder.cdp.logger.CdpConsoleLogggerLevel.Info;

import io.webfolder.cdp.Launcher;
import io.webfolder.cdp.Options;
import io.webfolder.cdp.session.Session;
import io.webfolder.cdp.session.SessionFactory;

public class Logging {

    public static void main(String[] args) {
        Launcher launcher = new Launcher(Options.builder()
                                            .consoleLoggerLevel(Info)
                                            .loggerType(Console)
                                           .build());

        try (SessionFactory factory = launcher.launch();
                            Session session = factory.create()) {
            session.navigate("about:blank");
            session.waitDocumentReady();

            // logs javascript messages
            session.enableConsoleLog();

            session.evaluate("console.info('info message')");
            session.evaluate("console.error('error message')");
            session.evaluate("console.warn('warning message')");

            // logs newtwork, violation, security, storage and deprecation messages
            session.enableDetailLog();

            session.evaluate("fetch('https://google.com')");
            session.wait(2000);
        } finally {
            launcher.kill();
        }
    }
}
