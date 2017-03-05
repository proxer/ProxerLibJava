package com.proxerme.library.entitiy.user;

import com.proxerme.library.interfaces.IdItem;
import com.proxerme.library.interfaces.ImageItem;
import com.squareup.moshi.Json;
import org.jetbrains.annotations.NotNull;

/**
 * Entity holding information for the login and about the user after the login.
 *
 * @author Ruben Gees
 */
public class User implements IdItem, ImageItem {

    @Json(name = "uid")
    private String id;
    @Json(name = "avatar")
    private String imageId;
    @Json(name = "token")
    private String loginToken;

    private User() {

    }

    /**
     * The Constructor.
     *
     * @param id         The id of the user.
     * @param image      The profile picture of the user.
     * @param loginToken The login token, usable for further authentication.
     */
    public User(@NotNull String id, @NotNull String image, @NotNull String loginToken) {
        this.id = id;
        this.imageId = image;
        this.loginToken = loginToken;
    }

    /**
     * Returns the id of the user.
     *
     * @return The id.
     */
    @Override
    @NotNull
    public String getId() {
        return id;
    }

    /**
     * Returns the profile picture of the user.
     *
     * @return The image.
     */
    @Override
    @NotNull
    public String getImageId() {
        return imageId;
    }

    /**
     * Returns the login token of the user for further authentication.
     *
     * @return The token.
     */
    @NotNull
    public String getLoginToken() {
        return loginToken;
    }

    @SuppressWarnings("SimplifiableIfStatement")
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!id.equals(user.id)) return false;
        if (!imageId.equals(user.imageId)) return false;
        return loginToken.equals(user.loginToken);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + imageId.hashCode();
        result = 31 * result + loginToken.hashCode();
        return result;
    }
}
