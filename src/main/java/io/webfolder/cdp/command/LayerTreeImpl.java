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
import io.webfolder.cdp.command.LayerTree;
import io.webfolder.cdp.type.dom.Rect;
import io.webfolder.cdp.type.layertree.PictureTile;

import java.util.List;
import com.google.gson.reflect.TypeToken;

public class LayerTreeImpl implements LayerTree {

	private static final Object[] EMPTY_VALUES = new Object[]{};
	private static final String[] EMPTY_ARGS = new String[]{};
	private final SessionInvocationHandler handler;
	private static final TypeToken<List<String>> TT_LIST_STRING = new TypeToken<List<String>>() { };
	private static final TypeToken<List<Double>> TT_LIST_DOUBLE = new TypeToken<List<Double>>() { };

	public LayerTreeImpl(SessionInvocationHandler handler) {
		this.handler = handler;
	}

	@Override
	@java.lang.SuppressWarnings("unchecked")
	public List<String> compositingReasons(String layerId) {
		return (List<String>) handler.invoke("LayerTree", "compositingReasons", "LayerTree.compositingReasons",
				"compositingReasons", List.class, TT_LIST_STRING.getType(), false, false, false,
				new String[]{"layerId"}, new Object[]{layerId});
	}

	@Override
	public void disable() {
		handler.invoke("LayerTree", "disable", "LayerTree.disable", null, void.class, null, true, false, true,
				EMPTY_ARGS, EMPTY_VALUES);
	}

	@Override
	public void enable() {
		handler.invoke("LayerTree", "enable", "LayerTree.enable", null, void.class, null, true, true, false, EMPTY_ARGS,
				EMPTY_VALUES);
	}

	@Override
	public String loadSnapshot(List<PictureTile> tiles) {
		return (String) handler.invoke("LayerTree", "loadSnapshot", "LayerTree.loadSnapshot", "snapshotId",
				String.class, null, false, false, false, new String[]{"tiles"}, new Object[]{tiles});
	}

	@Override
	public String makeSnapshot(String layerId) {
		return (String) handler.invoke("LayerTree", "makeSnapshot", "LayerTree.makeSnapshot", "snapshotId",
				String.class, null, false, false, false, new String[]{"layerId"}, new Object[]{layerId});
	}

	@Override
	@java.lang.SuppressWarnings("unchecked")
	public List<Double> profileSnapshot(String snapshotId, Integer minRepeatCount, Double minDuration, Rect clipRect) {
		return (List<Double>) handler.invoke("LayerTree", "profileSnapshot", "LayerTree.profileSnapshot", "timings",
				List.class, TT_LIST_DOUBLE.getType(), false, false, false,
				new String[]{"snapshotId", "minRepeatCount", "minDuration", "clipRect"},
				new Object[]{snapshotId, minRepeatCount, minDuration, clipRect});
	}

	@Override
	public void releaseSnapshot(String snapshotId) {
		handler.invoke("LayerTree", "releaseSnapshot", "LayerTree.releaseSnapshot", null, void.class, null, true, false,
				false, new String[]{"snapshotId"}, new Object[]{snapshotId});
	}

	@Override
	public String replaySnapshot(String snapshotId, Integer fromStep, Integer toStep, Double scale) {
		return (String) handler.invoke("LayerTree", "replaySnapshot", "LayerTree.replaySnapshot", "dataURL",
				String.class, null, false, false, false, new String[]{"snapshotId", "fromStep", "toStep", "scale"},
				new Object[]{snapshotId, fromStep, toStep, scale});
	}

	@Override
	@java.lang.SuppressWarnings("unchecked")
	public List<Double> profileSnapshot(String snapshotId) {
		return (List<Double>) handler.invoke("LayerTree", "profileSnapshot", "LayerTree.profileSnapshot", "timings",
				List.class, TT_LIST_DOUBLE.getType(), false, false, false, new String[]{"snapshotId"},
				new Object[]{snapshotId});
	}

	@Override
	public String replaySnapshot(String snapshotId) {
		return (String) handler.invoke("LayerTree", "replaySnapshot", "LayerTree.replaySnapshot", "dataURL",
				String.class, null, false, false, false, new String[]{"snapshotId"}, new Object[]{snapshotId});
	}
}
