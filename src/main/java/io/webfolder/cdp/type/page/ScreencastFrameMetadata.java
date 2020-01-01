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

import com.vimeo.stag.UseStag;

import io.webfolder.cdp.annotation.Experimental;

/**
 * Screencast frame metadata
 */
@Experimental
@UseStag
public class ScreencastFrameMetadata {
    private Double offsetTop;

    private Double pageScaleFactor;

    private Double deviceWidth;

    private Double deviceHeight;

    private Double scrollOffsetX;

    private Double scrollOffsetY;

    private Double timestamp;

    /**
     * Top offset in DIP.
     */
    public Double getOffsetTop() {
        return offsetTop;
    }

    /**
     * Top offset in DIP.
     */
    public void setOffsetTop(Double offsetTop) {
        this.offsetTop = offsetTop;
    }

    /**
     * Page scale factor.
     */
    public Double getPageScaleFactor() {
        return pageScaleFactor;
    }

    /**
     * Page scale factor.
     */
    public void setPageScaleFactor(Double pageScaleFactor) {
        this.pageScaleFactor = pageScaleFactor;
    }

    /**
     * Device screen width in DIP.
     */
    public Double getDeviceWidth() {
        return deviceWidth;
    }

    /**
     * Device screen width in DIP.
     */
    public void setDeviceWidth(Double deviceWidth) {
        this.deviceWidth = deviceWidth;
    }

    /**
     * Device screen height in DIP.
     */
    public Double getDeviceHeight() {
        return deviceHeight;
    }

    /**
     * Device screen height in DIP.
     */
    public void setDeviceHeight(Double deviceHeight) {
        this.deviceHeight = deviceHeight;
    }

    /**
     * Position of horizontal scroll in CSS pixels.
     */
    public Double getScrollOffsetX() {
        return scrollOffsetX;
    }

    /**
     * Position of horizontal scroll in CSS pixels.
     */
    public void setScrollOffsetX(Double scrollOffsetX) {
        this.scrollOffsetX = scrollOffsetX;
    }

    /**
     * Position of vertical scroll in CSS pixels.
     */
    public Double getScrollOffsetY() {
        return scrollOffsetY;
    }

    /**
     * Position of vertical scroll in CSS pixels.
     */
    public void setScrollOffsetY(Double scrollOffsetY) {
        this.scrollOffsetY = scrollOffsetY;
    }

    /**
     * Frame swap timestamp.
     */
    public Double getTimestamp() {
        return timestamp;
    }

    /**
     * Frame swap timestamp.
     */
    public void setTimestamp(Double timestamp) {
        this.timestamp = timestamp;
    }
}
