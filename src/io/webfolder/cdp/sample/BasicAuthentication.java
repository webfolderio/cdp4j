package io.webfolder.cdp.sample;

import static io.webfolder.cdp.event.Events.NetworkRequestIntercepted;
import static java.util.Arrays.asList;

import java.util.Map;
import java.util.concurrent.CountDownLatch;

import com.google.gson.Gson;

import io.webfolder.cdp.Launcher;
import io.webfolder.cdp.command.Network;
import io.webfolder.cdp.event.network.RequestIntercepted;
import io.webfolder.cdp.session.Session;
import io.webfolder.cdp.session.SessionFactory;
import io.webfolder.cdp.type.constant.AuthResponse;
import io.webfolder.cdp.type.network.AuthChallengeResponse;
import io.webfolder.cdp.type.network.RequestPattern;
import io.webfolder.cdp.type.network.ResourceType;

public class BasicAuthentication {

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {

        Launcher launcher = new Launcher();

        try (SessionFactory factory = launcher.launch();
                            Session session = factory.create()) {

            Network network = session.getCommand().getNetwork();
            network.enable();
            RequestPattern pattern = new RequestPattern();
            pattern.setUrlPattern("*");
            pattern.setResourceType(ResourceType.Document);

            network.setRequestInterception(asList(pattern));
            // Disable newtork caching when intercepting
            // https://github.com/GoogleChrome/puppeteer/pull/1154
            network.setCacheDisabled(Boolean.TRUE);

            CountDownLatch latch = new CountDownLatch(1);

            session.addEventListener((e, v) -> {
                
                if (NetworkRequestIntercepted.equals(e)) {
                    RequestIntercepted ri = (RequestIntercepted) v;
                    if (ri.getAuthChallenge() != null) {
                        AuthChallengeResponse acr = new AuthChallengeResponse();
                        acr.setUsername("user");
                        acr.setPassword("password");
                        acr.setResponse(AuthResponse.ProvideCredentials);
                        network.continueInterceptedRequest(ri.getInterceptionId(), null,
                                                           null, null,
                                                           null, null,
                                                           null,  acr);
                        latch.countDown();
                    } else {
                        network.continueInterceptedRequest(ri.getInterceptionId());
                    }
                }
            });

            session.navigate("https://httpbin.org/basic-auth/user/password");
            try {
                latch.await();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            session.waitDocumentReady();
            String content = (String) session.evaluate("window.document.body.textContent");
            System.out.println(content);
            Map<String, Object> map = new Gson().fromJson(content, Map.class);
            Object authenticated = map.get("authenticated");
            Object user = map.get("user");

            System.out.println("Authenticated: " + authenticated);
            System.out.println("User:" + user);
        } finally {
            launcher.kill();
        }
    }
}
