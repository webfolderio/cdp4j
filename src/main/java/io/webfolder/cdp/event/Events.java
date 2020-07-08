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
package io.webfolder.cdp.event;

import io.webfolder.cdp.event.animation.AnimationCanceled;
import io.webfolder.cdp.event.animation.AnimationCreated;
import io.webfolder.cdp.event.animation.AnimationStarted;
import io.webfolder.cdp.event.applicationcache.ApplicationCacheStatusUpdated;
import io.webfolder.cdp.event.applicationcache.NetworkStateUpdated;
import io.webfolder.cdp.event.audits.IssueAdded;
import io.webfolder.cdp.event.backgroundservice.BackgroundServiceEventReceived;
import io.webfolder.cdp.event.backgroundservice.RecordingStateChanged;
import io.webfolder.cdp.event.cast.IssueUpdated;
import io.webfolder.cdp.event.cast.SinksUpdated;
import io.webfolder.cdp.event.console.MessageAdded;
import io.webfolder.cdp.event.css.FontsUpdated;
import io.webfolder.cdp.event.css.MediaQueryResultChanged;
import io.webfolder.cdp.event.css.StyleSheetAdded;
import io.webfolder.cdp.event.css.StyleSheetChanged;
import io.webfolder.cdp.event.css.StyleSheetRemoved;
import io.webfolder.cdp.event.database.AddDatabase;
import io.webfolder.cdp.event.debugger.BreakpointResolved;
import io.webfolder.cdp.event.debugger.Paused;
import io.webfolder.cdp.event.debugger.Resumed;
import io.webfolder.cdp.event.debugger.ScriptFailedToParse;
import io.webfolder.cdp.event.debugger.ScriptParsed;
import io.webfolder.cdp.event.dom.AttributeModified;
import io.webfolder.cdp.event.dom.AttributeRemoved;
import io.webfolder.cdp.event.dom.CharacterDataModified;
import io.webfolder.cdp.event.dom.ChildNodeCountUpdated;
import io.webfolder.cdp.event.dom.ChildNodeInserted;
import io.webfolder.cdp.event.dom.ChildNodeRemoved;
import io.webfolder.cdp.event.dom.DistributedNodesUpdated;
import io.webfolder.cdp.event.dom.DocumentUpdated;
import io.webfolder.cdp.event.dom.InlineStyleInvalidated;
import io.webfolder.cdp.event.dom.PseudoElementAdded;
import io.webfolder.cdp.event.dom.PseudoElementRemoved;
import io.webfolder.cdp.event.dom.SetChildNodes;
import io.webfolder.cdp.event.dom.ShadowRootPopped;
import io.webfolder.cdp.event.dom.ShadowRootPushed;
import io.webfolder.cdp.event.domstorage.DomStorageItemAdded;
import io.webfolder.cdp.event.domstorage.DomStorageItemRemoved;
import io.webfolder.cdp.event.domstorage.DomStorageItemUpdated;
import io.webfolder.cdp.event.domstorage.DomStorageItemsCleared;
import io.webfolder.cdp.event.emulation.VirtualTimeBudgetExpired;
import io.webfolder.cdp.event.fetch.AuthRequired;
import io.webfolder.cdp.event.fetch.RequestPaused;
import io.webfolder.cdp.event.headlessexperimental.NeedsBeginFramesChanged;
import io.webfolder.cdp.event.heapprofiler.AddHeapSnapshotChunk;
import io.webfolder.cdp.event.heapprofiler.HeapStatsUpdate;
import io.webfolder.cdp.event.heapprofiler.LastSeenObjectId;
import io.webfolder.cdp.event.heapprofiler.ReportHeapSnapshotProgress;
import io.webfolder.cdp.event.heapprofiler.ResetProfiles;
import io.webfolder.cdp.event.inspector.Detached;
import io.webfolder.cdp.event.inspector.TargetCrashed;
import io.webfolder.cdp.event.inspector.TargetReloadedAfterCrash;
import io.webfolder.cdp.event.layertree.LayerPainted;
import io.webfolder.cdp.event.layertree.LayerTreeDidChange;
import io.webfolder.cdp.event.log.EntryAdded;
import io.webfolder.cdp.event.media.PlayerErrorsRaised;
import io.webfolder.cdp.event.media.PlayerEventsAdded;
import io.webfolder.cdp.event.media.PlayerMessagesLogged;
import io.webfolder.cdp.event.media.PlayerPropertiesChanged;
import io.webfolder.cdp.event.media.PlayersCreated;
import io.webfolder.cdp.event.network.DataReceived;
import io.webfolder.cdp.event.network.EventSourceMessageReceived;
import io.webfolder.cdp.event.network.LoadingFailed;
import io.webfolder.cdp.event.network.LoadingFinished;
import io.webfolder.cdp.event.network.RequestIntercepted;
import io.webfolder.cdp.event.network.RequestServedFromCache;
import io.webfolder.cdp.event.network.RequestWillBeSent;
import io.webfolder.cdp.event.network.RequestWillBeSentExtraInfo;
import io.webfolder.cdp.event.network.ResourceChangedPriority;
import io.webfolder.cdp.event.network.ResponseReceived;
import io.webfolder.cdp.event.network.ResponseReceivedExtraInfo;
import io.webfolder.cdp.event.network.SignedExchangeReceived;
import io.webfolder.cdp.event.network.WebSocketClosed;
import io.webfolder.cdp.event.network.WebSocketCreated;
import io.webfolder.cdp.event.network.WebSocketFrameError;
import io.webfolder.cdp.event.network.WebSocketFrameReceived;
import io.webfolder.cdp.event.network.WebSocketFrameSent;
import io.webfolder.cdp.event.network.WebSocketHandshakeResponseReceived;
import io.webfolder.cdp.event.network.WebSocketWillSendHandshakeRequest;
import io.webfolder.cdp.event.overlay.InspectModeCanceled;
import io.webfolder.cdp.event.overlay.InspectNodeRequested;
import io.webfolder.cdp.event.overlay.NodeHighlightRequested;
import io.webfolder.cdp.event.overlay.ScreenshotRequested;
import io.webfolder.cdp.event.page.CompilationCacheProduced;
import io.webfolder.cdp.event.page.DomContentEventFired;
import io.webfolder.cdp.event.page.DownloadProgress;
import io.webfolder.cdp.event.page.DownloadWillBegin;
import io.webfolder.cdp.event.page.FileChooserOpened;
import io.webfolder.cdp.event.page.FrameAttached;
import io.webfolder.cdp.event.page.FrameClearedScheduledNavigation;
import io.webfolder.cdp.event.page.FrameDetached;
import io.webfolder.cdp.event.page.FrameNavigated;
import io.webfolder.cdp.event.page.FrameRequestedNavigation;
import io.webfolder.cdp.event.page.FrameResized;
import io.webfolder.cdp.event.page.FrameScheduledNavigation;
import io.webfolder.cdp.event.page.FrameStartedLoading;
import io.webfolder.cdp.event.page.FrameStoppedLoading;
import io.webfolder.cdp.event.page.InterstitialHidden;
import io.webfolder.cdp.event.page.InterstitialShown;
import io.webfolder.cdp.event.page.JavascriptDialogClosed;
import io.webfolder.cdp.event.page.JavascriptDialogOpening;
import io.webfolder.cdp.event.page.LifecycleEvent;
import io.webfolder.cdp.event.page.LoadEventFired;
import io.webfolder.cdp.event.page.NavigatedWithinDocument;
import io.webfolder.cdp.event.page.ScreencastFrame;
import io.webfolder.cdp.event.page.ScreencastVisibilityChanged;
import io.webfolder.cdp.event.page.WindowOpen;
import io.webfolder.cdp.event.performance.Metrics;
import io.webfolder.cdp.event.profiler.ConsoleProfileFinished;
import io.webfolder.cdp.event.profiler.ConsoleProfileStarted;
import io.webfolder.cdp.event.profiler.PreciseCoverageDeltaUpdate;
import io.webfolder.cdp.event.runtime.BindingCalled;
import io.webfolder.cdp.event.runtime.ConsoleAPICalled;
import io.webfolder.cdp.event.runtime.ExceptionRevoked;
import io.webfolder.cdp.event.runtime.ExceptionThrown;
import io.webfolder.cdp.event.runtime.ExecutionContextCreated;
import io.webfolder.cdp.event.runtime.ExecutionContextDestroyed;
import io.webfolder.cdp.event.runtime.ExecutionContextsCleared;
import io.webfolder.cdp.event.runtime.InspectRequested;
import io.webfolder.cdp.event.security.CertificateError;
import io.webfolder.cdp.event.security.SecurityStateChanged;
import io.webfolder.cdp.event.security.VisibleSecurityStateChanged;
import io.webfolder.cdp.event.serviceworker.WorkerErrorReported;
import io.webfolder.cdp.event.serviceworker.WorkerRegistrationUpdated;
import io.webfolder.cdp.event.serviceworker.WorkerVersionUpdated;
import io.webfolder.cdp.event.storage.CacheStorageContentUpdated;
import io.webfolder.cdp.event.storage.CacheStorageListUpdated;
import io.webfolder.cdp.event.storage.IndexedDBContentUpdated;
import io.webfolder.cdp.event.storage.IndexedDBListUpdated;
import io.webfolder.cdp.event.target.AttachedToTarget;
import io.webfolder.cdp.event.target.DetachedFromTarget;
import io.webfolder.cdp.event.target.ReceivedMessageFromTarget;
import io.webfolder.cdp.event.target.TargetCreated;
import io.webfolder.cdp.event.target.TargetDestroyed;
import io.webfolder.cdp.event.target.TargetInfoChanged;
import io.webfolder.cdp.event.tethering.Accepted;
import io.webfolder.cdp.event.tracing.BufferUsage;
import io.webfolder.cdp.event.tracing.DataCollected;
import io.webfolder.cdp.event.tracing.TracingComplete;
import io.webfolder.cdp.event.webaudio.AudioListenerCreated;
import io.webfolder.cdp.event.webaudio.AudioListenerWillBeDestroyed;
import io.webfolder.cdp.event.webaudio.AudioNodeCreated;
import io.webfolder.cdp.event.webaudio.AudioNodeWillBeDestroyed;
import io.webfolder.cdp.event.webaudio.AudioParamCreated;
import io.webfolder.cdp.event.webaudio.AudioParamWillBeDestroyed;
import io.webfolder.cdp.event.webaudio.ContextChanged;
import io.webfolder.cdp.event.webaudio.ContextCreated;
import io.webfolder.cdp.event.webaudio.ContextWillBeDestroyed;
import io.webfolder.cdp.event.webaudio.NodeParamConnected;
import io.webfolder.cdp.event.webaudio.NodeParamDisconnected;
import io.webfolder.cdp.event.webaudio.NodesConnected;
import io.webfolder.cdp.event.webaudio.NodesDisconnected;

