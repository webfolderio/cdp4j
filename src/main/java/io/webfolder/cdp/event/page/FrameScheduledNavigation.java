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
package io.webfolder.cdp.event.page;

import io.webfolder.cdp.annotation.Domain;
import io.webfolder.cdp.annotation.EventName;
import io.webfolder.cdp.type.page.ClientNavigationReason;

/**
 * Fired when frame schedules a potential navigation
 */
@Domain("Page")
@EventName("frameScheduledNavigation")
public class FrameScheduledNavigation {
    private String frameId;

    private Double delay;

    private ClientNavigationReason reason;

    private String url;

    /**
     * Id of the frame that has scheduled a navigation.
     */
    public String getFrameId() {
        return frameId;
    }

    /**
     * Id of the frame that has scheduled a navigation.
     */
    public void setFrameId(String frameId) {
        this.frameId = frameId;
    }

    /**
     * Delay (in seconds) until the navigation is scheduled to begin. The navigation is not
     * guaranteed to start.
     */
    public Double getDelay() {
        return delay;
    }

    /**
     * Delay (in seconds) until the navigation is scheduled to begin. The navigation is not
     * guaranteed to start.
     */
    public void setDelay(Double delay) {
        this.delay = delay;
    }

    /**
     * The reason for the navigation.
     */
    public ClientNavigationReason getReason() {
        return reason;
    }

    /**
     * The reason for the navigation.
     */
    public void setReason(ClientNavigationReason reason) {
        this.reason = reason;
    }

    /**
     * The destination URL for the scheduled navigation.
     */
    public String getUrl() {
        return url;
    }

    /**
     * The destination URL for the scheduled navigation.
     */
    public void setUrl(String url) {
        this.url = url;
    }
}
