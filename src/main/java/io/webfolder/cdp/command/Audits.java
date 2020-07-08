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
package io.webfolder.cdp.command;

import io.webfolder.cdp.annotation.Domain;
import io.webfolder.cdp.annotation.Experimental;
import io.webfolder.cdp.annotation.Optional;
import io.webfolder.cdp.type.audits.GetEncodedResponseResult;
import io.webfolder.cdp.type.constant.Encoding;

/**
 * Audits domain allows investigation of page violations and possible improvements
 */
@Experimental
@Domain("Audits")
public interface Audits {
    /**
     * Returns the response body and size if it were re-encoded with the specified settings. Only
     * applies to images.
     *
     * @param requestId Identifier of the network request to get content for.
     * @param encoding The encoding to use.
     * @param quality The quality of the encoding (0-1). (defaults to 1)
     * @param sizeOnly Whether to only return the size information (defaults to false).
     *
     * @return GetEncodedResponseResult
     */
    GetEncodedResponseResult getEncodedResponse(String requestId, Encoding encoding,
            @Optional Double quality, @Optional Boolean sizeOnly);

    /**
     * Disables issues domain, prevents further issues from being reported to the client.
     */
    void disable();

    /**
     * Enables issues domain, sends the issues collected so far to the client by means of the
     * issueAdded event.
     */
    void enable();

    /**
     * Returns the response body and size if it were re-encoded with the specified settings. Only
     * applies to images.
     *
     * @param requestId Identifier of the network request to get content for.
     * @param encoding The encoding to use.
     *
     * @return GetEncodedResponseResult
     */
    GetEncodedResponseResult getEncodedResponse(String requestId, Encoding encoding);
}
