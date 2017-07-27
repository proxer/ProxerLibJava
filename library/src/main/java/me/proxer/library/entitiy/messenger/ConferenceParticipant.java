package me.proxer.library.entitiy.messenger;

import com.squareup.moshi.Json;
import lombok.Getter;
import lombok.Value;
import me.proxer.library.entitiy.ProxerIdItem;
import me.proxer.library.entitiy.ProxerImageItem;

import javax.annotation.Nonnull;

/**
 * Entity that represents a participant in a {@link Conference}.
 *
 * @author Desnoo
 */
@Value
public class ConferenceParticipant implements ProxerIdItem, ProxerImageItem {

    /**
     * {@inheritDoc}
     */
    @Getter(onMethod = @__({@Override, @Nonnull}))
    @Json(name = "uid")
    private String id;

    /**
     * {@inheritDoc}
     */
    @Getter(onMethod = @__({@Override, @Nonnull}))
    @Json(name = "avatar")
    private String image;

    /**
     * Returns the username.
     */
    @Getter(onMethod = @__({@Nonnull}))
    @Json(name = "username")
    private String username;

    /**
     * Returns the current status.
     */
    @Getter(onMethod = @__({@Nonnull}))
    @Json(name = "status")
    private String status;
}
