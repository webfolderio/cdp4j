package io.webfolder.cdp.test;

import static java.nio.file.Paths.get;

import java.nio.file.Path;

import org.junit.Test;

import io.webfolder.cdp.js.JsEngine;

public class TestQuickJs {

    @Test
    public void test() {
        try (JsEngine engine = new JsEngine()) {
            Path resource = get("src/test/resources/quickjs/sample/bing.js");
            engine.evaluateModule(resource);
        }
    }
}
