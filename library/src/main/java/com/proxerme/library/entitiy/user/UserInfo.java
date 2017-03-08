package com.proxerme.library.entitiy.user;

import com.proxerme.library.interfaces.IdItem;
import com.proxerme.library.interfaces.ImageItem;
import com.squareup.moshi.Json;
import lombok.Getter;
import lombok.Value;
import org.jetbrains.annotations.NotNull;

import java.util.Date;

/**
 * Entity holding all basic info of a {@link User}.
 *
 * @author Ruben Gees
 */
@SuppressWarnings("JavaDoc")
@Value
public class UserInfo implements IdItem, ImageItem {

    /**
     * @return The id.
     */
    @Getter(onMethod = @__({@Override, @NotNull}))
    @Json(name = "uid")
    private String id;

    /**
     * @return The username.
     */
    @Getter(onMethod = @__({@NotNull}))
    @Json(name = "username")
    private String username;

    /**
     * @return The image id.
     */
    @Getter(onMethod = @__({@NotNull}))
    @Json(name = "avatar")
    private String imageId;

    /**
     * @return The current status message.
     */
    @Getter(onMethod = @__({@NotNull}))
    @Json(name = "status")
    private String status;

    /**
     * @return The time of the last status change.
     */
    @Getter(onMethod = @__({@NotNull}))
    @Json(name = "status_time")
    private Date lastStatusChange;

    /**
     * @return The upload points.
     */
    @Json(name = "points_uploads")
    private int uploadPoints;

    /**
     * @return The forum points.
     */
    @Json(name = "points_forum")
    private int forumPoints;

    /**
     * @return The anime points.
     */
    @Json(name = "points_anime")
    private int animePoints;

    /**
     * @return The manga points.
     */
    @Json(name = "points_manga")
    private int mangaPoints;

    /**
     * @return The info points.
     */
    @Json(name = "points_info")
    private int infoPoints;

    /**
     * @return The miscellaneous points.
     */
    @Json(name = "points_misc")
    private int miscPoints;
}
