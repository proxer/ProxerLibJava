package com.proxerme.library.api.messenger;

import com.proxerme.library.api.Endpoint;
import com.proxerme.library.api.ProxerCall;
import com.proxerme.library.entitiy.messenger.Message;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Endpoint for retrieving the user's messages.
 * <p>
 * Four types of this request exist:
 * 1) conferenceId = 0 and messageId = 0: Returns the last messages of the user from all conferences.
 * 2) conferenceId = 0 and messageId != 0: Returns the last messages before that of the passed messageId from all
 * conferences.
 * 3) conferenceId != 0 and messageId = 0: Returns the last messages of the conference, specified by the passed
 * conferenceId.
 * 4) conferenceId != 0 and message != 0: Returns the last messages before that of the passed messageId of the
 * conference, specified by the passed conferenceId.
 * <p>
 * Moreover the {@link #markAsRead} parameter controls if the conference should be marked as read in the cases 3)
 * and 4).
 *
 * @author Ruben Gees
 */
@Accessors(fluent = true)
public final class MessagesEndpoint implements Endpoint {

    private final InternalApi internalApi;

    /**
     * Sets the conference id of the conference to load.
     */
    @Setter(onMethod = @__({@Nullable}))
    private String conferenceId;

    /**
     * Sets the message id to load from.
     */
    @Setter(onMethod = @__({@Nullable}))
    private String messageId;

    /**
     * Sets if the conference should be marked as read. Defaults to true.
     */
    @Setter(onMethod = @__({@Nullable}))
    private Boolean markAsRead;

    MessagesEndpoint(@NotNull final InternalApi internalApi) {
        this.internalApi = internalApi;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @NotNull
    public ProxerCall<List<Message>> build() {
        return internalApi.messages(conferenceId, messageId, markAsRead);
    }
}
