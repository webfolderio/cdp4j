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
