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

import java.lang.ProcessHandle.Info;
import java.time.Instant;
import java.util.Optional;

import static java.lang.ProcessHandle.of;

public class DefaultProcessManager extends ProcessManager {

    protected long pid;

    protected Instant startTime;

    protected String command;

    @Override
    void setProcess(CdpProcess process) {
        ProcessHandle handle = process.getProcess().toHandle();
        pid = process.getProcess().pid();
        try {
            Info info = handle.info();
            startTime = info.startInstant().get();
            command = info.command().get();
        } catch (Exception ignored) {
            //It may sometimes fail with RuntimeException
        }
    }

    @Override
    public boolean kill() {
        Optional<ProcessHandle> process = of(pid);
        if (process.isPresent()) {
            ProcessHandle handle = process.get();
            Info info = handle.info();
            if (
                    handle.isAlive() &&
                            (startTime==null || (info.startInstant().isPresent() && info.startInstant().get().equals(startTime))) &&
                            (command==null || (info.command().isPresent() && info.command().get().equals(command)))
            ) {
                handle.descendants().forEach(ph -> {
                    try {
                        if (ph.isAlive()) {
                            ph.destroyForcibly();
                        }
                    } catch (Exception ignored) {

                    }
                });

                return handle.destroyForcibly();
            }
        }
        return false;
    }
}
