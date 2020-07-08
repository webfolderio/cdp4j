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
import io.webfolder.cdp.annotation.Optional;
import io.webfolder.cdp.annotation.Returns;
import io.webfolder.cdp.type.memory.GetDOMCountersResult;
import io.webfolder.cdp.type.memory.PressureLevel;
import io.webfolder.cdp.type.memory.SamplingProfile;

@Experimental
@Domain("Memory")
public interface Memory {
    /**
     *
     * @return GetDOMCountersResult
     */
    GetDOMCountersResult getDOMCounters();

    void prepareForLeakDetection();

    /**
     * Simulate OomIntervention by purging V8 memory.
     */
    void forciblyPurgeJavaScriptMemory();

    /**
     * Enable/disable suppressing memory pressure notifications in all processes.
     *
     * @param suppressed If true, memory pressure notifications will be suppressed.
     */
    void setPressureNotificationsSuppressed(Boolean suppressed);

    /**
     * Simulate a memory pressure notification in all processes.
     *
     * @param level Memory pressure level of the notification.
     */
    void simulatePressureNotification(PressureLevel level);

    /**
     * Start collecting native memory profile.
     *
     * @param samplingInterval Average number of bytes between samples.
     * @param suppressRandomness Do not randomize intervals between samples.
     */
    void startSampling(@Optional Integer samplingInterval, @Optional Boolean suppressRandomness);

    /**
     * Stop collecting native memory profile.
     */
    void stopSampling();

    /**
     * Retrieve native memory allocations profile
     * collected since renderer process startup.
     */
    @Returns("profile")
    SamplingProfile getAllTimeSamplingProfile();

    /**
     * Retrieve native memory allocations profile
     * collected since browser process startup.
     */
    @Returns("profile")
    SamplingProfile getBrowserSamplingProfile();

    /**
     * Retrieve native memory allocations profile collected since last
     * startSampling call.
     */
    @Returns("profile")
    SamplingProfile getSamplingProfile();

    /**
     * Start collecting native memory profile.
     */
    void startSampling();
}
