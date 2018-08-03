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
package io.webfolder.cdp.event.page;

import io.webfolder.cdp.annotation.Domain;
import io.webfolder.cdp.annotation.EventName;

import java.util.List;

/**
 * Fired when a new window is going to be opened, via window
 * open(), link click, form submission,
 * etc
 */
@Domain("Page")
@EventName("windowOpen")
public class WindowOpen {
    private String url;

    private String windowName;

    private List<String> windowFeatures;

    private Boolean userGesture;

    /**
     * The URL for the new window.
     */
    public String getUrl() {
        return url;
    }

    /**
     * The URL for the new window.
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Window name.
     */
    public String getWindowName() {
        return windowName;
    }

    /**
     * Window name.
     */
    public void setWindowName(String windowName) {
        this.windowName = windowName;
    }

    /**
     * An array of enabled window features.
     */
    public List<String> getWindowFeatures() {
        return windowFeatures;
    }

    /**
     * An array of enabled window features.
     */
    public void setWindowFeatures(List<String> windowFeatures) {
        this.windowFeatures = windowFeatures;
    }

    /**
     * Whether or not it was triggered by user gesture.
     */
    public Boolean isUserGesture() {
        return userGesture;
    }

    /**
     * Whether or not it was triggered by user gesture.
     */
    public void setUserGesture(Boolean userGesture) {
        this.userGesture = userGesture;
    }
}
