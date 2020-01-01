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
import io.webfolder.cdp.type.runtime.StackTrace;

/**
 * Fired when frame has been attached to its parent
 */
@Domain("Page")
@EventName("frameAttached")
@UseStag
public class FrameAttached {
    private String frameId;

    private String parentFrameId;

    private StackTrace stack;

    /**
     * Id of the frame that has been attached.
     */
    public String getFrameId() {
        return frameId;
    }

    /**
     * Id of the frame that has been attached.
     */
    public void setFrameId(String frameId) {
        this.frameId = frameId;
    }

    /**
     * Parent frame identifier.
     */
    public String getParentFrameId() {
        return parentFrameId;
    }

    /**
     * Parent frame identifier.
     */
    public void setParentFrameId(String parentFrameId) {
        this.parentFrameId = parentFrameId;
    }

    /**
     * JavaScript stack trace of when frame was attached, only set if frame initiated from script.
     */
    public StackTrace getStack() {
        return stack;
    }

    /**
     * JavaScript stack trace of when frame was attached, only set if frame initiated from script.
     */
    public void setStack(StackTrace stack) {
        this.stack = stack;
    }
}
