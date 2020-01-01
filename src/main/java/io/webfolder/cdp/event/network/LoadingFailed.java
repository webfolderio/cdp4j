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
package io.webfolder.cdp.event.network;

import com.vimeo.stag.UseStag;

import io.webfolder.cdp.annotation.Domain;
import io.webfolder.cdp.annotation.EventName;
import io.webfolder.cdp.type.network.BlockedReason;
import io.webfolder.cdp.type.network.ResourceType;

/**
 * Fired when HTTP request has failed to load
 */
@Domain("Network")
@EventName("loadingFailed")
@UseStag
public class LoadingFailed {
    private String requestId;

    private Double timestamp;

    private ResourceType type;

    private String errorText;

    private Boolean canceled;

    private BlockedReason blockedReason;

    /**
     * Request identifier.
     */
    public String getRequestId() {
        return requestId;
    }

    /**
     * Request identifier.
     */
    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    /**
     * Timestamp.
     */
    public Double getTimestamp() {
        return timestamp;
    }

    /**
     * Timestamp.
     */
    public void setTimestamp(Double timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Resource type.
     */
    public ResourceType getType() {
        return type;
    }

    /**
     * Resource type.
     */
    public void setType(ResourceType type) {
        this.type = type;
    }

    /**
     * User friendly error message.
     */
    public String getErrorText() {
        return errorText;
    }

    /**
     * User friendly error message.
     */
    public void setErrorText(String errorText) {
        this.errorText = errorText;
    }

    /**
     * True if loading was canceled.
     */
    public Boolean isCanceled() {
        return canceled;
    }

    /**
     * True if loading was canceled.
     */
    public void setCanceled(Boolean canceled) {
        this.canceled = canceled;
    }

    /**
     * The reason why loading was blocked, if any.
     */
    public BlockedReason getBlockedReason() {
        return blockedReason;
    }

    /**
     * The reason why loading was blocked, if any.
     */
    public void setBlockedReason(BlockedReason blockedReason) {
        this.blockedReason = blockedReason;
    }
}
