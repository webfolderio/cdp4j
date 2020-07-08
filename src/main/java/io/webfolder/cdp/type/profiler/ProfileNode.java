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

import io.webfolder.cdp.type.runtime.CallFrame;

/**
 * Profile node
 * Holds callsite information, execution statistics and child nodes
 */
public class ProfileNode {
    private Integer id;

    private CallFrame callFrame;

    private Integer hitCount;

    private List<Integer> children;

    private String deoptReason;

    private List<PositionTickInfo> positionTicks;

    /**
     * Unique id of the node.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Unique id of the node.
     */
    public void setId(Integer id) {
        this.id = id;
    }

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
     * Number of samples where this node was on top of the call stack.
     */
    public Integer getHitCount() {
        return hitCount;
    }

    /**
     * Number of samples where this node was on top of the call stack.
     */
    public void setHitCount(Integer hitCount) {
        this.hitCount = hitCount;
    }

    /**
     * Child node ids.
     */
    public List<Integer> getChildren() {
        return children;
    }

    /**
     * Child node ids.
     */
    public void setChildren(List<Integer> children) {
        this.children = children;
    }

    /**
     * The reason of being not optimized. The function may be deoptimized or marked as don't
     * optimize.
     */
    public String getDeoptReason() {
        return deoptReason;
    }

    /**
     * The reason of being not optimized. The function may be deoptimized or marked as don't
     * optimize.
     */
    public void setDeoptReason(String deoptReason) {
        this.deoptReason = deoptReason;
    }

    /**
     * An array of source position ticks.
     */
    public List<PositionTickInfo> getPositionTicks() {
        return positionTicks;
    }

    /**
     * An array of source position ticks.
     */
    public void setPositionTicks(List<PositionTickInfo> positionTicks) {
        this.positionTicks = positionTicks;
    }
}
