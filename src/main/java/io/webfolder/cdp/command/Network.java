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
import io.webfolder.cdp.type.debugger.SearchMatch;
import io.webfolder.cdp.type.emulation.UserAgentMetadata;
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
import java.util.List;
import java.util.Map;

/**
 * Network domain allows tracking network activities of the page
 * It exposes information about http,
 * file, data and other requests and responses, their headers, bodies, timing, etc
 */
@Domain("Network")
public interface Network {
    /**
     * Tells whether clearing browser cache is supported.
     *
     * @return True if browser cache can be cleared.
     */
    @Returns("result")
    Boolean canClearBrowserCache();

    /**
     * Tells whether clearing browser cookies is supported.
     *
     * @return True if browser cookies can be cleared.
     */
    @Returns("result")
    Boolean canClearBrowserCookies();

    /**
     * Tells whether emulation of network conditions is supported.
     *
     * @return True if emulation of network conditions is supported.
     */
    @Returns("result")
    Boolean canEmulateNetworkConditions();

    /**
     * Clears browser cache.
     */
    void clearBrowserCache();

    /**
     * Clears browser cookies.
     */
    void clearBrowserCookies();

    /**
     * Response to Network.requestIntercepted which either modifies the request to continue with any
     * modifications, or blocks it, or completes it with the provided response bytes. If a network
     * fetch occurs as a result which encounters a redirect an additional Network.requestIntercepted
     * event will be sent with the same InterceptionId.
     * Deprecated, use Fetch.continueRequest, Fetch.fulfillRequest and Fetch.failRequest instead.
     *
     * @param errorReason If set this causes the request to fail with the given reason. Passing Aborted for requests
     * marked with isNavigationRequest also cancels the navigation. Must not be set in response
     * to an authChallenge.
     * @param rawResponse If set the requests completes using with the provided base64 encoded raw response, including
     * HTTP status line and headers etc... Must not be set in response to an authChallenge.
     * @param url If set the request url will be modified in a way that's not observable by page. Must not be
     * set in response to an authChallenge.
     * @param method If set this allows the request method to be overridden. Must not be set in response to an
     * authChallenge.
     * @param postData If set this allows postData to be set. Must not be set in response to an authChallenge.
     * @param headers If set this allows the request headers to be changed. Must not be set in response to an
     * authChallenge.
     * @param authChallengeResponse Response to a requestIntercepted with an authChallenge. Must not be set otherwise.
     */
    @Experimental
    void continueInterceptedRequest(String interceptionId, @Optional ErrorReason errorReason,
            @Optional String rawResponse, @Optional String url, @Optional String method,
            @Optional String postData, @Optional Map<String, Object> headers,
            @Optional AuthChallengeResponse authChallengeResponse);

    /**
     * Deletes browser cookies with matching name and url or domain/path pair.
     *
     * @param name Name of the cookies to remove.
     * @param url If specified, deletes all the cookies with the given name where domain and path match
     * provided URL.
     * @param domain If specified, deletes only cookies with the exact domain.
     * @param path If specified, deletes only cookies with the exact path.
     */
    void deleteCookies(String name, @Optional String url, @Optional String domain,
            @Optional String path);

    /**
     * Disables network tracking, prevents network events from being sent to the client.
     */
    void disable();

    /**
     * Activates emulation of network conditions.
     *
     * @param offline True to emulate internet disconnection.
     * @param latency Minimum latency from request sent to response headers received (ms).
     * @param downloadThroughput Maximal aggregated download throughput (bytes/sec). -1 disables download throttling.
     * @param uploadThroughput Maximal aggregated upload throughput (bytes/sec).  -1 disables upload throttling.
     * @param connectionType Connection type if known.
     */
    void emulateNetworkConditions(Boolean offline, Double latency, Double downloadThroughput,
            Double uploadThroughput, @Optional ConnectionType connectionType);

