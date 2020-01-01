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
package io.webfolder.cdp.type.network;

import com.vimeo.stag.UseStag;

/**
 * WebSocket message data
 * This represents an entire WebSocket message, not just a fragmented frame as the name suggests
 */
@UseStag
public class WebSocketFrame {
    private Double opcode;

    private Boolean mask;

    private String payloadData;

    /**
     * WebSocket message opcode.
     */
    public Double getOpcode() {
        return opcode;
    }

    /**
     * WebSocket message opcode.
     */
    public void setOpcode(Double opcode) {
        this.opcode = opcode;
    }

    /**
     * WebSocket message mask.
     */
    public Boolean isMask() {
        return mask;
    }

    /**
     * WebSocket message mask.
     */
    public void setMask(Boolean mask) {
        this.mask = mask;
    }

    /**
     * WebSocket message payload data.
     * If the opcode is 1, this is a text message and payloadData is a UTF-8 string.
     * If the opcode isn't 1, then payloadData is a base64 encoded string representing binary data.
     */
    public String getPayloadData() {
        return payloadData;
    }

    /**
     * WebSocket message payload data.
     * If the opcode is 1, this is a text message and payloadData is a UTF-8 string.
     * If the opcode isn't 1, then payloadData is a base64 encoded string representing binary data.
     */
    public void setPayloadData(String payloadData) {
        this.payloadData = payloadData;
    }
}
