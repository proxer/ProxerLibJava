package com.proxerme.library.util;

import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.annotation.VisibleForTesting;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Utility class containing various methods for all kind of purposes.
 *
 * @author Ruben Gees
 */
@RestrictTo(RestrictTo.Scope.LIBRARY)
public final class Utils {

    private static final String DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";

    private static final String EMPTY_DATA_PATTERN = "\"data\":\"(\\[\\])?\"";
    private static final String EMPTY_DATA_REPLACEMENT = "\"data\":{}";
    private static final String START_PATTERN = "\"{";
    private static final String END_PATTERN = "}\"";
    private static final String START_REPLACEMENT = "{";
    private static final String END_REPLACEMENT = "}";
    private static final String ESCAPE_PATTERN = "\\";
    private static final String ESCAPE_REPLACEMENT = "";

    private Utils() {

    }

    /**
     * Converts an ISO timestamp into an UNIX timestamp.
     *
     * @param timestamp The ISO timestamp.
     * @return The converted UNIX timestamp
     */
    public static long timestampToUnixTime(@NonNull String timestamp) {
        SimpleDateFormat dateParser = new SimpleDateFormat(DATE_PATTERN, Locale.getDefault());

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
     * @param dimensions The amount of dimensions the input has.
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

    /**
     * Parses and repairs the broken comment JSON.
     *
     * @param rawData The raw response from the API.
     * @return The parsable JSON.
     */
    @NonNull
    public static String parseCommentData(@NonNull String rawData) {
        String result = rawData.replaceAll(EMPTY_DATA_PATTERN, EMPTY_DATA_REPLACEMENT);

        int startIndex = result.indexOf(START_PATTERN);
        int endIndex = result.indexOf(END_PATTERN);

        while (startIndex > 0 && endIndex > 0) {
            String convertedSection = result.substring(startIndex, endIndex + END_PATTERN.length())
                    .replace(START_PATTERN, START_REPLACEMENT)
                    .replace(ESCAPE_PATTERN, ESCAPE_REPLACEMENT)
                    .replace(END_PATTERN, END_REPLACEMENT);

            result = result.substring(0, startIndex) + convertedSection +
                    result.substring(endIndex + END_PATTERN.length());
            startIndex = result.indexOf(START_PATTERN, startIndex);
            endIndex = result.indexOf(END_PATTERN, endIndex);
        }

        return result;
    }

    @VisibleForTesting
    public static class UncheckedParseException extends RuntimeException {
        public UncheckedParseException(Exception e) {
            super(e);
        }
    }
}
