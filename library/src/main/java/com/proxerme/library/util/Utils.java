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

    public static long timestampToUnixTime(@NonNull String timestamp) {
        SimpleDateFormat dateParser = new SimpleDateFormat(PATTERN, Locale.getDefault());

        try {
            return dateParser.parse(timestamp).getTime() / 1000;
        } catch (ParseException e) {
            throw new UncheckedParseException(e);
        }
    }

    @VisibleForTesting
    public static class UncheckedParseException extends RuntimeException {

        public UncheckedParseException(Exception e) {
            super(e);
        }
    }
}
