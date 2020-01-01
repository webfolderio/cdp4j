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

import java.util.List;

import io.webfolder.cdp.session.SessionInvocationHandler;
import io.webfolder.cdp.type.constant.KeyEventType;
import io.webfolder.cdp.type.constant.MouseButtonType;
import io.webfolder.cdp.type.constant.MouseEventType;
import io.webfolder.cdp.type.constant.TouchEventType;
import io.webfolder.cdp.type.input.GestureSourceType;
import io.webfolder.cdp.type.input.TouchPoint;

public class InputImpl implements Input {

    private final SessionInvocationHandler handler;

    public InputImpl(SessionInvocationHandler handler) {
        this.handler = handler;
    }

    @Override
    public void dispatchKeyEvent(KeyEventType type) {
        handler.invoke("Input", "dispatchKeyEvent", "Input.dispatchKeyEvent", null, void.class, null, true, false,
                false, new String[] { "type" }, new Object[] { type });
    }

    @Override
    public void dispatchKeyEvent(KeyEventType type, Integer modifiers, Double timestamp, String text,
            String unmodifiedText, String keyIdentifier, String code, String key, Integer windowsVirtualKeyCode,
            Integer nativeVirtualKeyCode, Boolean autoRepeat, Boolean isKeypad, Boolean isSystemKey, Integer location) {
        handler.invoke("Input", "dispatchKeyEvent", "Input.dispatchKeyEvent", null, void.class, null, true, false,
                false,
                new String[] { "type", "modifiers", "timestamp", "text", "unmodifiedText", "keyIdentifier", "code",
                        "key", "windowsVirtualKeyCode", "nativeVirtualKeyCode", "autoRepeat", "isKeypad", "isSystemKey",
                        "location" },
                new Object[] { type, modifiers, timestamp, text, unmodifiedText, keyIdentifier, code, key,
                        windowsVirtualKeyCode, nativeVirtualKeyCode, autoRepeat, isKeypad, isSystemKey, location });
    }

    @Override
    public void dispatchMouseEvent(MouseEventType type, Double x, Double y) {
        handler.invoke("Input", "dispatchMouseEvent", "Input.dispatchMouseEvent", null, void.class, null, true, false,
                false, new String[] { "type", "x", "y" }, new Object[] { type, x, y });
    }

    @Override
    public void dispatchMouseEvent(MouseEventType type, Double x, Double y, Integer modifiers, Double timestamp,
            MouseButtonType button, Integer clickCount, Double deltaX, Double deltaY) {
        handler.invoke("Input", "dispatchMouseEvent", "Input.dispatchMouseEvent", null, void.class, null, true, false,
                false,
                new String[] { "type", "x", "y", "modifiers", "timestamp", "button", "clickCount", "deltaX", "deltaY" },
                new Object[] { type, x, y, modifiers, timestamp, button, clickCount, deltaX, deltaY });
    }

    @Override
    public void dispatchTouchEvent(TouchEventType type, List<TouchPoint> touchPoints) {
        handler.invoke("Input", "dispatchTouchEvent", "Input.dispatchTouchEvent", null, void.class, null, true, false,
                false, new String[] { "type", "touchPoints" }, new Object[] { type, touchPoints });
    }

    @Override
    public void dispatchTouchEvent(TouchEventType type, List<TouchPoint> touchPoints, Integer modifiers,
            Double timestamp) {
        handler.invoke("Input", "dispatchTouchEvent", "Input.dispatchTouchEvent", null, void.class, null, true, false,
                false, new String[] { "type", "touchPoints", "modifiers", "timestamp" },
                new Object[] { type, touchPoints, modifiers, timestamp });
    }

    @Override
    public void emulateTouchFromMouseEvent(MouseEventType type, Integer x, Integer y, MouseButtonType button) {
        handler.invoke("Input", "emulateTouchFromMouseEvent", "Input.emulateTouchFromMouseEvent", null, void.class,
                null, true, false, false, new String[] { "type", "x", "y", "button" },
                new Object[] { type, x, y, button });
    }

