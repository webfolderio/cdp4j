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
package io.webfolder.cdp.type.headlessexperimental;

import io.webfolder.cdp.type.constant.ImageFormat;

/**
 * Encoding options for a screenshot
 */
public class ScreenshotParams {
    private ImageFormat format;

    private Integer quality;

    /**
     * Image compression format (defaults to png).
     */
    public ImageFormat getFormat() {
        return format;
    }

    /**
     * Image compression format (defaults to png).
     */
    public void setFormat(ImageFormat format) {
        this.format = format;
    }

    /**
     * Compression quality from range [0..100] (jpeg only).
     */
    public Integer getQuality() {
        return quality;
    }

    /**
     * Compression quality from range [0..100] (jpeg only).
     */
    public void setQuality(Integer quality) {
        this.quality = quality;
    }
}
