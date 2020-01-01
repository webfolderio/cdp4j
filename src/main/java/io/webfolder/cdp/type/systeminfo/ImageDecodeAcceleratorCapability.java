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
package io.webfolder.cdp.type.systeminfo;

import java.util.List;

import com.vimeo.stag.UseStag;

/**
 * Describes a supported image decoding profile with its associated minimum and
 * maximum resolutions and subsampling
 */
@UseStag
public class ImageDecodeAcceleratorCapability {
    private String imageType;

    private Size maxDimensions;

    private Size minDimensions;

    private List<SubsamplingFormat> subsamplings;

    /**
     * Image coded, e.g. Jpeg.
     */
    public String getImageType() {
        return imageType;
    }

    /**
     * Image coded, e.g. Jpeg.
     */
    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    /**
     * Maximum supported dimensions of the image in pixels.
     */
    public Size getMaxDimensions() {
        return maxDimensions;
    }

    /**
     * Maximum supported dimensions of the image in pixels.
     */
    public void setMaxDimensions(Size maxDimensions) {
        this.maxDimensions = maxDimensions;
    }

    /**
     * Minimum supported dimensions of the image in pixels.
     */
    public Size getMinDimensions() {
        return minDimensions;
    }

    /**
     * Minimum supported dimensions of the image in pixels.
     */
    public void setMinDimensions(Size minDimensions) {
        this.minDimensions = minDimensions;
    }

    /**
     * Optional array of supported subsampling formats, e.g. 4:2:0, if known.
     */
    public List<SubsamplingFormat> getSubsamplings() {
        return subsamplings;
    }

    /**
     * Optional array of supported subsampling formats, e.g. 4:2:0, if known.
     */
    public void setSubsamplings(List<SubsamplingFormat> subsamplings) {
        this.subsamplings = subsamplings;
    }
}
