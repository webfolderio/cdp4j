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
package io.webfolder.cdp.command;

import io.webfolder.cdp.annotation.Domain;
import io.webfolder.cdp.type.log.ViolationSetting;

import java.util.List;

/**
 * Provides access to log entries
 */
@Domain("Log")
public interface Log {
    /**
     * Clears the log.
     */
    void clear();

    /**
     * Disables log domain, prevents further log entries from being reported to the client.
     */
    void disable();

    /**
     * Enables log domain, sends the entries collected so far to the client by means of the
     * <code>entryAdded</code> notification.
     */
    void enable();

    /**
     * start violation reporting.
     * 
     * @param config Configuration for violations.
     */
    void startViolationsReport(List<ViolationSetting> config);

    /**
     * Stop violation reporting.
     */
    void stopViolationsReport();
}
