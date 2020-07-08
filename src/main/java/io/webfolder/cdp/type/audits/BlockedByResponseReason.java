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

import com.google.gson.annotations.SerializedName;

/**
 * Enum indicating the reason a response has been blocked
 * These reasons are
 * refinements of the net error BLOCKED_BY_RESPONSE
 */
public enum BlockedByResponseReason {
    @SerializedName("CoepFrameResourceNeedsCoepHeader")
    CoepFrameResourceNeedsCoepHeader("CoepFrameResourceNeedsCoepHeader"),

    @SerializedName("CoopSandboxedIFrameCannotNavigateToCoopPage")
    CoopSandboxedIFrameCannotNavigateToCoopPage("CoopSandboxedIFrameCannotNavigateToCoopPage"),

    @SerializedName("CorpNotSameOrigin")
    CorpNotSameOrigin("CorpNotSameOrigin"),

    @SerializedName("CorpNotSameOriginAfterDefaultedToSameOriginByCoep")
    CorpNotSameOriginAfterDefaultedToSameOriginByCoep("CorpNotSameOriginAfterDefaultedToSameOriginByCoep"),

    @SerializedName("CorpNotSameSite")
    CorpNotSameSite("CorpNotSameSite");

    public final String value;

    BlockedByResponseReason(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
