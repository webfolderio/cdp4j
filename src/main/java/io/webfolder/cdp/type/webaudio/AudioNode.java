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
package io.webfolder.cdp.type.webaudio;

/**
 * Protocol object for AudioNode
 */
public class AudioNode {
    private String nodeId;

    private String contextId;

    private String nodeType;

    private Double numberOfInputs;

    private Double numberOfOutputs;

    private Double channelCount;

    private ChannelCountMode channelCountMode;

    private ChannelInterpretation channelInterpretation;

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getContextId() {
        return contextId;
    }

    public void setContextId(String contextId) {
        this.contextId = contextId;
    }

    public String getNodeType() {
        return nodeType;
    }

    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }

    public Double getNumberOfInputs() {
        return numberOfInputs;
    }

    public void setNumberOfInputs(Double numberOfInputs) {
        this.numberOfInputs = numberOfInputs;
    }

    public Double getNumberOfOutputs() {
        return numberOfOutputs;
    }

    public void setNumberOfOutputs(Double numberOfOutputs) {
        this.numberOfOutputs = numberOfOutputs;
    }

    public Double getChannelCount() {
        return channelCount;
    }

    public void setChannelCount(Double channelCount) {
        this.channelCount = channelCount;
    }

    public ChannelCountMode getChannelCountMode() {
        return channelCountMode;
    }

    public void setChannelCountMode(ChannelCountMode channelCountMode) {
        this.channelCountMode = channelCountMode;
    }

    public ChannelInterpretation getChannelInterpretation() {
        return channelInterpretation;
    }

    public void setChannelInterpretation(ChannelInterpretation channelInterpretation) {
        this.channelInterpretation = channelInterpretation;
    }
}
