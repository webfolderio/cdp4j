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
package io.webfolder.cdp;

import static java.lang.Class.forName;
import static java.util.concurrent.TimeUnit.SECONDS;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Scanner;

import io.webfolder.cdp.exception.CdpException;

public class LinuxProcessManager extends ProcessManager {

    private int pid;

    private String cdp4jId;

    @Override
    public void setProcess(CdpProcess process) {
        try {
            Field pidField = process.getProcess().getClass().getDeclaredField("pid");
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
