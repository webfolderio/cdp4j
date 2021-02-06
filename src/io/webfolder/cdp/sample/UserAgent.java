package io.webfolder.cdp.sample;

import java.util.Map;

import com.google.gson.Gson;

import io.webfolder.cdp.Launcher;
import io.webfolder.cdp.session.Session;
import io.webfolder.cdp.session.SessionFactory;

public class UserAgent {

    @SuppressWarnings("rawtypes")
    public static void main(String[] args) {
        Launcher launcher = new Launcher();

        try (SessionFactory factory = launcher.launch();
                            Session session = factory.create()) {

            session.setUserAgent("My Browser");
            session.navigate("https://httpbin.org/headers");
            session.waitDocumentReady();
            String response = (String) session.evaluate("document.body.textContent");

            Gson gson = new Gson();
            Map json = gson.fromJson(response, Map.class);
            Map headers = (Map) json.get("headers");

            System.out.println(headers.get("User-Agent"));
        } finally {
            launcher.kill();
        }
    }
}
