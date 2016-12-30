package com.proxerme.library.connection.ucp.request;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.proxerme.library.connection.EmptyResult;
import com.proxerme.library.connection.ProxerResult;
import com.proxerme.library.connection.ucp.UcpRequest;
import com.squareup.moshi.Moshi;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * Deletes the {@link com.proxerme.library.connection.ucp.entitiy.UcpToptenEntity} (specified
 * through the id in the constructor) from the list of the user. The user needs to be logged in for
 * this API.
 *
 * @author Ruben Gees
 */
public class DeleteFavoriteRequest extends UcpRequest<Void> {

    private static final String ENDPOINT = "deletetopten";

    private static final String ID_PARAMETER = "id";

    private String id;

    /**
     * The constructor.
     *
     * @param id The id of the reminder to delete.
     */
    public DeleteFavoriteRequest(@NonNull String id) {
        this.id = id;
    }

    @Override
    protected ProxerResult<Void> parse(@NonNull Moshi moshi, @NonNull ResponseBody body)
            throws IOException {
        return moshi.adapter(EmptyResult.class).fromJson(body.source());
    }

    @NonNull
    @Override
    protected String getApiEndpoint() {
        return ENDPOINT;
    }

    @Override
    protected String getMethod() {
        return POST;
    }

    @Nullable
    @Override
    protected RequestBody getRequestBody() {
        return new FormBody.Builder()
                .add(ID_PARAMETER, id)
                .build();
    }
}
