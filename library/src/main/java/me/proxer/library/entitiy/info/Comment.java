package me.proxer.library.entitiy.info;

import com.squareup.moshi.Json;
import lombok.Getter;
import lombok.Value;
import me.proxer.library.entitiy.ProxerDateItem;
import me.proxer.library.entitiy.ProxerIdItem;
import me.proxer.library.entitiy.ProxerImageItem;
import me.proxer.library.enums.UserMediaProgress;

import javax.annotation.Nonnull;
import java.util.Date;

/**
 * The complete details of a comment associated with an {@link Entry}.
 *
 * @author Desnoo
 */
@SuppressWarnings("JavaDoc")
@Value
public class Comment implements ProxerIdItem, ProxerImageItem, ProxerDateItem {

    /**
     * Returns the id of this comment.
     */
    @Getter(onMethod = @__({@Override, @Nonnull}))
    @Json(name = "id")
    private String id;

    /**
     * Returns the id of the associated entry.
     */
    @Getter(onMethod = @__({@Nonnull}))
    @Json(name = "tid")
    private String entryId;

    /**
     * Returns the id of the author.
     */
    @Getter(onMethod = @__({@Nonnull}))
    @Json(name = "uid")
    private String authorId;

    /**
     * Returns the progress, the user has made on the associated media.
     */
    @Getter(onMethod = @__({@Nonnull}))
    @Json(name = "state")
    private UserMediaProgress mediaProgress;

    /**
     * Returns finer grained ratings for e.g. music.
     */
    @Getter(onMethod = @__({@Nonnull}))
    @Json(name = "data")
    private RatingDetails ratingDetails;

    /**
     * Returns the actual content of the comment.
     */
    @Getter(onMethod = @__({@Nonnull}))
    @Json(name = "comment")
    private String content;

    /**
     * Returns the overall rating.
     */
    @Json(name = "rating")
    private int overallRating;

    /**
     * Returns the episode, the user is currently at.
     */
    @Json(name = "episode")
    private int episode;

    /**
     * Returns the amount of helpful votes by other users.
     */
    @Json(name = "positive")
    private int helpfulVotes;

    /**
     * Returns the time, the comment was updated the last time.
     */
    @Getter(onMethod = @__({@Override, @Nonnull}))
    @Json(name = "timestamp")
    private Date date;

    /**
     * Returns the username of the author.
     */
    @Getter(onMethod = @__({@Nonnull}))
    @Json(name = "username")
    private String author;

    /**
     * Returns the id of the author's image.
     */
    @Getter(onMethod = @__({@Override, @Nonnull}))
    @Json(name = "avatar")
    private String image;
}
