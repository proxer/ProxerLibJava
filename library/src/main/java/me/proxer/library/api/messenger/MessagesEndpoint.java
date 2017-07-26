package me.proxer.library.api.messenger;

import lombok.Setter;
import lombok.experimental.Accessors;
import me.proxer.library.api.Endpoint;
import me.proxer.library.api.ProxerCall;
import me.proxer.library.entitiy.messenger.Message;
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
public final class MessagesEndpoint implements Endpoint<List<Message>> {

    private final InternalApi internalApi;

    /**
     * Sets the conference id of the conference to load.
     */
    @Nullable
    @Setter(onMethod = @__({@NotNull}))
    private String conferenceId;

    /**
     * Sets the message id to load from.
     */
    @Nullable
    @Setter(onMethod = @__({@NotNull}))
    private String messageId;

    /**
     * Sets if the conference should be marked as read. Defaults to true.
     */
    @Nullable
    @Setter(onMethod = @__({@NotNull}))
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
