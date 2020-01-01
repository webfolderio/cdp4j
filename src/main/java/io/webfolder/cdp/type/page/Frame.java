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
 * Information about the Frame on the page
 */
@UseStag
public class Frame {
    private String id;

    private String parentId;

    private String loaderId;

    private String name;

    private String url;

    private String urlFragment;

    private String securityOrigin;

    private String mimeType;

    private String unreachableUrl;

    /**
     * Frame unique identifier.
     */
    public String getId() {
        return id;
    }

    /**
     * Frame unique identifier.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Parent frame identifier.
     */
    public String getParentId() {
        return parentId;
    }

    /**
     * Parent frame identifier.
     */
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    /**
     * Identifier of the loader associated with this frame.
     */
    public String getLoaderId() {
        return loaderId;
    }

    /**
     * Identifier of the loader associated with this frame.
     */
    public void setLoaderId(String loaderId) {
        this.loaderId = loaderId;
    }

    /**
     * Frame's name as specified in the tag.
     */
    public String getName() {
        return name;
    }

    /**
     * Frame's name as specified in the tag.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Frame document's URL without fragment.
     */
    public String getUrl() {
        return url;
    }

    /**
     * Frame document's URL without fragment.
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Frame document's URL fragment including the '#'.
     */
    public String getUrlFragment() {
        return urlFragment;
    }

    /**
     * Frame document's URL fragment including the '#'.
     */
    public void setUrlFragment(String urlFragment) {
        this.urlFragment = urlFragment;
    }

    /**
     * Frame document's security origin.
     */
    public String getSecurityOrigin() {
        return securityOrigin;
    }

    /**
     * Frame document's security origin.
     */
    public void setSecurityOrigin(String securityOrigin) {
        this.securityOrigin = securityOrigin;
    }

    /**
     * Frame document's mimeType as determined by the browser.
     */
    public String getMimeType() {
        return mimeType;
    }

    /**
     * Frame document's mimeType as determined by the browser.
     */
    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    /**
     * If the frame failed to load, this contains the URL that could not be loaded. Note that unlike url above, this URL may contain a fragment.
     */
    public String getUnreachableUrl() {
        return unreachableUrl;
    }

    /**
     * If the frame failed to load, this contains the URL that could not be loaded. Note that unlike url above, this URL may contain a fragment.
     */
    public void setUnreachableUrl(String unreachableUrl) {
        this.unreachableUrl = unreachableUrl;
    }
}
