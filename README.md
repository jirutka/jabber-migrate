Jabber Roster Migration
=======================

This tool allows to migrate from one jabber server to another.


Usage
-----

      MODE                     : export, or import
      --help                   : Show help
      -f (--file) PATH         : Roster file path (default is stdout/stdin)
      -h (--host) HOST         : Server address
      -p (--port) PORT         : Server port (default is 5222)
      -u (--jid) JID           : Jabber ID
      -w (--password) PASSWORD : Password

Roster export:

    $ ./bin/roster-migrate.sh get get -u kevin@flynn.com -w top-secret -h talk.google.com -p 5222 -f export.txt

Roster import:

    $ ./bin/roster-migrate.sh put -u kevin@flynn.com -w top-secret -h talk.google.com -p 5222 < export.txt



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

This project is fork of http://sourceforge.net/projects/migrate/.
