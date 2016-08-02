package com.proxerme.library.connection.info.request;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.afollestad.bridge.Form;
import com.afollestad.bridge.Response;
import com.proxerme.library.connection.ProxerRequest;
import com.proxerme.library.connection.info.result.EntryCoreResult;
import com.proxerme.library.info.ProxerTag;
import com.proxerme.library.info.ProxerUrlHolder;

/**
 * Request object for the core data of an entry by an id (Anime or Manga).
 *
 * @author Desnoo
 */
public class EntryCoreRequest extends ProxerRequest<EntryCoreResult> {

    private static final String ENTRY_CORE_URL = "/api/v1/info/entry";

    private static final String ENTRY_CORE_ID = "id";

    private String id;

    /**
     * Create a request object of an specific entry
     *
     * @param id the id of the entry to request.
     */
    public EntryCoreRequest(@NonNull String id) {
        this.id = id;
    }

    @Override
    protected int getTag() {
        return ProxerTag.INFO_ENTRY_CORE;
    }

    @Override
    protected EntryCoreResult parse(@NonNull Response response) throws Exception {
        return response.asClass(EntryCoreResult.class);
    }

    @NonNull
    @Override
    protected String getURL() {
        return ProxerUrlHolder.getHost() + ENTRY_CORE_URL;
    }

    @Nullable
    @Override
    protected Form getBody() {
        Form form = new Form();
        form.add(ENTRY_CORE_ID, this.id);
        return form;
    }
}
