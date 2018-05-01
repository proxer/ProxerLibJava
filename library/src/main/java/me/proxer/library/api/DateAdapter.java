package me.proxer.library.api;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.ToJson;

import javax.annotation.Nullable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author Ruben Gees
 */
final class DateAdapter {

    // The API returns seconds so multiply by 1000.
    private static final int DATE_MULTIPLICAND = 1000;

    @FromJson
    Date fromJson(final String date) throws ParseException {
        final Long dateAsLong = toLongOrNull(date);

        if (dateAsLong != null) {
            return new Date(dateAsLong * DATE_MULTIPLICAND);
        } else {
            // Distinguish between dates with time and dates without.
            if (date.contains(":")) {
                return new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.GERMANY).parse(date);
            } else {
                return new SimpleDateFormat("yyyy-MM-dd", Locale.GERMANY).parse(date);
            }
        }
    }

    @ToJson
    long toJson(@Nullable final Date date) {
        return date != null ? date.getTime() : 0;
    }

    @Nullable
    private Long toLongOrNull(final String candidate) {
        try {
            return Long.parseLong(candidate);
        } catch (NumberFormatException ignored) {
            return null;
        }
    }
}
