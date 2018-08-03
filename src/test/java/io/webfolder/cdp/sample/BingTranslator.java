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

import io.webfolder.cdp.Launcher;
import io.webfolder.cdp.session.Option;
import io.webfolder.cdp.session.Session;
import io.webfolder.cdp.session.SessionFactory;

public class BingTranslator {

    public static void main(String[] args) {
        Launcher launcher = new Launcher();

        try (SessionFactory factory = launcher.launch();
                            Session session = factory.create()) {
            session
                .navigateAndWait("https://www.bing.com/translator")
                .enableConsoleLog()
                .enableDetailLog()
                .enableNetworkLog();

            Option en = session
                        .getOptions("#t_sl")
                        .stream()
                        .filter(p -> "en".equals(p.getValue()))
                        .findFirst()
                        .get();
            
            Option et = session
                        .getOptions("#t_tl")
                        .stream()
                        .filter(p -> "et".equals(p.getValue()))
                        .findFirst()
                        .get();

            session
                .click("#t_sl") // click source language
                .wait(500)
                .setSelectedIndex("#t_sl", en.getIndex()) // choose English
                .wait(500)
                .click("#t_tl") // click destination language
                .wait(500)
                .setSelectedIndex("#t_tl", et.getIndex()) // choose Estonian
                .wait(500);

            session.focus("#t_sv")
                    .sendKeys("hello world")
                    .wait(1000);

            System.out.println(session.getValue("#t_tv"));
        }
    }
}
