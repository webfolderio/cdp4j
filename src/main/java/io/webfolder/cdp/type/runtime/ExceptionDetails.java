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
package io.webfolder.cdp.type.runtime;

import com.vimeo.stag.UseStag;

/**
 * Detailed information about exception (or error) that was thrown during script compilation or
 * execution
 */
@UseStag
public class ExceptionDetails {
    private Integer exceptionId;

    private String text;

    private Integer lineNumber;

    private Integer columnNumber;

    private String scriptId;

    private String url;

    private StackTrace stackTrace;

    private RemoteObject exception;

    private Integer executionContextId;

    /**
     * Exception id.
     */
    public Integer getExceptionId() {
        return exceptionId;
    }

    /**
     * Exception id.
     */
    public void setExceptionId(Integer exceptionId) {
        this.exceptionId = exceptionId;
    }

    /**
     * Exception text, which should be used together with exception object when available.
     */
    public String getText() {
        return text;
    }

    /**
     * Exception text, which should be used together with exception object when available.
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Line number of the exception location (0-based).
     */
    public Integer getLineNumber() {
        return lineNumber;
    }

    /**
     * Line number of the exception location (0-based).
     */
    public void setLineNumber(Integer lineNumber) {
        this.lineNumber = lineNumber;
    }

    /**
     * Column number of the exception location (0-based).
     */
    public Integer getColumnNumber() {
        return columnNumber;
    }

    /**
     * Column number of the exception location (0-based).
     */
    public void setColumnNumber(Integer columnNumber) {
        this.columnNumber = columnNumber;
    }

    /**
     * Script ID of the exception location.
     */
    public String getScriptId() {
        return scriptId;
    }

    /**
     * Script ID of the exception location.
     */
    public void setScriptId(String scriptId) {
        this.scriptId = scriptId;
    }

    /**
     * URL of the exception location, to be used when the script was not reported.
     */
    public String getUrl() {
        return url;
    }

    /**
     * URL of the exception location, to be used when the script was not reported.
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * JavaScript stack trace if available.
     */
    public StackTrace getStackTrace() {
        return stackTrace;
    }

    /**
     * JavaScript stack trace if available.
     */
    public void setStackTrace(StackTrace stackTrace) {
        this.stackTrace = stackTrace;
    }

    /**
     * Exception object if available.
     */
    public RemoteObject getException() {
        return exception;
    }

    /**
     * Exception object if available.
     */
    public void setException(RemoteObject exception) {
        this.exception = exception;
    }

    /**
     * Identifier of the context where exception happened.
     */
    public Integer getExecutionContextId() {
        return executionContextId;
    }

    /**
     * Identifier of the context where exception happened.
     */
    public void setExecutionContextId(Integer executionContextId) {
        this.executionContextId = executionContextId;
    }
}
