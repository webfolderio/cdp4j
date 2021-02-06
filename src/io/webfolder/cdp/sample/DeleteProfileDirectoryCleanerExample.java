package io.webfolder.cdp.sample;

import static io.webfolder.cdp.logger.CdpConsoleLogggerLevel.Info;
import static io.webfolder.cdp.logger.CdpLoggerType.Console;
import static java.nio.file.Files.exists;

import java.io.IOException;

import io.webfolder.cdp.Launcher;
import io.webfolder.cdp.Options;
import io.webfolder.cdp.session.Session;
import io.webfolder.cdp.session.SessionFactory;

public class DeleteProfileDirectoryCleanerExample {

	/**
	 * This example creates a new user profile directory and
	 * deletes the files after the browser is killed or SessionFactory is closed
	 * cdp4j uses Java's default temp directory to store user profile files.
	 * If you need a custom cleaner, copy and modify the UserProfileDirectoryCleaner
	 */
    public static void main(String[] args) throws IOException {
        Options options = Options.builder()
                                    .createNewUserDataDir(true) // <- set this value to true
                                    .loggerType(Console)
                                    .consoleLoggerLevel(Info)
                                .build();

        Launcher launcher = new Launcher(options);

        try (SessionFactory factory = launcher.launch();
                            Session session = factory.create()) {
            session.navigate("https://bing.com");
            session.waitDocumentReady();
            session.getContent();
        } finally {
            launcher.kill();
        }

        // make sure that the user profile directory not exist
        if (exists(options.userDataDir())) {
            throw new IllegalArgumentException();
        }
    }
}
