package me.proxer.library.entitiy.user;

import com.squareup.moshi.Json;
import lombok.Getter;
import lombok.Value;
import me.proxer.library.entitiy.ProxerIdItem;
import me.proxer.library.entitiy.ProxerImageItem;

import javax.annotation.Nonnull;

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
    @Getter(onMethod = @__({@Override, @Nonnull}))
    @Json(name = "uid")
    private String id;

    /**
     * Returns the image id.
     */
    @Getter(onMethod = @__({@Override, @Nonnull}))
    @Json(name = "avatar")
    private String image;

    /**
     * Returns the login token for further authentication.
     */
    @Getter(onMethod = @__({@Nonnull}))
    @Json(name = "token")
    private String loginToken;
}
