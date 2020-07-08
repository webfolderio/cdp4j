package io.webfolder.cdp.type.target;

public class TargetInfo {
    private String targetId;

    private String type;

    private String title;

    private String url;

    private Boolean attached;

    private String openerId;

    private String browserContextId;

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Whether the target has an attached client.
     */
    public Boolean isAttached() {
        return attached;
    }

    /**
     * Whether the target has an attached client.
     */
    public void setAttached(Boolean attached) {
        this.attached = attached;
    }

    /**
     * Opener target Id
     */
    public String getOpenerId() {
        return openerId;
    }

    /**
     * Opener target Id
     */
    public void setOpenerId(String openerId) {
        this.openerId = openerId;
    }

    public String getBrowserContextId() {
        return browserContextId;
    }

    public void setBrowserContextId(String browserContextId) {
        this.browserContextId = browserContextId;
    }
}
