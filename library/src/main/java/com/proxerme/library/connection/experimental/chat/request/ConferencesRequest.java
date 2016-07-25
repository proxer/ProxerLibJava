package com.proxerme.library.connection.experimental.chat.request;

import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.afollestad.bridge.Response;
import com.proxerme.library.connection.ProxerRequest;
import com.proxerme.library.connection.experimental.chat.result.ConferencesResult;
import com.proxerme.library.info.ProxerTag;
import com.proxerme.library.info.ProxerUrlHolder;

/**
 * Request for retrieving the conferences the User is part of. The Request requires the User to be
 * logged in. This API uses pagination.
 *
 * @author Ruben Gees
 */

public class ConferencesRequest extends ProxerRequest<ConferencesResult> {

    private static final String CONFERENCES_URL = "/messages?format=json&json=conferences&p=%s";

    private int page;

    /**
     * The constructor.
     *
     * @param page The page to load from. The first page is 1(!). If a page greater than the amount
     *             of pages is passed, an empty result is returned.
     */
    public ConferencesRequest(@IntRange(from = 1) int page) {
        this.page = page;
    }

    @Override
    protected ConferencesResult parse(Response response) throws Exception {
        return response.asClass(ConferencesResult.class);
    }

    @Override
    protected int getTag() {
        return ProxerTag.CONFERENCES;
    }

    @NonNull
    @Override
    protected String getURL() {
        return ProxerUrlHolder.getHost() + CONFERENCES_URL;
    }

    @Nullable
    @Override
    protected String[] getParameters() {
        return new String[]{String.valueOf(page)};
    }
}
