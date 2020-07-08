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
import io.webfolder.cdp.annotation.Returns;
import io.webfolder.cdp.type.target.RemoteLocation;
import io.webfolder.cdp.type.target.TargetInfo;
import java.util.List;

/**
 * Supports additional targets discovery and allows to attach to them
 */
@Domain("Target")
public interface Target {
    /**
     * Activates (focuses) the target.
     *
     */
    void activateTarget(String targetId);

    /**
     * Attaches to the target with given id.
     *
     * @param flatten Enables "flat" access to the session via specifying sessionId attribute in the commands.
     * We plan to make this the default, deprecate non-flattened mode,
     * and eventually retire it. See crbug.com/991325.
     *
     * @return Id assigned to the session.
     */
    @Returns("sessionId")
    String attachToTarget(String targetId, @Optional Boolean flatten);

    /**
     * Attaches to the browser target, only uses flat sessionId mode.
     *
     * @return Id assigned to the session.
     */
    @Experimental
    @Returns("sessionId")
    String attachToBrowserTarget();

    /**
     * Closes the target. If the target is a page that gets closed too.
     *
     */
    @Returns("success")
    Boolean closeTarget(String targetId);

    /**
     * Inject object to the target's main frame that provides a communication
     * channel with browser target.
     *
     * Injected object will be available as window[bindingName].
     *
     * The object has the follwing API:
     * - binding.send(json) - a method to send messages over the remote debugging protocol
     * - binding.onmessage = json => handleMessage(json) - a callback that will be called for the protocol notifications and command responses.
     *
     * @param bindingName Binding name, 'cdp' if not specified.
     */
    @Experimental
    void exposeDevToolsProtocol(String targetId, @Optional String bindingName);

    /**
     * Creates a new empty BrowserContext. Similar to an incognito profile but you can have more than
     * one.
     *
     * @param disposeOnDetach If specified, disposes this context when debugging session disconnects.
     * @param proxyServer Proxy server, similar to the one passed to --proxy-server
     * @param proxyBypassList Proxy bypass list, similar to the one passed to --proxy-bypass-list
     *
     * @return The id of the context created.
     */
    @Experimental
    @Returns("browserContextId")
    String createBrowserContext(@Optional Boolean disposeOnDetach, @Optional String proxyServer,
            @Optional String proxyBypassList);

    /**
     * Returns all browser contexts created with Target.createBrowserContext method.
     *
     * @return An array of browser context ids.
     */
    @Experimental
    @Returns("browserContextIds")
    List<String> getBrowserContexts();

    /**
     * Creates a new page.
     *
     * @param url The initial URL the page will be navigated to.
     * @param width Frame width in DIP (headless chrome only).
     * @param height Frame height in DIP (headless chrome only).
     * @param browserContextId The browser context to create the page in.
     * @param enableBeginFrameControl Whether BeginFrames for this target will be controlled via DevTools (headless chrome only,
     * not supported on MacOS yet, false by default).
     * @param newWindow Whether to create a new Window or Tab (chrome-only, false by default).
     * @param background Whether to create the target in background or foreground (chrome-only,
     * false by default).
     *
     * @return The id of the page opened.
     */
    @Returns("targetId")
    String createTarget(String url, @Optional Integer width, @Optional Integer height,
            @Optional String browserContextId,
            @Experimental @Optional Boolean enableBeginFrameControl, @Optional Boolean newWindow,
            @Optional Boolean background);

    /**
     * Detaches session with given id.
     *
     * @param sessionId Session to detach.
     * @param targetId Deprecated.
     */
    void detachFromTarget(@Optional String sessionId, @Optional String targetId);

    /**
     * Deletes a BrowserContext. All the belonging pages will be closed without calling their
     * beforeunload hooks.
     *
     */
    @Experimental
    void disposeBrowserContext(String browserContextId);

