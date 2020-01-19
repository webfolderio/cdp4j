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
package io.webfolder.cdp.session;

import static io.webfolder.cdp.CustomTypeAdapter.Generated;
import static io.webfolder.cdp.event.Events.RuntimeExecutionContextCreated;
import static io.webfolder.cdp.event.Events.RuntimeExecutionContextDestroyed;
import static java.lang.Boolean.TRUE;
import static java.util.Locale.ENGLISH;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicBoolean;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapterFactory;

import io.webfolder.cdp.CustomTypeAdapter;
import io.webfolder.cdp.Options;
import io.webfolder.cdp.Stag;
import io.webfolder.cdp.channel.Channel;
import io.webfolder.cdp.channel.ChannelFactory;
import io.webfolder.cdp.channel.Connection;
import io.webfolder.cdp.command.Target;
import io.webfolder.cdp.event.runtime.ExecutionContextCreated;
import io.webfolder.cdp.event.runtime.ExecutionContextDestroyed;
import io.webfolder.cdp.exception.CdpException;
import io.webfolder.cdp.listener.EventListener;
import io.webfolder.cdp.logger.CdpLoggerFactory;
import io.webfolder.cdp.logger.CdpLoggerType;
import io.webfolder.cdp.logger.LoggerFactory;
import io.webfolder.cdp.type.target.TargetInfo;

public class SessionFactory implements AutoCloseable {

    private final Gson gson;

    private final LoggerFactory loggerFactory;

    private final Options options;

    private final Map<String, Session> sessions = new ConcurrentHashMap<>();

    private final List<String> browserContexts = new CopyOnWriteArrayList<>();

    private final Channel channel;

    private volatile String browserTargetId;

    private volatile Session browserSession;

    private volatile Boolean headless;

    private volatile int majorVersion;

    private AtomicBoolean closed = new AtomicBoolean(false);

    private TypeAdapterFactory typeAdapterFactory;

    public SessionFactory(Options options, ChannelFactory channelFactory, Connection connection) {
        this(options, channelFactory, connection, true);
    }

    public SessionFactory(Options options, ChannelFactory channelFactory, Connection connection, boolean init) {
        this.options            = options;
        this.loggerFactory      = createLoggerFactory(options.loggerType());
        this.typeAdapterFactory = options.useCustomTypeAdapter() != null ? createTypeAdapterFactory(options.useCustomTypeAdapter()) : null;
        GsonBuilder builder     = new GsonBuilder().disableHtmlEscaping();
        if ( options.useCustomTypeAdapter() != null ) {
            this.gson = builder.registerTypeAdapterFactory(typeAdapterFactory)
                               .create();
        } else {
            this.gson = builder.create();
        }
        MessageHandler handler = new MessageHandler(gson, this,
                                                    options.workerThreadPool(), options.eventHandlerThreadPool(),
                                                    loggerFactory.getLogger("cdp4j.ws.response", options.consoleLoggerLevel()));
        channel = channelFactory.createChannel(connection, this, handler);
        if (init) {
            connect();
        }
    }

    public void connect() {
        if (browserTargetId == null) {
            channel.connect();
            browserTargetId = initBrowserSession();
        } else {
            throw new IllegalStateException();
        }
    }

    private TypeAdapterFactory createTypeAdapterFactory(CustomTypeAdapter adapter) {
        if (Generated.equals(adapter)) {
            return new Stag.Factory();
        }
        return new CdpTypeAdapterFactory();
    }

    /**
     * Creates a new page
     * 
     * @return this
     */
    public Session create() {
        return create(null, null);
    }

    /**
     * Creates a new page
     * 
     * @param settings
     * 
     * @return this
     */
    public Session create(SessionSettings settings) {
        return create(null, settings);
    }

    /**
     * Creates a new page
     * 
     * @param browserContextId incognito browser context id
     * 
     * @return this
     */
    public Session create(String browserContextId) {
        return create(browserContextId, null);
    }

