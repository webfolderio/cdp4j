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
import io.webfolder.cdp.command.Cast;

public class CastImpl implements Cast {

    private static final String[] EMPTY_ARGS = new String[] {};
    private static final Object[] EMPTY_VALUES = new Object[] {};
    private final SessionInvocationHandler handler;

    public CastImpl(SessionInvocationHandler handler) {
        this.handler = handler;
    }

    @Override
    public void disable() {
        handler.invoke("Cast", "disable", "Cast.disable", null, void.class, null, true, false, true, EMPTY_ARGS,
                EMPTY_VALUES);
    }

    @Override
    public void enable() {
        handler.invoke("Cast", "enable", "Cast.enable", null, void.class, null, true, true, false, EMPTY_ARGS,
                EMPTY_VALUES);
    }

    @Override
    public void enable(String presentationUrl) {
        handler.invoke("Cast", "enable", "Cast.enable", null, void.class, null, true, true, false,
                new String[] { "presentationUrl" }, new Object[] { presentationUrl });
    }

    @Override
    public void setSinkToUse(String sinkName) {
        handler.invoke("Cast", "setSinkToUse", "Cast.setSinkToUse", null, void.class, null, true, false, false,
                new String[] { "sinkName" }, new Object[] { sinkName });
    }

    @Override
    public void startTabMirroring(String sinkName) {
        handler.invoke("Cast", "startTabMirroring", "Cast.startTabMirroring", null, void.class, null, true, false,
                false, new String[] { "sinkName" }, new Object[] { sinkName });
    }

    @Override
    public void stopCasting(String sinkName) {
        handler.invoke("Cast", "stopCasting", "Cast.stopCasting", null, void.class, null, true, false, false,
                new String[] { "sinkName" }, new Object[] { sinkName });
    }
}