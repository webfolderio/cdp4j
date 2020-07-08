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

import java.util.List;

import com.google.gson.reflect.TypeToken;

import io.webfolder.cdp.session.SessionInvocationHandler;
import io.webfolder.cdp.type.target.RemoteLocation;
import io.webfolder.cdp.type.target.TargetInfo;

public class TargetImpl implements Target {

    private static final Object[] EMPTY_VALUES = new Object[]{};
    private static final String[] EMPTY_ARGS = new String[]{};
    private final SessionInvocationHandler handler;
    private static final TypeToken<List<String>> GET_BROWSER_CONTEXTS = new TypeToken<List<String>>() { };
    private static final TypeToken<List<TargetInfo>> GET_TARGETS = new TypeToken<List<TargetInfo>>() { };

    public TargetImpl(SessionInvocationHandler handler) {
        this.handler = handler;
    }

    @Override
    public void activateTarget(String targetId) {
        handler.invoke("Target", "activateTarget", "Target.activateTarget", null, void.class, null, true, false, false,
                new String[]{"targetId"}, new Object[]{targetId});
    }

    @Override
    public String attachToTarget(String targetId, Boolean flatten) {
        return (String) handler.invoke("Target", "attachToTarget", "Target.attachToTarget", "sessionId", String.class,
                null, false, false, false, new String[]{"targetId", "flatten"}, new Object[]{targetId, flatten});
    }

    @Override
    public String attachToBrowserTarget() {
        return (String) handler.invoke("Target", "attachToBrowserTarget", "Target.attachToBrowserTarget", "sessionId",
                String.class, null, false, false, false, EMPTY_ARGS, EMPTY_VALUES);
    }

    @Override
    public Boolean closeTarget(String targetId) {
        return (Boolean) handler.invoke("Target", "closeTarget", "Target.closeTarget", "success", Boolean.class, null,
                false, false, false, new String[]{"targetId"}, new Object[]{targetId});
    }

    @Override
    public void exposeDevToolsProtocol(String targetId, String bindingName) {
        handler.invoke("Target", "exposeDevToolsProtocol", "Target.exposeDevToolsProtocol", null, void.class, null,
                true, false, false, new String[]{"targetId", "bindingName"}, new Object[]{targetId, bindingName});
    }

    @Override
    public String createBrowserContext(Boolean disposeOnDetach, String proxyServer, String proxyBypassList) {
        return (String) handler.invoke("Target", "createBrowserContext", "Target.createBrowserContext",
                "browserContextId", String.class, null, false, false, false,
                new String[]{"disposeOnDetach", "proxyServer", "proxyBypassList"},
                new Object[]{disposeOnDetach, proxyServer, proxyBypassList});
    }

    @Override
    @java.lang.SuppressWarnings("unchecked")
    public List<String> getBrowserContexts() {
        return (List<String>) handler.invoke("Target", "getBrowserContexts", "Target.getBrowserContexts",
                "browserContextIds", List.class, GET_BROWSER_CONTEXTS.getType(), false, false, false, EMPTY_ARGS,
                EMPTY_VALUES);
    }

    @Override
    public String createTarget(String url, Integer width, Integer height, String browserContextId,
            Boolean enableBeginFrameControl, Boolean newWindow, Boolean background) {
        return (String) handler.invoke("Target", "createTarget", "Target.createTarget", "targetId", String.class, null,
                false, false, false,
                new String[]{"url", "width", "height", "browserContextId", "enableBeginFrameControl", "newWindow",
                        "background"},
                new Object[]{url, width, height, browserContextId, enableBeginFrameControl, newWindow, background});
    }

    @Override
    public void detachFromTarget(String sessionId, String targetId) {
        handler.invoke("Target", "detachFromTarget", "Target.detachFromTarget", null, void.class, null, true, false,
                false, new String[]{"sessionId", "targetId"}, new Object[]{sessionId, targetId});
    }

    @Override
    public void disposeBrowserContext(String browserContextId) {
        handler.invoke("Target", "disposeBrowserContext", "Target.disposeBrowserContext", null, void.class, null, true,
                false, false, new String[]{"browserContextId"}, new Object[]{browserContextId});
    }

