package me.proxer.library.entitiy.messenger;

import com.squareup.moshi.Json;
import lombok.Getter;
import lombok.Value;
import me.proxer.library.entitiy.ProxerDateItem;
import me.proxer.library.entitiy.ProxerIdItem;
import me.proxer.library.enums.Device;
import me.proxer.library.enums.MessageAction;
import org.jetbrains.annotations.NotNull;

import java.util.Date;

/**
 * Entity representing a single message.
 *
 * @author Ruben Gees
 */
@Value
public class Message implements ProxerIdItem, ProxerDateItem {

    /**
     * {@inheritDoc}
     */
    @Getter(onMethod = @__({@Override, @NotNull}))
    @Json(name = "message_id")
    private String id;

    /**
     * Returns the id of the associated {@link Conference}.
     */
    @Getter(onMethod = @__({@NotNull}))
    @Json(name = "conference_id")
    private String conferenceId;

    /**
     * Returns the id of the associated user.
     */
    @Getter(onMethod = @__({@NotNull}))
    @Json(name = "user_id")
    private String userId;

    /**
     * Returns the username of the author.
     */
    @Getter(onMethod = @__({@NotNull}))
    @Json(name = "username")
    private String username;

    /**
     * Returns the actual content of the message.
     */
    @Getter(onMethod = @__({@NotNull}))
    @Json(name = "message")
    private String message;

    /**
     * Returns the action of this message. If the action is not {@link MessageAction#NONE}, {@link #getMessage()}
     * returns associated information, like the name of the user, performing the action.
     */
    @Getter(onMethod = @__({@NotNull}))
    @Json(name = "action")
    private MessageAction action;

    /**
     * {@inheritDoc}
     */
    @Getter(onMethod = @__({@Override, @NotNull}))
    @Json(name = "timestamp")
    private Date date;

    /**
     * Returns the device, the message was sent from.
     */
    @Json(name = "device")
    private Device device;
}
