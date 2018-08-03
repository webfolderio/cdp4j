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
package io.webfolder.cdp.command;

import io.webfolder.cdp.annotation.Domain;
import io.webfolder.cdp.annotation.Experimental;
import io.webfolder.cdp.annotation.Optional;
import io.webfolder.cdp.type.domsnapshot.CaptureSnapshotResult;
import io.webfolder.cdp.type.domsnapshot.GetSnapshotResult;

import java.util.List;

/**
 * This domain facilitates obtaining document snapshots with DOM, layout, and style information
 */
@Experimental
@Domain("DOMSnapshot")
public interface DOMSnapshot {
    /**
     * Disables DOM snapshot agent for the given page.
     */
    void disable();

    /**
     * Enables DOM snapshot agent for the given page.
     */
    void enable();

    /**
     * Returns a document snapshot, including the full DOM tree of the root node (including iframes,
     * template contents, and imported documents) in a flattened array, as well as layout and
     * white-listed computed style information for the nodes. Shadow DOM in the returned DOM tree is
     * flattened.
     * 
     * @param computedStyleWhitelist Whitelist of computed styles to return.
     * @param includeEventListeners Whether or not to retrieve details of DOM listeners (default false).
     * @param includePaintOrder Whether to determine and include the paint order index of LayoutTreeNodes (default false).
     * @param includeUserAgentShadowTree Whether to include UA shadow tree in the snapshot (default false).
     * 
     * @return GetSnapshotResult
     */
    GetSnapshotResult getSnapshot(List<String> computedStyleWhitelist,
            @Optional Boolean includeEventListeners, @Optional Boolean includePaintOrder,
            @Optional Boolean includeUserAgentShadowTree);

    /**
     * Returns a document snapshot, including the full DOM tree of the root node (including iframes,
     * template contents, and imported documents) in a flattened array, as well as layout and
     * white-listed computed style information for the nodes. Shadow DOM in the returned DOM tree is
     * flattened.
     * 
     * @param computedStyles Whitelist of computed styles to return.
     * 
     * @return CaptureSnapshotResult
     */
    CaptureSnapshotResult captureSnapshot(List<String> computedStyles);

    /**
     * Returns a document snapshot, including the full DOM tree of the root node (including iframes,
     * template contents, and imported documents) in a flattened array, as well as layout and
     * white-listed computed style information for the nodes. Shadow DOM in the returned DOM tree is
     * flattened.
     * 
     * @param computedStyleWhitelist Whitelist of computed styles to return.
     * 
     * @return GetSnapshotResult
     */
    GetSnapshotResult getSnapshot(List<String> computedStyleWhitelist);
}
