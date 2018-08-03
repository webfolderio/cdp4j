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
package io.webfolder.cdp.event.performance;

import java.util.ArrayList;
import java.util.List;

import io.webfolder.cdp.annotation.Domain;
import io.webfolder.cdp.annotation.EventName;
import io.webfolder.cdp.type.performance.Metric;

/**
 * Current values of the metrics
 */
@Domain("Performance")
@EventName("metrics")
public class Metrics {
    private List<Metric> metrics = new ArrayList<>();

    private String title;

    /**
     * Current values of the metrics.
     */
    public List<Metric> getMetrics() {
        return metrics;
    }

    /**
     * Current values of the metrics.
     */
    public void setMetrics(List<Metric> metrics) {
        this.metrics = metrics;
    }

    /**
     * Timestamp title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Timestamp title.
     */
    public void setTitle(String title) {
        this.title = title;
    }
}
