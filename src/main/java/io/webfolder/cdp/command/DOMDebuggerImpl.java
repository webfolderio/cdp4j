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
import io.webfolder.cdp.type.domdebugger.DOMBreakpointType;
import io.webfolder.cdp.type.domdebugger.EventListener;

public class DOMDebuggerImpl implements DOMDebugger {

	private final SessionInvocationHandler handler;
	private static final TypeToken<List<EventListener>> TT_EVENT_LISTENERS = new TypeToken<List<EventListener>>() { };

	public DOMDebuggerImpl(SessionInvocationHandler handler) {
		this.handler = handler;
	}

	@Override
	@java.lang.SuppressWarnings("unchecked")
	public List<EventListener> getEventListeners(String objectId, Integer depth, Boolean pierce) {
		return (List<EventListener>) handler.invoke("DOMDebugger", "getEventListeners", "DOMDebugger.getEventListeners",
				"listeners", List.class, TT_EVENT_LISTENERS.getType(), false, false, false,
				new String[]{"objectId", "depth", "pierce"}, new Object[]{objectId, depth, pierce});
	}

	@Override
	public void removeDOMBreakpoint(Integer nodeId, DOMBreakpointType type) {
		handler.invoke("DOMDebugger", "removeDOMBreakpoint", "DOMDebugger.removeDOMBreakpoint", null, void.class, null,
				true, false, false, new String[]{"nodeId", "type"}, new Object[]{nodeId, type});
	}

	@Override
	public void removeEventListenerBreakpoint(String eventName, String targetName) {
		handler.invoke("DOMDebugger", "removeEventListenerBreakpoint", "DOMDebugger.removeEventListenerBreakpoint",
				null, void.class, null, true, false, false, new String[]{"eventName", "targetName"},
				new Object[]{eventName, targetName});
	}

	@Override
	public void removeInstrumentationBreakpoint(String eventName) {
		handler.invoke("DOMDebugger", "removeInstrumentationBreakpoint", "DOMDebugger.removeInstrumentationBreakpoint",
				null, void.class, null, true, false, false, new String[]{"eventName"}, new Object[]{eventName});
	}

	@Override
	public void removeXHRBreakpoint(String url) {
		handler.invoke("DOMDebugger", "removeXHRBreakpoint", "DOMDebugger.removeXHRBreakpoint", null, void.class, null,
				true, false, false, new String[]{"url"}, new Object[]{url});
	}

	@Override
	public void setDOMBreakpoint(Integer nodeId, DOMBreakpointType type) {
		handler.invoke("DOMDebugger", "setDOMBreakpoint", "DOMDebugger.setDOMBreakpoint", null, void.class, null, true,
				false, false, new String[]{"nodeId", "type"}, new Object[]{nodeId, type});
	}

	@Override
	public void setEventListenerBreakpoint(String eventName, String targetName) {
		handler.invoke("DOMDebugger", "setEventListenerBreakpoint", "DOMDebugger.setEventListenerBreakpoint", null,
				void.class, null, true, false, false, new String[]{"eventName", "targetName"},
				new Object[]{eventName, targetName});
	}

	@Override
	public void setInstrumentationBreakpoint(String eventName) {
		handler.invoke("DOMDebugger", "setInstrumentationBreakpoint", "DOMDebugger.setInstrumentationBreakpoint", null,
				void.class, null, true, false, false, new String[]{"eventName"}, new Object[]{eventName});
	}

	@Override
	public void setXHRBreakpoint(String url) {
		handler.invoke("DOMDebugger", "setXHRBreakpoint", "DOMDebugger.setXHRBreakpoint", null, void.class, null, true,
				false, false, new String[]{"url"}, new Object[]{url});
	}

	@Override
	@java.lang.SuppressWarnings("unchecked")
	public List<EventListener> getEventListeners(String objectId) {
		return (List<EventListener>) handler.invoke("DOMDebugger", "getEventListeners", "DOMDebugger.getEventListeners",
				"listeners", List.class, TT_EVENT_LISTENERS.getType(), false, false, false, new String[]{"objectId"},
				new Object[]{objectId});
	}

	@Override
	public void removeEventListenerBreakpoint(String eventName) {
		handler.invoke("DOMDebugger", "removeEventListenerBreakpoint", "DOMDebugger.removeEventListenerBreakpoint",
				null, void.class, null, true, false, false, new String[]{"eventName"}, new Object[]{eventName});
	}

	@Override
	public void setEventListenerBreakpoint(String eventName) {
		handler.invoke("DOMDebugger", "setEventListenerBreakpoint", "DOMDebugger.setEventListenerBreakpoint", null,
				void.class, null, true, false, false, new String[]{"eventName"}, new Object[]{eventName});
	}
}
