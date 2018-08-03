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

import java.util.ArrayList;
import java.util.List;

import io.webfolder.cdp.annotation.Domain;
import io.webfolder.cdp.annotation.EventName;
import io.webfolder.cdp.type.dom.Node;

/**
 * Fired when backend wants to provide client with the missing DOM structure
 * This happens upon
 * most of the calls requesting node ids
 */
@Domain("DOM")
@EventName("setChildNodes")
public class SetChildNodes {
    private Integer parentId;

    private List<Node> nodes = new ArrayList<>();

    /**
     * Parent node id to populate with children.
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * Parent node id to populate with children.
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /**
     * Child nodes array.
     */
    public List<Node> getNodes() {
        return nodes;
    }

    /**
     * Child nodes array.
     */
    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }
}
