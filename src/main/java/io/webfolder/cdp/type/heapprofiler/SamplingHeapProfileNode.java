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

import java.util.List;

import com.vimeo.stag.UseStag;

import io.webfolder.cdp.type.runtime.CallFrame;

/**
 * Sampling Heap Profile node
 * Holds callsite information, allocation statistics and child nodes
 */
@UseStag
public class SamplingHeapProfileNode {
    private CallFrame callFrame;

    private Double selfSize;

    private Integer id;

    private List<SamplingHeapProfileNode> children;

    /**
     * Function location.
     */
    public CallFrame getCallFrame() {
        return callFrame;
    }

    /**
     * Function location.
     */
    public void setCallFrame(CallFrame callFrame) {
        this.callFrame = callFrame;
    }

    /**
     * Allocations size in bytes for the node excluding children.
     */
    public Double getSelfSize() {
        return selfSize;
    }

    /**
     * Allocations size in bytes for the node excluding children.
     */
    public void setSelfSize(Double selfSize) {
        this.selfSize = selfSize;
    }

    /**
     * Node id. Ids are unique across all profiles collected between startSampling and stopSampling.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Node id. Ids are unique across all profiles collected between startSampling and stopSampling.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Child nodes.
     */
    public List<SamplingHeapProfileNode> getChildren() {
        return children;
    }

    /**
     * Child nodes.
     */
    public void setChildren(List<SamplingHeapProfileNode> children) {
        this.children = children;
    }
}
