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
package io.webfolder.cdp.type.network;

import java.util.List;

import com.vimeo.stag.UseStag;

import io.webfolder.cdp.annotation.Experimental;

/**
 * Information about a signed exchange response
 */
@Experimental
@UseStag
public class SignedExchangeInfo {
    private Response outerResponse;

    private SignedExchangeHeader header;

    private SecurityDetails securityDetails;

    private List<SignedExchangeError> errors;

    /**
     * The outer response of signed HTTP exchange which was received from network.
     */
    public Response getOuterResponse() {
        return outerResponse;
    }

    /**
     * The outer response of signed HTTP exchange which was received from network.
     */
    public void setOuterResponse(Response outerResponse) {
        this.outerResponse = outerResponse;
    }

    /**
     * Information about the signed exchange header.
     */
    public SignedExchangeHeader getHeader() {
        return header;
    }

    /**
     * Information about the signed exchange header.
     */
    public void setHeader(SignedExchangeHeader header) {
        this.header = header;
    }

    /**
     * Security details for the signed exchange header.
     */
    public SecurityDetails getSecurityDetails() {
        return securityDetails;
    }

    /**
     * Security details for the signed exchange header.
     */
    public void setSecurityDetails(SecurityDetails securityDetails) {
        this.securityDetails = securityDetails;
    }

    /**
     * Errors occurred while handling the signed exchagne.
     */
    public List<SignedExchangeError> getErrors() {
        return errors;
    }

    /**
     * Errors occurred while handling the signed exchagne.
     */
    public void setErrors(List<SignedExchangeError> errors) {
        this.errors = errors;
    }
}
