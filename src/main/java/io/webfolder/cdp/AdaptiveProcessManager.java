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
package io.webfolder.cdp;

import static java.io.File.pathSeparator;
import static java.lang.System.getProperty;
import static java.util.Locale.ENGLISH;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import io.webfolder.cdp.exception.CdpException;

public class AdaptiveProcessManager extends ProcessManager {

    private ProcessManager processManager;

    private static final String  OS      = getProperty("os.name").toLowerCase(ENGLISH);

    private static final boolean WINDOWS = ";".equals(pathSeparator);

    private static final boolean LINUX   = "linux".contains(OS);

    private static final boolean MAC     = OS.contains("mac");

    private static final boolean JAVA_8  = getProperty("java.version").startsWith("1.8.");

    public AdaptiveProcessManager() {
        processManager = init();
    }

    private ProcessManager init() {
        if ( ! JAVA_8 ) {
            try {
                Class<?> klass = getClass().getClassLoader().loadClass("io.webfolder.cdp.DefaultProcessManager");
                Constructor<?> constructor = klass.getConstructor();
                return (ProcessManager) constructor.newInstance();
            } catch (ClassNotFoundException |
                    InstantiationException | IllegalAccessException |
                    NoSuchMethodException  | SecurityException |
                    IllegalArgumentException | InvocationTargetException e) {
               throw new CdpException(e);
           }
        } else if (WINDOWS) {
            return new WindowsProcessManager();
        } else if (LINUX) {
            return new LinuxProcessManager();
        } else if (MAC) {
            return new MacOsProcessManager();            
        } else {
            throw new CdpException(OS + " is not supported by AdaptiveProcessManager");
        }
    }

    @Override
    void setProcess(CdpProcess process) {
        processManager.setProcess(process);
    }

    @Override
    public boolean kill() {
        return processManager.kill();
    }
}
