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
import io.webfolder.cdp.type.constant.TimeDomain;
import io.webfolder.cdp.type.performance.Metric;

public class PerformanceImpl implements Performance {

	private static final Object[] EMPTY_VALUES = new Object[]{};
	private static final String[] EMPTY_ARGS = new String[]{};
	private final SessionInvocationHandler handler;
	private static final TypeToken<List<Metric>> GET_METRICS = new TypeToken<List<Metric>>() { };

	public PerformanceImpl(SessionInvocationHandler handler) {
		this.handler = handler;
	}

	@Override
	public void disable() {
		handler.invoke("Performance", "disable", "Performance.disable", null, void.class, null, true, false, true,
				EMPTY_ARGS, EMPTY_VALUES);
	}

	@Override
	public void enable(TimeDomain timeDomain) {
		handler.invoke("Performance", "enable", "Performance.enable", null, void.class, null, true, true, false,
				new String[]{"timeDomain"}, new Object[]{timeDomain});
	}

	@Override
	public void setTimeDomain(TimeDomain timeDomain) {
		handler.invoke("Performance", "setTimeDomain", "Performance.setTimeDomain", null, void.class, null, true, false,
				false, new String[]{"timeDomain"}, new Object[]{timeDomain});
	}

	@Override
	@java.lang.SuppressWarnings("unchecked")
	public List<Metric> getMetrics() {
		return (List<Metric>) handler.invoke("Performance", "getMetrics", "Performance.getMetrics", "metrics",
				List.class, GET_METRICS.getType(), false, false, false, EMPTY_ARGS, EMPTY_VALUES);
	}

	@Override
	public void enable() {
		handler.invoke("Performance", "enable", "Performance.enable", null, void.class, null, true, true, false,
				EMPTY_ARGS, EMPTY_VALUES);
	}
}