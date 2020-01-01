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
package io.webfolder.cdp.type.profiler;

import com.vimeo.stag.UseStag;

/**
 * Coverage data for a source range
 */
@UseStag
public class CoverageRange {
    private Integer startOffset;

    private Integer endOffset;

    private Integer count;

    /**
     * JavaScript script source offset for the range start.
     */
    public Integer getStartOffset() {
        return startOffset;
    }

    /**
     * JavaScript script source offset for the range start.
     */
    public void setStartOffset(Integer startOffset) {
        this.startOffset = startOffset;
    }

    /**
     * JavaScript script source offset for the range end.
     */
    public Integer getEndOffset() {
        return endOffset;
    }

    /**
     * JavaScript script source offset for the range end.
     */
    public void setEndOffset(Integer endOffset) {
        this.endOffset = endOffset;
    }

    /**
     * Collected execution count of the source range.
     */
    public Integer getCount() {
        return count;
    }

    /**
     * Collected execution count of the source range.
     */
    public void setCount(Integer count) {
        this.count = count;
    }
}
