package com.proxerme.library.connection.experimental.messages.request;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.afollestad.bridge.BridgeException;
import com.afollestad.bridge.Form;
import com.afollestad.bridge.Response;
import com.proxerme.library.connection.ProxerException;
import com.proxerme.library.connection.ProxerRequest;
import com.proxerme.library.connection.experimental.messages.result.SendMessageErrorResult;
import com.proxerme.library.connection.experimental.messages.result.SendMessageResult;
import com.proxerme.library.info.ProxerTag;
import com.proxerme.library.info.ProxerUrlHolder;

/**
 * TODO: Describe class
 *
 * @author Ruben Gees
 */

public class SendMessageRequest extends ProxerRequest<SendMessageResult, SendMessageErrorResult> {

    private static final String SEND_MESSAGE_URL = "/messages/?format=json&json=answer&id=%s";

    private String conferenceId;
    private String message;

    public SendMessageRequest(@NonNull String conferenceId, @NonNull String message) {
        this.conferenceId = conferenceId;
        this.message = message;
    }

    @Override
    protected SendMessageResult parse(Response response) throws BridgeException {
        return new SendMessageResult(conferenceId);
    }

    @Override
    protected SendMessageErrorResult createErrorResult(@NonNull ProxerException exception) {
        return new SendMessageErrorResult(conferenceId, exception);
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
    protected void appendToBody(@NonNull Form form) {
        form.add("message", message);
    }
}
