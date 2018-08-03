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
package io.webfolder.cdp.event.runtime;

import java.util.ArrayList;
import java.util.List;

import io.webfolder.cdp.annotation.Domain;
import io.webfolder.cdp.annotation.EventName;
import io.webfolder.cdp.type.constant.ConsoleApiCallType;
import io.webfolder.cdp.type.runtime.RemoteObject;
import io.webfolder.cdp.type.runtime.StackTrace;

/**
 * Issued when console API was called
 */
@Domain("Runtime")
@EventName("consoleAPICalled")
public class ConsoleAPICalled {
    private ConsoleApiCallType type;

    private List<RemoteObject> args = new ArrayList<>();

    private Integer executionContextId;

    private Double timestamp;

    private StackTrace stackTrace;

    private String context;

    /**
     * Type of the call.
     */
    public ConsoleApiCallType getType() {
        return type;
    }

    /**
     * Type of the call.
     */
    public void setType(ConsoleApiCallType type) {
        this.type = type;
    }

    /**
     * Call arguments.
     */
    public List<RemoteObject> getArgs() {
        return args;
    }

    /**
     * Call arguments.
     */
    public void setArgs(List<RemoteObject> args) {
        this.args = args;
    }

    /**
     * Identifier of the context where the call was made.
     */
    public Integer getExecutionContextId() {
        return executionContextId;
    }

    /**
     * Identifier of the context where the call was made.
     */
    public void setExecutionContextId(Integer executionContextId) {
        this.executionContextId = executionContextId;
    }

    /**
     * Call timestamp.
     */
    public Double getTimestamp() {
        return timestamp;
    }

    /**
     * Call timestamp.
     */
    public void setTimestamp(Double timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Stack trace captured when the call was made.
     */
    public StackTrace getStackTrace() {
        return stackTrace;
    }

    /**
     * Stack trace captured when the call was made.
     */
    public void setStackTrace(StackTrace stackTrace) {
        this.stackTrace = stackTrace;
    }

    /**
     * Console context descriptor for calls on non-default console context (not console.*):
     * 'anonymous#unique-logger-id' for call on unnamed context, 'name#unique-logger-id' for call
     * on named context.
     */
    public String getContext() {
        return context;
    }

    /**
     * Console context descriptor for calls on non-default console context (not console.*):
     * 'anonymous#unique-logger-id' for call on unnamed context, 'name#unique-logger-id' for call
     * on named context.
     */
    public void setContext(String context) {
        this.context = context;
    }
}
