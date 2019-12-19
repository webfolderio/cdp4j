package io.webfolder.cdp;

import io.webfolder.cdp.channel.LibuvChannelFactory;

public class LibuvProcessManager extends ProcessManager {

    private final LibuvChannelFactory libuvChannelFactory;

    public LibuvProcessManager(LibuvChannelFactory libuvChannelFactory) {
        this.libuvChannelFactory = libuvChannelFactory;
    }

    @Override
    void setProcess(CdpProcess process) {
        // no op
    }

    @Override
    public boolean kill() {
        return libuvChannelFactory.getProcess()
                                  .kill();
    }
}
