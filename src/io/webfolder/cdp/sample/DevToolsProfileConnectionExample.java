package io.webfolder.cdp.sample;

import java.nio.file.Path;

import io.webfolder.cdp.Launcher;
import io.webfolder.cdp.Options;
import io.webfolder.cdp.channel.ChannelFactory;
import io.webfolder.cdp.channel.DevToolsProfileConnection;
import io.webfolder.cdp.channel.JreWebSocketFactory;
import io.webfolder.cdp.session.Session;
import io.webfolder.cdp.session.SessionFactory;

// This example is similar to DevToolsConnectionExample except that we don't need to know remote Debugging Port directly.
// If you don't specify the remote Debugging Port, Chrome will use random free port.
// Chrome writes the free port and websocket debugger to DevToolsActivePort file in the user profile directory.
// If you know the user profile directory, you could re-connect to existing DevTools server.
public class DevToolsProfileConnectionExample {

    public static void main(String[] args) throws InterruptedException {
        Launcher launcher = new Launcher();

        SessionFactory dummySessionFactory = launcher.launch();

        // --------------------------------------------------------------------
        // connect an existing devtools server
        // --------------------------------------------------------------------

        Path userProfileDirectory = launcher.getUserDataDir();
        DevToolsProfileConnection connection = new DevToolsProfileConnection(userProfileDirectory);

        // check the connection if it's valid (optional)
        if ( ! connection.isValid() ) {
            throw new IllegalStateException();
        }
        
        Options options = Options.builder().build();

        ChannelFactory webSocketFactory = new JreWebSocketFactory();

        SessionFactory factory = new SessionFactory(options, webSocketFactory, connection);

        Session session = factory.create();
        session.navigate("https://google.com");

        // --------------------------------------------------------------------

        Thread.sleep(5_000);

        dummySessionFactory.close();

        factory.close();

        launcher.kill();
    }
}
