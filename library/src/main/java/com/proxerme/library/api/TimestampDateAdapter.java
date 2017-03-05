package com.proxerme.library.api;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.ToJson;

import java.util.Date;

final class TimestampDateAdapter {

    @FromJson
    Date fromJson(final long timestamp) {
        // The API returns seconds
        return new Date(timestamp * 1000);
    }

    @ToJson
    String toJson(final Date date) {
        return String.valueOf(date.getTime());
    }
}
