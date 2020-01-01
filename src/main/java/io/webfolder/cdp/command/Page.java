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
import io.webfolder.cdp.type.constant.DownloadBehavior;
import io.webfolder.cdp.type.constant.ImageFormat;
import io.webfolder.cdp.type.constant.PdfTransferMode;
import io.webfolder.cdp.type.constant.Platform;
import io.webfolder.cdp.type.constant.SnapshotType;
import io.webfolder.cdp.type.constant.TargetLifecycleState;
import io.webfolder.cdp.type.debugger.SearchMatch;
import io.webfolder.cdp.type.emulation.ScreenOrientation;
import io.webfolder.cdp.type.network.Cookie;
import io.webfolder.cdp.type.page.FontFamilies;
import io.webfolder.cdp.type.page.FontSizes;
import io.webfolder.cdp.type.page.FrameResourceTree;
import io.webfolder.cdp.type.page.FrameTree;
import io.webfolder.cdp.type.page.GetAppManifestResult;
import io.webfolder.cdp.type.page.GetLayoutMetricsResult;
import io.webfolder.cdp.type.page.GetNavigationHistoryResult;
import io.webfolder.cdp.type.page.GetResourceContentResult;
import io.webfolder.cdp.type.page.NavigateResult;
import io.webfolder.cdp.type.page.PrintToPDFResult;
import io.webfolder.cdp.type.page.TransitionType;
import io.webfolder.cdp.type.page.Viewport;
import java.util.List;

/**
 * Actions and events related to the inspected page belong to the page domain
 */
@Domain("Page")
public interface Page {
    /**
     * Deprecated, please use addScriptToEvaluateOnNewDocument instead.
     * 
     * 
     * @return Identifier of the added script.
     */
    @Experimental
    @Returns("identifier")
    String addScriptToEvaluateOnLoad(String scriptSource);

    /**
     * Evaluates given script in every frame upon creation (before loading frame's scripts).
     * 
     * @param worldName If specified, creates an isolated world with the given name and evaluates given script in it.
     * This world name will be used as the ExecutionContextDescription::name when the corresponding
     * event is emitted.
     * 
     * @return Identifier of the added script.
     */
    @Returns("identifier")
    String addScriptToEvaluateOnNewDocument(String source,
            @Experimental @Optional String worldName);

    /**
     * Brings page to front (activates tab).
     */
    void bringToFront();

    /**
     * Capture page screenshot.
     * 
     * @param format Image compression format (defaults to png).
     * @param quality Compression quality from range [0..100] (jpeg only).
     * @param clip Capture the screenshot of a given region only.
     * @param fromSurface Capture the screenshot from the surface, rather than the view. Defaults to true.
     * 
     * @return Base64-encoded image data.
     */
    @Returns("data")
    byte[] captureScreenshot(@Optional ImageFormat format, @Optional Integer quality,
            @Optional Viewport clip, @Experimental @Optional Boolean fromSurface);

    /**
     * Returns a snapshot of the page as a string. For MHTML format, the serialization includes
     * iframes, shadow DOM, external resources, and element-inline styles.
     * 
     * @param format Format (defaults to mhtml).
     * 
     * @return Serialized page data.
     */
    @Experimental
    @Returns("data")
    byte[] captureSnapshot(@Optional SnapshotType format);

    /**
     * Clears the overriden device metrics.
     */
    @Experimental
    void clearDeviceMetricsOverride();

    /**
     * Clears the overridden Device Orientation.
     */
    @Experimental
    void clearDeviceOrientationOverride();

    /**
     * Clears the overriden Geolocation Position and Error.
     */
    void clearGeolocationOverride();

    /**
     * Creates an isolated world for the given frame.
     * 
     * @param frameId Id of the frame in which the isolated world should be created.
     * @param worldName An optional name which is reported in the Execution Context.
     * @param grantUniveralAccess Whether or not universal access should be granted to the isolated world. This is a powerful
     * option, use with caution.
     * 
     * @return Execution context of the isolated world.
     */
    @Returns("executionContextId")
    Integer createIsolatedWorld(String frameId, @Optional String worldName,
            @Optional Boolean grantUniveralAccess);

    /**
     * Deletes browser cookie with given name, domain and path.
     * 
     * @param cookieName Name of the cookie to remove.
     * @param url URL to match cooke domain and path.
     */
    @Experimental
    void deleteCookie(String cookieName, String url);

    /**
     * Disables page domain notifications.
     */
    void disable();

    /**
     * Enables page domain notifications.
     */
    void enable();

    /**
     * 
     * @return GetAppManifestResult
     */
    GetAppManifestResult getAppManifest();

    @Experimental
    @Returns("errors")
    List<String> getInstallabilityErrors();

    @Experimental
    @Returns("primaryIcon")
    String getManifestIcons();

