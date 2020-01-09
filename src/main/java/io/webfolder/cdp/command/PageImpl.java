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

import java.util.List;

import com.google.gson.reflect.TypeToken;

import io.webfolder.cdp.session.SessionInvocationHandler;
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

public class PageImpl implements Page {

    private static final Object[] EMPTY_VALUES = new Object[]{};
    private static final String[] EMPTY_ARGS = new String[]{};
    private final SessionInvocationHandler handler;
    private static final TypeToken<List<String>> TT_LIST_STRING = new TypeToken<List<String>>() { };
    private static final TypeToken<List<Cookie>> TT_LIST_COOKIE = new TypeToken<List<Cookie>>() { };
    private static final TypeToken<List<SearchMatch>> TT_LIST_SEARCH_MATCH = new TypeToken<List<SearchMatch>>() { };

    public PageImpl(SessionInvocationHandler handler) {
        this.handler = handler;
    }

    @Override
    public String addScriptToEvaluateOnLoad(String scriptSource) {
        return (String) handler.invoke("Page", "addScriptToEvaluateOnLoad", "Page.addScriptToEvaluateOnLoad",
                "identifier", String.class, null, false, false, false, new String[]{"scriptSource"},
                new Object[]{scriptSource});
    }

    @Override
    public String addScriptToEvaluateOnNewDocument(String source, String worldName) {
        return (String) handler.invoke("Page", "addScriptToEvaluateOnNewDocument",
                "Page.addScriptToEvaluateOnNewDocument", "identifier", String.class, null, false, false, false,
                new String[]{"source", "worldName"}, new Object[]{source, worldName});
    }

    @Override
    public void bringToFront() {
        handler.invoke("Page", "bringToFront", "Page.bringToFront", null, void.class, null, true, false, false,
                EMPTY_ARGS, EMPTY_VALUES);
    }

    @Override
    public byte[] captureScreenshot(ImageFormat format, Integer quality, Viewport clip, Boolean fromSurface) {
        return (byte[]) handler.invoke("Page", "captureScreenshot", "Page.captureScreenshot", "data", byte[].class, null,
                false, false, false, new String[]{"format", "quality", "clip", "fromSurface"},
                new Object[]{format, quality, clip, fromSurface});
    }

    @Override
    public byte[] captureSnapshot(SnapshotType format) {
        return (byte[]) handler.invoke("Page", "captureSnapshot", "Page.captureSnapshot", "data", byte[].class, null,
                false, false, false, new String[]{"format"}, new Object[]{format});
    }

    @Override
    public void clearDeviceMetricsOverride() {
        handler.invoke("Page", "clearDeviceMetricsOverride", "Page.clearDeviceMetricsOverride", null, void.class, null,
                true, false, false, EMPTY_ARGS, EMPTY_VALUES);
    }

    @Override
    public void clearDeviceOrientationOverride() {
        handler.invoke("Page", "clearDeviceOrientationOverride", "Page.clearDeviceOrientationOverride", null,
                void.class, null, true, false, false, EMPTY_ARGS, EMPTY_VALUES);
    }

    @Override
    public void clearGeolocationOverride() {
        handler.invoke("Page", "clearGeolocationOverride", "Page.clearGeolocationOverride", null, void.class, null,
                true, false, false, EMPTY_ARGS, EMPTY_VALUES);
    }

    @Override
    public Integer createIsolatedWorld(String frameId, String worldName, Boolean grantUniveralAccess) {
        return (Integer) handler.invoke("Page", "createIsolatedWorld", "Page.createIsolatedWorld", "executionContextId",
                Integer.class, null, false, false, false, new String[]{"frameId", "worldName", "grantUniveralAccess"},
                new Object[]{frameId, worldName, grantUniveralAccess});
    }

    @Override
    public void deleteCookie(String cookieName, String url) {
        handler.invoke("Page", "deleteCookie", "Page.deleteCookie", null, void.class, null, true, false, false,
                new String[]{"cookieName", "url"}, new Object[]{cookieName, url});
    }

    @Override
    public void disable() {
        handler.invoke("Page", "disable", "Page.disable", null, void.class, null, true, false, true, EMPTY_ARGS,
                EMPTY_VALUES);
    }

    @Override
    public void enable() {
        handler.invoke("Page", "enable", "Page.enable", null, void.class, null, true, true, false, EMPTY_ARGS,
                EMPTY_VALUES);
    }

    @Override
    public GetAppManifestResult getAppManifest() {
        return (GetAppManifestResult) handler.invoke("Page", "getAppManifest", "Page.getAppManifest", null,
                GetAppManifestResult.class, null, false, false, false, EMPTY_ARGS, EMPTY_VALUES);
    }

