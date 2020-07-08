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
     * Disables the accessibility domain.
     */
    void disable();

    /**
     * Enables the accessibility domain which causes AXNodeIds to remain consistent between method calls.
     * This turns on accessibility for the page, which can impact performance until accessibility is disabled.
     */
    void enable();

    /**
     * Fetches the accessibility node and partial accessibility tree for this DOM node, if it exists.
     *
     * @param nodeId Identifier of the node to get the partial accessibility tree for.
     * @param backendNodeId Identifier of the backend node to get the partial accessibility tree for.
     * @param objectId JavaScript object id of the node wrapper to get the partial accessibility tree for.
     * @param fetchRelatives Whether to fetch this nodes ancestors, siblings and children. Defaults to true.
     *
     * @return The Accessibility.AXNode for this DOM node, if it exists, plus its ancestors, siblings and
     * children, if requested.
     */
    @Experimental
    @Returns("nodes")
    List<AXNode> getPartialAXTree(@Optional Integer nodeId, @Optional Integer backendNodeId,
            @Optional String objectId, @Optional Boolean fetchRelatives);

    /**
     * Fetches the entire accessibility tree
     */
    @Experimental
    @Returns("nodes")
    List<AXNode> getFullAXTree();

    /**
     * Fetches the accessibility node and partial accessibility tree for this DOM node, if it exists.
     *
     * @return The Accessibility.AXNode for this DOM node, if it exists, plus its ancestors, siblings and
     * children, if requested.
     */
    @Experimental
    @Returns("nodes")
    List<AXNode> getPartialAXTree();
}
