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
import io.webfolder.cdp.command.Animation;
import io.webfolder.cdp.type.runtime.RemoteObject;
import java.util.List;

public class AnimationImpl implements Animation {

    private static final Object[] EMPTY_VALUES = new Object[]{};
    private static final String[] EMPTY_ARGS = new String[]{};
    private final SessionInvocationHandler handler;

    public AnimationImpl(SessionInvocationHandler handler) {
        this.handler = handler;
    }

    @Override
    public void disable() {
        handler.invoke("Animation", "disable", "Animation.disable", null, void.class, null, true, false, true,
                EMPTY_ARGS, EMPTY_VALUES);
    }

    @Override
    public void enable() {
        handler.invoke("Animation", "enable", "Animation.enable", null, void.class, null, true, true, false, EMPTY_ARGS,
                EMPTY_VALUES);
    }

    @Override
    public Double getCurrentTime(String id) {
        return (Double) handler.invoke("Animation", "getCurrentTime", "Animation.getCurrentTime", "currentTime",
                Double.class, null, false, false, false, new String[]{"id"}, new Object[]{id});
    }

    @Override
    public Double getPlaybackRate() {
        return (Double) handler.invoke("Animation", "getPlaybackRate", "Animation.getPlaybackRate", "playbackRate",
                Double.class, null, false, false, false, EMPTY_ARGS, EMPTY_VALUES);
    }

    @Override
    public void releaseAnimations(List<String> animations) {
        handler.invoke("Animation", "releaseAnimations", "Animation.releaseAnimations", null, void.class, null, true,
                false, false, new String[]{"animations"}, new Object[]{animations});
    }

    @Override
    public RemoteObject resolveAnimation(String animationId) {
        return (RemoteObject) handler.invoke("Animation", "resolveAnimation", "Animation.resolveAnimation",
                "remoteObject", RemoteObject.class, null, false, false, false, new String[]{"animationId"},
                new Object[]{animationId});
    }

    @Override
    public void seekAnimations(List<String> animations, Double currentTime) {
        handler.invoke("Animation", "seekAnimations", "Animation.seekAnimations", null, void.class, null, true, false,
                false, new String[]{"animations", "currentTime"}, new Object[]{animations, currentTime});
    }

    @Override
    public void setPaused(List<String> animations, Boolean paused) {
        handler.invoke("Animation", "setPaused", "Animation.setPaused", null, void.class, null, true, false, false,
                new String[]{"animations", "paused"}, new Object[]{animations, paused});
    }

    @Override
    public void setPlaybackRate(Double playbackRate) {
        handler.invoke("Animation", "setPlaybackRate", "Animation.setPlaybackRate", null, void.class, null, true, false,
                false, new String[]{"playbackRate"}, new Object[]{playbackRate});
    }

    @Override
    public void setTiming(String animationId, Double duration, Double delay) {
        handler.invoke("Animation", "setTiming", "Animation.setTiming", null, void.class, null, true, false, false,
                new String[]{"animationId", "duration", "delay"}, new Object[]{animationId, duration, delay});
    }
}