/**
 * cdp4j - Chrome DevTools Protocol for Java
 * Copyright © 2017, 2018 WebFolder OÜ (support@webfolder.io)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package io.webfolder.cdp.sample;

import java.net.URL;

import io.webfolder.cdp.Launcher;
import io.webfolder.cdp.session.Session;
import io.webfolder.cdp.session.SessionFactory;

public class Attributes {

    public static void main(String[] args) {
        URL url = Attributes.class.getResource("/attributes.html");

        try (
                Launcher launcher = new Launcher();
                SessionFactory factory = launcher.launch().setCloseBrowserOnFactoryClose(true);
                Session session = factory.create()
        ) {
            session.navigateAndWait(url.toString());
            String value = session.getAttribute("input", "value");
            System.out.println("Old Value: " + value);
            session.setAttribute("input", "value", "bar");
            value = session.getAttribute("input", "value");
            System.out.println("New Value: " + value);
        }
    }
}
