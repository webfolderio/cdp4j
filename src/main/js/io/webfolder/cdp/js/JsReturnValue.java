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
package io.webfolder.cdp.js;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

import com.koushikdutta.quack.QuackObject;

public class JsReturnValue implements QuackObject {

    private final Object value;

    private final String error;

    private static final JsReturnValue SUCCESS = new JsReturnValue(TRUE, null);

    private static final JsReturnValue RET_TRUE = new JsReturnValue(TRUE, null);

    private static final JsReturnValue RET_FAIL = new JsReturnValue(FALSE, null);

    private JsReturnValue(Object value, String error) {
        this.value = value;
        this.error = error;
    }

    @Override
    public Object get(Object key) {
        return "value".equals(key) ? value : "error".equals(key) ? error : null;
    }

    @Override
    public boolean has(Object key) {
        return "value".equals(key) || "error".equals(key);
    }

    public static JsReturnValue fail(String error) {
        return new JsReturnValue(FALSE, error);
    }

    public static JsReturnValue fail(Throwable t) {
        return fail(t != null ? t.getMessage() : null);
    }

    public static JsReturnValue success() {
        return SUCCESS;
    }

    public static JsReturnValue success(String val) {
        return new JsReturnValue(val, null);
    }

    public static JsReturnValue success(Boolean val) {
        return TRUE.equals(val) ? RET_TRUE : RET_FAIL;
    }

    @Override
    public String toString() {
        if (error == null) {
            return value == null ? null : value.toString();
        } else {
            return "value=" + value + ", error=" + error;
        }
    }
}
