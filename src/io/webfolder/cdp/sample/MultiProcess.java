package io.webfolder.cdp.sample;

import static java.lang.System.getProperty;
import static java.nio.file.Paths.get;

import java.io.IOException;
import java.net.ServerSocket;
import java.nio.file.Path;
import java.util.Random;

import io.webfolder.cdp.Launcher;
import io.webfolder.cdp.Options;
import io.webfolder.cdp.session.Session;
import io.webfolder.cdp.session.SessionFactory;

public class MultiProcess {

    public static void main(String[] args) throws InterruptedException {
        new Thread() {

            public void run() {
                Path remoteProfileData = get(getProperty("java.io.tmpdir")).resolve("remote-profile-" + new Random().nextInt());
                Options options = Options.builder().userDataDir(remoteProfileData).build();
                Launcher launcher = new Launcher(options);

                SessionFactory factory = launcher.launch();

                try (SessionFactory sf = factory) {
                    try (Session session = sf.create()) {
                        session.navigate("https://webfolder.io");
                        session.waitDocumentReady();
                        System.err.println("Content Length: " + session.getContent().length());
                    }
                }
            }
        }.start();

        new Thread() {

            public void run() {
                Path remoteProfileData = get(getProperty("java.io.tmpdir")).resolve("remote-profile-" + new Random().nextInt());
                Options options = Options.builder().userDataDir(remoteProfileData).build();
                Launcher launcher = new Launcher(options);

                SessionFactory factory = launcher.launch();

                try (SessionFactory sf = factory) {
                    try (Session session = sf.create()) {
                        session.navigate("https://webfolder.io");
                        session.waitDocumentReady();
                        System.err.println("Content Length: " + session.getContent().length());
                    }
                }
            }
        }.start();
    }

    protected static int getFreePort(int portNumber) {
        try (ServerSocket socket = new ServerSocket(portNumber)) {
            int freePort = socket.getLocalPort();
            return freePort;
        } catch (IOException e) {
            return getFreePort(portNumber + 1);
        }
    }
}