public enum Events {
  /**
   * Event for when an animation has been cancelled
   */
  AnimationAnimationCanceled("Animation", "animationCanceled", AnimationCanceled.class),

  /**
   * Event for each animation that has been created
   */
  AnimationAnimationCreated("Animation", "animationCreated", AnimationCreated.class),

  /**
   * Event for animation that has been started
   */
  AnimationAnimationStarted("Animation", "animationStarted", AnimationStarted.class),

  ApplicationCacheApplicationCacheStatusUpdated("ApplicationCache", "applicationCacheStatusUpdated", ApplicationCacheStatusUpdated.class),

  ApplicationCacheNetworkStateUpdated("ApplicationCache", "networkStateUpdated", NetworkStateUpdated.class),

  AuditsIssueAdded("Audits", "issueAdded", IssueAdded.class),

  /**
   * Called when the recording state for the service has been updated
   */
  BackgroundServiceRecordingStateChanged("BackgroundService", "recordingStateChanged", RecordingStateChanged.class),

  /**
   * Called with all existing backgroundServiceEvents when enabled, and all new
   * events afterwards if enabled and recording
   */
  BackgroundServiceBackgroundServiceEventReceived("BackgroundService", "backgroundServiceEventReceived", BackgroundServiceEventReceived.class),

  /**
   * Fires whenever a web font is updated
   * A non-empty font parameter indicates a successfully loaded
   * web font
   */
  CSSFontsUpdated("CSS", "fontsUpdated", FontsUpdated.class),

