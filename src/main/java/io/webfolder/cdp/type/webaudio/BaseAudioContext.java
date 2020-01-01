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

import com.vimeo.stag.UseStag;

/**
 * Protocol object for BaseAudioContext
 */
@UseStag
public class BaseAudioContext {
    private String contextId;

    private ContextType contextType;

    private ContextState contextState;

    private ContextRealtimeData realtimeData;

    private Double callbackBufferSize;

    private Double maxOutputChannelCount;

    private Double sampleRate;

    public String getContextId() {
        return contextId;
    }

    public void setContextId(String contextId) {
        this.contextId = contextId;
    }

    public ContextType getContextType() {
        return contextType;
    }

    public void setContextType(ContextType contextType) {
        this.contextType = contextType;
    }

    public ContextState getContextState() {
        return contextState;
    }

    public void setContextState(ContextState contextState) {
        this.contextState = contextState;
    }

    public ContextRealtimeData getRealtimeData() {
        return realtimeData;
    }

    public void setRealtimeData(ContextRealtimeData realtimeData) {
        this.realtimeData = realtimeData;
    }

    /**
     * Platform-dependent callback buffer size.
     */
    public Double getCallbackBufferSize() {
        return callbackBufferSize;
    }

    /**
     * Platform-dependent callback buffer size.
     */
    public void setCallbackBufferSize(Double callbackBufferSize) {
        this.callbackBufferSize = callbackBufferSize;
    }

    /**
     * Number of output channels supported by audio hardware in use.
     */
    public Double getMaxOutputChannelCount() {
        return maxOutputChannelCount;
    }

    /**
     * Number of output channels supported by audio hardware in use.
     */
    public void setMaxOutputChannelCount(Double maxOutputChannelCount) {
        this.maxOutputChannelCount = maxOutputChannelCount;
    }

    /**
     * Context sample rate.
     */
    public Double getSampleRate() {
        return sampleRate;
    }

    /**
     * Context sample rate.
     */
    public void setSampleRate(Double sampleRate) {
        this.sampleRate = sampleRate;
    }
}
