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

import io.webfolder.cdp.exception.CdpException;

import static java.io.File.pathSeparator;
import static java.lang.System.getProperty;
import static java.util.Locale.ENGLISH;

import io.webfolder.cdp.logger.CdpLogger;
import io.webfolder.cdp.logger.CdpLoggerFactory;

public class AdaptiveProcessManager extends ProcessManager {

    private ProcessManager processManager;

    private static final String  OS      = getProperty("os.name").toLowerCase(ENGLISH);

    private static final boolean WINDOWS = ";".equals(pathSeparator);

    private static final boolean LINUX   = "linux".contains(OS);

    private static final boolean MAC     = OS.contains("mac");

    private static final boolean JAVA_8  = getProperty("java.version").startsWith("1.8.");

    private CdpLogger logger = new CdpLoggerFactory().getLogger("cdp4j.process-manager");
    
    public AdaptiveProcessManager() {
        if ( ! JAVA_8 ) {
           processManager = new DefaultProcessManager();
        } else if (WINDOWS) {
        	try {
        		processManager = new WindowsProcessManager();
        	} catch (Throwable t) {
        		logger.error(t.getMessage());
        	}
        } else if (LINUX) {
            processManager = new LinuxProcessManager();
        } else if (MAC) {
            processManager = new MacOsProcessManager();            
        } else {
            throw new CdpException(OS + " is not supported by AdaptiveProcessManager");
        }
    }

    @Override
    void setProcess(CdpProcess process) {
        processManager.setProcess(process);
    }

    @Override
    public boolean kill() {
        return processManager.kill();
    }
}
