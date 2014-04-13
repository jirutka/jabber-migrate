#!/bin/bash

DIR="$( cd "$( dirname "$0" )" && pwd )"
LIB="$DIR/../lib"
JAVA="${JAVA:-java}"

$JAVA -Djava.ext.dirs="$LIB" -classpath "$JAVA_HOME/lib/ext/*" cz.rdc.devel.jabber.migrate.Main "$@"

if [ $? != 0 ]; then
    echo ""
    echo "JAVA_HOME=$JAVA_HOME"
fi
