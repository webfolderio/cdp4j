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
import io.webfolder.cdp.type.dom.Rect;
import io.webfolder.cdp.type.layertree.PictureTile;
import java.util.List;

@Experimental
@Domain("LayerTree")
public interface LayerTree {
    /**
     * Provides the reasons why the given layer was composited.
     * 
     * @param layerId The id of the layer for which we want to get the reasons it was composited.
     * 
     * @return A list of strings specifying reasons for the given layer to become composited.
     */
    @Returns("compositingReasons")
    List<String> compositingReasons(String layerId);

    /**
     * Disables compositing tree inspection.
     */
    void disable();

    /**
     * Enables compositing tree inspection.
     */
    void enable();

    /**
     * Returns the snapshot identifier.
     * 
     * @param tiles An array of tiles composing the snapshot.
     * 
     * @return The id of the snapshot.
     */
    @Returns("snapshotId")
    String loadSnapshot(List<PictureTile> tiles);

    /**
     * Returns the layer snapshot identifier.
     * 
     * @param layerId The id of the layer.
     * 
     * @return The id of the layer snapshot.
     */
    @Returns("snapshotId")
    String makeSnapshot(String layerId);

    @Returns("timings")
    List<Double> profileSnapshot(String snapshotId, @Optional Integer minRepeatCount,
            @Optional Double minDuration, @Optional Rect clipRect);

    /**
     * Releases layer snapshot captured by the back-end.
     * 
     * @param snapshotId The id of the layer snapshot.
     */
    void releaseSnapshot(String snapshotId);

    /**
     * Replays the layer snapshot and returns the resulting bitmap.
     * 
     * @param snapshotId The id of the layer snapshot.
     * @param fromStep The first step to replay from (replay from the very start if not specified).
     * @param toStep The last step to replay to (replay till the end if not specified).
     * @param scale The scale to apply while replaying (defaults to 1).
     * 
     * @return A data: URL for resulting image.
     */
    @Returns("dataURL")
    String replaySnapshot(String snapshotId, @Optional Integer fromStep, @Optional Integer toStep,
            @Optional Double scale);

    @Returns("timings")
    List<Double> profileSnapshot(String snapshotId);

    /**
     * Replays the layer snapshot and returns the resulting bitmap.
     * 
     * @param snapshotId The id of the layer snapshot.
     * 
     * @return A data: URL for resulting image.
     */
    @Returns("dataURL")
    String replaySnapshot(String snapshotId);
}
