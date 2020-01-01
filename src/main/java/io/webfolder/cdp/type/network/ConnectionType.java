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
 * The underlying connection technology that the browser is supposedly using
 */
@UseStag
public enum ConnectionType {
    @SerializedName("none")
    None("none"),

    @SerializedName("cellular2g")
    Cellular2g("cellular2g"),

    @SerializedName("cellular3g")
    Cellular3g("cellular3g"),

    @SerializedName("cellular4g")
    Cellular4g("cellular4g"),

    @SerializedName("bluetooth")
    Bluetooth("bluetooth"),

    @SerializedName("ethernet")
    Ethernet("ethernet"),

    @SerializedName("wifi")
    Wifi("wifi"),

    @SerializedName("wimax")
    Wimax("wimax"),

    @SerializedName("other")
    Other("other");

    public final String value;

    ConnectionType(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