  /**
   * Fires whenever a MediaQuery result changes (for example, after a browser window has been
   * resized
   * ) The current implementation considers only viewport-dependent media features
   */
  CSSMediaQueryResultChanged("CSS", "mediaQueryResultChanged", MediaQueryResultChanged.class),

  /**
   * Fired whenever an active document stylesheet is added
   */
  CSSStyleSheetAdded("CSS", "styleSheetAdded", StyleSheetAdded.class),

  /**
   * Fired whenever a stylesheet is changed as a result of the client operation
   */
  CSSStyleSheetChanged("CSS", "styleSheetChanged", StyleSheetChanged.class),

  /**
   * Fired whenever an active document stylesheet is removed
   */
  CSSStyleSheetRemoved("CSS", "styleSheetRemoved", StyleSheetRemoved.class),

  /**
   * This is fired whenever the list of available sinks changes
   * A sink is a
   * device or a software surface that you can cast to
   */
  CastSinksUpdated("Cast", "sinksUpdated", SinksUpdated.class),

  /**
   * This is fired whenever the outstanding issue/error message changes
   * |issueMessage| is empty if there is no issue
   */
  CastIssueUpdated("Cast", "issueUpdated", IssueUpdated.class),

  /**
   * Fired when Element's attribute is modified
   */
  DOMAttributeModified("DOM", "attributeModified", AttributeModified.class),

  /**
   * Fired when Element's attribute is removed
   */
  DOMAttributeRemoved("DOM", "attributeRemoved", AttributeRemoved.class),

  /**
   * Mirrors DOMCharacterDataModified event
   */
  DOMCharacterDataModified("DOM", "characterDataModified", CharacterDataModified.class),

  /**
   * Fired when Container's child node count has changed
   */
  DOMChildNodeCountUpdated("DOM", "childNodeCountUpdated", ChildNodeCountUpdated.class),

  /**
   * Mirrors DOMNodeInserted event
   */
  DOMChildNodeInserted("DOM", "childNodeInserted", ChildNodeInserted.class),

  /**
   * Mirrors DOMNodeRemoved event
   */
  DOMChildNodeRemoved("DOM", "childNodeRemoved", ChildNodeRemoved.class),

  /**
   * Called when distrubution is changed
   */
  DOMDistributedNodesUpdated("DOM", "distributedNodesUpdated", DistributedNodesUpdated.class),

