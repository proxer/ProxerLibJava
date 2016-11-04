package com.proxerme.library.connection.manga.result;

import com.proxerme.library.connection.ProxerResult;
import com.proxerme.library.connection.manga.entity.Chapter;
import com.squareup.moshi.Json;

/**
 * {@inheritDoc}
 */
public final class ChapterResult extends ProxerResult<Chapter> {

    @Json(name = "data")
    private Chapter chapter;

    protected ChapterResult() {
    }

    @Override
    public Chapter getData() {
        return chapter;
    }
}
