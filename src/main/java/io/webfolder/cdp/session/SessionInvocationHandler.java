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

import static io.webfolder.cdp.session.WaitingStrategy.ParkThread;
import static io.webfolder.cdp.session.WaitingStrategy.Semaphore;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static java.lang.Integer.valueOf;
import static java.lang.System.currentTimeMillis;
import static java.util.Base64.getDecoder;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import io.webfolder.cdp.channel.Channel;
import io.webfolder.cdp.exception.CdpException;
import io.webfolder.cdp.exception.CdpReadTimeoutException;
import io.webfolder.cdp.logger.CdpLogger;

public class SessionInvocationHandler {

    private final AtomicInteger counter       = new AtomicInteger(0);

    private final List<String> enabledDomains = new CopyOnWriteArrayList<>();

    private final Gson                  gson;

    private final Channel               channel;

    private final Map<Integer, Context> contexts;

    private final CdpLogger             log;

    private final Session               session;

    private final String                sessionId;

    private final int                   readTimeout;

    private final WaitingStrategy       waitingStrategy;

    SessionInvocationHandler(
                    final Gson                  gson,
                    final Channel               channel,
                    final Map<Integer, Context> contexts,
                    final Session               session,
                    final CdpLogger             log,
                    final String                sessionId,
                    final int                   readTimeOut,
                    final WaitingStrategy       waitingStrategy) {
        this.gson            = gson;
        this.channel         = channel;
        this.contexts        = contexts;
        this.session         = session;
        this.log             = log;
        this.sessionId       = sessionId;
        this.readTimeout     = readTimeOut;
        this.waitingStrategy = waitingStrategy;
    }

    public Object invoke(
                    final String   domain,
                    final String   command,
                    final String   method,
                    final String   returns,
                    final Type     returnType,
                    final Type     typeArgument,
                    final boolean  voidMethod,
                    final boolean  enable,
                    final boolean  disable,
                    final String[] parameters,
                    final Object[] args) {
        if ( ! session.isConnected() ) {
            throw new CdpException("WebSocket connection is not alive.");
        }

        final int id = counter.incrementAndGet();

        String json = toJson(method, id, parameters, args);
        log.debug("--> {}", json);

        final Context context = Semaphore.equals(waitingStrategy) ? new SemaphoreContext() : new ThreadContext();
        contexts.put(id, context);

        final long start = ParkThread.equals(waitingStrategy) ? currentTimeMillis() : 0;

        channel.sendText(json);

        context.await(readTimeout);

        // This rule must be applied only to ParkThread not for Semaphore.
        // SemaphoreContext implicitly throw an exception if timeout is occurred.
        // ThreadContext.await() can't detect if timeout occurred.
        // We must check explicitly timeout value if waiting strategy is ParkThread.
        if (ParkThread.equals(waitingStrategy) &&
                   (context.getData() == null && context.getError() == null) &&
                   (currentTimeMillis() - start) >= readTimeout) {
            throw new CdpReadTimeoutException(readTimeout + "ms");
        }

        if ( context.getError() != null ) {
            throw context.getError();
        }

        if (enable) {
            enabledDomains.add(domain);
        } else if (disable) {
            enabledDomains.remove(domain);
        }

        if (voidMethod) {
            return null;
        }

        return fromJson(returns, returnType, typeArgument, context);
    }

    String toJson(final String   method,
                  final int      id,
                  final String[] parameters,
                  final Object[] args) {
        final JsonObject params = new JsonObject();
        final boolean hasArgs = args.length > 0;
        if (hasArgs) {
            for (int i = 0; i < args.length; i++) {
                params.add(parameters[i], gson.toJsonTree(args[i]));
            }
        }
        final JsonObject payload = new JsonObject();
        payload.add("id", new JsonPrimitive(valueOf(id)));
        if ( sessionId != null ) {
            payload.add("sessionId", new JsonPrimitive(sessionId));
        }
        payload.add("method", new JsonPrimitive(method));
        if (hasArgs) {
            payload.add("params", gson.toJsonTree(params));
        }
        return gson.toJson(payload);
    }

    Object fromJson(
            final String  returns,
            final Type    returnType,
            final Type    typeArgument,
            final Context context) {
        final JsonElement data = context.getData();
        if (data == null) {
            return null;
        }

        if ( ! data.isJsonObject() ) {
            throw new CdpException("Invalid response");
        }

        final JsonObject object  = data.getAsJsonObject();
        final JsonElement result = object.get("result");

        if ( result == null || ! result.isJsonObject() ) {
            throw new CdpException("Invalid result");   
        }

        final JsonObject resultObject = result.getAsJsonObject();

        if ( returns != null ) {
            JsonElement jsonElement = resultObject.get(returns);
            if ( jsonElement != null ) {
                if (jsonElement.isJsonPrimitive()) {
                    if (String.class.equals(returnType)) {
                        return resultObject.get(returns).getAsString();
                    } else if (Boolean.class.equals(returnType)) {
                        return resultObject.get(returns).getAsBoolean() ? TRUE : FALSE;
                    } else if (Integer.class.equals(returnType)) {
                        return resultObject.get(returns).getAsInt();
                    } else if (Double.class.equals(returnType)) {
                        return resultObject.get(returns).getAsDouble();
                    }
                }
                if (byte[].class.equals(returnType)) {
                    String encoded = gson.fromJson(jsonElement, String.class);
                    if (encoded == null || encoded.trim().isEmpty()) {
                        return null;
                    } else {
                        return getDecoder().decode(encoded);
                    }
                }
            }
            if (List.class.equals(returnType)) {
                JsonArray jsonArray = jsonElement.getAsJsonArray();
                return gson.fromJson(jsonArray, typeArgument);
            } else {
                return gson.fromJson(jsonElement, returnType);
            }
        } else {
            return gson.fromJson(resultObject, returnType);
        }
    }

    void dispose() {
        enabledDomains.clear();
        for (Context context : contexts.values()) {
            try {
                context.release();
            } catch (Throwable t) {
                // ignore
            }
        }
        contexts.clear();
    }
}
