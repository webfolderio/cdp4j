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

import java.util.List;

import com.vimeo.stag.UseStag;

/**
 * Coverage data for a JavaScript function
 */
@UseStag
public class FunctionCoverage {
    private String functionName;

    private List<CoverageRange> ranges;

    private Boolean isBlockCoverage;

    /**
     * JavaScript function name.
     */
    public String getFunctionName() {
        return functionName;
    }

    /**
     * JavaScript function name.
     */
    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    /**
     * Source ranges inside the function with coverage data.
     */
    public List<CoverageRange> getRanges() {
        return ranges;
    }

    /**
     * Source ranges inside the function with coverage data.
     */
    public void setRanges(List<CoverageRange> ranges) {
        this.ranges = ranges;
    }

    /**
     * Whether coverage data for this function has block granularity.
     */
    public Boolean isIsBlockCoverage() {
        return isBlockCoverage;
    }

    /**
     * Whether coverage data for this function has block granularity.
     */
    public void setIsBlockCoverage(Boolean isBlockCoverage) {
        this.isBlockCoverage = isBlockCoverage;
    }

    public Boolean getIsBlockCoverage() {
        return isBlockCoverage;
    }
}
