package io.webfolder.cdp.js;

import static java.nio.file.Paths.get;

import java.nio.file.Path;

public class SetTimeout {

    @SuppressWarnings("resource")
    public static void main(String[] args) throws Exception {
        Path script = get("src/test/resources/quickjs/timeout.js");

        new JsEngine().evaluate(script);

        System.in.read();
    }
}
