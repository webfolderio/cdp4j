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
package io.webfolder.cdp.type.emulation;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * advance: If the scheduler runs out of immediate work, the virtual time base may fast forward to
 * allow the next delayed task (if any) to run; pause: The virtual time base may not advance;
 * pauseIfNetworkFetchesPending: The virtual time base may not advance if there are any pending
 * resource fetches
 */
public enum VirtualTimePolicy {
    Advance("advance"),
    Pause("pause"),
    PauseIfNetworkFetchesPending("pauseIfNetworkFetchesPending");

    @JsonValue
    public final String value;

    VirtualTimePolicy(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
