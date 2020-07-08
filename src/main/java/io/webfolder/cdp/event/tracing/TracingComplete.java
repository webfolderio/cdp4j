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

import io.webfolder.cdp.annotation.Domain;
import io.webfolder.cdp.annotation.EventName;
import io.webfolder.cdp.type.tracing.StreamCompression;
import io.webfolder.cdp.type.tracing.StreamFormat;

/**
 * Signals that tracing is stopped and there is no trace buffers pending flush, all data were
 * delivered via dataCollected events
 */
@Domain("Tracing")
@EventName("tracingComplete")
public class TracingComplete {
    private Boolean dataLossOccurred;

    private String stream;

    private StreamFormat traceFormat;

    private StreamCompression streamCompression;

    /**
     * Indicates whether some trace data is known to have been lost, e.g. because the trace ring
     * buffer wrapped around.
     */
    public Boolean isDataLossOccurred() {
        return dataLossOccurred;
    }

    /**
     * Indicates whether some trace data is known to have been lost, e.g. because the trace ring
     * buffer wrapped around.
     */
    public void setDataLossOccurred(Boolean dataLossOccurred) {
        this.dataLossOccurred = dataLossOccurred;
    }

    /**
     * A handle of the stream that holds resulting trace data.
     */
    public String getStream() {
        return stream;
    }

    /**
     * A handle of the stream that holds resulting trace data.
     */
    public void setStream(String stream) {
        this.stream = stream;
    }

    /**
     * Trace data format of returned stream.
     */
    public StreamFormat getTraceFormat() {
        return traceFormat;
    }

    /**
     * Trace data format of returned stream.
     */
    public void setTraceFormat(StreamFormat traceFormat) {
        this.traceFormat = traceFormat;
    }

    /**
     * Compression format of returned stream.
     */
    public StreamCompression getStreamCompression() {
        return streamCompression;
    }

    /**
     * Compression format of returned stream.
     */
    public void setStreamCompression(StreamCompression streamCompression) {
        this.streamCompression = streamCompression;
    }
}
