package com.proxerme.library.connection.experimental.chat.result;

import android.support.annotation.NonNull;

import com.afollestad.bridge.annotations.Body;
import com.proxerme.library.connection.experimental.chat.entity.Message;
import com.proxerme.library.interfaces.ProxerResult;

/**
 * The Result of a {@link com.proxerme.library.connection.experimental.chat.request.ChatRequest}.
 *
 * @author Ruben Gees
 */
public class ChatResult implements ProxerResult<Message[]> {

    String conferenceId;
    @Body(name = "messages")
    Message[] messages;

    ChatResult() {

    }

    /**
     * The constructor.
     *
     * @param conferenceId The id of the conference.
     * @param messages     The array of messages.
     */
    public ChatResult(@NonNull String conferenceId, @NonNull Message[] messages) {
        this.conferenceId = conferenceId;
        this.messages = messages;
    }

    /**
     * Returns the array of retrieved messages.
     *
     * @return The array of messages.
     */
    @NonNull
    @Override
    public Message[] getItem() {
        return messages;
    }

    /**
     * Returns the id of the conference.
     *
     * @return The id.
     */
    @NonNull
    public String getConferenceId() {
        return conferenceId;
    }
}
