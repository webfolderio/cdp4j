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
package io.webfolder.cdp.event.heapprofiler;

import io.webfolder.cdp.annotation.Domain;
import io.webfolder.cdp.annotation.EventName;

import java.util.List;

/**
 * If heap objects tracking has been started then backend may send update for one or more fragments
 */
@Domain("HeapProfiler")
@EventName("heapStatsUpdate")
public class HeapStatsUpdate {
    private List<Integer> statsUpdate;

    /**
     * An array of triplets. Each triplet describes a fragment. The first integer is the fragment
     * index, the second integer is a total count of objects for the fragment, the third integer is
     * a total size of the objects for the fragment.
     */
    public List<Integer> getStatsUpdate() {
        return statsUpdate;
    }

    /**
     * An array of triplets. Each triplet describes a fragment. The first integer is the fragment
     * index, the second integer is a total count of objects for the fragment, the third integer is
     * a total size of the objects for the fragment.
     */
    public void setStatsUpdate(List<Integer> statsUpdate) {
        this.statsUpdate = statsUpdate;
    }
}
