package com.proxerme.library.connection.info.result;

import com.proxerme.library.connection.ProxerResult;
import com.proxerme.library.connection.info.entity.ListInfo;
import com.squareup.moshi.Json;

/**
 * {@inheritDoc}
 */
public final class ListInfoResult extends ProxerResult<ListInfo> {

    @Json(name = "data")
    private ListInfo listInfo;

    protected ListInfoResult() {
    }

    @Override
    public ListInfo getData() {
        return listInfo;
    }
}
