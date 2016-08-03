package com.proxerme.library.connection.messenger.request;

import android.support.annotation.NonNull;

import com.afollestad.bridge.Form;
import com.afollestad.bridge.Response;
import com.proxerme.library.connection.ProxerRequest;
import com.proxerme.library.connection.messenger.result.MessagesResult;
import com.proxerme.library.info.ProxerUrlHolder;

import static com.proxerme.library.info.ProxerTag.MESSENGER_MESSAGES;

/**
 * TODO: Describe class
 *
 * @author Ruben Gees
 */

public class MessagesRequest extends ProxerRequest<MessagesResult> {

    private static final String MESSENGER_URL = "/api/v1/messenger/messages";

    private static final String CONFERENCE_ID_FORM = "conference_id";
    private static final String MESSAGE_ID_FORM = "message_id";

    private String conferenceId;
    private String messageId;

    public MessagesRequest(@NonNull String conferenceId, @NonNull String messageId) {
        this.conferenceId = conferenceId;
        this.messageId = messageId;
    }

    @Override
    protected int getTag() {
        return MESSENGER_MESSAGES;
    }

    @Override
    protected MessagesResult parse(@NonNull Response response) throws Exception {
        return response.asClass(MessagesResult.class);
    }

    @NonNull
    @Override
    protected String getURL() {
        return ProxerUrlHolder.getHost() + MESSENGER_URL;
    }

    @Override
    protected Form getBody() {
        return new Form()
                .add(CONFERENCE_ID_FORM, conferenceId)
                .add(MESSAGE_ID_FORM, messageId);
    }
}
