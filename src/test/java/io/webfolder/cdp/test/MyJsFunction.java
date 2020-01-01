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
package io.webfolder.cdp.test;

import java.util.List;

import io.webfolder.cdp.JsFunction;

public interface MyJsFunction {

    @JsFunction("let attributes = []; document.querySelectorAll(selector).forEach(e => { attributes.push(e.getAttribute(attributeName)); }); return attributes;")
    List<String> listAttributes(String selector, String attributeName);

    @JsFunction("")
    void dummy();

    @JsFunction("console.error(message);")
    void consoleError(String message);

    @JsFunction("return a + b")
    int sum(int a, int b);

    @JsFunction("return str1 + str2")
    String concat(String str1, String str2);

    @JsFunction("let list = []; numbers.forEach(i => list.push(i + inc)); return list;")
    List<Double> increment(List<Integer> numbers, int inc);
}
