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

import com.vimeo.stag.UseStag;

/**
 * Table of details of an element in the DOM tree with a LayoutObject
 */
@UseStag
public class LayoutTreeSnapshot {
    private List<Integer> nodeIndex;

    private List<Double> bounds;

    private List<Integer> text;

    private RareBooleanData stackingContexts;

    private List<Integer> paintOrders;

    private List<Double> offsetRects;

    private List<Double> scrollRects;

    private List<Double> clientRects;

    /**
     * Index of the corresponding node in the `NodeTreeSnapshot` array returned by `captureSnapshot`.
     */
    public List<Integer> getNodeIndex() {
        return nodeIndex;
    }

    /**
     * Index of the corresponding node in the `NodeTreeSnapshot` array returned by `captureSnapshot`.
     */
    public void setNodeIndex(List<Integer> nodeIndex) {
        this.nodeIndex = nodeIndex;
    }

    /**
     * The absolute position bounding box.
     */
    public List<Double> getBounds() {
        return bounds;
    }

    /**
     * The absolute position bounding box.
     */
    public void setBounds(List<Double> bounds) {
        this.bounds = bounds;
    }

    /**
     * Contents of the LayoutText, if any.
     */
    public List<Integer> getText() {
        return text;
    }

    /**
     * Contents of the LayoutText, if any.
     */
    public void setText(List<Integer> text) {
        this.text = text;
    }

    /**
     * Stacking context information.
     */
    public RareBooleanData getStackingContexts() {
        return stackingContexts;
    }

    /**
     * Stacking context information.
     */
    public void setStackingContexts(RareBooleanData stackingContexts) {
        this.stackingContexts = stackingContexts;
    }

    /**
     * Global paint order index, which is determined by the stacking order of the nodes. Nodes
     * that are painted together will have the same index. Only provided if includePaintOrder in
     * captureSnapshot was true.
     */
    public List<Integer> getPaintOrders() {
        return paintOrders;
    }

    /**
     * Global paint order index, which is determined by the stacking order of the nodes. Nodes
     * that are painted together will have the same index. Only provided if includePaintOrder in
     * captureSnapshot was true.
     */
    public void setPaintOrders(List<Integer> paintOrders) {
        this.paintOrders = paintOrders;
    }

    /**
     * The offset rect of nodes. Only available when includeDOMRects is set to true
     */
    public List<Double> getOffsetRects() {
        return offsetRects;
    }

    /**
     * The offset rect of nodes. Only available when includeDOMRects is set to true
     */
    public void setOffsetRects(List<Double> offsetRects) {
        this.offsetRects = offsetRects;
    }

    /**
     * The scroll rect of nodes. Only available when includeDOMRects is set to true
     */
    public List<Double> getScrollRects() {
        return scrollRects;
    }

    /**
     * The scroll rect of nodes. Only available when includeDOMRects is set to true
     */
    public void setScrollRects(List<Double> scrollRects) {
        this.scrollRects = scrollRects;
    }

    /**
     * The client rect of nodes. Only available when includeDOMRects is set to true
     */
    public List<Double> getClientRects() {
        return clientRects;
    }

    /**
     * The client rect of nodes. Only available when includeDOMRects is set to true
     */
    public void setClientRects(List<Double> clientRects) {
        this.clientRects = clientRects;
    }
}
