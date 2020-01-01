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

import static java.lang.System.getProperty;
import static java.nio.file.Paths.get;

import java.io.IOException;
import java.net.ServerSocket;
import java.nio.file.Path;
import java.util.Random;

import io.webfolder.cdp.Launcher;
import io.webfolder.cdp.Options;
import io.webfolder.cdp.session.Session;
import io.webfolder.cdp.session.SessionFactory;

public class MultiProcess {

    public static void main(String[] args) {
        new Thread() {

            public void run() {
                Path remoteProfileData = get(getProperty("java.io.tmpdir")).resolve("remote-profile-" + new Random().nextInt());
                Options options = Options.builder().userDataDir(remoteProfileData).build();
                Launcher launcher = new Launcher(options);

                SessionFactory factory = launcher.launch();

                try (SessionFactory sf = factory) {
                    try (Session session = sf.create()) {
                        session.navigate("https://webfolder.io");
                        session.waitDocumentReady();
                        System.err.println("Content Length: " + session.getContent().length());
                    }
                }
            }
        }.start();

        new Thread() {

            public void run() {
                Path remoteProfileData = get(getProperty("java.io.tmpdir")).resolve("remote-profile-" + new Random().nextInt());
                Options options = Options.builder().userDataDir(remoteProfileData).build();
                Launcher launcher = new Launcher(options);

                SessionFactory factory = launcher.launch();

                try (SessionFactory sf = factory) {
                    try (Session session = sf.create()) {
                        session.navigate("https://webfolder.io");
                        session.waitDocumentReady();
                        System.err.println("Content Length: " + session.getContent().length());
                    }
                }
            }
        }.start();
    }

    protected static int getFreePort(int portNumber) {
        try (ServerSocket socket = new ServerSocket(portNumber)) {
            int freePort = socket.getLocalPort();
            return freePort;
        } catch (IOException e) {
            return getFreePort(portNumber + 1);
        }
    }
}
