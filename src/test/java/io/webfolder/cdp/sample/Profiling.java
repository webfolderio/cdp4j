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
package io.webfolder.cdp.sample;

import java.util.Map;
import java.util.concurrent.CountDownLatch;

import io.webfolder.cdp.Launcher;
import io.webfolder.cdp.command.Tracing;
import io.webfolder.cdp.event.Events;
import io.webfolder.cdp.event.tracing.DataCollected;
import io.webfolder.cdp.listener.EventListener;
import io.webfolder.cdp.session.Session;
import io.webfolder.cdp.session.SessionFactory;
import io.webfolder.cdp.type.constant.TransferMode;
import io.webfolder.cdp.type.tracing.StreamFormat;

public class Profiling {

    public static void main(String[] args) {
        Launcher launcher = new Launcher();

        try (SessionFactory factory = launcher.launch();
                            Session session = factory.create()) {

            Tracing tracing = session.getCommand().getTracing();
            tracing.start("*", // * => trace all types of categories
                          "sampling-frequency=10000",
                          500D,
                          TransferMode.ReportEvents,
                          StreamFormat.Json,
                          null,
                          null);

            session.navigate("https://webfolder.io/");
            session.waitDocumentReady();

            CountDownLatch tracingLatch = new CountDownLatch(1);

            session.addEventListener(new EventListener() {

                @Override
                public void onEvent(Events event, Object value) {
                    if (Events.TracingDataCollected.equals(event)) {
                        DataCollected dataCollected = (DataCollected) value;
                        //
                        // Tracing Event is not part of the published DevTools protocol as of version 1.1
                        //
                        // See http://chromedriver.chromium.org/logging/performance-log for more details
                        //
                        for (Map<String, Object> next : dataCollected.getValue()) {
                            System.out.println("Tracing Event: " + next);
                        }
                    } else if (Events.TracingTracingComplete.equals(event)) {
                        tracingLatch.countDown();
                    }
                }
            });

            tracing.end();
            tracingLatch.await();

            System.out.println("Tracing ended");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            launcher.kill();
        }
    }
}
