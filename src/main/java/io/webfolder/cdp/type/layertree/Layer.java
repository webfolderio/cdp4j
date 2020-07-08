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
package io.webfolder.cdp.type.layertree;

import java.util.List;

/**
 * Information about a compositing layer
 */
public class Layer {
    private String layerId;

    private String parentLayerId;

    private Integer backendNodeId;

    private Double offsetX;

    private Double offsetY;

    private Double width;

    private Double height;

    private List<Double> transform;

    private Double anchorX;

    private Double anchorY;

    private Double anchorZ;

    private Integer paintCount;

    private Boolean drawsContent;

    private Boolean invisible;

    private List<ScrollRect> scrollRects;

    private StickyPositionConstraint stickyPositionConstraint;

    /**
     * The unique id for this layer.
     */
    public String getLayerId() {
        return layerId;
    }

    /**
     * The unique id for this layer.
     */
    public void setLayerId(String layerId) {
        this.layerId = layerId;
    }

    /**
     * The id of parent (not present for root).
     */
    public String getParentLayerId() {
        return parentLayerId;
    }

    /**
     * The id of parent (not present for root).
     */
    public void setParentLayerId(String parentLayerId) {
        this.parentLayerId = parentLayerId;
    }

    /**
     * The backend id for the node associated with this layer.
     */
    public Integer getBackendNodeId() {
        return backendNodeId;
    }

    /**
     * The backend id for the node associated with this layer.
     */
    public void setBackendNodeId(Integer backendNodeId) {
        this.backendNodeId = backendNodeId;
    }

    /**
     * Offset from parent layer, X coordinate.
     */
    public Double getOffsetX() {
        return offsetX;
    }

    /**
     * Offset from parent layer, X coordinate.
     */
    public void setOffsetX(Double offsetX) {
        this.offsetX = offsetX;
    }

    /**
     * Offset from parent layer, Y coordinate.
     */
    public Double getOffsetY() {
        return offsetY;
    }

    /**
     * Offset from parent layer, Y coordinate.
     */
    public void setOffsetY(Double offsetY) {
        this.offsetY = offsetY;
    }

    /**
     * Layer width.
     */
    public Double getWidth() {
        return width;
    }

    /**
     * Layer width.
     */
    public void setWidth(Double width) {
        this.width = width;
    }

    /**
     * Layer height.
     */
    public Double getHeight() {
        return height;
    }

    /**
     * Layer height.
     */
    public void setHeight(Double height) {
        this.height = height;
    }

    /**
     * Transformation matrix for layer, default is identity matrix
     */
    public List<Double> getTransform() {
        return transform;
    }

    /**
     * Transformation matrix for layer, default is identity matrix
     */
    public void setTransform(List<Double> transform) {
        this.transform = transform;
    }

    /**
     * Transform anchor point X, absent if no transform specified
     */
    public Double getAnchorX() {
        return anchorX;
    }

    /**
     * Transform anchor point X, absent if no transform specified
     */
    public void setAnchorX(Double anchorX) {
        this.anchorX = anchorX;
    }

    /**
     * Transform anchor point Y, absent if no transform specified
     */
    public Double getAnchorY() {
        return anchorY;
    }

    /**
     * Transform anchor point Y, absent if no transform specified
     */
    public void setAnchorY(Double anchorY) {
        this.anchorY = anchorY;
    }

    /**
     * Transform anchor point Z, absent if no transform specified
     */
    public Double getAnchorZ() {
        return anchorZ;
    }

    /**
     * Transform anchor point Z, absent if no transform specified
     */
    public void setAnchorZ(Double anchorZ) {
        this.anchorZ = anchorZ;
    }

    /**
     * Indicates how many time this layer has painted.
     */
    public Integer getPaintCount() {
        return paintCount;
    }

    /**
     * Indicates how many time this layer has painted.
     */
    public void setPaintCount(Integer paintCount) {
        this.paintCount = paintCount;
    }

    /**
     * Indicates whether this layer hosts any content, rather than being used for
     * transform/scrolling purposes only.
     */
    public Boolean isDrawsContent() {
        return drawsContent;
    }

    /**
     * Indicates whether this layer hosts any content, rather than being used for
     * transform/scrolling purposes only.
     */
    public void setDrawsContent(Boolean drawsContent) {
        this.drawsContent = drawsContent;
    }

    /**
     * Set if layer is not visible.
     */
    public Boolean isInvisible() {
        return invisible;
    }

    /**
     * Set if layer is not visible.
     */
    public void setInvisible(Boolean invisible) {
        this.invisible = invisible;
    }

    /**
     * Rectangles scrolling on main thread only.
     */
    public List<ScrollRect> getScrollRects() {
        return scrollRects;
    }

    /**
     * Rectangles scrolling on main thread only.
     */
    public void setScrollRects(List<ScrollRect> scrollRects) {
        this.scrollRects = scrollRects;
    }

    /**
     * Sticky position constraint information
     */
    public StickyPositionConstraint getStickyPositionConstraint() {
        return stickyPositionConstraint;
    }

    /**
     * Sticky position constraint information
     */
    public void setStickyPositionConstraint(StickyPositionConstraint stickyPositionConstraint) {
        this.stickyPositionConstraint = stickyPositionConstraint;
    }
}
