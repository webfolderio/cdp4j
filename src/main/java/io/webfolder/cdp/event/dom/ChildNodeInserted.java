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
package io.webfolder.cdp.event.dom;

import com.vimeo.stag.UseStag;

import io.webfolder.cdp.annotation.Domain;
import io.webfolder.cdp.annotation.EventName;
import io.webfolder.cdp.type.dom.Node;

/**
 * Mirrors `DOMNodeInserted` event
 */
@Domain("DOM")
@EventName("childNodeInserted")
@UseStag
public class ChildNodeInserted {
    private Integer parentNodeId;

    private Integer previousNodeId;

    private Node node;

    /**
     * Id of the node that has changed.
     */
    public Integer getParentNodeId() {
        return parentNodeId;
    }

    /**
     * Id of the node that has changed.
     */
    public void setParentNodeId(Integer parentNodeId) {
        this.parentNodeId = parentNodeId;
    }

    /**
     * If of the previous siblint.
     */
    public Integer getPreviousNodeId() {
        return previousNodeId;
    }

    /**
     * If of the previous siblint.
     */
    public void setPreviousNodeId(Integer previousNodeId) {
        this.previousNodeId = previousNodeId;
    }

    /**
     * Inserted node data.
     */
    public Node getNode() {
        return node;
    }

    /**
     * Inserted node data.
     */
    public void setNode(Node node) {
        this.node = node;
    }
}
