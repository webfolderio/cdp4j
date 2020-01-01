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
package io.webfolder.cdp.type.backgroundservice;

import java.util.List;

import com.vimeo.stag.UseStag;

@UseStag
public class BackgroundServiceEvent {
    private Double timestamp;

    private String origin;

    private String serviceWorkerRegistrationId;

    private ServiceName service;

    private String eventName;

    private String instanceId;

    private List<EventMetadata> eventMetadata;

    /**
     * Timestamp of the event (in seconds).
     */
    public Double getTimestamp() {
        return timestamp;
    }

    /**
     * Timestamp of the event (in seconds).
     */
    public void setTimestamp(Double timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * The origin this event belongs to.
     */
    public String getOrigin() {
        return origin;
    }

    /**
     * The origin this event belongs to.
     */
    public void setOrigin(String origin) {
        this.origin = origin;
    }

    /**
     * The Service Worker ID that initiated the event.
     */
    public String getServiceWorkerRegistrationId() {
        return serviceWorkerRegistrationId;
    }

    /**
     * The Service Worker ID that initiated the event.
     */
    public void setServiceWorkerRegistrationId(String serviceWorkerRegistrationId) {
        this.serviceWorkerRegistrationId = serviceWorkerRegistrationId;
    }

    /**
     * The Background Service this event belongs to.
     */
    public ServiceName getService() {
        return service;
    }

    /**
     * The Background Service this event belongs to.
     */
    public void setService(ServiceName service) {
        this.service = service;
    }

    /**
     * A description of the event.
     */
    public String getEventName() {
        return eventName;
    }

    /**
     * A description of the event.
     */
    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    /**
     * An identifier that groups related events together.
     */
    public String getInstanceId() {
        return instanceId;
    }

    /**
     * An identifier that groups related events together.
     */
    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    /**
     * A list of event-specific information.
     */
    public List<EventMetadata> getEventMetadata() {
        return eventMetadata;
    }

    /**
     * A list of event-specific information.
     */
    public void setEventMetadata(List<EventMetadata> eventMetadata) {
        this.eventMetadata = eventMetadata;
    }
}
