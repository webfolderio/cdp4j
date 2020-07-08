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

/**
 * Corresponds to kMediaError
 */
public class PlayerError {
    private io.webfolder.cdp.type.constant.PlayerError type;

    private String errorCode;

    public io.webfolder.cdp.type.constant.PlayerError getType() {
        return type;
    }

    public void setType(io.webfolder.cdp.type.constant.PlayerError type) {
        this.type = type;
    }

    /**
     * When this switches to using media::Status instead of PipelineStatus
     * we can remove "errorCode" and replace it with the fields from
     * a Status instance. This also seems like a duplicate of the error
     * level enum - there is a todo bug to have that level removed and
     * use this instead. (crbug.com/1068454)
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * When this switches to using media::Status instead of PipelineStatus
     * we can remove "errorCode" and replace it with the fields from
     * a Status instance. This also seems like a duplicate of the error
     * level enum - there is a todo bug to have that level removed and
     * use this instead. (crbug.com/1068454)
     */
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
