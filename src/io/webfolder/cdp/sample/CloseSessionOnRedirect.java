package io.webfolder.cdp.sample;

import static io.webfolder.cdp.event.Events.NetworkRequestWillBeSent;

import io.webfolder.cdp.Launcher;
import io.webfolder.cdp.command.Network;
import io.webfolder.cdp.event.network.RequestWillBeSent;
import io.webfolder.cdp.session.Session;
import io.webfolder.cdp.session.SessionFactory;
import io.webfolder.cdp.type.network.Response;

public class CloseSessionOnRedirect {

    private static boolean terminateSession;

    public static void main(String[] args) {
        Launcher launcher = new Launcher();

        try (SessionFactory factory = launcher.launch();
                            Session session = factory.create()) {

            Network network = session.getCommand().getNetwork();
            network.enable();

            session.addEventListener((e, d) -> {
                if (NetworkRequestWillBeSent.equals(e)) {
                    RequestWillBeSent rws = (RequestWillBeSent) d;
                    Response rr = rws.getRedirectResponse();

                    boolean isRedirect = rr != null && rr.getStatus() != null;

                    if (isRedirect) {
                        terminateSession = true;
                        session.stop();
                        session.close();

                        System.out.println("------------------------------------------------------------------------");
                        System.out.println("Redirect URL          : " + rws.getRequest().getUrl());
                        System.out.println("Redirect URL Fragment : " + rws.getRequest().getUrlFragment());
                        System.out.println("Redirect Status Code  : " + rr.getStatus());
                        System.out.println("Redirect Header       : " + rws.getRedirectResponse().getHeaders());
                        System.out.println("------------------------------------------------------------------------");
                    }
                }
            });

            session.navigate("https://httpbin.org/redirect-to?url=https://webfolder.io?cdp4j#test-url-fragment");

            if ( ! terminateSession ) {
                session.waitDocumentReady();
            }
        } finally {
            launcher.kill();
        }
    }
}
