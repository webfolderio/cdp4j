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
package io.webfolder.cdp.type.dom;

import com.google.gson.annotations.SerializedName;

/**
 * Pseudo element type
 */
public enum PseudoType {
    @SerializedName("first-line")
    FirstLine("first-line"),

    @SerializedName("first-letter")
    FirstLetter("first-letter"),

    @SerializedName("before")
    Before("before"),

    @SerializedName("after")
    After("after"),

    @SerializedName("marker")
    Marker("marker"),

    @SerializedName("backdrop")
    Backdrop("backdrop"),

    @SerializedName("selection")
    Selection("selection"),

    @SerializedName("first-line-inherited")
    FirstLineInherited("first-line-inherited"),

    @SerializedName("scrollbar")
    Scrollbar("scrollbar"),

    @SerializedName("scrollbar-thumb")
    ScrollbarThumb("scrollbar-thumb"),

    @SerializedName("scrollbar-button")
    ScrollbarButton("scrollbar-button"),

    @SerializedName("scrollbar-track")
    ScrollbarTrack("scrollbar-track"),

    @SerializedName("scrollbar-track-piece")
    ScrollbarTrackPiece("scrollbar-track-piece"),

    @SerializedName("scrollbar-corner")
    ScrollbarCorner("scrollbar-corner"),

    @SerializedName("resizer")
    Resizer("resizer"),

    @SerializedName("input-list-button")
    InputListButton("input-list-button");

    public final String value;

    PseudoType(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
