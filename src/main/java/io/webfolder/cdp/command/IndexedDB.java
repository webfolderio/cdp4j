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
import io.webfolder.cdp.annotation.Optional;
import io.webfolder.cdp.annotation.Returns;
import io.webfolder.cdp.type.indexeddb.DatabaseWithObjectStores;
import io.webfolder.cdp.type.indexeddb.KeyRange;
import io.webfolder.cdp.type.indexeddb.RequestDataResult;

import java.util.List;

@Experimental
@Domain("IndexedDB")
public interface IndexedDB {
    /**
     * Clears all entries from an object store.
     * 
     * @param securityOrigin Security origin.
     * @param databaseName Database name.
     * @param objectStoreName Object store name.
     */
    void clearObjectStore(String securityOrigin, String databaseName, String objectStoreName);

    /**
     * Deletes a database.
     * 
     * @param securityOrigin Security origin.
     * @param databaseName Database name.
     */
    void deleteDatabase(String securityOrigin, String databaseName);

    /**
     * Delete a range of entries from an object store
     * 
     * @param keyRange Range of entry keys to delete
     */
    void deleteObjectStoreEntries(String securityOrigin, String databaseName,
            String objectStoreName, KeyRange keyRange);

    /**
     * Disables events from backend.
     */
    void disable();

    /**
     * Enables events from backend.
     */
    void enable();

    /**
     * Requests data from object store or index.
     * 
     * @param securityOrigin Security origin.
     * @param databaseName Database name.
     * @param objectStoreName Object store name.
     * @param indexName Index name, empty string for object store data requests.
     * @param skipCount Number of records to skip.
     * @param pageSize Number of records to fetch.
     * @param keyRange Key range.
     * 
     * @return RequestDataResult
     */
    RequestDataResult requestData(String securityOrigin, String databaseName,
            String objectStoreName, String indexName, Integer skipCount, Integer pageSize,
            @Optional KeyRange keyRange);

    /**
     * Requests database with given name in given frame.
     * 
     * @param securityOrigin Security origin.
     * @param databaseName Database name.
     * 
     * @return Database with an array of object stores.
     */
    @Returns("databaseWithObjectStores")
    DatabaseWithObjectStores requestDatabase(String securityOrigin, String databaseName);

    /**
     * Requests database names for given security origin.
     * 
     * @param securityOrigin Security origin.
     * 
     * @return Database names for origin.
     */
    @Returns("databaseNames")
    List<String> requestDatabaseNames(String securityOrigin);

    /**
     * Requests data from object store or index.
     * 
     * @param securityOrigin Security origin.
     * @param databaseName Database name.
     * @param objectStoreName Object store name.
     * @param indexName Index name, empty string for object store data requests.
     * @param skipCount Number of records to skip.
     * @param pageSize Number of records to fetch.
     * 
     * @return RequestDataResult
     */
    RequestDataResult requestData(String securityOrigin, String databaseName,
            String objectStoreName, String indexName, Integer skipCount, Integer pageSize);
}
