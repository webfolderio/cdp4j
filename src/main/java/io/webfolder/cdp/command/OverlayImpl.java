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

import io.webfolder.cdp.session.SessionInvocationHandler;
import io.webfolder.cdp.command.Overlay;
import java.util.List;
import io.webfolder.cdp.type.dom.RGBA;
import io.webfolder.cdp.type.overlay.HighlightConfig;
import io.webfolder.cdp.type.overlay.InspectMode;

public class OverlayImpl implements Overlay {

    private static final String[] EMPTY_ARGS = new String[] {};
    private static final Object[] EMPTY_VALUES = new Object[] {};
    private final SessionInvocationHandler handler;

    public OverlayImpl(SessionInvocationHandler handler) {
        this.handler = handler;
    }

    @Override
    public void disable() {
        handler.invoke("Overlay", "disable", "Overlay.disable", null, void.class, null, true, false, true, EMPTY_ARGS,
                EMPTY_VALUES);
    }

    @Override
    public void enable() {
        handler.invoke("Overlay", "enable", "Overlay.enable", null, void.class, null, true, true, false, EMPTY_ARGS,
                EMPTY_VALUES);
    }

    @Override
    public void hideHighlight() {
        handler.invoke("Overlay", "hideHighlight", "Overlay.hideHighlight", null, void.class, null, true, false, false,
                EMPTY_ARGS, EMPTY_VALUES);
    }

    @Override
    public void highlightFrame(String frameId) {
        handler.invoke("Overlay", "highlightFrame", "Overlay.highlightFrame", null, void.class, null, true, false,
                false, new String[] { "frameId" }, new Object[] { frameId });
    }

    @Override
    public void highlightFrame(String frameId, RGBA contentColor, RGBA contentOutlineColor) {
        handler.invoke("Overlay", "highlightFrame", "Overlay.highlightFrame", null, void.class, null, true, false,
                false, new String[] { "frameId", "contentColor", "contentOutlineColor" },
                new Object[] { frameId, contentColor, contentOutlineColor });
    }

    @Override
    public void highlightNode(HighlightConfig highlightConfig) {
        handler.invoke("Overlay", "highlightNode", "Overlay.highlightNode", null, void.class, null, true, false, false,
                new String[] { "highlightConfig" }, new Object[] { highlightConfig });
    }

    @Override
    public void highlightNode(HighlightConfig highlightConfig, Integer nodeId, Integer backendNodeId, String objectId,
            String selector) {
        handler.invoke("Overlay", "highlightNode", "Overlay.highlightNode", null, void.class, null, true, false, false,
                new String[] { "highlightConfig", "nodeId", "backendNodeId", "objectId", "selector" },
                new Object[] { highlightConfig, nodeId, backendNodeId, objectId, selector });
    }

    @Override
    public void highlightQuad(List<Double> quad) {
        handler.invoke("Overlay", "highlightQuad", "Overlay.highlightQuad", null, void.class, null, true, false, false,
                new String[] { "quad" }, new Object[] { quad });
    }

    @Override
    public void highlightQuad(List<Double> quad, RGBA color, RGBA outlineColor) {
        handler.invoke("Overlay", "highlightQuad", "Overlay.highlightQuad", null, void.class, null, true, false, false,
                new String[] { "quad", "color", "outlineColor" }, new Object[] { quad, color, outlineColor });
    }

    @Override
    public void highlightRect(Integer x, Integer y, Integer width, Integer height) {
        handler.invoke("Overlay", "highlightRect", "Overlay.highlightRect", null, void.class, null, true, false, false,
                new String[] { "x", "y", "width", "height" }, new Object[] { x, y, width, height });
    }

    @Override
    public void highlightRect(Integer x, Integer y, Integer width, Integer height, RGBA color, RGBA outlineColor) {
        handler.invoke("Overlay", "highlightRect", "Overlay.highlightRect", null, void.class, null, true, false, false,
                new String[] { "x", "y", "width", "height", "color", "outlineColor" },
                new Object[] { x, y, width, height, color, outlineColor });
    }

    @Override
    public void setInspectMode(InspectMode mode) {
        handler.invoke("Overlay", "setInspectMode", "Overlay.setInspectMode", null, void.class, null, true, false,
                false, new String[] { "mode" }, new Object[] { mode });
    }

    @Override
    public void setInspectMode(InspectMode mode, HighlightConfig highlightConfig) {
        handler.invoke("Overlay", "setInspectMode", "Overlay.setInspectMode", null, void.class, null, true, false,
                false, new String[] { "mode", "highlightConfig" }, new Object[] { mode, highlightConfig });
    }

    @Override
    public void setPausedInDebuggerMessage() {
        handler.invoke("Overlay", "setPausedInDebuggerMessage", "Overlay.setPausedInDebuggerMessage", null, void.class,
                null, true, false, false, EMPTY_ARGS, EMPTY_VALUES);
    }

    @Override
    public void setPausedInDebuggerMessage(String message) {
        handler.invoke("Overlay", "setPausedInDebuggerMessage", "Overlay.setPausedInDebuggerMessage", null, void.class,
                null, true, false, false, new String[] { "message" }, new Object[] { message });
    }

    @Override
    public void setShowAdHighlights(Boolean show) {
        handler.invoke("Overlay", "setShowAdHighlights", "Overlay.setShowAdHighlights", null, void.class, null, true,
                false, false, new String[] { "show" }, new Object[] { show });
    }

    @Override
    public void setShowDebugBorders(Boolean show) {
        handler.invoke("Overlay", "setShowDebugBorders", "Overlay.setShowDebugBorders", null, void.class, null, true,
                false, false, new String[] { "show" }, new Object[] { show });
    }

    @Override
    public void setShowFPSCounter(Boolean show) {
        handler.invoke("Overlay", "setShowFPSCounter", "Overlay.setShowFPSCounter", null, void.class, null, true, false,
                false, new String[] { "show" }, new Object[] { show });
    }

    @Override
    public void setShowHitTestBorders(Boolean show) {
        handler.invoke("Overlay", "setShowHitTestBorders", "Overlay.setShowHitTestBorders", null, void.class, null,
                true, false, false, new String[] { "show" }, new Object[] { show });
    }

    @Override
    public void setShowLayoutShiftRegions(Boolean result) {
        handler.invoke("Overlay", "setShowLayoutShiftRegions", "Overlay.setShowLayoutShiftRegions", null, void.class,
                null, true, false, false, new String[] { "result" }, new Object[] { result });
    }

    @Override
    public void setShowPaintRects(Boolean result) {
        handler.invoke("Overlay", "setShowPaintRects", "Overlay.setShowPaintRects", null, void.class, null, true, false,
                false, new String[] { "result" }, new Object[] { result });
    }

    @Override
    public void setShowScrollBottleneckRects(Boolean show) {
        handler.invoke("Overlay", "setShowScrollBottleneckRects", "Overlay.setShowScrollBottleneckRects", null,
                void.class, null, true, false, false, new String[] { "show" }, new Object[] { show });
    }

    @Override
    public void setShowViewportSizeOnResize(Boolean show) {
        handler.invoke("Overlay", "setShowViewportSizeOnResize", "Overlay.setShowViewportSizeOnResize", null,
                void.class, null, true, false, false, new String[] { "show" }, new Object[] { show });
    }
}