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
package io.webfolder.cdp.type.profiler;

import java.util.List;

import com.vimeo.stag.UseStag;

import io.webfolder.cdp.annotation.Experimental;

/**
 * Source offset and types for a parameter or return value
 */
@Experimental
@UseStag
public class TypeProfileEntry {
    private Integer offset;

    private List<TypeObject> types;

    /**
     * Source offset of the parameter or end of function for return values.
     */
    public Integer getOffset() {
        return offset;
    }

    /**
     * Source offset of the parameter or end of function for return values.
     */
    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    /**
     * The types for this parameter or return value.
     */
    public List<TypeObject> getTypes() {
        return types;
    }

    /**
     * The types for this parameter or return value.
     */
    public void setTypes(List<TypeObject> types) {
        this.types = types;
    }
}
