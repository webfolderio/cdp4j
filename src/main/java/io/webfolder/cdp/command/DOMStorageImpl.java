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
import io.webfolder.cdp.command.DOMStorage;
import io.webfolder.cdp.type.domstorage.StorageId;
import java.util.List;
import com.google.gson.reflect.TypeToken;

public class DOMStorageImpl implements DOMStorage {

    private static final Object[] EMPTY_VALUES = new Object[]{};
    private static final String[] EMPTY_ARGS = new String[]{};
    private final SessionInvocationHandler handler;
    private static final TypeToken<List<String>> TT_LIST_STRING = new TypeToken<List<String>>() { };

    public DOMStorageImpl(SessionInvocationHandler handler) {
        this.handler = handler;
    }

    @Override
    public void clear(StorageId storageId) {
        handler.invoke("DOMStorage", "clear", "DOMStorage.clear", null, void.class, null, true, false, false,
                new String[]{"storageId"}, new Object[]{storageId});
    }

    @Override
    public void disable() {
        handler.invoke("DOMStorage", "disable", "DOMStorage.disable", null, void.class, null, true, false, true,
                EMPTY_ARGS, EMPTY_VALUES);
    }

    @Override
    public void enable() {
        handler.invoke("DOMStorage", "enable", "DOMStorage.enable", null, void.class, null, true, true, false,
                EMPTY_ARGS, EMPTY_VALUES);
    }

    @Override
    @java.lang.SuppressWarnings("unchecked")
    public List<String> getDOMStorageItems(StorageId storageId) {
        return (List<String>) handler.invoke("DOMStorage", "getDOMStorageItems", "DOMStorage.getDOMStorageItems",
                "entries", List.class, TT_LIST_STRING.getType(), false, false, false, new String[]{"storageId"},
                new Object[]{storageId});
    }

    @Override
    public void removeDOMStorageItem(StorageId storageId, String key) {
        handler.invoke("DOMStorage", "removeDOMStorageItem", "DOMStorage.removeDOMStorageItem", null, void.class, null,
                true, false, false, new String[]{"storageId", "key"}, new Object[]{storageId, key});
    }

    @Override
    public void setDOMStorageItem(StorageId storageId, String key, String value) {
        handler.invoke("DOMStorage", "setDOMStorageItem", "DOMStorage.setDOMStorageItem", null, void.class, null, true,
                false, false, new String[]{"storageId", "key", "value"}, new Object[]{storageId, key, value});
    }
}
