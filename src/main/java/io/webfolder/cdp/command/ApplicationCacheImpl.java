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
package io.webfolder.cdp.command;

import java.util.List;

import com.google.gson.reflect.TypeToken;

import io.webfolder.cdp.session.SessionInvocationHandler;
import io.webfolder.cdp.type.applicationcache.FrameWithManifest;

public class ApplicationCacheImpl implements ApplicationCache {

    private static final Object[] EMPTY_VALUES = new Object[]{};
    private static final String[] EMPTY_ARGS = new String[]{};
    private final SessionInvocationHandler handler;
    private static final TypeToken<List<FrameWithManifest>> GET_FRAMES_WITH_MANIFESTS = new TypeToken<List<FrameWithManifest>>() { };

    public ApplicationCacheImpl(SessionInvocationHandler handler) {
        this.handler = handler;
    }

    @Override
    public void enable() {
        handler.invoke("ApplicationCache", "enable", "ApplicationCache.enable", null, void.class, null, true, true,
                false, EMPTY_ARGS, EMPTY_VALUES);
    }

    @Override
    public io.webfolder.cdp.type.applicationcache.ApplicationCache getApplicationCacheForFrame(String frameId) {
        return (io.webfolder.cdp.type.applicationcache.ApplicationCache) handler.invoke("ApplicationCache", "getApplicationCacheForFrame",
                "ApplicationCache.getApplicationCacheForFrame", "applicationCache", ApplicationCache.class, null, false,
                false, false, new String[]{"frameId"}, new Object[]{frameId});
    }

    @Override
    @java.lang.SuppressWarnings("unchecked")
    public List<FrameWithManifest> getFramesWithManifests() {
        return (List<FrameWithManifest>) handler.invoke("ApplicationCache", "getFramesWithManifests",
                "ApplicationCache.getFramesWithManifests", "frameIds", List.class, GET_FRAMES_WITH_MANIFESTS.getType(),
                false, false, false, EMPTY_ARGS, EMPTY_VALUES);
    }

    @Override
    public String getManifestForFrame(String frameId) {
        return (String) handler.invoke("ApplicationCache", "getManifestForFrame",
                "ApplicationCache.getManifestForFrame", "manifestURL", String.class, null, false, false, false,
                new String[]{"frameId"}, new Object[]{frameId});
    }
}