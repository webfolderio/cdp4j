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
package io.webfolder.cdp.test;

import static java.nio.file.Paths.get;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import io.webfolder.cdp.Launcher;
import io.webfolder.cdp.Options;
import io.webfolder.cdp.exception.CdpReadTimeoutException;
import io.webfolder.cdp.session.Session;
import io.webfolder.cdp.session.SessionFactory;
import io.webfolder.cdp.session.WaitUntil;

public class MouseClickTest {

    @Test
    public void testMouseMove() throws Exception {
        String uri = get("src/test/resources/mouse-click.html").toAbsolutePath().toUri().toString();

        Launcher launcher = new Launcher(Options.builder().headless(true).build());

        try (SessionFactory factory = launcher.launch(); Session session = factory.create()) {
            session.navigateAndWait(uri, WaitUntil.DomContentLoad);
            session.click("#mybutton");
            Boolean clicked = session.getVariable("clicked", Boolean.class);
            assertTrue(clicked);
        } catch (CdpReadTimeoutException e) {
            // ignore
        } finally {
            launcher.kill();
        }
    }
}
