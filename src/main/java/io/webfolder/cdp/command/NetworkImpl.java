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
import java.util.Map;

import com.google.gson.reflect.TypeToken;

import io.webfolder.cdp.session.SessionInvocationHandler;
import io.webfolder.cdp.type.debugger.SearchMatch;
import io.webfolder.cdp.type.network.AuthChallengeResponse;
import io.webfolder.cdp.type.network.ConnectionType;
import io.webfolder.cdp.type.network.Cookie;
import io.webfolder.cdp.type.network.CookieParam;
import io.webfolder.cdp.type.network.CookiePriority;
import io.webfolder.cdp.type.network.CookieSameSite;
import io.webfolder.cdp.type.network.ErrorReason;
import io.webfolder.cdp.type.network.GetResponseBodyForInterceptionResult;
import io.webfolder.cdp.type.network.GetResponseBodyResult;
import io.webfolder.cdp.type.network.RequestPattern;

public class NetworkImpl implements Network {

    private static final Object[] EMPTY_VALUES = new Object[]{};
    private static final String[] EMPTY_ARGS = new String[]{};
    private final SessionInvocationHandler handler;
    private static final TypeToken<List<Cookie>> TT_LIST_COOKIES = new TypeToken<List<Cookie>>() { };
    private static final TypeToken<List<String>> TT_LIST_STRING = new TypeToken<List<String>>() { };
    private static final TypeToken<List<SearchMatch>> TT_LIST_SEARCH_MATCH = new TypeToken<List<SearchMatch>>() { };

    public NetworkImpl(SessionInvocationHandler handler) {
        this.handler = handler;
    }

    @Override
    public Boolean canClearBrowserCache() {
        return (Boolean) handler.invoke("Network", "canClearBrowserCache", "Network.canClearBrowserCache", "result",
                Boolean.class, null, false, false, false, EMPTY_ARGS, EMPTY_VALUES);
    }

    @Override
    public Boolean canClearBrowserCookies() {
        return (Boolean) handler.invoke("Network", "canClearBrowserCookies", "Network.canClearBrowserCookies", "result",
                Boolean.class, null, false, false, false, EMPTY_ARGS, EMPTY_VALUES);
    }

    @Override
    public Boolean canEmulateNetworkConditions() {
        return (Boolean) handler.invoke("Network", "canEmulateNetworkConditions", "Network.canEmulateNetworkConditions",
                "result", Boolean.class, null, false, false, false, EMPTY_ARGS, EMPTY_VALUES);
    }

    @Override
    public void clearBrowserCache() {
        handler.invoke("Network", "clearBrowserCache", "Network.clearBrowserCache", null, void.class, null, true, false,
                false, EMPTY_ARGS, EMPTY_VALUES);
    }

    @Override
    public void clearBrowserCookies() {
        handler.invoke("Network", "clearBrowserCookies", "Network.clearBrowserCookies", null, void.class, null, true,
                false, false, EMPTY_ARGS, EMPTY_VALUES);
    }

    @Override
    public void continueInterceptedRequest(String interceptionId, ErrorReason errorReason, String rawResponse,
            String url, String method, String postData, Map<String, Object> headers,
            AuthChallengeResponse authChallengeResponse) {
        handler.invoke("Network", "continueInterceptedRequest", "Network.continueInterceptedRequest", null, void.class,
                null, true, false, false,
                new String[]{"interceptionId", "errorReason", "rawResponse", "url", "method", "postData", "headers",
                        "authChallengeResponse"},
                new Object[]{interceptionId, errorReason, rawResponse, url, method, postData, headers,
                        authChallengeResponse});
    }

    @Override
    public void deleteCookies(String name, String url, String domain, String path) {
        handler.invoke("Network", "deleteCookies", "Network.deleteCookies", null, void.class, null, true, false, false,
                new String[]{"name", "url", "domain", "path"}, new Object[]{name, url, domain, path});
    }

    @Override
    public void disable() {
        handler.invoke("Network", "disable", "Network.disable", null, void.class, null, true, false, true, EMPTY_ARGS,
                EMPTY_VALUES);
    }

    @Override
    public void emulateNetworkConditions(Boolean offline, Double latency, Double downloadThroughput,
            Double uploadThroughput, ConnectionType connectionType) {
        handler.invoke("Network", "emulateNetworkConditions", "Network.emulateNetworkConditions", null, void.class,
                null, true, false, false,
                new String[]{"offline", "latency", "downloadThroughput", "uploadThroughput", "connectionType"},
                new Object[]{offline, latency, downloadThroughput, uploadThroughput, connectionType});
    }

