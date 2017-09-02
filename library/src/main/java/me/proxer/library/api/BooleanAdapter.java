package me.proxer.library.api;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.JsonDataException;

import javax.annotation.Nullable;

/**
 * @author Ruben Gees
 */
class BooleanAdapter {

    @FromJson
    boolean fromJson(final Object json) {
        final String jsonString = json.toString();
        final Integer jsonInteger = toIntOrNull(jsonString);

        if (jsonInteger != null) {
            if (jsonInteger == 1) {
                return true;
            } else if (jsonInteger == 0) {
                return false;
            }
        } else {
            if ("true".equals(jsonString)) {
                return true;
            } else if ("false".equals(jsonString)) {
                return false;
            }
        }

        throw new JsonDataException("Unable to map " + json + " to a boolean value");
    }

    @Nullable
    private Integer toIntOrNull(String candidate) {
        try {
            return (int) Float.parseFloat(candidate);
        } catch (NumberFormatException ignored) {
            return null;
        }
    }
}
