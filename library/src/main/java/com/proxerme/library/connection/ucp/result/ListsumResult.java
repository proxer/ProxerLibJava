package com.proxerme.library.connection.ucp.result;

import com.proxerme.library.connection.ProxerResult;
import com.squareup.moshi.Json;

/**
 * {@inheritDoc}
 */
public class ListsumResult extends ProxerResult<Integer> {

    @Json(name = "data")
    private Integer sum;

    protected ListsumResult(Integer sum) {
        this.sum = sum;
    }

    @Override
    public Integer getData() {
        return sum;
    }
}
