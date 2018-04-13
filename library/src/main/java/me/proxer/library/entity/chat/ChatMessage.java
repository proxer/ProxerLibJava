package me.proxer.library.entity.chat;

import com.squareup.moshi.Json;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Value;
import me.proxer.library.entity.ProxerDateItem;
import me.proxer.library.entity.ProxerIdItem;
import me.proxer.library.entity.ProxerImageItem;
import me.proxer.library.enums.ChatMessageAction;
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
public class ChatMessage implements ProxerIdItem, ProxerDateItem, ProxerImageItem {

    /**
     * {@inheritDoc}
     */
    @Getter(onMethod = @__({@Override}))
    @Json(name = "id")
    private String id;

    /**
     * Returns the id of the associated user.
     */
    @Json(name = "fromid")
    private String userId;

    /**
     * Returns the username of the author.
     */
    @Json(name = "username")
    private String username;

    /**
     * Returns the id of the author's image.
     */
    @Getter(onMethod = @__({@Override}))
    @Json(name = "avatar")
    private String image;

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
    private ChatMessageAction action;

    /**
     * {@inheritDoc}
     */
    @Getter(onMethod = @__({@Override}))
    @Json(name = "timestamp")
    private Date date;
}
