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
import io.webfolder.cdp.type.dom.BoxModel;
import io.webfolder.cdp.type.dom.GetFrameOwnerResult;
import io.webfolder.cdp.type.dom.GetNodeForLocationResult;
import io.webfolder.cdp.type.dom.Node;
import io.webfolder.cdp.type.dom.PerformSearchResult;
import io.webfolder.cdp.type.runtime.RemoteObject;
import io.webfolder.cdp.type.runtime.StackTrace;
import java.util.List;

/**
 * This domain exposes DOM read/write operations
 * Each DOM Node is represented with its mirror object
 * that has an `id`
 * This `id` can be used to get additional information on the Node, resolve it into
 * the JavaScript object wrapper, etc
 * It is important that client receives DOM events only for the
 * nodes that are known to the client
 * Backend keeps track of the nodes that were sent to the client
 * and never sends the same node twice
 * It is client's responsibility to collect information about
 * the nodes that were sent to the client
 * <p>Note that `iframe` owner elements will return
 * corresponding document elements as their child nodes
 * </p>
 */
@Domain("DOM")
public interface DOM {
    /**
     * Collects class names for the node with given id and all of it's child nodes.
     * 
     * @param nodeId Id of the node to collect class names.
     * 
     * @return Class name list.
     */
    @Experimental
    @Returns("classNames")
    List<String> collectClassNamesFromSubtree(Integer nodeId);

    /**
     * Creates a deep copy of the specified node and places it into the target container before the
     * given anchor.
     * 
     * @param nodeId Id of the node to copy.
     * @param targetNodeId Id of the element to drop the copy into.
     * @param insertBeforeNodeId Drop the copy before this node (if absent, the copy becomes the last child of
     * `targetNodeId`).
     * 
     * @return Id of the node clone.
     */
    @Experimental
    @Returns("nodeId")
    Integer copyTo(Integer nodeId, Integer targetNodeId, @Optional Integer insertBeforeNodeId);

    /**
     * Describes node given its id, does not require domain to be enabled. Does not start tracking any
     * objects, can be used for automation.
     * 
     * @param nodeId Identifier of the node.
     * @param backendNodeId Identifier of the backend node.
     * @param objectId JavaScript object id of the node wrapper.
     * @param depth The maximum depth at which children should be retrieved, defaults to 1. Use -1 for the
     * entire subtree or provide an integer larger than 0.
     * @param pierce Whether or not iframes and shadow roots should be traversed when returning the subtree
     * (default is false).
     * 
     * @return Node description.
     */
    @Returns("node")
    Node describeNode(@Optional Integer nodeId, @Optional Integer backendNodeId,
            @Optional String objectId, @Optional Integer depth, @Optional Boolean pierce);

    /**
     * Disables DOM agent for the given page.
     */
    void disable();

    /**
     * Discards search results from the session with the given id. `getSearchResults` should no longer
     * be called for that search.
     * 
     * @param searchId Unique search session identifier.
     */
    @Experimental
    void discardSearchResults(String searchId);

    /**
     * Enables DOM agent for the given page.
     */
    void enable();

    /**
     * Focuses the given element.
     * 
     * @param nodeId Identifier of the node.
     * @param backendNodeId Identifier of the backend node.
     * @param objectId JavaScript object id of the node wrapper.
     */
    void focus(@Optional Integer nodeId, @Optional Integer backendNodeId,
            @Optional String objectId);

    /**
     * Returns attributes for the specified node.
     * 
     * @param nodeId Id of the node to retrieve attibutes for.
     * 
     * @return An interleaved array of node attribute names and values.
     */
    @Returns("attributes")
    List<String> getAttributes(Integer nodeId);

    /**
     * Returns boxes for the given node.
     * 
     * @param nodeId Identifier of the node.
     * @param backendNodeId Identifier of the backend node.
     * @param objectId JavaScript object id of the node wrapper.
     * 
     * @return Box model for the node.
     */
    @Returns("model")
    BoxModel getBoxModel(@Optional Integer nodeId, @Optional Integer backendNodeId,
            @Optional String objectId);

    /**
     * Returns quads that describe node position on the page. This method
     * might return multiple quads for inline nodes.
     * 
     * @param nodeId Identifier of the node.
     * @param backendNodeId Identifier of the backend node.
     * @param objectId JavaScript object id of the node wrapper.
     * 
     * @return Quads that describe node layout relative to viewport.
     */
    @Experimental
    @Returns("quads")
    List<List<Double>> getContentQuads(@Optional Integer nodeId, @Optional Integer backendNodeId,
            @Optional String objectId);

