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

import io.webfolder.cdp.session.SessionInvocationHandler;
import io.webfolder.cdp.command.ServiceWorker;

public class ServiceWorkerImpl implements ServiceWorker {

    private static final String[] EMPTY_ARGS = new String[] {};
    private static final Object[] EMPTY_VALUES = new Object[] {};
    private final SessionInvocationHandler handler;

    public ServiceWorkerImpl(SessionInvocationHandler handler) {
        this.handler = handler;
    }

    @Override
    public void deliverPushMessage(String origin, String registrationId, String data) {
        handler.invoke("ServiceWorker", "deliverPushMessage", "ServiceWorker.deliverPushMessage", null, void.class,
                null, true, false, false, new String[] { "origin", "registrationId", "data" },
                new Object[] { origin, registrationId, data });
    }

    @Override
    public void disable() {
        handler.invoke("ServiceWorker", "disable", "ServiceWorker.disable", null, void.class, null, true, false, true,
                EMPTY_ARGS, EMPTY_VALUES);
    }

    @Override
    public void dispatchSyncEvent(String origin, String registrationId, String tag, Boolean lastChance) {
        handler.invoke("ServiceWorker", "dispatchSyncEvent", "ServiceWorker.dispatchSyncEvent", null, void.class, null,
                true, false, false, new String[] { "origin", "registrationId", "tag", "lastChance" },
                new Object[] { origin, registrationId, tag, lastChance });
    }

    @Override
    public void enable() {
        handler.invoke("ServiceWorker", "enable", "ServiceWorker.enable", null, void.class, null, true, true, false,
                EMPTY_ARGS, EMPTY_VALUES);
    }

    @Override
    public void inspectWorker(String versionId) {
        handler.invoke("ServiceWorker", "inspectWorker", "ServiceWorker.inspectWorker", null, void.class, null, true,
                false, false, new String[] { "versionId" }, new Object[] { versionId });
    }

    @Override
    public void setForceUpdateOnPageLoad(Boolean forceUpdateOnPageLoad) {
        handler.invoke("ServiceWorker", "setForceUpdateOnPageLoad", "ServiceWorker.setForceUpdateOnPageLoad", null,
                void.class, null, true, false, false, new String[] { "forceUpdateOnPageLoad" },
                new Object[] { forceUpdateOnPageLoad });
    }

    @Override
    public void skipWaiting(String scopeURL) {
        handler.invoke("ServiceWorker", "skipWaiting", "ServiceWorker.skipWaiting", null, void.class, null, true, false,
                false, new String[] { "scopeURL" }, new Object[] { scopeURL });
    }

    @Override
    public void startWorker(String scopeURL) {
        handler.invoke("ServiceWorker", "startWorker", "ServiceWorker.startWorker", null, void.class, null, true, false,
                false, new String[] { "scopeURL" }, new Object[] { scopeURL });
    }

    @Override
    public void stopAllWorkers() {
        handler.invoke("ServiceWorker", "stopAllWorkers", "ServiceWorker.stopAllWorkers", null, void.class, null, true,
                false, false, EMPTY_ARGS, EMPTY_VALUES);
    }

    @Override
    public void stopWorker(String versionId) {
        handler.invoke("ServiceWorker", "stopWorker", "ServiceWorker.stopWorker", null, void.class, null, true, false,
                false, new String[] { "versionId" }, new Object[] { versionId });
    }

    @Override
    public void unregister(String scopeURL) {
        handler.invoke("ServiceWorker", "unregister", "ServiceWorker.unregister", null, void.class, null, true, false,
                false, new String[] { "scopeURL" }, new Object[] { scopeURL });
    }

    @Override
    public void updateRegistration(String scopeURL) {
        handler.invoke("ServiceWorker", "updateRegistration", "ServiceWorker.updateRegistration", null, void.class,
                null, true, false, false, new String[] { "scopeURL" }, new Object[] { scopeURL });
    }
}