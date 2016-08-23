package com.proxerme.library.connection.messenger.result;

import com.proxerme.library.connection.ProxerResult;
import com.proxerme.library.connection.messenger.entity.Constants;
import com.squareup.moshi.Json;

public class ConstantsResult extends ProxerResult<Constants> {

    @Json(name = "data")
    private Constants constants;

    private ConstantsResult() {
    }

    @Override
    public Constants getData() {
        return constants;
    }
}
