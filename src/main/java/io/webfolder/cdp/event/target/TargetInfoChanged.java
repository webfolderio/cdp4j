package io.webfolder.cdp.event.target;

import io.webfolder.cdp.annotation.Domain;
import io.webfolder.cdp.annotation.EventName;
import io.webfolder.cdp.type.target.TargetInfo;

/**
 * Issued when some information about a target has changed
 * This only happens between
 * targetCreated and targetDestroyed
 */
@Domain("Target")
@EventName("targetInfoChanged")
public class TargetInfoChanged {
    private TargetInfo targetInfo;

    public TargetInfo getTargetInfo() {
        return targetInfo;
    }

    public void setTargetInfo(TargetInfo targetInfo) {
        this.targetInfo = targetInfo;
    }
}
