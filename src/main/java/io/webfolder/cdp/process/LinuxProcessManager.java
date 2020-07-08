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
package io.webfolder.cdp.process;

import static java.lang.Class.forName;
import static java.util.concurrent.TimeUnit.SECONDS;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Scanner;

import io.webfolder.cdp.exception.CdpException;

public class LinuxProcessManager implements ProcessManager {

    private int pid;

    private String cdp4jId;

    private CdpProcess process;

    @Override
    public void setProcess(CdpProcess process) {
        if (this.process != null) {
            throw new IllegalStateException();
        }
        try {
            Field pidField = process.getProcess()
                                    .getClass()
                                    .getDeclaredField("pid");
            pidField.setAccessible(true);
            this.pid = (int) pidField.get(process.getProcess());
        } catch (Throwable e) {
            throw new CdpException(e);
        }
        this.cdp4jId = process.getCdp4jProcessId();
    }

    @Override
    public boolean kill() {
        ProcessBuilder builder = new ProcessBuilder("strings",
                                                    "-a",
                                                    "/proc/" + pid + "/cmdline");
        try {
            Process process = builder.start();
            boolean ok = process.waitFor(5, SECONDS);
            if ( ! ok ) {
                return false;
            }
            if ( process.exitValue() != 0 ) {
                return false;
            }
            String stdout = toString(process.getInputStream());
            if ( ! stdout.contains("cdp4jId=" + cdp4jId) ) {
                return false;
            }
        } catch (Throwable e) {
            return false;
        }
        try {
            Class<?> clazz = forName("java.lang.UNIXProcess");
            Method destroyProcess = clazz.getDeclaredMethod("destroyProcess",
                                                                int.class,
                                                                boolean.class);
            destroyProcess.setAccessible(true);
            boolean force = false;
            destroyProcess.invoke(null, pid, force);
            return true;
        } catch (Throwable e) {
            return false;
        }
    }

    protected String toString(InputStream is) {
        try (Scanner scanner = new Scanner(is)) {
            scanner.useDelimiter("\\A");
            return scanner.hasNext() ? scanner.next() : "";
        }
    }
}
