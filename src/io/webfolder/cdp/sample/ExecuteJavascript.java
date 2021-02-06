package io.webfolder.cdp.sample;

import io.webfolder.cdp.Launcher;
import io.webfolder.cdp.session.Session;
import io.webfolder.cdp.session.SessionFactory;

public class ExecuteJavascript {

    public static void main(String[] args) {
        Launcher launcher = new Launcher();

        try (SessionFactory factory = launcher.launch();
                            Session session = factory.create()) {

            session.navigate("about:blank");
            session.waitDocumentReady();
            Double result = (Double) session.evaluate("var foo = function() { return 2 + 2; }; foo();");
            System.out.println(result);

            session.evaluate("var bar = {}; bar.myFunc = function(s1, s2) { return s1 + ' ' + s2; }");
            String message = session.callFunction("bar.myFunc", String.class, "hello", "world");
            System.out.println(message);

            Integer intResult = session.callFunction("foo", Integer.class);
            System.out.println(intResult);
        } finally {
            launcher.kill();
        }
    }
}
