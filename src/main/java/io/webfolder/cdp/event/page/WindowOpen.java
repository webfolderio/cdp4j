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
import java.util.List;

import com.vimeo.stag.UseStag;

/**
 * Fired when a new window is going to be opened, via window
 * open(), link click, form submission,
 * etc
 */
@Domain("Page")
@EventName("windowOpen")
@UseStag
public class WindowOpen {
    private String url;

    private String windowName;

    private List<String> windowFeatures;

    private Boolean userGesture;

    /**
     * The URL for the new window.
     */
    public String getUrl() {
        return url;
    }

    /**
     * The URL for the new window.
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Window name.
     */
    public String getWindowName() {
        return windowName;
    }

    /**
     * Window name.
     */
    public void setWindowName(String windowName) {
        this.windowName = windowName;
    }

    /**
     * An array of enabled window features.
     */
    public List<String> getWindowFeatures() {
        return windowFeatures;
    }

    /**
     * An array of enabled window features.
     */
    public void setWindowFeatures(List<String> windowFeatures) {
        this.windowFeatures = windowFeatures;
    }

    /**
     * Whether or not it was triggered by user gesture.
     */
    public Boolean isUserGesture() {
        return userGesture;
    }

    /**
     * Whether or not it was triggered by user gesture.
     */
    public void setUserGesture(Boolean userGesture) {
        this.userGesture = userGesture;
    }
}