    @Override
    @java.lang.SuppressWarnings("unchecked")
    public List<String> getInstallabilityErrors() {
        return (List<String>) handler.invoke("Page", "getInstallabilityErrors", "Page.getInstallabilityErrors",
                "errors", List.class, TT_LIST_STRING.getType(), false, false, false, EMPTY_ARGS,
                EMPTY_VALUES);
    }

    @Override
    public String getManifestIcons() {
        return (String) handler.invoke("Page", "getManifestIcons", "Page.getManifestIcons", "primaryIcon", String.class,
                null, false, false, false, EMPTY_ARGS, EMPTY_VALUES);
    }

    @Override
    @java.lang.SuppressWarnings("unchecked")
    public List<Cookie> getCookies() {
        return (List<Cookie>) handler.invoke("Page", "getCookies", "Page.getCookies", "cookies", List.class,
                TT_LIST_COOKIE.getType(), false, false, false, EMPTY_ARGS, EMPTY_VALUES);
    }

    @Override
    public FrameTree getFrameTree() {
        return (FrameTree) handler.invoke("Page", "getFrameTree", "Page.getFrameTree", "frameTree", FrameTree.class,
                null, false, false, false, EMPTY_ARGS, EMPTY_VALUES);
    }

    @Override
    public GetLayoutMetricsResult getLayoutMetrics() {
        return (GetLayoutMetricsResult) handler.invoke("Page", "getLayoutMetrics", "Page.getLayoutMetrics", null,
                GetLayoutMetricsResult.class, null, false, false, false, EMPTY_ARGS, EMPTY_VALUES);
    }

    @Override
    public GetNavigationHistoryResult getNavigationHistory() {
        return (GetNavigationHistoryResult) handler.invoke("Page", "getNavigationHistory", "Page.getNavigationHistory",
                null, GetNavigationHistoryResult.class, null, false, false, false, EMPTY_ARGS, EMPTY_VALUES);
    }

    @Override
    public void resetNavigationHistory() {
        handler.invoke("Page", "resetNavigationHistory", "Page.resetNavigationHistory", null, void.class, null, true,
                false, false, EMPTY_ARGS, EMPTY_VALUES);
    }

    @Override
    public GetResourceContentResult getResourceContent(String frameId, String url) {
        return (GetResourceContentResult) handler.invoke("Page", "getResourceContent", "Page.getResourceContent", null,
                GetResourceContentResult.class, null, false, false, false, new String[]{"frameId", "url"},
                new Object[]{frameId, url});
    }

    @Override
    public FrameResourceTree getResourceTree() {
        return (FrameResourceTree) handler.invoke("Page", "getResourceTree", "Page.getResourceTree", "frameTree",
                FrameResourceTree.class, null, false, false, false, EMPTY_ARGS, EMPTY_VALUES);
    }

    @Override
    public void handleJavaScriptDialog(Boolean accept, String promptText) {
        handler.invoke("Page", "handleJavaScriptDialog", "Page.handleJavaScriptDialog", null, void.class, null, true,
                false, false, new String[]{"accept", "promptText"}, new Object[]{accept, promptText});
    }

    @Override
    public NavigateResult navigate(String url, String referrer, TransitionType transitionType, String frameId) {
        return (NavigateResult) handler.invoke("Page", "navigate", "Page.navigate", null, NavigateResult.class, null,
                false, false, false, new String[]{"url", "referrer", "transitionType", "frameId"},
                new Object[]{url, referrer, transitionType, frameId});
    }

    @Override
    public void navigateToHistoryEntry(Integer entryId) {
        handler.invoke("Page", "navigateToHistoryEntry", "Page.navigateToHistoryEntry", null, void.class, null, true,
                false, false, new String[]{"entryId"}, new Object[]{entryId});
    }

    @Override
    public PrintToPDFResult printToPDF(Boolean landscape, Boolean displayHeaderFooter, Boolean printBackground,
            Double scale, Double paperWidth, Double paperHeight, Double marginTop, Double marginBottom,
            Double marginLeft, Double marginRight, String pageRanges, Boolean ignoreInvalidPageRanges,
            String headerTemplate, String footerTemplate, Boolean preferCSSPageSize, PdfTransferMode transferMode) {
        return (PrintToPDFResult) handler.invoke("Page", "printToPDF", "Page.printToPDF", null, PrintToPDFResult.class,
                null, false, false, false,
                new String[]{"landscape", "displayHeaderFooter", "printBackground", "scale", "paperWidth",
                        "paperHeight", "marginTop", "marginBottom", "marginLeft", "marginRight", "pageRanges",
                        "ignoreInvalidPageRanges", "headerTemplate", "footerTemplate", "preferCSSPageSize",
                        "transferMode"},
                new Object[]{landscape, displayHeaderFooter, printBackground, scale, paperWidth, paperHeight, marginTop,
                        marginBottom, marginLeft, marginRight, pageRanges, ignoreInvalidPageRanges, headerTemplate,
                        footerTemplate, preferCSSPageSize, transferMode});
    }

