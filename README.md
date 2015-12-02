Jabber Roster Migration
=======================

This tool simplifies migrating of a roster (contact list in XMPP terminology) from one Jabber server (including GTalk!) to another.


Usage
-----

    $ ./bin/roster-migrate MODE <options>

     MODE                 : export, or import
     --help               : Show help
     -f (--file) PATH     : Roster file path (default is stdout/stdin)
     -h (--host) HOST     : Server hostname (default is same as service name)
     -p (--port) PORT     : Server port (default is 5222)
     -s (--service) HOST  : Service (domain) name; the portion of JID after at (@)
                            sign.
     -u (--username) NAME : Username; usually the portion of JID before at (@)
                            sign, but some severs uses whole JID as an username.
     -w (--password) PASS : Password

Roster export:

    $ ./bin/roster-migrate export -u kevin@flynn.com -s flynn.com -w top-secret -h talk.google.com -f export.txt

Roster import:

    $ ./bin/roster-migrate import -u flynn -s jabbim.cz -w top-secret < export.txt



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
