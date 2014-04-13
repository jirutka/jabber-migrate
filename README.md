Jabber Roster Migration
=======================

This tool simplifies migrating of a roster (contact list in XMPP terminology) from one Jabber server (including GTalk!) to another.


Usage
-----

      $ ./bin/roster-migrate.sh MODE <options>

      MODE                     : export, or import
      --help                   : Show help
      -f (--file) PATH         : Roster file path (default is stdout/stdin)
      -h (--host) HOST         : Server address
      -p (--port) PORT         : Server port (default is 5222)
      -u (--jid) JID           : Jabber ID
      -w (--password) PASSWORD : Password

Roster export:

    $ ./bin/roster-migrate.sh export -u kevin@flynn.com -w top-secret -h talk.google.com -p 5222 -f export.txt

Roster import:

    $ ./bin/roster-migrate.sh import -u kevin@flynn.com -w top-secret -h talk.google.com -p 5222 < export.txt



Import/export format
--------------------

The exported format contains one line for every contact.

Format:

    <isRemove>;<nickname>;<user>;[<groups>]

* isRemove ... `-` to remove contact, `+` to add contact
* nickname ... contact nickname
* user     ... contact ID
* groups   ... comma-separated list of groups

Examples:

    +;Sam;somebody@jabber.cz;[Friends]
    -;alien;123@icq;[]
    +;alien;123@icq.netlab.cz;[Sales,Travel]


Origin
------

This project is a fork of http://sourceforge.net/projects/migrate/ by [Ivo Danihelka](https://github.com/fidlej).
