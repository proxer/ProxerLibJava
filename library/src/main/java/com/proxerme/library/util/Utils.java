package com.proxerme.library.util;

import com.squareup.moshi.Json;
import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.NotNull;

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
}
