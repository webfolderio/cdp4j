/**
 * cdp4j - Chrome DevTools Protocol for Java
 * Copyright © 2017, 2018 WebFolder OÜ (support@webfolder.io)
 * <p>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * <p>
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package io.webfolder.cdp.sample;

import io.webfolder.cdp.Launcher;
import io.webfolder.cdp.session.Session;
import io.webfolder.cdp.session.SessionFactory;

import java.nio.file.Path;
import java.util.Random;

import static java.lang.System.getProperty;
import static java.nio.file.Paths.get;
import static java.util.Arrays.asList;

public class MultiProcess {

    // port number and user-data-dir must be different for each chrome process
    // As an alternative @see IncognitoBrowsing.java for incognito mode (private browsing).
    public static void main(String[] args) {
        new Thread() {
            public void run() {
                try (Launcher launcher = new Launcher()) {
                    Path remoteProfileData = get(getProperty("java.io.tmpdir")).resolve("remote-profile-" + new Random().nextInt());
                    SessionFactory factory = launcher.launch(asList("--user-data-dir=" + remoteProfileData.toString()));

                    try (SessionFactory sf = factory) {
                        try (Session session = sf.create()) {
                            session.navigateAndWait("https://webfolder.io");
                            System.err.println("Content Length: " + session.getContent().length());
                        }
                    }
                }
            }
        }.start();

        new Thread() {
            public void run() {
                try (Launcher launcher = new Launcher()) {
                    Path remoteProfileData = get(getProperty("java.io.tmpdir")).resolve("remote-profile-" + new Random().nextInt());
                    SessionFactory factory = launcher.launch(asList("--user-data-dir=" + remoteProfileData.toString()));

                    try (SessionFactory sf = factory) {
                        try (Session session = sf.create()) {
                            session.navigateAndWait("https://webfolder.io");
                            System.err.println("Content Length: " + session.getContent().length());
                        }
                    }
                }
            }
        }.start();
    }

}
