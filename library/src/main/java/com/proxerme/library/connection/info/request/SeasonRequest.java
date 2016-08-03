package com.proxerme.library.connection.info.request;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.afollestad.bridge.Form;
import com.afollestad.bridge.Response;
import com.proxerme.library.connection.ProxerRequest;
import com.proxerme.library.connection.info.result.SeasonResult;
import com.proxerme.library.info.ProxerTag;
import com.proxerme.library.info.ProxerUrlHolder;

/**
 * Class that represents the request of a season request.
 *
 * @author Desnoo
 */
public class SeasonRequest extends ProxerRequest<SeasonResult> {

    private static final String ENTRY_SEASON_URL = "/api/v1/info/season";
    private static final String ENTRY_ID = "ENTRY_ID";

    private String id;

    /**
     * The Constructor of a request of a season for an entry (Anime, Manga)
     *
     * @param id The entry id to request.
     */
    public SeasonRequest(@NonNull String id) {
        this.id = id;
    }

    @Override
    protected int getTag() {
        return ProxerTag.INFO_ENTRY_SEASON;
    }

    @Override
    protected SeasonResult parse(@NonNull Response response) throws Exception {
        return response.asClass(SeasonResult.class);
    }

    @NonNull
    @Override
    protected String getURL() {
        return ProxerUrlHolder.getHost() + ENTRY_SEASON_URL;
    }

    @Nullable
    @Override
    protected Form getBody() {
        Form form = new Form();
        form.add(ENTRY_ID, this.id);
        return form;
    }
}
