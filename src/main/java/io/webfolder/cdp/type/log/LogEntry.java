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
package io.webfolder.cdp.type.log;

import java.util.List;

import com.vimeo.stag.UseStag;

import io.webfolder.cdp.type.constant.LogEntrySeverity;
import io.webfolder.cdp.type.constant.LogEntrySource;
import io.webfolder.cdp.type.runtime.RemoteObject;
import io.webfolder.cdp.type.runtime.StackTrace;

/**
 * Log entry
 */
@UseStag
public class LogEntry {
    private LogEntrySource source;

    private LogEntrySeverity level;

    private String text;

    private Double timestamp;

    private String url;

    private Integer lineNumber;

    private StackTrace stackTrace;

    private String networkRequestId;

    private String workerId;

    private List<RemoteObject> args;

    /**
     * Log entry source.
     */
    public LogEntrySource getSource() {
        return source;
    }

    /**
     * Log entry source.
     */
    public void setSource(LogEntrySource source) {
        this.source = source;
    }

    /**
     * Log entry severity.
     */
    public LogEntrySeverity getLevel() {
        return level;
    }

    /**
     * Log entry severity.
     */
    public void setLevel(LogEntrySeverity level) {
        this.level = level;
    }

    /**
     * Logged text.
     */
    public String getText() {
        return text;
    }

    /**
     * Logged text.
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Timestamp when this entry was added.
     */
    public Double getTimestamp() {
        return timestamp;
    }

    /**
     * Timestamp when this entry was added.
     */
    public void setTimestamp(Double timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * URL of the resource if known.
     */
    public String getUrl() {
        return url;
    }

    /**
     * URL of the resource if known.
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Line number in the resource.
     */
    public Integer getLineNumber() {
        return lineNumber;
    }

    /**
     * Line number in the resource.
     */
    public void setLineNumber(Integer lineNumber) {
        this.lineNumber = lineNumber;
    }

    /**
     * JavaScript stack trace.
     */
    public StackTrace getStackTrace() {
        return stackTrace;
    }

    /**
     * JavaScript stack trace.
     */
    public void setStackTrace(StackTrace stackTrace) {
        this.stackTrace = stackTrace;
    }

    /**
     * Identifier of the network request associated with this entry.
     */
    public String getNetworkRequestId() {
        return networkRequestId;
    }

    /**
     * Identifier of the network request associated with this entry.
     */
    public void setNetworkRequestId(String networkRequestId) {
        this.networkRequestId = networkRequestId;
    }

    /**
     * Identifier of the worker associated with this entry.
     */
    public String getWorkerId() {
        return workerId;
    }

    /**
     * Identifier of the worker associated with this entry.
     */
    public void setWorkerId(String workerId) {
        this.workerId = workerId;
    }

    /**
     * Call arguments.
     */
    public List<RemoteObject> getArgs() {
        return args;
    }

    /**
     * Call arguments.
     */
    public void setArgs(List<RemoteObject> args) {
        this.args = args;
    }
}
