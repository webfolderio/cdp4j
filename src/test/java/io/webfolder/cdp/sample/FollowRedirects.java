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

import static io.webfolder.cdp.event.Events.NetworkRequestIntercepted;
import static io.webfolder.cdp.type.network.ResourceType.Document;
import static java.util.Arrays.asList;

import io.webfolder.cdp.Launcher;
import io.webfolder.cdp.command.Network;
import io.webfolder.cdp.event.network.RequestIntercepted;
import io.webfolder.cdp.session.Session;
import io.webfolder.cdp.session.SessionFactory;
import io.webfolder.cdp.type.network.RequestPattern;

public class FollowRedirects {

    public static void main(String[] args) {
        Launcher launcher = new Launcher();

        try (SessionFactory factory = launcher.launch();
                            Session session = factory.create()) {

            Network network = session.getCommand().getNetwork();
            network.enable();
            // Disable newtork caching when intercepting
            // https://github.com/GoogleChrome/puppeteer/pull/1154
            network.setCacheDisabled(Boolean.TRUE);

            RequestPattern pattern = new RequestPattern();
            pattern.setUrlPattern("*");
            pattern.setResourceType(Document);
            network.setRequestInterception(asList(pattern));

            boolean followRedirect = true;

            session.addEventListener((e, d) -> {
                if (NetworkRequestIntercepted.equals(e)) {
                    RequestIntercepted ri = (RequestIntercepted) d;
                    boolean isRedirect = ri.getRedirectUrl() != null && ! ri.getRedirectUrl().isEmpty();

                    if (isRedirect) {

                        System.out.println("");
                        System.out.println("Redirect URL         : " + ri.getRedirectUrl());
                        System.out.println("Redirect Status Code : " + ri.getResponseStatusCode());
                        System.out.println("Redirect Header      : " + ri.getResponseHeaders());
                        System.out.println("");

                        if ( ! followRedirect ) {
                            return;
                        }
                    }

                    network.continueInterceptedRequest(ri.getInterceptionId());
                }
            });

            session.navigate("https://httpbin.org/redirect-to?url=https://webfolder.io?cdp4j");
            session.waitDocumentReady();

            System.out.println(session.evaluate("document.location.href"));
        } finally {
            launcher.kill();
        }
    }
}