    /**
     * Returns all browser cookies. Depending on the backend support, will return detailed cookie
     * information in the `cookies` field.
     * 
     * @return Array of cookie objects.
     */
    @Experimental
    @Returns("cookies")
    List<Cookie> getCookies();

    /**
     * Returns present frame tree structure.
     * 
     * @return Present frame tree structure.
     */
    @Returns("frameTree")
    FrameTree getFrameTree();

    /**
     * Returns metrics relating to the layouting of the page, such as viewport bounds/scale.
     * 
     * @return GetLayoutMetricsResult
     */
    GetLayoutMetricsResult getLayoutMetrics();

    /**
     * Returns navigation history for the current page.
     * 
     * @return GetNavigationHistoryResult
     */
    GetNavigationHistoryResult getNavigationHistory();

    /**
     * Resets navigation history for the current page.
     */
    void resetNavigationHistory();

    /**
     * Returns content of the given resource.
     * 
     * @param frameId Frame id to get resource for.
     * @param url URL of the resource to get content for.
     * 
     * @return GetResourceContentResult
     */
    @Experimental
    GetResourceContentResult getResourceContent(String frameId, String url);

    /**
     * Returns present frame / resource tree structure.
     * 
     * @return Present frame / resource tree structure.
     */
    @Experimental
    @Returns("frameTree")
    FrameResourceTree getResourceTree();

    /**
     * Accepts or dismisses a JavaScript initiated dialog (alert, confirm, prompt, or onbeforeunload).
     * 
     * @param accept Whether to accept or dismiss the dialog.
     * @param promptText The text to enter into the dialog prompt before accepting. Used only if this is a prompt
     * dialog.
     */
    void handleJavaScriptDialog(Boolean accept, @Optional String promptText);

    /**
     * Navigates current page to the given URL.
     * 
     * @param url URL to navigate the page to.
     * @param referrer Referrer URL.
     * @param transitionType Intended transition type.
     * @param frameId Frame id to navigate, if not specified navigates the top frame.
     * 
     * @return NavigateResult
     */
    NavigateResult navigate(String url, @Optional String referrer,
            @Optional TransitionType transitionType, @Optional String frameId);

    /**
     * Navigates current page to the given history entry.
     * 
     * @param entryId Unique id of the entry to navigate to.
     */
    void navigateToHistoryEntry(Integer entryId);

    /**
     * Print page as PDF.
     * 
     * @param landscape Paper orientation. Defaults to false.
     * @param displayHeaderFooter Display header and footer. Defaults to false.
     * @param printBackground Print background graphics. Defaults to false.
     * @param scale Scale of the webpage rendering. Defaults to 1.
     * @param paperWidth Paper width in inches. Defaults to 8.5 inches.
     * @param paperHeight Paper height in inches. Defaults to 11 inches.
     * @param marginTop Top margin in inches. Defaults to 1cm (~0.4 inches).
     * @param marginBottom Bottom margin in inches. Defaults to 1cm (~0.4 inches).
     * @param marginLeft Left margin in inches. Defaults to 1cm (~0.4 inches).
     * @param marginRight Right margin in inches. Defaults to 1cm (~0.4 inches).
     * @param pageRanges Paper ranges to print, e.g., '1-5, 8, 11-13'. Defaults to the empty string, which means
     * print all pages.
     * @param ignoreInvalidPageRanges Whether to silently ignore invalid but successfully parsed page ranges, such as '3-2'.
     * Defaults to false.
     * @param headerTemplate HTML template for the print header. Should be valid HTML markup with following
     * classes used to inject printing values into them:
     * - `date`: formatted print date
     * - `title`: document title
     * - `url`: document location
     * - `pageNumber`: current page number
     * - `totalPages`: total pages in the document
     *
     * For example, `<span class=title></span>` would generate span containing the title.
     * @param footerTemplate HTML template for the print footer. Should use the same format as the `headerTemplate`.
     * @param preferCSSPageSize Whether or not to prefer page size as defined by css. Defaults to false,
     * in which case the content will be scaled to fit the paper size.
     * @param transferMode return as stream
     * 
     * @return PrintToPDFResult
     */
    PrintToPDFResult printToPDF(@Optional Boolean landscape, @Optional Boolean displayHeaderFooter,
            @Optional Boolean printBackground, @Optional Double scale, @Optional Double paperWidth,
            @Optional Double paperHeight, @Optional Double marginTop, @Optional Double marginBottom,
            @Optional Double marginLeft, @Optional Double marginRight, @Optional String pageRanges,
            @Optional Boolean ignoreInvalidPageRanges, @Optional String headerTemplate,
            @Optional String footerTemplate, @Optional Boolean preferCSSPageSize,
            @Experimental @Optional PdfTransferMode transferMode);

