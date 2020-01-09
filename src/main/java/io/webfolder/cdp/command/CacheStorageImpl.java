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

import io.webfolder.cdp.session.SessionInvocationHandler;
import io.webfolder.cdp.command.CacheStorage;
import io.webfolder.cdp.type.cachestorage.Cache;
import io.webfolder.cdp.type.cachestorage.CachedResponse;
import io.webfolder.cdp.type.cachestorage.Header;
import io.webfolder.cdp.type.cachestorage.RequestEntriesResult;
import java.util.List;
import com.google.gson.reflect.TypeToken;

public class CacheStorageImpl implements CacheStorage {

    private final SessionInvocationHandler handler;
    private static final TypeToken<List<Cache>> TT_CACHE_NAMES = new TypeToken<List<Cache>>() { };

    public CacheStorageImpl(SessionInvocationHandler handler) {
        this.handler = handler;
    }

    @Override
    public void deleteCache(String cacheId) {
        handler.invoke("CacheStorage", "deleteCache", "CacheStorage.deleteCache", null, void.class, null, true, false,
                false, new String[]{"cacheId"}, new Object[]{cacheId});
    }

    @Override
    public void deleteEntry(String cacheId, String request) {
        handler.invoke("CacheStorage", "deleteEntry", "CacheStorage.deleteEntry", null, void.class, null, true, false,
                false, new String[]{"cacheId", "request"}, new Object[]{cacheId, request});
    }

    @Override
    @java.lang.SuppressWarnings("unchecked")
    public List<Cache> requestCacheNames(String securityOrigin) {
        return (List<Cache>) handler.invoke("CacheStorage", "requestCacheNames", "CacheStorage.requestCacheNames",
                "caches", List.class, TT_CACHE_NAMES.getType(), false, false, false,
                new String[]{"securityOrigin"}, new Object[]{securityOrigin});
    }

    @Override
    public CachedResponse requestCachedResponse(String cacheId, String requestURL, List<Header> requestHeaders) {
        return (CachedResponse) handler.invoke("CacheStorage", "requestCachedResponse",
                "CacheStorage.requestCachedResponse", "response", CachedResponse.class, null, false, false, false,
                new String[]{"cacheId", "requestURL", "requestHeaders"},
                new Object[]{cacheId, requestURL, requestHeaders});
    }

    @Override
    public RequestEntriesResult requestEntries(String cacheId, Integer skipCount, Integer pageSize, String pathFilter) {
        return (RequestEntriesResult) handler.invoke("CacheStorage", "requestEntries", "CacheStorage.requestEntries",
                null, RequestEntriesResult.class, null, false, false, false,
                new String[]{"cacheId", "skipCount", "pageSize", "pathFilter"},
                new Object[]{cacheId, skipCount, pageSize, pathFilter});
    }

    @Override
    public RequestEntriesResult requestEntries(String cacheId) {
        return (RequestEntriesResult) handler.invoke("CacheStorage", "requestEntries", "CacheStorage.requestEntries",
                null, RequestEntriesResult.class, null, false, false, false, new String[]{"cacheId"},
                new Object[]{cacheId});
    }
}
