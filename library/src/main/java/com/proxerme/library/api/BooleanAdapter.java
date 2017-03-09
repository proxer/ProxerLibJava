package com.proxerme.library.api;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.JsonDataException;

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
            if (jsonString.equals("true")) {
                return true;
            } else if (jsonString.equals("false")) {
                return false;
            }
        }

        throw new JsonDataException("Unable to map " + json + " to a boolean value");
    }

    private Integer toIntOrNull(String candidate) {
        try {
            return (int) Float.parseFloat(candidate);
        } catch (NumberFormatException ignored) {
            return null;
        }
    }
}
