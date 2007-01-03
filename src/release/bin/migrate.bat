
set PROG_HOME=.
if exist ..\bin\migrate.bat set PROG_HOME=..

java -Djava.ext.dirs=%PROG_HOME%\lib cz.rdc.devel.jabber.migrate.Main %1 %2 %3

