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
package io.webfolder.cdp.type.accessibility;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Values of AXProperty name: from 'busy' to 'roledescription' - states which apply to every AX
 * node, from 'live' to 'root' - attributes which apply to nodes in live regions, from
 * 'autocomplete' to 'valuetext' - attributes which apply to widgets, from 'checked' to 'selected'
 * - states which apply to widgets, from 'activedescendant' to 'owns' - relationships between
 * elements other than parent/child/sibling
 */
public enum AXPropertyName {
    Busy("busy"),
    Disabled("disabled"),
    Hidden("hidden"),
    HiddenRoot("hiddenRoot"),
    Invalid("invalid"),
    Keyshortcuts("keyshortcuts"),
    Roledescription("roledescription"),
    Live("live"),
    Atomic("atomic"),
    Relevant("relevant"),
    Root("root"),
    Autocomplete("autocomplete"),
    HasPopup("hasPopup"),
    Level("level"),
    Multiselectable("multiselectable"),
    Orientation("orientation"),
    Multiline("multiline"),
    Readonly("readonly"),
    Required("required"),
    Valuemin("valuemin"),
    Valuemax("valuemax"),
    Valuetext("valuetext"),
    Checked("checked"),
    Expanded("expanded"),
    Modal("modal"),
    Pressed("pressed"),
    Selected("selected"),
    Activedescendant("activedescendant"),
    Controls("controls"),
    Describedby("describedby"),
    Details("details"),
    Errormessage("errormessage"),
    Flowto("flowto"),
    Labelledby("labelledby"),
    Owns("owns");

    @JsonValue
    public final String value;

    AXPropertyName(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
