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
     */
    void highlightNode(HighlightConfig highlightConfig, @Optional Integer nodeId,
            @Optional Integer backendNodeId, @Optional String objectId);

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
     * @param highlightConfig A descriptor for the highlight appearance of hovered-over nodes. May be omitted if<code>enabled
     * == false.
     */
    void setInspectMode(InspectMode mode, @Optional HighlightConfig highlightConfig);

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
     * Requests that backend shows scroll bottleneck rects
     * 
     * @param show True for showing scroll bottleneck rects
     */
    void setShowScrollBottleneckRects(Boolean show);

    /**
     * Paints viewport size upon main frame resize.
     * 
     * @param show Whether to paint size or not.
     */
    void setShowViewportSizeOnResize(Boolean show);

    void setSuspended(Boolean suspended);

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
