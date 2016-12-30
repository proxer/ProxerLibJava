package com.proxerme.library.connection.ucp.result;

import com.proxerme.library.connection.ProxerResult;
import com.proxerme.library.connection.ucp.entitiy.UcpToptenEntry;
import com.squareup.moshi.Json;

/**
 * {@inheritDoc}
 */
public class UcpToptenResult extends ProxerResult<UcpToptenEntry[]> {

    @Json(name = "data")
    private UcpToptenEntry[] data;

    protected UcpToptenResult() {

    }

    @Override
    public UcpToptenEntry[] getData() {
        return data;
    }
}
