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
import io.webfolder.cdp.type.heapprofiler.SamplingHeapProfile;
import io.webfolder.cdp.type.runtime.RemoteObject;

@Experimental
@Domain("HeapProfiler")
public interface HeapProfiler {
    /**
     * Enables console to refer to the node with given id via  (see Command Line API for more details
     *  functions).
     * 
     * @param heapObjectId Heap snapshot object id to be accessible by means of x command line API.
     */
    void addInspectedHeapObject(String heapObjectId);

    void collectGarbage();

    void disable();

    void enable();

    @Returns("heapSnapshotObjectId")
    String getHeapObjectId(String objectId);

    @Returns("result")
    RemoteObject getObjectByHeapObjectId(String objectId, @Optional String objectGroup);

    @Returns("profile")
    SamplingHeapProfile getSamplingProfile();

    void startSampling(@Optional Double samplingInterval);

    void startTrackingHeapObjects(@Optional Boolean trackAllocations);

    @Returns("profile")
    SamplingHeapProfile stopSampling();

    void stopTrackingHeapObjects(@Optional Boolean reportProgress);

    void takeHeapSnapshot(@Optional Boolean reportProgress);

    @Returns("result")
    RemoteObject getObjectByHeapObjectId(String objectId);

    void startSampling();

    void startTrackingHeapObjects();

    void stopTrackingHeapObjects();

    void takeHeapSnapshot();
}
