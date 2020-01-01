package io.webfolder.cdp.event.target;

import com.vimeo.stag.UseStag;

import io.webfolder.cdp.annotation.Domain;
import io.webfolder.cdp.annotation.EventName;

/**
 * Notifies about a new protocol message received from the session (as reported in
 * `attachedToTarget` event)
 */
@Domain("Target")
@EventName("receivedMessageFromTarget")
@UseStag
public class ReceivedMessageFromTarget {
    private String sessionId;

    private String message;

    private String targetId;

    /**
     * Identifier of a session which sends a message.
     */
    public String getSessionId() {
        return sessionId;
    }

    /**
     * Identifier of a session which sends a message.
     */
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Deprecated.
     */
    public String getTargetId() {
        return targetId;
    }

    /**
     * Deprecated.
     */
    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }
}
