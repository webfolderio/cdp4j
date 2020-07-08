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
package io.webfolder.cdp.type.media;

import io.webfolder.cdp.type.constant.MediaLogMessageLevel;

/**
 * Have one type per entry in MediaLogRecord::Type
 * Corresponds to kMessage
 */
public class PlayerMessage {
    private MediaLogMessageLevel level;

    private String message;

    /**
     * Keep in sync with MediaLogMessageLevel
     * We are currently keeping the message level 'error' separate from the
     * PlayerError type because right now they represent different things,
     * this one being a DVLOG(ERROR) style log message that gets printed
     * based on what log level is selected in the UI, and the other is a
     * representation of a media::PipelineStatus object. Soon however we're
     * going to be moving away from using PipelineStatus for errors and
     * introducing a new error type which should hopefully let us integrate
     * the error log level into the PlayerError type.
     */
    public MediaLogMessageLevel getLevel() {
        return level;
    }

    /**
     * Keep in sync with MediaLogMessageLevel
     * We are currently keeping the message level 'error' separate from the
     * PlayerError type because right now they represent different things,
     * this one being a DVLOG(ERROR) style log message that gets printed
     * based on what log level is selected in the UI, and the other is a
     * representation of a media::PipelineStatus object. Soon however we're
     * going to be moving away from using PipelineStatus for errors and
     * introducing a new error type which should hopefully let us integrate
     * the error log level into the PlayerError type.
     */
    public void setLevel(MediaLogMessageLevel level) {
        this.level = level;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