    @Override
    public void reload(Boolean ignoreCache, String scriptToEvaluateOnLoad) {
        handler.invoke("Page", "reload", "Page.reload", null, void.class, null, true, false, false,
                new String[]{"ignoreCache", "scriptToEvaluateOnLoad"},
                new Object[]{ignoreCache, scriptToEvaluateOnLoad});
    }

    @Override
    public void removeScriptToEvaluateOnLoad(String identifier) {
        handler.invoke("Page", "removeScriptToEvaluateOnLoad", "Page.removeScriptToEvaluateOnLoad", null, void.class,
                null, true, false, false, new String[]{"identifier"}, new Object[]{identifier});
    }

    @Override
    public void removeScriptToEvaluateOnNewDocument(String identifier) {
        handler.invoke("Page", "removeScriptToEvaluateOnNewDocument", "Page.removeScriptToEvaluateOnNewDocument", null,
                void.class, null, true, false, false, new String[]{"identifier"}, new Object[]{identifier});
    }

    @Override
    public void screencastFrameAck(Integer sessionId) {
        handler.invoke("Page", "screencastFrameAck", "Page.screencastFrameAck", null, void.class, null, true, false,
                false, new String[]{"sessionId"}, new Object[]{sessionId});
    }

    @Override
    @java.lang.SuppressWarnings("unchecked")
    public List<SearchMatch> searchInResource(String frameId, String url, String query, Boolean caseSensitive,
            Boolean isRegex) {
        return (List<SearchMatch>) handler.invoke("Page", "searchInResource", "Page.searchInResource", "result",
                List.class, TT_LIST_SEARCH_MATCH.getType(), false, false, false,
                new String[]{"frameId", "url", "query", "caseSensitive", "isRegex"},
                new Object[]{frameId, url, query, caseSensitive, isRegex});
    }

    @Override
    public void setAdBlockingEnabled(Boolean enabled) {
        handler.invoke("Page", "setAdBlockingEnabled", "Page.setAdBlockingEnabled", null, void.class, null, true, false,
                false, new String[]{"enabled"}, new Object[]{enabled});
    }

    @Override
    public void setBypassCSP(Boolean enabled) {
        handler.invoke("Page", "setBypassCSP", "Page.setBypassCSP", null, void.class, null, true, false, false,
                new String[]{"enabled"}, new Object[]{enabled});
    }

    @Override
    public void setDeviceMetricsOverride(Integer width, Integer height, Double deviceScaleFactor, Boolean mobile,
            Double scale, Integer screenWidth, Integer screenHeight, Integer positionX, Integer positionY,
            Boolean dontSetVisibleSize, ScreenOrientation screenOrientation, Viewport viewport) {
        handler.invoke("Page", "setDeviceMetricsOverride", "Page.setDeviceMetricsOverride", null, void.class, null,
                true, false, false,
                new String[]{"width", "height", "deviceScaleFactor", "mobile", "scale", "screenWidth", "screenHeight",
                        "positionX", "positionY", "dontSetVisibleSize", "screenOrientation", "viewport"},
                new Object[]{width, height, deviceScaleFactor, mobile, scale, screenWidth, screenHeight, positionX,
                        positionY, dontSetVisibleSize, screenOrientation, viewport});
    }

    @Override
    public void setDeviceOrientationOverride(Double alpha, Double beta, Double gamma) {
        handler.invoke("Page", "setDeviceOrientationOverride", "Page.setDeviceOrientationOverride", null, void.class,
                null, true, false, false, new String[]{"alpha", "beta", "gamma"}, new Object[]{alpha, beta, gamma});
    }

    @Override
    public void setFontFamilies(FontFamilies fontFamilies) {
        handler.invoke("Page", "setFontFamilies", "Page.setFontFamilies", null, void.class, null, true, false, false,
                new String[]{"fontFamilies"}, new Object[]{fontFamilies});
    }

