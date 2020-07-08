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

/**
 * Document snapshot
 */
public class DocumentSnapshot {
    private Integer documentURL;

    private Integer title;

    private Integer baseURL;

    private Integer contentLanguage;

    private Integer encodingName;

    private Integer publicId;

    private Integer systemId;

    private Integer frameId;

    private NodeTreeSnapshot nodes;

    private LayoutTreeSnapshot layout;

    private TextBoxSnapshot textBoxes;

    private Double scrollOffsetX;

    private Double scrollOffsetY;

    private Double contentWidth;

    private Double contentHeight;

    /**
     * Document URL that Document or FrameOwner node points to.
     */
    public Integer getDocumentURL() {
        return documentURL;
    }

    /**
     * Document URL that Document or FrameOwner node points to.
     */
    public void setDocumentURL(Integer documentURL) {
        this.documentURL = documentURL;
    }

    /**
     * Document title.
     */
    public Integer getTitle() {
        return title;
    }

    /**
     * Document title.
     */
    public void setTitle(Integer title) {
        this.title = title;
    }

    /**
     * Base URL that Document or FrameOwner node uses for URL completion.
     */
    public Integer getBaseURL() {
        return baseURL;
    }

    /**
     * Base URL that Document or FrameOwner node uses for URL completion.
     */
    public void setBaseURL(Integer baseURL) {
        this.baseURL = baseURL;
    }

    /**
     * Contains the document's content language.
     */
    public Integer getContentLanguage() {
        return contentLanguage;
    }

    /**
     * Contains the document's content language.
     */
    public void setContentLanguage(Integer contentLanguage) {
        this.contentLanguage = contentLanguage;
    }

    /**
     * Contains the document's character set encoding.
     */
    public Integer getEncodingName() {
        return encodingName;
    }

    /**
     * Contains the document's character set encoding.
     */
    public void setEncodingName(Integer encodingName) {
        this.encodingName = encodingName;
    }

    /**
     * DocumentType node's publicId.
     */
    public Integer getPublicId() {
        return publicId;
    }

    /**
     * DocumentType node's publicId.
     */
    public void setPublicId(Integer publicId) {
        this.publicId = publicId;
    }

    /**
     * DocumentType node's systemId.
     */
    public Integer getSystemId() {
        return systemId;
    }

    /**
     * DocumentType node's systemId.
     */
    public void setSystemId(Integer systemId) {
        this.systemId = systemId;
    }

    /**
     * Frame ID for frame owner elements and also for the document node.
     */
    public Integer getFrameId() {
        return frameId;
    }

    /**
     * Frame ID for frame owner elements and also for the document node.
     */
    public void setFrameId(Integer frameId) {
        this.frameId = frameId;
    }

    /**
     * A table with dom nodes.
     */
    public NodeTreeSnapshot getNodes() {
        return nodes;
    }

    /**
     * A table with dom nodes.
     */
    public void setNodes(NodeTreeSnapshot nodes) {
        this.nodes = nodes;
    }

    /**
     * The nodes in the layout tree.
     */
    public LayoutTreeSnapshot getLayout() {
        return layout;
    }

    /**
     * The nodes in the layout tree.
     */
    public void setLayout(LayoutTreeSnapshot layout) {
        this.layout = layout;
    }

    /**
     * The post-layout inline text nodes.
     */
    public TextBoxSnapshot getTextBoxes() {
        return textBoxes;
    }

    /**
     * The post-layout inline text nodes.
     */
    public void setTextBoxes(TextBoxSnapshot textBoxes) {
        this.textBoxes = textBoxes;
    }

    /**
     * Horizontal scroll offset.
     */
    public Double getScrollOffsetX() {
        return scrollOffsetX;
    }

    /**
     * Horizontal scroll offset.
     */
    public void setScrollOffsetX(Double scrollOffsetX) {
        this.scrollOffsetX = scrollOffsetX;
    }

    /**
     * Vertical scroll offset.
     */
    public Double getScrollOffsetY() {
        return scrollOffsetY;
    }

    /**
     * Vertical scroll offset.
     */
    public void setScrollOffsetY(Double scrollOffsetY) {
        this.scrollOffsetY = scrollOffsetY;
    }

    /**
     * Document content width.
     */
    public Double getContentWidth() {
        return contentWidth;
    }

    /**
     * Document content width.
     */
    public void setContentWidth(Double contentWidth) {
        this.contentWidth = contentWidth;
    }

    /**
     * Document content height.
     */
    public Double getContentHeight() {
        return contentHeight;
    }

    /**
     * Document content height.
     */
    public void setContentHeight(Double contentHeight) {
        this.contentHeight = contentHeight;
    }
}
