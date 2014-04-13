package cz.rdc.devel.jabber.migrate;

import org.jivesoftware.smack.Roster;
import org.jivesoftware.smack.RosterEntry;
import org.jivesoftware.smack.RosterGroup;
import org.jivesoftware.smack.XMPPConnection;

import java.io.PrintStream;

/**
 * Exports contacts from a roster.
 */
public class RosterGet implements Command {

    private PrintStream out;

    public RosterGet(PrintStream out) {
        this.out = out;
    }

    @SuppressWarnings("unchecked")
    public void work(XMPPConnection con) throws Exception {
        Roster roster = con.getRoster();

        for (RosterEntry entry : roster.getEntries()) {
            Contact contact = new Contact();
            contact.setNickname(entry.getName());
            contact.setUser(entry.getUser());

            for (RosterGroup group : entry.getGroups()) {
                contact.addGroup(group.getName());
            }
            out.println(contact);
        }
    }
}
