package io.webfolder.cdp.sample;

import static io.webfolder.cdp.event.Events.NetworkRequestIntercepted;
import static io.webfolder.cdp.type.network.ResourceType.Image;
import static java.util.Arrays.asList;

import io.webfolder.cdp.Launcher;
import io.webfolder.cdp.command.Network;
import io.webfolder.cdp.event.network.RequestIntercepted;
import io.webfolder.cdp.session.Session;
import io.webfolder.cdp.session.SessionFactory;
import io.webfolder.cdp.type.network.RequestPattern;

public class URLFiltering {

    public static void main(String[] args) {
        Launcher launcher = new Launcher();

        try (SessionFactory factory = launcher.launch()) {
            try (Session session = factory.create()) {
                
                Network network = session.getCommand().getNetwork();
                network.enable();
                RequestPattern pattern = new RequestPattern();
                pattern.setUrlPattern("*");
                // intercept images
                pattern.setResourceType(Image);

                network.setRequestInterception(asList(pattern));

                session.addEventListener((e, v) -> {                    
                    if (NetworkRequestIntercepted.equals(e)) {
                        RequestIntercepted ri = (RequestIntercepted) v;
                        String url = ri.getRequest().getUrl();
                        // do not allow to load jpg files
                        if ( ! url.endsWith(".jpg") ) {
                            network.continueInterceptedRequest(ri.getInterceptionId());
                        }
                    }
                });

                session.navigate("https://cnn.com");
                session.waitDocumentReady(60_000);
            }
        } finally {
            launcher.kill();
        }
    }
}