    @Override
    public void setFontSizes(FontSizes fontSizes) {
        handler.invoke("Page", "setFontSizes", "Page.setFontSizes", null, void.class, null, true, false, false,
                new String[]{"fontSizes"}, new Object[]{fontSizes});
    }

    @Override
    public void setDocumentContent(String frameId, String html) {
        handler.invoke("Page", "setDocumentContent", "Page.setDocumentContent", null, void.class, null, true, false,
                false, new String[]{"frameId", "html"}, new Object[]{frameId, html});
    }

    @Override
    public void setDownloadBehavior(DownloadBehavior behavior, String downloadPath) {
        handler.invoke("Page", "setDownloadBehavior", "Page.setDownloadBehavior", null, void.class, null, true, false,
                false, new String[]{"behavior", "downloadPath"}, new Object[]{behavior, downloadPath});
    }

    @Override
    public void setGeolocationOverride(Double latitude, Double longitude, Double accuracy) {
        handler.invoke("Page", "setGeolocationOverride", "Page.setGeolocationOverride", null, void.class, null, true,
                false, false, new String[]{"latitude", "longitude", "accuracy"},
                new Object[]{latitude, longitude, accuracy});
    }

    @Override
    public void setLifecycleEventsEnabled(Boolean enabled) {
        handler.invoke("Page", "setLifecycleEventsEnabled", "Page.setLifecycleEventsEnabled", null, void.class, null,
                true, false, false, new String[]{"enabled"}, new Object[]{enabled});
    }

    @Override
    public void setTouchEmulationEnabled(Boolean enabled, Platform configuration) {
        handler.invoke("Page", "setTouchEmulationEnabled", "Page.setTouchEmulationEnabled", null, void.class, null,
                true, false, false, new String[]{"enabled", "configuration"}, new Object[]{enabled, configuration});
    }

    @Override
    public void startScreencast(ImageFormat format, Integer quality, Integer maxWidth, Integer maxHeight,
            Integer everyNthFrame) {
        handler.invoke("Page", "startScreencast", "Page.startScreencast", null, void.class, null, true, false, false,
                new String[]{"format", "quality", "maxWidth", "maxHeight", "everyNthFrame"},
                new Object[]{format, quality, maxWidth, maxHeight, everyNthFrame});
    }

    @Override
    public void stopLoading() {
        handler.invoke("Page", "stopLoading", "Page.stopLoading", null, void.class, null, true, false, false,
                EMPTY_ARGS, EMPTY_VALUES);
    }

    @Override
    public void crash() {
        handler.invoke("Page", "crash", "Page.crash", null, void.class, null, true, false, false, EMPTY_ARGS,
                EMPTY_VALUES);
    }

    @Override
    public void close() {
        handler.invoke("Page", "close", "Page.close", null, void.class, null, true, false, false, EMPTY_ARGS,
                EMPTY_VALUES);
    }

    @Override
    public void setWebLifecycleState(TargetLifecycleState state) {
        handler.invoke("Page", "setWebLifecycleState", "Page.setWebLifecycleState", null, void.class, null, true, false,
                false, new String[]{"state"}, new Object[]{state});
    }

    @Override
    public void stopScreencast() {
        handler.invoke("Page", "stopScreencast", "Page.stopScreencast", null, void.class, null, true, false, false,
                EMPTY_ARGS, EMPTY_VALUES);
    }

    @Override
    public void setProduceCompilationCache(Boolean enabled) {
        handler.invoke("Page", "setProduceCompilationCache", "Page.setProduceCompilationCache", null, void.class, null,
                true, false, false, new String[]{"enabled"}, new Object[]{enabled});
    }

    @Override
    public void addCompilationCache(String url, String data) {
        handler.invoke("Page", "addCompilationCache", "Page.addCompilationCache", null, void.class, null, true, false,
                false, new String[]{"url", "data"}, new Object[]{url, data});
    }

    @Override
    public void clearCompilationCache() {
        handler.invoke("Page", "clearCompilationCache", "Page.clearCompilationCache", null, void.class, null, true,
                false, false, EMPTY_ARGS, EMPTY_VALUES);
    }

    @Override
    public void generateTestReport(String message, String group) {
        handler.invoke("Page", "generateTestReport", "Page.generateTestReport", null, void.class, null, true, false,
                false, new String[]{"message", "group"}, new Object[]{message, group});
    }

    @Override
    public void waitForDebugger() {
        handler.invoke("Page", "waitForDebugger", "Page.waitForDebugger", null, void.class, null, true, false, false,
                EMPTY_ARGS, EMPTY_VALUES);
    }

