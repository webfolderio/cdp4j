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

/**
 * Profile
 */
public class Profile {
    private List<ProfileNode> nodes;

    private Double startTime;

    private Double endTime;

    private List<Integer> samples;

    private List<Integer> timeDeltas;

    /**
     * The list of profile nodes. First item is the root node.
     */
    public List<ProfileNode> getNodes() {
        return nodes;
    }

    /**
     * The list of profile nodes. First item is the root node.
     */
    public void setNodes(List<ProfileNode> nodes) {
        this.nodes = nodes;
    }

    /**
     * Profiling start timestamp in microseconds.
     */
    public Double getStartTime() {
        return startTime;
    }

    /**
     * Profiling start timestamp in microseconds.
     */
    public void setStartTime(Double startTime) {
        this.startTime = startTime;
    }

    /**
     * Profiling end timestamp in microseconds.
     */
    public Double getEndTime() {
        return endTime;
    }

    /**
     * Profiling end timestamp in microseconds.
     */
    public void setEndTime(Double endTime) {
        this.endTime = endTime;
    }

    /**
     * Ids of samples top nodes.
     */
    public List<Integer> getSamples() {
        return samples;
    }

    /**
     * Ids of samples top nodes.
     */
    public void setSamples(List<Integer> samples) {
        this.samples = samples;
    }

    /**
     * Time intervals between adjacent samples in microseconds. The first delta is relative to the
     * profile startTime.
     */
    public List<Integer> getTimeDeltas() {
        return timeDeltas;
    }

    /**
     * Time intervals between adjacent samples in microseconds. The first delta is relative to the
     * profile startTime.
     */
    public void setTimeDeltas(List<Integer> timeDeltas) {
        this.timeDeltas = timeDeltas;
    }
}
