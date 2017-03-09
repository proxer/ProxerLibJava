package com.proxerme.library.entitiy.user;

import com.proxerme.library.entitiy.interfaces.IdItem;
import com.proxerme.library.entitiy.interfaces.ImageItem;
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
public final class User implements IdItem, ImageItem {

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
    private String imageId;

    /**
     * Returns the login token for further authentication.
     */
    @Getter(onMethod = @__({@NotNull}))
    @Json(name = "token")
    private String loginToken;
}