    @Override
    public void setInterceptFileChooserDialog(Boolean enabled) {
        handler.invoke("Page", "setInterceptFileChooserDialog", "Page.setInterceptFileChooserDialog", null, void.class,
                null, true, false, false, new String[]{"enabled"}, new Object[]{enabled});
    }

    @Override
    public String addScriptToEvaluateOnNewDocument(String source) {
        return (String) handler.invoke("Page", "addScriptToEvaluateOnNewDocument",
                "Page.addScriptToEvaluateOnNewDocument", "identifier", String.class, null, false, false, false,
                new String[]{"source"}, new Object[]{source});
    }

    @Override
    public byte[] captureScreenshot() {
        return (byte[]) handler.invoke("Page", "captureScreenshot", "Page.captureScreenshot", "data", byte[].class, null,
                false, false, false, EMPTY_ARGS, EMPTY_VALUES);
    }

    @Override
    public byte[] captureSnapshot() {
        return (byte[]) handler.invoke("Page", "captureSnapshot", "Page.captureSnapshot", "data", byte[].class, null,
                false, false, false, EMPTY_ARGS, EMPTY_VALUES);
    }

    @Override
    public Integer createIsolatedWorld(String frameId) {
        return (Integer) handler.invoke("Page", "createIsolatedWorld", "Page.createIsolatedWorld", "executionContextId",
                Integer.class, null, false, false, false, new String[]{"frameId"}, new Object[]{frameId});
    }

    @Override
    public void handleJavaScriptDialog(Boolean accept) {
        handler.invoke("Page", "handleJavaScriptDialog", "Page.handleJavaScriptDialog", null, void.class, null, true,
                false, false, new String[]{"accept"}, new Object[]{accept});
    }

    @Override
    public NavigateResult navigate(String url) {
        return (NavigateResult) handler.invoke("Page", "navigate", "Page.navigate", null, NavigateResult.class, null,
                false, false, false, new String[]{"url"}, new Object[]{url});
    }

    @Override
    public PrintToPDFResult printToPDF() {
        return (PrintToPDFResult) handler.invoke("Page", "printToPDF", "Page.printToPDF", null, PrintToPDFResult.class,
                null, false, false, false, EMPTY_ARGS, EMPTY_VALUES);
    }

    @Override
    public void reload() {
        handler.invoke("Page", "reload", "Page.reload", null, void.class, null, true, false, false, EMPTY_ARGS,
                EMPTY_VALUES);
    }

    @Override
    @java.lang.SuppressWarnings("unchecked")
    public List<SearchMatch> searchInResource(String frameId, String url, String query) {
        return (List<SearchMatch>) handler.invoke("Page", "searchInResource", "Page.searchInResource", "result",
                List.class, TT_LIST_SEARCH_MATCH.getType(), false, false, false, new String[]{"frameId", "url", "query"},
                new Object[]{frameId, url, query});
    }

    @Override
    public void setDeviceMetricsOverride(Integer width, Integer height, Double deviceScaleFactor, Boolean mobile) {
        handler.invoke("Page", "setDeviceMetricsOverride", "Page.setDeviceMetricsOverride", null, void.class, null,
                true, false, false, new String[]{"width", "height", "deviceScaleFactor", "mobile"},
                new Object[]{width, height, deviceScaleFactor, mobile});
    }

    @Override
    public void setDownloadBehavior(DownloadBehavior behavior) {
        handler.invoke("Page", "setDownloadBehavior", "Page.setDownloadBehavior", null, void.class, null, true, false,
                false, new String[]{"behavior"}, new Object[]{behavior});
    }

    @Override
    public void setGeolocationOverride() {
        handler.invoke("Page", "setGeolocationOverride", "Page.setGeolocationOverride", null, void.class, null, true,
                false, false, EMPTY_ARGS, EMPTY_VALUES);
    }

    @Override
    public void setTouchEmulationEnabled(Boolean enabled) {
        handler.invoke("Page", "setTouchEmulationEnabled", "Page.setTouchEmulationEnabled", null, void.class, null,
                true, false, false, new String[]{"enabled"}, new Object[]{enabled});
    }

    @Override
    public void startScreencast() {
        handler.invoke("Page", "startScreencast", "Page.startScreencast", null, void.class, null, true, false, false,
                EMPTY_ARGS, EMPTY_VALUES);
    }

    @Override
    public void generateTestReport(String message) {
        handler.invoke("Page", "generateTestReport", "Page.generateTestReport", null, void.class, null, true, false,
                false, new String[]{"message"}, new Object[]{message});
    }
}