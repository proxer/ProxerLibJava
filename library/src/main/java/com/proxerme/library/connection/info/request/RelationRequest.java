package com.proxerme.library.connection.info.request;

import android.support.annotation.NonNull;
import android.util.Pair;

import com.proxerme.library.connection.ProxerResult;
import com.proxerme.library.connection.info.InfoRequest;
import com.proxerme.library.connection.info.entity.Relation;
import com.proxerme.library.connection.info.result.RelationResult;
import com.squareup.moshi.Moshi;

import java.io.IOException;
import java.util.Collections;

import okhttp3.ResponseBody;

/**
 * The request to get the relations of an entry.
 *
 * @author Desnoo
 */
public class RelationRequest extends InfoRequest<Relation[]> {

    private static final String ENDPOINT = "relations";
    private static final String ID_PARAMETER = "id";

    private String id;

    /**
     * The constructor of a request for relations of an entry.
     *
     * @param id The id of the entry.
     */
    public RelationRequest(@NonNull String id) {
        this.id = id;
    }

    @Override
    protected ProxerResult<Relation[]> parse(@NonNull Moshi moshi, @NonNull ResponseBody body) throws IOException {
        return moshi.adapter(RelationResult.class).fromJson(body.source());
    }


    @NonNull
    @Override
    protected String getApiEndpoint() {
        return ENDPOINT;
    }

    @NonNull
    @Override
    protected Iterable<Pair<String, ?>> getQueryParameters() {
        return Collections.<Pair<String, ?>>singletonList(
                new Pair<>(ID_PARAMETER, id)
        );
    }
}
