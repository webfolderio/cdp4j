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
package io.webfolder.cdp.type.layertree;

import io.webfolder.cdp.type.constant.RepaintReason;
import io.webfolder.cdp.type.dom.Rect;

/**
 * Rectangle where scrolling happens on the main thread
 */
public class ScrollRect {
    private Rect rect;

    private RepaintReason type;

    /**
     * Rectangle itself.
     */
    public Rect getRect() {
        return rect;
    }

    /**
     * Rectangle itself.
     */
    public void setRect(Rect rect) {
        this.rect = rect;
    }

    /**
     * Reason for rectangle to force scrolling on the main thread
     */
    public RepaintReason getType() {
        return type;
    }

    /**
     * Reason for rectangle to force scrolling on the main thread
     */
    public void setType(RepaintReason type) {
        this.type = type;
    }
}
