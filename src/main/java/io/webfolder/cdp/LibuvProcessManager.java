package io.webfolder.cdp;

public class LibuvProcessManager extends ProcessManager {

    @Override
    void setProcess(CdpProcess process) {
    }

    @Override
    public boolean kill() {
        return true;
    }
}
