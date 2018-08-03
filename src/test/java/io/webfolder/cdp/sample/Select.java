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
import java.util.Optional;

import io.webfolder.cdp.Launcher;
import io.webfolder.cdp.session.Option;
import io.webfolder.cdp.session.Session;
import io.webfolder.cdp.session.SessionFactory;

public class Select {

    public static void main(String[] args) {
        URL url = Select.class.getResource("/select.html");

        try (Launcher launcher = new Launcher();
                            SessionFactory factory = launcher.launch();
                            Session session = factory.create()) {
            session.navigateAndWait(url.toString());
            int selectedIndex = session.getSelectedIndex("select");
            System.out.println("Selected  index : " + selectedIndex);
            System.out.println("options         : " + session.getOptions("select"));
            session.wait(2000);
            session.setSelectedIndex("select", 2);
            session.wait(2000);
            selectedIndex = session.getSelectedIndex("select");
            System.out.println("Selected  index : " + selectedIndex);
            Optional<Option> selected = session.getOptions("select").stream().filter(Option::isSelected).findFirst();
            System.out.println("Selected        : " + selected.get().getText());
        }

    }
}
