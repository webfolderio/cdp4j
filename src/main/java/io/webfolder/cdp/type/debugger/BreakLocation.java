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
package io.webfolder.cdp.type.debugger;

import io.webfolder.cdp.type.constant.BreakLocationType;

public class BreakLocation {
    private String scriptId;

    private Integer lineNumber;

    private Integer columnNumber;

    private BreakLocationType type;

    /**
     * Script identifier as reported in the Debugger.scriptParsed.
     */
    public String getScriptId() {
        return scriptId;
    }

    /**
     * Script identifier as reported in the Debugger.scriptParsed.
     */
    public void setScriptId(String scriptId) {
        this.scriptId = scriptId;
    }

    /**
     * Line number in the script (0-based).
     */
    public Integer getLineNumber() {
        return lineNumber;
    }

    /**
     * Line number in the script (0-based).
     */
    public void setLineNumber(Integer lineNumber) {
        this.lineNumber = lineNumber;
    }

    /**
     * Column number in the script (0-based).
     */
    public Integer getColumnNumber() {
        return columnNumber;
    }

    /**
     * Column number in the script (0-based).
     */
    public void setColumnNumber(Integer columnNumber) {
        this.columnNumber = columnNumber;
    }

    public BreakLocationType getType() {
        return type;
    }

    public void setType(BreakLocationType type) {
        this.type = type;
    }
}