    @Override
    public TargetInfo getTargetInfo(String targetId) {
        return (TargetInfo) handler.invoke("Target", "getTargetInfo", "Target.getTargetInfo", "targetInfo",
                TargetInfo.class, null, false, false, false, new String[]{"targetId"}, new Object[]{targetId});
    }

    @Override
    @java.lang.SuppressWarnings("unchecked")
    public List<TargetInfo> getTargets() {
        return (List<TargetInfo>) handler.invoke("Target", "getTargets", "Target.getTargets", "targetInfos", List.class,
                GET_TARGETS.getType(), false, false, false, EMPTY_ARGS, EMPTY_VALUES);
    }

    @Override
    public void sendMessageToTarget(String message, String sessionId, String targetId) {
        handler.invoke("Target", "sendMessageToTarget", "Target.sendMessageToTarget", null, void.class, null, true,
                false, false, new String[]{"message", "sessionId", "targetId"},
                new Object[]{message, sessionId, targetId});
    }

    @Override
    public void setAutoAttach(Boolean autoAttach, Boolean waitForDebuggerOnStart, Boolean flatten) {
        handler.invoke("Target", "setAutoAttach", "Target.setAutoAttach", null, void.class, null, true, false, false,
                new String[]{"autoAttach", "waitForDebuggerOnStart", "flatten"},
                new Object[]{autoAttach, waitForDebuggerOnStart, flatten});
    }

    @Override
    public void setDiscoverTargets(Boolean discover) {
        handler.invoke("Target", "setDiscoverTargets", "Target.setDiscoverTargets", null, void.class, null, true, false,
                false, new String[]{"discover"}, new Object[]{discover});
    }

    @Override
    public void setRemoteLocations(List<RemoteLocation> locations) {
        handler.invoke("Target", "setRemoteLocations", "Target.setRemoteLocations", null, void.class, null, true, false,
                false, new String[]{"locations"}, new Object[]{locations});
    }

    @Override
    public String attachToTarget(String targetId) {
        return (String) handler.invoke("Target", "attachToTarget", "Target.attachToTarget", "sessionId", String.class,
                null, false, false, false, new String[]{"targetId"}, new Object[]{targetId});
    }

    @Override
    public void exposeDevToolsProtocol(String targetId) {
        handler.invoke("Target", "exposeDevToolsProtocol", "Target.exposeDevToolsProtocol", null, void.class, null,
                true, false, false, new String[]{"targetId"}, new Object[]{targetId});
    }

    @Override
    public String createBrowserContext() {
        return (String) handler.invoke("Target", "createBrowserContext", "Target.createBrowserContext",
                "browserContextId", String.class, null, false, false, false, EMPTY_ARGS, EMPTY_VALUES);
    }

    @Override
    public String createTarget(String url) {
        return (String) handler.invoke("Target", "createTarget", "Target.createTarget", "targetId", String.class, null,
                false, false, false, new String[]{"url"}, new Object[]{url});
    }

    @Override
    public void detachFromTarget() {
        handler.invoke("Target", "detachFromTarget", "Target.detachFromTarget", null, void.class, null, true, false,
                false, EMPTY_ARGS, EMPTY_VALUES);
    }

    @Override
    public TargetInfo getTargetInfo() {
        return (TargetInfo) handler.invoke("Target", "getTargetInfo", "Target.getTargetInfo", "targetInfo",
                TargetInfo.class, null, false, false, false, EMPTY_ARGS, EMPTY_VALUES);
    }

    @Override
    public void sendMessageToTarget(String message) {
        handler.invoke("Target", "sendMessageToTarget", "Target.sendMessageToTarget", null, void.class, null, true,
                false, false, new String[]{"message"}, new Object[]{message});
    }

    @Override
    public void setAutoAttach(Boolean autoAttach, Boolean waitForDebuggerOnStart) {
        handler.invoke("Target", "setAutoAttach", "Target.setAutoAttach", null, void.class, null, true, false, false,
                new String[]{"autoAttach", "waitForDebuggerOnStart"}, new Object[]{autoAttach, waitForDebuggerOnStart});
    }
}