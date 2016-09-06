package com.proxerme.library.connection.ucp.result;

import com.proxerme.library.connection.ProxerResult;
import com.proxerme.library.connection.ucp.entitiy.UcpToptenEntity;
import com.squareup.moshi.Json;

/**
 * {@inheritDoc}
 */
public class UcpToptenResult extends ProxerResult<UcpToptenEntity[]> {

    @Json(name = "data")
    private UcpToptenEntity[] data;

    protected UcpToptenResult() {

    }

    @Override
    public UcpToptenEntity[] getData() {
        return data;
    }
}
