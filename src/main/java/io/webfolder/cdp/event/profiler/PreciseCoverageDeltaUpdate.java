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
package io.webfolder.cdp.event.profiler;

import java.util.List;

import io.webfolder.cdp.annotation.Domain;
import io.webfolder.cdp.annotation.EventName;
import io.webfolder.cdp.annotation.Experimental;
import io.webfolder.cdp.type.profiler.ScriptCoverage;

/**
 * Reports coverage delta since the last poll (either from an event like this, or from
 * takePreciseCoverage for the current isolate
 * May only be sent if precise code
 * coverage has been started
 * This event can be trigged by the embedder to, for example,
 * trigger collection of coverage data immediatelly at a certain point in time
 */
@Experimental
@Domain("Profiler")
@EventName("preciseCoverageDeltaUpdate")
public class PreciseCoverageDeltaUpdate {
    private Double timestamp;

    private String occassion;

    private List<ScriptCoverage> result;

    /**
     * Monotonically increasing time (in seconds) when the coverage update was taken in the backend.
     */
    public Double getTimestamp() {
        return timestamp;
    }

    /**
     * Monotonically increasing time (in seconds) when the coverage update was taken in the backend.
     */
    public void setTimestamp(Double timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Identifier for distinguishing coverage events.
     */
    public String getOccassion() {
        return occassion;
    }

    /**
     * Identifier for distinguishing coverage events.
     */
    public void setOccassion(String occassion) {
        this.occassion = occassion;
    }

    /**
     * Coverage data for the current isolate.
     */
    public List<ScriptCoverage> getResult() {
        return result;
    }

    /**
     * Coverage data for the current isolate.
     */
    public void setResult(List<ScriptCoverage> result) {
        this.result = result;
    }
}
