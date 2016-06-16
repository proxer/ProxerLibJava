package com.proxerme.library.connection.experimental.messages.request;

import android.support.annotation.NonNull;

import com.afollestad.bridge.Bridge;
import com.afollestad.bridge.BridgeException;
import com.afollestad.bridge.Form;
import com.afollestad.bridge.RequestBuilder;
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

    @NonNull
    @Override
    protected RequestBuilder beginRequest() {
        Form messageParameters = new Form().add("message", message);

        return Bridge.post(ProxerUrlHolder.getHost() + SEND_MESSAGE_URL, conferenceId)
                .body(messageParameters);
    }

    @Override
    protected SendMessageResult parse(Response response) throws BridgeException {
        return new SendMessageResult(conferenceId);
    }

    @Override
    protected int getTag() {
        return ProxerTag.SEND_MESSAGE;
    }

    @Override
    protected SendMessageErrorResult createErrorResult(@NonNull ProxerException exception) {
        return new SendMessageErrorResult(conferenceId, exception);
    }

}
