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

/**
 * Details for a request that has been blocked with the BLOCKED_BY_RESPONSE
 * code
 * Currently only used for COEP/COOP, but may be extended to include
 * some CSP errors in the future
 */
public class BlockedByResponseIssueDetails {
    private AffectedRequest request;

    private AffectedFrame frame;

    private BlockedByResponseReason reason;

    public AffectedRequest getRequest() {
        return request;
    }

    public void setRequest(AffectedRequest request) {
        this.request = request;
    }

    public AffectedFrame getFrame() {
        return frame;
    }

    public void setFrame(AffectedFrame frame) {
        this.frame = frame;
    }

    public BlockedByResponseReason getReason() {
        return reason;
    }

    public void setReason(BlockedByResponseReason reason) {
        this.reason = reason;
    }
}
