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
import io.webfolder.cdp.type.constant.DownloadStatus;

/**
 * Fired when download makes progress
 * Last call has |done| == true
 */
@Experimental
@Domain("Page")
@EventName("downloadProgress")
public class DownloadProgress {
    private String guid;

    private Double totalBytes;

    private Double receivedBytes;

    private DownloadStatus state;

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
     * Total expected bytes to download.
     */
    public Double getTotalBytes() {
        return totalBytes;
    }

    /**
     * Total expected bytes to download.
     */
    public void setTotalBytes(Double totalBytes) {
        this.totalBytes = totalBytes;
    }

    /**
     * Total bytes received.
     */
    public Double getReceivedBytes() {
        return receivedBytes;
    }

    /**
     * Total bytes received.
     */
    public void setReceivedBytes(Double receivedBytes) {
        this.receivedBytes = receivedBytes;
    }

    /**
     * Download status.
     */
    public DownloadStatus getState() {
        return state;
    }

    /**
     * Download status.
     */
    public void setState(DownloadStatus state) {
        this.state = state;
    }
}
