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
import io.webfolder.cdp.type.cachestorage.Cache;
import io.webfolder.cdp.type.cachestorage.CachedResponse;
import io.webfolder.cdp.type.cachestorage.Header;
import io.webfolder.cdp.type.cachestorage.RequestEntriesResult;
import java.util.List;

@Experimental
@Domain("CacheStorage")
public interface CacheStorage {
    /**
     * Deletes a cache.
     * 
     * @param cacheId Id of cache for deletion.
     */
    void deleteCache(String cacheId);

    /**
     * Deletes a cache entry.
     * 
     * @param cacheId Id of cache where the entry will be deleted.
     * @param request URL spec of the request.
     */
    void deleteEntry(String cacheId, String request);

    /**
     * Requests cache names.
     * 
     * @param securityOrigin Security origin.
     * 
     * @return Caches for the security origin.
     */
    @Returns("caches")
    List<Cache> requestCacheNames(String securityOrigin);

    /**
     * Fetches cache entry.
     * 
     * @param cacheId Id of cache that contains the entry.
     * @param requestURL URL spec of the request.
     * @param requestHeaders headers of the request.
     * 
     * @return Response read from the cache.
     */
    @Returns("response")
    CachedResponse requestCachedResponse(String cacheId, String requestURL,
            List<Header> requestHeaders);

    /**
     * Requests data from cache.
     * 
     * @param cacheId ID of cache to get entries from.
     * @param skipCount Number of records to skip.
     * @param pageSize Number of records to fetch.
     * @param pathFilter If present, only return the entries containing this substring in the path
     * 
     * @return RequestEntriesResult
     */
    RequestEntriesResult requestEntries(String cacheId, Integer skipCount, Integer pageSize,
            @Optional String pathFilter);

    /**
     * Requests data from cache.
     * 
     * @param cacheId ID of cache to get entries from.
     * @param skipCount Number of records to skip.
     * @param pageSize Number of records to fetch.
     * 
     * @return RequestEntriesResult
     */
    RequestEntriesResult requestEntries(String cacheId, Integer skipCount, Integer pageSize);
}
