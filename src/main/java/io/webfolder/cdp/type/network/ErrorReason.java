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
 * Network level fetch failure reason
 */
@UseStag
public enum ErrorReason {
    @SerializedName("Failed")
    Failed("Failed"),

    @SerializedName("Aborted")
    Aborted("Aborted"),

    @SerializedName("TimedOut")
    TimedOut("TimedOut"),

    @SerializedName("AccessDenied")
    AccessDenied("AccessDenied"),

    @SerializedName("ConnectionClosed")
    ConnectionClosed("ConnectionClosed"),

    @SerializedName("ConnectionReset")
    ConnectionReset("ConnectionReset"),

    @SerializedName("ConnectionRefused")
    ConnectionRefused("ConnectionRefused"),

    @SerializedName("ConnectionAborted")
    ConnectionAborted("ConnectionAborted"),

    @SerializedName("ConnectionFailed")
    ConnectionFailed("ConnectionFailed"),

    @SerializedName("NameNotResolved")
    NameNotResolved("NameNotResolved"),

    @SerializedName("InternetDisconnected")
    InternetDisconnected("InternetDisconnected"),

    @SerializedName("AddressUnreachable")
    AddressUnreachable("AddressUnreachable"),

    @SerializedName("BlockedByClient")
    BlockedByClient("BlockedByClient"),

    @SerializedName("BlockedByResponse")
    BlockedByResponse("BlockedByResponse");

    public final String value;

    ErrorReason(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
