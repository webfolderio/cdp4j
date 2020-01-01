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
package io.webfolder.cdp.event.tracing;

import com.vimeo.stag.UseStag;

import io.webfolder.cdp.annotation.Domain;
import io.webfolder.cdp.annotation.EventName;

@Domain("Tracing")
@EventName("bufferUsage")
@UseStag
public class BufferUsage {
    private Double percentFull;

    private Double eventCount;

    private Double value;

    /**
     * A number in range [0..1] that indicates the used size of event buffer as a fraction of its
     * total size.
     */
    public Double getPercentFull() {
        return percentFull;
    }

    /**
     * A number in range [0..1] that indicates the used size of event buffer as a fraction of its
     * total size.
     */
    public void setPercentFull(Double percentFull) {
        this.percentFull = percentFull;
    }

    /**
     * An approximate number of events in the trace log.
     */
    public Double getEventCount() {
        return eventCount;
    }

    /**
     * An approximate number of events in the trace log.
     */
    public void setEventCount(Double eventCount) {
        this.eventCount = eventCount;
    }

    /**
     * A number in range [0..1] that indicates the used size of event buffer as a fraction of its
     * total size.
     */
    public Double getValue() {
        return value;
    }

    /**
     * A number in range [0..1] that indicates the used size of event buffer as a fraction of its
     * total size.
     */
    public void setValue(Double value) {
        this.value = value;
    }
}
