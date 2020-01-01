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
package io.webfolder.cdp.type.domsnapshot;

import java.util.List;

import com.vimeo.stag.UseStag;

@UseStag
public class GetSnapshotResult {
    private List<DOMNode> domNodes;

    private List<LayoutTreeNode> layoutTreeNodes;

    private List<ComputedStyle> computedStyles;

    public List<DOMNode> getDomNodes() {
        return domNodes;
    }

    public List<LayoutTreeNode> getLayoutTreeNodes() {
        return layoutTreeNodes;
    }

    public List<ComputedStyle> getComputedStyles() {
        return computedStyles;
    }

    public void setDomNodes(List<DOMNode> domNodes) {
        this.domNodes = domNodes;
    }

    public void setLayoutTreeNodes(List<LayoutTreeNode> layoutTreeNodes) {
        this.layoutTreeNodes = layoutTreeNodes;
    }

    public void setComputedStyles(List<ComputedStyle> computedStyles) {
        this.computedStyles = computedStyles;
    }
}
