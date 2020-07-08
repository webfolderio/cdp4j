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
package io.webfolder.cdp.type.applicationcache;

/**
 * Frame identifier - manifest URL pair
 */
public class FrameWithManifest {
    private String frameId;

    private String manifestURL;

    private Integer status;

    /**
     * Frame identifier.
     */
    public String getFrameId() {
        return frameId;
    }

    /**
     * Frame identifier.
     */
    public void setFrameId(String frameId) {
        this.frameId = frameId;
    }

    /**
     * Manifest URL.
     */
    public String getManifestURL() {
        return manifestURL;
    }

    /**
     * Manifest URL.
     */
    public void setManifestURL(String manifestURL) {
        this.manifestURL = manifestURL;
    }

    /**
     * Application cache status.
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * Application cache status.
     */
    public void setStatus(Integer status) {
        this.status = status;
    }
}
