package com.proxerme.library.connection.ucp.request;

import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.proxerme.library.connection.ProxerResult;
import com.proxerme.library.connection.ucp.UcpRequest;
import com.proxerme.library.connection.ucp.result.SetReminderResult;
import com.proxerme.library.parameters.CategoryParameter.Category;
import com.proxerme.library.parameters.LanguageParameter.Language;
import com.squareup.moshi.Moshi;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * Sets a reminder for the passed entry.
 *
 * @author Ruben Gees
 */
public class SetReminderRequest extends UcpRequest<Void> {

    private static final String ENDPOINT = "setreminder";

    private static final String ID_PARAMETER = "id";
    private static final String EPISODE_PARAMETER = "episode";
    private static final String LANGUAGE_PARAMETER = "language";
    private static final String CATEGORY_PARAMETER = "kat";

    private String id;
    private int episode;
    private String language;
    private String category;

    /**
     * The constructor.
     *
     * @param id       The id.
     * @param episode  The episode.
     * @param language The language.
     * @param category The category.
     */
    public SetReminderRequest(@NonNull String id, @IntRange(from = 1) int episode,
                              @NonNull @Language String language,
                              @NonNull @Category String category) {
        this.id = id;
        this.episode = episode;
        this.language = language;
        this.category = category;
    }

    @Override
    protected ProxerResult<Void> parse(@NonNull Moshi moshi, @NonNull ResponseBody body)
            throws IOException {
        return moshi.adapter(SetReminderResult.class).lenient().fromJson(body.source());
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
                .add(EPISODE_PARAMETER, String.valueOf(episode))
                .add(LANGUAGE_PARAMETER, language)
                .add(CATEGORY_PARAMETER, category)
                .build();
    }
}
