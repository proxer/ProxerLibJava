package com.proxerme.library.entitiy.info;

import com.proxerme.library.enums.UserMediaProgress;
import com.proxerme.library.interfaces.IdItem;
import com.proxerme.library.interfaces.ImageItem;
import com.proxerme.library.interfaces.TimeItem;
import com.squareup.moshi.Json;
import lombok.Getter;
import lombok.Value;
import org.jetbrains.annotations.NotNull;

import java.util.Date;

/**
 * The complete details of a comment of an entry.
 *
 * @author Desnoo
 */
@SuppressWarnings("JavaDoc")
@Value
public class Comment implements IdItem, ImageItem, TimeItem {

    /**
     * @return The id of this comment.
     */
    @Getter(onMethod = @__({@Override, @NotNull}))
    @Json(name = "id")
    private String id;

    /**
     * @return The id of the associated entry.
     */
    @Getter(onMethod = @__({@NotNull}))
    @Json(name = "tid")
    private String entryId;

    /**
     * @return The id of the author.
     */
    @Getter(onMethod = @__({@NotNull}))
    @Json(name = "uid")
    private String authorId;

    /**
     * @return The progress, the user has made on the associated media.
     */
    @Getter(onMethod = @__({@NotNull}))
    @Json(name = "state")
    private UserMediaProgress mediaProgress;

    /**
     * @return Finer grained ratings for e.g. music.
     */
    @Getter(onMethod = @__({@NotNull}))
    @Json(name = "data")
    private RatingDetails ratingDetails;

    /**
     * @return The actual content of the comment.
     */
    @Getter(onMethod = @__({@NotNull}))
    @Json(name = "comment")
    private String content;

    /**
     * @return The overall rating.
     */
    @Json(name = "rating")
    private int overallRating;

    /**
     * @return The episode, the user is currently at.
     */
    @Json(name = "episode")
    private int episode;

    /**
     * @return The amount of helpful votes by other users.
     */
    @Json(name = "positive")
    private int helpfulVotes;

    /**
     * @return The time, the comment was updated the last time.
     */
    @Getter(onMethod = @__({@Override, @NotNull}))
    @Json(name = "timestamp")
    private Date time;

    /**
     * @return The username of the author.
     */
    @Getter(onMethod = @__({@NotNull}))
    @Json(name = "username")
    private String author;

    /**
     * @return The id of the author's image.
     */
    @Getter(onMethod = @__({@Override, @NotNull}))
    @Json(name = "avatar")
    private String imageId;
}
