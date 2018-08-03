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
package io.webfolder.cdp.type.domsnapshot;

import io.webfolder.cdp.type.dom.Rect;
import java.util.ArrayList;
import java.util.List;

/**
 * Details of an element in the DOM tree with a LayoutObject
 */
public class LayoutTreeNode {
    private Integer domNodeIndex;

    private Rect boundingBox;

    private String layoutText;

    private List<InlineTextBox> inlineTextNodes = new ArrayList<>();

    private Integer styleIndex;

    private Integer paintOrder;

    /**
     * The index of the related DOM node in the <code>domNodes</code> array returned by <code>getSnapshot</code>.
     */
    public Integer getDomNodeIndex() {
        return domNodeIndex;
    }

    /**
     * The index of the related DOM node in the <code>domNodes</code> array returned by <code>getSnapshot</code>.
     */
    public void setDomNodeIndex(Integer domNodeIndex) {
        this.domNodeIndex = domNodeIndex;
    }

    /**
     * The absolute position bounding box.
     */
    public Rect getBoundingBox() {
        return boundingBox;
    }

    /**
     * The absolute position bounding box.
     */
    public void setBoundingBox(Rect boundingBox) {
        this.boundingBox = boundingBox;
    }

    /**
     * Contents of the LayoutText, if any.
     */
    public String getLayoutText() {
        return layoutText;
    }

    /**
     * Contents of the LayoutText, if any.
     */
    public void setLayoutText(String layoutText) {
        this.layoutText = layoutText;
    }

    /**
     * The post-layout inline text nodes, if any.
     */
    public List<InlineTextBox> getInlineTextNodes() {
        return inlineTextNodes;
    }

    /**
     * The post-layout inline text nodes, if any.
     */
    public void setInlineTextNodes(List<InlineTextBox> inlineTextNodes) {
        this.inlineTextNodes = inlineTextNodes;
    }

    /**
     * Index into the <code>computedStyles</code>array returned by<code>getSnapshot</code>.
     */
    public Integer getStyleIndex() {
        return styleIndex;
    }

    /**
     * Index into the <code>computedStyles</code>array returned by<code>getSnapshot</code>.
     */
    public void setStyleIndex(Integer styleIndex) {
        this.styleIndex = styleIndex;
    }

    /**
     * Global paint order index, which is determined by the stacking order of the nodes. Nodes
     * that are painted together will have the same index. Only provided if includePaintOrder in
     * getSnapshot was true.
     */
    public Integer getPaintOrder() {
        return paintOrder;
    }

    /**
     * Global paint order index, which is determined by the stacking order of the nodes. Nodes
     * that are painted together will have the same index. Only provided if includePaintOrder in
     * getSnapshot was true.
     */
    public void setPaintOrder(Integer paintOrder) {
        this.paintOrder = paintOrder;
    }
}
