/**
 * cdp4j - Chrome DevTools Protocol for Java
 * Copyright © 2017, 2018 WebFolder OÜ (support@webfolder.io)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package io.webfolder.cdp.type.domsnapshot;

import io.webfolder.cdp.type.dom.PseudoType;
import io.webfolder.cdp.type.dom.ShadowRootType;
import io.webfolder.cdp.type.domdebugger.EventListener;
import java.util.ArrayList;
import java.util.List;

/**
 * A Node in the DOM tree
 */
public class DOMNode {
    private Integer nodeType;

    private String nodeName;

    private String nodeValue;

    private String textValue;

    private String inputValue;

    private Boolean inputChecked;

    private Boolean optionSelected;

    private Integer backendNodeId;

    private List<Integer> childNodeIndexes = new ArrayList<>();

    private List<NameValue> attributes = new ArrayList<>();

    private List<Integer> pseudoElementIndexes = new ArrayList<>();

    private Integer layoutNodeIndex;

    private String documentURL;

    private String baseURL;

    private String contentLanguage;

    private String documentEncoding;

    private String publicId;

    private String systemId;

    private String frameId;

    private Integer contentDocumentIndex;

    private PseudoType pseudoType;

    private ShadowRootType shadowRootType;

    private Boolean isClickable;

    private List<EventListener> eventListeners = new ArrayList<>();

    private String currentSourceURL;

    private String originURL;

    /**
     * <code>Node</code>'s nodeType.
     */
    public Integer getNodeType() {
        return nodeType;
    }

    /**
     * <code>Node</code>'s nodeType.
     */
    public void setNodeType(Integer nodeType) {
        this.nodeType = nodeType;
    }

    /**
     * <code>Node</code>'s nodeName.
     */
    public String getNodeName() {
        return nodeName;
    }

    /**
     * <code>Node</code>'s nodeName.
     */
    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    /**
     * <code>Node</code>'s nodeValue.
     */
    public String getNodeValue() {
        return nodeValue;
    }

    /**
     * <code>Node</code>'s nodeValue.
     */
    public void setNodeValue(String nodeValue) {
        this.nodeValue = nodeValue;
    }

    /**
     * Only set for textarea elements, contains the text value.
     */
    public String getTextValue() {
        return textValue;
    }

    /**
     * Only set for textarea elements, contains the text value.
     */
    public void setTextValue(String textValue) {
        this.textValue = textValue;
    }

    /**
     * Only set for input elements, contains the input's associated text value.
     */
    public String getInputValue() {
        return inputValue;
    }

    /**
     * Only set for input elements, contains the input's associated text value.
     */
    public void setInputValue(String inputValue) {
        this.inputValue = inputValue;
    }

    /**
     * Only set for radio and checkbox input elements, indicates if the element has been checked
     */
    public Boolean isInputChecked() {
        return inputChecked;
    }

    /**
     * Only set for radio and checkbox input elements, indicates if the element has been checked
     */
    public void setInputChecked(Boolean inputChecked) {
        this.inputChecked = inputChecked;
    }

    /**
     * Only set for option elements, indicates if the element has been selected
     */
    public Boolean isOptionSelected() {
        return optionSelected;
    }

    /**
     * Only set for option elements, indicates if the element has been selected
     */
    public void setOptionSelected(Boolean optionSelected) {
        this.optionSelected = optionSelected;
    }

    /**
     * <code>Node</code>'s id, corresponds to DOM.Node.backendNodeId.
     */
    public Integer getBackendNodeId() {
        return backendNodeId;
    }

    /**
     * <code>Node</code>'s id, corresponds to DOM.Node.backendNodeId.
     */
    public void setBackendNodeId(Integer backendNodeId) {
        this.backendNodeId = backendNodeId;
    }

    /**
     * The indexes of the node's child nodes in the <code>domNodes</code>array returned by<code>getSnapshot</code>, if
     * any.
     */
    public List<Integer> getChildNodeIndexes() {
        return childNodeIndexes;
    }

    /**
     * The indexes of the node's child nodes in the <code>domNodes</code>array returned by<code>getSnapshot</code>, if
     * any.
     */
    public void setChildNodeIndexes(List<Integer> childNodeIndexes) {
        this.childNodeIndexes = childNodeIndexes;
    }

    /**
     * Attributes of an <code>Element</code> node.
     */
    public List<NameValue> getAttributes() {
        return attributes;
    }

    /**
     * Attributes of an <code>Element</code> node.
     */
    public void setAttributes(List<NameValue> attributes) {
        this.attributes = attributes;
    }

    /**
     * Indexes of pseudo elements associated with this node in the <code>domNodes</code> array returned by
     * <code>getSnapshot</code>, if any.
     */
    public List<Integer> getPseudoElementIndexes() {
        return pseudoElementIndexes;
    }

    /**
     * Indexes of pseudo elements associated with this node in the <code>domNodes</code> array returned by
     * <code>getSnapshot</code>, if any.
     */
    public void setPseudoElementIndexes(List<Integer> pseudoElementIndexes) {
        this.pseudoElementIndexes = pseudoElementIndexes;
    }

