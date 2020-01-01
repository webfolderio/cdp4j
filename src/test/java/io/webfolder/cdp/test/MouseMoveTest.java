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

import java.util.List;

import org.junit.Test;

import io.webfolder.cdp.Launcher;
import io.webfolder.cdp.Options;
import io.webfolder.cdp.exception.CdpReadTimeoutException;
import io.webfolder.cdp.session.Session;
import io.webfolder.cdp.session.SessionFactory;

public class MouseMoveTest {

    @Test
    @SuppressWarnings({ "unchecked" })
    public void testMouseMove() throws Exception {
        String uri = get("src/test/resources/mouse-move.html").toAbsolutePath().toUri().toString();

        Launcher launcher = new Launcher(Options.builder().headless(true).build());

        try (SessionFactory factory = launcher.launch(); Session session = factory.create()) {
            session.enableConsoleLog();
            session.navigate(uri);
            session.move(20, 20);
            session.move(21, 21);
            session.move(22, 22);
            session.move(23, 23);
            List<Double> positionsX = session.getVariable("positionsX", List.class);
            List<Double> positionsY = session.getVariable("positionsY", List.class);
            System.out.println(positionsX);
            System.out.println(positionsY);
            assertTrue(positionsX.size() >= 4);
            assertTrue(positionsY.size() >= 4);
        } catch (CdpReadTimeoutException e) {
            // ignore
        } finally {
            launcher.kill();
        }
    }
}
