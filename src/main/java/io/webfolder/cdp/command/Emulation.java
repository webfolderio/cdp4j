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
package io.webfolder.cdp.command;

import io.webfolder.cdp.annotation.Domain;
import io.webfolder.cdp.annotation.Experimental;
import io.webfolder.cdp.annotation.Optional;
import io.webfolder.cdp.annotation.Returns;
import io.webfolder.cdp.type.constant.Platform;
import io.webfolder.cdp.type.dom.RGBA;
import io.webfolder.cdp.type.emulation.MediaFeature;
import io.webfolder.cdp.type.emulation.ScreenOrientation;
import io.webfolder.cdp.type.emulation.VirtualTimePolicy;
import io.webfolder.cdp.type.page.Viewport;
import java.util.List;

/**
 * This domain emulates different environments for the page
 */
@Domain("Emulation")
public interface Emulation {
    /**
     * Tells whether emulation is supported.
     * 
     * @return True if emulation is supported.
     */
    @Returns("result")
    Boolean canEmulate();

    /**
     * Clears the overriden device metrics.
     */
    void clearDeviceMetricsOverride();

    /**
     * Clears the overriden Geolocation Position and Error.
     */
    void clearGeolocationOverride();

    /**
     * Requests that page scale factor is reset to initial values.
     */
    @Experimental
    void resetPageScaleFactor();

    /**
     * Enables or disables simulating a focused and active page.
     * 
     * @param enabled Whether to enable to disable focus emulation.
     */
    @Experimental
    void setFocusEmulationEnabled(Boolean enabled);

    /**
     * Enables CPU throttling to emulate slow CPUs.
     * 
     * @param rate Throttling rate as a slowdown factor (1 is no throttle, 2 is 2x slowdown, etc).
     */
    @Experimental
    void setCPUThrottlingRate(Double rate);

    /**
     * Sets or clears an override of the default background color of the frame. This override is used
     * if the content does not specify one.
     * 
     * @param color RGBA of the default background color. If not specified, any existing override will be
     * cleared.
     */
    void setDefaultBackgroundColorOverride(@Optional RGBA color);

    /**
     * Overrides the values of device screen dimensions (window.screen.width, window.screen.height,
     * window.innerWidth, window.innerHeight, and "device-width"/"device-height"-related CSS media
     * query results).
     * 
     * @param width Overriding width value in pixels (minimum 0, maximum 10000000). 0 disables the override.
     * @param height Overriding height value in pixels (minimum 0, maximum 10000000). 0 disables the override.
     * @param deviceScaleFactor Overriding device scale factor value. 0 disables the override.
     * @param mobile Whether to emulate mobile device. This includes viewport meta tag, overlay scrollbars, text
     * autosizing and more.
     * @param scale Scale to apply to resulting view image.
     * @param screenWidth Overriding screen width value in pixels (minimum 0, maximum 10000000).
     * @param screenHeight Overriding screen height value in pixels (minimum 0, maximum 10000000).
     * @param positionX Overriding view X position on screen in pixels (minimum 0, maximum 10000000).
     * @param positionY Overriding view Y position on screen in pixels (minimum 0, maximum 10000000).
     * @param dontSetVisibleSize Do not set visible view size, rely upon explicit setVisibleSize call.
     * @param screenOrientation Screen orientation override.
     * @param viewport If set, the visible area of the page will be overridden to this viewport. This viewport
     * change is not observed by the page, e.g. viewport-relative elements do not change positions.
     */
    void setDeviceMetricsOverride(Integer width, Integer height, Double deviceScaleFactor,
            Boolean mobile, @Experimental @Optional Double scale,
            @Experimental @Optional Integer screenWidth,
            @Experimental @Optional Integer screenHeight, @Experimental @Optional Integer positionX,
            @Experimental @Optional Integer positionY,
            @Experimental @Optional Boolean dontSetVisibleSize,
            @Optional ScreenOrientation screenOrientation,
            @Experimental @Optional Viewport viewport);

    @Experimental
    void setScrollbarsHidden(Boolean hidden);

    @Experimental
    void setDocumentCookieDisabled(Boolean disabled);

    @Experimental
    void setEmitTouchEventsForMouse(Boolean enabled, @Optional Platform configuration);

    /**
     * Emulates the given media type or media feature for CSS media queries.
     * 
     * @param media Media type to emulate. Empty string disables the override.
     * @param features Media features to emulate.
     */
    void setEmulatedMedia(@Optional String media, @Optional List<MediaFeature> features);

    /**
     * Overrides the Geolocation Position or Error. Omitting any of the parameters emulates position
     * unavailable.
     * 
     * @param latitude Mock latitude
     * @param longitude Mock longitude
     * @param accuracy Mock accuracy
     */
    void setGeolocationOverride(@Optional Double latitude, @Optional Double longitude,
            @Optional Double accuracy);