    /**
     * Returns the root DOM node (and optionally the subtree) to the caller.
     * 
     * @param depth The maximum depth at which children should be retrieved, defaults to 1. Use -1 for the
     * entire subtree or provide an integer larger than 0.
     * @param pierce Whether or not iframes and shadow roots should be traversed when returning the subtree
     * (default is false).
     * 
     * @return Resulting node.
     */
    @Returns("root")
    Node getDocument(@Optional Integer depth, @Optional Boolean pierce);

    /**
     * Returns the root DOM node (and optionally the subtree) to the caller.
     * 
     * @param depth The maximum depth at which children should be retrieved, defaults to 1. Use -1 for the
     * entire subtree or provide an integer larger than 0.
     * @param pierce Whether or not iframes and shadow roots should be traversed when returning the subtree
     * (default is false).
     * 
     * @return Resulting node.
     */
    @Returns("nodes")
    List<Node> getFlattenedDocument(@Optional Integer depth, @Optional Boolean pierce);

    /**
     * Returns node id at given location. Depending on whether DOM domain is enabled, nodeId is
     * either returned or not.
     * 
     * @param x X coordinate.
     * @param y Y coordinate.
     * @param includeUserAgentShadowDOM False to skip to the nearest non-UA shadow root ancestor (default: false).
     * @param ignorePointerEventsNone Whether to ignore pointer-events: none on elements and hit test them.
     * 
     * @return GetNodeForLocationResult
     */
    GetNodeForLocationResult getNodeForLocation(Integer x, Integer y,
            @Optional Boolean includeUserAgentShadowDOM, @Optional Boolean ignorePointerEventsNone);

    /**
     * Returns node's HTML markup.
     * 
     * @param nodeId Identifier of the node.
     * @param backendNodeId Identifier of the backend node.
     * @param objectId JavaScript object id of the node wrapper.
     * 
     * @return Outer HTML markup.
     */
    @Returns("outerHTML")
    String getOuterHTML(@Optional Integer nodeId, @Optional Integer backendNodeId,
            @Optional String objectId);

    /**
     * Returns the id of the nearest ancestor that is a relayout boundary.
     * 
     * @param nodeId Id of the node.
     * 
     * @return Relayout boundary node id for the given node.
     */
    @Experimental
    @Returns("nodeId")
    Integer getRelayoutBoundary(Integer nodeId);

    /**
     * Returns search results from given `fromIndex` to given `toIndex` from the search with the given
     * identifier.
     * 
     * @param searchId Unique search session identifier.
     * @param fromIndex Start index of the search result to be returned.
     * @param toIndex End index of the search result to be returned.
     * 
     * @return Ids of the search result nodes.
     */
    @Experimental
    @Returns("nodeIds")
    List<Integer> getSearchResults(String searchId, Integer fromIndex, Integer toIndex);

    /**
     * Hides any highlight.
     */
    void hideHighlight();

    /**
     * Highlights DOM node.
     */
    void highlightNode();

    /**
     * Highlights given rectangle.
     */
    void highlightRect();

    /**
     * Marks last undoable state.
     */
    @Experimental
    void markUndoableState();

    /**
     * Moves node into the new container, places it before the given anchor.
     * 
     * @param nodeId Id of the node to move.
     * @param targetNodeId Id of the element to drop the moved node into.
     * @param insertBeforeNodeId Drop node before this one (if absent, the moved node becomes the last child of
     * `targetNodeId`).
     * 
     * @return New id of the moved node.
     */
    @Returns("nodeId")
    Integer moveTo(Integer nodeId, Integer targetNodeId, @Optional Integer insertBeforeNodeId);

    /**
     * Searches for a given string in the DOM tree. Use `getSearchResults` to access search results or
     * `cancelSearch` to end this search session.
     * 
     * @param query Plain text or query selector or XPath search query.
     * @param includeUserAgentShadowDOM True to search in user agent shadow DOM.
     * 
     * @return PerformSearchResult
     */
    @Experimental
    PerformSearchResult performSearch(String query, @Optional Boolean includeUserAgentShadowDOM);

    /**
     * Requests that the node is sent to the caller given its path. // FIXME, use XPath
     * 
     * @param path Path to node in the proprietary format.
     * 
     * @return Id of the node for given path.
     */
    @Experimental
    @Returns("nodeId")
    Integer pushNodeByPathToFrontend(String path);

    /**
     * Requests that a batch of nodes is sent to the caller given their backend node ids.
     * 
     * @param backendNodeIds The array of backend node ids.
     * 
     * @return The array of ids of pushed nodes that correspond to the backend ids specified in
     * backendNodeIds.
     */
    @Experimental
    @Returns("nodeIds")
    List<Integer> pushNodesByBackendIdsToFrontend(List<Integer> backendNodeIds);

