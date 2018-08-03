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

import static java.util.Locale.ENGLISH;

import io.webfolder.cdp.Launcher;
import io.webfolder.cdp.session.Session;
import io.webfolder.cdp.session.SessionFactory;

public class Bing {

    public static void main(String[] args) {
        try (Launcher launcher = new Launcher();
                            SessionFactory factory = launcher.launch();
                            Session session = factory.create()) {
            session
                .navigateAndWait("https://www.bing.com")
                .enableNetworkLog()
                .click("input[type='search']")
                .sendKeys("Microsoft")
                .sendEnter()
                .wait(1000);

            String firstResult = session.getText("strong").toLowerCase(ENGLISH);
 
            System.out.println("Query String : " + session.getQueryString());
            System.out.println("Path name    : " + session.getPathname());
    
            System.out.println(firstResult);            
        }
    }
}
