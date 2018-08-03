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
package io.webfolder.cdp.exception;

public class CommandException extends CdpException {

    private static final long serialVersionUID = -815753477843472988L;

    private final int code;

    public CommandException(final int code, final String message, final String jsonRequest) {
        super(message + (jsonRequest == null ? "" : (" for " + jsonRequest)));
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
