package com.proxerme.library.connection.user.request;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Pair;

import com.proxerme.library.connection.ProxerResult;
import com.proxerme.library.connection.user.UserRequest;
import com.proxerme.library.connection.user.entitiy.ToptenEntry;
import com.proxerme.library.connection.user.result.ToptenResult;
import com.proxerme.library.parameters.CategoryParameter.Category;
import com.squareup.moshi.Moshi;

import java.io.IOException;
import java.util.Arrays;

import okhttp3.ResponseBody;

/**
 * Request for retrieving the Topten of the passed user. This API honors the visibility settings and
 * might return an error if the user does not allow access.
 *
 * @author Ruben Gees
 */
public class ToptenRequest extends UserRequest<ToptenEntry[]> {

    private static final String ENDPOINT = "topten";

    private static final String USER_ID_PARAMETER = "uid";
    private static final String USERNAME_PARAMETER = "username";
    private static final String CATEGORY_PARAMETER = "kat";

    @Nullable
    private String userId;
    @Nullable
    private String username;
    @Nullable
    private String category;

    /**
     * One of the available constructors. You can choose if you want to pass the id or the name of
     * the user, but must pass at least one. If you pass both, the username is discarded by the API.
     *
     * @param userId   The id of the user.
     * @param username The name of the user.
     */
    public ToptenRequest(@Nullable String userId, @Nullable String username) {
        this.userId = userId;
        this.username = username;
    }

    /**
     * One of the available constructors. You can choose if you want to pass the id or the name of
     * the user, but must pass at least one. If you pass both, the username is discarded by the API.
     *
     * @param userId   The id of the user.
     * @param username The name of the user.
     * @param category The category to load.
     *                 {@link com.proxerme.library.parameters.CategoryParameter#ANIME} is default.
     */
    public ToptenRequest(@Nullable String userId, @Nullable String username,
                         @Nullable @Category String category) {
        this.userId = userId;
        this.username = username;
        this.category = category;
    }

    @Override
    protected ProxerResult<ToptenEntry[]> parse(@NonNull Moshi moshi, @NonNull ResponseBody body)
            throws IOException {
        return moshi.adapter(ToptenResult.class).fromJson(body.source());
    }

    @NonNull
    @Override
    protected String getApiEndpoint() {
        return ENDPOINT;
    }

    @NonNull
    @Override
    protected Iterable<Pair<String, ?>> getQueryParameters() {
        return Arrays.<Pair<String, ?>>asList(
                new Pair<>(USER_ID_PARAMETER, userId),
                new Pair<>(USERNAME_PARAMETER, username),
                new Pair<>(CATEGORY_PARAMETER, category)
        );
    }
}