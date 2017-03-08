package com.proxerme.library.api;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.ToJson;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * TODO: Describe class
 *
 * @author Ruben Gees
 */
final class DateAdapter {

    private static final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    @FromJson
    Date fromJson(final String date) throws ParseException {
        final Long dateAsLong = toLongOrNull(date);

        if (dateAsLong != null) {
            // The API returns seconds
            return new Date(dateAsLong * 1000);
        } else {
            return FORMAT.parse(date);
        }
    }

    @ToJson
    long toJson(final Date date) {
        return date.getTime();
    }

    private Long toLongOrNull(String candidate) {
        try {
            return Long.parseLong(candidate);
        } catch (NumberFormatException ignored) {
            return null;
        }
    }
}
