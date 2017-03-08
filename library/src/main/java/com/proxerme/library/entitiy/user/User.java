package com.proxerme.library.entitiy.user;

import com.proxerme.library.interfaces.IdItem;
import com.proxerme.library.interfaces.ImageItem;
import com.squareup.moshi.Json;
import lombok.Value;

/**
 * Entity holding information for the login and about the user after the login.
 *
 * @author Ruben Gees
 */
@Value
public final class User implements IdItem, ImageItem {

    @Json(name = "uid")
    private String id;
    @Json(name = "avatar")
    private String imageId;
    @Json(name = "token")
    private String loginToken;
}
