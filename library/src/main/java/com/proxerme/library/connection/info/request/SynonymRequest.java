package com.proxerme.library.connection.info.request;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.afollestad.bridge.Form;
import com.afollestad.bridge.Response;
import com.proxerme.library.connection.ProxerRequest;
import com.proxerme.library.connection.info.result.SynonymResult;
import com.proxerme.library.info.ProxerTag;
import com.proxerme.library.info.ProxerUrlHolder;

/**
 * Class to create a object to request the Synonyms of a specified entry.
 *
 * @author Desnoo
 */
public class SynonymRequest extends ProxerRequest<SynonymResult> {

    private static final String ENTRY_NAMES_URL = "/api/v1/info/names";

    private static final String ENTRY_NAMES_ID = "id";

    private String id;

    /**
     * Create a request object for the alternative names of an entry by its id.
     *
     * @param id the id of the entry (Anime/Manga) to request the name for.
     */
    public SynonymRequest(@NonNull String id) {
        this.id = id;
    }

    @Override
    protected int getTag() {
        return ProxerTag.INFO_ENTRY_SYNONYM;
    }

    @Override
    protected SynonymResult parse(@NonNull Response response) throws Exception {
        return response.asClass(SynonymResult.class);
    }

    @NonNull
    @Override
    protected String getURL() {
        return ProxerUrlHolder.getHost() + ENTRY_NAMES_URL;
    }

    @Nullable
    @Override
    protected Form getBody() {
        Form form = new Form();
        form.add(ENTRY_NAMES_ID, this.id);
        return form;
    }
}
