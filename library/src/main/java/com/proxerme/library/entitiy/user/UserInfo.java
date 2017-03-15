package com.proxerme.library.entitiy.user;

import com.proxerme.library.entitiy.ProxerIdItem;
import com.proxerme.library.entitiy.ProxerImageItem;
import com.squareup.moshi.Json;
import lombok.Getter;
import lombok.Value;
import org.jetbrains.annotations.NotNull;

import java.util.Date;

/**
 * Entity holding all basic info of a user.
 *
 * @author Ruben Gees
 */
@SuppressWarnings("JavaDoc")
@Value
public class UserInfo implements ProxerIdItem, ProxerImageItem {

    /**
     * Returns the id.
     */
    @Getter(onMethod = @__({@Override, @NotNull}))
    @Json(name = "uid")
    private String id;

    /**
     * Returns the username.
     */
    @Getter(onMethod = @__({@NotNull}))
    @Json(name = "username")
    private String username;

    /**
     * Returns the image id.
     */
    @Getter(onMethod = @__({@NotNull}))
    @Json(name = "avatar")
    private String image;

    /**
     * Returns the current status message.
     */
    @Getter(onMethod = @__({@NotNull}))
    @Json(name = "status")
    private String status;

    /**
     * Returns the time of the last status change.
     */
    @Getter(onMethod = @__({@NotNull}))
    @Json(name = "status_time")
    private Date lastStatusChange;

    /**
     * Returns the upload points.
     */
    @Json(name = "points_uploads")
    private int uploadPoints;

    /**
     * Returns the forum points.
     */
    @Json(name = "points_forum")
    private int forumPoints;

    /**
     * Returns the anime points.
     */
    @Json(name = "points_anime")
    private int animePoints;

    /**
     * Returns the manga points.
     */
    @Json(name = "points_manga")
    private int mangaPoints;

    /**
     * Returns the info points.
     */
    @Json(name = "points_info")
    private int infoPoints;

    /**
     * Returns the miscellaneous points.
     */
    @Json(name = "points_misc")
    private int miscPoints;
}
