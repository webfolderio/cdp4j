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

import io.webfolder.cdp.type.security.SecurityState;
import java.util.HashMap;
import java.util.Map;

/**
 * HTTP response data
 */
public class Response {
    private String url;

    private Integer status;

    private String statusText;

    private Map<String, Object> headers = new HashMap<>();

    private String headersText;

    private String mimeType;

    private Map<String, Object> requestHeaders = new HashMap<>();

    private String requestHeadersText;

    private Boolean connectionReused;

    private Double connectionId;

    private String remoteIPAddress;

    private Integer remotePort;

    private Boolean fromDiskCache;

    private Boolean fromServiceWorker;

    private Double encodedDataLength;

    private ResourceTiming timing;

    private String protocol;

    private SecurityState securityState;

    private SecurityDetails securityDetails;

    /**
     * Response URL. This URL can be different from CachedResource.url in case of redirect.
     */
    public String getUrl() {
        return url;
    }

    /**
     * Response URL. This URL can be different from CachedResource.url in case of redirect.
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * HTTP response status code.
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * HTTP response status code.
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * HTTP response status text.
     */
    public String getStatusText() {
        return statusText;
    }

    /**
     * HTTP response status text.
     */
    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }

    /**
     * HTTP response headers.
     */
    public Map<String, Object> getHeaders() {
        return headers;
    }

    /**
     * HTTP response headers.
     */
    public void setHeaders(Map<String, Object> headers) {
        this.headers = headers;
    }

    /**
     * HTTP response headers text.
     */
    public String getHeadersText() {
        return headersText;
    }

    /**
     * HTTP response headers text.
     */
    public void setHeadersText(String headersText) {
        this.headersText = headersText;
    }

    /**
     * Resource mimeType as determined by the browser.
     */
    public String getMimeType() {
        return mimeType;
    }

    /**
     * Resource mimeType as determined by the browser.
     */
    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    /**
     * Refined HTTP request headers that were actually transmitted over the network.
     */
    public Map<String, Object> getRequestHeaders() {
        return requestHeaders;
    }

    /**
     * Refined HTTP request headers that were actually transmitted over the network.
     */
    public void setRequestHeaders(Map<String, Object> requestHeaders) {
        this.requestHeaders = requestHeaders;
    }

    /**
     * HTTP request headers text.
     */
    public String getRequestHeadersText() {
        return requestHeadersText;
    }

    /**
     * HTTP request headers text.
     */
    public void setRequestHeadersText(String requestHeadersText) {
        this.requestHeadersText = requestHeadersText;
    }

    /**
     * Specifies whether physical connection was actually reused for this request.
     */
    public Boolean isConnectionReused() {
        return connectionReused;
    }

    /**
     * Specifies whether physical connection was actually reused for this request.
     */
    public void setConnectionReused(Boolean connectionReused) {
        this.connectionReused = connectionReused;
    }

    /**
     * Physical connection id that was actually used for this request.
     */
    public Double getConnectionId() {
        return connectionId;
    }

    /**
     * Physical connection id that was actually used for this request.
     */
    public void setConnectionId(Double connectionId) {
        this.connectionId = connectionId;
    }

    /**
     * Remote IP address.
     */
    public String getRemoteIPAddress() {
        return remoteIPAddress;
    }

    /**
     * Remote IP address.
     */
    public void setRemoteIPAddress(String remoteIPAddress) {
        this.remoteIPAddress = remoteIPAddress;
    }

    /**
     * Remote port.
     */
    public Integer getRemotePort() {
        return remotePort;
    }

    /**
     * Remote port.
     */
    public void setRemotePort(Integer remotePort) {
        this.remotePort = remotePort;
    }

    /**
     * Specifies that the request was served from the disk cache.
     */
    public Boolean isFromDiskCache() {
        return fromDiskCache;
    }

    /**
     * Specifies that the request was served from the disk cache.
     */
    public void setFromDiskCache(Boolean fromDiskCache) {
        this.fromDiskCache = fromDiskCache;
    }

    /**
     * Specifies that the request was served from the ServiceWorker.
     */
    public Boolean isFromServiceWorker() {
        return fromServiceWorker;
    }

    /**
     * Specifies that the request was served from the ServiceWorker.
     */
    public void setFromServiceWorker(Boolean fromServiceWorker) {
        this.fromServiceWorker = fromServiceWorker;
    }

    /**
     * Total number of bytes received for this request so far.
     */
    public Double getEncodedDataLength() {
        return encodedDataLength;
    }

    /**
     * Total number of bytes received for this request so far.
     */
    public void setEncodedDataLength(Double encodedDataLength) {
        this.encodedDataLength = encodedDataLength;
    }

    /**
     * Timing information for the given request.
     */
    public ResourceTiming getTiming() {
        return timing;
    }

    /**
     * Timing information for the given request.
     */
    public void setTiming(ResourceTiming timing) {
        this.timing = timing;
    }

    /**
     * Protocol used to fetch this request.
     */
    public String getProtocol() {
        return protocol;
    }

    /**
     * Protocol used to fetch this request.
     */
    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    /**
     * Security state of the request resource.
     */
    public SecurityState getSecurityState() {
        return securityState;
    }

    /**
     * Security state of the request resource.
     */
    public void setSecurityState(SecurityState securityState) {
        this.securityState = securityState;
    }

    /**
     * Security details for the request.
     */
    public SecurityDetails getSecurityDetails() {
        return securityDetails;
    }

    /**
     * Security details for the request.
     */
    public void setSecurityDetails(SecurityDetails securityDetails) {
        this.securityDetails = securityDetails;
    }
}
