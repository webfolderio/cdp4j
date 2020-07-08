package io.webfolder.cdp.event.target;

import io.webfolder.cdp.annotation.Domain;
import io.webfolder.cdp.annotation.EventName;
import io.webfolder.cdp.annotation.Experimental;
import io.webfolder.cdp.type.target.TargetInfo;

/**
 * Issued when attached to target because of auto-attach or attachToTarget command
 */
@Experimental
@Domain("Target")
@EventName("attachedToTarget")
public class AttachedToTarget {
    private String sessionId;

    private TargetInfo targetInfo;

    private Boolean waitingForDebugger;

    /**
     * Identifier assigned to the session used to send/receive messages.
     */
    public String getSessionId() {
        return sessionId;
    }

    /**
     * Identifier assigned to the session used to send/receive messages.
     */
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public TargetInfo getTargetInfo() {
        return targetInfo;
    }

    public void setTargetInfo(TargetInfo targetInfo) {
        this.targetInfo = targetInfo;
    }

    public Boolean isWaitingForDebugger() {
        return waitingForDebugger;
    }

    public void setWaitingForDebugger(Boolean waitingForDebugger) {
        this.waitingForDebugger = waitingForDebugger;
    }
}
