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
package io.webfolder.cdp.type.audits;

public class MixedContentIssueDetails {
    private MixedContentResourceType resourceType;

    private MixedContentResolutionStatus resolutionStatus;

    private String insecureURL;

    private String mainResourceURL;

    private AffectedRequest request;

    private AffectedFrame frame;

    /**
     * The type of resource causing the mixed content issue (css, js, iframe,
     * form,...). Marked as optional because it is mapped to from
     * blink::mojom::RequestContextType, which will be replaced
     * by network::mojom::RequestDestination
     */
    public MixedContentResourceType getResourceType() {
        return resourceType;
    }

    /**
     * The type of resource causing the mixed content issue (css, js, iframe,
     * form,...). Marked as optional because it is mapped to from
     * blink::mojom::RequestContextType, which will be replaced
     * by network::mojom::RequestDestination
     */
    public void setResourceType(MixedContentResourceType resourceType) {
        this.resourceType = resourceType;
    }

    /**
     * The way the mixed content issue is being resolved.
     */
    public MixedContentResolutionStatus getResolutionStatus() {
        return resolutionStatus;
    }

    /**
     * The way the mixed content issue is being resolved.
     */
    public void setResolutionStatus(MixedContentResolutionStatus resolutionStatus) {
        this.resolutionStatus = resolutionStatus;
    }

    /**
     * The unsafe http url causing the mixed content issue.
     */
    public String getInsecureURL() {
        return insecureURL;
    }

    /**
     * The unsafe http url causing the mixed content issue.
     */
    public void setInsecureURL(String insecureURL) {
        this.insecureURL = insecureURL;
    }

    /**
     * The url responsible for the call to an unsafe url.
     */
    public String getMainResourceURL() {
        return mainResourceURL;
    }

    /**
     * The url responsible for the call to an unsafe url.
     */
    public void setMainResourceURL(String mainResourceURL) {
        this.mainResourceURL = mainResourceURL;
    }

    /**
     * The mixed content request.
     * Does not always exist (e.g. for unsafe form submission urls).
     */
    public AffectedRequest getRequest() {
        return request;
    }

    /**
     * The mixed content request.
     * Does not always exist (e.g. for unsafe form submission urls).
     */
    public void setRequest(AffectedRequest request) {
        this.request = request;
    }

    /**
     * Optional because not every mixed content issue is necessarily linked to a frame.
     */
    public AffectedFrame getFrame() {
        return frame;
    }

    /**
     * Optional because not every mixed content issue is necessarily linked to a frame.
     */
    public void setFrame(AffectedFrame frame) {
        this.frame = frame;
    }
}
