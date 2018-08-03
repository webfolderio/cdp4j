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
package io.webfolder.cdp.type.constant;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ConsoleApiCallType {
    Log("log"),
    Debug("debug"),
    Info("info"),
    Error("error"),
    Warning("warning"),
    Dir("dir"),
    Dirxml("dirxml"),
    Table("table"),
    Trace("trace"),
    Clear("clear"),
    StartGroup("startGroup"),
    StartGroupCollapsed("startGroupCollapsed"),
    EndGroup("endGroup"),
    Assert("assert"),
    Profile("profile"),
    ProfileEnd("profileEnd"),
    Count("count"),
    TimeEnd("timeEnd");

    @JsonValue
    public final String value;

    ConsoleApiCallType(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