    @Override
    public void enable(Integer maxTotalBufferSize, Integer maxResourceBufferSize, Integer maxPostDataSize) {
        handler.invoke("Network", "enable", "Network.enable", null, void.class, null, true, true, false,
                new String[]{"maxTotalBufferSize", "maxResourceBufferSize", "maxPostDataSize"},
                new Object[]{maxTotalBufferSize, maxResourceBufferSize, maxPostDataSize});
    }

    @Override
    @java.lang.SuppressWarnings("unchecked")
    public List<Cookie> getAllCookies() {
        return (List<Cookie>) handler.invoke("Network", "getAllCookies", "Network.getAllCookies", "cookies", List.class,
                TT_LIST_COOKIES.getType(), false, false, false, EMPTY_ARGS, EMPTY_VALUES);
    }

    @Override
    @java.lang.SuppressWarnings("unchecked")
    public List<String> getCertificate(String origin) {
        return (List<String>) handler.invoke("Network", "getCertificate", "Network.getCertificate", "tableNames",
                List.class, TT_LIST_STRING.getType(), false, false, false, new String[]{"origin"},
                new Object[]{origin});
    }

    @Override
    @java.lang.SuppressWarnings("unchecked")
    public List<Cookie> getCookies(List<String> urls) {
        return (List<Cookie>) handler.invoke("Network", "getCookies", "Network.getCookies", "cookies", List.class,
                TT_LIST_COOKIES.getType(), false, false, false, new String[]{"urls"}, new Object[]{urls});
    }

    @Override
    public GetResponseBodyResult getResponseBody(String requestId) {
        return (GetResponseBodyResult) handler.invoke("Network", "getResponseBody", "Network.getResponseBody", null,
                GetResponseBodyResult.class, null, false, false, false, new String[]{"requestId"},
                new Object[]{requestId});
    }

    @Override
    public String getRequestPostData(String requestId) {
        return (String) handler.invoke("Network", "getRequestPostData", "Network.getRequestPostData", "postData",
                String.class, null, false, false, false, new String[]{"requestId"}, new Object[]{requestId});
    }

    @Override
    public GetResponseBodyForInterceptionResult getResponseBodyForInterception(String interceptionId) {
        return (GetResponseBodyForInterceptionResult) handler.invoke("Network", "getResponseBodyForInterception",
                "Network.getResponseBodyForInterception", null, GetResponseBodyForInterceptionResult.class, null, false,
                false, false, new String[]{"interceptionId"}, new Object[]{interceptionId});
    }

    @Override
    public String takeResponseBodyForInterceptionAsStream(String interceptionId) {
        return (String) handler.invoke("Network", "takeResponseBodyForInterceptionAsStream",
                "Network.takeResponseBodyForInterceptionAsStream", "stream", String.class, null, false, false, false,
                new String[]{"interceptionId"}, new Object[]{interceptionId});
    }

    @Override
    public void replayXHR(String requestId) {
        handler.invoke("Network", "replayXHR", "Network.replayXHR", null, void.class, null, true, false, false,
                new String[]{"requestId"}, new Object[]{requestId});
    }

    @Override
    @java.lang.SuppressWarnings("unchecked")
    public List<SearchMatch> searchInResponseBody(String requestId, String query, Boolean caseSensitive,
            Boolean isRegex) {
        return (List<SearchMatch>) handler.invoke("Network", "searchInResponseBody", "Network.searchInResponseBody",
                "result", List.class, TT_LIST_SEARCH_MATCH.getType(), false, false, false,
                new String[]{"requestId", "query", "caseSensitive", "isRegex"},
                new Object[]{requestId, query, caseSensitive, isRegex});
    }

    @Override
    public void setBlockedURLs(List<String> urls) {
        handler.invoke("Network", "setBlockedURLs", "Network.setBlockedURLs", null, void.class, null, true, false,
                false, new String[]{"urls"}, new Object[]{urls});
    }

    @Override
    public void setBypassServiceWorker(Boolean bypass) {
        handler.invoke("Network", "setBypassServiceWorker", "Network.setBypassServiceWorker", null, void.class, null,
                true, false, false, new String[]{"bypass"}, new Object[]{bypass});
    }

    @Override
    public void setCacheDisabled(Boolean cacheDisabled) {
        handler.invoke("Network", "setCacheDisabled", "Network.setCacheDisabled", null, void.class, null, true, false,
                false, new String[]{"cacheDisabled"}, new Object[]{cacheDisabled});
    }

