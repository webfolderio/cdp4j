package io.webfolder.cdp.sample;

import io.webfolder.cdp.Launcher;
import io.webfolder.cdp.Options;
import io.webfolder.cdp.channel.ChannelFactory;
import io.webfolder.cdp.channel.DevToolsConnection;
import io.webfolder.cdp.channel.JreWebSocketFactory;
import io.webfolder.cdp.session.Session;
import io.webfolder.cdp.session.SessionFactory;

// For some scenario you might need to connect an existing Chrome which is launched without cdp4j (e.g. Selenium etc...)
// If you need to connect an existing DevTools server you could use DevToolsConnection
public class DevToolsConnectionExample {

    public static void main(String[] args) throws InterruptedException {
        int remoteDebuggingPort = 9222;
        
        Options options1 = Options.builder()
                                .remoteDebuggingPort(remoteDebuggingPort)
                            .build();

        Launcher launcher = new Launcher(options1);
        SessionFactory dummySessionFactory = launcher.launch();

        // --------------------------------------------------------------------
        // connect an existing devtools server
        // --------------------------------------------------------------------
        
        Options options2 = Options.builder()
                                .build();

        DevToolsConnection connection = new DevToolsConnection(remoteDebuggingPort);

        // check the connection if it's valid (optional)
        if ( ! connection.isValid() ) {
            throw new IllegalStateException();
        }

        ChannelFactory webSocketFactory = new JreWebSocketFactory();

        SessionFactory factory = new SessionFactory(options2, webSocketFactory, connection);

        Session session = factory.create();

        session.navigate("https://google.com");

        // --------------------------------------------------------------------

        Thread.sleep(5_000);

        factory.close();
        dummySessionFactory.close();

        launcher.kill();
    }
}
