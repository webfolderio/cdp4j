package io.webfolder.cdp.sample;

import static io.webfolder.cdp.event.Events.RuntimeBindingCalled;
import static io.webfolder.cdp.session.WaitUntil.DomContentLoad;
import static java.lang.String.format;

import io.webfolder.cdp.Launcher;
import io.webfolder.cdp.command.Page;
import io.webfolder.cdp.event.runtime.BindingCalled;
import io.webfolder.cdp.session.Session;
import io.webfolder.cdp.session.SessionFactory;

public class InvokeJavaFromJs {

    public static void main(String[] args) {
        Launcher launcher = new Launcher();

        try (SessionFactory factory = launcher.launch();
                                Session session = factory.create()) {
            session.enableConsoleLog();

            Page page = session.getCommand().getPage();
            // enable Page domain before using the addScriptToEvaluateOnNewDocument()
            page.enable();

            // addScriptToEvaluateOnNewDocument() must be called before Session.navigate()
            page.addScriptToEvaluateOnNewDocument("sendMessage = send");
            session.getCommand().getRuntime().addBinding("send");

            session.navigateAndWait("about:blank", DomContentLoad);

            session.addEventListener((event, value) -> {
                if (RuntimeBindingCalled.equals(event)) {
                    BindingCalled binding = (BindingCalled) value;
                    System.out.println(format("name: [%s] payload: [%s]",
                                            binding.getName(), binding.getPayload()));
                }
            });

            session.evaluate("sendMessage(JSON.stringify({ 'foo' : 'bar' }));");
            session.evaluate("sendMessage(JSON.stringify({ 'hello' : 'world' }));");
        } finally {
            launcher.kill();
        }
    }
}
