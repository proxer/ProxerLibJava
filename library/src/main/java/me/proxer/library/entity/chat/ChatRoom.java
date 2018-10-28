package me.proxer.library.entity.chat;

import com.squareup.moshi.Json;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Value;
import me.proxer.library.api.NumberBasedBoolean;
import me.proxer.library.entity.ProxerIdItem;

import javax.annotation.Nullable;

/**
 * Entity representing a chat room.
 *
 * @author Ruben Gees
 */
@Value
@EqualsAndHashCode(onParam = @__({@Nullable}))
public class ChatRoom implements ProxerIdItem {

    /**
     * Returns the id of this chat room.
     */
    @Getter(onMethod = @__({@Override}))
    @Json(name = "id")
    private String id;

    /**
     * Returns the name.
     */
    @Json(name = "name")
    private String name;

    /**
     * Returns the topic.
     */
    @Json(name = "topic")
    private String topic;

    /**
     * Returns if this chat room is read only.
     */
    @NumberBasedBoolean
    @Json(name = "flag_readonly")
    private boolean isReadOnly;
}
