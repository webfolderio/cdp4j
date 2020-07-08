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

import io.webfolder.cdp.type.dom.RGBA;
import io.webfolder.cdp.type.dom.Rect;

/**
 * Configuration for dual screen hinge
 */
public class HingeConfig {
    private Rect rect;

    private RGBA contentColor;

    private RGBA outlineColor;

    /**
     * A rectangle represent hinge
     */
    public Rect getRect() {
        return rect;
    }

    /**
     * A rectangle represent hinge
     */
    public void setRect(Rect rect) {
        this.rect = rect;
    }

    /**
     * The content box highlight fill color (default: a dark color).
     */
    public RGBA getContentColor() {
        return contentColor;
    }

    /**
     * The content box highlight fill color (default: a dark color).
     */
    public void setContentColor(RGBA contentColor) {
        this.contentColor = contentColor;
    }

    /**
     * The content box highlight outline color (default: transparent).
     */
    public RGBA getOutlineColor() {
        return outlineColor;
    }

    /**
     * The content box highlight outline color (default: transparent).
     */
    public void setOutlineColor(RGBA outlineColor) {
        this.outlineColor = outlineColor;
    }
}
