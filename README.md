Jabber Roster Migration
=======================

This tool allows to migrate from one jabber server to another.

Usage: migrate command username:password@host[:port] [file]

Roster export:
$ ./bin/migrate.sh get test:pass@jabber.sh.cvut.cz:5222 export.txt

Roster import:
$ ./bin/migrate.sh put test2:newpass@jabber.org <export.txt



Import/export format
--------------------

The exported format contains one line for every contact.
Format:
<isRemove>;<nickname>;<user>;[<groups>]

<isRemove> ... '-' to remove contact, '+' to add contact
<nickname> ... contact nickname
<user>     ... contact ID
<groups>   ... list of groups separated by comma

Examples:
+;Sam;somebody@jabber.cz;[Friends]
-;alien;123@icq;[]
+;alien;123@icq.netlab.cz;[Sales,Travel]


Origin
------

This project is a fork of http://sourceforge.net/projects/migrate/.
