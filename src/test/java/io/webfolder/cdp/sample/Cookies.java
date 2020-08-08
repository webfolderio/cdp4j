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

import static io.webfolder.cdp.session.WaitUntil.NetworkIdle;

import java.util.Arrays;
import java.util.List;

import io.webfolder.cdp.Launcher;
import io.webfolder.cdp.command.Network;
import io.webfolder.cdp.session.Session;
import io.webfolder.cdp.session.SessionFactory;
import io.webfolder.cdp.type.network.Cookie;
import io.webfolder.cdp.type.network.CookieParam;

public class Cookies {

    public static void main(String[] args) {
        Launcher launcher = new Launcher();

        try (SessionFactory factory = launcher.launch()) {
            try (Session session = factory.create()) {
                session.navigateAndWait("https://httpbin.org/cookies/set?foo=bar", NetworkIdle);
                Network network = session.getCommand().getNetwork();
                List<Cookie> cookies = network.getCookies();
                Cookie cookie = cookies.get(0);
                System.out.println("Cookie Domain=" + cookie.getDomain());
                System.out.println("name=" + cookie.getName());
                System.out.println("value=" + cookie.getValue());

                CookieParam param = new CookieParam();
                param.setDomain(cookie.getDomain());
                param.setName(cookie.getName());
                param.setValue("new-value");
                param.setPath(cookie.getPath());

                network.setCookies(Arrays.asList(param));
                
                session.navigateAndWait("https://httpbin.org/cookies", NetworkIdle);
                cookie = network.getCookies().get(0);

                System.out.println("Cookie Domain=" + cookie.getDomain());
                System.out.println("name=" + cookie.getName());
                System.out.println("value=" + cookie.getValue());
            }
        } finally {
            launcher.kill();
        }
    }
}
