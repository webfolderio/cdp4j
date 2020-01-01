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
package io.webfolder.cdp.type.console;

import com.vimeo.stag.UseStag;

import io.webfolder.cdp.type.constant.MessageSeverity;
import io.webfolder.cdp.type.constant.MessageSource;

/**
 * Console message
 */
@UseStag
public class ConsoleMessage {
    private MessageSource source;

    private MessageSeverity level;

    private String text;

    private String url;

    private Integer line;

    private Integer column;

    /**
     * Message source.
     */
    public MessageSource getSource() {
        return source;
    }

    /**
     * Message source.
     */
    public void setSource(MessageSource source) {
        this.source = source;
    }

    /**
     * Message severity.
     */
    public MessageSeverity getLevel() {
        return level;
    }

    /**
     * Message severity.
     */
    public void setLevel(MessageSeverity level) {
        this.level = level;
    }

    /**
     * Message text.
     */
    public String getText() {
        return text;
    }

    /**
     * Message text.
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * URL of the message origin.
     */
    public String getUrl() {
        return url;
    }

    /**
     * URL of the message origin.
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Line number in the resource that generated this message (1-based).
     */
    public Integer getLine() {
        return line;
    }

    /**
     * Line number in the resource that generated this message (1-based).
     */
    public void setLine(Integer line) {
        this.line = line;
    }

    /**
     * Column number in the resource that generated this message (1-based).
     */
    public Integer getColumn() {
        return column;
    }

    /**
     * Column number in the resource that generated this message (1-based).
     */
    public void setColumn(Integer column) {
        this.column = column;
    }
}
