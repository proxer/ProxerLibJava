package me.proxer.library.entitiy.user;

import com.squareup.moshi.Json;
import lombok.Value;
import me.proxer.library.entitiy.ProxerIdItem;
import me.proxer.library.entitiy.ProxerImageItem;

import java.util.Date;

/**
 * Entity holding all basic info of a user.
 *
 * @author Ruben Gees
 */
@Value
public class UserInfo implements ProxerIdItem, ProxerImageItem {

    /**
     * Returns the id.
     */
    @Json(name = "uid")
    private String id;

    /**
     * Returns the username.
     */
    @Json(name = "username")
    private String username;

    /**
     * Returns the image id.
     */
    @Json(name = "avatar")
    private String image;

    /**
     * Returns the current status message.
     */
    @Json(name = "status")
    private String status;

    /**
     * Returns the time of the last status change.
     */
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
