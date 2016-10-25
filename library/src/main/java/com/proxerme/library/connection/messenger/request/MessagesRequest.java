package com.proxerme.library.connection.messenger.request;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Pair;

import com.proxerme.library.connection.ProxerResult;
import com.proxerme.library.connection.messenger.MessengerRequest;
import com.proxerme.library.connection.messenger.entity.Message;
import com.proxerme.library.connection.messenger.result.MessagesResult;
import com.squareup.moshi.Moshi;

import java.io.IOException;
import java.util.Arrays;

import okhttp3.ResponseBody;

/**
 * Request for loading messages. There are 4 different cases associated with the conferenceId and
 * messageId:
 * <p>
 * 1) conferenceId = "0" and messageId = "0". The latest messages of the user are loaded.
 * <br>
 * 2) conferenceId = "0" and messageId = X. The messages before the specified id are loaded.
 * <br>
 * 3) conferenceId = X and messageId = "0". The latest messages in the specified conference are
 * loaded.
 * <br>
 * 4) conferenceId = X and messageId = X. The messages before the specified messageId in the
 * specified conference are loaded.
 * <p>
 * This API requires the user to be logged in.
 *
 * @author Ruben Gees
 */
public class MessagesRequest extends MessengerRequest<Message[]> {

    public static final String ENDPOINT = "messages";

    private static final String CONFERENCE_ID_PARAMETER = "conference_id";
    private static final String MESSAGE_ID_PARAMETER = "message_id";
    private static final String MARK_AS_READ_TYPE = "read";

    private String conferenceId;
    private String messageId;
    private Boolean markAsRead;

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

    /**
     * Sets if the conference should be marked as read.
     *
     * @param markAsRead If the conference should be marked as read.
     * @return This request.
     */
    public MessagesRequest withMarkAsRead(@Nullable Boolean markAsRead) {
        this.markAsRead = markAsRead;

        return this;
    }

    @Override
    protected ProxerResult<Message[]> parse(@NonNull Moshi moshi, @NonNull ResponseBody body)
            throws IOException {
        return moshi.adapter(MessagesResult.class).fromJson(body.source());
    }

    @NonNull
    @Override
    protected String getApiEndpoint() {
        return ENDPOINT;
    }

    @NonNull
    @Override
    protected Iterable<Pair<String, ?>> getQueryParameters() {
        return Arrays.<Pair<String, ?>>asList(
                new Pair<>(CONFERENCE_ID_PARAMETER, conferenceId),
                new Pair<>(MESSAGE_ID_PARAMETER, messageId),
                new Pair<>(MARK_AS_READ_TYPE, markAsRead)
        );
    }
}
