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
package io.webfolder.cdp.type.page;

/**
 * Layout viewport position and dimensions
 */
public class LayoutViewport {
    private Integer pageX;

    private Integer pageY;

    private Integer clientWidth;

    private Integer clientHeight;

    /**
     * Horizontal offset relative to the document (CSS pixels).
     */
    public Integer getPageX() {
        return pageX;
    }

    /**
     * Horizontal offset relative to the document (CSS pixels).
     */
    public void setPageX(Integer pageX) {
        this.pageX = pageX;
    }

    /**
     * Vertical offset relative to the document (CSS pixels).
     */
    public Integer getPageY() {
        return pageY;
    }

    /**
     * Vertical offset relative to the document (CSS pixels).
     */
    public void setPageY(Integer pageY) {
        this.pageY = pageY;
    }

    /**
     * Width (CSS pixels), excludes scrollbar if present.
     */
    public Integer getClientWidth() {
        return clientWidth;
    }

    /**
     * Width (CSS pixels), excludes scrollbar if present.
     */
    public void setClientWidth(Integer clientWidth) {
        this.clientWidth = clientWidth;
    }

    /**
     * Height (CSS pixels), excludes scrollbar if present.
     */
    public Integer getClientHeight() {
        return clientHeight;
    }

    /**
     * Height (CSS pixels), excludes scrollbar if present.
     */
    public void setClientHeight(Integer clientHeight) {
        this.clientHeight = clientHeight;
    }
}
