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
 * CSS property declaration data
 */
@UseStag
public class CSSProperty {
    private String name;

    private String value;

    private Boolean important;

    private Boolean implicit;

    private String text;

    private Boolean parsedOk;

    private Boolean disabled;

    private SourceRange range;

    /**
     * The property name.
     */
    public String getName() {
        return name;
    }

    /**
     * The property name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * The property value.
     */
    public String getValue() {
        return value;
    }

    /**
     * The property value.
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Whether the property has "!important" annotation (implies <code>false</code> if absent).
     */
    public Boolean isImportant() {
        return important;
    }

    /**
     * Whether the property has "!important" annotation (implies <code>false</code> if absent).
     */
    public void setImportant(Boolean important) {
        this.important = important;
    }

    /**
     * Whether the property is implicit (implies <code>false</code> if absent).
     */
    public Boolean isImplicit() {
        return implicit;
    }

    /**
     * Whether the property is implicit (implies <code>false</code> if absent).
     */
    public void setImplicit(Boolean implicit) {
        this.implicit = implicit;
    }

    /**
     * The full property text as specified in the style.
     */
    public String getText() {
        return text;
    }

    /**
     * The full property text as specified in the style.
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Whether the property is understood by the browser (implies <code>true</code> if absent).
     */
    public Boolean isParsedOk() {
        return parsedOk;
    }

    /**
     * Whether the property is understood by the browser (implies <code>true</code> if absent).
     */
    public void setParsedOk(Boolean parsedOk) {
        this.parsedOk = parsedOk;
    }

    /**
     * Whether the property is disabled by the user (present for source-based properties only).
     */
    public Boolean isDisabled() {
        return disabled;
    }

    /**
     * Whether the property is disabled by the user (present for source-based properties only).
     */
    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }

    /**
     * The entire property range in the enclosing style declaration (if available).
     */
    public SourceRange getRange() {
        return range;
    }

    /**
     * The entire property range in the enclosing style declaration (if available).
     */
    public void setRange(SourceRange range) {
        this.range = range;
    }
}
