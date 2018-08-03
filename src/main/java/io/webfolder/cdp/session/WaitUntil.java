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
package io.webfolder.cdp.session;

import com.fasterxml.jackson.annotation.JsonValue;

public enum WaitUntil {
	Load("load"), DomContentLoad("DOMContentLoaded"),
	/**
	 * Consider navigation to be finished when there are no more than 0 network
	 * connections for at least 500 ms.
	 */
	NetworkIdle("networkIdle"),
	/**
	 * Consider navigation to be finished when there are no more than 2 network
	 * connections for at least 500 ms.
	 */
	NetworkAlmostIdle("networkAlmostIdle"), DomReady("DomReady");

	@JsonValue
	public String value;

	private WaitUntil(String value) {
		this.value = value;
	}
}
