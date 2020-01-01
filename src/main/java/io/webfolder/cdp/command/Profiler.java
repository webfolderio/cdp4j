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
package io.webfolder.cdp.command;

import io.webfolder.cdp.annotation.Domain;
import io.webfolder.cdp.annotation.Experimental;
import io.webfolder.cdp.annotation.Optional;
import io.webfolder.cdp.annotation.Returns;
import io.webfolder.cdp.type.profiler.Profile;
import io.webfolder.cdp.type.profiler.ScriptCoverage;
import io.webfolder.cdp.type.profiler.ScriptTypeProfile;
import java.util.List;

@Domain("Profiler")
public interface Profiler {
    void disable();

    void enable();

    /**
     * Collect coverage data for the current isolate. The coverage data may be incomplete due to
     * garbage collection.
     * 
     * @return Coverage data for the current isolate.
     */
    @Returns("result")
    List<ScriptCoverage> getBestEffortCoverage();

    /**
     * Changes CPU profiler sampling interval. Must be called before CPU profiles recording started.
     * 
     * @param interval New sampling interval in microseconds.
     */
    void setSamplingInterval(Integer interval);

    void start();

    /**
     * Enable precise code coverage. Coverage data for JavaScript executed before enabling precise code
     * coverage may be incomplete. Enabling prevents running optimized code and resets execution
     * counters.
     * 
     * @param callCount Collect accurate call counts beyond simple 'covered' or 'not covered'.
     * @param detailed Collect block-based coverage.
     */
    void startPreciseCoverage(@Optional Boolean callCount, @Optional Boolean detailed);

    /**
     * Enable type profile.
     */
    @Experimental
    void startTypeProfile();

    @Returns("profile")
    Profile stop();

    /**
     * Disable precise code coverage. Disabling releases unnecessary execution count records and allows
     * executing optimized code.
     */
    void stopPreciseCoverage();

    /**
     * Disable type profile. Disabling releases type profile data collected so far.
     */
    @Experimental
    void stopTypeProfile();

    /**
     * Collect coverage data for the current isolate, and resets execution counters. Precise code
     * coverage needs to have started.
     * 
     * @return Coverage data for the current isolate.
     */
    @Returns("result")
    List<ScriptCoverage> takePreciseCoverage();

    /**
     * Collect type profile.
     * 
     * @return Type profile for all scripts since startTypeProfile() was turned on.
     */
    @Experimental
    @Returns("result")
    List<ScriptTypeProfile> takeTypeProfile();

    /**
     * Enable precise code coverage. Coverage data for JavaScript executed before enabling precise code
     * coverage may be incomplete. Enabling prevents running optimized code and resets execution
     * counters.
     */
    void startPreciseCoverage();
}
