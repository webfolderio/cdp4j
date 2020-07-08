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
import io.webfolder.cdp.type.constant.FileChooserInputMode;

/**
 * Emitted only when page
 * interceptFileChooser is enabled
 */
@Domain("Page")
@EventName("fileChooserOpened")
public class FileChooserOpened {
    private String frameId;

    private Integer backendNodeId;

    private FileChooserInputMode mode;

    /**
     * Id of the frame containing input node.
     */
    public String getFrameId() {
        return frameId;
    }

    /**
     * Id of the frame containing input node.
     */
    public void setFrameId(String frameId) {
        this.frameId = frameId;
    }

    /**
     * Input node id.
     */
    public Integer getBackendNodeId() {
        return backendNodeId;
    }

    /**
     * Input node id.
     */
    public void setBackendNodeId(Integer backendNodeId) {
        this.backendNodeId = backendNodeId;
    }

    /**
     * Input mode.
     */
    public FileChooserInputMode getMode() {
        return mode;
    }

    /**
     * Input mode.
     */
    public void setMode(FileChooserInputMode mode) {
        this.mode = mode;
    }
}
