package com.proxerme.library.entitiy.user;

import com.proxerme.library.entitiy.interfaces.IdItem;
import com.proxerme.library.enums.MediaState;
import com.proxerme.library.enums.Medium;
import com.proxerme.library.enums.UserMediaProgress;
import com.squareup.moshi.Json;
import lombok.Getter;
import lombok.Value;
import org.jetbrains.annotations.NotNull;

/**
 * Entity holding all relevant information of an entry in the user's media list (Watched, watching).
 * This also includes comments by the user.
 *
 * @author Ruben Gees
 */
@SuppressWarnings("JavaDoc")
@Value
public class UserMediaListEntry implements IdItem {

    /**
     * Returns the id.
     */
    @Getter(onMethod = @__({@Override, @NotNull}))
    @Json(name = "id")
    private String id;

    /**
     * Returns the name.
     */
    @Getter(onMethod = @__({@NotNull}))
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
    @Getter(onMethod = @__({@NotNull}))
    @Json(name = "medium")
    private Medium medium;

    /**
     * Returns the state, the associated {@link com.proxerme.library.entitiy.info.Entry} currently has.
     */
    @Getter(onMethod = @__({@NotNull}))
    @Json(name = "estate")
    private MediaState state;

    /**
     * Returns the id of the associated {@link com.proxerme.library.entitiy.info.Comment}.
     */
    @Getter(onMethod = @__({@NotNull}))
    @Json(name = "cid")
    private String commentId;

    /**
     * Returns the content of the associated {@link com.proxerme.library.entitiy.info.Comment}.
     */
    @Getter(onMethod = @__({@NotNull}))
    @Json(name = "comment")
    private String commentContent;

    /**
     * Returns the progress, the user has made on this entry,
     */
    @Getter(onMethod = @__({@NotNull}))
    @Json(name = "state")
    private UserMediaProgress mediaProgress;

    /**
     * Returns the episode, the user is currently at.
     */
    @Getter(onMethod = @__({@NotNull}))
    @Json(name = "episode")
    private int episode;

    /**
     * Returns the rating, the user has given. 0 means that the user has not rated yet.
     */
    @Getter(onMethod = @__({@NotNull}))
    @Json(name = "rating")
    private int rating;
}
