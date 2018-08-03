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
package io.webfolder.cdp.type.network;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Stages of the interception to begin intercepting
 * Request will intercept before the request is
 * sent
 * Response will intercept after the response is received
 */
public enum InterceptionStage {
    Request("Request"),
    HeadersReceived("HeadersReceived");

    @JsonValue
    public final String value;

    InterceptionStage(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
