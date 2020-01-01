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
import io.webfolder.cdp.type.indexeddb.DatabaseWithObjectStores;
import io.webfolder.cdp.type.indexeddb.GetMetadataResult;
import io.webfolder.cdp.type.indexeddb.KeyRange;
import io.webfolder.cdp.type.indexeddb.RequestDataResult;

public class IndexedDBImpl implements IndexedDB {

    private static final String[] EMPTY_ARGS = new String[] {};
    private static final Object[] EMPTY_VALUES = new Object[] {};
    private static final TypeToken<List<String>> REQUEST_DATABASE_NAMES = new TypeToken<List<String>>() {
    };
    private final SessionInvocationHandler handler;

    public IndexedDBImpl(SessionInvocationHandler handler) {
        this.handler = handler;
    }

    @Override
    public void clearObjectStore(String securityOrigin, String databaseName, String objectStoreName) {
        handler.invoke("IndexedDB", "clearObjectStore", "IndexedDB.clearObjectStore", null, void.class, null, true,
                false, false, new String[] { "securityOrigin", "databaseName", "objectStoreName" },
                new Object[] { securityOrigin, databaseName, objectStoreName });
    }

    @Override
    public void deleteDatabase(String securityOrigin, String databaseName) {
        handler.invoke("IndexedDB", "deleteDatabase", "IndexedDB.deleteDatabase", null, void.class, null, true, false,
                false, new String[] { "securityOrigin", "databaseName" },
                new Object[] { securityOrigin, databaseName });
    }

    @Override
    public void deleteObjectStoreEntries(String securityOrigin, String databaseName, String objectStoreName,
            KeyRange keyRange) {
        handler.invoke("IndexedDB", "deleteObjectStoreEntries", "IndexedDB.deleteObjectStoreEntries", null, void.class,
                null, true, false, false,
                new String[] { "securityOrigin", "databaseName", "objectStoreName", "keyRange" },
                new Object[] { securityOrigin, databaseName, objectStoreName, keyRange });
    }

    @Override
    public void disable() {
        handler.invoke("IndexedDB", "disable", "IndexedDB.disable", null, void.class, null, true, false, true,
                EMPTY_ARGS, EMPTY_VALUES);
    }

    @Override
    public void enable() {
        handler.invoke("IndexedDB", "enable", "IndexedDB.enable", null, void.class, null, true, true, false, EMPTY_ARGS,
                EMPTY_VALUES);
    }

    @Override
    public GetMetadataResult getMetadata(String securityOrigin, String databaseName, String objectStoreName) {
        return (GetMetadataResult) handler.invoke("IndexedDB", "getMetadata", "IndexedDB.getMetadata", null,
                GetMetadataResult.class, null, false, false, false,
                new String[] { "securityOrigin", "databaseName", "objectStoreName" },
                new Object[] { securityOrigin, databaseName, objectStoreName });
    }

    @Override
    public RequestDataResult requestData(String securityOrigin, String databaseName, String objectStoreName,
            String indexName, Integer skipCount, Integer pageSize) {
        return (RequestDataResult) handler.invoke("IndexedDB", "requestData", "IndexedDB.requestData", null,
                RequestDataResult.class, null, false, false, false,
                new String[] { "securityOrigin", "databaseName", "objectStoreName", "indexName", "skipCount",
                        "pageSize" },
                new Object[] { securityOrigin, databaseName, objectStoreName, indexName, skipCount, pageSize });
    }

    @Override
    public RequestDataResult requestData(String securityOrigin, String databaseName, String objectStoreName,
            String indexName, Integer skipCount, Integer pageSize, KeyRange keyRange) {
        return (RequestDataResult) handler.invoke("IndexedDB", "requestData", "IndexedDB.requestData", null,
                RequestDataResult.class, null, false, false, false,
                new String[] { "securityOrigin", "databaseName", "objectStoreName", "indexName", "skipCount",
                        "pageSize", "keyRange" },
                new Object[] { securityOrigin, databaseName, objectStoreName, indexName, skipCount, pageSize,
                        keyRange });
    }

    @Override
    public DatabaseWithObjectStores requestDatabase(String securityOrigin, String databaseName) {
        return (DatabaseWithObjectStores) handler.invoke("IndexedDB", "requestDatabase", "IndexedDB.requestDatabase",
                "databaseWithObjectStores", DatabaseWithObjectStores.class, null, false, false, false,
                new String[] { "securityOrigin", "databaseName" }, new Object[] { securityOrigin, databaseName });
    }

    @Override
    @java.lang.SuppressWarnings("unchecked")
    public List<String> requestDatabaseNames(String securityOrigin) {
        return (List<String>) handler.invoke("IndexedDB", "requestDatabaseNames", "IndexedDB.requestDatabaseNames",
                "databaseNames", List.class, REQUEST_DATABASE_NAMES.getType(), false, false, false,
                new String[] { "securityOrigin" }, new Object[] { securityOrigin });
    }
}