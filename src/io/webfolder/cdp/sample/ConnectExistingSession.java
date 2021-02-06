package io.webfolder.cdp.sample;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import io.webfolder.cdp.Launcher;
import io.webfolder.cdp.event.Events;
import io.webfolder.cdp.event.target.TargetCreated;
import io.webfolder.cdp.event.target.TargetDestroyed;
import io.webfolder.cdp.listener.EventListener;
import io.webfolder.cdp.session.Session;
import io.webfolder.cdp.session.SessionFactory;
import io.webfolder.cdp.type.target.TargetInfo;

public class ConnectExistingSession {

	public static void main(String[] args) throws InterruptedException {
        Launcher launcher = new Launcher();

        SessionFactory factory = launcher.launch();

        Session dummySession = factory.create();

        Set<String> existingTargets = new HashSet<>();

        for (int i = 0; i < 2; i++) {
        	Session session = factory.create();
        	session.navigate("about:blank?" + i);
        	existingTargets.add(session.getTargetId());
        }

        dummySession.addEventListener(new EventListener() {
			
			@Override
			public void onEvent(Events event, Object value) {
				if (Events.TargetTargetCreated.equals(event)) {
					TargetCreated tc = (TargetCreated) value;
					System.out.println("target created: " + tc.getTargetInfo().getUrl());
				} else if (Events.TargetTargetDestroyed.equals(event)) {
					TargetDestroyed td = (TargetDestroyed) value;
					System.out.println("target destroyed: " + td.getTargetId());
				}
				
			}
		});

        List<TargetInfo> targets = dummySession.getCommand()
        									   .getTarget()
        									   .getTargets();

        for (TargetInfo next : targets) {
        	// skip if target is not page
        	if (! "page".equals(next.getType()) ) {
        		continue;
        	}
        	// skip if target is new tab or chrome's internal tab/page
        	if ( next.getUrl().startsWith("chrome://") ) {
        		continue;
        	}
        	Session session = factory.connect(next.getTargetId());
        	if ( ! existingTargets.contains(session.getTargetId()) ) {
        		throw new IllegalStateException();
        	}
        	session.close();
        }

        Thread.sleep(2000);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> launcher.kill()));
	}
}
