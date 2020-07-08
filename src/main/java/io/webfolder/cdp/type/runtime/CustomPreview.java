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
package io.webfolder.cdp.type.runtime;

import io.webfolder.cdp.annotation.Experimental;

@Experimental
public class CustomPreview {
    private String header;

    private String bodyGetterId;

    /**
     * The JSON-stringified result of formatter.header(object, config) call.
     * It contains json ML array that represents RemoteObject.
     */
    public String getHeader() {
        return header;
    }

    /**
     * The JSON-stringified result of formatter.header(object, config) call.
     * It contains json ML array that represents RemoteObject.
     */
    public void setHeader(String header) {
        this.header = header;
    }

    /**
     * If formatter returns true as a result of formatter.hasBody call then bodyGetterId will
     * contain RemoteObjectId for the function that returns result of formatter.body(object, config) call.
     * The result value is json ML array.
     */
    public String getBodyGetterId() {
        return bodyGetterId;
    }

    /**
     * If formatter returns true as a result of formatter.hasBody call then bodyGetterId will
     * contain RemoteObjectId for the function that returns result of formatter.body(object, config) call.
     * The result value is json ML array.
     */
    public void setBodyGetterId(String bodyGetterId) {
        this.bodyGetterId = bodyGetterId;
    }
}
