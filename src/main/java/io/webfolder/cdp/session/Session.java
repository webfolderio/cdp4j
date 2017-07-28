/**
 * The MIT License
 * Copyright © 2017 WebFolder OÜ
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package io.webfolder.cdp.session;

import static io.webfolder.cdp.event.Events.LogEntryAdded;
import static io.webfolder.cdp.event.Events.NetworkResponseReceived;
import static io.webfolder.cdp.event.Events.PageLoadEventFired;
import static io.webfolder.cdp.event.Events.RuntimeConsoleAPICalled;
import static io.webfolder.cdp.type.constant.ImageFormat.Png;
import static io.webfolder.cdp.type.page.ResourceType.Document;
import static io.webfolder.cdp.type.page.ResourceType.XHR;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static java.lang.Math.floor;
import static java.lang.String.format;
import static java.lang.String.valueOf;
import static java.lang.ThreadLocal.withInitial;
import static java.lang.reflect.Proxy.newProxyInstance;
import static java.util.Locale.ENGLISH;
import static java.util.concurrent.TimeUnit.MILLISECONDS;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Predicate;

import com.google.gson.Gson;
import com.neovisionaries.ws.client.WebSocket;

import io.webfolder.cdp.command.CSS;
import io.webfolder.cdp.command.DOM;
import io.webfolder.cdp.command.Emulation;
import io.webfolder.cdp.command.Page;
import io.webfolder.cdp.event.log.EntryAdded;
import io.webfolder.cdp.event.network.ResponseReceived;
import io.webfolder.cdp.event.runtime.ConsoleAPICalled;
import io.webfolder.cdp.exception.CdpException;
import io.webfolder.cdp.listener.EventListener;
import io.webfolder.cdp.listener.TerminateEvent;
import io.webfolder.cdp.listener.TerminateListener;
import io.webfolder.cdp.logger.CdpLogger;
import io.webfolder.cdp.logger.CdpLoggerFactory;
import io.webfolder.cdp.type.css.SourceRange;
import io.webfolder.cdp.type.dom.Rect;
import io.webfolder.cdp.type.log.LogEntry;
import io.webfolder.cdp.type.network.Response;
import io.webfolder.cdp.type.page.GetLayoutMetricsResult;
import io.webfolder.cdp.type.runtime.RemoteObject;

public class Session implements AutoCloseable,
                                Selector     ,
                                Keyboard     ,
                                Mouse        ,
                                Navigator    ,
                                JavaScript   ,
                                Sizzle       ,
                                Dom          {

    private final Map<Class<?>, Object> proxies = new ConcurrentHashMap<>();

    private AtomicBoolean connected = new AtomicBoolean(true);

    private final AtomicBoolean enableSizzle = new AtomicBoolean();

    private final List<EventListener<?>> eventListeners;

    private final SessionInvocationHandler invocationHandler;

    private final SessionFactory sesessionFactory;

    private final String sessionId;

    private final WebSocket webSocket;

    private final CdpLogger log;

    private final CdpLogger logFlow;

    private final Gson gson;

    private TerminateListener terminateListener;

    private String frameId;

    private Command command;

    private final ReentrantLock lock = new ReentrantLock(true);

    private static final ThreadLocal<Boolean> ENABLE_ENTRY_EXIT_LOG = 
                                                    withInitial(() -> { return TRUE; });

    Session(
            final Gson gson,
            final String sessionId,
            final WebSocket webSocket,
            final Map<Integer, WSContext> contextList,
            final SessionFactory sessionFactory,
            final List<EventListener<?>> eventListeners,
            final CdpLoggerFactory loggerFactory) {
        this.sessionId = sessionId;
        this.invocationHandler = new SessionInvocationHandler(
                                                        gson,
                                                        webSocket,
                                                        contextList,
                                                        this,
                                                        loggerFactory.getLogger("cdp4j.ws.request"));
        this.sesessionFactory = sessionFactory;
        this.eventListeners   = eventListeners;
        this.webSocket        = webSocket;
        this.log              = loggerFactory.getLogger("cdp4j.session");
        this.logFlow          = loggerFactory.getLogger("cdp4j.flow");
        this.gson             = gson;
        this.command          = new Command(this);
    }

    public String getId() {
        return sessionId;
    }

    /**
     * Close the this browser window
     */
    @Override
    public void close() {
        logEntry("close");
        if (connected.get()) {
            sesessionFactory.close(sessionId, this);
            connected.set(false);
        }
    }

    public boolean isConnected() {
        return connected.get();
    }

    /**
     * Activate this browser window
     */
    public void activate() {
        logEntry("activate");
        sesessionFactory.activate(sessionId);
    }

    public void addEventListener(EventListener<?> eventListener) {
        eventListeners.add(eventListener);
    }

    public void removeEventEventListener(EventListener<?> eventListener) {
        if (eventListener != null) {
            eventListeners.remove(eventListener);
        }
    }

    /**
     * waits until document is ready
     * 
     * @return this
     */
    public Session waitDocumentReady() {
        return waitDocumentReady(Constant.WAIT_TIMEOUT);
    }

    /**
     * waits until document is ready
     * 
     * @param timeout
     * 
     * @return this
     */
    public Session waitDocumentReady(final int timeout) {
        if (isDomReady()) {
            return getThis();
        }
        logEntry("waitDocumentReady", format("[timeout=%d]", timeout));
        command.getPage().enable();
        CountDownLatch latch = new CountDownLatch(1);
        addEventListener((e, d) -> {
            if (PageLoadEventFired.equals(e)) {
                latch.countDown();
            }
        });
        try {
            latch.await(timeout, MILLISECONDS);
        } catch (InterruptedException e) {
            throw new CdpException(e);
        }
        if ( ! isDomReady() ) {
            waitUntil(s -> s.isDomReady());
        }
        if ( ! isDomReady()) {
            throw new CdpException("DOM is not ready");
        }
        return this;
    }

    public boolean waitUntil(final Predicate<Session> predicate) {
        return waitUntil(predicate, Constant.WAIT_TIMEOUT, Constant.WAIT_PERIOD);
    }

    public boolean waitUntil(final Predicate<Session> predicate, final int timeout) {
        return waitUntil(predicate, timeout, Constant.WAIT_PERIOD);
    }

    public boolean waitUntil(
                    final Predicate<Session> predicate,
                    final int timeout,
                    final int period) {
        final int count = (int) floor(timeout / period);
        for (int i = 0; i < count; i++) {
            final boolean wakeup = predicate.test(getThis());
            if (wakeup) {
                return true;
            } else {
                wait(period);
            }
        }
        return false;
    }

    public Session navigate(final String url) {
        logEntry("navigate", url);
        command.getInspector().enable();
        this.frameId = command.getPage().navigate(url);
        return this;
    }

    /**
     * Redirects javascript console logs to slf4j
     * 
     * @return this
     */
    public Session enableConsoleLog() {
        getCommand().getRuntime().enable();
        addEventListener((e, d) -> {
            if (RuntimeConsoleAPICalled.equals(e)) {
                ConsoleAPICalled ca = (ConsoleAPICalled) d;
                for (RemoteObject next : ca.getArgs()) {
                    Object value = next.getValue();
                    String type = ca.getType().toString().toUpperCase(ENGLISH);
                    switch (ca.getType()) {
                        case Log    :
                        case Info   : log.info("[console] [{}] {}", new Object[] { type, valueOf(value) }); break;
                        case Error  : log.info("[console] [{}] {}", new Object[] { type, valueOf(value) }); break;
                        case Warning: log.info("[console] [{}] {}", new Object[] { type, valueOf(value) }); break;
                        default: break;
                    }
                }
            }
        });
        return getThis();
    }

    /**
     * Redirects runtime logs (network, security, storage etc..) to slf4j
     * 
     * @return this
     */
    public Session enableDetailLog() {
        getCommand().getLog().enable();
        addEventListener((e, d) -> {
            if (LogEntryAdded.equals(e)) {
                EntryAdded entryAdded = (EntryAdded) d;
                LogEntry entry = entryAdded.getEntry();
                String level = entry.getLevel().toString().toUpperCase(ENGLISH);
                switch (entry.getLevel()) {
                    case Verbose: log.info("[{}] [{}] {}", entry.getSource(), level, entry.getText()); break;
                    case Info   : log.info("[{}] [{}] {}", entry.getSource(), level, entry.getText()); break;
                    case Warning: log.info("[{}] [{}] {}", entry.getSource(), level, entry.getText()); break;
                    case Error  : log.info("[{}] [{}] {}", entry.getSource(), level, entry.getText()); break;
                }
            }
        });
        return getThis();
    }

    /**
     * Redirects network logs to slf4j
     * 
     * @return this
     */
    public Session enableNetworkLog() {
        getCommand().getNetwork().enable();
        addEventListener((e, d) -> {
            if (NetworkResponseReceived.equals(e)) {
                ResponseReceived   rr = (ResponseReceived) d;
                Response     response = rr.getResponse();
                final String      url = response.getUrl();
                final String   status = response.getStatus().intValue() + " " + response.getStatusText();
                final String mimeType = response.getMimeType();
                if (Document.equals(rr.getType()) || XHR.equals(rr.getType())) {
                    log.info("[{}] [{}] [{}] [{}] [{}]", new Object[] {
                        rr.getType().toString().toUpperCase(ENGLISH),
                        rr.getResponse().getProtocol().toUpperCase(ENGLISH),
                        status,
                        mimeType,
                        url
                    });
                }
            }
        });
        return getThis();
    }

    public Session getThis() {
        return this;
    }

    public String getFrameId() {
        return frameId;
    }

    public byte[] captureScreenshot() {
        String frameId = getThis().getFrameId();
        DOM dom = getThis().getCommand().getDOM();
        dom.enable();
        CSS css = getThis().getCommand().getCSS();
        css.enable();
        String styleSheetId = css.createStyleSheet(frameId);
        SourceRange location = new SourceRange();
        location.setEndColumn(0);
        location.setEndLine(0);
        location.setStartColumn(0);
        location.setStartLine(0);
        css.addRule(styleSheetId, "::-webkit-scrollbar { display: none !important; }", location);
        Page page = getThis().getCommand().getPage();
        GetLayoutMetricsResult metrics = page.getLayoutMetrics();
        Rect cs = metrics.getContentSize();
        Emulation emulation = getThis().getCommand().getEmulation();
		emulation.setDeviceMetricsOverride(cs.getWidth().intValue(), cs.getHeight().intValue(), 1D, false, true);
        byte[] data = page.captureScreenshot(Png, null, true);        
        emulation.clearDeviceMetricsOverride();
        emulation.resetPageScaleFactor();
        css.getStyleSheetText(styleSheetId);
        css.setStyleSheetText(styleSheetId, "");
        return data;
    }

    /**
     * Causes the current thread to wait until waiting time elapses.
     * 
     * @param timeout the maximum time to wait in milliseconds
     * 
     * @throws CdpException if the session held by another thread at the time of invocation.
     * 
     * @return this
     */
    public Session wait(int timeout) {
        if (lock.tryLock()) {
            Condition condition = lock.newCondition();
            try {
                logEntry("wait", timeout + "ms");
                condition.await(timeout, MILLISECONDS);
            } catch (InterruptedException e) {
                throw new CdpException(e);
            } finally {
                if (lock.isLocked()) {
                    lock.unlock();
                }
            }
        } else {
            throw new CdpException("Unable to acquire lock");
        }
        return getThis();
    }

    public void onTerminate(TerminateListener terminateListener) {
        this.terminateListener = terminateListener;
    }


    public Command getCommand() {
        return command;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((sessionId == null) ? 0 : sessionId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Session other = (Session) obj;
        if (sessionId == null) {
            if (other.sessionId != null)
                return false;
        } else if (!sessionId.equals(other.sessionId))
            return false;
        return true;
    }

    void dispose() {
        proxies.clear();
        eventListeners.clear();
        invocationHandler.dispose();
        try {
            webSocket.disconnect();
        } catch (Throwable t) {
        }
    }

    Gson getGson() {
        return gson;
    }

    void terminate(String message) {
        if (terminateListener != null) {
            terminateListener.onTerminate(new TerminateEvent(message));
        }
    }

    void info(
            final String message,
            final Object ...args) {
        log.info(message, args);
    }

    void error(final String message, final Object ...args) {
        log.error(message, args);
    }

    void logEntry(final String method) {
        logEntry(method, null);
    }

    void logEntry(
            final String method,
            final String args) {
        if ( ! ENABLE_ENTRY_EXIT_LOG.get() ) {
            return;
        }
        boolean hasArgs = args != null ? true : false;
        logFlow.info("{}({}{}{})", new Object[] {
            method,
            hasArgs ? "\"" : "",
            hasArgs ? args : "",
            hasArgs ? "\"" : ""
        });
    }

    void logExit(
            final String method,
            final Object retValue) {
        logExit(method, null, retValue);
    }

    void logExit(
            final String method,
            final String args,
            final Object retValue) {
        if ( ! ENABLE_ENTRY_EXIT_LOG.get() ) {
            return;
        }
        boolean hasArgs = args != null ? true : false;
        logFlow.info("{}({}{}{}): {}", new Object[] {
            method,
            hasArgs ? "\"" : "",
            hasArgs ? args : "",
            hasArgs ? "\"" : "",
            retValue
        });
    }

    void setSizzle(final boolean enable) {
        this.enableSizzle.set(enable);
    }

    boolean getSizzle() {
        return this.enableSizzle.get();
    }

    @SuppressWarnings("unchecked")
    <T> T getProxy(Class<T> klass) {
        T proxy = (T) proxies.get(klass);
        if (proxy != null) {
            return (T) proxy;
        }
        ClassLoader classLoader = getClass().getClassLoader();
        Class<T>[] interfaces = new Class[] { klass };
        proxy = (T) newProxyInstance(classLoader, interfaces, invocationHandler);
        Object existing = proxies.putIfAbsent(klass, proxy);
        if (existing != null) {
            return (T) existing;
        }
        return proxy;
    }

    void disableFlowLog() {
        ENABLE_ENTRY_EXIT_LOG.set(FALSE);
    }

    void enableFlowLog() {
        ENABLE_ENTRY_EXIT_LOG.set(TRUE);
    }

    boolean isPrimitive(Class<?> klass) {
        if (String.class.equals(klass)) {
            return true;
        } else if (boolean.class.equals(klass) || Boolean.class.equals(klass)) {
            return true;
        } else if (void.class.equals(klass) || Void.class.equals(klass)) {
            return true;
        } else if (int.class.equals(klass) || Integer.class.equals(klass)) {
            return true;
        } else if (double.class.equals(klass) || Double.class.equals(klass)) {
            return true;
        } else if (long.class.equals(klass) || Long.class.equals(klass)) {
            return true;
        } else if (float.class.equals(klass) || Float.class.equals(klass)) {
            return true;
        } else if (char.class.equals(klass) || Character.class.equals(klass)) {
            return true;
        } else if (byte.class.equals(klass) || Byte.class.equals(klass)) {
            return true;
        } else if (short.class.equals(klass) || Short.class.equals(klass)) {
            return true;
        }
        return false;
    }

	@SuppressWarnings("unchecked")
	public boolean emulateDevice(final String deviceName, Boolean fitWindow) {

		try {
		final Map<String, Object> emulationMap = jsonToMap();
		

		final List<Map<String, Object>> result = (List<Map<String, Object>>) emulationMap.get("extensions");
		for (final Map<String, ?> extension : result) {
			final Map<String, Object> device = (Map<String, Object>) extension.get("device");
			final String dName = (String) device.get("title");
			if (dName.equals(deviceName)) {

				final Emulation emulation = getCommand().getEmulation();

				final Map<String, Double> widthHeightMap = (Map<String, Double>) ((Map<String, ?>) device.get("screen")).get("vertical");
				final Double deviceScaleFactor = (Double) ((Map<String, ?>) device.get("screen")).get("device-pixel-ratio");
				final Integer width = widthHeightMap.get("width").intValue();
				final Integer height = widthHeightMap.get("height").intValue();
				final boolean mobile = ((List<String>) device.get("capabilities")).contains("mobile");
				final String userAgent = (String) device.get("user-agent");
				getCommand().getNetwork().setUserAgentOverride(userAgent);
				emulation.setVisibleSize(width, height);
					emulation.setDeviceMetricsOverride(width, height, deviceScaleFactor, mobile, fitWindow);
				emulation.setPageScaleFactor(deviceScaleFactor);
				emulation.setTouchEmulationEnabled(false);

					return true;
			}
		}
		}
		catch(IOException e) {
			log.error("Error emulating device", e);
		}
		log.warning("Could not find mobile device {}", deviceName);
		return false;
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> jsonToMap() throws IOException {
		Gson gson = new Gson();
		try (InputStream moduleStream = Session.class.getResourceAsStream("/module.json");
				InputStreamReader reader = new InputStreamReader(moduleStream)) {

			return gson.fromJson(reader, Map.class);
		}

	}

	/**
	 * Reads all bytes from an input stream and writes them to an output stream.
	 */
	// buffer size used for reading and writing
	private static final int BUFFER_SIZE = 8192;

	private long copy(InputStream source, OutputStream sink) throws IOException {
		long nread = 0L;
		byte[] buf = new byte[BUFFER_SIZE];
		int n;
		while ((n = source.read(buf)) > 0) {
			sink.write(buf, 0, n);
			nread += n;
		}
		return nread;
	}

	String getResourceAsString(String path) {
		try {
			try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
					InputStream moduleStream = Session.class.getResourceAsStream(path);) {
				copy(moduleStream, bos);
				bos.flush();
				return bos.toString(Charset.defaultCharset().name());
			}


		} catch (IOException e) {
			log.error("Could not load CallFunctionScript", e);
			throw new RuntimeException(e);
		}

	}
}
