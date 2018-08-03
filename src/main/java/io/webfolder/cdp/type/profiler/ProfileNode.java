/**
 * cdp4j - Chrome DevTools Protocol for Java
 * Copyright © 2017, 2018 WebFolder OÜ (support@webfolder.io)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package io.webfolder.cdp.type.profiler;

import io.webfolder.cdp.type.runtime.CallFrame;
import java.util.ArrayList;
import java.util.List;

/**
 * Profile node
 * Holds callsite information, execution statistics and child nodes
 */
public class ProfileNode {
    private Integer id;

    private CallFrame callFrame;

    private Integer hitCount;

    private List<Integer> children = new ArrayList<>();

    private String deoptReason;

    private List<PositionTickInfo> positionTicks = new ArrayList<>();

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
