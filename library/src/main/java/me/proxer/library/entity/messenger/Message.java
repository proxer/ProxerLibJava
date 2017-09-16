package me.proxer.library.entity.messenger;

import com.squareup.moshi.Json;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Value;
import me.proxer.library.entity.ProxerDateItem;
import me.proxer.library.entity.ProxerIdItem;
import me.proxer.library.enums.Device;
import me.proxer.library.enums.MessageAction;

import javax.annotation.Nullable;
import java.util.Date;

/**
 * Entity representing a single message.
 *
 * @author Ruben Gees
 */
@Value
@EqualsAndHashCode(onParam = @__({@Nullable}))
public class Message implements ProxerIdItem, ProxerDateItem {

    /**
     * {@inheritDoc}
     */
    @Getter(onMethod = @__({@Override}))
    @Json(name = "message_id")
    private String id;

    /**
     * Returns the id of the associated {@link Conference}.
     */
    @Json(name = "conference_id")
    private String conferenceId;

    /**
     * Returns the id of the associated user.
     */
    @Json(name = "user_id")
    private String userId;

    /**
     * Returns the username of the author.
     */
    @Json(name = "username")
    private String username;

    /**
     * Returns the actual content of the message.
     */
    @Json(name = "message")
    private String message;

    /**
     * Returns the action of this message. If the action is not {@link MessageAction#NONE}, {@link #getMessage()}
     * returns associated information, like the name of the user, performing the action.
     */
    @Json(name = "action")
    private MessageAction action;

    /**
     * {@inheritDoc}
     */
    @Getter(onMethod = @__({@Override}))
    @Json(name = "timestamp")
    private Date date;

    /**
     * Returns the device, the message was sent from.
     */
    @Json(name = "device")
    private Device device;
}
