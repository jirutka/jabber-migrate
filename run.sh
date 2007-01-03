#!/bin/sh

#mvn compile dependency:copy-dependencies || exit $?

cp=target/classes
for jar in target/dependency/*.jar ; do
    cp="$cp:$jar"
done

exec java -cp "$cp" cz.rdc.devel.jabber.migrate.Main "$@"
