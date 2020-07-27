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

import static java.lang.Integer.valueOf;
import static java.util.concurrent.TimeUnit.MILLISECONDS;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.atomic.AtomicInteger;

import com.koushikdutta.quack.JavaScriptObject;
import com.koushikdutta.quack.QuackObject;

public class JsTimer implements ITimer, QuackObject, AutoCloseable {

    private final AtomicInteger jobCounter = new AtomicInteger(0);

    private final ScheduledExecutorService worker;

    private final Map<Integer, ScheduledFuture<?>> jobs = new ConcurrentHashMap<>();

    public JsTimer(ScheduledExecutorService worker) {
        this.worker = worker;
    }

    @Override
    public Object construct(Object... args) {
        return this;
    }

    @Override
    public int setTimeout(JavaScriptObject callback, int delay) {
        // Chrome execute the callback immediately if delayed is less than zero
        if (delay < 0) {
            delay = 0;
        }
        int timeoutId = jobCounter.incrementAndGet();
        ScheduledFuture<?> job = worker.schedule(() -> {
            ScheduledFuture<?> future = jobs.remove(timeoutId);
            if ( future != null ) {
                callback.call();
            }
        }, delay, MILLISECONDS);
        jobs.put(valueOf(timeoutId), job);
        return timeoutId;
    }


    @Override
    public int setTimeout(JavaScriptObject callback) {
        return setTimeout(callback, 0);
    }

    @Override
    public void clearTimeout(int timeoutId) {
        Integer id = valueOf(timeoutId);
        ScheduledFuture<?> job = jobs.remove(id);
        if (job == null) {
            return;
        }
        job.cancel(false);
    }

    @Override
    public int setInterval(JavaScriptObject callback, int delay) {
        // Chrome execute the callback immediately if delayed is less than zero
        if (delay < 0) {
            delay = 0;
        }
        int intervalId = jobCounter.incrementAndGet();
        ScheduledFuture<?> job = worker.scheduleWithFixedDelay(() -> {
            ScheduledFuture<?> future = jobs.get(intervalId);
            if ( future != null ) {
                callback.call();
            }
        }, delay, delay, MILLISECONDS);
        jobs.put(valueOf(intervalId), job);
        return intervalId;
    }

    @Override
    public int setInterval(JavaScriptObject callback) {
        return setInterval(callback, 0);
    }

    @Override
    public void clearInterval(int intervalId) {
        clearTimeout(intervalId);
    }

    @Override
    public void close() {
        if ( ! jobs.isEmpty() ) {
            jobs.clear();
        }
        if ( ! worker.isShutdown() ) {
            worker.shutdown();
        }
    }
}
