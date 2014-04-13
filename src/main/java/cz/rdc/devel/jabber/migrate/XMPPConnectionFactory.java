package cz.rdc.devel.jabber.migrate;

import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;

public class XMPPConnectionFactory {

    public static XMPPConnection connectAndLogin(
            String jid, String password, String host, int port) throws XMPPException {

        String serviceName = jid.contains("@") ? jid.split("@")[1] : host;
        ConnectionConfiguration config = new ConnectionConfiguration(host, port, serviceName);

        XMPPConnection conn = new XMPPConnection(config);
        conn.connect();
        conn.login(jid, password);

        return conn;
    }
}
