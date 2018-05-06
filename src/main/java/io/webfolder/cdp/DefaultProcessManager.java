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

import static java.lang.ProcessHandle.of;

import java.lang.ProcessHandle.Info;
import java.time.Instant;
import java.util.Optional;

public class DefaultProcessManager extends ProcessManager {

    private long pid;

    private Instant startTime;

    private String command;

    @Override
    public void setProcess(CdpProcess process) {
        ProcessHandle handle = process.getProcess().toHandle();
        Info info = handle.info();
        startTime = info.startInstant().get();
        command = info.command().get();
        pid = handle.pid();
    }

    @Override
    public boolean kill() {
        Optional<ProcessHandle> process = of(pid);
        if (process.isPresent()) {
            ProcessHandle handle = process.get();
            Info info = handle.info();
            if (handle.isAlive() &&
                    info.startInstant().isPresent() &&
                    info.startInstant().get().equals(startTime) &&
                    info.command().isPresent() &&
                    info.command().get().equals(command)) {
                handle.descendants().forEach(ph -> {
                    if (ph.isAlive()) {
                        ph.destroy();
                    }
                });
                return handle.destroy();
            }
        }
        return false;
    }
}