  /**
   * Fired when Document has been totally updated
   * Node ids are no longer valid
   */
  DOMDocumentUpdated("DOM", "documentUpdated", DocumentUpdated.class),

  /**
   * Fired when Element's inline style is modified via a CSS property modification
   */
  DOMInlineStyleInvalidated("DOM", "inlineStyleInvalidated", InlineStyleInvalidated.class),

  /**
   * Called when a pseudo element is added to an element
   */
  DOMPseudoElementAdded("DOM", "pseudoElementAdded", PseudoElementAdded.class),

  /**
   * Called when a pseudo element is removed from an element
   */
  DOMPseudoElementRemoved("DOM", "pseudoElementRemoved", PseudoElementRemoved.class),

  /**
   * Fired when backend wants to provide client with the missing DOM structure
   * This happens upon
   * most of the calls requesting node ids
   */
  DOMSetChildNodes("DOM", "setChildNodes", SetChildNodes.class),

  /**
   * Called when shadow root is popped from the element
   */
  DOMShadowRootPopped("DOM", "shadowRootPopped", ShadowRootPopped.class),

  /**
   * Called when shadow root is pushed into the element
   */
  DOMShadowRootPushed("DOM", "shadowRootPushed", ShadowRootPushed.class),

  DOMStorageDomStorageItemAdded("DOMStorage", "domStorageItemAdded", DomStorageItemAdded.class),

  DOMStorageDomStorageItemRemoved("DOMStorage", "domStorageItemRemoved", DomStorageItemRemoved.class),

  DOMStorageDomStorageItemUpdated("DOMStorage", "domStorageItemUpdated", DomStorageItemUpdated.class),

  DOMStorageDomStorageItemsCleared("DOMStorage", "domStorageItemsCleared", DomStorageItemsCleared.class),

  DatabaseAddDatabase("Database", "addDatabase", AddDatabase.class),

  /**
   * Notification sent after the virtual time budget for the current VirtualTimePolicy has run out
   */
  EmulationVirtualTimeBudgetExpired("Emulation", "virtualTimeBudgetExpired", VirtualTimeBudgetExpired.class),

  /**
   * Issued when the target starts or stops needing BeginFrames
   * Deprecated
   * Issue beginFrame unconditionally instead and use result from
   * beginFrame to detect whether the frames were suppressed
   */
  HeadlessExperimentalNeedsBeginFramesChanged("HeadlessExperimental", "needsBeginFramesChanged", NeedsBeginFramesChanged.class),

  /**
   * Fired when remote debugging connection is about to be terminated
   * Contains detach reason
   */
  InspectorDetached("Inspector", "detached", Detached.class),

  /**
   * Fired when debugging target has crashed
   */
  InspectorTargetCrashed("Inspector", "targetCrashed", TargetCrashed.class),

  /**
   * Fired when debugging target has reloaded after crash
   */
  InspectorTargetReloadedAfterCrash("Inspector", "targetReloadedAfterCrash", TargetReloadedAfterCrash.class),

  LayerTreeLayerPainted("LayerTree", "layerPainted", LayerPainted.class),

  LayerTreeLayerTreeDidChange("LayerTree", "layerTreeDidChange", LayerTreeDidChange.class),

  /**
   * Issued when new message was logged
   */
  LogEntryAdded("Log", "entryAdded", EntryAdded.class),

  /**
   * Fired when data chunk was received over the network
   */
  NetworkDataReceived("Network", "dataReceived", DataReceived.class),

  /**
   * Fired when EventSource message is received
   */
  NetworkEventSourceMessageReceived("Network", "eventSourceMessageReceived", EventSourceMessageReceived.class),

  /**
   * Fired when HTTP request has failed to load
   */
  NetworkLoadingFailed("Network", "loadingFailed", LoadingFailed.class),

  /**
   * Fired when HTTP request has finished loading
   */
  NetworkLoadingFinished("Network", "loadingFinished", LoadingFinished.class),

  /**
   * Details of an intercepted HTTP request, which must be either allowed, blocked, modified or
   * mocked
   * Deprecated, use Fetch
   * requestPaused instead
   */
  NetworkRequestIntercepted("Network", "requestIntercepted", RequestIntercepted.class),

  /**
   * Fired if request ended up loading from cache
   */
  NetworkRequestServedFromCache("Network", "requestServedFromCache", RequestServedFromCache.class),

  /**
   * Fired when page is about to send HTTP request
   */
  NetworkRequestWillBeSent("Network", "requestWillBeSent", RequestWillBeSent.class),

  /**
   * Fired when resource loading priority is changed
   */
  NetworkResourceChangedPriority("Network", "resourceChangedPriority", ResourceChangedPriority.class),

  /**
   * Fired when a signed exchange was received over the network
   */
  NetworkSignedExchangeReceived("Network", "signedExchangeReceived", SignedExchangeReceived.class),

