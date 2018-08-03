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
package io.webfolder.cdp.type.browser;

import io.webfolder.cdp.annotation.Experimental;

import java.util.ArrayList;
import java.util.List;

/**
 * Chrome histogram
 */
@Experimental
public class Histogram {
    private String name;

    private Integer sum;

    private Integer count;

    private List<Bucket> buckets = new ArrayList<>();

    /**
     * Name.
     */
    public String getName() {
        return name;
    }

    /**
     * Name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sum of sample values.
     */
    public Integer getSum() {
        return sum;
    }

    /**
     * Sum of sample values.
     */
    public void setSum(Integer sum) {
        this.sum = sum;
    }

    /**
     * Total number of samples.
     */
    public Integer getCount() {
        return count;
    }

    /**
     * Total number of samples.
     */
    public void setCount(Integer count) {
        this.count = count;
    }

    /**
     * Buckets.
     */
    public List<Bucket> getBuckets() {
        return buckets;
    }

    /**
     * Buckets.
     */
    public void setBuckets(List<Bucket> buckets) {
        this.buckets = buckets;
    }
}
