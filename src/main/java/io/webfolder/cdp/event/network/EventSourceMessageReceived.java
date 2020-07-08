/**
 * cdp4j Commercial License
 *
 * Copyright 2017, 2020 WebFolder OÜ
 *
 * Permission  is hereby  granted,  to "____" obtaining  a  copy of  this software  and
 * associated  documentation files  (the "Software"), to deal in  the Software  without
 * restriction, including without limitation  the rights  to use, copy, modify,  merge,
 * publish, distribute  and sublicense  of the Software,  and to permit persons to whom
 * the Software is furnished to do so, subject to the following conditions:
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR  IMPLIED,
 * INCLUDING  BUT NOT  LIMITED  TO THE  WARRANTIES  OF  MERCHANTABILITY, FITNESS  FOR A
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL  THE AUTHORS  OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package io.webfolder.cdp.event.network;

import io.webfolder.cdp.annotation.Domain;
import io.webfolder.cdp.annotation.EventName;

/**
 * Fired when EventSource message is received
 */
@Domain("Network")
@EventName("eventSourceMessageReceived")
public class EventSourceMessageReceived {
    private String requestId;

    private Double timestamp;

    private String eventName;

    private String eventId;

    private String data;

    /**
     * Request identifier.
     */
    public String getRequestId() {
        return requestId;
    }

    /**
     * Request identifier.
     */
    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    /**
     * Timestamp.
     */
    public Double getTimestamp() {
        return timestamp;
    }

    /**
     * Timestamp.
     */
    public void setTimestamp(Double timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Message type.
     */
    public String getEventName() {
        return eventName;
    }

    /**
     * Message type.
     */
    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    /**
     * Message identifier.
     */
    public String getEventId() {
        return eventId;
    }

    /**
     * Message identifier.
     */
    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    /**
     * Message content.
     */
    public String getData() {
        return data;
    }

    /**
     * Message content.
     */
    public void setData(String data) {
        this.data = data;
    }
}
