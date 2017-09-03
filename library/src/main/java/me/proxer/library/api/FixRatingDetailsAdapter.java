package me.proxer.library.api;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import me.proxer.library.entity.info.RatingDetails;

import javax.annotation.Nullable;
import java.io.IOException;

/**
 * @author Ruben Gees
 */
class FixRatingDetailsAdapter {

    private static final String INVALID_ARRAY = "[]";

    private final JsonAdapter<RatingDetails> internalAdapter;

    FixRatingDetailsAdapter() {
        internalAdapter = new Moshi.Builder().build().adapter(RatingDetails.class);
    }

    @FromJson
    @Nullable
    RatingDetails fromJson(final String json) throws IOException {
        if (json.isEmpty() || json.equals(INVALID_ARRAY)) {
            return new RatingDetails(0, 0, 0, 0, 0);
        } else {
            return internalAdapter.fromJson(json);
        }
    }
}
