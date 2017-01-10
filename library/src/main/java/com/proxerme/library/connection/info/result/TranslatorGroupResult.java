package com.proxerme.library.connection.info.result;

import com.proxerme.library.connection.ProxerResult;
import com.proxerme.library.connection.info.entity.TranslatorGroup;
import com.squareup.moshi.Json;

/**
 * {@inheritDoc}
 */
public final class TranslatorGroupResult extends ProxerResult<TranslatorGroup> {

    @Json(name = "data")
    private TranslatorGroup translatorGroup;

    protected TranslatorGroupResult() {
    }

    @Override
    public TranslatorGroup getData() {
        return translatorGroup;
    }
}