    /**
     * Creates a new page
     * 
     * @param browserContextId incognito browser context id
     * @param settings
     * 
     * @return this
     */
    public Session create(String browserContextId, SessionSettings settings) {
        Session browserSession = getBrowserSession();
        Target target = browserSession.getCommand().getTarget();
        // Try to use blank page on first launch
        if (sessions.isEmpty() && browserContextId == null) {
            TargetInfo blankPage = null;
            List<TargetInfo> targets = target.getTargets();
            for (TargetInfo next : targets) {
                if (isEmptyTarget(next)) {
                    blankPage = next;
                    break;
                }
            }
            if ( blankPage != null ) {
                return connect(blankPage.getTargetId(), blankPage.getBrowserContextId());
            }
        }
        int width = settings != null ? settings.getScreenWidth() : options.screenWidth();
        int height = settings != null ? settings.getScreenHeight() : options.screenHeight();
        String targetId = target.createTarget("about:blank",
                                              width,
                                              height,
                                              browserContextId, false, null, null);
        if (targetId == null) {
            throw new CdpException("Couldn't create a new session");
        }
        return connect(targetId, browserContextId);
    }

    /**
     * Connect to existing session
     * 
     * @return this
     */
    public Session connect(String targetId) {
        return connect(targetId, null);
    }

    private boolean isEmptyTarget(TargetInfo targetInfo) {
        String url = targetInfo.getUrl();
        String type = targetInfo.getType();
        if ("page".equals(type) &&
                (url.isEmpty()                      ||
                    "about:blank".equals(url)       ||
                    "chrome://welcome/".equals(url) ||
                    "chrome://newtab/".equals(url)  ||
                    "edge://newtab/".equals(url)    ||
                    url.startsWith("chrome://welcome-win10"))) {
            return true;
        }
        return false;
    }

    Session connect(String targetId, String browserContextId) {
        Session bs = getBrowserSession();

        if (browserContextId == null) {
            TargetInfo found = null;
            List<TargetInfo> targets = bs.getCommand().getTarget().getTargets();
            for (TargetInfo next : targets) {
                if (next.getTargetId().equals(targetId)) {
                    found = next;
                }
            }
            if (found == null) {
                throw new CdpException("Target not found: " + targetId);
            }
            browserContextId = found.getBrowserContextId();
        }

        Target target = bs.getCommand().getTarget();
        String sessionId = target.attachToTarget(targetId, TRUE);

        Map<Integer, Context> contexts = new ConcurrentHashMap<>();
        List<EventListener> eventListeners = new CopyOnWriteArrayList<>();

        Session session = new Session(options, gson, sessionId,
                                      targetId, browserContextId,
                                      channel, contexts,
                                      this, eventListeners,
                                      loggerFactory);
        sessions.put(sessionId, session);

        session.getCommand().getRuntime().enable();

        session.addEventListener((event, value) -> {
            if (RuntimeExecutionContextCreated.equals(event)) {
                ExecutionContextCreated ecc = (ExecutionContextCreated) value;
                if (targetId.equals(ecc.getContext().getAuxData().get("frameId"))) {
                    session.setExecutionContextId(ecc.getContext().getId());
                }
            } else if (RuntimeExecutionContextDestroyed.equals(event)) {
                ExecutionContextDestroyed ecd = (ExecutionContextDestroyed) value;
                if ( ecd.getExecutionContextId() != null &&
                        ecd.getExecutionContextId().equals(session.getExecutionContextId()) ) {
                    session.setExecutionContextId(null);
                }
            }
        });

        Command command = session.getCommand();

        command.getPage().enable();
        command.getPage().setLifecycleEventsEnabled(true);
 
        return session;
    }

    private String initBrowserSession() {
        Map<Integer, Context> contexts = new ConcurrentHashMap<>();
        List<EventListener> eventlisteners = new CopyOnWriteArrayList<>();
        browserSession = new Session(options, gson, null,
                                     null, null,
                                     channel, contexts,
                                     this, eventlisteners,
                                     loggerFactory);
        browserSession.addEventListener(new TargetListener(sessions));        
        Target target = browserSession.getCommand().getTarget();
        target.setDiscoverTargets(TRUE);
        TargetInfo info = target.getTargetInfo();
        String targetId = info.getTargetId();
        return targetId;
    }

