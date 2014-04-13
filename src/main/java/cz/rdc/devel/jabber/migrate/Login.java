package cz.rdc.devel.jabber.migrate;

import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;

/**
 * Stores login information.
 */
public class Login {

    private String username;
    private String password;
    private String host;
    private int port = 5222;

    /**
     * Converts connect strings to values.
     * @param connectString connect string with format: username:password@host[:port]
     * @throws IllegalArgumentException when the connectString is invalid
     */
    public Login(String connectString) {
        String[] parts = connectString.split("@", 2);
        if (parts.length != 2) {
            throw new IllegalArgumentException("Host part is missing");
        }
        String userPass = parts[0];
        String hostPort = parts[1];

        parts = userPass.split(":", 2);
        if (parts.length != 2) {
            throw new IllegalArgumentException("Password part is missing");
        }
        username = parts[0];
        password = parts[1];

        parts = hostPort.split(":", 2);
        if (parts.length != 2) {
            host = hostPort;
        } else {
            try {
                host = parts[0];
                port = Integer.parseInt(parts[1]);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Port number is invalid");
            }
        }
    }

    /**
     * Connects to the Jabber server.
     */
    public XMPPConnection connect() throws XMPPException {
        ConnectionConfiguration config = new ConnectionConfiguration(host, port);

        XMPPConnection con = new XMPPConnection(config);
        con.connect();
        con.login(username, password);

        return con;
    }
}

