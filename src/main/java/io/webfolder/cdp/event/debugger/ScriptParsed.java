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
package io.webfolder.cdp.event.debugger;

import com.vimeo.stag.UseStag;

import io.webfolder.cdp.annotation.Domain;
import io.webfolder.cdp.annotation.EventName;
import io.webfolder.cdp.type.runtime.StackTrace;

/**
 * Fired when virtual machine parses script
 * This event is also fired for all known and uncollected
 * scripts upon enabling debugger
 */
@Domain("Debugger")
@EventName("scriptParsed")
@UseStag
public class ScriptParsed {
    private String scriptId;

    private String url;

    private Integer startLine;

    private Integer startColumn;

    private Integer endLine;

    private Integer endColumn;

    private Integer executionContextId;

    private String hash;

    private Object executionContextAuxData;

    private Boolean isLiveEdit;

    private String sourceMapURL;

    private Boolean hasSourceURL;

    private Boolean isModule;

    private Integer length;

    private StackTrace stackTrace;

    /**
     * Identifier of the script parsed.
     */
    public String getScriptId() {
        return scriptId;
    }

    /**
     * Identifier of the script parsed.
     */
    public void setScriptId(String scriptId) {
        this.scriptId = scriptId;
    }

    /**
     * URL or name of the script parsed (if any).
     */
    public String getUrl() {
        return url;
    }

    /**
     * URL or name of the script parsed (if any).
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Line offset of the script within the resource with given URL (for script tags).
     */
    public Integer getStartLine() {
        return startLine;
    }

    /**
     * Line offset of the script within the resource with given URL (for script tags).
     */
    public void setStartLine(Integer startLine) {
        this.startLine = startLine;
    }

    /**
     * Column offset of the script within the resource with given URL.
     */
    public Integer getStartColumn() {
        return startColumn;
    }

    /**
     * Column offset of the script within the resource with given URL.
     */
    public void setStartColumn(Integer startColumn) {
        this.startColumn = startColumn;
    }

    /**
     * Last line of the script.
     */
    public Integer getEndLine() {
        return endLine;
    }

    /**
     * Last line of the script.
     */
    public void setEndLine(Integer endLine) {
        this.endLine = endLine;
    }

    /**
     * Length of the last line of the script.
     */
    public Integer getEndColumn() {
        return endColumn;
    }

    /**
     * Length of the last line of the script.
     */
    public void setEndColumn(Integer endColumn) {
        this.endColumn = endColumn;
    }

    /**
     * Specifies script creation context.
     */
    public Integer getExecutionContextId() {
        return executionContextId;
    }

    /**
     * Specifies script creation context.
     */
    public void setExecutionContextId(Integer executionContextId) {
        this.executionContextId = executionContextId;
    }

    /**
     * Content hash of the script.
     */
    public String getHash() {
        return hash;
    }

    /**
     * Content hash of the script.
     */
    public void setHash(String hash) {
        this.hash = hash;
    }

    /**
     * Embedder-specific auxiliary data.
     */
    public Object getExecutionContextAuxData() {
        return executionContextAuxData;
    }

    /**
     * Embedder-specific auxiliary data.
     */
    public void setExecutionContextAuxData(Object executionContextAuxData) {
        this.executionContextAuxData = executionContextAuxData;
    }

    /**
     * True, if this script is generated as a result of the live edit operation.
     */
    public Boolean getIsLiveEdit() {
        return isLiveEdit;
    }

    /**
     * True, if this script is generated as a result of the live edit operation.
     */
    public void setIsLiveEdit(Boolean isLiveEdit) {
        this.isLiveEdit = isLiveEdit;
    }

    /**
     * URL of source map associated with script (if any).
     */
    public String getSourceMapURL() {
        return sourceMapURL;
    }

    /**
     * URL of source map associated with script (if any).
     */
    public void setSourceMapURL(String sourceMapURL) {
        this.sourceMapURL = sourceMapURL;
    }

    /**
     * True, if this script has sourceURL.
     */
    public Boolean getHasSourceURL() {
        return hasSourceURL;
    }

    /**
     * True, if this script has sourceURL.
     */
    public void setHasSourceURL(Boolean hasSourceURL) {
        this.hasSourceURL = hasSourceURL;
    }

    /**
     * True, if this script is ES6 module.
     */
    public Boolean getIsModule() {
        return isModule;
    }

    /**
     * True, if this script is ES6 module.
     */
    public void setIsModule(Boolean isModule) {
        this.isModule = isModule;
    }

    /**
     * This script length.
     */
    public Integer getLength() {
        return length;
    }

    /**
     * This script length.
     */
    public void setLength(Integer length) {
        this.length = length;
    }

    /**
     * JavaScript top stack frame of where the script parsed event was triggered if available.
     */
    public StackTrace getStackTrace() {
        return stackTrace;
    }

    /**
     * JavaScript top stack frame of where the script parsed event was triggered if available.
     */
    public void setStackTrace(StackTrace stackTrace) {
        this.stackTrace = stackTrace;
    }
}
