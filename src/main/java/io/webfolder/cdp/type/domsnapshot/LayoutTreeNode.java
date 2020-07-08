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
package io.webfolder.cdp.type.domsnapshot;

import java.util.List;

import io.webfolder.cdp.type.dom.Rect;

/**
 * Details of an element in the DOM tree with a LayoutObject
 */
public class LayoutTreeNode {
    private Integer domNodeIndex;

    private Rect boundingBox;

    private String layoutText;

    private List<InlineTextBox> inlineTextNodes;

    private Integer styleIndex;

    private Integer paintOrder;

    private Boolean isStackingContext;

    /**
     * The index of the related DOM node in the domNodes array returned by getSnapshot.
     */
    public Integer getDomNodeIndex() {
        return domNodeIndex;
    }

    /**
     * The index of the related DOM node in the domNodes array returned by getSnapshot.
     */
    public void setDomNodeIndex(Integer domNodeIndex) {
        this.domNodeIndex = domNodeIndex;
    }

    /**
     * The bounding box in document coordinates. Note that scroll offset of the document is ignored.
     */
    public Rect getBoundingBox() {
        return boundingBox;
    }

    /**
     * The bounding box in document coordinates. Note that scroll offset of the document is ignored.
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
     * Index into the computedStyles array returned by getSnapshot.
     */
    public Integer getStyleIndex() {
        return styleIndex;
    }

    /**
     * Index into the computedStyles array returned by getSnapshot.
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

    /**
     * Set to true to indicate the element begins a new stacking context.
     */
    public Boolean isIsStackingContext() {
        return isStackingContext;
    }

    /**
     * Set to true to indicate the element begins a new stacking context.
     */
    public void setIsStackingContext(Boolean isStackingContext) {
        this.isStackingContext = isStackingContext;
    }
}
