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
package io.webfolder.cdp.type.memory;

import com.vimeo.stag.UseStag;

/**
 * Executable module information
 */
@UseStag
public class Module {
    private String name;

    private String uuid;

    private String baseAddress;

    private Double size;

    /**
     * Name of the module.
     */
    public String getName() {
        return name;
    }

    /**
     * Name of the module.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * UUID of the module.
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * UUID of the module.
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    /**
     * Base address where the module is loaded into memory. Encoded as a decimal
     * or hexadecimal (0x prefixed) string.
     */
    public String getBaseAddress() {
        return baseAddress;
    }

    /**
     * Base address where the module is loaded into memory. Encoded as a decimal
     * or hexadecimal (0x prefixed) string.
     */
    public void setBaseAddress(String baseAddress) {
        this.baseAddress = baseAddress;
    }

    /**
     * Size of the module in bytes.
     */
    public Double getSize() {
        return size;
    }

    /**
     * Size of the module in bytes.
     */
    public void setSize(Double size) {
        this.size = size;
    }
}
