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
package io.webfolder.cdp.session;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketAdapter;
import io.webfolder.cdp.event.Events;
import io.webfolder.cdp.exception.CommandException;
import io.webfolder.cdp.json.JsonResponse;
import io.webfolder.cdp.json.JsonResponseError;
import io.webfolder.cdp.listener.EventListener;
import io.webfolder.cdp.logger.CdpLogger;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;

import static java.util.Collections.unmodifiableMap;

class WSAdapter extends WebSocketAdapter {

    private final Map<String, Events> events = listEvents();

    private final ObjectMapper jackson;

    private final Map<Integer, WSContext> contexts;

    private final List<EventListener> listeners;

    private final Executor executor;

    private final CdpLogger log;

    private Session session;

    private static class TerminateSession implements Runnable {

        private final Session session;

        private final String reason;

        public TerminateSession(final Session session, final String reason) {
            this.session = session;
            this.reason = reason;
        }

        @Override
        public void run() {
            if (session != null && session.isConnected())
                try {
                    session.close();
                    session.terminate(reason);
                } catch (Exception ignored){
                }
        }
    }

    WSAdapter(
            final ObjectMapper jackson,
            final Map<Integer, WSContext> contexts,
            final List<EventListener> listeners,
            final Executor executor,
            final CdpLogger log) {
        this.jackson = jackson;
        this.contexts = contexts;
        this.listeners = listeners;
        this.executor = executor;
        this.log = log;
    }

    @Override
    public void onTextMessage(
            final WebSocket websocket,
            final String data) throws Exception {
        onMessage(data, true);
    }

    void onMessage(final String data, boolean async) throws Exception {
        Runnable runnable = () -> {
            log.debug(data);
            try {
                JsonResponse json = jackson.readValue(data, JsonResponse.class);
                if (json.getId() != null) {
                    WSContext context = contexts.remove(json.getId());
                    if (context != null) {
                        JsonResponseError error = json.getError();
                        if (error != null) {
                            int code = error.getCode().intValue();
                            String message = error.getMessage();
                            JsonNode messageData = error.getData();
                            context.setError(
                                new CommandException(
                                    code,
                                    message +
                                        (messageData != null && messageData.isTextual() ?
                                            ". " + messageData.asText() :
                                            ""
                                        ),
                                    context.getJsonRequest()
                                )
                            );
                        } else {
                            context.setData(json);
                        }
                    }
                } else {
                    String eventName = json.getMethod();
                    if (eventName != null) {
                        if ("Inspector.detached".equals(eventName) && session != null) {
                            if (session.isConnected()) {
                                String reason = json.getParams().get("reason").asText();
                                Thread thread = new Thread(new TerminateSession(session, reason));
                                thread.setName("cdp4j-terminate");
                                thread.setDaemon(true);
                                thread.start();
                                session = null;
                            }
                        } else {
                            Events event = events.get(eventName);
                            if (event != null) {
                                Object value = jackson.treeToValue(json.getParams(), event.klass);
                                for (EventListener next : listeners) {
                                    executor.execute(() -> {
                                        next.onEvent(event, value);
                                    });
                                }
                            }
                        }
                    }
                }
            } catch (IOException e) {
                log.error("Error", e);
            }
        };
        if (async) {
            executor.execute(runnable);
        } else {
            runnable.run();
        }
    }

    Map<String, Events> listEvents() {
        Map<String, Events> map = new HashMap<>();
        for (Events next : Events.values()) {
            map.put(next.domain + "." + next.name, next);
        }
        return unmodifiableMap(map);
    }

    void setSession(final Session session) {
        this.session = session;
    }
}
