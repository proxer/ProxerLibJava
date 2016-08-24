package com.proxerme.library.connection.info.result;

import com.proxerme.library.connection.ProxerResult;
import com.proxerme.library.connection.info.entity.EntryDetails;
import com.proxerme.library.connection.info.request.EntryDetailsRequest;
import com.squareup.moshi.Json;

/**
 * Holds the result of the
 * {@link EntryDetailsRequest}.
 *
 * @author Desnoo
 */
public final class EntryDetailsResult extends ProxerResult<EntryDetails> {

    @Json(name = "data")
    private EntryDetails details;

    /**
     * Private constructor.
     */
    private EntryDetailsResult() {
    }

    @Override
    public EntryDetails getData() {
        return details;
    }
}
