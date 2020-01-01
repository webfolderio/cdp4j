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

import java.util.Map;

import com.vimeo.stag.UseStag;

/**
 * Description of an isolated world
 */
@UseStag
public class ExecutionContextDescription {
    private Integer id;

    private String origin;

    private String name;

    private Map<String, Object> auxData;

    /**
     * Unique id of the execution context. It can be used to specify in which execution context
     * script evaluation should be performed.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Unique id of the execution context. It can be used to specify in which execution context
     * script evaluation should be performed.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Execution context origin.
     */
    public String getOrigin() {
        return origin;
    }

    /**
     * Execution context origin.
     */
    public void setOrigin(String origin) {
        this.origin = origin;
    }

    /**
     * Human readable name describing given context.
     */
    public String getName() {
        return name;
    }

    /**
     * Human readable name describing given context.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Embedder-specific auxiliary data.
     */
    public Map<String, Object> getAuxData() {
        return auxData;
    }

    /**
     * Embedder-specific auxiliary data.
     */
    public void setAuxData(Map<String, Object> auxData) {
        this.auxData = auxData;
    }
}
