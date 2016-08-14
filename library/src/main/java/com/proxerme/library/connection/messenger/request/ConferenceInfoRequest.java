package com.proxerme.library.connection.messenger.request;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.afollestad.bridge.Form;
import com.afollestad.bridge.Response;
import com.proxerme.library.connection.ProxerRequest;
import com.proxerme.library.connection.messenger.result.ConferenceInfoResult;
import com.proxerme.library.info.ProxerTag;
import com.proxerme.library.info.ProxerUrlHolder;

/**
 * The class that represents the request to get the conference info.
 *
 * @author Desnoo
 */
public class ConferenceInfoRequest extends ProxerRequest<ConferenceInfoResult> {

    private static final String CONFERENCE_INFO_URL = "/api/v1/messenger/conferenceinfo";

    private static final String CONFERENCE_ID = "conference_id";

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
    protected int getTag() {
        return ProxerTag.MESSENGER_CONFERENCE_INFO;
    }

    @Override
    protected ConferenceInfoResult parse(@NonNull Response response) throws Exception {
        return response.asClass(ConferenceInfoResult.class);
    }

    @NonNull
    @Override
    protected String getURL() {
        return ProxerUrlHolder.getHost() + CONFERENCE_INFO_URL;
    }

    @Nullable
    @Override
    protected Form getBody() {
        return new Form()
                .add(CONFERENCE_ID, conferenceId);
    }
}
