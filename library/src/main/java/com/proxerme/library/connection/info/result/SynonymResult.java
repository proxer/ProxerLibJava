package com.proxerme.library.connection.info.result;

import com.proxerme.library.connection.ProxerResult;
import com.proxerme.library.connection.info.entity.Synonym;
import com.squareup.moshi.Json;

/**
 * {@inheritDoc}
 */
public final class SynonymResult extends ProxerResult<Synonym[]> {

    @Json(name = "data")
    private Synonym[] synonyms;

    /**
     * Private constructor.
     */
    private SynonymResult() {
    }

    @Override
    public Synonym[] getData() {
        return synonyms;
    }
}
