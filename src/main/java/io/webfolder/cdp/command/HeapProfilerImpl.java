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
import io.webfolder.cdp.command.HeapProfiler;
import io.webfolder.cdp.type.heapprofiler.SamplingHeapProfile;
import io.webfolder.cdp.type.runtime.RemoteObject;

public class HeapProfilerImpl implements HeapProfiler {

    private static final Object[] EMPTY_VALUES = new Object[]{};
    private static final String[] EMPTY_ARGS = new String[]{};
    private final SessionInvocationHandler handler;

    public HeapProfilerImpl(SessionInvocationHandler handler) {
        this.handler = handler;
    }

    @Override
    public void addInspectedHeapObject(String heapObjectId) {
        handler.invoke("HeapProfiler", "addInspectedHeapObject", "HeapProfiler.addInspectedHeapObject", null,
                void.class, null, true, false, false, new String[]{"heapObjectId"}, new Object[]{heapObjectId});
    }

    @Override
    public void collectGarbage() {
        handler.invoke("HeapProfiler", "collectGarbage", "HeapProfiler.collectGarbage", null, void.class, null, true,
                false, false, EMPTY_ARGS, EMPTY_VALUES);
    }

    @Override
    public void disable() {
        handler.invoke("HeapProfiler", "disable", "HeapProfiler.disable", null, void.class, null, true, false, true,
                EMPTY_ARGS, EMPTY_VALUES);
    }

    @Override
    public void enable() {
        handler.invoke("HeapProfiler", "enable", "HeapProfiler.enable", null, void.class, null, true, true, false,
                EMPTY_ARGS, EMPTY_VALUES);
    }

    @Override
    public String getHeapObjectId(String objectId) {
        return (String) handler.invoke("HeapProfiler", "getHeapObjectId", "HeapProfiler.getHeapObjectId",
                "heapSnapshotObjectId", String.class, null, false, false, false, new String[]{"objectId"},
                new Object[]{objectId});
    }

    @Override
    public RemoteObject getObjectByHeapObjectId(String objectId, String objectGroup) {
        return (RemoteObject) handler.invoke("HeapProfiler", "getObjectByHeapObjectId",
                "HeapProfiler.getObjectByHeapObjectId", "result", RemoteObject.class, null, false, false, false,
                new String[]{"objectId", "objectGroup"}, new Object[]{objectId, objectGroup});
    }

    @Override
    public SamplingHeapProfile getSamplingProfile() {
        return (SamplingHeapProfile) handler.invoke("HeapProfiler", "getSamplingProfile",
                "HeapProfiler.getSamplingProfile", "profile", SamplingHeapProfile.class, null, false, false, false,
                EMPTY_ARGS, EMPTY_VALUES);
    }

    @Override
    public void startSampling(Double samplingInterval) {
        handler.invoke("HeapProfiler", "startSampling", "HeapProfiler.startSampling", null, void.class, null, true,
                false, false, new String[]{"samplingInterval"}, new Object[]{samplingInterval});
    }

    @Override
    public void startTrackingHeapObjects(Boolean trackAllocations) {
        handler.invoke("HeapProfiler", "startTrackingHeapObjects", "HeapProfiler.startTrackingHeapObjects", null,
                void.class, null, true, false, false, new String[]{"trackAllocations"}, new Object[]{trackAllocations});
    }

    @Override
    public SamplingHeapProfile stopSampling() {
        return (SamplingHeapProfile) handler.invoke("HeapProfiler", "stopSampling", "HeapProfiler.stopSampling",
                "profile", SamplingHeapProfile.class, null, false, false, false, EMPTY_ARGS, EMPTY_VALUES);
    }

    @Override
    public void stopTrackingHeapObjects(Boolean reportProgress, Boolean treatGlobalObjectsAsRoots) {
        handler.invoke("HeapProfiler", "stopTrackingHeapObjects", "HeapProfiler.stopTrackingHeapObjects", null,
                void.class, null, true, false, false, new String[]{"reportProgress", "treatGlobalObjectsAsRoots"},
                new Object[]{reportProgress, treatGlobalObjectsAsRoots});
    }

    @Override
    public void takeHeapSnapshot(Boolean reportProgress, Boolean treatGlobalObjectsAsRoots) {
        handler.invoke("HeapProfiler", "takeHeapSnapshot", "HeapProfiler.takeHeapSnapshot", null, void.class, null,
                true, false, false, new String[]{"reportProgress", "treatGlobalObjectsAsRoots"},
                new Object[]{reportProgress, treatGlobalObjectsAsRoots});
    }

    @Override
    public RemoteObject getObjectByHeapObjectId(String objectId) {
        return (RemoteObject) handler.invoke("HeapProfiler", "getObjectByHeapObjectId",
                "HeapProfiler.getObjectByHeapObjectId", "result", RemoteObject.class, null, false, false, false,
                new String[]{"objectId"}, new Object[]{objectId});
    }

    @Override
    public void startSampling() {
        handler.invoke("HeapProfiler", "startSampling", "HeapProfiler.startSampling", null, void.class, null, true,
                false, false, EMPTY_ARGS, EMPTY_VALUES);
    }

    @Override
    public void startTrackingHeapObjects() {
        handler.invoke("HeapProfiler", "startTrackingHeapObjects", "HeapProfiler.startTrackingHeapObjects", null,
                void.class, null, true, false, false, EMPTY_ARGS, EMPTY_VALUES);
    }

    @Override
    public void stopTrackingHeapObjects() {
        handler.invoke("HeapProfiler", "stopTrackingHeapObjects", "HeapProfiler.stopTrackingHeapObjects", null,
                void.class, null, true, false, false, EMPTY_ARGS, EMPTY_VALUES);
    }

    @Override
    public void takeHeapSnapshot() {
        handler.invoke("HeapProfiler", "takeHeapSnapshot", "HeapProfiler.takeHeapSnapshot", null, void.class, null,
                true, false, false, EMPTY_ARGS, EMPTY_VALUES);
    }
}
