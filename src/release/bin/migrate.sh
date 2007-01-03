#!/bin/sh

dir=`dirname "$0"`
PROG_HOME=`cd "$dir/.." ; pwd`
echo "PROG_HOME=$PROG_HOME"

exec java -Djava.ext.dirs="$PROG_HOME/lib" cz.rdc.devel.jabber.migrate.Main "$@"
