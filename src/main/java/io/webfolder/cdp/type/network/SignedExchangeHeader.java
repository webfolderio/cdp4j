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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Information about a signed exchange header
 * https://wicg.github.io/webpackage/draft-yasskin-httpbis-origin-signed-exchanges-impl.html#cbor-representation
 */
@Experimental
public class SignedExchangeHeader {
    private String requestUrl;

    private String requestMethod;

    private Integer responseCode;

    private Map<String, Object> responseHeaders = new HashMap<>();

    private List<SignedExchangeSignature> signatures = new ArrayList<>();

    /**
     * Signed exchange request URL.
     */
    public String getRequestUrl() {
        return requestUrl;
    }

    /**
     * Signed exchange request URL.
     */
    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    /**
     * Signed exchange request method.
     */
    public String getRequestMethod() {
        return requestMethod;
    }

    /**
     * Signed exchange request method.
     */
    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    /**
     * Signed exchange response code.
     */
    public Integer getResponseCode() {
        return responseCode;
    }

    /**
     * Signed exchange response code.
     */
    public void setResponseCode(Integer responseCode) {
        this.responseCode = responseCode;
    }

    /**
     * Signed exchange response headers.
     */
    public Map<String, Object> getResponseHeaders() {
        return responseHeaders;
    }

    /**
     * Signed exchange response headers.
     */
    public void setResponseHeaders(Map<String, Object> responseHeaders) {
        this.responseHeaders = responseHeaders;
    }

    /**
     * Signed exchange response signature.
     */
    public List<SignedExchangeSignature> getSignatures() {
        return signatures;
    }

    /**
     * Signed exchange response signature.
     */
    public void setSignatures(List<SignedExchangeSignature> signatures) {
        this.signatures = signatures;
    }
}
