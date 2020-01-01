/**
 * cdp4j Commercial License
 *
 * Copyright 2017, 2020 WebFolder OÜ
 *
 * Permission  is hereby  granted,  to "____" obtaining  a  copy of  this software  and
 * associated  documentation files  (the "Software"), to deal in  the Software  without
 * restriction, including without limitation  the rights  to use, copy, modify,  merge,
 * publish, distribute  and sublicense  of the Software,  and to permit persons to whom
 * the Software is furnished to do so, subject to the following conditions:
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR  IMPLIED,
 * INCLUDING  BUT NOT  LIMITED  TO THE  WARRANTIES  OF  MERCHANTABILITY, FITNESS  FOR A
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL  THE AUTHORS  OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package io.webfolder.cdp.sample;

import static java.util.Arrays.asList;

import java.net.URL;

import io.webfolder.cdp.Launcher;
import io.webfolder.cdp.session.Session;
import io.webfolder.cdp.session.SessionFactory;

public class MultiSelect {

    public static void main(String[] args) {
        URL url = MultiSelect.class.getResource("/multi-select.html");

        Launcher launcher = new Launcher();

        try (SessionFactory factory = launcher.launch();
                            Session session = factory.create()) {
            session.navigate(url.toString());
            session.waitDocumentReady();

            System.out.println("Selected options:");
            session.getOptions("select").stream().forEach(o -> {
                if (o.isSelected()) {
                    System.out.println(o);
                }
            });

            session.wait(1000);
            session.clearOptions("select");

            System.out.println("Selected Option count: " +
            session.getOptions("select").stream().filter(o -> o.isSelected()).count());

            session.wait(1000);
            session.setSelectedOptions("select", asList(0, 1, 2));
            System.out.println("Selected options:");
            session.getOptions("select").stream().forEach(o -> {
                if (o.isSelected()) {
                    System.out.println(o);
                }
            });

            System.out.println("Selected Option count: " +
            session.getOptions("select").stream().filter(o -> o.isSelected()).count());

            session.wait(1000);
        } finally {
            launcher.kill();
        }
    }
}
