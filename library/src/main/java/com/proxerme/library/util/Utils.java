package com.proxerme.library.util;

import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Utility class containing various methods for all kind of purposes.
 *
 * @author Ruben Gees
 */
public final class Utils {

    private static final String PATTERN = "yyyy-MM-dd HH:mm:ss";

    private Utils() {

    }

    /**
     * Converts an ISO timestamp into an UNIX timestamp.
     *
     * @param timestamp The ISO timestamp.
     * @return The converted UNIX timestamp
     */
    public static long timestampToUnixTime(@NonNull String timestamp) {
        SimpleDateFormat dateParser = new SimpleDateFormat(PATTERN, Locale.getDefault());

        try {
            return dateParser.parse(timestamp).getTime() / 1000;
        } catch (ParseException e) {
            throw new UncheckedParseException(e);
        }
    }

    /**
     * Converts a two dimensional array into a one dimensional (Used for parceling).
     * Stolen from here: http://stackoverflow.com/a/9562083/4279995
     *
     * @param input The array to convert.
     * @return The newly converted array.
     */
    public static String[] toOneDimension(String[][] input) {
        String[] output = new String[input.length * input[0].length];

        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[i].length; j++) {
                output[i * j] = input[i][j];
            }
        }

        return output;
    }


    /**
     * Converts a one dimensional array back into a two dimensional (Used for parceling).
     * Stolen from here: http://stackoverflow.com/a/9562083/4279995
     *
     * @param input The array to convert.
     * @return The newly converted array.
     */
    public static String[][] toTwoDimensions(int dimensions, String[] input) {
        String[][] output = new String[input.length / dimensions][dimensions];

        for (int i = 0; i < input.length; i++) {
            output[i / dimensions][i % dimensions] = input[i];
        }

        return output;
    }

    @VisibleForTesting
    public static class UncheckedParseException extends RuntimeException {

        public UncheckedParseException(Exception e) {
            super(e);
        }
    }
}
