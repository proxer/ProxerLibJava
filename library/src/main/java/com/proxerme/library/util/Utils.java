package com.proxerme.library.util;

import com.squareup.moshi.Json;
import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

/**
 * Utility class for internal use.
 *
 * @author Ruben Gees
 */
@UtilityClass
public class Utils {

    /**
     * Returns the name of the passed enum instance (Using that specified in the @Json annotation).
     */
    public String getEnumName(@NotNull final Enum<?> it) throws NoSuchFieldException {
        return it.getClass().getField(it.name()).getAnnotation(Json.class).name();
    }

    /**
     * Joins a {@link String} Array with a given delimiter to a {@link String}.
     * Returns the joined String.
     */
    public String join(final String delimiter, final String... array) {
        if (delimiter == null || array == null) {
            return "";
        } else if (array.length == 1) {
            return array[0];
        } else {
            StringBuilder builder = new StringBuilder();
            builder.append(array[0]);
            for (String entry : array) {
                builder.append(delimiter).append(entry);
            }
            return builder.toString();
        }
    }

    /**
     * Joins a {@link String} Iterator with a given delimiter to a {@link String}.
     * Returns the joined String.
     */
    public String join(final String delimiter, final Iterable<String> iterable) {
        if (delimiter == null || iterable == null) {
            return "";
        } else {
            Iterator<String> iterator = iterable.iterator();
            if (!iterator.hasNext()) {
                return "";
            }
            StringBuilder builder = new StringBuilder();
            builder.append(iterator.next());
            while (iterator.hasNext()) {
                builder.append(delimiter).append(iterator.next());
            }
            return builder.toString();
        }
    }
}
