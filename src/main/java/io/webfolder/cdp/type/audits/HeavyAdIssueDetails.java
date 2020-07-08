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
package io.webfolder.cdp.type.audits;

public class HeavyAdIssueDetails {
    private HeavyAdResolutionStatus resolution;

    private HeavyAdReason reason;

    private AffectedFrame frame;

    /**
     * The resolution status, either blocking the content or warning.
     */
    public HeavyAdResolutionStatus getResolution() {
        return resolution;
    }

    /**
     * The resolution status, either blocking the content or warning.
     */
    public void setResolution(HeavyAdResolutionStatus resolution) {
        this.resolution = resolution;
    }

    /**
     * The reason the ad was blocked, total network or cpu or peak cpu.
     */
    public HeavyAdReason getReason() {
        return reason;
    }

    /**
     * The reason the ad was blocked, total network or cpu or peak cpu.
     */
    public void setReason(HeavyAdReason reason) {
        this.reason = reason;
    }

    /**
     * The frame that was blocked.
     */
    public AffectedFrame getFrame() {
        return frame;
    }

    /**
     * The frame that was blocked.
     */
    public void setFrame(AffectedFrame frame) {
        this.frame = frame;
    }
}
