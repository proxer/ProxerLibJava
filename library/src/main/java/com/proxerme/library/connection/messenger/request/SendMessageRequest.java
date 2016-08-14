package com.proxerme.library.connection.messenger.request;

import android.support.annotation.NonNull;

import com.afollestad.bridge.Form;
import com.afollestad.bridge.Response;
import com.proxerme.library.connection.ProxerRequest;
import com.proxerme.library.connection.messenger.result.SendMessageResult;
import com.proxerme.library.info.ProxerTag;
import com.proxerme.library.info.ProxerUrlHolder;

/**
 * Request for sending a single message. This API requires the user to be logged in.
 *
 * @author Ruben Gees
 */

public class SendMessageRequest extends ProxerRequest<SendMessageResult> {

    private static final String SEND_MESSAGE_URL = "/api/v1/messenger/setmessage";

    private static final String CONFERENCE_ID_FORM = "conference_id";
    private static final String TEXT_FORM = "text";

    private String conferenceId;
    private String text;

    /**
     * The constructor.
     *
     * @param conferenceId The conference to send the message to.
     * @param text         The contents of the message.
     */
    public SendMessageRequest(@NonNull String conferenceId, @NonNull String text) {
        this.conferenceId = conferenceId;
        this.text = text;
    }

    @Override
    protected int getTag() {
        return ProxerTag.MESSENGER_SEND_MESSAGE;
    }

    @Override
    protected SendMessageResult parse(@NonNull Response response) throws Exception {
        return response.asClass(SendMessageResult.class);
    }

    @NonNull
    @Override
    protected String getURL() {
        return ProxerUrlHolder.getHost() + SEND_MESSAGE_URL;
    }

    @Override
    protected Form getBody() {
        return new Form().add(CONFERENCE_ID_FORM, conferenceId)
                .add(TEXT_FORM, text);
    }
}
