package com.proxerme.library.entitiy.user;

import com.proxerme.library.entitiy.ProxerIdItem;
import com.proxerme.library.entitiy.ProxerImageItem;
import com.squareup.moshi.Json;
import lombok.Getter;
import lombok.Value;
import org.jetbrains.annotations.NotNull;

/**
 * Entity holding information about the user after a login.
 *
 * @author Ruben Gees
 */
@SuppressWarnings("JavaDoc")
@Value
public final class User implements ProxerIdItem, ProxerImageItem {

    /**
     * Returns the id.
     */
    @Getter(onMethod = @__({@Override, @NotNull}))
    @Json(name = "uid")
    private String id;

    /**
     * Returns the image id.
     */
    @Getter(onMethod = @__({@Override, @NotNull}))
    @Json(name = "avatar")
    private String image;

    /**
     * Returns the login token for further authentication.
     */
    @Getter(onMethod = @__({@NotNull}))
    @Json(name = "token")
    private String loginToken;
}
