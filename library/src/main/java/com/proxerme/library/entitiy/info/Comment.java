package com.proxerme.library.entitiy.info;

import com.proxerme.library.enums.UserMediaProgress;
import com.proxerme.library.interfaces.IdItem;
import com.proxerme.library.interfaces.ImageItem;
import com.proxerme.library.interfaces.TimeItem;
import com.squareup.moshi.Json;
import org.jetbrains.annotations.NotNull;

import java.util.Date;

/**
 * The complete details of a comment of an entry.
 *
 * @author Desnoo
 */
public final class Comment implements IdItem, ImageItem, TimeItem {

    @Json(name = "id")
    private String id;
    @Json(name = "tid")
    private String entryId;
    @Json(name = "uid")
    private String authorId;
    @Json(name = "state")
    private UserMediaProgress mediaProgress;
    @Json(name = "data")
    private RatingDetails ratingDetails;
    @Json(name = "comment")
    private String content;
    @Json(name = "rating")
    private int overallRating;
    @Json(name = "episode")
    private int episode;
    @Json(name = "positive")
    private int helpfulVotes;
    @Json(name = "timestamp")
    private Date time;
    @Json(name = "username")
    private String author;
    @Json(name = "avatar")
    private String imageId;

    private Comment() {

    }

    /**
     * The constructor.
     *
     * @param id            The content id.
     * @param entryId       The entry id.
     * @param authorId      The user id.
     * @param mediaProgress The progress the user has made for the media.
     * @param ratingDetails The rating details.
     * @param content       The content text.
     * @param overallRating The overallRating value.
     * @param episode       The highest episode the user watched.
     * @param helpfulVotes  The number of users that voted this content up.
     * @param time          The content creation time.
     * @param author        The name of the user.
     * @param imageId       The image id.
     */
    public Comment(@NotNull final String id, @NotNull final String entryId, @NotNull final String authorId,
                   @NotNull final UserMediaProgress mediaProgress, @NotNull final RatingDetails ratingDetails,
                   @NotNull String content, final int overallRating, final int episode, final int helpfulVotes,
                   @NotNull Date time, @NotNull String author,
                   @NotNull String imageId) {
        this.id = id;
        this.entryId = entryId;
        this.authorId = authorId;
        this.mediaProgress = mediaProgress;
        this.ratingDetails = ratingDetails;
        this.content = content;
        this.overallRating = overallRating;
        this.episode = episode;
        this.helpfulVotes = helpfulVotes;
        this.time = time;
        this.author = author;
        this.imageId = imageId;
    }

    /**
     * {@inheritDoc}
     */
    @NotNull
    @Override
    public String getId() {
        return id;
    }

    /**
     * Returns the if of the entry.
     *
     * @return The id.
     **/
    @NotNull
    public String getEntryId() {
        return entryId;
    }

    /**
     * Returns the id of the author.
     *
     * @return The id.
     **/
    @NotNull
    public String getAuthorId() {
        return authorId;
    }

    /**
     * Returns the progress the user has made.
     *
     * @return The progress.
     */
    @NotNull
    public UserMediaProgress getMediaProgress() {
        return mediaProgress;
    }

    /**
     * Returns the RatingDetails.
     *
     * @return The RatingDetails.
     **/
    @NotNull
    public RatingDetails getRatingDetails() {
        return ratingDetails;
    }

    /**
     * Returns the content of this comment.
     *
     * @return The content.
     **/
    public String getContent() {
        return content;
    }

    /**
     * Returns the overall rating in a range from 1 to 10. If no rating is present yet, 0 is returned.
     *
     * @return The rating.
     **/
    public int getOverallRating() {
        return overallRating;
    }

    /**
     * Returns the episode.
     *
     * @return The episode.
     **/
    public int getEpisode() {
        return episode;
    }

    /**
     * Returns the amount of helpful votes.
     *
     * @return The helpful votes.
     **/
    public int getHelpfulVotes() {
        return helpfulVotes;
    }

    /**
     * Returns the time this comment was made.
     *
     * @return The time.
     **/
    @Override
    @NotNull
    public Date getTime() {
        return time;
    }

    /**
     * Returns the username of the author.
     *
     * @return The username.
     **/
    @NotNull
    public String getAuthor() {
        return author;
    }

    /**
     * {@inheritDoc}
     */
    @NotNull
    @Override
    public String getImageId() {
        return imageId;
    }

    @SuppressWarnings("SimplifiableIfStatement")
    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Comment comment = (Comment) o;

        if (overallRating != comment.overallRating) return false;
        if (episode != comment.episode) return false;
        if (helpfulVotes != comment.helpfulVotes) return false;
        if (!id.equals(comment.id)) return false;
        if (!entryId.equals(comment.entryId)) return false;
        if (!authorId.equals(comment.authorId)) return false;
        if (mediaProgress != comment.mediaProgress) return false;
        if (!ratingDetails.equals(comment.ratingDetails)) return false;
        if (!content.equals(comment.content)) return false;
        if (!time.equals(comment.time)) return false;
        if (!author.equals(comment.author)) return false;
        return imageId.equals(comment.imageId);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + entryId.hashCode();
        result = 31 * result + authorId.hashCode();
        result = 31 * result + mediaProgress.hashCode();
        result = 31 * result + ratingDetails.hashCode();
        result = 31 * result + content.hashCode();
        result = 31 * result + overallRating;
        result = 31 * result + episode;
        result = 31 * result + helpfulVotes;
        result = 31 * result + time.hashCode();
        result = 31 * result + author.hashCode();
        result = 31 * result + imageId.hashCode();
        return result;
    }
}
