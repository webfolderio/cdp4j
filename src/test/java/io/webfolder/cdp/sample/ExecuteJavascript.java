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
import io.webfolder.cdp.session.Session;
import io.webfolder.cdp.session.SessionFactory;

public class ExecuteJavascript {

    public static void main(String[] args) {
        Launcher launcher = new Launcher();

        try (SessionFactory factory = launcher.launch();
                            Session session = factory.create()) {

            session.waitDocumentReady();
            Integer result = (Integer) session.evaluate("var foo = function() { return 2 + 2; }; foo();");
            System.out.println(result);

            session.evaluate("var bar = {}; bar.myFunc = function(s1, s2) { return s1 + ' ' + s2; }");
            String message = session.callFunction("bar.myFunc", String.class, "hello", "world");
            System.out.println(message);

            Integer intResult = session.callFunction("foo", Integer.class);
            System.out.println(intResult);
        }
    }
}
