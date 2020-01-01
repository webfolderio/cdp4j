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
package io.webfolder.cdp.type.tracing;

import java.util.List;

import com.vimeo.stag.UseStag;

import io.webfolder.cdp.type.constant.TraceRecordMode;

@UseStag
public class TraceConfig {
    private TraceRecordMode recordMode;

    private Boolean enableSampling;

    private Boolean enableSystrace;

    private Boolean enableArgumentFilter;

    private List<String> includedCategories;

    private List<String> excludedCategories;

    private List<String> syntheticDelays;

    private MemoryDumpConfig memoryDumpConfig;

    /**
     * Controls how the trace buffer stores data.
     */
    public TraceRecordMode getRecordMode() {
        return recordMode;
    }

    /**
     * Controls how the trace buffer stores data.
     */
    public void setRecordMode(TraceRecordMode recordMode) {
        this.recordMode = recordMode;
    }

    /**
     * Turns on JavaScript stack sampling.
     */
    public Boolean isEnableSampling() {
        return enableSampling;
    }

    /**
     * Turns on JavaScript stack sampling.
     */
    public void setEnableSampling(Boolean enableSampling) {
        this.enableSampling = enableSampling;
    }

    /**
     * Turns on system tracing.
     */
    public Boolean isEnableSystrace() {
        return enableSystrace;
    }

    /**
     * Turns on system tracing.
     */
    public void setEnableSystrace(Boolean enableSystrace) {
        this.enableSystrace = enableSystrace;
    }

    /**
     * Turns on argument filter.
     */
    public Boolean isEnableArgumentFilter() {
        return enableArgumentFilter;
    }

    /**
     * Turns on argument filter.
     */
    public void setEnableArgumentFilter(Boolean enableArgumentFilter) {
        this.enableArgumentFilter = enableArgumentFilter;
    }

    /**
     * Included category filters.
     */
    public List<String> getIncludedCategories() {
        return includedCategories;
    }

    /**
     * Included category filters.
     */
    public void setIncludedCategories(List<String> includedCategories) {
        this.includedCategories = includedCategories;
    }

    /**
     * Excluded category filters.
     */
    public List<String> getExcludedCategories() {
        return excludedCategories;
    }

    /**
     * Excluded category filters.
     */
    public void setExcludedCategories(List<String> excludedCategories) {
        this.excludedCategories = excludedCategories;
    }

    /**
     * Configuration to synthesize the delays in tracing.
     */
    public List<String> getSyntheticDelays() {
        return syntheticDelays;
    }

    /**
     * Configuration to synthesize the delays in tracing.
     */
    public void setSyntheticDelays(List<String> syntheticDelays) {
        this.syntheticDelays = syntheticDelays;
    }

    /**
     * Configuration for memory dump triggers. Used only when "memory-infra" category is enabled.
     */
    public MemoryDumpConfig getMemoryDumpConfig() {
        return memoryDumpConfig;
    }

    /**
     * Configuration for memory dump triggers. Used only when "memory-infra" category is enabled.
     */
    public void setMemoryDumpConfig(MemoryDumpConfig memoryDumpConfig) {
        this.memoryDumpConfig = memoryDumpConfig;
    }
}
