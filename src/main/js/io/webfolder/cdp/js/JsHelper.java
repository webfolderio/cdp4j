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
package io.webfolder.cdp.js;

import static java.util.Collections.emptyList;

import java.util.ArrayList;
import java.util.List;

import com.koushikdutta.quack.JavaScriptObject;

public class JsHelper {

    public JavaScriptObject getObject(JavaScriptObject object, String name) {
        if (object == null) {
            return null;
        }
        if (name == null) {
            return null;
        }
        return object.has(name) ? (JavaScriptObject) object.get(name) : null;
    }

    public Integer getInteger(JavaScriptObject object, String name) {
        Object value = object.get(name);
        if (value == null) {
            return null;
        }
        if (value instanceof Integer) {
            return (Integer) value;
        }
        try {
            return Integer.valueOf(Integer.parseInt(value.toString()));
        } catch(NumberFormatException e) {
            return null;
        }
    }

    public List<Integer> getIntegerList(JavaScriptObject object, String name) {
        JavaScriptObject list = getObject(object, name);
        if (list == null) {
            return emptyList();
        }
        return toIntegerList(list);
    }

    public List<Integer> toIntegerList(JavaScriptObject object) {
        Integer length = getInteger(object, "length");
        if (length == null) {
            return emptyList();
        }
        if (length < 0) {
            return emptyList();
        }
        List<Integer> list = new ArrayList<>(length);
        for (int i = 0; i < length.intValue(); i++) {
            Object value = object.get(i);
            if (value instanceof Integer) {
                list.add((Integer) value);
            }
        }
        return list;
    }

    public List<String> getStringList(JavaScriptObject object, String name) {
        JavaScriptObject list = getObject(object, name);
        if (list == null) {
            return emptyList();
        }
        return toStringList(list);
    }

    public List<String> toStringList(JavaScriptObject object) {
        Integer length = getInteger(object, "length");
        if (length == null) {
            return emptyList();
        }
        if (length < 0) {
            return emptyList();
        }
        List<String> list = new ArrayList<>(length);
        for (int i = 0; i < length.intValue(); i++) {
            Object value = object.get(i);
            list.add(value.toString());
        }
        return list;
    }
}
