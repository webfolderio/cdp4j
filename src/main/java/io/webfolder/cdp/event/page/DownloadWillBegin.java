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
import io.webfolder.cdp.annotation.Experimental;

/**
 * Fired when page is about to start a download
 */
@Experimental
@Domain("Page")
@EventName("downloadWillBegin")
public class DownloadWillBegin {
    private String frameId;

    private String guid;

    private String url;

    private String suggestedFilename;

    /**
     * Id of the frame that caused download to begin.
     */
    public String getFrameId() {
        return frameId;
    }

    /**
     * Id of the frame that caused download to begin.
     */
    public void setFrameId(String frameId) {
        this.frameId = frameId;
    }

    /**
     * Global unique identifier of the download.
     */
    public String getGuid() {
        return guid;
    }

    /**
     * Global unique identifier of the download.
     */
    public void setGuid(String guid) {
        this.guid = guid;
    }

    /**
     * URL of the resource being downloaded.
     */
    public String getUrl() {
        return url;
    }

    /**
     * URL of the resource being downloaded.
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Suggested file name of the resource (the actual name of the file saved on disk may differ).
     */
    public String getSuggestedFilename() {
        return suggestedFilename;
    }

    /**
     * Suggested file name of the resource (the actual name of the file saved on disk may differ).
     */
    public void setSuggestedFilename(String suggestedFilename) {
        this.suggestedFilename = suggestedFilename;
    }
}
