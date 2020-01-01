/**
 * cdp4j Commercial License
 *
 * Copyright 2017, 2020 WebFolder OÜ
 *
 * Permission  is hereby  granted,  to "____" obtaining  a  copy of  this software  and
 * associated  documentation files  (the "Software"), to deal in  the Software  without
 * restriction, including without limitation  the rights  to use, copy, modify,  merge,
 * publish, distribute  and sublicense  of the Software,  and to permit persons to whom
 * the Software is furnished to do so, subject to the following conditions:
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR  IMPLIED,
 * INCLUDING  BUT NOT  LIMITED  TO THE  WARRANTIES  OF  MERCHANTABILITY, FITNESS  FOR A
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL  THE AUTHORS  OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
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
