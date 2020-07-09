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

import static java.lang.Integer.parseInt;
import static java.util.Collections.unmodifiableMap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import io.webfolder.cdp.event.Events;
import io.webfolder.cdp.exception.CommandException;
import io.webfolder.cdp.listener.EventListener;
import io.webfolder.cdp.logger.CdpLogger;

public final class MessageHandler {

    private static final Map<String, Events> EVENTS = listEvents();

    private final Gson gson;

    private final Executor workerThreadPool;

    private final Executor eventHandlerThreadPool;

    private final CdpLogger log;

    private final SessionFactory factory;

    MessageHandler(
            final Gson gson,
            final SessionFactory factory,
            final Executor workerThreadPool,
            final Executor eventHandlerThreadPool,
            final CdpLogger log) {
        this.gson                   = gson;
        this.factory                = factory;
        this.workerThreadPool       = workerThreadPool;
        this.eventHandlerThreadPool = eventHandlerThreadPool;
        this.log                    = log; 
    }

    public void process(final String content)  {
        Runnable runnable = () -> {
            log.debug("<-- {}", content);
            JsonElement json = gson.fromJson(content, JsonElement.class);
            JsonObject  object = json.getAsJsonObject();
            JsonElement idElement = object.get("id");
            if ( idElement != null ) {
                // Process command response
                String id = idElement.getAsString();
                if (id == null) {
                    return;
                }
                int valId = parseInt(id);
                JsonElement sid = object.get("sessionId");
                String sessionId = sid == null ? null : sid.getAsString();
                Session session = sessionId == null ? factory.getBrowserSession() : factory.getSession(sessionId);
                if (session == null) {
                    return;
                }
                Context context = session.getContext(valId);
                if (context == null) {
                    return;
                }
                JsonObject error = object.getAsJsonObject("error");
                if (error == null) {
                    context.setData(json);
                    context.release();
                } else {
                    int code = (int) error.getAsJsonPrimitive("code").getAsDouble();
                    String message = error.getAsJsonPrimitive("message").getAsString();
                    JsonElement messageData = error.get("data");
                    context.setError(new CommandException(code, message +
                                                (messageData != null && messageData.isJsonPrimitive() ? ". " +
                                                messageData.getAsString() : "")));
                    context.release();
                }
            } else {
                // Process event response
                JsonElement method = object.get("method");
                if ( method == null || ! method.isJsonPrimitive() ) {
                    return;
                }
                String eventName = method.getAsString();
                Events event = EVENTS.get(eventName);
                if (event == null) {
                    return;
                }
                JsonElement params = object.get("params");
                Object value = gson.fromJson(params, event.klass);
                JsonElement sid = object.get("sessionId");
                String sessionId = sid == null ? null : sid.getAsString();
                Session session = sessionId == null ? factory.getBrowserSession() : factory.getSession(sessionId);
                if (session == null) {
                    return;
                }
                List<EventListener> listeners = session.getListeners();
                if (listeners == null) {
                    return;
                }
                eventHandlerThreadPool.execute(() -> {
                    for (EventListener next : listeners) {
                        next.onEvent(event, value);
                    }
                });
            }
        };
        workerThreadPool.execute(runnable);
    }

    private static Map<String, Events> listEvents() {
        Events[] values = Events.values();
        Map<String, Events> map = new HashMap<>(values.length);
        for (Events next : values) {
            map.put(next.domain + "." + next.name, next);
        }
        return unmodifiableMap(map);
    }
}