    /**
     * Executes `querySelector` on a given node.
     * 
     * @param nodeId Id of the node to query upon.
     * @param selector Selector string.
     * 
     * @return Query selector result.
     */
    @Returns("nodeId")
    Integer querySelector(Integer nodeId, String selector);

    /**
     * Executes `querySelectorAll` on a given node.
     * 
     * @param nodeId Id of the node to query upon.
     * @param selector Selector string.
     * 
     * @return Query selector result.
     */
    @Returns("nodeIds")
    List<Integer> querySelectorAll(Integer nodeId, String selector);

    /**
     * Re-does the last undone action.
     */
    @Experimental
    void redo();

    /**
     * Removes attribute with given name from an element with given id.
     * 
     * @param nodeId Id of the element to remove attribute from.
     * @param name Name of the attribute to remove.
     */
    void removeAttribute(Integer nodeId, String name);

    /**
     * Removes node with given id.
     * 
     * @param nodeId Id of the node to remove.
     */
    void removeNode(Integer nodeId);

    /**
     * Requests that children of the node with given id are returned to the caller in form of
     * `setChildNodes` events where not only immediate children are retrieved, but all children down to
     * the specified depth.
     * 
     * @param nodeId Id of the node to get children for.
     * @param depth The maximum depth at which children should be retrieved, defaults to 1. Use -1 for the
     * entire subtree or provide an integer larger than 0.
     * @param pierce Whether or not iframes and shadow roots should be traversed when returning the sub-tree
     * (default is false).
     */
    void requestChildNodes(Integer nodeId, @Optional Integer depth, @Optional Boolean pierce);

    /**
     * Requests that the node is sent to the caller given the JavaScript node object reference. All
     * nodes that form the path from the node to the root are also sent to the client as a series of
     * `setChildNodes` notifications.
     * 
     * @param objectId JavaScript object id to convert into node.
     * 
     * @return Node id for given object.
     */
    @Returns("nodeId")
    Integer requestNode(String objectId);

    /**
     * Resolves the JavaScript node object for a given NodeId or BackendNodeId.
     * 
     * @param nodeId Id of the node to resolve.
     * @param backendNodeId Backend identifier of the node to resolve.
     * @param objectGroup Symbolic group name that can be used to release multiple objects.
     * @param executionContextId Execution context in which to resolve the node.
     * 
     * @return JavaScript object wrapper for given node.
     */
    @Returns("object")
    RemoteObject resolveNode(@Optional Integer nodeId, @Optional Integer backendNodeId,
            @Optional String objectGroup, @Optional Integer executionContextId);

    /**
     * Sets attribute for an element with given id.
     * 
     * @param nodeId Id of the element to set attribute for.
     * @param name Attribute name.
     * @param value Attribute value.
     */
    void setAttributeValue(Integer nodeId, String name, String value);

    /**
     * Sets attributes on element with given id. This method is useful when user edits some existing
     * attribute value and types in several attribute name/value pairs.
     * 
     * @param nodeId Id of the element to set attributes for.
     * @param text Text with a number of attributes. Will parse this text using HTML parser.
     * @param name Attribute name to replace with new attributes derived from text in case text parsed
     * successfully.
     */
    void setAttributesAsText(Integer nodeId, String text, @Optional String name);

    /**
     * Sets files for the given file input element.
     * 
     * @param files Array of file paths to set.
     * @param nodeId Identifier of the node.
     * @param backendNodeId Identifier of the backend node.
     * @param objectId JavaScript object id of the node wrapper.
     */
    void setFileInputFiles(List<String> files, @Optional Integer nodeId,
            @Optional Integer backendNodeId, @Optional String objectId);

    /**
     * Sets if stack traces should be captured for Nodes. See `Node.getNodeStackTraces`. Default is disabled.
     * 
     * @param enable Enable or disable.
     */
    @Experimental
    void setNodeStackTracesEnabled(Boolean enable);

    /**
     * Gets stack traces associated with a Node. As of now, only provides stack trace for Node creation.
     * 
     * @param nodeId Id of the node to get stack traces for.
     * 
     * @return Creation stack trace, if available.
     */
    @Experimental
    @Returns("creation")
    StackTrace getNodeStackTraces(Integer nodeId);

    /**
     * Returns file information for the given
     * File wrapper.
     * 
     * @param objectId JavaScript object id of the node wrapper.
     */
    @Experimental
    @Returns("path")
    String getFileInfo(String objectId);

