package com.proxerme.library.connection.experimental.chat.request;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.afollestad.bridge.Form;
import com.afollestad.bridge.Response;
import com.proxerme.library.connection.ProxerRequest;
import com.proxerme.library.connection.experimental.chat.result.SendMessageResult;
import com.proxerme.library.info.ProxerTag;
import com.proxerme.library.info.ProxerUrlHolder;

/**
 * A Request for sending a single message to a specified conference. This requires the User to be
 * logged in.
 *
 * @author Ruben Gees
 */

public class SendMessageRequest extends ProxerRequest<SendMessageResult> {

    private static final String SEND_MESSAGE_URL = "/messages/?format=json&json=answer&id=%s";

    private String conferenceId;
    private String message;

    /**
     * The constructor.
     *
     * @param conferenceId The id of the conference to send the message to.
     * @param message      The message.
     */
    public SendMessageRequest(@NonNull String conferenceId, @NonNull String message) {
        this.conferenceId = conferenceId;
        this.message = message;
    }

    @Override
    protected SendMessageResult parse(@NonNull Response response) throws Exception {
        return new SendMessageResult();
    }

    @Override
    protected int getTag() {
        return ProxerTag.SEND_MESSAGE;
    }

    @NonNull
    @Override
    protected String getURL() {
        return ProxerUrlHolder.getHost() + SEND_MESSAGE_URL;
    }

    @Nullable
    @Override
    protected String[] getParameters() {
        return new String[]{conferenceId};
    }

    @Override
    protected Form getBody() {
        return new Form().add("message", message);
    }
}
