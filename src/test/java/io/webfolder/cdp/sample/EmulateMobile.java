package io.webfolder.cdp.sample;

import io.webfolder.cdp.Launcher;
import io.webfolder.cdp.session.Session;
import io.webfolder.cdp.session.SessionFactory;

public class EmulateMobile {
	// We need something that changes the site based on mobile device.
	public static void main(String[] args) {
		Launcher launcher = new Launcher();

		try (SessionFactory factory = launcher.launch(); Session session = factory.create()) {

			// session.emulateDevice("Nexus 6", Boolean.FALSE);
			session.navigate("https://www.finovera.com/billmanager/login");
			session.waitDocumentReady();

			session.focus("//input[@name='username']");
			session.sendKeys("purna@finovera.com");
			session.focus("//input[@name='password']");
			session.sendKeys("Monkey12");

			session.click("//button[@id='login-button']");

			System.out.println("Button clicked");
		}
	}
}
