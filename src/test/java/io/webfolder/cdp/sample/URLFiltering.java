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
                        // do not allow to download jpg files
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
