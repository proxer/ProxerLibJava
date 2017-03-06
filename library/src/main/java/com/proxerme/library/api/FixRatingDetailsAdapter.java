package com.proxerme.library.api;

import com.proxerme.library.entitiy.info.RatingDetails;
import com.squareup.moshi.FromJson;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.ToJson;

import java.io.IOException;

/**
 * TODO: Describe class
 *
 * @author Ruben Gees
 */
class FixRatingDetailsAdapter {

    private static final String INVALID_ARRAY = "[]";

    private final JsonAdapter<RatingDetails> internalAdapter;

    FixRatingDetailsAdapter() {
        internalAdapter = new Moshi.Builder().build().adapter(RatingDetails.class);
    }

    @FromJson
    RatingDetails fromJson(final String json) throws IOException {
        if (json.equals(INVALID_ARRAY)) {
            return new RatingDetails(0, 0, 0, 0, 0);
        } else {
            return internalAdapter.fromJson(json);
        }
    }

    @ToJson
    String toJson(final RatingDetails ratingDetails) {
        return internalAdapter.toJson(ratingDetails);
    }
}
