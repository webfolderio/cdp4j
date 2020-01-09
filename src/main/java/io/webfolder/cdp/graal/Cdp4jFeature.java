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
package io.webfolder.cdp.graal;

import java.lang.reflect.AccessibleObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.graalvm.nativeimage.hosted.Feature;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.internal.ConstructorConstructor;
import com.google.gson.internal.ObjectConstructor;
import com.google.gson.internal.bind.ReflectiveTypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.oracle.svm.core.annotate.Substitute;
import com.oracle.svm.core.annotate.TargetClass;

import io.webfolder.cdp.DefaultProcessManager;
import io.webfolder.cdp.ProcessManager;
import io.webfolder.cdp.logger.CdpConsoleLogger;
import io.webfolder.cdp.logger.CdpConsoleLogggerLevel;
import io.webfolder.cdp.logger.CdpLogger;

@TargetClass(value = io.webfolder.cdp.logger.CdpLoggerFactory.class)
final class Target_io_webfolder_cdp_logger_CdpLoggerFactory {

    @Substitute
    public CdpLogger getLogger(String name, CdpConsoleLogggerLevel loggerLevel) {
        return new CdpConsoleLogger(loggerLevel);
    }
}

@TargetClass(className = "io.webfolder.cdp.session.CdpTypeAdapterFactory")
final class Target_io_webfolder_cdp_session_CdpTypeAdapterFactory {

    @Substitute
    @SuppressWarnings("rawtypes")
    public TypeAdapter create(Gson gson, TypeToken type) {
        return null;
    }
}

@TargetClass(io.webfolder.cdp.AdaptiveProcessManager.class)
final class Target_io_webfolder_cdp_AdaptiveProcessManager {

    @Substitute
    private ProcessManager init() {
        return new DefaultProcessManager();
    }
}

public final class Cdp4jFeature implements Feature {

    @Override
    public void beforeAnalysis(BeforeAnalysisAccess access) {
        // no op
    }
}

@TargetClass(ReflectiveTypeAdapterFactory.class)
final class Target_com_google_gson_internal_bind_ReflectiveTypeAdapterFactory {

    @Substitute
    public <T> TypeAdapter<T> create(Gson gson, final TypeToken<T> type) {
        return null;
    }
}

@TargetClass(className = "com.google.gson.internal.reflect.UnsafeReflectionAccessor")
final class Target_com_google_gson_internal_reflect_UnsafeReflectionAccessor {

    @Substitute
    public void makeAccessible(AccessibleObject ao) {
        // no op
    }
}

@TargetClass(ConstructorConstructor.class)
final class Target_com_google_gson_internal_ConstructorConstructor {

    @Substitute
    @SuppressWarnings("unchecked")
    public <T> ObjectConstructor<T> get(TypeToken<T> typeToken) {
        Class<? super T> rt = typeToken.getRawType();
        if (ArrayList.class.equals(rt) || List.class.equals(rt)) {
            return ArrayListObjectConstructor.INSTANCE;
        } else if (HashMap.class.equals(rt)) {
            return HashMapObjectConstructor.INSTANCE;
        } else if (LinkedHashMap.class.equals(rt) || Map.class.equals(rt)) {
            return LinkedHashMapObjectConstructor.INSTANCE;
        } else if (HashSet.class.equals(rt) || Set.class.equals(rt)) {
            return HashSetObjectConstructor.INSTANCE;
        }
        return null;
    }
}

@TargetClass(className = "com.google.gson.internal.bind.TypeAdapters$EnumTypeAdapter")
final class Target_com_google_gson_internal_bind_TypeAdapters$EnumTypeAdapter {

    @Substitute
    public <T> Target_com_google_gson_internal_bind_TypeAdapters$EnumTypeAdapter(Class<T> classOfT) {
        // no op
    }
}
