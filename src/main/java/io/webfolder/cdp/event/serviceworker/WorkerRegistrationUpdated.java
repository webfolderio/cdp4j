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
package io.webfolder.cdp.event.serviceworker;

import java.util.ArrayList;
import java.util.List;

import io.webfolder.cdp.annotation.Domain;
import io.webfolder.cdp.annotation.EventName;
import io.webfolder.cdp.type.serviceworker.ServiceWorkerRegistration;

@Domain("ServiceWorker")
@EventName("workerRegistrationUpdated")
public class WorkerRegistrationUpdated {
    private List<ServiceWorkerRegistration> registrations = new ArrayList<>();

    public List<ServiceWorkerRegistration> getRegistrations() {
        return registrations;
    }

    public void setRegistrations(List<ServiceWorkerRegistration> registrations) {
        this.registrations = registrations;
    }
}
