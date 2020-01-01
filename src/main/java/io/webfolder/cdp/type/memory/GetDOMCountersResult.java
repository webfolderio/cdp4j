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
package io.webfolder.cdp.type.memory;

import com.vimeo.stag.UseStag;

@UseStag
public class GetDOMCountersResult {
    private Integer documents;

    private Integer nodes;

    private Integer jsEventListeners;

    public Integer getDocuments() {
        return documents;
    }

    public Integer getNodes() {
        return nodes;
    }

    public Integer getJsEventListeners() {
        return jsEventListeners;
    }

    public void setDocuments(Integer documents) {
        this.documents = documents;
    }

    public void setNodes(Integer nodes) {
        this.nodes = nodes;
    }

    public void setJsEventListeners(Integer jsEventListeners) {
        this.jsEventListeners = jsEventListeners;
    }
}