    Session getBrowserSession() {
        return browserSession;
    }

    Session getSession(String sessionId) {
        return sessions.get(sessionId);
    }

    void close(Session session) {
        session.getCommand()
               .getPage()
               .close();
        session.getCommand()
               .getTarget()
               .closeTarget(session.getTargetId());
        session.dispose();
        sessions.remove(session.getId());
    }

    /**
     * Get major version of the browser.
     * 
     * @return major version number
     */
    public int getMajorVersion() {
        if (majorVersion == 0) {
            String[] product = browserSession
                                        .getCommand()
                                        .getBrowser()
                                        .getVersion()
                                        .getProduct()
                                        .split("/");
            if (product.length == 2) {
                String[] version = product[1].split("\\.");
                if (version.length > 2) {
                    majorVersion = Integer.parseInt(version[0]);
                }
            }
        }
        return majorVersion;
    }

    /**
     * Closes all sessions.
     * 
     * The factory object itself is considered disposed and cannot be used anymore.
     */
    @Override
    public void close() {
        if (closed.compareAndSet(false, true)) {
            Target target = browserSession.getCommand().getTarget();
            if (channel.isOpen()) {
                for (String next : browserContexts) {
                    target.disposeBrowserContext(next);
                }
            }
            if ( browserSession != null ) {
                if (channel.isOpen()) {
                    target.closeTarget(browserTargetId);
                }
                browserSession.dispose();
            }
            channel.disconnect();
            sessions.clear();
            browserContexts.clear();
            if (options.shutdownThreadPoolOnClose()) {
                Executor wp = options.workerThreadPool();
                if (wp instanceof ExecutorService) {
                    ExecutorService wps = (ExecutorService) wp;
                    if ( ! wps.isShutdown() ) {
                        wps.shutdownNow();
                    }
                }
                Executor ep = options.eventHandlerThreadPool();
                if (ep instanceof ExecutorService) {
                    ExecutorService eps = (ExecutorService) ep;
                    if ( ! eps.isShutdown() ) {
                        eps.shutdownNow();
                    }
                }
            }
            if ( options.useCustomTypeAdapter() != null &&
                    typeAdapterFactory instanceof AutoCloseable ) {
                try {
                    ((AutoCloseable) typeAdapterFactory).close();
                } catch (Exception e) {
                    // ignore
                }
            }
            browserSession = null;
        }
    }

    /**
     * Activate this browser window
     * 
     * @param sessionId session identifier
     */
    public void activate(String sessionId) {
        Session session = sessions.get(sessionId);
        if ( session != null ) {
                browserSession
                    .getCommand()
                    .getTarget()
                    .activateTarget(session.getTargetId());
        }
    }

    /**
     * Tests whether or not a Browser launched with headless argument.
     * 
     * @return {@code true} if browser launched with headless argument.
     */
    public boolean isHeadless() {
        if (headless == null) {
            headless = getBrowserSession()
                            .getCommand()
                            .getBrowser()
                            .getVersion()
                            .getProduct()
                            .toLowerCase(ENGLISH)
                            .contains("headless");
        }
        return headless.booleanValue();
    }

    /**
     * Creates a new incognito browser context.
     * 
     * This won't share cookies/cache with other browser contexts.
     */
    public String createBrowserContext() {
        String browserContextId = getBrowserSession()
                                    .getCommand()
                                    .getTarget()
                                    .createBrowserContext();
        browserContexts.add(browserContextId);
        return browserContextId;
    }

    /**
     * Dispose incoginto browser context.
     */
    public void disposeBrowserContext(final String browserContextId) {
        if (browserContexts.contains(browserContextId)) {
            getBrowserSession()
                    .getCommand()
                    .getTarget()
                    .disposeBrowserContext(browserContextId);
            browserContexts.remove(browserContextId);
        }
    }

    public boolean closed() {
        return closed.get();
    }

    protected LoggerFactory createLoggerFactory(CdpLoggerType loggerType) {
        return new CdpLoggerFactory(loggerType);
    }

    @Override
    public String toString() {
        return "SessionFactory [sessions=" + sessions + "]";
    }
}
