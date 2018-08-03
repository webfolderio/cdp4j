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
package io.webfolder.cdp.logger;

import static io.webfolder.cdp.logger.CdpLogLevel.*;
import static io.webfolder.cdp.logger.CdpLogLevel.Error;
import static io.webfolder.cdp.logger.MessageFormatter.arrayFormat;

public class CdpConsoleLogger implements CdpLogger {

    private final CdpLogLevel level;

    public CdpConsoleLogger() {
        this(Info);
    }

    public CdpConsoleLogger(final CdpLogLevel level) {
        this.level = level;
    }

    @Override
    public void info(String message, Object... args) {
        if (Info.equals(level) ||
                Debug.equals(level)) {
            FormattingTuple tuple = arrayFormat(message, args);
            System.out.println("[INFO] " + tuple.getMessage());
        }
    }

    @Override
    public void debug(String message, Object... args) {
        if (Debug.equals(level)) {
            FormattingTuple tuple = arrayFormat(message, args);
            System.out.println("[DEBUG] " + tuple.getMessage());
        }
    }

    @Override
    public void warn(String message, Object... args) {
        if (Info.equals(level)     ||
                Warn.equals(level) ||
                Debug.equals(level)) {
            FormattingTuple tuple = arrayFormat(message, args);
            System.out.println("[WARN] " + tuple.getMessage());
        }
    }

    @Override
    public void error(String message, Object... args) {
        if (Info.equals(level)      ||
                Warn.equals(level)  ||
                Error.equals(level) ||
                Debug.equals(level)) {
            FormattingTuple tuple = arrayFormat(message, args);
            System.out.println("[ERROR] " + tuple.getMessage());
        }
    }

    @Override
    public void error(String message, Throwable t) {
        if (Info.equals(level)      ||
                Warn.equals(level)  ||
                Error.equals(level) ||
                Debug.equals(level)) {
            System.err.println("[ERROR] " + message);
            if ( t != null ) {
                t.printStackTrace();
            }
        }
    }
}
