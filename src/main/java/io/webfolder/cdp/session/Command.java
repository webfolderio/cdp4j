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
package io.webfolder.cdp.session;

import io.webfolder.cdp.command.*;
import io.webfolder.cdp.command.Runtime;

@SuppressWarnings("deprecation")
public class Command {

    private final Session session;

    public Command(final Session session) {
        this.session = session;
    }

    public Accessibility getAccessibility() {
        return getProxy(Accessibility.class);
    }

    public Animation getAnimation() {
        return getProxy(Animation.class);
    }

    public ApplicationCache getApplicationCache() {
        return getProxy(ApplicationCache.class);
    }

    public Browser getBrowser() {
        return getProxy(Browser.class);
    }

    public CacheStorage getCacheStorage() {
        return getProxy(CacheStorage.class);
    }
 
    public CSS getCSS() {
        return getProxy(CSS.class);
    }

    public Database getDatabase() {
        return getProxy(Database.class);
    }
    
    public Debugger getDebugger() {
        return getProxy(Debugger.class);
    }

    public DeviceOrientation getDeviceOrientation() {
        return getProxy(DeviceOrientation.class);
    }

    public DOM getDOM() {
        return getProxy(DOM.class);
    }

    public DOMDebugger getDOMDebugger() {
        return getProxy(DOMDebugger.class);
    }

    public DOMStorage getDOMStorage() {
        return getProxy(DOMStorage.class);
    }

    public Emulation getEmulation() {
        return getProxy(Emulation.class);
    }

    public HeapProfiler getHeapProfiler() {
        return getProxy(HeapProfiler.class);
    }

    public IndexedDB getIndexedDB() {
        return getProxy(IndexedDB.class);
    }

    public Input getInput() {
        return getProxy(Input.class);
    }

    public Inspector getInspector() {
        return getProxy(Inspector.class);
    }

    public IO getIO() {
        return getProxy(IO.class);
    }

    public LayerTree getLayerTree() {
        return getProxy(LayerTree.class);
    }
   
    public Log getLog() {
        return getProxy(Log.class);
    }

    public Memory getMemory() {
        return getProxy(Memory.class);
    }

    public Network getNetwork() {
        return getProxy(Network.class);
    }

    public Page getPage() {
        return getProxy(Page.class);
    }

    public Profiler getProfiler() {
        return getProxy(Profiler.class);
    }

    public Overlay getOverlay() {
        return getProxy(Overlay.class);
    }

    public Runtime getRuntime() {
        return getProxy(Runtime.class);
    }

    public Schema getSchema() {
        return getProxy(Schema.class);
    }

    public Security getSecurity() {
        return getProxy(Security.class);
    }

    public ServiceWorker getServiceWorker() {
        return getProxy(ServiceWorker.class);
    }

    public Storage getStorage() {
        return getProxy(Storage.class);
    }

    public SystemInfo getSystemInfo() {
        return getProxy(SystemInfo.class);
    }

    public Target getTarget() {
        return getProxy(Target.class);
    }

    public Tethering getTethering() {
        return getProxy(Tethering.class);
    }

    public Tracing getTracing() {
        return getProxy(Tracing.class);
    }

    public DOMSnapshot getDOMSnapshot() {
        return getProxy(DOMSnapshot.class);
    }

    @SuppressWarnings("unchecked")
    private <T> T getProxy(Class<?> klass) {
        return (T) session.getProxy(klass);
    }
}
