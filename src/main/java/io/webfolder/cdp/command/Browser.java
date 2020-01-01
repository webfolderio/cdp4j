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
import io.webfolder.cdp.type.browser.Bounds;
import io.webfolder.cdp.type.browser.GetVersionResult;
import io.webfolder.cdp.type.browser.GetWindowForTargetResult;
import io.webfolder.cdp.type.browser.Histogram;
import io.webfolder.cdp.type.browser.PermissionDescriptor;
import io.webfolder.cdp.type.browser.PermissionSetting;
import io.webfolder.cdp.type.browser.PermissionType;
import java.util.List;

/**
 * The Browser domain defines methods and events for browser managing
 */
@Domain("Browser")
public interface Browser {
    /**
     * Set permission settings for given origin.
     * 
     * @param origin Origin the permission applies to.
     * @param permission Descriptor of permission to override.
     * @param setting Setting of the permission.
     * @param browserContextId Context to override. When omitted, default browser context is used.
     */
    @Experimental
    void setPermission(String origin, PermissionDescriptor permission, PermissionSetting setting,
            @Optional String browserContextId);

    /**
     * Grant specific permissions to the given origin and reject all others.
     * 
     * @param browserContextId BrowserContext to override permissions. When omitted, default browser context is used.
     */
    @Experimental
    void grantPermissions(String origin, PermissionType permissions,
            @Optional String browserContextId);

    /**
     * Reset all permission management for all origins.
     * 
     * @param browserContextId BrowserContext to reset permissions. When omitted, default browser context is used.
     */
    @Experimental
    void resetPermissions(@Optional String browserContextId);

    /**
     * Close browser gracefully.
     */
    void close();

    /**
     * Crashes browser on the main thread.
     */
    @Experimental
    void crash();

    /**
     * Crashes GPU process.
     */
    @Experimental
    void crashGpuProcess();

    /**
     * Returns version information.
     * 
     * @return GetVersionResult
     */
    GetVersionResult getVersion();

    /**
     * Returns the command line switches for the browser process if, and only if
     * --enable-automation is on the commandline.
     * 
     * @return Commandline parameters
     */
    @Experimental
    @Returns("arguments")
    List<String> getBrowserCommandLine();

    /**
     * Get Chrome histograms.
     * 
     * @param query Requested substring in name. Only histograms which have query as a
     * substring in their name are extracted. An empty or absent query returns
     * all histograms.
     * @param delta If true, retrieve delta since last call.
     * 
     * @return Histograms.
     */
    @Experimental
    @Returns("histograms")
    List<Histogram> getHistograms(@Optional String query, @Optional Boolean delta);

    /**
     * Get a Chrome histogram by name.
     * 
     * @param name Requested histogram name.
     * @param delta If true, retrieve delta since last call.
     * 
     * @return Histogram.
     */
    @Experimental
    @Returns("histogram")
    Histogram getHistogram(String name, @Optional Boolean delta);

    /**
     * Get position and size of the browser window.
     * 
     * @param windowId Browser window id.
     * 
     * @return Bounds information of the window. When window state is 'minimized', the restored window
     * position and size are returned.
     */
    @Experimental
    @Returns("bounds")
    Bounds getWindowBounds(Integer windowId);

    /**
     * Get the browser window that contains the devtools target.
     * 
     * @param targetId Devtools agent host id. If called as a part of the session, associated targetId is used.
     * 
     * @return GetWindowForTargetResult
     */
    @Experimental
    GetWindowForTargetResult getWindowForTarget(@Optional String targetId);

    /**
     * Set position and/or size of the browser window.
     * 
     * @param windowId Browser window id.
     * @param bounds New window bounds. The 'minimized', 'maximized' and 'fullscreen' states cannot be combined
     * with 'left', 'top', 'width' or 'height'. Leaves unspecified fields unchanged.
     */
    @Experimental
    void setWindowBounds(Integer windowId, Bounds bounds);

    /**
     * Set dock tile details, platform-specific.
     * 
     * @param image Png encoded image.
     */
    @Experimental
    void setDockTile(@Optional String badgeLabel, @Optional String image);

    /**
     * Set permission settings for given origin.
     * 
     * @param origin Origin the permission applies to.
     * @param permission Descriptor of permission to override.
     * @param setting Setting of the permission.
     */
    @Experimental
    void setPermission(String origin, PermissionDescriptor permission, PermissionSetting setting);

    /**
     * Grant specific permissions to the given origin and reject all others.
     * 
     */
    @Experimental
    void grantPermissions(String origin, PermissionType permissions);

    /**
     * Reset all permission management for all origins.
     */
    @Experimental
    void resetPermissions();

    /**
     * Get Chrome histograms.
     * 
     * @return Histograms.
     */
    @Experimental
    @Returns("histograms")
    List<Histogram> getHistograms();

    /**
     * Get a Chrome histogram by name.
     * 
     * @param name Requested histogram name.
     * 
     * @return Histogram.
     */
    @Experimental
    @Returns("histogram")
    Histogram getHistogram(String name);

    /**
     * Get the browser window that contains the devtools target.
     * 
     * @return GetWindowForTargetResult
     */
    @Experimental
    GetWindowForTargetResult getWindowForTarget();

    /**
     * Set dock tile details, platform-specific.
     */
    @Experimental
    void setDockTile();
}
