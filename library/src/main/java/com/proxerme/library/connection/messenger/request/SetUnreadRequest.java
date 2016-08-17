package com.proxerme.library.connection.messenger.request;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.afollestad.bridge.Form;
import com.afollestad.bridge.Response;
import com.proxerme.library.connection.ProxerRequest;
import com.proxerme.library.connection.messenger.result.VoidResult;
import com.proxerme.library.info.ProxerTag;
import com.proxerme.library.info.ProxerUrlHolder;

/**
 * The class that represents the SetUnreadRequest. Use this to mark a conference as not read.
 *
 * @author Desnoo
 */
public class SetUnreadRequest extends ProxerRequest<VoidResult> {

    private static final String SET_UNREAD_URL = "/api/v1/messenger/setunread";
    private static final String CONFERENCE_ID = "conference_id";

    private String conferenceId;

    /**
     * The constructor.
     *
     * @param conferenceId The conference id of the conference to mark as unread.
     */
    public SetUnreadRequest(@NonNull String conferenceId) {
        this.conferenceId = conferenceId;
    }

    @Override
    protected int getTag() {
        return ProxerTag.MESSENGER_SET_UNREAD;
    }

    @Override
    protected VoidResult parse(@NonNull Response response) throws Exception {
        return new VoidResult();
    }

    @NonNull
    @Override
    protected String getURL() {
        return ProxerUrlHolder.getHost() + SET_UNREAD_URL;
    }

    @Nullable
    @Override
    protected Form getBody() {
        return new Form()
                .add(CONFERENCE_ID, conferenceId);
    }
}
