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
package io.webfolder.cdp.type.heapprofiler;

import io.webfolder.cdp.type.runtime.CallFrame;
import java.util.ArrayList;
import java.util.List;

/**
 * Sampling Heap Profile node
 * Holds callsite information, allocation statistics and child nodes
 */
public class SamplingHeapProfileNode {
    private CallFrame callFrame;

    private Double selfSize;

    private List<SamplingHeapProfileNode> children = new ArrayList<>();

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
