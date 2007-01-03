package cz.rdc.devel.jabber.migrate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Re {
    /**
     * Returns groups.
     * @throws IllegalArgumentException when where is no match
     */
    public static String[] deformat(String pattern, String input) {
        Matcher matcher = Pattern.compile(pattern).matcher(input);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Wrong input format"
                    + "; pattern='" + pattern + "'"
                    + "; input='" + input + "'");
        }

        String[] result = new String[matcher.groupCount()];
        for (int i = 0; i < result.length; ++i) {
            result[i] = matcher.group(i + 1);
        }
        return result;
    }
}
