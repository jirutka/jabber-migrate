package cz.rdc.devel.jabber.migrate;

import java.io.BufferedReader;
import java.io.IOException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.jivesoftware.smack.Roster;
import org.jivesoftware.smack.RosterEntry;
import org.jivesoftware.smack.XMPPConnection;

/**
 * Puts contacts to a roster.
 */
public class RosterPut implements Command {
    private static final Log logger =
        LogFactory.getLog(RosterPut.class);

    private BufferedReader in;

    public RosterPut(BufferedReader in) {
        this.in = in;
    }

    /**
     * Imports contact to the new roster.
     * It first reads all contacts to ensure their validity.
     * They are then imported to the roster.
     * Input file format:
     * nickname;user;[group,group,...]
     */
    public void work(XMPPConnection con) throws Exception {
        Roster roster = con.getRoster();

        List<Contact> contacts = parseContacts();
        for (Contact contact : contacts) {
            if (contact.isRemove()) {
                logger.info("Removing contact: " + contact);
                RosterEntry entry = roster.getEntry(contact.getUser());
                if (entry != null) {
                    roster.removeEntry(entry);
                }
            }
            else {
                logger.info("Importing contact: " + contact);
                roster.createEntry(contact.getUser(), contact.getNickname(), contact.getGroups());
            }
        }

        waitForRosterUpdate(roster, contacts);
    }

    /**
     * Ensures that all the new contacts are in roster before quit.
     * See the issue: http://www.jivesoftware.org/issues/browse/SMACK-10
     */
    @SuppressWarnings("unchecked")
    private void waitForRosterUpdate(Roster roster, List<Contact> contacts) throws InterruptedException {
        Set<String> newUsers = new HashSet<String>();
        for (Contact contact : contacts) {
            if (!contact.isRemove()) {
                newUsers.add(contact.getUser());
            }
        }

        while (newUsers.size() > 0) {
            Iterator<RosterEntry> entries = roster.getEntries();
            while (entries.hasNext()) {
                RosterEntry entry = entries.next();
                newUsers.remove(entry.getUser());
            }

            logger.info("Waiting for roster update: "
                    + (contacts.size() - newUsers.size())
                    + "/" + contacts.size());
            Thread.sleep(1000);
        }
    }

    private List<Contact> parseContacts() throws IOException {
        List<Contact> contacts = new ArrayList<Contact>();
        String line;
        while ((line = in.readLine()) != null) {
            String[] parts = Re.deformat("([+-]);([^;]*);([^;]+);\\[([^\\]]*)\\]", line);
            Contact contact = new Contact();
            contact.setRemove("-".equals(parts[0]));
            contact.setNickname("".equals(parts[1]) ? null : parts[1]);
            contact.setUser(parts[2]);

            String groups = parts[3];
            for (String group : groups.split(",")) {
                if (group.length() > 0) {
                    contact.addGroup(group);
                }
            }

            contacts.add(contact);
        }
        return contacts;
    }
}