  /**
   * Fired when HTTP response is available
   */
  NetworkResponseReceived("Network", "responseReceived", ResponseReceived.class),

  /**
   * Fired when WebSocket is closed
   */
  NetworkWebSocketClosed("Network", "webSocketClosed", WebSocketClosed.class),

  /**
   * Fired upon WebSocket creation
   */
  NetworkWebSocketCreated("Network", "webSocketCreated", WebSocketCreated.class),

  /**
   * Fired when WebSocket message error occurs
   */
  NetworkWebSocketFrameError("Network", "webSocketFrameError", WebSocketFrameError.class),

  /**
   * Fired when WebSocket message is received
   */
  NetworkWebSocketFrameReceived("Network", "webSocketFrameReceived", WebSocketFrameReceived.class),

  /**
   * Fired when WebSocket message is sent
   */
  NetworkWebSocketFrameSent("Network", "webSocketFrameSent", WebSocketFrameSent.class),

  /**
   * Fired when WebSocket handshake response becomes available
   */
  NetworkWebSocketHandshakeResponseReceived("Network", "webSocketHandshakeResponseReceived", WebSocketHandshakeResponseReceived.class),

  /**
   * Fired when WebSocket is about to initiate handshake
   */
  NetworkWebSocketWillSendHandshakeRequest("Network", "webSocketWillSendHandshakeRequest", WebSocketWillSendHandshakeRequest.class),

  /**
   * Fired when additional information about a requestWillBeSent event is available from the
   * network stack
   * Not every requestWillBeSent event will have an additional
   * requestWillBeSentExtraInfo fired for it, and there is no guarantee whether requestWillBeSent
   * or requestWillBeSentExtraInfo will be fired first for the same request
   */
  NetworkRequestWillBeSentExtraInfo("Network", "requestWillBeSentExtraInfo", RequestWillBeSentExtraInfo.class),

  /**
   * Fired when additional information about a responseReceived event is available from the network
   * stack
   * Not every responseReceived event will have an additional responseReceivedExtraInfo for
   * it, and responseReceivedExtraInfo may be fired before or after responseReceived
   */
  NetworkResponseReceivedExtraInfo("Network", "responseReceivedExtraInfo", ResponseReceivedExtraInfo.class),

  /**
   * Fired when the node should be inspected
   * This happens after call to setInspectMode or when
   * user manually inspects an element
   */
  OverlayInspectNodeRequested("Overlay", "inspectNodeRequested", InspectNodeRequested.class),

  /**
   * Fired when the node should be highlighted
   * This happens after call to setInspectMode
   */
  OverlayNodeHighlightRequested("Overlay", "nodeHighlightRequested", NodeHighlightRequested.class),

  /**
   * Fired when user asks to capture screenshot of some area on the page
   */
  OverlayScreenshotRequested("Overlay", "screenshotRequested", ScreenshotRequested.class),

  /**
   * Fired when user cancels the inspect mode
   */
  OverlayInspectModeCanceled("Overlay", "inspectModeCanceled", InspectModeCanceled.class),

  PageDomContentEventFired("Page", "domContentEventFired", DomContentEventFired.class),

  /**
   * Emitted only when page
   * interceptFileChooser is enabled
   */
  PageFileChooserOpened("Page", "fileChooserOpened", FileChooserOpened.class),

  /**
   * Fired when frame has been attached to its parent
   */
  PageFrameAttached("Page", "frameAttached", FrameAttached.class),

  /**
   * Fired when frame no longer has a scheduled navigation
   */
  PageFrameClearedScheduledNavigation("Page", "frameClearedScheduledNavigation", FrameClearedScheduledNavigation.class),

  /**
   * Fired when frame has been detached from its parent
   */
  PageFrameDetached("Page", "frameDetached", FrameDetached.class),

  /**
   * Fired once navigation of the frame has completed
   * Frame is now associated with the new loader
   */
  PageFrameNavigated("Page", "frameNavigated", FrameNavigated.class),

  PageFrameResized("Page", "frameResized", FrameResized.class),

  /**
   * Fired when a renderer-initiated navigation is requested
   * Navigation may still be cancelled after the event is issued
   */
  PageFrameRequestedNavigation("Page", "frameRequestedNavigation", FrameRequestedNavigation.class),

  /**
   * Fired when frame schedules a potential navigation
   */
  PageFrameScheduledNavigation("Page", "frameScheduledNavigation", FrameScheduledNavigation.class),

  /**
   * Fired when frame has started loading
   */
  PageFrameStartedLoading("Page", "frameStartedLoading", FrameStartedLoading.class),

  /**
   * Fired when frame has stopped loading
   */
  PageFrameStoppedLoading("Page", "frameStoppedLoading", FrameStoppedLoading.class),

  /**
   * Fired when page is about to start a download
   */
  PageDownloadWillBegin("Page", "downloadWillBegin", DownloadWillBegin.class),

