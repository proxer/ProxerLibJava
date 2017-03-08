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
@Value
public final class Comment implements IdItem, ImageItem, TimeItem {

    /**
     * The id of this comment.
     */
    @Getter(onMethod = @__(@NotNull))
    @Json(name = "id")
    private String id;

    @Getter(onMethod = @__(@NotNull))
    @Json(name = "tid")
    private String entryId;
    @Getter(onMethod = @__(@NotNull))
    @Json(name = "uid")
    private String authorId;
    @Getter(onMethod = @__(@NotNull))
    @Json(name = "state")
    private UserMediaProgress mediaProgress;
    @Getter(onMethod = @__(@NotNull))
    @Json(name = "data")
    private RatingDetails ratingDetails;
    @Getter(onMethod = @__(@NotNull))
    @Json(name = "comment")
    private String content;
    @Json(name = "rating")
    private int overallRating;
    @Json(name = "episode")
    private int episode;
    @Json(name = "positive")
    private int helpfulVotes;
    @Getter(onMethod = @__(@NotNull))
    @Json(name = "timestamp")
    private Date time;
    @Getter(onMethod = @__(@NotNull))
    @Json(name = "username")
    private String author;
    @Getter(onMethod = @__(@NotNull))
    @Json(name = "avatar")
    private String imageId;
}
