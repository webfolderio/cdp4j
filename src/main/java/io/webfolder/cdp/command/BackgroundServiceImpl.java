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
import io.webfolder.cdp.type.backgroundservice.ServiceName;

public class BackgroundServiceImpl implements BackgroundService {

	private final SessionInvocationHandler handler;

	public BackgroundServiceImpl(SessionInvocationHandler handler) {
		this.handler = handler;
	}

	@Override
	public void startObserving(ServiceName service) {
		handler.invoke("BackgroundService", "startObserving", "BackgroundService.startObserving", null, void.class,
				null, true, false, false, new String[]{"service"}, new Object[]{service});
	}

	@Override
	public void stopObserving(ServiceName service) {
		handler.invoke("BackgroundService", "stopObserving", "BackgroundService.stopObserving", null, void.class, null,
				true, false, false, new String[]{"service"}, new Object[]{service});
	}

	@Override
	public void setRecording(Boolean shouldRecord, ServiceName service) {
		handler.invoke("BackgroundService", "setRecording", "BackgroundService.setRecording", null, void.class, null,
				true, false, false, new String[]{"shouldRecord", "service"}, new Object[]{shouldRecord, service});
	}

	@Override
	public void clearEvents(ServiceName service) {
		handler.invoke("BackgroundService", "clearEvents", "BackgroundService.clearEvents", null, void.class, null,
				true, false, false, new String[]{"service"}, new Object[]{service});
	}
}
