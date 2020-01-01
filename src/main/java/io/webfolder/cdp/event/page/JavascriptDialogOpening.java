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
package io.webfolder.cdp.event.page;

import com.vimeo.stag.UseStag;

import io.webfolder.cdp.annotation.Domain;
import io.webfolder.cdp.annotation.EventName;
import io.webfolder.cdp.type.page.DialogType;

/**
 * Fired when a JavaScript initiated dialog (alert, confirm, prompt, or onbeforeunload) is about to
 * open
 */
@Domain("Page")
@EventName("javascriptDialogOpening")
@UseStag
public class JavascriptDialogOpening {
    private String url;

    private String message;

    private DialogType type;

    private Boolean hasBrowserHandler;

    private String defaultPrompt;

    /**
     * Frame url.
     */
    public String getUrl() {
        return url;
    }

    /**
     * Frame url.
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Message that will be displayed by the dialog.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Message that will be displayed by the dialog.
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Dialog type.
     */
    public DialogType getType() {
        return type;
    }

    /**
     * Dialog type.
     */
    public void setType(DialogType type) {
        this.type = type;
    }

    /**
     * True iff browser is capable showing or acting on the given dialog. When browser has no
     * dialog handler for given target, calling alert while Page domain is engaged will stall
     * the page execution. Execution can be resumed via calling Page.handleJavaScriptDialog.
     */
    public Boolean isHasBrowserHandler() {
        return hasBrowserHandler;
    }

    /**
     * True iff browser is capable showing or acting on the given dialog. When browser has no
     * dialog handler for given target, calling alert while Page domain is engaged will stall
     * the page execution. Execution can be resumed via calling Page.handleJavaScriptDialog.
     */
    public void setHasBrowserHandler(Boolean hasBrowserHandler) {
        this.hasBrowserHandler = hasBrowserHandler;
    }

    /**
     * Default dialog prompt.
     */
    public String getDefaultPrompt() {
        return defaultPrompt;
    }

    /**
     * Default dialog prompt.
     */
    public void setDefaultPrompt(String defaultPrompt) {
        this.defaultPrompt = defaultPrompt;
    }
}
