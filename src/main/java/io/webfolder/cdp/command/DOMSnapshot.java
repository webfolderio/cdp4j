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
     * @param includeDOMRects Whether to include DOM rectangles (offsetRects, clientRects, scrollRects) into the snapshot
     * 
     * @return CaptureSnapshotResult
     */
    CaptureSnapshotResult captureSnapshot(List<String> computedStyles,
            @Optional Boolean includeDOMRects);

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
}
