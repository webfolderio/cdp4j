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
import io.webfolder.cdp.type.database.ExecuteSQLResult;
import java.util.List;

@Experimental
@Domain("Database")
public interface Database {
    /**
     * Disables database tracking, prevents database events from being sent to the client.
     */
    void disable();

    /**
     * Enables database tracking, database events will now be delivered to the client.
     */
    void enable();

    /**
     * 
     * @return ExecuteSQLResult
     */
    ExecuteSQLResult executeSQL(String databaseId, String query);

    @Returns("tableNames")
    List<String> getDatabaseTableNames(String databaseId);
}
