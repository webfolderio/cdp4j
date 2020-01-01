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

/**
 * Navigation history entry
 */
@UseStag
public class NavigationEntry {
    private Integer id;

    private String url;

    private String userTypedURL;

    private String title;

    private TransitionType transitionType;

    /**
     * Unique id of the navigation history entry.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Unique id of the navigation history entry.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * URL of the navigation history entry.
     */
    public String getUrl() {
        return url;
    }

    /**
     * URL of the navigation history entry.
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * URL that the user typed in the url bar.
     */
    public String getUserTypedURL() {
        return userTypedURL;
    }

    /**
     * URL that the user typed in the url bar.
     */
    public void setUserTypedURL(String userTypedURL) {
        this.userTypedURL = userTypedURL;
    }

    /**
     * Title of the navigation history entry.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Title of the navigation history entry.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Transition type.
     */
    public TransitionType getTransitionType() {
        return transitionType;
    }

    /**
     * Transition type.
     */
    public void setTransitionType(TransitionType transitionType) {
        this.transitionType = transitionType;
    }
}
