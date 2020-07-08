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
import io.webfolder.cdp.type.security.CertificateErrorAction;

/**
 * Security
 */
@Domain("Security")
public interface Security {
    /**
     * Disables tracking security state changes.
     */
    void disable();

    /**
     * Enables tracking security state changes.
     */
    void enable();

    /**
     * Enable/disable whether all certificate errors should be ignored.
     *
     * @param ignore If true, all certificate errors will be ignored.
     */
    @Experimental
    void setIgnoreCertificateErrors(Boolean ignore);

    /**
     * Handles a certificate error that fired a certificateError event.
     *
     * @param eventId The ID of the event.
     * @param action The action to take on the certificate error.
     */
    void handleCertificateError(Integer eventId, CertificateErrorAction action);

    /**
     * Enable/disable overriding certificate errors. If enabled, all certificate error events need to
     * be handled by the DevTools client and should be answered with handleCertificateError commands.
     *
     * @param override If true, certificate errors will be overridden.
     */
    void setOverrideCertificateErrors(Boolean override);
}
