#!/bin/sh

SCRIPT_DIR="$(cd "$(dirname "$(readlink "$0" || echo "$0")")" && pwd -P)"
LIBS_DIR="${SCRIPT_DIR}/../lib"
JAVA="${JAVA:-java}"

$JAVA -Djava.ext.dirs="$LIBS_DIR" -classpath "$JAVA_HOME/lib/ext/*" cz.rdc.devel.jabber.migrate.Main "$@"
[ $? -eq 0 ] || echo -e "\nJAVA_HOME=$JAVA_HOME"
