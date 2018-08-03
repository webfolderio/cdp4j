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

import io.webfolder.cdp.annotation.Experimental;

import java.util.ArrayList;
import java.util.List;

/**
 * Information about a signed exchange response
 */
@Experimental
public class SignedExchangeInfo {
    private Response outerResponse;

    private SignedExchangeHeader header;

    private SecurityDetails securityDetails;

    private List<SignedExchangeError> errors = new ArrayList<>();

    /**
     * The outer response of signed HTTP exchange which was received from network.
     */
    public Response getOuterResponse() {
        return outerResponse;
    }

    /**
     * The outer response of signed HTTP exchange which was received from network.
     */
    public void setOuterResponse(Response outerResponse) {
        this.outerResponse = outerResponse;
    }

    /**
     * Information about the signed exchange header.
     */
    public SignedExchangeHeader getHeader() {
        return header;
    }

    /**
     * Information about the signed exchange header.
     */
    public void setHeader(SignedExchangeHeader header) {
        this.header = header;
    }

    /**
     * Security details for the signed exchange header.
     */
    public SecurityDetails getSecurityDetails() {
        return securityDetails;
    }

    /**
     * Security details for the signed exchange header.
     */
    public void setSecurityDetails(SecurityDetails securityDetails) {
        this.securityDetails = securityDetails;
    }

    /**
     * Errors occurred while handling the signed exchagne.
     */
    public List<SignedExchangeError> getErrors() {
        return errors;
    }

    /**
     * Errors occurred while handling the signed exchagne.
     */
    public void setErrors(List<SignedExchangeError> errors) {
        this.errors = errors;
    }
}
