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

import java.util.ArrayList;
import java.util.List;

/**
 * Fired when <code>Element</code>'s inline style is modified via a CSS property modification
 */
@Experimental
@Domain("DOM")
@EventName("inlineStyleInvalidated")
public class InlineStyleInvalidated {
    private List<Integer> nodeIds = new ArrayList<>();

    /**
     * Ids of the nodes for which the inline styles have been invalidated.
     */
    public List<Integer> getNodeIds() {
        return nodeIds;
    }

    /**
     * Ids of the nodes for which the inline styles have been invalidated.
     */
    public void setNodeIds(List<Integer> nodeIds) {
        this.nodeIds = nodeIds;
    }
}
