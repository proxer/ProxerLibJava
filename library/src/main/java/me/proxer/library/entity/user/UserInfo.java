package me.proxer.library.entity.user;

import com.squareup.moshi.Json;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Value;
import me.proxer.library.entity.ProxerIdItem;
import me.proxer.library.entity.ProxerImageItem;

import javax.annotation.Nullable;
import java.util.Date;

/**
 * Entity holding all basic info of a user.
 *
 * @author Ruben Gees
 */
@Value
@EqualsAndHashCode(onParam = @__({@Nullable}))
public class UserInfo implements ProxerIdItem, ProxerImageItem {

    /**
     * Returns the id.
     */
    @Getter(onMethod = @__({@Override}))
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
    @Getter(onMethod = @__({@Override}))
    @Json(name = "avatar")
    private String image;

    /**
     * Returns if the user is a team member.
     */
    @Getter
    @Json(name = "isTeam")
    private boolean isTeamMember;

    /**
     * Returns if the user is currently a donator.
     */
    @Getter
    @Json(name = "isDonator")
    private boolean isDonator;

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
