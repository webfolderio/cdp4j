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

public enum MixedContentResourceType {
    @SerializedName("Audio")
    Audio("Audio"),

    @SerializedName("Beacon")
    Beacon("Beacon"),

    @SerializedName("CSPReport")
    CSPReport("CSPReport"),

    @SerializedName("Download")
    Download("Download"),

    @SerializedName("EventSource")
    EventSource("EventSource"),

    @SerializedName("Favicon")
    Favicon("Favicon"),

    @SerializedName("Font")
    Font("Font"),

    @SerializedName("Form")
    Form("Form"),

    @SerializedName("Frame")
    Frame("Frame"),

    @SerializedName("Image")
    Image("Image"),

    @SerializedName("Import")
    Import("Import"),

    @SerializedName("Manifest")
    Manifest("Manifest"),

    @SerializedName("Ping")
    Ping("Ping"),

    @SerializedName("PluginData")
    PluginData("PluginData"),

    @SerializedName("PluginResource")
    PluginResource("PluginResource"),

    @SerializedName("Prefetch")
    Prefetch("Prefetch"),

    @SerializedName("Resource")
    Resource("Resource"),

    @SerializedName("Script")
    Script("Script"),

    @SerializedName("ServiceWorker")
    ServiceWorker("ServiceWorker"),

    @SerializedName("SharedWorker")
    SharedWorker("SharedWorker"),

    @SerializedName("Stylesheet")
    Stylesheet("Stylesheet"),

    @SerializedName("Track")
    Track("Track"),

    @SerializedName("Video")
    Video("Video"),

    @SerializedName("Worker")
    Worker("Worker"),

    @SerializedName("XMLHttpRequest")
    XMLHttpRequest("XMLHttpRequest"),

    @SerializedName("XSLT")
    XSLT("XSLT");

    public final String value;

    MixedContentResourceType(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
