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
package io.webfolder.cdp;

public enum SelectorEngine {
    /**
     * Use w3c's native selector engine.
     */
    Native,
    /**
     * Use Playwright's selector engine.
     * 
     * Playwright supports multiple selector engines used to query elements in the
     * web page. Selector is a string that consists of one or more clauses separated
     * by >> token, e.g. clause1 >> clause2 >> clause3. When multiple clauses are
     * present, next one is queried relative to the previous one's result.
     * 
     * @see <a href=
     *      "https://playwright.dev/#version=v1.2.1&path=docs%2Fselectors.md">https://playwright.dev/#version=v1.2.1&path=docs%2Fselectors.md</a>
     */
    Playwright
}
