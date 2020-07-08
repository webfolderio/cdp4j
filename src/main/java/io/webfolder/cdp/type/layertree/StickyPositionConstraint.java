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

import io.webfolder.cdp.type.dom.Rect;

/**
 * Sticky position constraints
 */
public class StickyPositionConstraint {
    private Rect stickyBoxRect;

    private Rect containingBlockRect;

    private String nearestLayerShiftingStickyBox;

    private String nearestLayerShiftingContainingBlock;

    /**
     * Layout rectangle of the sticky element before being shifted
     */
    public Rect getStickyBoxRect() {
        return stickyBoxRect;
    }

    /**
     * Layout rectangle of the sticky element before being shifted
     */
    public void setStickyBoxRect(Rect stickyBoxRect) {
        this.stickyBoxRect = stickyBoxRect;
    }

    /**
     * Layout rectangle of the containing block of the sticky element
     */
    public Rect getContainingBlockRect() {
        return containingBlockRect;
    }

    /**
     * Layout rectangle of the containing block of the sticky element
     */
    public void setContainingBlockRect(Rect containingBlockRect) {
        this.containingBlockRect = containingBlockRect;
    }

    /**
     * The nearest sticky layer that shifts the sticky box
     */
    public String getNearestLayerShiftingStickyBox() {
        return nearestLayerShiftingStickyBox;
    }

    /**
     * The nearest sticky layer that shifts the sticky box
     */
    public void setNearestLayerShiftingStickyBox(String nearestLayerShiftingStickyBox) {
        this.nearestLayerShiftingStickyBox = nearestLayerShiftingStickyBox;
    }

    /**
     * The nearest sticky layer that shifts the containing block
     */
    public String getNearestLayerShiftingContainingBlock() {
        return nearestLayerShiftingContainingBlock;
    }

    /**
     * The nearest sticky layer that shifts the containing block
     */
    public void setNearestLayerShiftingContainingBlock(String nearestLayerShiftingContainingBlock) {
        this.nearestLayerShiftingContainingBlock = nearestLayerShiftingContainingBlock;
    }
}
