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

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;

class CdpTypeAdapterFactory implements TypeAdapterFactory, AutoCloseable {

    private final ClassLoader cl = getClass().getClassLoader();

    private static final String EVENT_PACKAGE  = "io.webfolder.cdp.event";

    private static final String TYPE_PACKAGE   = "io.webfolder.cdp.type";

    private static final String ADAPTER_PREFIX = "$TypeAdapter";

    @SuppressWarnings("rawtypes")
    private Map<Class, TypeAdapter> adapters = new ConcurrentHashMap<>();

    @Override
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
        final Class clazz = type.getRawType();
        final String packageName = clazz.getPackage().getName();
        if ( packageName != null &&
                (packageName.startsWith(EVENT_PACKAGE) ||
                 packageName.startsWith(TYPE_PACKAGE)) ) {
            final TypeAdapter typeAdapter = adapters.get(clazz);
            if ( typeAdapter != null ) {
                return typeAdapter;
            }
            try {
                final Class<?> typeAdapterClass = cl.loadClass(clazz.getName() + ADAPTER_PREFIX);
                final Constructor<?> constructor = typeAdapterClass.getConstructor(Gson.class);
                final TypeAdapter instance = (TypeAdapter) constructor.newInstance(gson);
                TypeAdapter existing = adapters.putIfAbsent(clazz, instance);
                if ( existing != null ) {
                    return existing;
                } else {
                    return instance;
                }
            } catch (ClassNotFoundException    | NoSuchMethodException    |
                     SecurityException         | InstantiationException   |
                     IllegalAccessException    | IllegalArgumentException |
                     InvocationTargetException e) {
                return null;
            }
        }
        return null;
    }

    @Override
    public void close() {
        adapters.clear();
    }
}
