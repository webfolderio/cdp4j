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

import io.webfolder.cdp.session.SessionInvocationHandler;
import io.webfolder.cdp.type.fetch.AuthChallengeResponse;
import io.webfolder.cdp.type.fetch.GetResponseBodyResult;
import io.webfolder.cdp.type.fetch.HeaderEntry;
import io.webfolder.cdp.type.fetch.RequestPattern;
import io.webfolder.cdp.type.network.ErrorReason;

public class FetchImpl implements Fetch {

    private static final Object[] EMPTY_VALUES = new Object[]{};
    private static final String[] EMPTY_ARGS = new String[]{};
    private final SessionInvocationHandler handler;

    public FetchImpl(SessionInvocationHandler handler) {
        this.handler = handler;
    }

    @Override
    public void disable() {
        handler.invoke("Fetch", "disable", "Fetch.disable", null, void.class, null, true, false, true, EMPTY_ARGS,
                EMPTY_VALUES);
    }

    @Override
    public void enable(List<RequestPattern> patterns, Boolean handleAuthRequests) {
        handler.invoke("Fetch", "enable", "Fetch.enable", null, void.class, null, true, true, false,
                new String[]{"patterns", "handleAuthRequests"}, new Object[]{patterns, handleAuthRequests});
    }

    @Override
    public void failRequest(String requestId, ErrorReason errorReason) {
        handler.invoke("Fetch", "failRequest", "Fetch.failRequest", null, void.class, null, true, false, false,
                new String[]{"requestId", "errorReason"}, new Object[]{requestId, errorReason});
    }

    @Override
    public void fulfillRequest(String requestId, Integer responseCode, List<HeaderEntry> responseHeaders,
            String binaryResponseHeaders, String body, String responsePhrase) {
        handler.invoke("Fetch", "fulfillRequest", "Fetch.fulfillRequest", null, void.class, null, true, false, false,
                new String[]{"requestId", "responseCode", "responseHeaders", "binaryResponseHeaders", "body",
                        "responsePhrase"},
                new Object[]{requestId, responseCode, responseHeaders, binaryResponseHeaders, body, responsePhrase});
    }

    @Override
    public void continueRequest(String requestId, String url, String method, String postData,
            List<HeaderEntry> headers) {
        handler.invoke("Fetch", "continueRequest", "Fetch.continueRequest", null, void.class, null, true, false, false,
                new String[]{"requestId", "url", "method", "postData", "headers"},
                new Object[]{requestId, url, method, postData, headers});
    }

    @Override
    public void continueWithAuth(String requestId, AuthChallengeResponse authChallengeResponse) {
        handler.invoke("Fetch", "continueWithAuth", "Fetch.continueWithAuth", null, void.class, null, true, false,
                false, new String[]{"requestId", "authChallengeResponse"},
                new Object[]{requestId, authChallengeResponse});
    }

    @Override
    public GetResponseBodyResult getResponseBody(String requestId) {
        return (GetResponseBodyResult) handler.invoke("Fetch", "getResponseBody", "Fetch.getResponseBody", null,
                GetResponseBodyResult.class, null, false, false, false, new String[]{"requestId"},
                new Object[]{requestId});
    }

    @Override
    public String takeResponseBodyAsStream(String requestId) {
        return (String) handler.invoke("Fetch", "takeResponseBodyAsStream", "Fetch.takeResponseBodyAsStream", "stream",
                String.class, null, false, false, false, new String[]{"requestId"}, new Object[]{requestId});
    }

    @Override
    public void enable() {
        handler.invoke("Fetch", "enable", "Fetch.enable", null, void.class, null, true, true, false, EMPTY_ARGS,
                EMPTY_VALUES);
    }

    @Override
    public void fulfillRequest(String requestId, Integer responseCode) {
        handler.invoke("Fetch", "fulfillRequest", "Fetch.fulfillRequest", null, void.class, null, true, false, false,
                new String[]{"requestId", "responseCode"}, new Object[]{requestId, responseCode});
    }

    @Override
    public void continueRequest(String requestId) {
        handler.invoke("Fetch", "continueRequest", "Fetch.continueRequest", null, void.class, null, true, false, false,
                new String[]{"requestId"}, new Object[]{requestId});
    }
}