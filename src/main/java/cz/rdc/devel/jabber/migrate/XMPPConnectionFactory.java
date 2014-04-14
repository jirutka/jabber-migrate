package cz.rdc.devel.jabber.migrate;

import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;

public class XMPPConnectionFactory {

    public static XMPPConnection connectAndLogin(
            String username, String serviceName, String password, String host, int port) throws XMPPException {

        if (host == null || host.isEmpty()) {
            host = serviceName;
        }
        ConnectionConfiguration config = new ConnectionConfiguration(host, port, serviceName);

        XMPPConnection conn = new XMPPConnection(config);
        conn.connect();
        conn.login(username, password);

        return conn;
    }
}
