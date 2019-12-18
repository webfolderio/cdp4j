package io.webfolder.cdp;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import com.oracle.svm.core.annotate.Substitute;
import com.oracle.svm.core.annotate.TargetClass;

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

public class Cdp4jSubstitutions {

}
