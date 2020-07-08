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
package io.webfolder.cdp.type.webaudio;

/**
 * Fields in AudioContext that change in real-time
 */
public class ContextRealtimeData {
    private Double currentTime;

    private Double renderCapacity;

    private Double callbackIntervalMean;

    private Double callbackIntervalVariance;

    /**
     * The current context time in second in BaseAudioContext.
     */
    public Double getCurrentTime() {
        return currentTime;
    }

    /**
     * The current context time in second in BaseAudioContext.
     */
    public void setCurrentTime(Double currentTime) {
        this.currentTime = currentTime;
    }

    /**
     * The time spent on rendering graph divided by render qunatum duration,
     * and multiplied by 100. 100 means the audio renderer reached the full
     * capacity and glitch may occur.
     */
    public Double getRenderCapacity() {
        return renderCapacity;
    }

    /**
     * The time spent on rendering graph divided by render qunatum duration,
     * and multiplied by 100. 100 means the audio renderer reached the full
     * capacity and glitch may occur.
     */
    public void setRenderCapacity(Double renderCapacity) {
        this.renderCapacity = renderCapacity;
    }

    /**
     * A running mean of callback interval.
     */
    public Double getCallbackIntervalMean() {
        return callbackIntervalMean;
    }

    /**
     * A running mean of callback interval.
     */
    public void setCallbackIntervalMean(Double callbackIntervalMean) {
        this.callbackIntervalMean = callbackIntervalMean;
    }

    /**
     * A running variance of callback interval.
     */
    public Double getCallbackIntervalVariance() {
        return callbackIntervalVariance;
    }

    /**
     * A running variance of callback interval.
     */
    public void setCallbackIntervalVariance(Double callbackIntervalVariance) {
        this.callbackIntervalVariance = callbackIntervalVariance;
    }
}
