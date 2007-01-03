package cz.rdc.devel.jabber.migrate;

public class Main {
    static final int MAX_ARGS = 3;

    private static void printUsageAndExit(String error) {
        System.err.println("Error: " + error);
        System.err.println("Usage: command username:password@host[:port] [file]");
        System.err.println("Example: get test:pass@jabber.sh.cvut.cz:5222 export.txt");
        System.err.println("Example: put test2:newpass@jabber.org <export.txt");
        System.exit(1);
    }

    public static void main(String[] args) throws Exception {
        if (args.length < MAX_ARGS - 1) {
            printUsageAndExit("Too few arguments");
            return;
        }
        if (args.length > MAX_ARGS) {
            printUsageAndExit("Too many arguments");
            return;
        }

        String cmd = args[0];
        String connectString = args[1];
        String file = args.length == MAX_ARGS ? args[MAX_ARGS - 1] : null;

        Command command;
        if ("get".equals(cmd)) {
            command = new RosterGet(IOSupport.createOutput(file));
        }
        else if ("put".equals(cmd)) {
            command = new RosterPut(IOSupport.createInput(file));
        }
        else {
            printUsageAndExit("Unknown command '" + cmd + "'");
            return;
        }

        Login login = new Login(connectString);
        command.work(login.connect());
    }
}
