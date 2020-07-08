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

/**
 * Describes a supported video encoding profile with its associated maximum
 * resolution and maximum framerate
 */
public class VideoEncodeAcceleratorCapability {
    private String profile;

    private Size maxResolution;

    private Integer maxFramerateNumerator;

    private Integer maxFramerateDenominator;

    /**
     * Video codec profile that is supported, e.g H264 Main.
     */
    public String getProfile() {
        return profile;
    }

    /**
     * Video codec profile that is supported, e.g H264 Main.
     */
    public void setProfile(String profile) {
        this.profile = profile;
    }

    /**
     * Maximum video dimensions in pixels supported for this |profile|.
     */
    public Size getMaxResolution() {
        return maxResolution;
    }

    /**
     * Maximum video dimensions in pixels supported for this |profile|.
     */
    public void setMaxResolution(Size maxResolution) {
        this.maxResolution = maxResolution;
    }

    /**
     * Maximum encoding framerate in frames per second supported for this
     * |profile|, as fraction's numerator and denominator, e.g. 24/1 fps,
     * 24000/1001 fps, etc.
     */
    public Integer getMaxFramerateNumerator() {
        return maxFramerateNumerator;
    }

    /**
     * Maximum encoding framerate in frames per second supported for this
     * |profile|, as fraction's numerator and denominator, e.g. 24/1 fps,
     * 24000/1001 fps, etc.
     */
    public void setMaxFramerateNumerator(Integer maxFramerateNumerator) {
        this.maxFramerateNumerator = maxFramerateNumerator;
    }

    public Integer getMaxFramerateDenominator() {
        return maxFramerateDenominator;
    }

    public void setMaxFramerateDenominator(Integer maxFramerateDenominator) {
        this.maxFramerateDenominator = maxFramerateDenominator;
    }
}
