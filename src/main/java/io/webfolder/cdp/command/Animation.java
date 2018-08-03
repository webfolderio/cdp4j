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
