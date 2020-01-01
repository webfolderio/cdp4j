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

import com.vimeo.stag.UseStag;

import io.webfolder.cdp.annotation.Domain;
import io.webfolder.cdp.annotation.EventName;
import io.webfolder.cdp.annotation.Experimental;
import io.webfolder.cdp.type.page.ClientNavigationReason;

/**
 * Fired when a renderer-initiated navigation is requested
 * Navigation may still be cancelled after the event is issued
 */
@Experimental
@Domain("Page")
@EventName("frameRequestedNavigation")
@UseStag
public class FrameRequestedNavigation {
    private String frameId;

    private ClientNavigationReason reason;

    private String url;

    /**
     * Id of the frame that is being navigated.
     */
    public String getFrameId() {
        return frameId;
    }

    /**
     * Id of the frame that is being navigated.
     */
    public void setFrameId(String frameId) {
        this.frameId = frameId;
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
     * The destination URL for the requested navigation.
     */
    public String getUrl() {
        return url;
    }

    /**
     * The destination URL for the requested navigation.
     */
    public void setUrl(String url) {
        this.url = url;
    }
}
