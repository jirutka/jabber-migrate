package cz.rdc.devel.jabber.migrate;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.Reader;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class IOSupport {
    private static final Log logger =
        LogFactory.getLog(IOSupport.class);

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
            logger.info("Using system input as input source.");
            reader = new InputStreamReader(System.in);
        }
        else {
            reader = new FileReader(file);
        }
        return new BufferedReader(reader);
    }
}
