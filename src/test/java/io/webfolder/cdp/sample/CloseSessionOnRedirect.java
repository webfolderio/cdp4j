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
        try (Launcher launcher = new Launcher();
                            SessionFactory factory = launcher.launch();
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

                        System.out.println("");
                        System.out.println("Redirect URL         : " + rws.getRequest().getUrl());
                        System.out.println("Redirect Status Code : " + rr.getStatus());
                        System.out.println("Redirect Header      : " + rws.getRedirectResponse().getHeaders());
                        System.out.println("");
                    }
                }
            });

            session.getCommand().getPage().navigate("https://httpbin.org/redirect-to?url=https://webfolder.io?cdp4j");

            if ( ! terminateSession ) {
                session.waitDocumentReady();
            }
        }
    }
}
