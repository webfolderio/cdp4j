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

import java.util.List;

import com.vimeo.stag.UseStag;

import io.webfolder.cdp.annotation.Domain;
import io.webfolder.cdp.annotation.EventName;
import io.webfolder.cdp.type.dom.Node;

/**
 * Fired when backend wants to provide client with the missing DOM structure
 * This happens upon
 * most of the calls requesting node ids
 */
@Domain("DOM")
@EventName("setChildNodes")
@UseStag
public class SetChildNodes {
    private Integer parentId;

    private List<Node> nodes;

    /**
     * Parent node id to populate with children.
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * Parent node id to populate with children.
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /**
     * Child nodes array.
     */
    public List<Node> getNodes() {
        return nodes;
    }

    /**
     * Child nodes array.
     */
    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }
}
