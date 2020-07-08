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

import io.webfolder.cdp.session.SessionInvocationHandler;
import io.webfolder.cdp.type.io.ReadResult;

public class IOImpl implements IO {

    private final SessionInvocationHandler handler;

    public IOImpl(SessionInvocationHandler handler) {
        this.handler = handler;
    }

    @Override
    public void close(String handle) {
        handler.invoke("IO", "close", "IO.close", null, void.class, null, true, false, false, new String[]{"handle"},
                new Object[]{handle});
    }

    @Override
    public ReadResult read(String handle, Integer offset, Integer size) {
        return (ReadResult) handler.invoke("IO", "read", "IO.read", null, ReadResult.class, null, false, false, false,
                new String[]{"handle", "offset", "size"}, new Object[]{handle, offset, size});
    }

    @Override
    public String resolveBlob(String objectId) {
        return (String) handler.invoke("IO", "resolveBlob", "IO.resolveBlob", "uuid", String.class, null, false, false,
                false, new String[]{"objectId"}, new Object[]{objectId});
    }

    @Override
    public ReadResult read(String handle) {
        return (ReadResult) handler.invoke("IO", "read", "IO.read", null, ReadResult.class, null, false, false, false,
                new String[]{"handle"}, new Object[]{handle});
    }
}