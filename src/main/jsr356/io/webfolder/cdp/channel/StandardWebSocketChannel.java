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
package io.webfolder.cdp.channel;

import java.io.IOException;

import javax.websocket.CloseReason;
import javax.websocket.CloseReason.CloseCodes;
import javax.websocket.Session;

public class StandardWebSocketChannel implements Channel {

    private final Session session;

    public StandardWebSocketChannel(Session session) {
        this.session = session;
    }

    @Override
    public boolean isOpen() {
        return session.isOpen();
    }

    @Override
    public void disconnect() {
        try {
            session.close(new CloseReason(CloseCodes.getCloseCode(CLOSE_STATUS_CODE), CLOSE_REASON_TEXT));
        } catch (IOException e) {
            // ignore
        }
    }

    @Override
    public void sendText(String message) {
        if (session.isOpen()) {
            session.getAsyncRemote().sendText(message);
        }
    }

    @Override
    public void connect() {
        // no op
    }
}
