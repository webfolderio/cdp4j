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

import io.webfolder.cdp.session.SessionInvocationHandler;
import io.webfolder.cdp.command.HeadlessExperimental;
import io.webfolder.cdp.type.headlessexperimental.BeginFrameResult;
import io.webfolder.cdp.type.headlessexperimental.ScreenshotParams;

public class HeadlessExperimentalImpl implements HeadlessExperimental {

    private static final Object[] EMPTY_VALUES = new Object[]{};
    private static final String[] EMPTY_ARGS = new String[]{};
    private final SessionInvocationHandler handler;

    public HeadlessExperimentalImpl(SessionInvocationHandler handler) {
        this.handler = handler;
    }

    @Override
    public BeginFrameResult beginFrame(Double frameTimeTicks, Double interval, Boolean noDisplayUpdates,
            ScreenshotParams screenshot) {
        return (BeginFrameResult) handler.invoke("HeadlessExperimental", "beginFrame",
                "HeadlessExperimental.beginFrame", null, BeginFrameResult.class, null, false, false, false,
                new String[]{"frameTimeTicks", "interval", "noDisplayUpdates", "screenshot"},
                new Object[]{frameTimeTicks, interval, noDisplayUpdates, screenshot});
    }

    @Override
    public void disable() {
        handler.invoke("HeadlessExperimental", "disable", "HeadlessExperimental.disable", null, void.class, null, true,
                false, true, EMPTY_ARGS, EMPTY_VALUES);
    }

    @Override
    public void enable() {
        handler.invoke("HeadlessExperimental", "enable", "HeadlessExperimental.enable", null, void.class, null, true,
                true, false, EMPTY_ARGS, EMPTY_VALUES);
    }

    @Override
    public BeginFrameResult beginFrame() {
        return (BeginFrameResult) handler.invoke("HeadlessExperimental", "beginFrame",
                "HeadlessExperimental.beginFrame", null, BeginFrameResult.class, null, false, false, false, EMPTY_ARGS,
                EMPTY_VALUES);
    }
}