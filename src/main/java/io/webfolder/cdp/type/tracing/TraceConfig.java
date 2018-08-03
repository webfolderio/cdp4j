/**
 * cdp4j - Chrome DevTools Protocol for Java
 * Copyright © 2017, 2018 WebFolder OÜ (support@webfolder.io)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package io.webfolder.cdp.type.tracing;

import io.webfolder.cdp.type.constant.TraceRecordMode;

import java.util.ArrayList;
import java.util.List;

public class TraceConfig {
    private TraceRecordMode recordMode;

    private Boolean enableSampling;

    private Boolean enableSystrace;

    private Boolean enableArgumentFilter;

    private List<String> includedCategories = new ArrayList<>();

    private List<String> excludedCategories = new ArrayList<>();

    private List<String> syntheticDelays = new ArrayList<>();

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
