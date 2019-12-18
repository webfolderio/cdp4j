package io.webfolder.cdp.libuv;

public class UvLogger {

    public static boolean debug = true;

    static void debug(String message) {
        if (debug) {
            System.out.println(message);
        }
    }
}
