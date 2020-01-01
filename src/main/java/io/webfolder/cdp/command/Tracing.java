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
import io.webfolder.cdp.type.constant.TransferMode;
import io.webfolder.cdp.type.tracing.RequestMemoryDumpResult;
import io.webfolder.cdp.type.tracing.StreamCompression;
import io.webfolder.cdp.type.tracing.StreamFormat;
import io.webfolder.cdp.type.tracing.TraceConfig;
import java.util.List;

@Experimental
@Domain("Tracing")
public interface Tracing {
    /**
     * Stop trace events collection.
     */
    void end();

    /**
     * Gets supported tracing categories.
     * 
     * @return A list of supported tracing categories.
     */
    @Returns("categories")
    List<String> getCategories();

    /**
     * Record a clock sync marker in the trace.
     * 
     * @param syncId The ID of this clock sync marker
     */
    void recordClockSyncMarker(String syncId);

    /**
     * Request a global memory dump.
     * 
     * @return RequestMemoryDumpResult
     */
    RequestMemoryDumpResult requestMemoryDump();

    /**
     * Start trace events collection.
     * 
     * @param categories Category/tag filter
     * @param options Tracing options
     * @param bufferUsageReportingInterval If set, the agent will issue bufferUsage events at this interval, specified in milliseconds
     * @param transferMode Whether to report trace events as series of dataCollected events or to save trace to a
     * stream (defaults to <code>ReportEvents</code>).
     * @param streamFormat Trace data format to use. This only applies when using <code>ReturnAsStream</code>
     * transfer mode (defaults to <code>json</code>).
     * @param streamCompression Compression format to use. This only applies when using <code>ReturnAsStream</code>
     * transfer mode (defaults to <code>none</code>)
     */
    void start(@Optional String categories, @Optional String options,
            @Optional Double bufferUsageReportingInterval, @Optional TransferMode transferMode,
            @Optional StreamFormat streamFormat, @Optional StreamCompression streamCompression,
            @Optional TraceConfig traceConfig);

    /**
     * Start trace events collection.
     */
    void start();
}
