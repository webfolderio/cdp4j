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
package io.webfolder.cdp.type.css;

import com.vimeo.stag.UseStag;

/**
 * Text range within a resource
 * All numbers are zero-based
 */
@UseStag
public class SourceRange {
    private Integer startLine;

    private Integer startColumn;

    private Integer endLine;

    private Integer endColumn;

    /**
     * Start line of range.
     */
    public Integer getStartLine() {
        return startLine;
    }

    /**
     * Start line of range.
     */
    public void setStartLine(Integer startLine) {
        this.startLine = startLine;
    }

    /**
     * Start column of range (inclusive).
     */
    public Integer getStartColumn() {
        return startColumn;
    }

    /**
     * Start column of range (inclusive).
     */
    public void setStartColumn(Integer startColumn) {
        this.startColumn = startColumn;
    }

    /**
     * End line of range
     */
    public Integer getEndLine() {
        return endLine;
    }

    /**
     * End line of range
     */
    public void setEndLine(Integer endLine) {
        this.endLine = endLine;
    }

    /**
     * End column of range (exclusive).
     */
    public Integer getEndColumn() {
        return endColumn;
    }

    /**
     * End column of range (exclusive).
     */
    public void setEndColumn(Integer endColumn) {
        this.endColumn = endColumn;
    }
}
