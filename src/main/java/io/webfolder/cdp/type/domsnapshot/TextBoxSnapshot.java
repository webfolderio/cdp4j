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
package io.webfolder.cdp.type.domsnapshot;

import java.util.List;

import com.vimeo.stag.UseStag;

/**
 * Table of details of the post layout rendered text positions
 * The exact layout should not be regarded as
 * stable and may change between versions
 */
@UseStag
public class TextBoxSnapshot {
    private List<Integer> layoutIndex;

    private List<List<Double>> bounds;

    private List<Integer> start;

    private List<Integer> length;

    /**
     * Index of the layout tree node that owns this box collection.
     */
    public List<Integer> getLayoutIndex() {
        return layoutIndex;
    }

    /**
     * Index of the layout tree node that owns this box collection.
     */
    public void setLayoutIndex(List<Integer> layoutIndex) {
        this.layoutIndex = layoutIndex;
    }

    /**
     * The absolute position bounding box.
     */
    public List<List<Double>> getBounds() {
        return bounds;
    }

    /**
     * The absolute position bounding box.
     */
    public void setBounds(List<List<Double>> bounds) {
        this.bounds = bounds;
    }

    /**
     * The starting index in characters, for this post layout textbox substring. Characters that
     * would be represented as a surrogate pair in UTF-16 have length 2.
     */
    public List<Integer> getStart() {
        return start;
    }

    /**
     * The starting index in characters, for this post layout textbox substring. Characters that
     * would be represented as a surrogate pair in UTF-16 have length 2.
     */
    public void setStart(List<Integer> start) {
        this.start = start;
    }

    /**
     * The number of characters in this post layout textbox substring. Characters that would be
     * represented as a surrogate pair in UTF-16 have length 2.
     */
    public List<Integer> getLength() {
        return length;
    }

    /**
     * The number of characters in this post layout textbox substring. Characters that would be
     * represented as a surrogate pair in UTF-16 have length 2.
     */
    public void setLength(List<Integer> length) {
        this.length = length;
    }
}
