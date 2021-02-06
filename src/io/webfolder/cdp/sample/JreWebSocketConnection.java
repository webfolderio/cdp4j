package io.webfolder.cdp.sample;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import io.webfolder.cdp.Launcher;
import io.webfolder.cdp.channel.ChannelFactory;
import io.webfolder.cdp.exception.CdpException;
import io.webfolder.cdp.session.Session;
import io.webfolder.cdp.session.SessionFactory;

public class JreWebSocketConnection {

    public static void main(String[] args) {
        ChannelFactory jreWebSocketFactory = null;
        try {
            Class<?> klass = Launcher.class.getClassLoader().loadClass("io.webfolder.cdp.channel.JreWebSocketFactory");
            Constructor<?> constructor = klass.getConstructor();
            jreWebSocketFactory = (ChannelFactory) constructor.newInstance();
        } catch (ClassNotFoundException |
                InstantiationException | IllegalAccessException |
                NoSuchMethodException  | SecurityException |
                IllegalArgumentException | InvocationTargetException e) {
           throw new CdpException(e);
       }

        Launcher launcher = new Launcher(jreWebSocketFactory);

        try (SessionFactory factory = launcher.launch();
                            Session session = factory.create()) {
            session.navigate("https://webfolder.io?cdp4j");
            session.waitDocumentReady();
            String content = session.getContent();
            System.out.println(content);
        } finally {
            launcher.kill();
        }
    }
}
