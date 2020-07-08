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
import io.webfolder.cdp.type.accessibility.AXNode;

public class AccessibilityImpl implements Accessibility {

    private static final Object[] EMPTY_VALUES = new Object[]{};
    private static final String[] EMPTY_ARGS = new String[]{};
    private final SessionInvocationHandler handler;
    private static final TypeToken<List<AXNode>> GET_PARTIAL_AX_TREE = new TypeToken<List<AXNode>>() { };
    private static final TypeToken<List<AXNode>> GET_FULL_AX_TREE = new TypeToken<List<AXNode>>() { };

    public AccessibilityImpl(SessionInvocationHandler handler) {
        this.handler = handler;
    }

    @Override
    public void disable() {
        handler.invoke("Accessibility", "disable", "Accessibility.disable", null, void.class, null, true, false, true,
                EMPTY_ARGS, EMPTY_VALUES);
    }

    @Override
    public void enable() {
        handler.invoke("Accessibility", "enable", "Accessibility.enable", null, void.class, null, true, true, false,
                EMPTY_ARGS, EMPTY_VALUES);
    }

    @Override
    @java.lang.SuppressWarnings("unchecked")
    public List<AXNode> getPartialAXTree(Integer nodeId, Integer backendNodeId, String objectId,
            Boolean fetchRelatives) {
        return (List<AXNode>) handler.invoke("Accessibility", "getPartialAXTree", "Accessibility.getPartialAXTree",
                "nodes", List.class, GET_PARTIAL_AX_TREE.getType(), false, false, false,
                new String[]{"nodeId", "backendNodeId", "objectId", "fetchRelatives"},
                new Object[]{nodeId, backendNodeId, objectId, fetchRelatives});
    }

    @Override
    @java.lang.SuppressWarnings("unchecked")
    public List<AXNode> getFullAXTree() {
        return (List<AXNode>) handler.invoke("Accessibility", "getFullAXTree", "Accessibility.getFullAXTree", "nodes",
                List.class, GET_FULL_AX_TREE.getType(), false, false, false, EMPTY_ARGS, EMPTY_VALUES);
    }

    @Override
    @java.lang.SuppressWarnings("unchecked")
    public List<AXNode> getPartialAXTree() {
        return (List<AXNode>) handler.invoke("Accessibility", "getPartialAXTree", "Accessibility.getPartialAXTree",
                "nodes", List.class, GET_PARTIAL_AX_TREE.getType(), false, false, false, EMPTY_ARGS, EMPTY_VALUES);
    }
}