package me.proxer.library.entitiy.user;

import com.squareup.moshi.Json;
import lombok.Value;
import me.proxer.library.entitiy.ProxerIdItem;
import me.proxer.library.entitiy.ProxerImageItem;

/**
 * Entity holding information about the user after a login.
 *
 * @author Ruben Gees
 */
@Value
public final class User implements ProxerIdItem, ProxerImageItem {

    /**
     * Returns the id.
     */
    @Json(name = "uid")
    private String id;

    /**
     * Returns the image id.
     */
    @Json(name = "avatar")
    private String image;

    /**
     * Returns the login token for further authentication.
     */
    @Json(name = "token")
    private String loginToken;
}
