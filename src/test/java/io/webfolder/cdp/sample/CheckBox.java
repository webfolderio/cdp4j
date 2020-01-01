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

import java.net.URL;

import io.webfolder.cdp.Launcher;
import io.webfolder.cdp.session.Session;
import io.webfolder.cdp.session.SessionFactory;

public class CheckBox {

    public static void main(String[] args) {
        Launcher launcher = new Launcher();

        URL url = CheckBox.class.getResource("/checkbox.html");

        try (SessionFactory factory = launcher.launch();
                            Session session = factory.create()) {

            session.navigate(url.toString());
            session.waitDocumentReady();
            System.out.println("Checked: " + session.isChecked("input[name='red']"));
            session.setChecked("input[name='red']", true);
            System.out.println("Checked: " + session.isChecked("input[name='red']"));
            session.wait(1000);
            session.setChecked("input[name='red']", false);
            System.out.println("Checked: " + session.isChecked("input[name='red']"));
            session.wait(1000);
        } finally {
            launcher.kill();
        }
    }
}
