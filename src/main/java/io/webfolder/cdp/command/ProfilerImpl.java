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

import com.google.gson.reflect.TypeToken;

import io.webfolder.cdp.session.SessionInvocationHandler;
import io.webfolder.cdp.type.profiler.CounterInfo;
import io.webfolder.cdp.type.profiler.Profile;
import io.webfolder.cdp.type.profiler.ScriptCoverage;
import io.webfolder.cdp.type.profiler.ScriptTypeProfile;

public class ProfilerImpl implements Profiler {

    private static final Object[] EMPTY_VALUES = new Object[]{};
    private static final String[] EMPTY_ARGS = new String[]{};
    private final SessionInvocationHandler handler;
    private static final TypeToken<List<ScriptCoverage>> TT_LIST_SCRIPT_COVERAGE = new TypeToken<List<ScriptCoverage>>() { };
    private static final TypeToken<List<ScriptTypeProfile>> TT_LIST_SCRIPT_TYPE_PROFILE = new TypeToken<List<ScriptTypeProfile>>() { };
    private static final TypeToken<List<CounterInfo>> TT_LIST_COUNTER_INFO = new TypeToken<List<CounterInfo>>() { };

    public ProfilerImpl(SessionInvocationHandler handler) {
        this.handler = handler;
    }

    @Override
    public void disable() {
        handler.invoke("Profiler", "disable", "Profiler.disable", null, void.class, null, true, false, true, EMPTY_ARGS,
                EMPTY_VALUES);
    }

    @Override
    public void enable() {
        handler.invoke("Profiler", "enable", "Profiler.enable", null, void.class, null, true, true, false, EMPTY_ARGS,
                EMPTY_VALUES);
    }

    @Override
    @java.lang.SuppressWarnings("unchecked")
    public List<ScriptCoverage> getBestEffortCoverage() {
        return (List<ScriptCoverage>) handler.invoke("Profiler", "getBestEffortCoverage",
                "Profiler.getBestEffortCoverage", "result", List.class, TT_LIST_SCRIPT_COVERAGE.getType(), false,
                false, false, EMPTY_ARGS, EMPTY_VALUES);
    }

    @Override
    public void setSamplingInterval(Integer interval) {
        handler.invoke("Profiler", "setSamplingInterval", "Profiler.setSamplingInterval", null, void.class, null, true,
                false, false, new String[]{"interval"}, new Object[]{interval});
    }

    @Override
    public void start() {
        handler.invoke("Profiler", "start", "Profiler.start", null, void.class, null, true, false, false, EMPTY_ARGS,
                EMPTY_VALUES);
    }

    @Override
    public void startPreciseCoverage(Boolean callCount, Boolean detailed) {
        handler.invoke("Profiler", "startPreciseCoverage", "Profiler.startPreciseCoverage", null, void.class, null,
                true, false, false, new String[]{"callCount", "detailed"}, new Object[]{callCount, detailed});
    }

    @Override
    public void startTypeProfile() {
        handler.invoke("Profiler", "startTypeProfile", "Profiler.startTypeProfile", null, void.class, null, true, false,
                false, EMPTY_ARGS, EMPTY_VALUES);
    }

    @Override
    public Profile stop() {
        return (Profile) handler.invoke("Profiler", "stop", "Profiler.stop", "profile", Profile.class, null, false,
                false, false, EMPTY_ARGS, EMPTY_VALUES);
    }

    @Override
    public void stopPreciseCoverage() {
        handler.invoke("Profiler", "stopPreciseCoverage", "Profiler.stopPreciseCoverage", null, void.class, null, true,
                false, false, EMPTY_ARGS, EMPTY_VALUES);
    }

    @Override
    public void stopTypeProfile() {
        handler.invoke("Profiler", "stopTypeProfile", "Profiler.stopTypeProfile", null, void.class, null, true, false,
                false, EMPTY_ARGS, EMPTY_VALUES);
    }

    @Override
    @java.lang.SuppressWarnings("unchecked")
    public List<ScriptCoverage> takePreciseCoverage() {
        return (List<ScriptCoverage>) handler.invoke("Profiler", "takePreciseCoverage", "Profiler.takePreciseCoverage",
                "result", List.class, TT_LIST_SCRIPT_COVERAGE.getType(), false, false, false, EMPTY_ARGS, EMPTY_VALUES);
    }

    @Override
    @java.lang.SuppressWarnings("unchecked")
    public List<ScriptTypeProfile> takeTypeProfile() {
        return (List<ScriptTypeProfile>) handler.invoke("Profiler", "takeTypeProfile", "Profiler.takeTypeProfile",
                "result", List.class, TT_LIST_SCRIPT_TYPE_PROFILE.getType(), false, false, false, EMPTY_ARGS, EMPTY_VALUES);
    }

    @Override
    public void enableRuntimeCallStats() {
        handler.invoke("Profiler", "enableRuntimeCallStats", "Profiler.enableRuntimeCallStats", null, void.class, null,
                true, false, false, EMPTY_ARGS, EMPTY_VALUES);
    }

    @Override
    public void disableRuntimeCallStats() {
        handler.invoke("Profiler", "disableRuntimeCallStats", "Profiler.disableRuntimeCallStats", null, void.class,
                null, true, false, false, EMPTY_ARGS, EMPTY_VALUES);
    }

    @Override
    @java.lang.SuppressWarnings("unchecked")
    public List<CounterInfo> getRuntimeCallStats() {
        return (List<CounterInfo>) handler.invoke("Profiler", "getRuntimeCallStats", "Profiler.getRuntimeCallStats",
                "result", List.class, TT_LIST_COUNTER_INFO.getType(), false, false, false, EMPTY_ARGS, EMPTY_VALUES);
    }

    @Override
    public void startPreciseCoverage() {
        handler.invoke("Profiler", "startPreciseCoverage", "Profiler.startPreciseCoverage", null, void.class, null,
                true, false, false, EMPTY_ARGS, EMPTY_VALUES);
    }
}
