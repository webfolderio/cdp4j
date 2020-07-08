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

import io.webfolder.cdp.annotation.Domain;
import io.webfolder.cdp.annotation.Experimental;
import io.webfolder.cdp.annotation.Returns;
import io.webfolder.cdp.type.runtime.RemoteObject;
import java.util.List;

@Experimental
@Domain("Animation")
public interface Animation {
    /**
     * Disables animation domain notifications.
     */
    void disable();

    /**
     * Enables animation domain notifications.
     */
    void enable();

    /**
     * Returns the current time of the an animation.
     *
     * @param id Id of animation.
     *
     * @return Current time of the page.
     */
    @Returns("currentTime")
    Double getCurrentTime(String id);

    /**
     * Gets the playback rate of the document timeline.
     *
     * @return Playback rate for animations on page.
     */
    @Returns("playbackRate")
    Double getPlaybackRate();

    /**
     * Releases a set of animations to no longer be manipulated.
     *
     * @param animations List of animation ids to seek.
     */
    void releaseAnimations(List<String> animations);

    /**
     * Gets the remote object of the Animation.
     *
     * @param animationId Animation id.
     *
     * @return Corresponding remote object.
     */
    @Returns("remoteObject")
    RemoteObject resolveAnimation(String animationId);

    /**
     * Seek a set of animations to a particular time within each animation.
     *
     * @param animations List of animation ids to seek.
     * @param currentTime Set the current time of each animation.
     */
    void seekAnimations(List<String> animations, Double currentTime);

    /**
     * Sets the paused state of a set of animations.
     *
     * @param animations Animations to set the pause state of.
     * @param paused Paused state to set to.
     */
    void setPaused(List<String> animations, Boolean paused);

    /**
     * Sets the playback rate of the document timeline.
     *
     * @param playbackRate Playback rate for animations on page
     */
    void setPlaybackRate(Double playbackRate);

    /**
     * Sets the timing of an animation node.
     *
     * @param animationId Animation id.
     * @param duration Duration of the animation.
     * @param delay Delay of the animation.
     */
    void setTiming(String animationId, Double duration, Double delay);
}
