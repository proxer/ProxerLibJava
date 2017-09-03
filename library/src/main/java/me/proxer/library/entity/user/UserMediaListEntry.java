package me.proxer.library.entity.user;

import com.squareup.moshi.Json;
import lombok.Getter;
import lombok.Value;
import me.proxer.library.entity.ProxerIdItem;
import me.proxer.library.entity.info.Comment;
import me.proxer.library.entity.info.Entry;
import me.proxer.library.enums.MediaState;
import me.proxer.library.enums.Medium;
import me.proxer.library.enums.UserMediaProgress;

/**
 * Entity holding all relevant information of an entry in the user's media list (Watched, watching).
 * This also includes comments by the user.
 *
 * @author Ruben Gees
 */
@Value
public class UserMediaListEntry implements ProxerIdItem {

    /**
     * Returns the id.
     */
    @Getter(onMethod = @__({@Override}))
    @Json(name = "id")
    private String id;

    /**
     * Returns the name.
     */
    @Json(name = "name")
    private String name;

    /**
     * Returns the amount of episodes.
     */
    @Json(name = "count")
    private int episodeAmount;

    /**
     * Returns the medium.
     */
    @Json(name = "medium")
    private Medium medium;

    /**
     * Returns the state, the associated {@link Entry} currently has.
     */
    @Json(name = "estate")
    private MediaState state;

    /**
     * Returns the id of the associated {@link Comment}.
     */
    @Json(name = "cid")
    private String commentId;

    /**
     * Returns the content of the associated {@link Comment}.
     */
    @Json(name = "comment")
    private String commentContent;

    /**
     * Returns the progress, the user has made on this entry,
     */
    @Json(name = "state")
    private UserMediaProgress mediaProgress;

    /**
     * Returns the episode, the user is currently at.
     */
    @Json(name = "episode")
    private int episode;

    /**
     * Returns the rating, the user has given. 0 means that the user has not rated yet.
     */
    @Json(name = "rating")
    private int rating;
}
