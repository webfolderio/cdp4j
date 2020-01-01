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
package io.webfolder.cdp.session;

import java.util.Map;

import io.webfolder.cdp.event.Events;
import io.webfolder.cdp.event.target.DetachedFromTarget;
import io.webfolder.cdp.event.target.TargetDestroyed;
import io.webfolder.cdp.listener.EventListener;

class TargetListener implements EventListener {

    private Map<String, Session> sessions;

    TargetListener(Map<String, Session> sessions) {
        this.sessions = sessions;
    }

    @Override
    public void onEvent(Events event, Object value) {
        switch (event) {
            case TargetTargetDestroyed:
                TargetDestroyed destroyed = (TargetDestroyed) value;
                for (Session next : sessions.values()) {
                    if (destroyed.getTargetId().equals(next.getTargetId())) {
                        if ( sessions.remove(next.getId()) != null ) {
                            next.dispose();
                        }
                    }
                }
            break;
            case TargetDetachedFromTarget:
                DetachedFromTarget detached = (DetachedFromTarget) value;
                Session removed = null;
                if ( ( removed = sessions.remove(detached.getSessionId()) ) != null ) {
                    removed.dispose();
                }
            break;
            default:
            break;
        }
    }
}
