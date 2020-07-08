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
package io.webfolder.cdp.type.dom;

import java.util.List;

/**
 * Box model
 */
public class BoxModel {
    private List<Double> content;

    private List<Double> padding;

    private List<Double> border;

    private List<Double> margin;

    private Integer width;

    private Integer height;

    private ShapeOutsideInfo shapeOutside;

    /**
     * Content box
     */
    public List<Double> getContent() {
        return content;
    }

    /**
     * Content box
     */
    public void setContent(List<Double> content) {
        this.content = content;
    }

    /**
     * Padding box
     */
    public List<Double> getPadding() {
        return padding;
    }

    /**
     * Padding box
     */
    public void setPadding(List<Double> padding) {
        this.padding = padding;
    }

    /**
     * Border box
     */
    public List<Double> getBorder() {
        return border;
    }

    /**
     * Border box
     */
    public void setBorder(List<Double> border) {
        this.border = border;
    }

    /**
     * Margin box
     */
    public List<Double> getMargin() {
        return margin;
    }

    /**
     * Margin box
     */
    public void setMargin(List<Double> margin) {
        this.margin = margin;
    }

    /**
     * Node width
     */
    public Integer getWidth() {
        return width;
    }

    /**
     * Node width
     */
    public void setWidth(Integer width) {
        this.width = width;
    }

    /**
     * Node height
     */
    public Integer getHeight() {
        return height;
    }

    /**
     * Node height
     */
    public void setHeight(Integer height) {
        this.height = height;
    }

    /**
     * Shape outside coordinates
     */
    public ShapeOutsideInfo getShapeOutside() {
        return shapeOutside;
    }

    /**
     * Shape outside coordinates
     */
    public void setShapeOutside(ShapeOutsideInfo shapeOutside) {
        this.shapeOutside = shapeOutside;
    }
}
