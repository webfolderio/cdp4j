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
package io.webfolder.cdp.type.css;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Stylesheet type: "injected" for stylesheets injected via extension, "user-agent" for user-agent
 * stylesheets, "inspector" for stylesheets created by the inspector (i
 * e
 * those holding the "via
 * inspector" rules), "regular" for regular stylesheets
 */
public enum StyleSheetOrigin {
    Injected("injected"),

    UserAgent("user-agent"),

    Inspector("inspector"),

    Regular("regular");

    @JsonValue
    public final String value;

    StyleSheetOrigin(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
