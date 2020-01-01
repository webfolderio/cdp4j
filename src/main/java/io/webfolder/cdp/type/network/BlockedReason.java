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

import com.google.gson.annotations.SerializedName;
import com.vimeo.stag.UseStag;

/**
 * The reason why request was blocked
 */
@UseStag
public enum BlockedReason {
    @SerializedName("other")
    Other("other"),

    @SerializedName("csp")
    Csp("csp"),

    @SerializedName("mixed-content")
    MixedContent("mixed-content"),

    @SerializedName("origin")
    Origin("origin"),

    @SerializedName("inspector")
    Inspector("inspector"),

    @SerializedName("subresource-filter")
    SubresourceFilter("subresource-filter"),

    @SerializedName("content-type")
    ContentType("content-type"),

    @SerializedName("collapsed-by-client")
    CollapsedByClient("collapsed-by-client");

    public final String value;

    BlockedReason(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
