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
package io.webfolder.cdp.command;

import java.util.List;

import com.google.gson.reflect.TypeToken;

import io.webfolder.cdp.session.SessionInvocationHandler;
import io.webfolder.cdp.type.systeminfo.GetInfoResult;
import io.webfolder.cdp.type.systeminfo.ProcessInfo;

public class SystemInfoImpl implements SystemInfo {

    private static final String[] EMPTY_ARGS = new String[] {};
    private static final Object[] EMPTY_VALUES = new Object[] {};
    private static final TypeToken<List<ProcessInfo>> GET_PROCESS_INFO = new TypeToken<List<ProcessInfo>>() {
    };
    private final SessionInvocationHandler handler;

    public SystemInfoImpl(SessionInvocationHandler handler) {
        this.handler = handler;
    }

    @Override
    public GetInfoResult getInfo() {
        return (GetInfoResult) handler.invoke("SystemInfo", "getInfo", "SystemInfo.getInfo", null, GetInfoResult.class,
                null, false, false, false, EMPTY_ARGS, EMPTY_VALUES);
    }

    @Override
    @java.lang.SuppressWarnings("unchecked")
    public List<ProcessInfo> getProcessInfo() {
        return (List<ProcessInfo>) handler.invoke("SystemInfo", "getProcessInfo", "SystemInfo.getProcessInfo",
                "processInfo", List.class, GET_PROCESS_INFO.getType(), false, false, false, EMPTY_ARGS, EMPTY_VALUES);
    }
}