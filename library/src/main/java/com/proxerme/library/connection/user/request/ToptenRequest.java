package com.proxerme.library.connection.user.request;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.afollestad.bridge.Form;
import com.afollestad.bridge.Response;
import com.proxerme.library.connection.ProxerRequest;
import com.proxerme.library.connection.parameters.CategoryParameter.Category;
import com.proxerme.library.connection.user.result.ToptenResult;
import com.proxerme.library.info.ProxerTag;
import com.proxerme.library.info.ProxerUrlHolder;

/**
 * Request for retrieving the Topten of the passed user. This API honors the visibility settings and
 * might return an error if the user does not allow access.
 *
 * @author Ruben Gees
 */

public class ToptenRequest extends ProxerRequest<ToptenResult> {

    private static final String TOPTEN_URL = "/api/v1/user/topten";

    private static final String USERID_FORM = "uid";
    private static final String USERNAME_FORM = "username";
    private static final String CATEGORY_FORM = "kat";

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
     * @param category One of the categories available in the
     *                 {@link com.proxerme.library.connection.parameters.CategoryParameter} class.
     */
    public ToptenRequest(@Nullable String userId, @Nullable String username,
                         @Nullable @Category String category) {
        this.userId = userId;
        this.username = username;
        this.category = category;
    }

    @Override
    protected int getTag() {
        return ProxerTag.TOPTEN;
    }

    @Override
    protected ToptenResult parse(Response response) throws Exception {
        return response.asClass(ToptenResult.class);
    }

    @NonNull
    @Override
    protected String getURL() {
        return ProxerUrlHolder.getHost() + TOPTEN_URL;
    }

    @Override
    protected void appendToBody(@NonNull Form form) {
        if (userId != null) {
            form.add(USERID_FORM, userId);
        }

        if (username != null) {
            form.add(USERNAME_FORM, username);
        }

        if (category != null) {
            form.add(CATEGORY_FORM, category);
        }
    }
}
