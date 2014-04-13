package cz.rdc.devel.jabber.migrate;

import java.io.PrintStream;

import java.util.Iterator;

import org.jivesoftware.smack.Roster;
import org.jivesoftware.smack.RosterEntry;
import org.jivesoftware.smack.RosterGroup;
import org.jivesoftware.smack.XMPPConnection;

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

        Iterator<RosterEntry> entries = roster.getEntries();
        while (entries.hasNext()) {
            RosterEntry entry = entries.next();
            Contact contact = new Contact();
            contact.setNickname(entry.getName());
            contact.setUser(entry.getUser());

            Iterator<RosterGroup> groups = entry.getGroups();
            while (groups.hasNext()) {
                RosterGroup group = groups.next();
                contact.addGroup(group.getName());
            }
            out.println(contact);
        }
    }
}
