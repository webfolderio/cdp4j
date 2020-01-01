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

/**
 * Table containing nodes
 */
@UseStag
public class NodeTreeSnapshot {
    private List<Integer> parentIndex;

    private List<Integer> nodeType;

    private List<Integer> nodeName;

    private List<Integer> nodeValue;

    private List<Integer> backendNodeId;

    private RareStringData textValue;

    private RareStringData inputValue;

    private RareBooleanData inputChecked;

    private RareBooleanData optionSelected;

    private RareIntegerData contentDocumentIndex;

    private RareStringData pseudoType;

    private RareBooleanData isClickable;

    private RareStringData currentSourceURL;

    private RareStringData originURL;

    /**
     * Parent node index.
     */
    public List<Integer> getParentIndex() {
        return parentIndex;
    }

    /**
     * Parent node index.
     */
    public void setParentIndex(List<Integer> parentIndex) {
        this.parentIndex = parentIndex;
    }

    /**
     * `Node`'s nodeType.
     */
    public List<Integer> getNodeType() {
        return nodeType;
    }

    /**
     * `Node`'s nodeType.
     */
    public void setNodeType(List<Integer> nodeType) {
        this.nodeType = nodeType;
    }

    /**
     * `Node`'s nodeName.
     */
    public List<Integer> getNodeName() {
        return nodeName;
    }

    /**
     * `Node`'s nodeName.
     */
    public void setNodeName(List<Integer> nodeName) {
        this.nodeName = nodeName;
    }

    /**
     * `Node`'s nodeValue.
     */
    public List<Integer> getNodeValue() {
        return nodeValue;
    }

    /**
     * `Node`'s nodeValue.
     */
    public void setNodeValue(List<Integer> nodeValue) {
        this.nodeValue = nodeValue;
    }

    /**
     * `Node`'s id, corresponds to DOM.Node.backendNodeId.
     */
    public List<Integer> getBackendNodeId() {
        return backendNodeId;
    }

    /**
     * `Node`'s id, corresponds to DOM.Node.backendNodeId.
     */
    public void setBackendNodeId(List<Integer> backendNodeId) {
        this.backendNodeId = backendNodeId;
    }

    /**
     * Only set for textarea elements, contains the text value.
     */
    public RareStringData getTextValue() {
        return textValue;
    }

    /**
     * Only set for textarea elements, contains the text value.
     */
    public void setTextValue(RareStringData textValue) {
        this.textValue = textValue;
    }

    /**
     * Only set for input elements, contains the input's associated text value.
     */
    public RareStringData getInputValue() {
        return inputValue;
    }

    /**
     * Only set for input elements, contains the input's associated text value.
     */
    public void setInputValue(RareStringData inputValue) {
        this.inputValue = inputValue;
    }

    /**
     * Only set for radio and checkbox input elements, indicates if the element has been checked
     */
    public RareBooleanData getInputChecked() {
        return inputChecked;
    }

    /**
     * Only set for radio and checkbox input elements, indicates if the element has been checked
     */
    public void setInputChecked(RareBooleanData inputChecked) {
        this.inputChecked = inputChecked;
    }

    /**
     * Only set for option elements, indicates if the element has been selected
     */
    public RareBooleanData getOptionSelected() {
        return optionSelected;
    }

    /**
     * Only set for option elements, indicates if the element has been selected
     */
    public void setOptionSelected(RareBooleanData optionSelected) {
        this.optionSelected = optionSelected;
    }

    /**
     * The index of the document in the list of the snapshot documents.
     */
    public RareIntegerData getContentDocumentIndex() {
        return contentDocumentIndex;
    }

    /**
     * The index of the document in the list of the snapshot documents.
     */
    public void setContentDocumentIndex(RareIntegerData contentDocumentIndex) {
        this.contentDocumentIndex = contentDocumentIndex;
    }

    /**
     * Type of a pseudo element node.
     */
    public RareStringData getPseudoType() {
        return pseudoType;
    }

    /**
     * Type of a pseudo element node.
     */
    public void setPseudoType(RareStringData pseudoType) {
        this.pseudoType = pseudoType;
    }

    /**
     * Whether this DOM node responds to mouse clicks. This includes nodes that have had click
     * event listeners attached via JavaScript as well as anchor tags that naturally navigate when
     * clicked.
     */
    public RareBooleanData getIsClickable() {
        return isClickable;
    }

    /**
     * Whether this DOM node responds to mouse clicks. This includes nodes that have had click
     * event listeners attached via JavaScript as well as anchor tags that naturally navigate when
     * clicked.
     */
    public void setIsClickable(RareBooleanData isClickable) {
        this.isClickable = isClickable;
    }

    /**
     * The selected url for nodes with a srcset attribute.
     */
    public RareStringData getCurrentSourceURL() {
        return currentSourceURL;
    }

    /**
     * The selected url for nodes with a srcset attribute.
     */
    public void setCurrentSourceURL(RareStringData currentSourceURL) {
        this.currentSourceURL = currentSourceURL;
    }

    /**
     * The url of the script (if any) that generates this node.
     */
    public RareStringData getOriginURL() {
        return originURL;
    }

    /**
     * The url of the script (if any) that generates this node.
     */
    public void setOriginURL(RareStringData originURL) {
        this.originURL = originURL;
    }
}