    @Override
    public Boolean setCookie(String name, String value, String url, String domain, String path, Boolean secure,
            Boolean httpOnly, CookieSameSite sameSite, Double expires, CookiePriority priority) {
        return (Boolean) handler.invoke("Network", "setCookie", "Network.setCookie", "success", Boolean.class, null,
                false, false, false,
                new String[]{"name", "value", "url", "domain", "path", "secure", "httpOnly", "sameSite", "expires",
                        "priority"},
                new Object[]{name, value, url, domain, path, secure, httpOnly, sameSite, expires, priority});
    }

    @Override
    public void setCookies(List<CookieParam> cookies) {
        handler.invoke("Network", "setCookies", "Network.setCookies", null, void.class, null, true, false, false,
                new String[]{"cookies"}, new Object[]{cookies});
    }

    @Override
    public void setDataSizeLimitsForTest(Integer maxTotalSize, Integer maxResourceSize) {
        handler.invoke("Network", "setDataSizeLimitsForTest", "Network.setDataSizeLimitsForTest", null, void.class,
                null, true, false, false, new String[]{"maxTotalSize", "maxResourceSize"},
                new Object[]{maxTotalSize, maxResourceSize});
    }

    @Override
    public void setExtraHTTPHeaders(Map<String, Object> headers) {
        handler.invoke("Network", "setExtraHTTPHeaders", "Network.setExtraHTTPHeaders", null, void.class, null, true,
                false, false, new String[]{"headers"}, new Object[]{headers});
    }

    @Override
    public void setRequestInterception(List<RequestPattern> patterns) {
        handler.invoke("Network", "setRequestInterception", "Network.setRequestInterception", null, void.class, null,
                true, false, false, new String[]{"patterns"}, new Object[]{patterns});
    }

    @Override
    public void setUserAgentOverride(String userAgent, String acceptLanguage, String platform) {
        handler.invoke("Network", "setUserAgentOverride", "Network.setUserAgentOverride", null, void.class, null, true,
                false, false, new String[]{"userAgent", "acceptLanguage", "platform"},
                new Object[]{userAgent, acceptLanguage, platform});
    }

    @Override
    public void continueInterceptedRequest(String interceptionId) {
        handler.invoke("Network", "continueInterceptedRequest", "Network.continueInterceptedRequest", null, void.class,
                null, true, false, false, new String[]{"interceptionId"}, new Object[]{interceptionId});
    }

    @Override
    public void deleteCookies(String name) {
        handler.invoke("Network", "deleteCookies", "Network.deleteCookies", null, void.class, null, true, false, false,
                new String[]{"name"}, new Object[]{name});
    }

    @Override
    public void emulateNetworkConditions(Boolean offline, Double latency, Double downloadThroughput,
            Double uploadThroughput) {
        handler.invoke("Network", "emulateNetworkConditions", "Network.emulateNetworkConditions", null, void.class,
                null, true, false, false, new String[]{"offline", "latency", "downloadThroughput", "uploadThroughput"},
                new Object[]{offline, latency, downloadThroughput, uploadThroughput});
    }

    @Override
    public void enable() {
        handler.invoke("Network", "enable", "Network.enable", null, void.class, null, true, true, false, EMPTY_ARGS,
                EMPTY_VALUES);
    }

    @Override
    @java.lang.SuppressWarnings("unchecked")
    public List<Cookie> getCookies() {
        return (List<Cookie>) handler.invoke("Network", "getCookies", "Network.getCookies", "cookies", List.class,
                TT_LIST_COOKIES.getType(), false, false, false, EMPTY_ARGS, EMPTY_VALUES);
    }

    @Override
    @java.lang.SuppressWarnings("unchecked")
    public List<SearchMatch> searchInResponseBody(String requestId, String query) {
        return (List<SearchMatch>) handler.invoke("Network", "searchInResponseBody", "Network.searchInResponseBody",
                "result", List.class, TT_LIST_SEARCH_MATCH.getType(), false, false, false,
                new String[]{"requestId", "query"}, new Object[]{requestId, query});
    }

    @Override
    public Boolean setCookie(String name, String value) {
        return (Boolean) handler.invoke("Network", "setCookie", "Network.setCookie", "success", Boolean.class, null,
                false, false, false, new String[]{"name", "value"}, new Object[]{name, value});
    }

    @Override
    public void setUserAgentOverride(String userAgent) {
        handler.invoke("Network", "setUserAgentOverride", "Network.setUserAgentOverride", null, void.class, null, true,
                false, false, new String[]{"userAgent"}, new Object[]{userAgent});
    }
}
