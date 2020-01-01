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
package io.webfolder.cdp.type.systeminfo;

import com.vimeo.stag.UseStag;

/**
 * Represents process info
 */
@UseStag
public class ProcessInfo {
    private String type;

    private Integer id;

    private Double cpuTime;

    /**
     * Specifies process type.
     */
    public String getType() {
        return type;
    }

    /**
     * Specifies process type.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Specifies process id.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Specifies process id.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Specifies cumulative CPU usage in seconds across all threads of the
     * process since the process start.
     */
    public Double getCpuTime() {
        return cpuTime;
    }

    /**
     * Specifies cumulative CPU usage in seconds across all threads of the
     * process since the process start.
     */
    public void setCpuTime(Double cpuTime) {
        this.cpuTime = cpuTime;
    }
}
