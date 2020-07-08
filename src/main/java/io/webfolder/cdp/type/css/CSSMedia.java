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

import java.util.List;

import io.webfolder.cdp.type.constant.CSSMediaSource;

/**
 * CSS media rule descriptor
 */
public class CSSMedia {
    private String text;

    private CSSMediaSource source;

    private String sourceURL;

    private SourceRange range;

    private String styleSheetId;

    private List<MediaQuery> mediaList;

    /**
     * Media query text.
     */
    public String getText() {
        return text;
    }

    /**
     * Media query text.
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Source of the media query: "mediaRule" if specified by a @media rule, "importRule" if
     * specified by an @import rule, "linkedSheet" if specified by a "media" attribute in a linked
     * stylesheet's LINK tag, "inlineSheet" if specified by a "media" attribute in an inline
     * stylesheet's STYLE tag.
     */
    public CSSMediaSource getSource() {
        return source;
    }

    /**
     * Source of the media query: "mediaRule" if specified by a @media rule, "importRule" if
     * specified by an @import rule, "linkedSheet" if specified by a "media" attribute in a linked
     * stylesheet's LINK tag, "inlineSheet" if specified by a "media" attribute in an inline
     * stylesheet's STYLE tag.
     */
    public void setSource(CSSMediaSource source) {
        this.source = source;
    }

    /**
     * URL of the document containing the media query description.
     */
    public String getSourceURL() {
        return sourceURL;
    }

    /**
     * URL of the document containing the media query description.
     */
    public void setSourceURL(String sourceURL) {
        this.sourceURL = sourceURL;
    }

    /**
     * The associated rule (@media or @import) header range in the enclosing stylesheet (if
     * available).
     */
    public SourceRange getRange() {
        return range;
    }

    /**
     * The associated rule (@media or @import) header range in the enclosing stylesheet (if
     * available).
     */
    public void setRange(SourceRange range) {
        this.range = range;
    }

    /**
     * Identifier of the stylesheet containing this object (if exists).
     */
    public String getStyleSheetId() {
        return styleSheetId;
    }

    /**
     * Identifier of the stylesheet containing this object (if exists).
     */
    public void setStyleSheetId(String styleSheetId) {
        this.styleSheetId = styleSheetId;
    }

    /**
     * Array of media queries.
     */
    public List<MediaQuery> getMediaList() {
        return mediaList;
    }

    /**
     * Array of media queries.
     */
    public void setMediaList(List<MediaQuery> mediaList) {
        this.mediaList = mediaList;
    }
}
