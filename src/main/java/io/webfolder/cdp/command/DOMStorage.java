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
import io.webfolder.cdp.type.domstorage.StorageId;

import java.util.List;

/**
 * Query and modify DOM storage
 */
@Experimental
@Domain("DOMStorage")
public interface DOMStorage {
    void clear(StorageId storageId);

    /**
     * Disables storage tracking, prevents storage events from being sent to the client.
     */
    void disable();

    /**
     * Enables storage tracking, storage events will now be delivered to the client.
     */
    void enable();

    @Returns("entries")
    List<String> getDOMStorageItems(StorageId storageId);

    void removeDOMStorageItem(StorageId storageId, String key);

    void setDOMStorageItem(StorageId storageId, String key, String value);
}
