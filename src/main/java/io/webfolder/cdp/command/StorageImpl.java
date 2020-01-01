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
import io.webfolder.cdp.command.Storage;
import io.webfolder.cdp.type.storage.GetUsageAndQuotaResult;

public class StorageImpl implements Storage {

    private final SessionInvocationHandler handler;

    public StorageImpl(SessionInvocationHandler handler) {
        this.handler = handler;
    }

    @Override
    public void clearDataForOrigin(String origin, String storageTypes) {
        handler.invoke("Storage", "clearDataForOrigin", "Storage.clearDataForOrigin", null, void.class, null, true,
                false, false, new String[] { "origin", "storageTypes" }, new Object[] { origin, storageTypes });
    }

    @Override
    public GetUsageAndQuotaResult getUsageAndQuota(String origin) {
        return (GetUsageAndQuotaResult) handler.invoke("Storage", "getUsageAndQuota", "Storage.getUsageAndQuota", null,
                GetUsageAndQuotaResult.class, null, false, false, false, new String[] { "origin" },
                new Object[] { origin });
    }

    @Override
    public void trackCacheStorageForOrigin(String origin) {
        handler.invoke("Storage", "trackCacheStorageForOrigin", "Storage.trackCacheStorageForOrigin", null, void.class,
                null, true, false, false, new String[] { "origin" }, new Object[] { origin });
    }

    @Override
    public void trackIndexedDBForOrigin(String origin) {
        handler.invoke("Storage", "trackIndexedDBForOrigin", "Storage.trackIndexedDBForOrigin", null, void.class, null,
                true, false, false, new String[] { "origin" }, new Object[] { origin });
    }

    @Override
    public void untrackCacheStorageForOrigin(String origin) {
        handler.invoke("Storage", "untrackCacheStorageForOrigin", "Storage.untrackCacheStorageForOrigin", null,
                void.class, null, true, false, false, new String[] { "origin" }, new Object[] { origin });
    }

    @Override
    public void untrackIndexedDBForOrigin(String origin) {
        handler.invoke("Storage", "untrackIndexedDBForOrigin", "Storage.untrackIndexedDBForOrigin", null, void.class,
                null, true, false, false, new String[] { "origin" }, new Object[] { origin });
    }
}