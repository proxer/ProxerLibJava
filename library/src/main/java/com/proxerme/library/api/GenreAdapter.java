package com.proxerme.library.api;

import com.proxerme.library.enums.Genre;
import com.squareup.moshi.FromJson;
import com.squareup.moshi.Json;
import com.squareup.moshi.ToJson;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * TODO: Describe class
 *
 * @author Ruben Gees
 */
class GenreAdapter {

    private static final String DELIMITER = " ";

    @FromJson
    Set<Genre> fromJson(final String json) throws IOException, NoSuchFieldException {
        if (json.isEmpty()) {
            return Collections.emptySet();
        }

        final String[] parts = json.split(DELIMITER);
        final Set<Genre> result = new HashSet<>(parts.length);

        for (final String part : parts) {
            for (final Genre genre : Genre.values()) {
                if (getName(genre).equals(part)) {
                    result.add(genre);

                    break;
                }
            }
        }

        return result;
    }

    @ToJson
    String toJson(final Set<Genre> genres) throws NoSuchFieldException {
        String result = "";

        for (final Genre genre : genres) {
            result += getName(genre);
            result += DELIMITER;
        }

        if (!result.isEmpty()) {
            result = result.substring(0, result.length() - DELIMITER.length());
        }

        return result;
    }

    private String getName(Genre genre) throws NoSuchFieldException {
        return Genre.class.getField(genre.name()).getAnnotation(Json.class).name();
    }
}
