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
package io.webfolder.cdp.type.browser;

import java.util.List;

import com.vimeo.stag.UseStag;

import io.webfolder.cdp.annotation.Experimental;

/**
 * Chrome histogram
 */
@Experimental
@UseStag
public class Histogram {
    private String name;

    private Integer sum;

    private Integer count;

    private List<Bucket> buckets;

    /**
     * Name.
     */
    public String getName() {
        return name;
    }

    /**
     * Name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sum of sample values.
     */
    public Integer getSum() {
        return sum;
    }

    /**
     * Sum of sample values.
     */
    public void setSum(Integer sum) {
        this.sum = sum;
    }

    /**
     * Total number of samples.
     */
    public Integer getCount() {
        return count;
    }

    /**
     * Total number of samples.
     */
    public void setCount(Integer count) {
        this.count = count;
    }

    /**
     * Buckets.
     */
    public List<Bucket> getBuckets() {
        return buckets;
    }

    /**
     * Buckets.
     */
    public void setBuckets(List<Bucket> buckets) {
        this.buckets = buckets;
    }
}
