package com.proxerme.library.api;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.JsonDataException;

import java.text.ParseException;

/**
 * TODO: Describe class
 *
 * @author Ruben Gees
 */
class BooleanAdapter {

    @FromJson
    boolean fromJson(final Object json) throws ParseException {
        final String jsonString = json.toString();

        switch (jsonString) {
            case "true":
            case "1":
                return true;
            case "false":
            case "0":
                return false;
            default:
                throw new JsonDataException("Unable to map " + jsonString + " to a boolean value");
        }
    }
}
