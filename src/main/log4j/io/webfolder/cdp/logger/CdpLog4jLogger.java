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
package io.webfolder.cdp.logger;

import static io.webfolder.cdp.logger.MessageFormatter.arrayFormat;
import static org.apache.log4j.Level.ERROR;
import static org.apache.log4j.Level.WARN;

import org.apache.log4j.Logger;

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
