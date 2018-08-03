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
import io.webfolder.cdp.annotation.Returns;
import io.webfolder.cdp.type.cachestorage.Cache;
import io.webfolder.cdp.type.cachestorage.CachedResponse;
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
     * @param cacheId Id of cache that contains the enty.
     * @param requestURL URL spec of the request.
     * 
     * @return Response read from the cache.
     */
    @Returns("response")
    CachedResponse requestCachedResponse(String cacheId, String requestURL);

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
