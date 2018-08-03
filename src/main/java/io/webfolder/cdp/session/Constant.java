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
