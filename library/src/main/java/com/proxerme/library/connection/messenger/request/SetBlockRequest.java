package com.proxerme.library.connection.messenger.request;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.afollestad.bridge.Form;
import com.afollestad.bridge.Response;
import com.proxerme.library.connection.ProxerRequest;
import com.proxerme.library.connection.messenger.result.SetActionResult;
import com.proxerme.library.info.ProxerTag;
import com.proxerme.library.info.ProxerUrlHolder;

/**
 * The class that represents the SetBlockRequest. Use this to block a conference.
 *
 * @author Desnoo
 */
public class SetBlockRequest extends ProxerRequest<SetActionResult> {

    private static final String SET_BLOCK_URL = "/api/v1/messenger/setblock";
    private static final String CONFERENCE_ID = "conference_id";

    private String conferenceId;


    /**
     * Package constructor.
     */
    SetBlockRequest() {
    }

    /**
     * The constructor.
     *
     * @param conferenceId The conference id of the conference to block.
     */
    public SetBlockRequest(@NonNull String conferenceId) {
        this.conferenceId = conferenceId;
    }

    /**
     * Returns the id of the conference.
     *
     * @return The conference id.
     */
    @NonNull
    public String getConferenceId() {
        return conferenceId;
    }

    @Override
    protected int getTag() {
        return ProxerTag.MESSENGER_SET_BLOCK;
    }

    @Override
    protected SetActionResult parse(@NonNull Response response) throws Exception {
        return response.asClass(SetActionResult.class);
    }

    @NonNull
    @Override
    protected String getURL() {
        return ProxerUrlHolder.getHost() + SET_BLOCK_URL;
    }

    @Nullable
    @Override
    protected Form getBody() {
        return new Form()
                .add(CONFERENCE_ID, conferenceId);
    }
}
