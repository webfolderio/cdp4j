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
package io.webfolder.cdp.type.browser;

import com.google.gson.annotations.SerializedName;
import com.vimeo.stag.UseStag;

@UseStag
public enum PermissionType {
    @SerializedName("accessibilityEvents")
    AccessibilityEvents("accessibilityEvents"),

    @SerializedName("audioCapture")
    AudioCapture("audioCapture"),

    @SerializedName("backgroundSync")
    BackgroundSync("backgroundSync"),

    @SerializedName("backgroundFetch")
    BackgroundFetch("backgroundFetch"),

    @SerializedName("clipboardRead")
    ClipboardRead("clipboardRead"),

    @SerializedName("clipboardWrite")
    ClipboardWrite("clipboardWrite"),

    @SerializedName("durableStorage")
    DurableStorage("durableStorage"),

    @SerializedName("flash")
    Flash("flash"),

    @SerializedName("geolocation")
    Geolocation("geolocation"),

    @SerializedName("midi")
    Midi("midi"),

    @SerializedName("midiSysex")
    MidiSysex("midiSysex"),

    @SerializedName("notifications")
    Notifications("notifications"),

    @SerializedName("paymentHandler")
    PaymentHandler("paymentHandler"),

    @SerializedName("periodicBackgroundSync")
    PeriodicBackgroundSync("periodicBackgroundSync"),

    @SerializedName("protectedMediaIdentifier")
    ProtectedMediaIdentifier("protectedMediaIdentifier"),

    @SerializedName("sensors")
    Sensors("sensors"),

    @SerializedName("videoCapture")
    VideoCapture("videoCapture"),

    @SerializedName("idleDetection")
    IdleDetection("idleDetection"),

    @SerializedName("wakeLockScreen")
    WakeLockScreen("wakeLockScreen"),

    @SerializedName("wakeLockSystem")
    WakeLockSystem("wakeLockSystem");

    public final String value;

    PermissionType(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
