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

/**
 * Fired when a navigation is started if navigation throttles are enabled
 * The navigation will be deferred until processNavigation is called
 */
@Domain("Page")
@EventName("navigationRequested")
@UseStag
public class NavigationRequested {
    private Boolean isInMainFrame;

    private Boolean isRedirect;

    private Integer navigationId;

    private String url;

    /**
     * Whether the navigation is taking place in the main frame or in a subframe.
     */
    public Boolean getIsInMainFrame() {
        return isInMainFrame;
    }

    /**
     * Whether the navigation is taking place in the main frame or in a subframe.
     */
    public void setIsInMainFrame(Boolean isInMainFrame) {
        this.isInMainFrame = isInMainFrame;
    }

    /**
     * Whether the navigation has encountered a server redirect or not.
     */
    public Boolean getIsRedirect() {
        return isRedirect;
    }

    /**
     * Whether the navigation has encountered a server redirect or not.
     */
    public void setIsRedirect(Boolean isRedirect) {
        this.isRedirect = isRedirect;
    }

    public Integer getNavigationId() {
        return navigationId;
    }

    public void setNavigationId(Integer navigationId) {
        this.navigationId = navigationId;
    }

    /**
     * URL of requested navigation.
     */
    public String getUrl() {
        return url;
    }

    /**
     * URL of requested navigation.
     */
    public void setUrl(String url) {
        this.url = url;
    }
}
