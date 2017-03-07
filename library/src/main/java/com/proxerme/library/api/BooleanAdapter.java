package com.proxerme.library.api;

import com.squareup.moshi.FromJson;

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

        return jsonString.equals("true") || jsonString.equals("1");
    }
}
