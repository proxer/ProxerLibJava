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
     * Joins a {@link String} Iterator with a given delimiter to a {@link String}.
     * Returns the joined String.
     */
    public String join(@NotNull final String delimiter, @NotNull final Iterable<?> iterable) {
        final Iterator<?> iterator = iterable.iterator();

        if (!iterator.hasNext()) {
            return "";
        }

        final StringBuilder builder = new StringBuilder().append(iterator.next());

        while (iterator.hasNext()) {
            builder.append(delimiter).append(iterator.next());
        }

        return builder.toString();
    }
}
