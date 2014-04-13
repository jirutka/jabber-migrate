package cz.rdc.devel.jabber.migrate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;


public abstract class IOSupport {

    private static final Logger LOG = LoggerFactory.getLogger(IOSupport.class);

    /**
     * Returns System.out or file to overwrite.
     */
    public static PrintStream createOutput(String file) throws FileNotFoundException {
        if (file == null) {
            return System.out;
        }
        return new PrintStream(new FileOutputStream(file));
    }

    /**
     * Returns reader from System.in or from file to read.
     */
    public static BufferedReader createInput(String file) throws FileNotFoundException {
        Reader reader;
        if (file == null) {
            LOG.info("Using system input as input source.");
            reader = new InputStreamReader(System.in);
        } else {
            reader = new FileReader(file);
        }
        return new BufferedReader(reader);
    }
}
