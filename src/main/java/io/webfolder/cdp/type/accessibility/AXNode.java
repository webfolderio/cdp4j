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
package io.webfolder.cdp.type.accessibility;

import java.util.List;

/**
 * A node in the accessibility tree
 */
public class AXNode {
    private String nodeId;

    private Boolean ignored;

    private List<AXProperty> ignoredReasons;

    private AXValue role;

    private AXValue name;

    private AXValue description;

    private AXValue value;

    private List<AXProperty> properties;

    private List<String> childIds;

    private Integer backendDOMNodeId;

    /**
     * Unique identifier for this node.
     */
    public String getNodeId() {
        return nodeId;
    }

    /**
     * Unique identifier for this node.
     */
    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    /**
     * Whether this node is ignored for accessibility
     */
    public Boolean isIgnored() {
        return ignored;
    }

    /**
     * Whether this node is ignored for accessibility
     */
    public void setIgnored(Boolean ignored) {
        this.ignored = ignored;
    }

    /**
     * Collection of reasons why this node is hidden.
     */
    public List<AXProperty> getIgnoredReasons() {
        return ignoredReasons;
    }

    /**
     * Collection of reasons why this node is hidden.
     */
    public void setIgnoredReasons(List<AXProperty> ignoredReasons) {
        this.ignoredReasons = ignoredReasons;
    }

    /**
     * This Node's role, whether explicit or implicit.
     */
    public AXValue getRole() {
        return role;
    }

    /**
     * This Node's role, whether explicit or implicit.
     */
    public void setRole(AXValue role) {
        this.role = role;
    }

    /**
     * The accessible name for this Node.
     */
    public AXValue getName() {
        return name;
    }

    /**
     * The accessible name for this Node.
     */
    public void setName(AXValue name) {
        this.name = name;
    }

    /**
     * The accessible description for this Node.
     */
    public AXValue getDescription() {
        return description;
    }

    /**
     * The accessible description for this Node.
     */
    public void setDescription(AXValue description) {
        this.description = description;
    }

    /**
     * The value for this Node.
     */
    public AXValue getValue() {
        return value;
    }

    /**
     * The value for this Node.
     */
    public void setValue(AXValue value) {
        this.value = value;
    }

    /**
     * All other properties
     */
    public List<AXProperty> getProperties() {
        return properties;
    }

    /**
     * All other properties
     */
    public void setProperties(List<AXProperty> properties) {
        this.properties = properties;
    }

    /**
     * IDs for each of this node's child nodes.
     */
    public List<String> getChildIds() {
        return childIds;
    }

    /**
     * IDs for each of this node's child nodes.
     */
    public void setChildIds(List<String> childIds) {
        this.childIds = childIds;
    }

    /**
     * The backend ID for the associated DOM node, if any.
     */
    public Integer getBackendDOMNodeId() {
        return backendDOMNodeId;
    }

    /**
     * The backend ID for the associated DOM node, if any.
     */
    public void setBackendDOMNodeId(Integer backendDOMNodeId) {
        this.backendDOMNodeId = backendDOMNodeId;
    }
}
