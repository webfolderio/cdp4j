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
import io.webfolder.cdp.command.Memory;
import io.webfolder.cdp.type.memory.GetDOMCountersResult;
import io.webfolder.cdp.type.memory.PressureLevel;
import io.webfolder.cdp.type.memory.SamplingProfile;

public class MemoryImpl implements Memory {

    private static final String[] EMPTY_ARGS = new String[] {};
    private static final Object[] EMPTY_VALUES = new Object[] {};
    private final SessionInvocationHandler handler;

    public MemoryImpl(SessionInvocationHandler handler) {
        this.handler = handler;
    }

    @Override
    public void forciblyPurgeJavaScriptMemory() {
        handler.invoke("Memory", "forciblyPurgeJavaScriptMemory", "Memory.forciblyPurgeJavaScriptMemory", null,
                void.class, null, true, false, false, EMPTY_ARGS, EMPTY_VALUES);
    }

    @Override
    public SamplingProfile getAllTimeSamplingProfile() {
        return (SamplingProfile) handler.invoke("Memory", "getAllTimeSamplingProfile",
                "Memory.getAllTimeSamplingProfile", "profile", SamplingProfile.class, null, false, false, false,
                EMPTY_ARGS, EMPTY_VALUES);
    }

    @Override
    public SamplingProfile getBrowserSamplingProfile() {
        return (SamplingProfile) handler.invoke("Memory", "getBrowserSamplingProfile",
                "Memory.getBrowserSamplingProfile", "profile", SamplingProfile.class, null, false, false, false,
                EMPTY_ARGS, EMPTY_VALUES);
    }

    @Override
    public GetDOMCountersResult getDOMCounters() {
        return (GetDOMCountersResult) handler.invoke("Memory", "getDOMCounters", "Memory.getDOMCounters", null,
                GetDOMCountersResult.class, null, false, false, false, EMPTY_ARGS, EMPTY_VALUES);
    }

    @Override
    public SamplingProfile getSamplingProfile() {
        return (SamplingProfile) handler.invoke("Memory", "getSamplingProfile", "Memory.getSamplingProfile", "profile",
                SamplingProfile.class, null, false, false, false, EMPTY_ARGS, EMPTY_VALUES);
    }

    @Override
    public void prepareForLeakDetection() {
        handler.invoke("Memory", "prepareForLeakDetection", "Memory.prepareForLeakDetection", null, void.class, null,
                true, false, false, EMPTY_ARGS, EMPTY_VALUES);
    }

    @Override
    public void setPressureNotificationsSuppressed(Boolean suppressed) {
        handler.invoke("Memory", "setPressureNotificationsSuppressed", "Memory.setPressureNotificationsSuppressed",
                null, void.class, null, true, false, false, new String[] { "suppressed" }, new Object[] { suppressed });
    }

    @Override
    public void simulatePressureNotification(PressureLevel level) {
        handler.invoke("Memory", "simulatePressureNotification", "Memory.simulatePressureNotification", null,
                void.class, null, true, false, false, new String[] { "level" }, new Object[] { level });
    }

    @Override
    public void startSampling() {
        handler.invoke("Memory", "startSampling", "Memory.startSampling", null, void.class, null, true, false, false,
                EMPTY_ARGS, EMPTY_VALUES);
    }

    @Override
    public void startSampling(Integer samplingInterval, Boolean suppressRandomness) {
        handler.invoke("Memory", "startSampling", "Memory.startSampling", null, void.class, null, true, false, false,
                new String[] { "samplingInterval", "suppressRandomness" },
                new Object[] { samplingInterval, suppressRandomness });
    }

    @Override
    public void stopSampling() {
        handler.invoke("Memory", "stopSampling", "Memory.stopSampling", null, void.class, null, true, false, false,
                EMPTY_ARGS, EMPTY_VALUES);
    }
}