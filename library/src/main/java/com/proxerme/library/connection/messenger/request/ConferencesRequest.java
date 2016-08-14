package com.proxerme.library.connection.messenger.request;

import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.afollestad.bridge.Form;
import com.afollestad.bridge.Response;
import com.proxerme.library.connection.ProxerRequest;
import com.proxerme.library.connection.messenger.result.ConferencesResult;
import com.proxerme.library.info.ProxerUrlHolder;
import com.proxerme.library.parameters.ConferenceTypeParameter.ConferenceType;

import static com.proxerme.library.info.ProxerTag.MESSENGER_CONFERENCES;

/**
 * Request for the conferences of the user. The user must be logged in for this API to return data.
 * This API uses pagination.
 *
 * @author Ruben Gees
 */

public class ConferencesRequest extends ProxerRequest<ConferencesResult> {

    private static final String CONFERENCES_URL = "/api/v1/messenger/conferences";

    private static final String PAGE_FORM = "p";
    private static final String TYPE_FORM = "type";

    private int page;
    private String type;

    /**
     * The constructor.
     *
     * @param page The page to load.
     */
    public ConferencesRequest(@IntRange(from = 0) int page) {
        this.page = page;
    }

    /**
     * The type to load.
     *
     * @param type The type.
     * @return This Request.
     */
    public ConferencesRequest withType(@Nullable @ConferenceType String type) {
        this.type = type;

        return this;
    }

    @Override
    protected int getTag() {
        return MESSENGER_CONFERENCES;
    }

    @Override
    protected ConferencesResult parse(@NonNull Response response) throws Exception {
        return response.asClass(ConferencesResult.class);
    }

    @NonNull
    @Override
    protected String getURL() {
        return ProxerUrlHolder.getHost() + CONFERENCES_URL;
    }

    @Override
    protected Form getBody() {
        Form form = new Form();

        form.add(PAGE_FORM, page);

        if (type != null) {
            form.add(TYPE_FORM, type);
        }

        return form;
    }
}
