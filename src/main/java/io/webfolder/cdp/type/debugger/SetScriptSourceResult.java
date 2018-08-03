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
package io.webfolder.cdp.type.debugger;

import io.webfolder.cdp.type.runtime.ExceptionDetails;
import io.webfolder.cdp.type.runtime.StackTrace;
import io.webfolder.cdp.type.runtime.StackTraceId;

import java.util.List;

public class SetScriptSourceResult {
    private List<CallFrame> callFrames;

    private Boolean stackChanged;

    private StackTrace asyncStackTrace;

    private StackTraceId asyncStackTraceId;

    private ExceptionDetails exceptionDetails;

    public List<CallFrame> getCallFrames() {
        return callFrames;
    }

    public Boolean getStackChanged() {
        return stackChanged;
    }

    public StackTrace getAsyncStackTrace() {
        return asyncStackTrace;
    }

    public StackTraceId getAsyncStackTraceId() {
        return asyncStackTraceId;
    }

    public ExceptionDetails getExceptionDetails() {
        return exceptionDetails;
    }
}
