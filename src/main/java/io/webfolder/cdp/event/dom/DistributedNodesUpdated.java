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
package io.webfolder.cdp.event.dom;

import io.webfolder.cdp.annotation.Domain;
import io.webfolder.cdp.annotation.EventName;
import io.webfolder.cdp.annotation.Experimental;
import io.webfolder.cdp.type.dom.BackendNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Called when distrubution is changed
 */
@Experimental
@Domain("DOM")
@EventName("distributedNodesUpdated")
public class DistributedNodesUpdated {
    private Integer insertionPointId;

    private List<BackendNode> distributedNodes = new ArrayList<>();

    /**
     * Insertion point where distrubuted nodes were updated.
     */
    public Integer getInsertionPointId() {
        return insertionPointId;
    }

    /**
     * Insertion point where distrubuted nodes were updated.
     */
    public void setInsertionPointId(Integer insertionPointId) {
        this.insertionPointId = insertionPointId;
    }

    /**
     * Distributed nodes for given insertion point.
     */
    public List<BackendNode> getDistributedNodes() {
        return distributedNodes;
    }

    /**
     * Distributed nodes for given insertion point.
     */
    public void setDistributedNodes(List<BackendNode> distributedNodes) {
        this.distributedNodes = distributedNodes;
    }
}