    /**
     * Reloads given page optionally ignoring the cache.
     * 
     * @param ignoreCache If true, browser cache is ignored (as if the user pressed Shift+refresh).
     * @param scriptToEvaluateOnLoad If set, the script will be injected into all frames of the inspected page after reload.
     * Argument will be ignored if reloading dataURL origin.
     */
    void reload(@Optional Boolean ignoreCache, @Optional String scriptToEvaluateOnLoad);

    /**
     * Deprecated, please use removeScriptToEvaluateOnNewDocument instead.
     * 
     */
    @Experimental
    void removeScriptToEvaluateOnLoad(String identifier);

    /**
     * Removes given script from the list.
     * 
     */
    void removeScriptToEvaluateOnNewDocument(String identifier);

    /**
     * Acknowledges that a screencast frame has been received by the frontend.
     * 
     * @param sessionId Frame number.
     */
    @Experimental
    void screencastFrameAck(Integer sessionId);

    /**
     * Searches for given string in resource content.
     * 
     * @param frameId Frame id for resource to search in.
     * @param url URL of the resource to search in.
     * @param query String to search for.
     * @param caseSensitive If true, search is case sensitive.
     * @param isRegex If true, treats string parameter as regex.
     * 
     * @return List of search matches.
     */
    @Experimental
    @Returns("result")
    List<SearchMatch> searchInResource(String frameId, String url, String query,
            @Optional Boolean caseSensitive, @Optional Boolean isRegex);

    /**
     * Enable Chrome's experimental ad filter on all sites.
     * 
     * @param enabled Whether to block ads.
     */
    @Experimental
    void setAdBlockingEnabled(Boolean enabled);

    /**
     * Enable page Content Security Policy by-passing.
     * 
     * @param enabled Whether to bypass page CSP.
     */
    @Experimental
    void setBypassCSP(Boolean enabled);

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
     * @param viewport The viewport dimensions and scale. If not set, the override is cleared.
     */
    @Experimental
    void setDeviceMetricsOverride(Integer width, Integer height, Double deviceScaleFactor,
            Boolean mobile, @Optional Double scale, @Optional Integer screenWidth,
            @Optional Integer screenHeight, @Optional Integer positionX,
            @Optional Integer positionY, @Optional Boolean dontSetVisibleSize,
            @Optional ScreenOrientation screenOrientation, @Optional Viewport viewport);

    /**
     * Overrides the Device Orientation.
     * 
     * @param alpha Mock alpha
     * @param beta Mock beta
     * @param gamma Mock gamma
     */
    @Experimental
    void setDeviceOrientationOverride(Double alpha, Double beta, Double gamma);

    /**
     * Set generic font families.
     * 
     * @param fontFamilies Specifies font families to set. If a font family is not specified, it won't be changed.
     */
    @Experimental
    void setFontFamilies(FontFamilies fontFamilies);

    /**
     * Set default font sizes.
     * 
     * @param fontSizes Specifies font sizes to set. If a font size is not specified, it won't be changed.
     */
    @Experimental
    void setFontSizes(FontSizes fontSizes);

    /**
     * Sets given markup as the document's HTML.
     * 
     * @param frameId Frame id to set HTML for.
     * @param html HTML content to set.
     */
    void setDocumentContent(String frameId, String html);

    /**
     * Set the behavior when downloading a file.
     * 
     * @param behavior Whether to allow all or deny all download requests, or use default Chrome behavior if
     * available (otherwise deny).
     * @param downloadPath The default path to save downloaded files to. This is requred if behavior is set to 'allow'
     */
    @Experimental
    void setDownloadBehavior(DownloadBehavior behavior, @Optional String downloadPath);

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
     * Controls whether page will emit lifecycle events.
     * 
     * @param enabled If true, starts emitting lifecycle events.
     */
    @Experimental
    void setLifecycleEventsEnabled(Boolean enabled);

    /**
     * Toggles mouse event-based touch event emulation.
     * 
     * @param enabled Whether the touch event emulation should be enabled.
     * @param configuration Touch/gesture events configuration. Default: current platform.
     */
    @Experimental
    void setTouchEmulationEnabled(Boolean enabled, @Optional Platform configuration);

    /**
     * Starts sending each frame using the `screencastFrame` event.
     * 
     * @param format Image compression format.
     * @param quality Compression quality from range [0..100].
     * @param maxWidth Maximum screenshot width.
     * @param maxHeight Maximum screenshot height.
     * @param everyNthFrame Send every n-th frame.
     */
    @Experimental
    void startScreencast(@Optional ImageFormat format, @Optional Integer quality,
            @Optional Integer maxWidth, @Optional Integer maxHeight,
            @Optional Integer everyNthFrame);

    /**
     * Force the page stop all navigations and pending resource fetches.
     */
    void stopLoading();