    /**
     * Enables console to refer to the node with given id via  (see Command Line API for more details
     *  functions).
     * 
     * @param nodeId DOM node id to be accessible by means of x command line API.
     */
    @Experimental
    void setInspectedNode(Integer nodeId);

    /**
     * Sets node name for a node with given id.
     * 
     * @param nodeId Id of the node to set name for.
     * @param name New node's name.
     * 
     * @return New node's id.
     */
    @Returns("nodeId")
    Integer setNodeName(Integer nodeId, String name);

    /**
     * Sets node value for a node with given id.
     * 
     * @param nodeId Id of the node to set value for.
     * @param value New node's value.
     */
    void setNodeValue(Integer nodeId, String value);

    /**
     * Sets node HTML markup, returns new node id.
     * 
     * @param nodeId Id of the node to set markup for.
     * @param outerHTML Outer HTML markup to set.
     */
    void setOuterHTML(Integer nodeId, String outerHTML);

    /**
     * Undoes the last performed action.
     */
    @Experimental
    void undo();

    /**
     * Returns iframe node that owns iframe with the given domain.
     * 
     * 
     * @return GetFrameOwnerResult
     */
    @Experimental
    GetFrameOwnerResult getFrameOwner(String frameId);

    /**
     * Creates a deep copy of the specified node and places it into the target container before the
     * given anchor.
     * 
     * @param nodeId Id of the node to copy.
     * @param targetNodeId Id of the element to drop the copy into.
     * 
     * @return Id of the node clone.
     */
    @Experimental
    @Returns("nodeId")
    Integer copyTo(Integer nodeId, Integer targetNodeId);

    /**
     * Describes node given its id, does not require domain to be enabled. Does not start tracking any
     * objects, can be used for automation.
     * 
     * @return Node description.
     */
    @Returns("node")
    Node describeNode();

    /**
     * Focuses the given element.
     */
    void focus();

    /**
     * Returns boxes for the given node.
     * 
     * @return Box model for the node.
     */
    @Returns("model")
    BoxModel getBoxModel();

    /**
     * Returns quads that describe node position on the page. This method
     * might return multiple quads for inline nodes.
     * 
     * @return Quads that describe node layout relative to viewport.
     */
    @Experimental
    @Returns("quads")
    List<List<Double>> getContentQuads();

    /**
     * Returns the root DOM node (and optionally the subtree) to the caller.
     * 
     * @return Resulting node.
     */
    @Returns("root")
    Node getDocument();

    /**
     * Returns the root DOM node (and optionally the subtree) to the caller.
     * 
     * @return Resulting node.
     */
    @Returns("nodes")
    List<Node> getFlattenedDocument();

    /**
     * Returns node id at given location. Depending on whether DOM domain is enabled, nodeId is
     * either returned or not.
     * 
     * @param x X coordinate.
     * @param y Y coordinate.
     * 
     * @return GetNodeForLocationResult
     */
    GetNodeForLocationResult getNodeForLocation(Integer x, Integer y);

    /**
     * Returns node's HTML markup.
     * 
     * @return Outer HTML markup.
     */
    @Returns("outerHTML")
    String getOuterHTML();

    /**
     * Moves node into the new container, places it before the given anchor.
     * 
     * @param nodeId Id of the node to move.
     * @param targetNodeId Id of the element to drop the moved node into.
     * 
     * @return New id of the moved node.
     */
    @Returns("nodeId")
    Integer moveTo(Integer nodeId, Integer targetNodeId);

    /**
     * Searches for a given string in the DOM tree. Use `getSearchResults` to access search results or
     * `cancelSearch` to end this search session.
     * 
     * @param query Plain text or query selector or XPath search query.
     * 
     * @return PerformSearchResult
     */
    @Experimental
    PerformSearchResult performSearch(String query);

    /**
     * Requests that children of the node with given id are returned to the caller in form of
     * `setChildNodes` events where not only immediate children are retrieved, but all children down to
     * the specified depth.
     * 
     * @param nodeId Id of the node to get children for.
     */
    void requestChildNodes(Integer nodeId);

    /**
     * Resolves the JavaScript node object for a given NodeId or BackendNodeId.
     * 
     * @return JavaScript object wrapper for given node.
     */
    @Returns("object")
    RemoteObject resolveNode();

    /**
     * Sets attributes on element with given id. This method is useful when user edits some existing
     * attribute value and types in several attribute name/value pairs.
     * 
     * @param nodeId Id of the element to set attributes for.
     * @param text Text with a number of attributes. Will parse this text using HTML parser.
     */
    void setAttributesAsText(Integer nodeId, String text);

    /**
     * Sets files for the given file input element.
     * 
     * @param files Array of file paths to set.
     */
    void setFileInputFiles(List<String> files);
}