  /**
   * Fired when download makes progress
   * Last call has |done| == true
   */
  PageDownloadProgress("Page", "downloadProgress", DownloadProgress.class),

  /**
   * Fired when interstitial page was hidden
   */
  PageInterstitialHidden("Page", "interstitialHidden", InterstitialHidden.class),

  /**
   * Fired when interstitial page was shown
   */
  PageInterstitialShown("Page", "interstitialShown", InterstitialShown.class),

  /**
   * Fired when a JavaScript initiated dialog (alert, confirm, prompt, or onbeforeunload) has been
   * closed
   */
  PageJavascriptDialogClosed("Page", "javascriptDialogClosed", JavascriptDialogClosed.class),

  /**
   * Fired when a JavaScript initiated dialog (alert, confirm, prompt, or onbeforeunload) is about to
   * open
   */
  PageJavascriptDialogOpening("Page", "javascriptDialogOpening", JavascriptDialogOpening.class),

  /**
   * Fired for top level page lifecycle events such as navigation, load, paint, etc
   */
  PageLifecycleEvent("Page", "lifecycleEvent", LifecycleEvent.class),

  PageLoadEventFired("Page", "loadEventFired", LoadEventFired.class),

  /**
   * Fired when same-document navigation happens, e
   * g
   * due to history API usage or anchor navigation
   */
  PageNavigatedWithinDocument("Page", "navigatedWithinDocument", NavigatedWithinDocument.class),

  /**
   * Compressed image data requested by the startScreencast
   */
  PageScreencastFrame("Page", "screencastFrame", ScreencastFrame.class),

  /**
   * Fired when the page with currently enabled screencast was shown or hidden 
   */
  PageScreencastVisibilityChanged("Page", "screencastVisibilityChanged", ScreencastVisibilityChanged.class),

  /**
   * Fired when a new window is going to be opened, via window
   * open(), link click, form submission,
   * etc
   */
  PageWindowOpen("Page", "windowOpen", WindowOpen.class),

  /**
   * Issued for every compilation cache generated
   * Is only available
   * if Page
   * setGenerateCompilationCache is enabled
   */
  PageCompilationCacheProduced("Page", "compilationCacheProduced", CompilationCacheProduced.class),

  /**
   * Current values of the metrics
   */
  PerformanceMetrics("Performance", "metrics", Metrics.class),

  /**
   * There is a certificate error
   * If overriding certificate errors is enabled, then it should be
   * handled with the handleCertificateError command
   * Note: this event does not fire if the
   * certificate error has been allowed internally
   * Only one client per target should override
   * certificate errors at the same time
   */
  SecurityCertificateError("Security", "certificateError", CertificateError.class),

  /**
   * The security state of the page changed
   */
  SecurityVisibleSecurityStateChanged("Security", "visibleSecurityStateChanged", VisibleSecurityStateChanged.class),

  /**
   * The security state of the page changed
   */
  SecuritySecurityStateChanged("Security", "securityStateChanged", SecurityStateChanged.class),

  ServiceWorkerWorkerErrorReported("ServiceWorker", "workerErrorReported", WorkerErrorReported.class),

  ServiceWorkerWorkerRegistrationUpdated("ServiceWorker", "workerRegistrationUpdated", WorkerRegistrationUpdated.class),

  ServiceWorkerWorkerVersionUpdated("ServiceWorker", "workerVersionUpdated", WorkerVersionUpdated.class),

  /**
   * A cache's contents have been modified
   */
  StorageCacheStorageContentUpdated("Storage", "cacheStorageContentUpdated", CacheStorageContentUpdated.class),

  /**
   * A cache has been added/deleted
   */
  StorageCacheStorageListUpdated("Storage", "cacheStorageListUpdated", CacheStorageListUpdated.class),

  /**
   * The origin's IndexedDB object store has been modified
   */
  StorageIndexedDBContentUpdated("Storage", "indexedDBContentUpdated", IndexedDBContentUpdated.class),

  /**
   * The origin's IndexedDB database list has been modified
   */
  StorageIndexedDBListUpdated("Storage", "indexedDBListUpdated", IndexedDBListUpdated.class),

  /**
   * Issued when attached to target because of auto-attach or attachToTarget command
   */
  TargetAttachedToTarget("Target", "attachedToTarget", AttachedToTarget.class),

  /**
   * Issued when detached from target for any reason (including detachFromTarget command)
   * Can be
   * issued multiple times per target if multiple sessions have been attached to it
   */
  TargetDetachedFromTarget("Target", "detachedFromTarget", DetachedFromTarget.class),

  /**
   * Notifies about a new protocol message received from the session (as reported in
   * attachedToTarget event)
   */
  TargetReceivedMessageFromTarget("Target", "receivedMessageFromTarget", ReceivedMessageFromTarget.class),

  /**
   * Issued when a possible inspection target is created
   */
  TargetTargetCreated("Target", "targetCreated", TargetCreated.class),

