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
     * 
     * @return Id assigned to the session.
     */
    @Returns("sessionId")
    String attachToTarget(String targetId, @Experimental @Optional Boolean flatten);

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
     * Injected object will be available as <code>window[bindingName]</code>.
     *
     * The object has the follwing API:
     * - <code>binding.send(json)</code> - a method to send messages over the remote debugging protocol
     * - <code>binding.onmessage = json => handleMessage(json)</code> - a callback that will be called for the protocol notifications and command responses.
     * 
     * @param bindingName Binding name, 'cdp' if not specified.
     */
    @Experimental
    void exposeDevToolsProtocol(String targetId, @Optional String bindingName);

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
     * Returns all browser contexts created with <code>Target.createBrowserContext</code> method.
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
     * 
     * @return The id of the page opened.
     */
    @Returns("targetId")
    String createTarget(String url, @Optional Integer width, @Optional Integer height,
            @Optional String browserContextId,
            @Experimental @Optional Boolean enableBeginFrameControl);

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
     * @param waitForDebuggerOnStart Whether to pause new targets when attaching to them. Use <code>Runtime.runIfWaitingForDebugger</code>
     * to run paused targets.
     */
    @Experimental
    void setAutoAttach(Boolean autoAttach, Boolean waitForDebuggerOnStart);

    /**
     * Controls whether to discover available targets and notify via
     * <code>targetCreated/targetInfoChanged/targetDestroyed</code> events.
     * 
     * @param discover Whether to discover available targets.
     */
    void setDiscoverTargets(Boolean discover);

    /**
     * Enables target discovery for the specified locations, when <code>setDiscoverTargets</code> was set to
     * <code>true</code>.
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
     * Injected object will be available as <code>window[bindingName]</code>.
     *
     * The object has the follwing API:
     * - <code>binding.send(json)</code> - a method to send messages over the remote debugging protocol
     * - <code>binding.onmessage = json => handleMessage(json)</code> - a callback that will be called for the protocol notifications and command responses.
     * 
     */
    @Experimental
    void exposeDevToolsProtocol(String targetId);

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
     * 
     */
    void sendMessageToTarget(String message);
}
