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
package io.webfolder.cdp.type.security;

import com.vimeo.stag.UseStag;

import io.webfolder.cdp.annotation.Experimental;

@Experimental
@UseStag
public class SafetyTipInfo {
    private SafetyTipStatus safetyTipStatus;

    private String safeUrl;

    /**
     * Describes whether the page triggers any safety tips or reputation warnings. Default is unknown.
     */
    public SafetyTipStatus getSafetyTipStatus() {
        return safetyTipStatus;
    }

    /**
     * Describes whether the page triggers any safety tips or reputation warnings. Default is unknown.
     */
    public void setSafetyTipStatus(SafetyTipStatus safetyTipStatus) {
        this.safetyTipStatus = safetyTipStatus;
    }

    /**
     * The URL the safety tip suggested ("Did you mean?"). Only filled in for lookalike matches.
     */
    public String getSafeUrl() {
        return safeUrl;
    }

    /**
     * The URL the safety tip suggested ("Did you mean?"). Only filled in for lookalike matches.
     */
    public void setSafeUrl(String safeUrl) {
        this.safeUrl = safeUrl;
    }
}
