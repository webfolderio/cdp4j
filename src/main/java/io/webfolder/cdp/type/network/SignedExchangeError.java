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

import com.vimeo.stag.UseStag;

import io.webfolder.cdp.annotation.Experimental;

/**
 * Information about a signed exchange response
 */
@Experimental
@UseStag
public class SignedExchangeError {
    private String message;

    private Integer signatureIndex;

    private SignedExchangeErrorField errorField;

    /**
     * Error message.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Error message.
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * The index of the signature which caused the error.
     */
    public Integer getSignatureIndex() {
        return signatureIndex;
    }

    /**
     * The index of the signature which caused the error.
     */
    public void setSignatureIndex(Integer signatureIndex) {
        this.signatureIndex = signatureIndex;
    }

    /**
     * The field which caused the error.
     */
    public SignedExchangeErrorField getErrorField() {
        return errorField;
    }

    /**
     * The field which caused the error.
     */
    public void setErrorField(SignedExchangeErrorField errorField) {
        this.errorField = errorField;
    }
}
