package com.proxerme.library.connection.messenger.request;

import android.support.annotation.NonNull;
import android.util.Pair;

import com.proxerme.library.connection.ProxerResult;
import com.proxerme.library.connection.messenger.MessengerRequest;
import com.proxerme.library.connection.messenger.entity.ConferenceInfoContainer;
import com.proxerme.library.connection.messenger.result.ConferenceInfoResult;
import com.squareup.moshi.Moshi;

import java.io.IOException;
import java.util.Collections;

import okhttp3.ResponseBody;

/**
 * Request for information about a
 * {@link com.proxerme.library.connection.messenger.entity.Conference}.
 *
 * @author Desnoo
 */
public class ConferenceInfoRequest extends MessengerRequest<ConferenceInfoContainer> {

    private static final String ENDPOINT = "conferenceinfo";

    private static final String CONFERENCE_ID_PARAMETER = "conference_id";

    private String conferenceId;

    /**
     * The constructor of this request.
     *
     * @param conferenceId The id of the conference to request.
     */
    public ConferenceInfoRequest(@NonNull String conferenceId) {
        this.conferenceId = conferenceId;
    }

    @Override
    protected ProxerResult<ConferenceInfoContainer> parse(@NonNull Moshi moshi,
                                                          @NonNull ResponseBody body)
            throws IOException {
        return moshi.adapter(ConferenceInfoResult.class).lenient().fromJson(body.source());
    }

    @NonNull
    @Override
    protected String getApiEndpoint() {
        return ENDPOINT;
    }

    @NonNull
    @Override
    protected Iterable<Pair<String, ?>> getQueryParameters() {
        return Collections.<Pair<String, ?>>singletonList(
                new Pair<>(CONFERENCE_ID_PARAMETER, conferenceId)
        );
    }
}
