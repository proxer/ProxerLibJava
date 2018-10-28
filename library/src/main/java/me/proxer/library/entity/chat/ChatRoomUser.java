package me.proxer.library.entity.chat;

import com.squareup.moshi.Json;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Value;
import me.proxer.library.api.NumberBasedBoolean;
import me.proxer.library.entity.ProxerIdItem;
import me.proxer.library.entity.ProxerImageItem;

import javax.annotation.Nullable;

/**
 * Entity representing a user, active in a {@link ChatRoom}.
 *
 * @author Ruben Gees
 */
@Value
@EqualsAndHashCode(onParam = @__({@Nullable}))
public class ChatRoomUser implements ProxerIdItem, ProxerImageItem {

    /**
     * Returns the id of this user.
     */
    @Getter(onMethod = @__({@Override}))
    @Json(name = "uid")
    private String id;

    /**
     * Returns the name.
     */
    @Json(name = "username")
    private String name;

    /**
     * {@inheritDoc}
     */
    @Getter(onMethod = @__({@Override}))
    @Json(name = "avatar")
    private String image;

    /**
     * Returns the status.
     */
    @Json(name = "status")
    private String status;

    /**
     * Returns if this user is a moderator.
     */
    @NumberBasedBoolean
    @Json(name = "mod")
    private boolean isModerator;
}
