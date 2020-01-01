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
package io.webfolder.cdp.type.input;

import com.vimeo.stag.UseStag;

@UseStag
public class TouchPoint {
    private Double x;

    private Double y;

    private Double radiusX;

    private Double radiusY;

    private Double rotationAngle;

    private Double force;

    private Double id;

    /**
     * X coordinate of the event relative to the main frame's viewport in CSS pixels.
     */
    public Double getX() {
        return x;
    }

    /**
     * X coordinate of the event relative to the main frame's viewport in CSS pixels.
     */
    public void setX(Double x) {
        this.x = x;
    }

    /**
     * Y coordinate of the event relative to the main frame's viewport in CSS pixels. 0 refers to
     * the top of the viewport and Y increases as it proceeds towards the bottom of the viewport.
     */
    public Double getY() {
        return y;
    }

    /**
     * Y coordinate of the event relative to the main frame's viewport in CSS pixels. 0 refers to
     * the top of the viewport and Y increases as it proceeds towards the bottom of the viewport.
     */
    public void setY(Double y) {
        this.y = y;
    }

    /**
     * X radius of the touch area (default: 1.0).
     */
    public Double getRadiusX() {
        return radiusX;
    }

    /**
     * X radius of the touch area (default: 1.0).
     */
    public void setRadiusX(Double radiusX) {
        this.radiusX = radiusX;
    }

    /**
     * Y radius of the touch area (default: 1.0).
     */
    public Double getRadiusY() {
        return radiusY;
    }

    /**
     * Y radius of the touch area (default: 1.0).
     */
    public void setRadiusY(Double radiusY) {
        this.radiusY = radiusY;
    }

    /**
     * Rotation angle (default: 0.0).
     */
    public Double getRotationAngle() {
        return rotationAngle;
    }

    /**
     * Rotation angle (default: 0.0).
     */
    public void setRotationAngle(Double rotationAngle) {
        this.rotationAngle = rotationAngle;
    }

    /**
     * Force (default: 1.0).
     */
    public Double getForce() {
        return force;
    }

    /**
     * Force (default: 1.0).
     */
    public void setForce(Double force) {
        this.force = force;
    }

    /**
     * Identifier used to track touch sources between events, must be unique within an event.
     */
    public Double getId() {
        return id;
    }

    /**
     * Identifier used to track touch sources between events, must be unique within an event.
     */
    public void setId(Double id) {
        this.id = id;
    }
}
