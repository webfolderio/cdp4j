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
package io.webfolder.cdp.type.animation;

import com.vimeo.stag.UseStag;

/**
 * AnimationEffect instance
 */
@UseStag
public class AnimationEffect {
    private Double delay;

    private Double endDelay;

    private Double iterationStart;

    private Double iterations;

    private Double duration;

    private String direction;

    private String fill;

    private Integer backendNodeId;

    private KeyframesRule keyframesRule;

    private String easing;

    /**
     * <code>AnimationEffect</code>'s delay.
     */
    public Double getDelay() {
        return delay;
    }

    /**
     * <code>AnimationEffect</code>'s delay.
     */
    public void setDelay(Double delay) {
        this.delay = delay;
    }

    /**
     * <code>AnimationEffect</code>'s end delay.
     */
    public Double getEndDelay() {
        return endDelay;
    }

    /**
     * <code>AnimationEffect</code>'s end delay.
     */
    public void setEndDelay(Double endDelay) {
        this.endDelay = endDelay;
    }

    /**
     * <code>AnimationEffect</code>'s iteration start.
     */
    public Double getIterationStart() {
        return iterationStart;
    }

    /**
     * <code>AnimationEffect</code>'s iteration start.
     */
    public void setIterationStart(Double iterationStart) {
        this.iterationStart = iterationStart;
    }

    /**
     * <code>AnimationEffect</code>'s iterations.
     */
    public Double getIterations() {
        return iterations;
    }

    /**
     * <code>AnimationEffect</code>'s iterations.
     */
    public void setIterations(Double iterations) {
        this.iterations = iterations;
    }

    /**
     * <code>AnimationEffect</code>'s iteration duration.
     */
    public Double getDuration() {
        return duration;
    }

    /**
     * <code>AnimationEffect</code>'s iteration duration.
     */
    public void setDuration(Double duration) {
        this.duration = duration;
    }

    /**
     * <code>AnimationEffect</code>'s playback direction.
     */
    public String getDirection() {
        return direction;
    }

    /**
     * <code>AnimationEffect</code>'s playback direction.
     */
    public void setDirection(String direction) {
        this.direction = direction;
    }

    /**
     * <code>AnimationEffect</code>'s fill mode.
     */
    public String getFill() {
        return fill;
    }

    /**
     * <code>AnimationEffect</code>'s fill mode.
     */
    public void setFill(String fill) {
        this.fill = fill;
    }

    /**
     * <code>AnimationEffect</code>'s target node.
     */
    public Integer getBackendNodeId() {
        return backendNodeId;
    }

    /**
     * <code>AnimationEffect</code>'s target node.
     */
    public void setBackendNodeId(Integer backendNodeId) {
        this.backendNodeId = backendNodeId;
    }

    /**
     * <code>AnimationEffect</code>'s keyframes.
     */
    public KeyframesRule getKeyframesRule() {
        return keyframesRule;
    }

    /**
     * <code>AnimationEffect</code>'s keyframes.
     */
    public void setKeyframesRule(KeyframesRule keyframesRule) {
        this.keyframesRule = keyframesRule;
    }

    /**
     * <code>AnimationEffect</code>'s timing function.
     */
    public String getEasing() {
        return easing;
    }

    /**
     * <code>AnimationEffect</code>'s timing function.
     */
    public void setEasing(String easing) {
        this.easing = easing;
    }
}