    /**
     * Returns information about a target.
     *
     */
    @Experimental
    @Returns("targetInfo")
    TargetInfo getTargetInfo(@Optional String targetId);

    /**
     * Retrieves a list of available targets.
     *
     * @return The list of targets.
     */
    @Returns("targetInfos")
    List<TargetInfo> getTargets();

    /**
     * Sends protocol message over session with given id.
     * Consider using flat mode instead; see commands attachToTarget, setAutoAttach,
     * and crbug.com/991325.
     *
     * @param sessionId Identifier of the session.
     * @param targetId Deprecated.
     */
    void sendMessageToTarget(String message, @Optional String sessionId, @Optional String targetId);

    /**
     * Controls whether to automatically attach to new targets which are considered to be related to
     * this one. When turned on, attaches to all existing related targets as well. When turned off,
     * automatically detaches from all currently attached targets.
     *
     * @param autoAttach Whether to auto-attach to related targets.
     * @param waitForDebuggerOnStart Whether to pause new targets when attaching to them. Use Runtime.runIfWaitingForDebugger
     * to run paused targets.
     * @param flatten Enables "flat" access to the session via specifying sessionId attribute in the commands.
     * We plan to make this the default, deprecate non-flattened mode,
     * and eventually retire it. See crbug.com/991325.
     */
    @Experimental
    void setAutoAttach(Boolean autoAttach, Boolean waitForDebuggerOnStart,
            @Optional Boolean flatten);

    /**
     * Controls whether to discover available targets and notify via
     * targetCreated/targetInfoChanged/targetDestroyed events.
     *
     * @param discover Whether to discover available targets.
     */
    void setDiscoverTargets(Boolean discover);

    /**
     * Enables target discovery for the specified locations, when setDiscoverTargets was set to
     * true.
     *
     * @param locations List of remote locations.
     */
    @Experimental
    void setRemoteLocations(List<RemoteLocation> locations);

    /**
     * Attaches to the target with given id.
     *
     *
     * @return Id assigned to the session.
     */
    @Returns("sessionId")
    String attachToTarget(String targetId);

    /**
     * Inject object to the target's main frame that provides a communication
     * channel with browser target.
     *
     * Injected object will be available as window[bindingName].
     *
     * The object has the follwing API:
     * - binding.send(json) - a method to send messages over the remote debugging protocol
     * - binding.onmessage = json => handleMessage(json) - a callback that will be called for the protocol notifications and command responses.
     *
     */
    @Experimental
    void exposeDevToolsProtocol(String targetId);

    /**
     * Creates a new empty BrowserContext. Similar to an incognito profile but you can have more than
     * one.
     *
     * @return The id of the context created.
     */
    @Experimental
    @Returns("browserContextId")
    String createBrowserContext();

    /**
     * Creates a new page.
     *
     * @param url The initial URL the page will be navigated to.
     *
     * @return The id of the page opened.
     */
    @Returns("targetId")
    String createTarget(String url);

    /**
     * Detaches session with given id.
     */
    void detachFromTarget();

    /**
     * Returns information about a target.
     */
    @Experimental
    @Returns("targetInfo")
    TargetInfo getTargetInfo();

    /**
     * Sends protocol message over session with given id.
     * Consider using flat mode instead; see commands attachToTarget, setAutoAttach,
     * and crbug.com/991325.
     *
     */
    void sendMessageToTarget(String message);

    /**
     * Controls whether to automatically attach to new targets which are considered to be related to
     * this one. When turned on, attaches to all existing related targets as well. When turned off,
     * automatically detaches from all currently attached targets.
     *
     * @param autoAttach Whether to auto-attach to related targets.
     * @param waitForDebuggerOnStart Whether to pause new targets when attaching to them. Use Runtime.runIfWaitingForDebugger
     * to run paused targets.
     */
    @Experimental
    void setAutoAttach(Boolean autoAttach, Boolean waitForDebuggerOnStart);
}