    /**
     * The index of the node's related layout tree node in the <code>layoutTreeNodes</code> array returned by
     * <code>getSnapshot</code>, if any.
     */
    public Integer getLayoutNodeIndex() {
        return layoutNodeIndex;
    }

    /**
     * The index of the node's related layout tree node in the <code>layoutTreeNodes</code> array returned by
     * <code>getSnapshot</code>, if any.
     */
    public void setLayoutNodeIndex(Integer layoutNodeIndex) {
        this.layoutNodeIndex = layoutNodeIndex;
    }

    /**
     * Document URL that <code>Document</code>or<code>FrameOwner</code> node points to.
     */
    public String getDocumentURL() {
        return documentURL;
    }

    /**
     * Document URL that <code>Document</code>or<code>FrameOwner</code> node points to.
     */
    public void setDocumentURL(String documentURL) {
        this.documentURL = documentURL;
    }

    /**
     * Base URL that <code>Document</code>or<code>FrameOwner</code> node uses for URL completion.
     */
    public String getBaseURL() {
        return baseURL;
    }

    /**
     * Base URL that <code>Document</code>or<code>FrameOwner</code> node uses for URL completion.
     */
    public void setBaseURL(String baseURL) {
        this.baseURL = baseURL;
    }

    /**
     * Only set for documents, contains the document's content language.
     */
    public String getContentLanguage() {
        return contentLanguage;
    }

    /**
     * Only set for documents, contains the document's content language.
     */
    public void setContentLanguage(String contentLanguage) {
        this.contentLanguage = contentLanguage;
    }

    /**
     * Only set for documents, contains the document's character set encoding.
     */
    public String getDocumentEncoding() {
        return documentEncoding;
    }

    /**
     * Only set for documents, contains the document's character set encoding.
     */
    public void setDocumentEncoding(String documentEncoding) {
        this.documentEncoding = documentEncoding;
    }

    /**
     * <code>DocumentType</code> node's publicId.
     */
    public String getPublicId() {
        return publicId;
    }

    /**
     * <code>DocumentType</code> node's publicId.
     */
    public void setPublicId(String publicId) {
        this.publicId = publicId;
    }

    /**
     * <code>DocumentType</code> node's systemId.
     */
    public String getSystemId() {
        return systemId;
    }

    /**
     * <code>DocumentType</code> node's systemId.
     */
    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }

    /**
     * Frame ID for frame owner elements and also for the document node.
     */
    public String getFrameId() {
        return frameId;
    }

    /**
     * Frame ID for frame owner elements and also for the document node.
     */
    public void setFrameId(String frameId) {
        this.frameId = frameId;
    }

    /**
     * The index of a frame owner element's content document in the <code>domNodes</code> array returned by
     * <code>getSnapshot</code>, if any.
     */
    public Integer getContentDocumentIndex() {
        return contentDocumentIndex;
    }

    /**
     * The index of a frame owner element's content document in the <code>domNodes</code> array returned by
     * <code>getSnapshot</code>, if any.
     */
    public void setContentDocumentIndex(Integer contentDocumentIndex) {
        this.contentDocumentIndex = contentDocumentIndex;
    }

    /**
     * Type of a pseudo element node.
     */
    public PseudoType getPseudoType() {
        return pseudoType;
    }

    /**
     * Type of a pseudo element node.
     */
    public void setPseudoType(PseudoType pseudoType) {
        this.pseudoType = pseudoType;
    }

    /**
     * Shadow root type.
     */
    public ShadowRootType getShadowRootType() {
        return shadowRootType;
    }

    /**
     * Shadow root type.
     */
    public void setShadowRootType(ShadowRootType shadowRootType) {
        this.shadowRootType = shadowRootType;
    }

    /**
     * Whether this DOM node responds to mouse clicks. This includes nodes that have had click
     * event listeners attached via JavaScript as well as anchor tags that naturally navigate when
     * clicked.
     */
    public Boolean isIsClickable() {
        return isClickable;
    }

    /**
     * Whether this DOM node responds to mouse clicks. This includes nodes that have had click
     * event listeners attached via JavaScript as well as anchor tags that naturally navigate when
     * clicked.
     */
    public void setIsClickable(Boolean isClickable) {
        this.isClickable = isClickable;
    }

    /**
     * Details of the node's event listeners, if any.
     */
    public List<EventListener> getEventListeners() {
        return eventListeners;
    }

    /**
     * Details of the node's event listeners, if any.
     */
    public void setEventListeners(List<EventListener> eventListeners) {
        this.eventListeners = eventListeners;
    }

    /**
     * The selected url for nodes with a srcset attribute.
     */
    public String getCurrentSourceURL() {
        return currentSourceURL;
    }

    /**
     * The selected url for nodes with a srcset attribute.
     */
    public void setCurrentSourceURL(String currentSourceURL) {
        this.currentSourceURL = currentSourceURL;
    }

    /**
     * The url of the script (if any) that generates this node.
     */
    public String getOriginURL() {
        return originURL;
    }

    /**
     * The url of the script (if any) that generates this node.
     */
    public void setOriginURL(String originURL) {
        this.originURL = originURL;
    }
}
