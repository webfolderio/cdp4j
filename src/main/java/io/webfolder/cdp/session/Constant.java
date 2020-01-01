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
package io.webfolder.cdp.session;

import static java.util.Arrays.asList;

import java.util.List;

public interface Constant {

    List<String> DOM_PROPERTIES = asList("checked", "disabled", "selectedIndex");

    int TAB         =  9;
    int ENTER       = 13;
    int ESC         = 27;
    int BACKSPACE   = 46;
    int LEFT_ARROW  = 37;
    int UP_ARROW    = 38;
    int RIGHT_ARROW = 39;
    int DOWN_ARROW  = 40;

    List<Integer> SPECIAL_KEYS = asList(
                                    TAB, ENTER,
                                    BACKSPACE, LEFT_ARROW,
                                    UP_ARROW, RIGHT_ARROW,
                                    DOWN_ARROW, ESC
                                );

    Integer EMPTY_NODE_ID = 0;

    Object[] EMPTY_ARGS = new Object[] { };

    int WAIT_TIMEOUT = 10 * 1000; // 10 seconds

    int WAIT_PERIOD = 100; // 0.1 seconds
}
