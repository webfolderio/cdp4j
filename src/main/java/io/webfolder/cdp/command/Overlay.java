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
import io.webfolder.cdp.type.dom.RGBA;
import io.webfolder.cdp.type.overlay.HighlightConfig;
import io.webfolder.cdp.type.overlay.InspectMode;
import java.util.List;

/**
 * This domain provides various functionality related to drawing atop the inspected page
 */
@Experimental
@Domain("Overlay")
public interface Overlay {
    /**
     * Disables domain notifications.
     */
    void disable();

    /**
     * Enables domain notifications.
     */
    void enable();

    /**
     * Hides any highlight.
     */
    void hideHighlight();

    /**
     * Highlights owner element of the frame with given id.
     * 
     * @param frameId Identifier of the frame to highlight.
     * @param contentColor The content box highlight fill color (default: transparent).
     * @param contentOutlineColor The content box highlight outline color (default: transparent).
     */
    void highlightFrame(String frameId, @Optional RGBA contentColor,
            @Optional RGBA contentOutlineColor);

    /**
     * Highlights DOM node with given id or with the given JavaScript object wrapper. Either nodeId or
     * objectId must be specified.
     * 
     * @param highlightConfig A descriptor for the highlight appearance.
     * @param nodeId Identifier of the node to highlight.
     * @param backendNodeId Identifier of the backend node to highlight.
     * @param objectId JavaScript object id of the node to be highlighted.
     * @param selector Selectors to highlight relevant nodes.
     */
    void highlightNode(HighlightConfig highlightConfig, @Optional Integer nodeId,
            @Optional Integer backendNodeId, @Optional String objectId, @Optional String selector);

    /**
     * Highlights given quad. Coordinates are absolute with respect to the main frame viewport.
     * 
     * @param quad Quad to highlight
     * @param color The highlight fill color (default: transparent).
     * @param outlineColor The highlight outline color (default: transparent).
     */
    void highlightQuad(List<Double> quad, @Optional RGBA color, @Optional RGBA outlineColor);

    /**
     * Highlights given rectangle. Coordinates are absolute with respect to the main frame viewport.
     * 
     * @param x X coordinate
     * @param y Y coordinate
     * @param width Rectangle width
     * @param height Rectangle height
     * @param color The highlight fill color (default: transparent).
     * @param outlineColor The highlight outline color (default: transparent).
     */
    void highlightRect(Integer x, Integer y, Integer width, Integer height, @Optional RGBA color,
            @Optional RGBA outlineColor);

    /**
     * Enters the 'inspect' mode. In this mode, elements that user is hovering over are highlighted.
     * Backend then generates 'inspectNodeRequested' event upon element selection.
     * 
     * @param mode Set an inspection mode.
     * @param highlightConfig A descriptor for the highlight appearance of hovered-over nodes. May be omitted if `enabled
     * == false`.
     */
    void setInspectMode(InspectMode mode, @Optional HighlightConfig highlightConfig);

    /**
     * Highlights owner element of all frames detected to be ads.
     * 
     * @param show True for showing ad highlights
     */
    void setShowAdHighlights(Boolean show);

    void setPausedInDebuggerMessage(@Optional String message);

    /**
     * Requests that backend shows debug borders on layers
     * 
     * @param show True for showing debug borders
     */
    void setShowDebugBorders(Boolean show);

    /**
     * Requests that backend shows the FPS counter
     * 
     * @param show True for showing the FPS counter
     */
    void setShowFPSCounter(Boolean show);

    /**
     * Requests that backend shows paint rectangles
     * 
     * @param result True for showing paint rectangles
     */
    void setShowPaintRects(Boolean result);

    /**
     * Requests that backend shows layout shift regions
     * 
     * @param result True for showing layout shift regions
     */
    void setShowLayoutShiftRegions(Boolean result);

    /**
     * Requests that backend shows scroll bottleneck rects
     * 
     * @param show True for showing scroll bottleneck rects
     */
    void setShowScrollBottleneckRects(Boolean show);

    /**
     * Requests that backend shows hit-test borders on layers
     * 
     * @param show True for showing hit-test borders
     */
    void setShowHitTestBorders(Boolean show);

    /**
     * Paints viewport size upon main frame resize.
     * 
     * @param show Whether to paint size or not.
     */
    void setShowViewportSizeOnResize(Boolean show);

    /**
     * Highlights owner element of the frame with given id.
     * 
     * @param frameId Identifier of the frame to highlight.
     */
    void highlightFrame(String frameId);

    /**
     * Highlights DOM node with given id or with the given JavaScript object wrapper. Either nodeId or
     * objectId must be specified.
     * 
     * @param highlightConfig A descriptor for the highlight appearance.
     */
    void highlightNode(HighlightConfig highlightConfig);

    /**
     * Highlights given quad. Coordinates are absolute with respect to the main frame viewport.
     * 
     * @param quad Quad to highlight
     */
    void highlightQuad(List<Double> quad);

    /**
     * Highlights given rectangle. Coordinates are absolute with respect to the main frame viewport.
     * 
     * @param x X coordinate
     * @param y Y coordinate
     * @param width Rectangle width
     * @param height Rectangle height
     */
    void highlightRect(Integer x, Integer y, Integer width, Integer height);

    /**
     * Enters the 'inspect' mode. In this mode, elements that user is hovering over are highlighted.
     * Backend then generates 'inspectNodeRequested' event upon element selection.
     * 
     * @param mode Set an inspection mode.
     */
    void setInspectMode(InspectMode mode);

    void setPausedInDebuggerMessage();
}
