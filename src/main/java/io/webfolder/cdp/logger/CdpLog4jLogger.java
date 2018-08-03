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

import org.apache.log4j.Logger;

import static io.webfolder.cdp.logger.MessageFormatter.arrayFormat;
import static org.apache.log4j.Level.ERROR;
import static org.apache.log4j.Level.WARN;

public class CdpLog4jLogger implements CdpLogger {

    private final Logger logger;

    public CdpLog4jLogger(final String name) {
        logger = Logger.getLogger(name);
    }

    @Override
    public void info(String message, Object... args) {
        if (logger.isInfoEnabled()) {
            FormattingTuple tuple = arrayFormat(message, args);
            logger.info(tuple.getMessage());
        }
    }

    @Override
    public void debug(String message, Object... args) {
        if (logger.isDebugEnabled()) {
            FormattingTuple tuple = arrayFormat(message, args);
            logger.debug(tuple.getMessage());
        }
    }

    @Override
    public void warn(String message, Object... args) {
        if (logger.isEnabledFor(WARN)) {
            FormattingTuple tuple = arrayFormat(message, args);
            logger.log(WARN, tuple.getMessage());
        }
    }

    @Override
    public void error(String message, Object... args) {
        if (logger.isEnabledFor(ERROR)) {
            FormattingTuple tuple = arrayFormat(message, args);
            logger.error(tuple.getMessage());
        }
    }

    @Override
    public void error(String message, Throwable t) {
        if (logger.isEnabledFor(ERROR)) {
            logger.error(message, t);
        }
    }
}
