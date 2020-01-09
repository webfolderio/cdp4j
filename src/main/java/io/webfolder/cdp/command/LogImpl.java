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
package io.webfolder.cdp.command;

import java.util.List;

import io.webfolder.cdp.session.SessionInvocationHandler;
import io.webfolder.cdp.type.log.ViolationSetting;

public class LogImpl implements Log {

    private static final Object[] EMPTY_VALUES = new Object[]{};
    private static final String[] EMPTY_ARGS = new String[]{};
    private final SessionInvocationHandler handler;

    public LogImpl(SessionInvocationHandler handler) {
        this.handler = handler;
    }

    @Override
    public void clear() {
        handler.invoke("Log", "clear", "Log.clear", null, void.class, null, true, false, false, EMPTY_ARGS,
                EMPTY_VALUES);
    }

    @Override
    public void disable() {
        handler.invoke("Log", "disable", "Log.disable", null, void.class, null, true, false, true, EMPTY_ARGS,
                EMPTY_VALUES);
    }

    @Override
    public void enable() {
        handler.invoke("Log", "enable", "Log.enable", null, void.class, null, true, true, false, EMPTY_ARGS,
                EMPTY_VALUES);
    }

    @Override
    public void startViolationsReport(List<ViolationSetting> config) {
        handler.invoke("Log", "startViolationsReport", "Log.startViolationsReport", null, void.class, null, true, false,
                false, new String[]{"config"}, new Object[]{config});
    }

    @Override
    public void stopViolationsReport() {
        handler.invoke("Log", "stopViolationsReport", "Log.stopViolationsReport", null, void.class, null, true, false,
                false, EMPTY_ARGS, EMPTY_VALUES);
    }
}
