/**
 * cdp4j - Chrome DevTools Protocol for Java
 * Copyright © 2017, 2018 WebFolder OÜ (support@webfolder.io)
 * <p>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * <p>
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package io.webfolder.cdp.session;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.neovisionaries.ws.client.WebSocket;
import io.webfolder.cdp.annotation.Domain;
import io.webfolder.cdp.annotation.Returns;
import io.webfolder.cdp.exception.CdpException;
import io.webfolder.cdp.exception.ConnectionLostException;
import io.webfolder.cdp.json.JsonRequest;
import io.webfolder.cdp.json.JsonResponse;
import io.webfolder.cdp.logger.CdpLogger;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.String.format;
import static java.util.Base64.getDecoder;

class SessionInvocationHandler implements InvocationHandler {

    private final AtomicInteger counter = new AtomicInteger(0);

    private final ObjectMapper jackson;

    private final WebSocket webSocket;

    private final Map<Integer, WSContext> contexts;

    private final List<String> enabledDomains = new CopyOnWriteArrayList<>();

    private final CdpLogger log;

    private final Session session;

    private final boolean browserSession;

    private final String sessionId;

    private final String targetId;

    private final int timeout;

    SessionInvocationHandler(
            final ObjectMapper jackson,
            final WebSocket webSocket,
            final Map<Integer, WSContext> contexts,
            final Session session,
            final CdpLogger log,
            final boolean browserSession,
            final String sessionId,
            final String targetId,
            final int webSocketReadTimeout) {
        this.jackson = jackson;
        this.webSocket = webSocket;
        this.contexts = contexts;
        this.session = session;
        this.log = log;
        this.browserSession = browserSession;
        this.sessionId = sessionId;
        this.targetId = targetId;
        this.timeout = webSocketReadTimeout;
    }

    @Override
    public Object invoke(
            final Object proxy,
            final Method method,
            final Object[] args) throws Throwable {

        final Class<?> klass = method.getDeclaringClass();
        final String domain = klass.getAnnotation(Domain.class).value();
        final String command = method.getName();

        final boolean hasArgs = args != null && args.length > 0;
        final boolean voidMethod = void.class.equals(method.getReturnType());

        boolean enable = "enable".intern() == command && voidMethod;

        // it's unnecessary to call enable command more than once.
        if (enable && enabledDomains.contains(domain)) {
            return null;
        }

        boolean disable = "disable".intern() == command && voidMethod;

        if (disable) {
            enabledDomains.remove(domain);
        }

        int id = counter.incrementAndGet();

        String json = buildRequestJson(method, args, domain, command, hasArgs, id);

        log.debug(json);

        WSContext context;

        if (session.isConnected()) {
            context = new WSContext(json);
            contexts.put(id, context);
            if (browserSession) {
                webSocket.sendText(json);
            } else {
                session.getCommand()
                        .getTarget()
                        .sendMessageToTarget(json, sessionId, targetId);
            }
            context.await(timeout);
        } else {
            throw new ConnectionLostException("WebSocket connection is not alive. id: " + id);
        }

        if (context.getError() != null) {
            throw context.getError();
        }

        if (enable) {
            enabledDomains.add(domain);
        }

        Class<?> retType = method.getReturnType();

        if (voidMethod || retType.equals(Void.class)) {
            return null;
        }

        JsonResponse data = context.getData();

        String returns = method.isAnnotationPresent(Returns.class) ?
                method.getAnnotation(Returns.class).value() : null;

        return jsonToObject(method, retType, data, returns);
    }

    private Object jsonToObject(Method method, Class<?> retType, JsonResponse data, String returns) throws IOException {
        if (data == null)
            return null;

        JsonNode result = data.getResult();

        if (result == null || !result.isObject()) {
            throw new CdpException("invalid result");
        }

        Type genericReturnType = method.getGenericReturnType();

        if (returns != null)
            result = result.get(returns);

        if (result.isNull())
            return null;

        if (result.isValueNode()) {
            if (String.class.equals(retType))
                return result.asText();

            if (Boolean.class.equals(retType))
                return result.asBoolean() ? Boolean.TRUE : Boolean.FALSE;

            if (Integer.class.equals(retType))
                return result.asInt();

            if (Double.class.equals(retType))
                return result.asDouble();
        }

        if (byte[].class.equals(genericReturnType)) {
            String encoded = result.asText();
            if (encoded == null || encoded.trim().isEmpty()) {
                return null;
            } else {
                return getDecoder().decode(encoded);
            }
        }

        if (List.class.equals(retType)) {
            return jackson
                    .readValue(
                            jackson.treeAsTokens(result),
                            jackson.getTypeFactory().constructType(genericReturnType)
                    );
        }

        return jackson.treeToValue(result, retType);
    }

    private String buildRequestJson(Method method, Object[] args, String domain, String command, boolean hasArgs, int id) throws JsonProcessingException {
        Map<String, JsonNode> paramsJson = new HashMap<>(hasArgs ? args.length : 0);

        if (hasArgs) {
            int argIndex = 0;
            Parameter[] parameters = method.getParameters();
            for (Object argValue : args) {
                String argName = parameters[argIndex++].getName();
                paramsJson.put(argName, jackson.valueToTree(argValue));
            }
        }

        JsonRequest request = new JsonRequest(
                id,
                format("%s.%s", domain, command),
                paramsJson
        );

        return jackson.writeValueAsString(request);
    }

    void dispose() {
        enabledDomains.clear();
        for (WSContext context : contexts.values()) {
            try {
                context.setData(null);
            } catch (Throwable t) {
            }
        }
    }

    WSContext getContext(int id) {
        return contexts.get(id);
    }
}
