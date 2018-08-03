/**
 * cdp4j - Chrome DevTools Protocol for Java
 * Copyright © 2017, 2018 WebFolder OÜ (support@webfolder.io)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
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
import java.util.List;

/**
 * The Browser domain defines methods and events for browser managing
 */
@Domain("Browser")
public interface Browser {
    /**
     * Close browser gracefully.
     */
    void close();

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
     * @param targetId Devtools agent host id.
     * 
     * @return GetWindowForTargetResult
     */
    @Experimental
    GetWindowForTargetResult getWindowForTarget(String targetId);

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
}
