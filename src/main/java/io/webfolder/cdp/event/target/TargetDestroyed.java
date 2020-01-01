package io.webfolder.cdp.event.target;

import com.vimeo.stag.UseStag;

import io.webfolder.cdp.annotation.Domain;
import io.webfolder.cdp.annotation.EventName;

/**
 * Issued when a target is destroyed
 */
@Domain("Target")
@EventName("targetDestroyed")
@UseStag
public class TargetDestroyed {
    private String targetId;

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }
}
