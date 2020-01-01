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
import io.webfolder.cdp.type.page.ScreencastFrameMetadata;

/**
 * Compressed image data requested by the `startScreencast`
 */
@Experimental
@Domain("Page")
@EventName("screencastFrame")
@UseStag
public class ScreencastFrame {
    private String data;

    private ScreencastFrameMetadata metadata;

    private Integer sessionId;

    /**
     * Base64-encoded compressed image.
     */
    public String getData() {
        return data;
    }

    /**
     * Base64-encoded compressed image.
     */
    public void setData(String data) {
        this.data = data;
    }

    /**
     * Screencast frame metadata.
     */
    public ScreencastFrameMetadata getMetadata() {
        return metadata;
    }

    /**
     * Screencast frame metadata.
     */
    public void setMetadata(ScreencastFrameMetadata metadata) {
        this.metadata = metadata;
    }

    /**
     * Frame number.
     */
    public Integer getSessionId() {
        return sessionId;
    }

    /**
     * Frame number.
     */
    public void setSessionId(Integer sessionId) {
        this.sessionId = sessionId;
    }
}
