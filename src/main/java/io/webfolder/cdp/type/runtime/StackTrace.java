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

import java.util.List;

/**
 * Call frames for assertions or error messages
 */
public class StackTrace {
    private String description;

    private List<CallFrame> callFrames;

    private StackTrace parent;

    private StackTraceId parentId;

    /**
     * String label of this stack trace. For async traces this may be a name of the function that
     * initiated the async call.
     */
    public String getDescription() {
        return description;
    }

    /**
     * String label of this stack trace. For async traces this may be a name of the function that
     * initiated the async call.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * JavaScript function name.
     */
    public List<CallFrame> getCallFrames() {
        return callFrames;
    }

    /**
     * JavaScript function name.
     */
    public void setCallFrames(List<CallFrame> callFrames) {
        this.callFrames = callFrames;
    }

    /**
     * Asynchronous JavaScript stack trace that preceded this stack, if available.
     */
    public StackTrace getParent() {
        return parent;
    }

    /**
     * Asynchronous JavaScript stack trace that preceded this stack, if available.
     */
    public void setParent(StackTrace parent) {
        this.parent = parent;
    }

    /**
     * Asynchronous JavaScript stack trace that preceded this stack, if available.
     */
    public StackTraceId getParentId() {
        return parentId;
    }

    /**
     * Asynchronous JavaScript stack trace that preceded this stack, if available.
     */
    public void setParentId(StackTraceId parentId) {
        this.parentId = parentId;
    }
}
