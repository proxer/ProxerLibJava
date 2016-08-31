package com.proxerme.library.connection.info.result;

import com.proxerme.library.connection.ProxerResult;
import com.proxerme.library.connection.info.entity.Relation;
import com.squareup.moshi.Json;

/**
 * The result of the {@link com.proxerme.library.connection.info.request.RelationRequest}.
 *
 * @author Desnoo
 */
public final class RelationResult extends ProxerResult<Relation[]> {

    @Json(name = "data")
    private Relation[] relations;

    protected RelationResult() {
    }

    @Override
    public Relation[] getData() {
        return relations;
    }
}
