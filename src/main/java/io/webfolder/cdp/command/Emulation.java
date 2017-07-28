/**
 * The MIT License
 * Copyright © 2017 WebFolder OÜ
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package io.webfolder.cdp.command;

import io.webfolder.cdp.annotation.Domain;
import io.webfolder.cdp.annotation.Experimental;
import io.webfolder.cdp.annotation.Optional;
import io.webfolder.cdp.annotation.Returns;
import io.webfolder.cdp.type.constant.Platform;
import io.webfolder.cdp.type.dom.RGBA;
import io.webfolder.cdp.type.emulation.ScreenOrientation;
import io.webfolder.cdp.type.emulation.VirtualTimePolicy;

/**
 * This domain emulates different environments for the page
 */
@Domain("Emulation")
public interface Emulation {
    /**
     * Overrides the values of device screen dimensions (window.screen.width, window.screen.height, window.innerWidth, window.innerHeight, and "device-width"/"device-height"-related CSS media query results).
     * 
     * @param width Overriding width value in pixels (minimum 0, maximum 10000000). 0 disables the override.
     * @param height Overriding height value in pixels (minimum 0, maximum 10000000). 0 disables the override.
     * @param deviceScaleFactor Overriding device scale factor value. 0 disables the override.
     * @param mobile Whether to emulate mobile device. This includes viewport meta tag, overlay scrollbars, text autosizing and more.
     * @param scale Scale to apply to resulting view image. Ignored in |fitWindow| mode.
     * @param screenWidth Overriding screen width value in pixels (minimum 0, maximum 10000000). Only used for |mobile==true|.
     * @param screenHeight Overriding screen height value in pixels (minimum 0, maximum 10000000). Only used for |mobile==true|.
     * @param positionX Overriding view X position on screen in pixels (minimum 0, maximum 10000000). Only used for |mobile==true|.
     * @param positionY Overriding view Y position on screen in pixels (minimum 0, maximum 10000000). Only used for |mobile==true|.
     * @param dontSetVisibleSize Do not set visible view size, rely upon explicit setVisibleSize call.
     * @param screenOrientation Screen orientation override.
     */
    void setDeviceMetricsOverride(Integer width, Integer height, Double deviceScaleFactor,
            Boolean mobile, @Experimental @Optional Double scale,
            @Experimental @Optional Integer screenWidth,
            @Experimental @Optional Integer screenHeight, 
            @Experimental @Optional Integer positionX,
            @Experimental @Optional Integer positionY,
            @Experimental @Optional Boolean dontSetVisibleSize,
            @Optional ScreenOrientation screenOrientation);

    /**
     * Clears the overriden device metrics.
     */
    void clearDeviceMetricsOverride();

    /**
     * Requests that page scale factor is reset to initial values.
     */
    @Experimental
    void resetPageScaleFactor();

    /**
     * Sets a specified page scale factor.
     * 
     * @param pageScaleFactor Page scale factor.
     */
    @Experimental
    void setPageScaleFactor(Double pageScaleFactor);

    /**
     * Resizes the frame/viewport of the page. Note that this does not affect the frame's container (e.g. browser window). Can be used to produce screenshots of the specified size. Not supported on Android.
     * 
     * @param width Frame width (DIP).
     * @param height Frame height (DIP).
     */
    @Experimental
    void setVisibleSize(Integer width, Integer height);

    /**
     * Switches script execution in the page.
     * 
     * @param value Whether script execution should be disabled in the page.
     */
    @Experimental
    void setScriptExecutionDisabled(Boolean value);

    /**
     * Overrides the Geolocation Position or Error. Omitting any of the parameters emulates position unavailable.
     * 
     * @param latitude Mock latitude
     * @param longitude Mock longitude
     * @param accuracy Mock accuracy
     */
    @Experimental
    void setGeolocationOverride(@Optional Double latitude, @Optional Double longitude,
            @Optional Double accuracy);

    /**
     * Clears the overriden Geolocation Position and Error.
     */
    @Experimental
    void clearGeolocationOverride();

    /**
     * Toggles mouse event-based touch event emulation.
     * 
     * @param enabled Whether the touch event emulation should be enabled.
     * @param configuration Touch/gesture events configuration. Default: current platform.
     */
    void setTouchEmulationEnabled(Boolean enabled, @Optional Platform configuration);

    /**
     * Emulates the given media for CSS media queries.
     * 
     * @param media Media type to emulate. Empty string disables the override.
     */
    void setEmulatedMedia(String media);

    /**
     * Enables CPU throttling to emulate slow CPUs.
     * 
     * @param rate Throttling rate as a slowdown factor (1 is no throttle, 2 is 2x slowdown, etc).
     */
    @Experimental
    void setCPUThrottlingRate(Double rate);

    /**
     * Tells whether emulation is supported.
     * 
     * @return True if emulation is supported.
     */
    @Experimental
    @Returns("result")
    Boolean canEmulate();

    /**
     * Turns on virtual time for all frames (replacing real-time with a synthetic time source) and sets the current virtual time policy.  Note this supersedes any previous time budget.
     * 
     * @param budget If set, after this many virtual milliseconds have elapsed virtual time will be paused and a virtualTimeBudgetExpired event is sent.
     */
    @Experimental
    void setVirtualTimePolicy(VirtualTimePolicy policy, @Optional Integer budget);

    /**
     * Sets or clears an override of the default background color of the frame. This override is used if the content does not specify one.
     * 
     * @param color RGBA of the default background color. If not specified, any existing override will be cleared.
     */
    @Experimental
    void setDefaultBackgroundColorOverride(@Optional RGBA color);

    /**
	 * Overrides the values of device screen dimensions (window.screen.width,
	 * window.screen.height, window.innerWidth, window.innerHeight, and
	 * "device-width"/"device-height"-related CSS media query results).
	 * 
	 * @param width
	 *            Overriding width value in pixels (minimum 0, maximum
	 *            10000000). 0 disables the override.
	 * @param height
	 *            Overriding height value in pixels (minimum 0, maximum
	 *            10000000). 0 disables the override.
	 * @param deviceScaleFactor
	 *            Overriding device scale factor value. 0 disables the override.
	 * @param mobile
	 *            Whether to emulate mobile device. This includes viewport meta
	 *            tag, overlay scrollbars, text autosizing and more.
	 * @param fitWindow
	 */
    void setDeviceMetricsOverride(Integer width, Integer height, Double deviceScaleFactor,
			Boolean mobile, Boolean fitWindow);

    /**
     * Overrides the Geolocation Position or Error. Omitting any of the parameters emulates position unavailable.
     */
    @Experimental
    void setGeolocationOverride();

    /**
     * Toggles mouse event-based touch event emulation.
     * 
     * @param enabled Whether the touch event emulation should be enabled.
     */
    void setTouchEmulationEnabled(Boolean enabled);

    /**
     * Turns on virtual time for all frames (replacing real-time with a synthetic time source) and sets the current virtual time policy.  Note this supersedes any previous time budget.
     * 
     */
    @Experimental
    void setVirtualTimePolicy(VirtualTimePolicy policy);

    /**
     * Sets or clears an override of the default background color of the frame. This override is used if the content does not specify one.
     */
    @Experimental
    void setDefaultBackgroundColorOverride();
}
