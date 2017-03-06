package com.proxerme.library.api;

import com.proxerme.library.enums.FskConstraint;
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
class FskConstraintAdapter {

    private static final String DELIMITER = " ";

    @FromJson
    Set<FskConstraint> fromJson(final String json) throws IOException, NoSuchFieldException {
        if (json.isEmpty()) {
            return Collections.emptySet();
        }

        final String[] parts = json.split(DELIMITER);
        final Set<FskConstraint> result = new HashSet<>(parts.length);

        for (final String part : parts) {
            for (final FskConstraint fskConstraint : FskConstraint.values()) {
                if (getName(fskConstraint).equals(part)) {
                    result.add(fskConstraint);

                    break;
                }
            }
        }

        return result;
    }

    @ToJson
    String toJson(final Set<FskConstraint> fskConstraints) throws NoSuchFieldException {
        String result = "";

        for (final FskConstraint fskConstraint : fskConstraints) {
            result += getName(fskConstraint);
            result += DELIMITER;
        }

        if (!result.isEmpty()) {
            result = result.substring(0, result.length() - DELIMITER.length());
        }

        return result;
    }

    private String getName(FskConstraint fskConstraint) throws NoSuchFieldException {
        return FskConstraint.class.getField(fskConstraint.name()).getAnnotation(Json.class).name();
    }
}
