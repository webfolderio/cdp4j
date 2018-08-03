/**
 * cdp4j - Chrome DevTools Protocol for Java
 * Copyright © 2017, 2018 WebFolder OÜ (support@webfolder.io)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package io.webfolder.cdp.sample;

import static java.lang.Thread.sleep;
import static java.util.Arrays.asList;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

import io.webfolder.cdp.AdaptiveProcessManager;
import io.webfolder.cdp.Launcher;
import io.webfolder.cdp.session.Session;
import io.webfolder.cdp.session.SessionFactory;

public class PdfPrinter implements AutoCloseable {

    private SessionFactory factory;

    private Timer timer = new Timer("cdp4j-task-manager", true);

    private Launcher launcher;

    private ExecutorService pool = Executors.newCachedThreadPool();

    private List<String> arguments;

    public PdfPrinter(List<String> arguments) {
        this.arguments = Collections.unmodifiableList(arguments);
    }
    
    public void execute(Consumer<Session> func) {
        pool.execute(() -> {
            String context = factory.createBrowserContext();
            Session session = factory.create(context);
            try {
                func.accept(session);
            } catch (Throwable t) {
                throw new RuntimeException(t);
            } finally {
                if ( session != null ) {
                    try {
                        session.close();
                    } catch (Throwable t) {
                        // ignore
                    }
                }
                if (factory.ping()) {
                    factory.disposeBrowserContext(context);
                }
            }
        });
    }

    public void init() {
        launcher = new Launcher();

        factory = launcher.launch(arguments);

        int delay = 1000;

        timer.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {
                if ( factory != null ) {
                    int     retryCount = 0;
                    boolean connected;
                    while ( ! ( connected = factory.ping() ) && retryCount < 50 ) {
                        try {
                            sleep(100);
                        } catch (InterruptedException e) {
                            // ignore
                        }
                        retryCount += 1;
                    }
                    if ( ! connected ) {
                        restart();
                    }
                }
            }
        }, delay, delay);
    }

    protected void restart() {
        SessionFactory oldFactory = factory;
        Thread shutdownThread = new Thread(() -> {
            try {
                oldFactory.close();
            } catch (Throwable t) {
                // ignore
            }
        });
        shutdownThread.setDaemon(true);
        shutdownThread.setName("cdp4j-task-manager");
        shutdownThread.start();

        launcher.kill();
        launcher = new Launcher();

        factory = launcher.launch();
    }

    @Override
    public void close() {
        pool.shutdown();
        timer.cancel();
        factory.close();
        launcher.kill();
    }

    public static void main(String[] args) {        
        PdfPrinter manager = new PdfPrinter(Arrays.asList("--disable-gpu", "--headless"));
        manager.init();

        List<String> urls = asList(
                                    "http://www.google.com",
                                    "http://www.bing.com");

        CountDownLatch latch = new CountDownLatch(urls.size());

        for (String next : urls) {
            manager.execute(new Consumer<Session>() {

                @Override
                public void accept(Session s) {
                    s.navigateAndWait(next);
                    try {
                        byte[] content = s.getCommand().getPage().printToPDF();
                        if ( content != null ) {
                            System.out.println("PDF size: " + content.length + " (" + next + ")");
                        }
                    } catch (Throwable e) {
                        //
                    } finally {
                        latch.countDown();
                    }
                }
            });
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            manager.close();
        }
    }
}
