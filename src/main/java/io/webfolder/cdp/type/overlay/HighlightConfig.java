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
package io.webfolder.cdp.type.overlay;

import com.vimeo.stag.UseStag;

import io.webfolder.cdp.type.dom.RGBA;

/**
 * Configuration data for the highlighting of page elements
 */
@UseStag
public class HighlightConfig {
    private Boolean showInfo;

    private Boolean showStyles;

    private Boolean showRulers;

    private Boolean showExtensionLines;

    private RGBA contentColor;

    private RGBA paddingColor;

    private RGBA borderColor;

    private RGBA marginColor;

    private RGBA eventTargetColor;

    private RGBA shapeColor;

    private RGBA shapeMarginColor;

    private RGBA cssGridColor;

    /**
     * Whether the node info tooltip should be shown (default: false).
     */
    public Boolean isShowInfo() {
        return showInfo;
    }

    /**
     * Whether the node info tooltip should be shown (default: false).
     */
    public void setShowInfo(Boolean showInfo) {
        this.showInfo = showInfo;
    }

    /**
     * Whether the node styles in the tooltip (default: false).
     */
    public Boolean isShowStyles() {
        return showStyles;
    }

    /**
     * Whether the node styles in the tooltip (default: false).
     */
    public void setShowStyles(Boolean showStyles) {
        this.showStyles = showStyles;
    }

    /**
     * Whether the rulers should be shown (default: false).
     */
    public Boolean isShowRulers() {
        return showRulers;
    }

    /**
     * Whether the rulers should be shown (default: false).
     */
    public void setShowRulers(Boolean showRulers) {
        this.showRulers = showRulers;
    }

    /**
     * Whether the extension lines from node to the rulers should be shown (default: false).
     */
    public Boolean isShowExtensionLines() {
        return showExtensionLines;
    }

    /**
     * Whether the extension lines from node to the rulers should be shown (default: false).
     */
    public void setShowExtensionLines(Boolean showExtensionLines) {
        this.showExtensionLines = showExtensionLines;
    }

    /**
     * The content box highlight fill color (default: transparent).
     */
    public RGBA getContentColor() {
        return contentColor;
    }

    /**
     * The content box highlight fill color (default: transparent).
     */
    public void setContentColor(RGBA contentColor) {
        this.contentColor = contentColor;
    }

    /**
     * The padding highlight fill color (default: transparent).
     */
    public RGBA getPaddingColor() {
        return paddingColor;
    }

    /**
     * The padding highlight fill color (default: transparent).
     */
    public void setPaddingColor(RGBA paddingColor) {
        this.paddingColor = paddingColor;
    }

    /**
     * The border highlight fill color (default: transparent).
     */
    public RGBA getBorderColor() {
        return borderColor;
    }

    /**
     * The border highlight fill color (default: transparent).
     */
    public void setBorderColor(RGBA borderColor) {
        this.borderColor = borderColor;
    }

    /**
     * The margin highlight fill color (default: transparent).
     */
    public RGBA getMarginColor() {
        return marginColor;
    }

    /**
     * The margin highlight fill color (default: transparent).
     */
    public void setMarginColor(RGBA marginColor) {
        this.marginColor = marginColor;
    }

    /**
     * The event target element highlight fill color (default: transparent).
     */
    public RGBA getEventTargetColor() {
        return eventTargetColor;
    }

    /**
     * The event target element highlight fill color (default: transparent).
     */
    public void setEventTargetColor(RGBA eventTargetColor) {
        this.eventTargetColor = eventTargetColor;
    }

    /**
     * The shape outside fill color (default: transparent).
     */
    public RGBA getShapeColor() {
        return shapeColor;
    }

    /**
     * The shape outside fill color (default: transparent).
     */
    public void setShapeColor(RGBA shapeColor) {
        this.shapeColor = shapeColor;
    }

    /**
     * The shape margin fill color (default: transparent).
     */
    public RGBA getShapeMarginColor() {
        return shapeMarginColor;
    }

    /**
     * The shape margin fill color (default: transparent).
     */
    public void setShapeMarginColor(RGBA shapeMarginColor) {
        this.shapeMarginColor = shapeMarginColor;
    }

    /**
     * The grid layout color (default: transparent).
     */
    public RGBA getCssGridColor() {
        return cssGridColor;
    }

    /**
     * The grid layout color (default: transparent).
     */
    public void setCssGridColor(RGBA cssGridColor) {
        this.cssGridColor = cssGridColor;
    }
}
