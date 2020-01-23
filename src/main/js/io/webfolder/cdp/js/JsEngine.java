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
package io.webfolder.cdp.js;

import static com.koushikdutta.quack.QuackContext.create;
import static java.lang.System.getProperty;
import static java.lang.System.load;
import static java.nio.charset.StandardCharsets.UTF_8;
import static java.nio.file.Files.copy;
import static java.nio.file.Files.createDirectory;
import static java.nio.file.Files.createFile;
import static java.nio.file.Files.exists;
import static java.nio.file.Files.readAllBytes;
import static java.nio.file.Paths.get;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import static java.util.Locale.ENGLISH;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;

import com.koushikdutta.quack.JavaScriptObject;
import com.koushikdutta.quack.QuackContext;

public class JsEngine implements AutoCloseable {

    private final QuackContext engine;

    private final JavaScriptObject global;

    // temporary directory location
    private static final Path tmpdir = get(getProperty("java.io.tmpdir")).toAbsolutePath();

    private static final String version = "4.3.0";

    private static final String OS_NAME = getProperty("os.name").toLowerCase(ENGLISH);

    protected static final boolean WINDOWS = OS_NAME.startsWith("windows");

    private static boolean loaded;

    static {
        loadJni();
    }

    private static synchronized boolean loadJni() {
        if (loaded) {
            return true;
        }
        if (!WINDOWS) {
            return false;
        }
        Path libFile = tmpdir.resolve("cdp4js-" + version).resolve("quack.dll");
        if (!exists(libFile)) {
            ClassLoader cl = JsEngine.class.getClassLoader();
            try (InputStream is = cl.getResourceAsStream("META-INF/quack.dll")) {
                if (!exists(libFile.getParent())) {
                    createDirectory(libFile.getParent());
                }
                if (!exists(libFile)) {
                    createFile(libFile);
                }
                copy(is, libFile, REPLACE_EXISTING);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        load(libFile.toString());
        loaded = true;
        return true;
    }

    public JsEngine() {
        engine = create(true);
        if (engine == null) {
            throw new IllegalStateException();
        }
        global = engine.getGlobalObject();
        init(global);
    }

    protected void init(JavaScriptObject global) {
        global.set("console", new JsConsole(System.out, System.err));
        global.set("Launcher", new JsLauncher());
    }

    @Override
    public void close() {
        if ( engine != null ) {
            engine.close();
        }
    }

    public Object evaluate(Path path) {
        if (path == null) {
            return null;
        }
        byte[] content = null;
        try {
            content = readAllBytes(path);
        } catch (IOException e) {
            return null;
        }
        String script = new String(content, UTF_8);
        String fileName = path.getFileName().toString();
        Object ret = engine.evaluate(script, fileName);
        return ret;
    }
}
