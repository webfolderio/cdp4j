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

import static java.util.Arrays.asList;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import io.webfolder.cdp.type.domsnapshot.DOMNode;
import io.webfolder.cdp.type.domsnapshot.NameValue;

class TreeNode {

    private static final short ELEMENT_NODE = 1;

    private static final short TEXT_NODE = 3;

    private static final Set<String> VOID_ELEMENTS = new HashSet<>(asList(
                                                                    "AREA",
                                                                    "BASE",
                                                                    "BASEFONT",
                                                                    "BGSOUND",
                                                                    "BR",
                                                                    "COL",
                                                                    "COMMAND",
                                                                    "EMBED",
                                                                    "FRAME",
                                                                    "HR",
                                                                    "IMAGE",
                                                                    "IMG",
                                                                    "INPUT",
                                                                    "ISINDEX",
                                                                    "KEYGEN",
                                                                    "LINK",
                                                                    "MENUITEM",
                                                                    "META",
                                                                    "NEXTID",
                                                                    "PARAM",
                                                                    "SOURCE",
                                                                    "TRACK",
                                                                    "WBR"
                                                                ));

    private final DOMNode data;

    private TreeNode[] children;

    private int size;

    TreeNode(DOMNode data, int length) {
        this.data = data;
        this.children = new TreeNode[length];
    }

    TreeNode find(DOMNode node) {
        TreeNode found = null;
        if (data.equals(node)) {
            found = this;
        } else {
            for (int i = 0; i < size; i++) {
                TreeNode next = children[i];
                found = next.find(node);
                if ( found != null ) {
                    break;
                }
            }
        }
        return found;
    }

    void add(TreeNode child) {
        if (size == children.length) {
            TreeNode[] temp = new TreeNode[children.length * 2];
            System.arraycopy(children, 0, temp, 0, children.length);
            this.children = temp;
        }
        children[size++] = child;
    }

    @Override
    public String toString() {
        String content = "";
        String nodeName = data.getNodeName();
        if (nodeName.charAt(0) == '<') {
            return content;
        }
        short nodeType = data.getNodeType().shortValue();
        if (ELEMENT_NODE == nodeType) {
            content += "<" + nodeName;
            List<NameValue> attributes = data.getAttributes();
            if ( attributes != null ) {
                content += " ";
                int size = attributes.size();
                for (int i = 0; i < size; i++) {
                    NameValue next = attributes.get(i);
                    content += next.getName();
                    if ( next.getValue() != null && ! next.getValue().trim().isEmpty() ) {
                        content += "=\"" + next.getValue() + "\"";
                    }
                    if (i + 1 < size) {
                        content += " ";
                    }
                }
            }
            content += ">";
        }
        if (TEXT_NODE == nodeType) {
            content += data.getNodeValue();
        }
        for (int i = 0; i < size; i++) {
            content += children[i].toString();
        }
        if ( ELEMENT_NODE == nodeType && ! VOID_ELEMENTS.contains(nodeName) ) {
            content += "</" + nodeName + ">";
        }
        return content;
    }
}
