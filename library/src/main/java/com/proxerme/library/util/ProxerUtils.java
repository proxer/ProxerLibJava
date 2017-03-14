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
public class ProxerUtils {

    /**
     * Returns the name of the passed enum instance (Using that specified in the {@code @Json} annotation).
     */
    @NotNull
    public String getApiEnumName(@NotNull final Enum<?> it) throws NoSuchFieldException {
        return it.getClass().getField(it.name()).getAnnotation(Json.class).name();
    }

    /**
     * Joins an Iterator with a given delimiter to a {@link String} and returns the result.
     * <p>
     * The Iterator can have any type, toString is called on the individual items.
     */
    @NotNull
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
