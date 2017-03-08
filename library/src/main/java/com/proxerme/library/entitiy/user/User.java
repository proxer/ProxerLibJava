package com.proxerme.library.entitiy.user;

import com.proxerme.library.interfaces.IdItem;
import com.proxerme.library.interfaces.ImageItem;
import com.squareup.moshi.Json;
import lombok.Getter;
import lombok.Value;
import org.jetbrains.annotations.NotNull;

/**
 * Entity holding information for the login and about the user after the login.
 *
 * @author Ruben Gees
 */
@SuppressWarnings("JavaDoc")
@Value
public final class User implements IdItem, ImageItem {

    /**
     * @return The id.
     */
    @Getter(onMethod = @__({@Override, @NotNull}))
    @Json(name = "uid")
    private String id;

    /**
     * @return The image id.
     */
    @Getter(onMethod = @__({@Override, @NotNull}))
    @Json(name = "avatar")
    private String imageId;

    /**
     * @return The login token for further authentication.
     */
    @Getter(onMethod = @__({@NotNull}))
    @Json(name = "token")
    private String loginToken;
}
