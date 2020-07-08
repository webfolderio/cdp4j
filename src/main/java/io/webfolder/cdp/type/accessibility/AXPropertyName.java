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
package io.webfolder.cdp.type.accessibility;

import com.google.gson.annotations.SerializedName;

/**
 * Values of AXProperty name:
 * - from 'busy' to 'roledescription': states which apply to every AX node
 * - from 'live' to 'root': attributes which apply to nodes in live regions
 * - from 'autocomplete' to 'valuetext': attributes which apply to widgets
 * - from 'checked' to 'selected': states which apply to widgets
 * - from 'activedescendant' to 'owns' - relationships between elements other than parent/child/sibling
 */
public enum AXPropertyName {
    @SerializedName("busy")
    Busy("busy"),

    @SerializedName("disabled")
    Disabled("disabled"),

    @SerializedName("editable")
    Editable("editable"),

    @SerializedName("focusable")
    Focusable("focusable"),

    @SerializedName("focused")
    Focused("focused"),

    @SerializedName("hidden")
    Hidden("hidden"),

    @SerializedName("hiddenRoot")
    HiddenRoot("hiddenRoot"),

    @SerializedName("invalid")
    Invalid("invalid"),

    @SerializedName("keyshortcuts")
    Keyshortcuts("keyshortcuts"),

    @SerializedName("settable")
    Settable("settable"),

    @SerializedName("roledescription")
    Roledescription("roledescription"),

    @SerializedName("live")
    Live("live"),

    @SerializedName("atomic")
    Atomic("atomic"),

    @SerializedName("relevant")
    Relevant("relevant"),

    @SerializedName("root")
    Root("root"),

    @SerializedName("autocomplete")
    Autocomplete("autocomplete"),

    @SerializedName("hasPopup")
    HasPopup("hasPopup"),

    @SerializedName("level")
    Level("level"),

    @SerializedName("multiselectable")
    Multiselectable("multiselectable"),

    @SerializedName("orientation")
    Orientation("orientation"),

    @SerializedName("multiline")
    Multiline("multiline"),

    @SerializedName("readonly")
    Readonly("readonly"),

    @SerializedName("required")
    Required("required"),

    @SerializedName("valuemin")
    Valuemin("valuemin"),

    @SerializedName("valuemax")
    Valuemax("valuemax"),

    @SerializedName("valuetext")
    Valuetext("valuetext"),

    @SerializedName("checked")
    Checked("checked"),

    @SerializedName("expanded")
    Expanded("expanded"),

    @SerializedName("modal")
    Modal("modal"),

    @SerializedName("pressed")
    Pressed("pressed"),

    @SerializedName("selected")
    Selected("selected"),

    @SerializedName("activedescendant")
    Activedescendant("activedescendant"),

    @SerializedName("controls")
    Controls("controls"),

    @SerializedName("describedby")
    Describedby("describedby"),

    @SerializedName("details")
    Details("details"),

    @SerializedName("errormessage")
    Errormessage("errormessage"),

    @SerializedName("flowto")
    Flowto("flowto"),

    @SerializedName("labelledby")
    Labelledby("labelledby"),

    @SerializedName("owns")
    Owns("owns");

    public final String value;

    AXPropertyName(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
