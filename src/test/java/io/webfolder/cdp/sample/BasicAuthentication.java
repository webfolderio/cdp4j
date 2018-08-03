/**
 * cdp4j - Chrome DevTools Protocol for Java
 * Copyright © 2017, 2018 WebFolder OÜ (support@webfolder.io)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package io.webfolder.cdp.sample;

import static io.webfolder.cdp.event.Events.NetworkRequestIntercepted;
import static java.util.Arrays.asList;

import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.webfolder.cdp.Launcher;
import io.webfolder.cdp.command.Network;
import io.webfolder.cdp.event.network.RequestIntercepted;
import io.webfolder.cdp.session.Session;
import io.webfolder.cdp.session.SessionFactory;
import io.webfolder.cdp.type.constant.AuthResponse;
import io.webfolder.cdp.type.network.AuthChallengeResponse;
import io.webfolder.cdp.type.network.RequestPattern;
import io.webfolder.cdp.type.page.ResourceType;

public class BasicAuthentication {

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws IOException {
        try (Launcher launcher = new Launcher();
                            SessionFactory factory = launcher.launch();
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
                    } else {
                        network.continueInterceptedRequest(ri.getInterceptionId());
                    }
                }
            });

            session.navigateAndWait("https://httpbin.org/basic-auth/user/password");
            session.wait(1000);

            String content = (String) session.evaluate("window.document.body.textContent");
            System.out.println(content);
            Map<String, Object> map = new ObjectMapper().readValue(content, Map.class);
            Object authenticated = map.get("authenticated");
            Object user = map.get("user");

            System.out.println("Authenticated: " + authenticated);
            System.out.println("User:" + user);
        }

    }
}
