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
package io.webfolder.cdp.type.page;

import io.webfolder.cdp.annotation.Experimental;

import java.util.ArrayList;
import java.util.List;

/**
 * Information about the Frame hierarchy along with their cached resources
 */
@Experimental
public class FrameResourceTree {
    private Frame frame;

    private List<FrameResourceTree> childFrames = new ArrayList<>();

    private List<FrameResource> resources = new ArrayList<>();

    /**
     * Frame information for this tree item.
     */
    public Frame getFrame() {
        return frame;
    }

    /**
     * Frame information for this tree item.
     */
    public void setFrame(Frame frame) {
        this.frame = frame;
    }

    /**
     * Child frames.
     */
    public List<FrameResourceTree> getChildFrames() {
        return childFrames;
    }

    /**
     * Child frames.
     */
    public void setChildFrames(List<FrameResourceTree> childFrames) {
        this.childFrames = childFrames;
    }

    /**
     * Information about frame resources.
     */
    public List<FrameResource> getResources() {
        return resources;
    }

    /**
     * Information about frame resources.
     */
    public void setResources(List<FrameResource> resources) {
        this.resources = resources;
    }
}
