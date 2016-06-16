package com.proxerme.library.connection.experimental.messages.request;

import android.support.annotation.IntRange;
import android.support.annotation.NonNull;

import com.afollestad.bridge.Bridge;
import com.afollestad.bridge.BridgeException;
import com.afollestad.bridge.RequestBuilder;
import com.afollestad.bridge.Response;
import com.proxerme.library.connection.ProxerException;
import com.proxerme.library.connection.ProxerRequest;
import com.proxerme.library.connection.experimental.messages.result.MessagesErrorResult;
import com.proxerme.library.connection.experimental.messages.result.MessagesResult;
import com.proxerme.library.info.ProxerTag;
import com.proxerme.library.interfaces.ProxerResult;

/**
 * TODO: Describe class
 *
 * @author Ruben Gees
 */

public class MessagesRequest extends ProxerRequest {

    private static final String MESSAGES_URL = "/messages?format=json&json=messages&id=%s&p=%s";

    private String conferenceId;
    private int page;

    public MessagesRequest(@NonNull String conferenceId, @IntRange(from = 0) int page) {
        this.conferenceId = conferenceId;
        this.page = page;
    }

    @NonNull
    @Override
    protected RequestBuilder beginRequest() {
        return Bridge.get(MESSAGES_URL, conferenceId, page);
    }

    @Override
    protected ProxerResult parse(Response response) throws BridgeException {
        return response.asClass(MessagesResult.class);
    }

    @Override
    protected int getTag() {
        return ProxerTag.MESSAGES;
    }

    @Override
    protected MessagesErrorResult createErrorResult(@NonNull ProxerException exception) {
        return new MessagesErrorResult(conferenceId, exception);
    }

}
