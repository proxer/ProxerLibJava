package com.proxerme.library.entitiy.messenger;

import com.proxerme.library.entitiy.ProxerIdItem;
import com.proxerme.library.entitiy.ProxerImageItem;
import com.squareup.moshi.Json;
import lombok.Getter;
import lombok.Value;
import org.jetbrains.annotations.NotNull;

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
    @Getter(onMethod = @__({@Override, @NotNull}))
    @Json(name = "uid")
    private String id;

    /**
     * {@inheritDoc}
     */
    @Getter(onMethod = @__({@Override, @NotNull}))
    @Json(name = "avatar")
    private String image;

    /**
     * Returns the username.
     */
    @Getter(onMethod = @__({@NotNull}))
    @Json(name = "username")
    private String username;

    /**
     * Returns the current status.
     */
    @Getter(onMethod = @__({@NotNull}))
    @Json(name = "status")
    private String status;
}
