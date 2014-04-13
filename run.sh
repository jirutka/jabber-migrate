#!/bin/sh

JAVA="${JAVA:-java}"

if [ ! -d target/dependency ]; then
    mvn compile dependency:copy-dependencies || exit $?
fi

cp=target/classes
for jar in target/dependency/*.jar; do
    cp="$cp:$jar"
done

$JAVA -classpath "$JAVA_HOME/lib/ext/*:$cp" cz.rdc.devel.jabber.migrate.Main "$@"

if [ $? != 0 ]; then
    echo ""
    echo "JAVA_HOME=$JAVA_HOME"
fi
