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
package io.webfolder.cdp.type.overlay;

import io.webfolder.cdp.type.dom.RGBA;

/**
 * Configuration data for the highlighting of Grid elements
 */
public class GridHighlightConfig {
    private Boolean showGridExtensionLines;

    private Boolean showPositiveLineNumbers;

    private Boolean showNegativeLineNumbers;

    private RGBA gridBorderColor;

    private RGBA cellBorderColor;

    private Boolean gridBorderDash;

    private Boolean cellBorderDash;

    private RGBA rowGapColor;

    private RGBA rowHatchColor;

    private RGBA columnGapColor;

    private RGBA columnHatchColor;

    /**
     * Whether the extension lines from grid cells to the rulers should be shown (default: false).
     */
    public Boolean isShowGridExtensionLines() {
        return showGridExtensionLines;
    }

    /**
     * Whether the extension lines from grid cells to the rulers should be shown (default: false).
     */
    public void setShowGridExtensionLines(Boolean showGridExtensionLines) {
        this.showGridExtensionLines = showGridExtensionLines;
    }

    /**
     * Show Positive line number labels (default: false).
     */
    public Boolean isShowPositiveLineNumbers() {
        return showPositiveLineNumbers;
    }

    /**
     * Show Positive line number labels (default: false).
     */
    public void setShowPositiveLineNumbers(Boolean showPositiveLineNumbers) {
        this.showPositiveLineNumbers = showPositiveLineNumbers;
    }

    /**
     * Show Negative line number labels (default: false).
     */
    public Boolean isShowNegativeLineNumbers() {
        return showNegativeLineNumbers;
    }

    /**
     * Show Negative line number labels (default: false).
     */
    public void setShowNegativeLineNumbers(Boolean showNegativeLineNumbers) {
        this.showNegativeLineNumbers = showNegativeLineNumbers;
    }

    /**
     * The grid container border highlight color (default: transparent).
     */
    public RGBA getGridBorderColor() {
        return gridBorderColor;
    }

    /**
     * The grid container border highlight color (default: transparent).
     */
    public void setGridBorderColor(RGBA gridBorderColor) {
        this.gridBorderColor = gridBorderColor;
    }

    /**
     * The cell border color (default: transparent).
     */
    public RGBA getCellBorderColor() {
        return cellBorderColor;
    }

    /**
     * The cell border color (default: transparent).
     */
    public void setCellBorderColor(RGBA cellBorderColor) {
        this.cellBorderColor = cellBorderColor;
    }

    /**
     * Whether the grid border is dashed (default: false).
     */
    public Boolean isGridBorderDash() {
        return gridBorderDash;
    }

    /**
     * Whether the grid border is dashed (default: false).
     */
    public void setGridBorderDash(Boolean gridBorderDash) {
        this.gridBorderDash = gridBorderDash;
    }

    /**
     * Whether the cell border is dashed (default: false).
     */
    public Boolean isCellBorderDash() {
        return cellBorderDash;
    }

    /**
     * Whether the cell border is dashed (default: false).
     */
    public void setCellBorderDash(Boolean cellBorderDash) {
        this.cellBorderDash = cellBorderDash;
    }

    /**
     * The row gap highlight fill color (default: transparent).
     */
    public RGBA getRowGapColor() {
        return rowGapColor;
    }

    /**
     * The row gap highlight fill color (default: transparent).
     */
    public void setRowGapColor(RGBA rowGapColor) {
        this.rowGapColor = rowGapColor;
    }

    /**
     * The row gap hatching fill color (default: transparent).
     */
    public RGBA getRowHatchColor() {
        return rowHatchColor;
    }

    /**
     * The row gap hatching fill color (default: transparent).
     */
    public void setRowHatchColor(RGBA rowHatchColor) {
        this.rowHatchColor = rowHatchColor;
    }

    /**
     * The column gap highlight fill color (default: transparent).
     */
    public RGBA getColumnGapColor() {
        return columnGapColor;
    }

    /**
     * The column gap highlight fill color (default: transparent).
     */
    public void setColumnGapColor(RGBA columnGapColor) {
        this.columnGapColor = columnGapColor;
    }

    /**
     * The column gap hatching fill color (default: transparent).
     */
    public RGBA getColumnHatchColor() {
        return columnHatchColor;
    }

    /**
     * The column gap hatching fill color (default: transparent).
     */
    public void setColumnHatchColor(RGBA columnHatchColor) {
        this.columnHatchColor = columnHatchColor;
    }
}
