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
import io.webfolder.cdp.command.Browser;
import io.webfolder.cdp.type.browser.Bounds;
import io.webfolder.cdp.type.browser.GetVersionResult;
import io.webfolder.cdp.type.browser.GetWindowForTargetResult;
import io.webfolder.cdp.type.browser.Histogram;
import io.webfolder.cdp.type.browser.PermissionDescriptor;
import io.webfolder.cdp.type.browser.PermissionSetting;
import io.webfolder.cdp.type.browser.PermissionType;
import java.util.List;
import com.google.gson.reflect.TypeToken;

public class BrowserImpl implements Browser {

	private static final Object[] EMPTY_VALUES = new Object[]{};
	private static final String[] EMPTY_ARGS = new String[]{};
	private final SessionInvocationHandler handler;
	private static final TypeToken<List<String>> TT_BROWSER_COMMAND_LINE = new TypeToken<List<String>>() { };
	private static final TypeToken<List<Histogram>> TT_HISTOGRAMS = new TypeToken<List<Histogram>>() { };

	public BrowserImpl(SessionInvocationHandler handler) {
		this.handler = handler;
	}

	@Override
	public void setPermission(String origin, PermissionDescriptor permission, PermissionSetting setting,
			String browserContextId) {
		handler.invoke("Browser", "setPermission", "Browser.setPermission", null, void.class, null, true, false, false,
				new String[]{"origin", "permission", "setting", "browserContextId"},
				new Object[]{origin, permission, setting, browserContextId});
	}

	@Override
	public void grantPermissions(String origin, PermissionType permissions, String browserContextId) {
		handler.invoke("Browser", "grantPermissions", "Browser.grantPermissions", null, void.class, null, true, false,
				false, new String[]{"origin", "permissions", "browserContextId"},
				new Object[]{origin, permissions, browserContextId});
	}

	@Override
	public void resetPermissions(String browserContextId) {
		handler.invoke("Browser", "resetPermissions", "Browser.resetPermissions", null, void.class, null, true, false,
				false, new String[]{"browserContextId"}, new Object[]{browserContextId});
	}

	@Override
	public void close() {
		handler.invoke("Browser", "close", "Browser.close", null, void.class, null, true, false, false, EMPTY_ARGS,
				EMPTY_VALUES);
	}

	@Override
	public void crash() {
		handler.invoke("Browser", "crash", "Browser.crash", null, void.class, null, true, false, false, EMPTY_ARGS,
				EMPTY_VALUES);
	}

	@Override
	public void crashGpuProcess() {
		handler.invoke("Browser", "crashGpuProcess", "Browser.crashGpuProcess", null, void.class, null, true, false,
				false, EMPTY_ARGS, EMPTY_VALUES);
	}

	@Override
	public GetVersionResult getVersion() {
		return (GetVersionResult) handler.invoke("Browser", "getVersion", "Browser.getVersion", null,
				GetVersionResult.class, null, false, false, false, EMPTY_ARGS, EMPTY_VALUES);
	}

	@Override
	@java.lang.SuppressWarnings("unchecked")
	public List<String> getBrowserCommandLine() {
		return (List<String>) handler.invoke("Browser", "getBrowserCommandLine", "Browser.getBrowserCommandLine",
				"arguments", List.class, TT_BROWSER_COMMAND_LINE.getType(), false, false, false, EMPTY_ARGS,
				EMPTY_VALUES);
	}

	@Override
	@java.lang.SuppressWarnings("unchecked")
	public List<Histogram> getHistograms(String query, Boolean delta) {
		return (List<Histogram>) handler.invoke("Browser", "getHistograms", "Browser.getHistograms", "histograms",
				List.class, TT_HISTOGRAMS.getType(), false, false, false, new String[]{"query", "delta"},
				new Object[]{query, delta});
	}

	@Override
	public Histogram getHistogram(String name, Boolean delta) {
		return (Histogram) handler.invoke("Browser", "getHistogram", "Browser.getHistogram", "histogram",
				Histogram.class, null, false, false, false, new String[]{"name", "delta"}, new Object[]{name, delta});
	}

	@Override
	public Bounds getWindowBounds(Integer windowId) {
		return (Bounds) handler.invoke("Browser", "getWindowBounds", "Browser.getWindowBounds", "bounds", Bounds.class,
				null, false, false, false, new String[]{"windowId"}, new Object[]{windowId});
	}

	@Override
	public GetWindowForTargetResult getWindowForTarget(String targetId) {
		return (GetWindowForTargetResult) handler.invoke("Browser", "getWindowForTarget", "Browser.getWindowForTarget",
				null, GetWindowForTargetResult.class, null, false, false, false, new String[]{"targetId"},
				new Object[]{targetId});
	}

	@Override
	public void setWindowBounds(Integer windowId, Bounds bounds) {
		handler.invoke("Browser", "setWindowBounds", "Browser.setWindowBounds", null, void.class, null, true, false,
				false, new String[]{"windowId", "bounds"}, new Object[]{windowId, bounds});
	}

	@Override
	public void setDockTile(String badgeLabel, String image) {
		handler.invoke("Browser", "setDockTile", "Browser.setDockTile", null, void.class, null, true, false, false,
				new String[]{"badgeLabel", "image"}, new Object[]{badgeLabel, image});
	}

	@Override
	public void setPermission(String origin, PermissionDescriptor permission, PermissionSetting setting) {
		handler.invoke("Browser", "setPermission", "Browser.setPermission", null, void.class, null, true, false, false,
				new String[]{"origin", "permission", "setting"}, new Object[]{origin, permission, setting});
	}

	@Override
	public void grantPermissions(String origin, PermissionType permissions) {
		handler.invoke("Browser", "grantPermissions", "Browser.grantPermissions", null, void.class, null, true, false,
				false, new String[]{"origin", "permissions"}, new Object[]{origin, permissions});
	}

	@Override
	public void resetPermissions() {
		handler.invoke("Browser", "resetPermissions", "Browser.resetPermissions", null, void.class, null, true, false,
				false, EMPTY_ARGS, EMPTY_VALUES);
	}

	@Override
	@java.lang.SuppressWarnings("unchecked")
	public List<Histogram> getHistograms() {
		return (List<Histogram>) handler.invoke("Browser", "getHistograms", "Browser.getHistograms", "histograms",
				List.class, TT_HISTOGRAMS.getType(), false, false, false, EMPTY_ARGS, EMPTY_VALUES);
	}

	@Override
	public Histogram getHistogram(String name) {
		return (Histogram) handler.invoke("Browser", "getHistogram", "Browser.getHistogram", "histogram",
				Histogram.class, null, false, false, false, new String[]{"name"}, new Object[]{name});
	}

	@Override
	public GetWindowForTargetResult getWindowForTarget() {
		return (GetWindowForTargetResult) handler.invoke("Browser", "getWindowForTarget", "Browser.getWindowForTarget",
				null, GetWindowForTargetResult.class, null, false, false, false, EMPTY_ARGS, EMPTY_VALUES);
	}

	@Override
	public void setDockTile() {
		handler.invoke("Browser", "setDockTile", "Browser.setDockTile", null, void.class, null, true, false, false,
				EMPTY_ARGS, EMPTY_VALUES);
	}
}
