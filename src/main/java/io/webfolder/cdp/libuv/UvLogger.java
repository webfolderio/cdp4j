package io.webfolder.cdp.libuv;

import static java.lang.System.getProperty;

public class UvLogger {

    public static boolean debug = "true".equals(getProperty("cdp4j.libuv.debug"));

    static void debug(String message) {
        if (debug) {
            System.out.println(message);
        }
    }
}
