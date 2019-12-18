package io.webfolder.cdp.graal;

import java.lang.reflect.AccessibleObject;
import java.util.ArrayList;

import org.graalvm.nativeimage.hosted.Feature;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.internal.ConstructorConstructor;
import com.google.gson.internal.ObjectConstructor;
import com.google.gson.internal.bind.ReflectiveTypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.oracle.svm.core.annotate.Substitute;
import com.oracle.svm.core.annotate.TargetClass;

import io.webfolder.cdp.LibuvProcessManager;
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
        return new LibuvProcessManager();
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
    public <T> ObjectConstructor<T> get(TypeToken<T> typeToken) {
        return new ObjectConstructor<T>() {

            @Override
            @SuppressWarnings("unchecked")
            public T construct() {
                return (T) new ArrayList<T>();
            }
        };
    }
}

@TargetClass(className = "com.google.gson.internal.bind.TypeAdapters$EnumTypeAdapter")
final class Target_com_google_gson_internal_bind_TypeAdapters$EnumTypeAdapter {

    @Substitute
    public <T> Target_com_google_gson_internal_bind_TypeAdapters$EnumTypeAdapter(Class<T> classOfT) {
        // no op
    }
}
