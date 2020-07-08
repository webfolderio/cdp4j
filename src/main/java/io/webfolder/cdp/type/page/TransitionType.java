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
package io.webfolder.cdp.type.page;

import com.google.gson.annotations.SerializedName;

/**
 * Transition type
 */
public enum TransitionType {
    @SerializedName("link")
    Link("link"),

    @SerializedName("typed")
    Typed("typed"),

    @SerializedName("address_bar")
    AddressBar("address_bar"),

    @SerializedName("auto_bookmark")
    AutoBookmark("auto_bookmark"),

    @SerializedName("auto_subframe")
    AutoSubframe("auto_subframe"),

    @SerializedName("manual_subframe")
    ManualSubframe("manual_subframe"),

    @SerializedName("generated")
    Generated("generated"),

    @SerializedName("auto_toplevel")
    AutoToplevel("auto_toplevel"),

    @SerializedName("form_submit")
    FormSubmit("form_submit"),

    @SerializedName("reload")
    Reload("reload"),

    @SerializedName("keyword")
    Keyword("keyword"),

    @SerializedName("keyword_generated")
    KeywordGenerated("keyword_generated"),

    @SerializedName("other")
    Other("other");

    public final String value;

    TransitionType(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