    /**
     * Overrides value returned by the javascript navigator object.
     * 
     * @param platform The platform navigator.platform should return.
     */
    @Experimental
    void setNavigatorOverrides(String platform);

    /**
     * Sets a specified page scale factor.
     * 
     * @param pageScaleFactor Page scale factor.
     */
    @Experimental
    void setPageScaleFactor(Double pageScaleFactor);

    /**
     * Switches script execution in the page.
     * 
     * @param value Whether script execution should be disabled in the page.
     */
    void setScriptExecutionDisabled(Boolean value);

    /**
     * Enables touch on platforms which do not support them.
     * 
     * @param enabled Whether the touch event emulation should be enabled.
     * @param maxTouchPoints Maximum touch points supported. Defaults to one.
     */
    void setTouchEmulationEnabled(Boolean enabled, @Optional Integer maxTouchPoints);

    /**
     * Turns on virtual time for all frames (replacing real-time with a synthetic time source) and sets
     * the current virtual time policy.  Note this supersedes any previous time budget.
     * 
     * @param budget If set, after this many virtual milliseconds have elapsed virtual time will be paused and a
     * virtualTimeBudgetExpired event is sent.
     * @param maxVirtualTimeTaskStarvationCount If set this specifies the maximum number of tasks that can be run before virtual is forced
     * forwards to prevent deadlock.
     * @param waitForNavigation If set the virtual time policy change should be deferred until any frame starts navigating.
     * Note any previous deferred policy change is superseded.
     * @param initialVirtualTime If set, base::Time::Now will be overriden to initially return this value.
     * 
     * @return Absolute timestamp at which virtual time was first enabled (up time in milliseconds).
     */
    @Experimental
    @Returns("virtualTimeTicksBase")
    Double setVirtualTimePolicy(VirtualTimePolicy policy, @Optional Double budget,
            @Optional Integer maxVirtualTimeTaskStarvationCount,
            @Optional Boolean waitForNavigation, @Optional Double initialVirtualTime);

    /**
     * Overrides default host system timezone with the specified one.
     * 
     * @param timezoneId The timezone identifier. If empty, disables the override and
     * restores default host system timezone.
     */
    @Experimental
    void setTimezoneOverride(String timezoneId);

    /**
     * Resizes the frame/viewport of the page. Note that this does not affect the frame's container
     * (e.g. browser window). Can be used to produce screenshots of the specified size. Not supported
     * on Android.
     * 
     * @param width Frame width (DIP).
     * @param height Frame height (DIP).
     */
    @Experimental
    void setVisibleSize(Integer width, Integer height);

    /**
     * Allows overriding user agent with the given string.
     * 
     * @param userAgent User agent to use.
     * @param acceptLanguage Browser langugage to emulate.
     * @param platform The platform navigator.platform should return.
     */
    void setUserAgentOverride(String userAgent, @Optional String acceptLanguage,
            @Optional String platform);

    /**
     * Sets or clears an override of the default background color of the frame. This override is used
     * if the content does not specify one.
     */
    void setDefaultBackgroundColorOverride();

    /**
     * Overrides the values of device screen dimensions (window.screen.width, window.screen.height,
     * window.innerWidth, window.innerHeight, and "device-width"/"device-height"-related CSS media
     * query results).
     * 
     * @param width Overriding width value in pixels (minimum 0, maximum 10000000). 0 disables the override.
     * @param height Overriding height value in pixels (minimum 0, maximum 10000000). 0 disables the override.
     * @param deviceScaleFactor Overriding device scale factor value. 0 disables the override.
     * @param mobile Whether to emulate mobile device. This includes viewport meta tag, overlay scrollbars, text
     * autosizing and more.
     */
    void setDeviceMetricsOverride(Integer width, Integer height, Double deviceScaleFactor,
            Boolean mobile);

    @Experimental
    void setEmitTouchEventsForMouse(Boolean enabled);

    /**
     * Emulates the given media type or media feature for CSS media queries.
     */
    void setEmulatedMedia();

    /**
     * Overrides the Geolocation Position or Error. Omitting any of the parameters emulates position
     * unavailable.
     */
    void setGeolocationOverride();

    /**
     * Enables touch on platforms which do not support them.
     * 
     * @param enabled Whether the touch event emulation should be enabled.
     */
    void setTouchEmulationEnabled(Boolean enabled);

    /**
     * Turns on virtual time for all frames (replacing real-time with a synthetic time source) and sets
     * the current virtual time policy.  Note this supersedes any previous time budget.
     * 
     * 
     * @return Absolute timestamp at which virtual time was first enabled (up time in milliseconds).
     */
    @Experimental
    @Returns("virtualTimeTicksBase")
    Double setVirtualTimePolicy(VirtualTimePolicy policy);

    /**
     * Allows overriding user agent with the given string.
     * 
     * @param userAgent User agent to use.
     */
    void setUserAgentOverride(String userAgent);
}
