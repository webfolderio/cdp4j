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

import static io.webfolder.cdp.event.Events.NetworkLoadingFinished;
import static io.webfolder.cdp.event.Events.NetworkResponseReceived;

import java.util.HashSet;
import java.util.Set;

import io.webfolder.cdp.Launcher;
import io.webfolder.cdp.event.network.LoadingFinished;
import io.webfolder.cdp.event.network.ResponseReceived;
import io.webfolder.cdp.session.Session;
import io.webfolder.cdp.session.SessionFactory;
import io.webfolder.cdp.type.network.GetResponseBodyResult;
import io.webfolder.cdp.type.network.Response;
import io.webfolder.cdp.type.network.ResourceType;

public class NetworkResponse {

    public static void main(String[] args) {
        
        Launcher launcher = new Launcher();

        Set<String> finished = new HashSet<>();

        try (SessionFactory factory = launcher.launch();
                            Session session = factory.create()) {
            session.getCommand().getNetwork().enable();
            session.addEventListener((e, d) -> {
                if (NetworkLoadingFinished.equals(e)) {
                    LoadingFinished lf = (LoadingFinished) d;
                    finished.add(lf.getRequestId());
                }
                if (NetworkResponseReceived.equals(e)) {
                    ResponseReceived rr = (ResponseReceived) d;
                    Response response = rr.getResponse();
                    System.out.println("----------------------------------------");
                    System.out.println("URL       : " + response.getUrl());
                    System.out.println("Status    : HTTP " + response.getStatus().intValue() + " " + response.getStatusText());
                    System.out.println("Mime Type : " + response.getMimeType());
                    if (finished.contains(rr.getRequestId()) && ResourceType.Document.equals(rr.getType())) {
                        GetResponseBodyResult rb = session.getCommand().getNetwork().getResponseBody(rr.getRequestId());
                        if ( rb != null ) {
                            String body = rb.getBody();
                            System.out.println("Content   : " + body.substring(0, body.length() > 1024 ? 1024 : body.length()));
                        }
                    }
                }
            });
            session.navigate("http://cnn.com");
            session.waitDocumentReady();
        } finally {
            launcher.kill();
        }
    }
}