  /**
   * Issued when a target is destroyed
   */
  TargetTargetDestroyed("Target", "targetDestroyed", TargetDestroyed.class),

  /**
   * Issued when a target has crashed
   */
  TargetTargetCrashed("Target", "targetCrashed", io.webfolder.cdp.event.target.TargetCrashed.class),

  /**
   * Issued when some information about a target has changed
   * This only happens between
   * targetCreated and targetDestroyed
   */
  TargetTargetInfoChanged("Target", "targetInfoChanged", TargetInfoChanged.class),

  /**
   * Informs that port was successfully bound and got a specified connection id
   */
  TetheringAccepted("Tethering", "accepted", Accepted.class),

  TracingBufferUsage("Tracing", "bufferUsage", BufferUsage.class),

  /**
   * Contains an bucket of collected trace events
   * When tracing is stopped collected events will be
   * send as a sequence of dataCollected events followed by tracingComplete event
   */
  TracingDataCollected("Tracing", "dataCollected", DataCollected.class),

  /**
   * Signals that tracing is stopped and there is no trace buffers pending flush, all data were
   * delivered via dataCollected events
   */
  TracingTracingComplete("Tracing", "tracingComplete", TracingComplete.class),

  /**
   * Issued when the domain is enabled and the request URL matches the
   * specified filter
   * The request is paused until the client responds
   * with one of continueRequest, failRequest or fulfillRequest
   * The stage of the request can be determined by presence of responseErrorReason
   * and responseStatusCode -- the request is at the response stage if either
   * of these fields is present and in the request stage otherwise
   */
  FetchRequestPaused("Fetch", "requestPaused", RequestPaused.class),

  /**
   * Issued when the domain is enabled with handleAuthRequests set to true
   * The request is paused until client responds with continueWithAuth
   */
  FetchAuthRequired("Fetch", "authRequired", AuthRequired.class),

  /**
   * Notifies that a new BaseAudioContext has been created
   */
  WebAudioContextCreated("WebAudio", "contextCreated", ContextCreated.class),

  /**
   * Notifies that an existing BaseAudioContext will be destroyed
   */
  WebAudioContextWillBeDestroyed("WebAudio", "contextWillBeDestroyed", ContextWillBeDestroyed.class),

  /**
   * Notifies that existing BaseAudioContext has changed some properties (id stays the same)
   */
  WebAudioContextChanged("WebAudio", "contextChanged", ContextChanged.class),

  /**
   * Notifies that the construction of an AudioListener has finished
   */
  WebAudioAudioListenerCreated("WebAudio", "audioListenerCreated", AudioListenerCreated.class),

  /**
   * Notifies that a new AudioListener has been created
   */
  WebAudioAudioListenerWillBeDestroyed("WebAudio", "audioListenerWillBeDestroyed", AudioListenerWillBeDestroyed.class),

  /**
   * Notifies that a new AudioNode has been created
   */
  WebAudioAudioNodeCreated("WebAudio", "audioNodeCreated", AudioNodeCreated.class),

  /**
   * Notifies that an existing AudioNode has been destroyed
   */
  WebAudioAudioNodeWillBeDestroyed("WebAudio", "audioNodeWillBeDestroyed", AudioNodeWillBeDestroyed.class),

  /**
   * Notifies that a new AudioParam has been created
   */
  WebAudioAudioParamCreated("WebAudio", "audioParamCreated", AudioParamCreated.class),

  /**
   * Notifies that an existing AudioParam has been destroyed
   */
  WebAudioAudioParamWillBeDestroyed("WebAudio", "audioParamWillBeDestroyed", AudioParamWillBeDestroyed.class),

  /**
   * Notifies that two AudioNodes are connected
   */
  WebAudioNodesConnected("WebAudio", "nodesConnected", NodesConnected.class),

  /**
   * Notifies that AudioNodes are disconnected
   * The destination can be null, and it means all the outgoing connections from the source are disconnected
   */
  WebAudioNodesDisconnected("WebAudio", "nodesDisconnected", NodesDisconnected.class),

  /**
   * Notifies that an AudioNode is connected to an AudioParam
   */
  WebAudioNodeParamConnected("WebAudio", "nodeParamConnected", NodeParamConnected.class),

  /**
   * Notifies that an AudioNode is disconnected to an AudioParam
   */
  WebAudioNodeParamDisconnected("WebAudio", "nodeParamDisconnected", NodeParamDisconnected.class),

  /**
   * This can be called multiple times, and can be used to set / override /
   * remove player properties
   * A null propValue indicates removal
   */
  MediaPlayerPropertiesChanged("Media", "playerPropertiesChanged", PlayerPropertiesChanged.class),

  /**
   * Send events as a list, allowing them to be batched on the browser for less
   * congestion
   * If batched, events must ALWAYS be in chronological order
   */
  MediaPlayerEventsAdded("Media", "playerEventsAdded", PlayerEventsAdded.class),

  /**
   * Send a list of any messages that need to be delivered
   */
  MediaPlayerMessagesLogged("Media", "playerMessagesLogged", PlayerMessagesLogged.class),