    /**
     * Enables network tracking, network events will now be delivered to the client.
     *
     * @param maxTotalBufferSize Buffer size in bytes to use when preserving network payloads (XHRs, etc).
     * @param maxResourceBufferSize Per-resource buffer size in bytes to use when preserving network payloads (XHRs, etc).
     * @param maxPostDataSize Longest post body size (in bytes) that would be included in requestWillBeSent notification
     */
    void enable(@Experimental @Optional Integer maxTotalBufferSize,
            @Experimental @Optional Integer maxResourceBufferSize,
            @Optional Integer maxPostDataSize);

    /**
     * Returns all browser cookies. Depending on the backend support, will return detailed cookie
     * information in the cookies field.
     *
     * @return Array of cookie objects.
     */
    @Returns("cookies")
    List<Cookie> getAllCookies();

    /**
     * Returns the DER-encoded certificate.
     *
     * @param origin Origin to get certificate for.
     */
    @Experimental
    @Returns("tableNames")
    List<String> getCertificate(String origin);

    /**
     * Returns all browser cookies for the current URL. Depending on the backend support, will return
     * detailed cookie information in the cookies field.
     *
     * @param urls The list of URLs for which applicable cookies will be fetched
     *
     * @return Array of cookie objects.
     */
    @Returns("cookies")
    List<Cookie> getCookies(@Optional List<String> urls);

    /**
     * Returns content served for the given request.
     *
     * @param requestId Identifier of the network request to get content for.
     *
     * @return GetResponseBodyResult
     */
    GetResponseBodyResult getResponseBody(String requestId);

    /**
     * Returns post data sent with the request. Returns an error when no data was sent with the request.
     *
     * @param requestId Identifier of the network request to get content for.
     *
     * @return Request body string, omitting files from multipart requests
     */
    @Returns("postData")
    String getRequestPostData(String requestId);

    /**
     * Returns content served for the given currently intercepted request.
     *
     * @param interceptionId Identifier for the intercepted request to get body for.
     *
     * @return GetResponseBodyForInterceptionResult
     */
    @Experimental
    GetResponseBodyForInterceptionResult getResponseBodyForInterception(String interceptionId);

    /**
     * Returns a handle to the stream representing the response body. Note that after this command,
     * the intercepted request can't be continued as is -- you either need to cancel it or to provide
     * the response body. The stream only supports sequential read, IO.read will fail if the position
     * is specified.
     *
     */
    @Experimental
    @Returns("stream")
    String takeResponseBodyForInterceptionAsStream(String interceptionId);

    /**
     * This method sends a new XMLHttpRequest which is identical to the original one. The following
     * parameters should be identical: method, url, async, request body, extra headers, withCredentials
     * attribute, user, password.
     *
     * @param requestId Identifier of XHR to replay.
     */
    @Experimental
    void replayXHR(String requestId);

    /**
     * Searches for given string in response content.
     *
     * @param requestId Identifier of the network response to search.
     * @param query String to search for.
     * @param caseSensitive If true, search is case sensitive.
     * @param isRegex If true, treats string parameter as regex.
     *
     * @return List of search matches.
     */
    @Experimental
    @Returns("result")
    List<SearchMatch> searchInResponseBody(String requestId, String query,
            @Optional Boolean caseSensitive, @Optional Boolean isRegex);

    /**
     * Blocks URLs from loading.
     *
     * @param urls URL patterns to block. Wildcards ('*') are allowed.
     */
    @Experimental
    void setBlockedURLs(List<String> urls);

    /**
     * Toggles ignoring of service worker for each request.
     *
     * @param bypass Bypass service worker and load from network.
     */
    @Experimental
    void setBypassServiceWorker(Boolean bypass);

    /**
     * Toggles ignoring cache for each request. If true, cache will not be used.
     *
     * @param cacheDisabled Cache disabled state.
     */
    void setCacheDisabled(Boolean cacheDisabled);

    /**
     * Sets a cookie with the given cookie data; may overwrite equivalent cookies if they exist.
     *
     * @param name Cookie name.
     * @param value Cookie value.
     * @param url The request-URI to associate with the setting of the cookie. This value can affect the
     * default domain and path values of the created cookie.
     * @param domain Cookie domain.
     * @param path Cookie path.
     * @param secure True if cookie is secure.
     * @param httpOnly True if cookie is http-only.
     * @param sameSite Cookie SameSite type.
     * @param expires Cookie expiration date, session cookie if not set
     * @param priority Cookie Priority type.
     *
     * @return True if successfully set cookie.
     */
    @Returns("success")
    Boolean setCookie(String name, String value, @Optional String url, @Optional String domain,
            @Optional String path, @Optional Boolean secure, @Optional Boolean httpOnly,
            @Optional CookieSameSite sameSite, @Optional Double expires,
            @Experimental @Optional CookiePriority priority);

