package me.proxer.library.entity.messenger;

import com.squareup.moshi.Json;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Value;
import me.proxer.library.entity.ProxerIdItem;
import me.proxer.library.entity.ProxerImageItem;

import javax.annotation.Nullable;

/**
 * Entity that represents a participant in a {@link Conference}.
 *
 * @author Desnoo
 */
@Value
@EqualsAndHashCode(onParam = @__({@Nullable}))
public class ConferenceParticipant implements ProxerIdItem, ProxerImageItem {

    /**
     * {@inheritDoc}
     */
    @Getter(onMethod = @__({@Override}))
    @Json(name = "uid")
    private String id;

    /**
     * {@inheritDoc}
     */
    @Getter(onMethod = @__({@Override}))
    @Json(name = "avatar")
    private String image;

    /**
     * Returns the username.
     */
    @Json(name = "username")
    private String username;

    /**
     * Returns the current status.
     */
    @Json(name = "status")
    private String status;
}
