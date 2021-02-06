package io.webfolder.cdp.sample;

import static io.webfolder.cdp.event.Events.NetworkLoadingFinished;
import static io.webfolder.cdp.event.Events.NetworkResponseReceived;
import static java.util.Base64.getDecoder;
import static java.util.Collections.emptyList;
import static java.util.Collections.synchronizedSet;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import io.webfolder.cdp.Launcher;
import io.webfolder.cdp.event.network.LoadingFinished;
import io.webfolder.cdp.event.network.ResponseReceived;
import io.webfolder.cdp.exception.CdpException;
import io.webfolder.cdp.session.Session;
import io.webfolder.cdp.session.SessionFactory;
import io.webfolder.cdp.type.network.GetResponseBodyResult;
import io.webfolder.cdp.type.network.ResourceType;

public class Crawler {

    public static class SitemapParser {

        public List<String> parse(String content) throws Exception {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new ByteArrayInputStream(content.getBytes()));
            NodeList nodes = document.getElementsByTagName("loc");
            List<String> urls = new ArrayList<>();
            for (int i = 0; i < nodes.getLength(); i++) {
                Node node = nodes.item(i);
                String tc = node.getTextContent();
                if ( tc != null ) {
                    urls.add(tc);
                }
            }
            return urls;
        }
    }

    public static class Resource {

        private String document;

        private byte[] content;

        private int status;

        private Map<String, Object> responseHeaders;

        private String url;

        private String requestId;

        public String getDocument() {
            return document;
        }

        public void setDocument(String document) {
            this.document = document;
        }


        public byte[] getContent() {
            return content;
        }

        public void setContent(byte[] content) {
            this.content = content;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public Map<String, Object> getResponseHeaders() {
            return responseHeaders;
        }

        public void setResponseHeaders(Map<String, Object> responseHeaders) {
            this.responseHeaders = responseHeaders;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getRequestId() {
            return requestId;
        }

        public void setRequestId(String requestId) {
            this.requestId = requestId;
        }
    }

    public static class HttpClient implements AutoCloseable {

        private SessionFactory factory;

        private Session session;

        private int pageLoadTimeout;

        private Set<String> finishedResources = synchronizedSet(new HashSet<>());

        public HttpClient(SessionFactory factory, int pageLoadTimeout) {
            this.factory = factory;
            this.pageLoadTimeout = pageLoadTimeout;
        }

        public Resource fetch(String url) {
            Resource webResource = new Resource();

            session = factory.create();

            session.getCommand().getNetwork().enable();

            session.addEventListener((e, d) -> {
                if (NetworkLoadingFinished.equals(e)) {
                    LoadingFinished lf = (LoadingFinished) d;
                    finishedResources.add(lf.getRequestId());
                }
                if (NetworkResponseReceived.equals(e)) {
                    ResponseReceived rr = (ResponseReceived) d;

                    if ( ! rr.getResponse().getUrl().startsWith("https://") &&
                            ! rr.getResponse().getUrl().startsWith("http://") ) {
                        return;
                    }

                    ResourceType type = rr.getType();

                    if (ResourceType.Document.equals(type)) {
                        webResource.setResponseHeaders(new ConcurrentHashMap<>(rr.getResponse().getHeaders()));
                        webResource.setStatus(rr.getResponse().getStatus().intValue());
                        webResource.setUrl(rr.getResponse().getUrl());
                        webResource.setRequestId(rr.getRequestId());
                    }
                }
            });

            session.navigate(url);
            session.waitDocumentReady(pageLoadTimeout);

            for (String requestId : new ArrayList<>(finishedResources)) {
                try {
                    GetResponseBodyResult rb = session.getCommand().getNetwork().getResponseBody(requestId);
                    if ( rb.getBase64Encoded() ) {
                        webResource.setContent(getDecoder().decode(rb.getBody()));
                    } else {
                        webResource.setDocument(rb.getBody());
                    }
                } catch (CdpException e) {
                    // ignore
                }
            }

            String ct = (String) webResource.getResponseHeaders().get("content-type");
            if ( ct != null && "text/html".equals(ct) ) {
                webResource.setDocument(session.getContent());
            }

            return webResource;
        }

        @Override
        public void close() {
            if ( session != null ) {
                session.close();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Launcher launcher = new Launcher();

        SessionFactory factory = launcher.launch();
        Session dummySession = factory.create();
        dummySession.navigate("about:blank");

        List<String> urls = emptyList();

        final int pageLoadTimeout = 60 * 1000; // 60 seconds

        try (HttpClient client = new HttpClient(factory, pageLoadTimeout)) {
            Resource resource = client.fetch("https://webfolder.io/sitemap.xml");
            urls = new SitemapParser().parse(resource.getDocument());
        }

        List<Resource> resources = new ArrayList<>();

        for (String url : urls) {
            try (HttpClient client = new HttpClient(factory, pageLoadTimeout)) {
                Resource resource = client.fetch(url);
                resources.add(resource);
            }
        }

        for (Resource resource : resources) {
            System.out.println("URL: " + resource.getUrl() +
                               ", Status Code: " + resource.getStatus() +
                               ", Content Length: " + resource.getDocument().length());
        }

        dummySession.close();

        launcher.kill();
    }
}