    /**
     * Sets given cookies.
     *
     * @param cookies Cookies to be set.
     */
    void setCookies(List<CookieParam> cookies);

    /**
     * For testing.
     *
     * @param maxTotalSize Maximum total buffer size.
     * @param maxResourceSize Maximum per-resource size.
     */
    @Experimental
    void setDataSizeLimitsForTest(Integer maxTotalSize, Integer maxResourceSize);

    /**
     * Specifies whether to always send extra HTTP headers with the requests from this page.
     *
     * @param headers Map with extra HTTP headers.
     */
    void setExtraHTTPHeaders(Map<String, Object> headers);

    /**
     * Sets the requests to intercept that match the provided patterns and optionally resource types.
     * Deprecated, please use Fetch.enable instead.
     *
     * @param patterns Requests matching any of these patterns will be forwarded and wait for the corresponding
     * continueInterceptedRequest call.
     */
    @Experimental
    void setRequestInterception(List<RequestPattern> patterns);

    /**
     * Allows overriding user agent with the given string.
     *
     * @param userAgent User agent to use.
     * @param acceptLanguage Browser langugage to emulate.
     * @param platform The platform navigator.platform should return.
     * @param userAgentMetadata To be sent in Sec-CH-UA-* headers and returned in navigator.userAgentData
     */
    void setUserAgentOverride(String userAgent, @Optional String acceptLanguage,
            @Optional String platform, @Experimental @Optional UserAgentMetadata userAgentMetadata);

    /**
     * Response to Network.requestIntercepted which either modifies the request to continue with any
     * modifications, or blocks it, or completes it with the provided response bytes. If a network
     * fetch occurs as a result which encounters a redirect an additional Network.requestIntercepted
     * event will be sent with the same InterceptionId.
     * Deprecated, use Fetch.continueRequest, Fetch.fulfillRequest and Fetch.failRequest instead.
     *
     */
    @Experimental
    void continueInterceptedRequest(String interceptionId);

    /**
     * Deletes browser cookies with matching name and url or domain/path pair.
     *
     * @param name Name of the cookies to remove.
     */
    void deleteCookies(String name);

    /**
     * Activates emulation of network conditions.
     *
     * @param offline True to emulate internet disconnection.
     * @param latency Minimum latency from request sent to response headers received (ms).
     * @param downloadThroughput Maximal aggregated download throughput (bytes/sec). -1 disables download throttling.
     * @param uploadThroughput Maximal aggregated upload throughput (bytes/sec).  -1 disables upload throttling.
     */
    void emulateNetworkConditions(Boolean offline, Double latency, Double downloadThroughput,
            Double uploadThroughput);

    /**
     * Enables network tracking, network events will now be delivered to the client.
     */
    void enable();

    /**
     * Returns all browser cookies for the current URL. Depending on the backend support, will return
     * detailed cookie information in the cookies field.
     *
     * @return Array of cookie objects.
     */
    @Returns("cookies")
    List<Cookie> getCookies();

    /**
     * Searches for given string in response content.
     *
     * @param requestId Identifier of the network response to search.
     * @param query String to search for.
     *
     * @return List of search matches.
     */
    @Experimental
    @Returns("result")
    List<SearchMatch> searchInResponseBody(String requestId, String query);

    /**
     * Sets a cookie with the given cookie data; may overwrite equivalent cookies if they exist.
     *
     * @param name Cookie name.
     * @param value Cookie value.
     *
     * @return True if successfully set cookie.
     */
    @Returns("success")
    Boolean setCookie(String name, String value);

    /**
     * Allows overriding user agent with the given string.
     *
     * @param userAgent User agent to use.
     */
    void setUserAgentOverride(String userAgent);
}
