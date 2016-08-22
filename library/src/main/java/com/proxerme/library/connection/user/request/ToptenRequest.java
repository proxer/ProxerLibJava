package com.proxerme.library.connection.user.request;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.proxerme.library.connection.ProxerRequest;
import com.proxerme.library.connection.ProxerResult;
import com.proxerme.library.connection.user.entitiy.ToptenEntry;
import com.proxerme.library.connection.user.result.ToptenResult;
import com.proxerme.library.parameters.CategoryParameter.Category;
import com.squareup.moshi.Moshi;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;

/**
 * Request for retrieving the Topten of the passed user. This API honors the visibility settings and
 * might return an error if the user does not allow access.
 *
 * @author Ruben Gees
 */
public class ToptenRequest extends ProxerRequest<ToptenEntry[]> {

    private static final String CLASS = "user";
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
    protected Iterable<String> getEndpointPathSegments() {
        return Arrays.asList(CLASS, ENDPOINT);
    }

    @NonNull
    @Override
    protected Map<String, String> getQueryParameters() {
        Map<String, String> result = new HashMap<>();

        if (userId != null) {
            result.put(USER_ID_PARAMETER, userId);
        }

        if (username != null) {
            result.put(USERNAME_PARAMETER, username);
        }

        if (category != null) {
            result.put(CATEGORY_PARAMETER, category);
        }

        return result;
    }
}