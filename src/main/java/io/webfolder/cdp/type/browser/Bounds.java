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
package io.webfolder.cdp.type.browser;

import io.webfolder.cdp.annotation.Experimental;

/**
 * Browser window bounds information
 */
@Experimental
public class Bounds {
    private Integer left;

    private Integer top;

    private Integer width;

    private Integer height;

    private WindowState windowState;

    /**
     * The offset from the left edge of the screen to the window in pixels.
     */
    public Integer getLeft() {
        return left;
    }

    /**
     * The offset from the left edge of the screen to the window in pixels.
     */
    public void setLeft(Integer left) {
        this.left = left;
    }

    /**
     * The offset from the top edge of the screen to the window in pixels.
     */
    public Integer getTop() {
        return top;
    }

    /**
     * The offset from the top edge of the screen to the window in pixels.
     */
    public void setTop(Integer top) {
        this.top = top;
    }

    /**
     * The window width in pixels.
     */
    public Integer getWidth() {
        return width;
    }

    /**
     * The window width in pixels.
     */
    public void setWidth(Integer width) {
        this.width = width;
    }

    /**
     * The window height in pixels.
     */
    public Integer getHeight() {
        return height;
    }

    /**
     * The window height in pixels.
     */
    public void setHeight(Integer height) {
        this.height = height;
    }

    /**
     * The window state. Default to normal.
     */
    public WindowState getWindowState() {
        return windowState;
    }

    /**
     * The window state. Default to normal.
     */
    public void setWindowState(WindowState windowState) {
        this.windowState = windowState;
    }
}