    /**
     * Crashes renderer on the IO thread, generates minidumps.
     */
    @Experimental
    void crash();

    /**
     * Tries to close page, running its beforeunload hooks, if any.
     */
    @Experimental
    void close();

    /**
     * Tries to update the web lifecycle state of the page.
     * It will transition the page to the given state according to:
     * https://github.com/WICG/web-lifecycle/
     * 
     * @param state Target lifecycle state
     */
    @Experimental
    void setWebLifecycleState(TargetLifecycleState state);

    /**
     * Stops sending each frame in the `screencastFrame`.
     */
    @Experimental
    void stopScreencast();

    /**
     * Forces compilation cache to be generated for every subresource script.
     * 
     */
    @Experimental
    void setProduceCompilationCache(Boolean enabled);

    /**
     * Seeds compilation cache for given url. Compilation cache does not survive
     * cross-process navigation.
     * 
     * @param data Base64-encoded data
     */
    @Experimental
    void addCompilationCache(String url, String data);

    /**
     * Clears seeded compilation cache.
     */
    @Experimental
    void clearCompilationCache();

    /**
     * Generates a report for testing.
     * 
     * @param message Message to be displayed in the report.
     * @param group Specifies the endpoint group to deliver the report to.
     */
    @Experimental
    void generateTestReport(String message, @Optional String group);

    /**
     * Pauses page execution. Can be resumed using generic Runtime.runIfWaitingForDebugger.
     */
    @Experimental
    void waitForDebugger();

    /**
     * Intercept file chooser requests and transfer control to protocol clients.
     * When file chooser interception is enabled, native file chooser dialog is not shown.
     * Instead, a protocol event `Page.fileChooserOpened` is emitted.
     * 
     */
    @Experimental
    void setInterceptFileChooserDialog(Boolean enabled);

    /**
     * Evaluates given script in every frame upon creation (before loading frame's scripts).
     * 
     * 
     * @return Identifier of the added script.
     */
    @Returns("identifier")
    String addScriptToEvaluateOnNewDocument(String source);

    /**
     * Capture page screenshot.
     * 
     * @return Base64-encoded image data.
     */
    @Returns("data")
    byte[] captureScreenshot();

    /**
     * Returns a snapshot of the page as a string. For MHTML format, the serialization includes
     * iframes, shadow DOM, external resources, and element-inline styles.
     * 
     * @return Serialized page data.
     */
    @Experimental
    @Returns("data")
    byte[] captureSnapshot();

    /**
     * Creates an isolated world for the given frame.
     * 
     * @param frameId Id of the frame in which the isolated world should be created.
     * 
     * @return Execution context of the isolated world.
     */
    @Returns("executionContextId")
    Integer createIsolatedWorld(String frameId);

    /**
     * Accepts or dismisses a JavaScript initiated dialog (alert, confirm, prompt, or onbeforeunload).
     * 
     * @param accept Whether to accept or dismiss the dialog.
     */
    void handleJavaScriptDialog(Boolean accept);

    /**
     * Navigates current page to the given URL.
     * 
     * @param url URL to navigate the page to.
     * 
     * @return NavigateResult
     */
    NavigateResult navigate(String url);

    /**
     * Print page as PDF.
     * 
     * @return PrintToPDFResult
     */
    PrintToPDFResult printToPDF();

    /**
     * Reloads given page optionally ignoring the cache.
     */
    void reload();

    /**
     * Searches for given string in resource content.
     * 
     * @param frameId Frame id for resource to search in.
     * @param url URL of the resource to search in.
     * @param query String to search for.
     * 
     * @return List of search matches.
     */
    @Experimental
    @Returns("result")
    List<SearchMatch> searchInResource(String frameId, String url, String query);

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
    @Experimental
    void setDeviceMetricsOverride(Integer width, Integer height, Double deviceScaleFactor,
            Boolean mobile);

    /**
     * Set the behavior when downloading a file.
     * 
     * @param behavior Whether to allow all or deny all download requests, or use default Chrome behavior if
     * available (otherwise deny).
     */
    @Experimental
    void setDownloadBehavior(DownloadBehavior behavior);

    /**
     * Overrides the Geolocation Position or Error. Omitting any of the parameters emulates position
     * unavailable.
     */
    void setGeolocationOverride();

    /**
     * Toggles mouse event-based touch event emulation.
     * 
     * @param enabled Whether the touch event emulation should be enabled.
     */
    @Experimental
    void setTouchEmulationEnabled(Boolean enabled);

    /**
     * Starts sending each frame using the `screencastFrame` event.
     */
    @Experimental
    void startScreencast();

    /**
     * Generates a report for testing.
     * 
     * @param message Message to be displayed in the report.
     */
    @Experimental
    void generateTestReport(String message);
}
