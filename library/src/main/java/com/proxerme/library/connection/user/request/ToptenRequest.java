package com.proxerme.library.connection.user.request;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.afollestad.bridge.Form;
import com.afollestad.bridge.Response;
import com.proxerme.library.connection.ProxerRequest;
import com.proxerme.library.connection.parameters.CategoryParameter.Category;
import com.proxerme.library.connection.user.result.ToptenResult;
import com.proxerme.library.info.ProxerTag;

/**
 * TODO: Describe class
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

    public ToptenRequest(@Nullable String userId, @Nullable String username) {
        this.userId = userId;
        this.username = username;
    }

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
        return TOPTEN_URL;
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
