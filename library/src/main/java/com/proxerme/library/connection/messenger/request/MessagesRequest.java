package com.proxerme.library.connection.messenger.request;

import android.support.annotation.NonNull;

import com.afollestad.bridge.Form;
import com.afollestad.bridge.Response;
import com.proxerme.library.connection.ProxerRequest;
import com.proxerme.library.connection.messenger.result.MessagesResult;
import com.proxerme.library.info.ProxerUrlHolder;

import static com.proxerme.library.info.ProxerTag.MESSENGER_MESSAGES;

/**
 * Request for loading messages. There are 4 different cases associated with the conferenceId and
 * messageId:
 * <p>
 * 1) conferenceId = "0" and messageId = "0". The latest messages of the user are loaded.
 * 2) conferenceId = "0" and messageId = X. The messages before the specified id are loaded.
 * 3) conferenceId = X and messageId = "0". The latest messages in the specified conference are
 * loaded.
 * 4) conferenceId = X and messageId = X. The messages before the specified messageId in the
 * specified conference are loaded.
 * <p>
 * This API requires the user to be logged in.
 *
 * @author Ruben Gees
 */

public class MessagesRequest extends ProxerRequest<MessagesResult> {

    private static final String MESSENGER_URL = "/api/v1/messenger/messages";

    private static final String CONFERENCE_ID_FORM = "conference_id";
    private static final String MESSAGE_ID_FORM = "message_id";

    private String conferenceId;
    private String messageId;

    /**
     * The constructor.
     *
     * @param conferenceId The id of the conference to load from.
     * @param messageId    The id of the message to load previous messages from.
     */
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
