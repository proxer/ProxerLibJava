package me.proxer.library.api;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.JsonDataException;
import com.squareup.moshi.ToJson;

/**
 * @author Ruben Gees
 */
final class VoidAdapter {

    private static final String JSON_NULL = "null";

    @FromJson
    Void fromJson(final String json) {
        if (json.equals(JSON_NULL)) {
            return null;
        } else {
            throw new JsonDataException("Void field can only host null values. Actual: " + json);
        }
    }

    @ToJson
    String toJson(@SuppressWarnings("unused") final Void nothingness) {
        return JSON_NULL;
    }
}
