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
package io.webfolder.cdp.type.css;

import com.vimeo.stag.UseStag;

/**
 * CSS stylesheet metainformation
 */
@UseStag
public class CSSStyleSheetHeader {
    private String styleSheetId;

    private String frameId;

    private String sourceURL;

    private String sourceMapURL;

    private StyleSheetOrigin origin;

    private String title;

    private Integer ownerNode;

    private Boolean disabled;

    private Boolean hasSourceURL;

    private Boolean isInline;

    private Double startLine;

    private Double startColumn;

    private Double length;

    private Double endLine;

    private Double endColumn;

    /**
     * The stylesheet identifier.
     */
    public String getStyleSheetId() {
        return styleSheetId;
    }

    /**
     * The stylesheet identifier.
     */
    public void setStyleSheetId(String styleSheetId) {
        this.styleSheetId = styleSheetId;
    }

    /**
     * Owner frame identifier.
     */
    public String getFrameId() {
        return frameId;
    }

    /**
     * Owner frame identifier.
     */
    public void setFrameId(String frameId) {
        this.frameId = frameId;
    }

    /**
     * Stylesheet resource URL.
     */
    public String getSourceURL() {
        return sourceURL;
    }

    /**
     * Stylesheet resource URL.
     */
    public void setSourceURL(String sourceURL) {
        this.sourceURL = sourceURL;
    }

    /**
     * URL of source map associated with the stylesheet (if any).
     */
    public String getSourceMapURL() {
        return sourceMapURL;
    }

    /**
     * URL of source map associated with the stylesheet (if any).
     */
    public void setSourceMapURL(String sourceMapURL) {
        this.sourceMapURL = sourceMapURL;
    }

    /**
     * Stylesheet origin.
     */
    public StyleSheetOrigin getOrigin() {
        return origin;
    }

    /**
     * Stylesheet origin.
     */
    public void setOrigin(StyleSheetOrigin origin) {
        this.origin = origin;
    }

    /**
     * Stylesheet title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Stylesheet title.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * The backend id for the owner node of the stylesheet.
     */
    public Integer getOwnerNode() {
        return ownerNode;
    }

    /**
     * The backend id for the owner node of the stylesheet.
     */
    public void setOwnerNode(Integer ownerNode) {
        this.ownerNode = ownerNode;
    }

    /**
     * Denotes whether the stylesheet is disabled.
     */
    public Boolean isDisabled() {
        return disabled;
    }

    /**
     * Denotes whether the stylesheet is disabled.
     */
    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }

    /**
     * Whether the sourceURL field value comes from the sourceURL comment.
     */
    public Boolean isHasSourceURL() {
        return hasSourceURL;
    }

    /**
     * Whether the sourceURL field value comes from the sourceURL comment.
     */
    public void setHasSourceURL(Boolean hasSourceURL) {
        this.hasSourceURL = hasSourceURL;
    }

    /**
     * Whether this stylesheet is created for STYLE tag by parser. This flag is not set for
     * document.written STYLE tags.
     */
    public void setIsInline(Boolean isInline) {
        this.isInline = isInline;
    }

    /**
     * Line offset of the stylesheet within the resource (zero based).
     */
    public Double getStartLine() {
        return startLine;
    }

    /**
     * Line offset of the stylesheet within the resource (zero based).
     */
    public void setStartLine(Double startLine) {
        this.startLine = startLine;
    }

    /**
     * Column offset of the stylesheet within the resource (zero based).
     */
    public Double getStartColumn() {
        return startColumn;
    }

    /**
     * Column offset of the stylesheet within the resource (zero based).
     */
    public void setStartColumn(Double startColumn) {
        this.startColumn = startColumn;
    }

    /**
     * Size of the content (in characters).
     */
    public Double getLength() {
        return length;
    }

    /**
     * Size of the content (in characters).
     */
    public void setLength(Double length) {
        this.length = length;
    }

    /**
     * Line offset of the end of the stylesheet within the resource (zero based).
     */
    public Double getEndLine() {
        return endLine;
    }

    /**
     * Line offset of the end of the stylesheet within the resource (zero based).
     */
    public void setEndLine(Double endLine) {
        this.endLine = endLine;
    }

    /**
     * Column offset of the end of the stylesheet within the resource (zero based).
     */
    public Double getEndColumn() {
        return endColumn;
    }

    /**
     * Column offset of the end of the stylesheet within the resource (zero based).
     */
    public void setEndColumn(Double endColumn) {
        this.endColumn = endColumn;
    }

    public Boolean getDisabled() {
        return disabled;
    }

    public Boolean getHasSourceURL() {
        return hasSourceURL;
    }

    /**
     * Whether this stylesheet is created for STYLE tag by parser. This flag is not set for
     * document.written STYLE tags.
     */
    public Boolean getIsInline() {
        return isInline;
    }
}
