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
import static java.nio.charset.StandardCharsets.UTF_8;
import static java.nio.file.Files.readAllBytes;
import static java.util.Locale.ENGLISH;
import static java.util.stream.Collectors.joining;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.util.concurrent.ScheduledExecutorService;

import com.koushikdutta.quack.JavaScriptObject;
import com.koushikdutta.quack.QuackContext;

import io.webfolder.cdp.exception.CdpException;

public class JsEngine implements AutoCloseable {

    private final QuackContext context;

    private final JavaScriptObject global;

    private final ScheduledExecutorService executor;

    private static final String OS_NAME = getProperty("os.name")
                                          .toLowerCase(ENGLISH);

    private static final boolean WINDOWS = OS_NAME.startsWith("windows");

    private static final String boostrapJs;

    static {
        try (InputStream is = JsEngine.class.getResourceAsStream("/cdp4js-bootstrap.js");
                BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            boostrapJs = reader.lines()
                               .collect(joining(WINDOWS ? "\r\n" : "\n"));
        } catch (IOException e) {
            throw new CdpException(e);
        }
    }

    public JsEngine() {
        this(null);
    }

    public JsEngine(ScheduledExecutorService executor) {
        this.executor = executor;
        context = create(true);
        context.setJobExecutor(executor);
        if (context == null) {
            throw new IllegalStateException();
        }
        global = context.getGlobalObject();
        init(global);
    }

    protected void init(JavaScriptObject global) {
        if (executor != null) {
            ITimer timer = createTimer(executor);
            if (timer != null) {
                global.set("Timer", timer);
            }
        }
        IConsole console = createConsole();
        if (console != null) {
            global.set("console", console);
        }
        ILauncher launcher = createLauncher();
        if (launcher != null) {
            global.set("Launcher", launcher);
        }
        context.evaluate(boostrapJs);
    }

    protected ITimer createTimer(ScheduledExecutorService worker) {
        return new JsTimer(worker);
    }

    protected IConsole createConsole() {
        return new JsConsole(System.out, System.err);
    }

    protected ILauncher createLauncher() {
        return new JsLauncher();
    }

    public void evaluateModule(Path script) {
        evaluate(true, script);
    }

    public void evaluate(Path... scripts) {
        evaluate(false, scripts);
    }

    private void evaluate(boolean module, Path... scripts) {
        for (Path script : scripts) {
            if (script == null) {
                continue;
            }
            byte[] arr = null;
            try {
                arr = readAllBytes(script);
            } catch (IOException e) {
                throw new CdpException(e);
            }
            String content = new String(arr, UTF_8);
            String fileName = script.getFileName().toString();
            if (module) {
                context.evaluateModule(content, fileName);
            } else {
                context.evaluate(content, fileName);
            }
        }
    }

    @Override
    public void close() {
        if ( context != null ) {
            context.close();
        }
    }

    public void execute(String script) {
        execute(script, null);
    }

    public void execute(String script, String fileName) {
        try {
            context.evaluate(script, fileName == null ? "?" : fileName);
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}
