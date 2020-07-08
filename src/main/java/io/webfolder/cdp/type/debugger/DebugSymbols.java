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
package io.webfolder.cdp.type.debugger;

import io.webfolder.cdp.type.constant.DebugSymbolType;

/**
 * Debug symbols available for a wasm script
 */
public class DebugSymbols {
    private DebugSymbolType type;

    private String externalURL;

    /**
     * Type of the debug symbols.
     */
    public DebugSymbolType getType() {
        return type;
    }

    /**
     * Type of the debug symbols.
     */
    public void setType(DebugSymbolType type) {
        this.type = type;
    }

    /**
     * URL of the external symbol source.
     */
    public String getExternalURL() {
        return externalURL;
    }

    /**
     * URL of the external symbol source.
     */
    public void setExternalURL(String externalURL) {
        this.externalURL = externalURL;
    }
}
