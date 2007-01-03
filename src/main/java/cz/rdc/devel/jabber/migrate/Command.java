package cz.rdc.devel.jabber.migrate;

import org.jivesoftware.smack.XMPPConnection;

public interface Command {
    public void work(XMPPConnection con) throws Exception;
}
