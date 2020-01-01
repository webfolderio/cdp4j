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
import io.webfolder.cdp.command.Tracing;
import java.util.List;
import io.webfolder.cdp.type.constant.TransferMode;
import io.webfolder.cdp.type.tracing.RequestMemoryDumpResult;
import io.webfolder.cdp.type.tracing.StreamCompression;
import io.webfolder.cdp.type.tracing.StreamFormat;
import io.webfolder.cdp.type.tracing.TraceConfig;
import com.google.gson.reflect.TypeToken;

public class TracingImpl implements Tracing {

    private static final String[] EMPTY_ARGS = new String[] {};
    private static final Object[] EMPTY_VALUES = new Object[] {};
    private static final TypeToken<List<String>> GET_CATEGORIES = new TypeToken<List<String>>() {
    };
    private final SessionInvocationHandler handler;

    public TracingImpl(SessionInvocationHandler handler) {
        this.handler = handler;
    }

    @Override
    public void end() {
        handler.invoke("Tracing", "end", "Tracing.end", null, void.class, null, true, false, false, EMPTY_ARGS,
                EMPTY_VALUES);
    }

    @Override
    @java.lang.SuppressWarnings("unchecked")
    public List<String> getCategories() {
        return (List<String>) handler.invoke("Tracing", "getCategories", "Tracing.getCategories", "categories",
                List.class, GET_CATEGORIES.getType(), false, false, false, EMPTY_ARGS, EMPTY_VALUES);
    }

    @Override
    public void recordClockSyncMarker(String syncId) {
        handler.invoke("Tracing", "recordClockSyncMarker", "Tracing.recordClockSyncMarker", null, void.class, null,
                true, false, false, new String[] { "syncId" }, new Object[] { syncId });
    }

    @Override
    public RequestMemoryDumpResult requestMemoryDump() {
        return (RequestMemoryDumpResult) handler.invoke("Tracing", "requestMemoryDump", "Tracing.requestMemoryDump",
                null, RequestMemoryDumpResult.class, null, false, false, false, EMPTY_ARGS, EMPTY_VALUES);
    }

    @Override
    public void start() {
        handler.invoke("Tracing", "start", "Tracing.start", null, void.class, null, true, false, false, EMPTY_ARGS,
                EMPTY_VALUES);
    }

    @Override
    public void start(String categories, String options, Double bufferUsageReportingInterval, TransferMode transferMode,
            StreamFormat streamFormat, StreamCompression streamCompression, TraceConfig traceConfig) {
        handler.invoke("Tracing", "start", "Tracing.start", null, void.class, null, true, false, false,
                new String[] { "categories", "options", "bufferUsageReportingInterval", "transferMode", "streamFormat",
                        "streamCompression", "traceConfig" },
                new Object[] { categories, options, bufferUsageReportingInterval, transferMode, streamFormat,
                        streamCompression, traceConfig });
    }
}