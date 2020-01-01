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

import io.webfolder.cdp.session.SessionInvocationHandler;
import io.webfolder.cdp.command.DOMSnapshot;
import java.util.List;
import io.webfolder.cdp.type.domsnapshot.CaptureSnapshotResult;
import io.webfolder.cdp.type.domsnapshot.GetSnapshotResult;

public class DOMSnapshotImpl implements DOMSnapshot {

    private static final String[] EMPTY_ARGS = new String[] {};
    private static final Object[] EMPTY_VALUES = new Object[] {};
    private final SessionInvocationHandler handler;

    public DOMSnapshotImpl(SessionInvocationHandler handler) {
        this.handler = handler;
    }

    @Override
    public CaptureSnapshotResult captureSnapshot(List<String> computedStyles) {
        return (CaptureSnapshotResult) handler.invoke("DOMSnapshot", "captureSnapshot", "DOMSnapshot.captureSnapshot",
                null, CaptureSnapshotResult.class, null, false, false, false, new String[] { "computedStyles" },
                new Object[] { computedStyles });
    }

    @Override
    public CaptureSnapshotResult captureSnapshot(List<String> computedStyles, Boolean includeDOMRects) {
        return (CaptureSnapshotResult) handler.invoke("DOMSnapshot", "captureSnapshot", "DOMSnapshot.captureSnapshot",
                null, CaptureSnapshotResult.class, null, false, false, false,
                new String[] { "computedStyles", "includeDOMRects" }, new Object[] { computedStyles, includeDOMRects });
    }

    @Override
    public void disable() {
        handler.invoke("DOMSnapshot", "disable", "DOMSnapshot.disable", null, void.class, null, true, false, true,
                EMPTY_ARGS, EMPTY_VALUES);
    }

    @Override
    public void enable() {
        handler.invoke("DOMSnapshot", "enable", "DOMSnapshot.enable", null, void.class, null, true, true, false,
                EMPTY_ARGS, EMPTY_VALUES);
    }

    @Override
    public GetSnapshotResult getSnapshot(List<String> computedStyleWhitelist) {
        return (GetSnapshotResult) handler.invoke("DOMSnapshot", "getSnapshot", "DOMSnapshot.getSnapshot", null,
                GetSnapshotResult.class, null, false, false, false, new String[] { "computedStyleWhitelist" },
                new Object[] { computedStyleWhitelist });
    }

    @Override
    public GetSnapshotResult getSnapshot(List<String> computedStyleWhitelist, Boolean includeEventListeners,
            Boolean includePaintOrder, Boolean includeUserAgentShadowTree) {
        return (GetSnapshotResult) handler.invoke("DOMSnapshot", "getSnapshot", "DOMSnapshot.getSnapshot", null,
                GetSnapshotResult.class, null, false, false, false,
                new String[] { "computedStyleWhitelist", "includeEventListeners", "includePaintOrder",
                        "includeUserAgentShadowTree" },
                new Object[] { computedStyleWhitelist, includeEventListeners, includePaintOrder,
                        includeUserAgentShadowTree });
    }
}