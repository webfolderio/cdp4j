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
package io.webfolder.cdp.session;

import io.webfolder.cdp.command.Accessibility;
import io.webfolder.cdp.command.Animation;
import io.webfolder.cdp.command.ApplicationCache;
import io.webfolder.cdp.command.Audits;
import io.webfolder.cdp.command.BackgroundService;
import io.webfolder.cdp.command.Browser;
import io.webfolder.cdp.command.CSS;
import io.webfolder.cdp.command.CacheStorage;
import io.webfolder.cdp.command.Cast;
import io.webfolder.cdp.command.DOM;
import io.webfolder.cdp.command.DOMDebugger;
import io.webfolder.cdp.command.DOMSnapshot;
import io.webfolder.cdp.command.DOMStorage;
import io.webfolder.cdp.command.Database;
import io.webfolder.cdp.command.Debugger;
import io.webfolder.cdp.command.DeviceOrientation;
import io.webfolder.cdp.command.Emulation;
import io.webfolder.cdp.command.Fetch;
import io.webfolder.cdp.command.HeadlessExperimental;
import io.webfolder.cdp.command.HeapProfiler;
import io.webfolder.cdp.command.IO;
import io.webfolder.cdp.command.IndexedDB;
import io.webfolder.cdp.command.Input;
import io.webfolder.cdp.command.Inspector;
import io.webfolder.cdp.command.LayerTree;
import io.webfolder.cdp.command.Log;
import io.webfolder.cdp.command.Memory;
import io.webfolder.cdp.command.Network;
import io.webfolder.cdp.command.Overlay;
import io.webfolder.cdp.command.Page;
import io.webfolder.cdp.command.Performance;
import io.webfolder.cdp.command.Profiler;
import io.webfolder.cdp.command.Runtime;
import io.webfolder.cdp.command.Schema;
import io.webfolder.cdp.command.Security;
import io.webfolder.cdp.command.ServiceWorker;
import io.webfolder.cdp.command.Storage;
import io.webfolder.cdp.command.SystemInfo;
import io.webfolder.cdp.command.Target;
import io.webfolder.cdp.command.Tethering;
import io.webfolder.cdp.command.Tracing;
import io.webfolder.cdp.command.WebAudio;
import io.webfolder.cdp.command.WebAuthn;

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

    public Audits getAudits() {
        return getProxy(Audits.class);
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

    public Fetch getFetch() {
        return getProxy(Fetch.class);
    }

    public HeadlessExperimental getHeadlessExperimental() {
        return getProxy(HeadlessExperimental.class);
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

    public Performance getPerformance() {
        return getProxy(Performance.class);
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

    public BackgroundService getBackgroundService() {
        return getProxy(BackgroundService.class);
    }

    public Cast getCast() {
        return getProxy(Cast.class);
    }

    public WebAudio getWebAudio() {
        return getProxy(WebAudio.class);
    }

    public WebAuthn WebAuthn() {
        return getProxy(WebAuthn.class);
    }

    @SuppressWarnings("unchecked")
    private <T> T getProxy(Class<?> klass) {
        return (T) session.getCommand(klass);
    }
}
