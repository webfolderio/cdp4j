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
package io.webfolder.cdp.command;

import io.webfolder.cdp.annotation.Domain;
import io.webfolder.cdp.annotation.Experimental;
import io.webfolder.cdp.annotation.Optional;
import io.webfolder.cdp.annotation.Returns;
import io.webfolder.cdp.type.accessibility.AXNode;
import java.util.List;

@Experimental
@Domain("Accessibility")
public interface Accessibility {
    /**
     * Fetches the accessibility node and partial accessibility tree for this DOM node, if it exists.
     * 
     * @param nodeId Identifier of the node to get the partial accessibility tree for.
     * @param backendNodeId Identifier of the backend node to get the partial accessibility tree for.
     * @param objectId JavaScript object id of the node wrapper to get the partial accessibility tree for.
     * @param fetchRelatives Whether to fetch this nodes ancestors, siblings and children. Defaults to true.
     * 
     * @return The <code>Accessibility.AXNode</code> for this DOM node, if it exists, plus its ancestors, siblings and
     * children, if requested.
     */
    @Experimental
    @Returns("nodes")
    List<AXNode> getPartialAXTree(@Optional Integer nodeId, @Optional Integer backendNodeId,
            @Optional String objectId, @Optional Boolean fetchRelatives);

    /**
     * Fetches the accessibility node and partial accessibility tree for this DOM node, if it exists.
     * 
     * @return The <code>Accessibility.AXNode</code> for this DOM node, if it exists, plus its ancestors, siblings and
     * children, if requested.
     */
    @Experimental
    @Returns("nodes")
    List<AXNode> getPartialAXTree();
}
