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
package io.webfolder.cdp.type.heapprofiler;

import com.vimeo.stag.UseStag;

/**
 * A single sample from a sampling profile
 */
@UseStag
public class SamplingHeapProfileSample {
    private Double size;

    private Integer nodeId;

    private Double ordinal;

    /**
     * Allocation size in bytes attributed to the sample.
     */
    public Double getSize() {
        return size;
    }

    /**
     * Allocation size in bytes attributed to the sample.
     */
    public void setSize(Double size) {
        this.size = size;
    }

    /**
     * Id of the corresponding profile tree node.
     */
    public Integer getNodeId() {
        return nodeId;
    }

    /**
     * Id of the corresponding profile tree node.
     */
    public void setNodeId(Integer nodeId) {
        this.nodeId = nodeId;
    }

    /**
     * Time-ordered sample ordinal number. It is unique across all profiles retrieved
     * between startSampling and stopSampling.
     */
    public Double getOrdinal() {
        return ordinal;
    }

    /**
     * Time-ordered sample ordinal number. It is unique across all profiles retrieved
     * between startSampling and stopSampling.
     */
    public void setOrdinal(Double ordinal) {
        this.ordinal = ordinal;
    }
}