    @Override
    public void emulateTouchFromMouseEvent(MouseEventType type, Integer x, Integer y, MouseButtonType button,
            Double timestamp, Double deltaX, Double deltaY, Integer modifiers, Integer clickCount) {
        handler.invoke("Input", "emulateTouchFromMouseEvent", "Input.emulateTouchFromMouseEvent", null, void.class,
                null, true, false, false,
                new String[] { "type", "x", "y", "button", "timestamp", "deltaX", "deltaY", "modifiers", "clickCount" },
                new Object[] { type, x, y, button, timestamp, deltaX, deltaY, modifiers, clickCount });
    }

    @Override
    public void insertText(String text) {
        handler.invoke("Input", "insertText", "Input.insertText", null, void.class, null, true, false, false,
                new String[] { "text" }, new Object[] { text });
    }

    @Override
    public void setIgnoreInputEvents(Boolean ignore) {
        handler.invoke("Input", "setIgnoreInputEvents", "Input.setIgnoreInputEvents", null, void.class, null, true,
                false, false, new String[] { "ignore" }, new Object[] { ignore });
    }

    @Override
    public void synthesizePinchGesture(Double x, Double y, Double scaleFactor) {
        handler.invoke("Input", "synthesizePinchGesture", "Input.synthesizePinchGesture", null, void.class, null, true,
                false, false, new String[] { "x", "y", "scaleFactor" }, new Object[] { x, y, scaleFactor });
    }

    @Override
    public void synthesizePinchGesture(Double x, Double y, Double scaleFactor, Integer relativeSpeed,
            GestureSourceType gestureSourceType) {
        handler.invoke("Input", "synthesizePinchGesture", "Input.synthesizePinchGesture", null, void.class, null, true,
                false, false, new String[] { "x", "y", "scaleFactor", "relativeSpeed", "gestureSourceType" },
                new Object[] { x, y, scaleFactor, relativeSpeed, gestureSourceType });
    }

    @Override
    public void synthesizeScrollGesture(Double x, Double y) {
        handler.invoke("Input", "synthesizeScrollGesture", "Input.synthesizeScrollGesture", null, void.class, null,
                true, false, false, new String[] { "x", "y" }, new Object[] { x, y });
    }

    @Override
    public void synthesizeScrollGesture(Double x, Double y, Double xDistance, Double yDistance, Double xOverscroll,
            Double yOverscroll, Boolean preventFling, Integer speed, GestureSourceType gestureSourceType,
            Integer repeatCount, Integer repeatDelayMs, String interactionMarkerName) {
        handler.invoke("Input", "synthesizeScrollGesture", "Input.synthesizeScrollGesture", null, void.class, null,
                true, false, false,
                new String[] { "x", "y", "xDistance", "yDistance", "xOverscroll", "yOverscroll", "preventFling",
                        "speed", "gestureSourceType", "repeatCount", "repeatDelayMs", "interactionMarkerName" },
                new Object[] { x, y, xDistance, yDistance, xOverscroll, yOverscroll, preventFling, speed,
                        gestureSourceType, repeatCount, repeatDelayMs, interactionMarkerName });
    }

    @Override
    public void synthesizeTapGesture(Double x, Double y) {
        handler.invoke("Input", "synthesizeTapGesture", "Input.synthesizeTapGesture", null, void.class, null, true,
                false, false, new String[] { "x", "y" }, new Object[] { x, y });
    }

    @Override
    public void synthesizeTapGesture(Double x, Double y, Integer duration, Integer tapCount,
            GestureSourceType gestureSourceType) {
        handler.invoke("Input", "synthesizeTapGesture", "Input.synthesizeTapGesture", null, void.class, null, true,
                false, false, new String[] { "x", "y", "duration", "tapCount", "gestureSourceType" },
                new Object[] { x, y, duration, tapCount, gestureSourceType });
    }
}