  /**
   * Send a list of any errors that need to be delivered
   */
  MediaPlayerErrorsRaised("Media", "playerErrorsRaised", PlayerErrorsRaised.class),

  /**
   * Called whenever a player is created, or when a new agent joins and recieves
   * a list of active players
   * If an agent is restored, it will recieve the full
   * list of player ids and all events again
   */
  MediaPlayersCreated("Media", "playersCreated", PlayersCreated.class),

  /**
   * Issued when new console message is added
   */
  ConsoleMessageAdded("Console", "messageAdded", MessageAdded.class),

  /**
   * Fired when breakpoint is resolved to an actual script and location
   */
  DebuggerBreakpointResolved("Debugger", "breakpointResolved", BreakpointResolved.class),

  /**
   * Fired when the virtual machine stopped on breakpoint or exception or any other stop criteria
   */
  DebuggerPaused("Debugger", "paused", Paused.class),

  /**
   * Fired when the virtual machine resumed execution
   */
  DebuggerResumed("Debugger", "resumed", Resumed.class),

  /**
   * Fired when virtual machine fails to parse the script
   */
  DebuggerScriptFailedToParse("Debugger", "scriptFailedToParse", ScriptFailedToParse.class),

  /**
   * Fired when virtual machine parses script
   * This event is also fired for all known and uncollected
   * scripts upon enabling debugger
   */
  DebuggerScriptParsed("Debugger", "scriptParsed", ScriptParsed.class),

  HeapProfilerAddHeapSnapshotChunk("HeapProfiler", "addHeapSnapshotChunk", AddHeapSnapshotChunk.class),

  /**
   * If heap objects tracking has been started then backend may send update for one or more fragments
   */
  HeapProfilerHeapStatsUpdate("HeapProfiler", "heapStatsUpdate", HeapStatsUpdate.class),

  /**
   * If heap objects tracking has been started then backend regularly sends a current value for last
   * seen object id and corresponding timestamp
   * If the were changes in the heap since last event
   * then one or more heapStatsUpdate events will be sent before a new lastSeenObjectId event
   */
  HeapProfilerLastSeenObjectId("HeapProfiler", "lastSeenObjectId", LastSeenObjectId.class),

  HeapProfilerReportHeapSnapshotProgress("HeapProfiler", "reportHeapSnapshotProgress", ReportHeapSnapshotProgress.class),

  HeapProfilerResetProfiles("HeapProfiler", "resetProfiles", ResetProfiles.class),

  ProfilerConsoleProfileFinished("Profiler", "consoleProfileFinished", ConsoleProfileFinished.class),

  /**
   * Sent when new profile recording is started using console
   * profile() call
   */
  ProfilerConsoleProfileStarted("Profiler", "consoleProfileStarted", ConsoleProfileStarted.class),

  /**
   * Reports coverage delta since the last poll (either from an event like this, or from
   * takePreciseCoverage for the current isolate
   * May only be sent if precise code
   * coverage has been started
   * This event can be trigged by the embedder to, for example,
   * trigger collection of coverage data immediatelly at a certain point in time
   */
  ProfilerPreciseCoverageDeltaUpdate("Profiler", "preciseCoverageDeltaUpdate", PreciseCoverageDeltaUpdate.class),

  /**
   * Notification is issued every time when binding is called
   */
  RuntimeBindingCalled("Runtime", "bindingCalled", BindingCalled.class),

  /**
   * Issued when console API was called
   */
  RuntimeConsoleAPICalled("Runtime", "consoleAPICalled", ConsoleAPICalled.class),

  /**
   * Issued when unhandled exception was revoked
   */
  RuntimeExceptionRevoked("Runtime", "exceptionRevoked", ExceptionRevoked.class),

  /**
   * Issued when exception was thrown and unhandled
   */
  RuntimeExceptionThrown("Runtime", "exceptionThrown", ExceptionThrown.class),

  /**
   * Issued when new execution context is created
   */
  RuntimeExecutionContextCreated("Runtime", "executionContextCreated", ExecutionContextCreated.class),

  /**
   * Issued when execution context is destroyed
   */
  RuntimeExecutionContextDestroyed("Runtime", "executionContextDestroyed", ExecutionContextDestroyed.class),

  /**
   * Issued when all executionContexts were cleared in browser
   */
  RuntimeExecutionContextsCleared("Runtime", "executionContextsCleared", ExecutionContextsCleared.class),

  /**
   * Issued when object should be inspected (for example, as a result of inspect() command line API
   * call)
   */
  RuntimeInspectRequested("Runtime", "inspectRequested", InspectRequested.class);

  public final String domain;

  public final String name;

  public final Class<?> klass;

  Events(String domain, String name, Class<?> klass) {
    this.domain = domain;
    this.name = name;
    this.klass = klass;
  }

  @java.lang.Override
  public String toString() {
    return domain + "." + name;
  }
}

