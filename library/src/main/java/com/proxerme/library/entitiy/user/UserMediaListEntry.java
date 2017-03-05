package com.proxerme.library.entitiy.user;

import com.proxerme.library.enums.MediaState;
import com.proxerme.library.enums.Medium;
import com.proxerme.library.enums.UserMediaProgress;
import com.proxerme.library.interfaces.IdItem;
import com.squareup.moshi.Json;
import org.jetbrains.annotations.NotNull;

/**
 * Entity holding all relevant information of an entry in the user's media list (Watched, watching).
 * This also includes comments by the user.
 *
 * @author Ruben Gees
 */
public class UserMediaListEntry implements IdItem {

    @Json(name = "id")
    private String id;
    @Json(name = "name")
    private String name;
    @Json(name = "count")
    private int episodeCount;
    @Json(name = "medium")
    private Medium medium;
    @Json(name = "estate")
    private MediaState state;
    @Json(name = "cid")
    private String commentId;
    @Json(name = "comment")
    private String comment;
    @Json(name = "state")
    private UserMediaProgress mediaProgress;
    @Json(name = "episode")
    private int episode;
    @Json(name = "rating")
    private int rating;

    private UserMediaListEntry() {

    }

    /**
     * The constructor.
     *
     * @param id            The id of the entry.
     * @param name          The name.
     * @param episodeCount  The amount of episodes, the media has.
     * @param medium        The medium of the entry.
     * @param state         The state.
     * @param commentId     The id of the comment.
     * @param comment       The content of the comment.
     * @param mediaProgress The progress the user has made for this media.
     * @param episode       The episode the user is at currently. The user does not need to have a
     *                      comment for this field to be set.
     * @param rating        The rating of the media. This ranges from 1 to 10. If the value is 0,
     *                      the user haven't rated the media yet.
     */
    public UserMediaListEntry(@NotNull final String id, @NotNull final String name, final int episodeCount,
                              @NotNull final Medium medium, final MediaState state, @NotNull final String commentId,
                              @NotNull final String comment, @NotNull final UserMediaProgress mediaProgress,
                              final int episode, final int rating) {
        this.id = id;
        this.name = name;
        this.episodeCount = episodeCount;
        this.medium = medium;
        this.state = state;
        this.commentId = commentId;
        this.comment = comment;
        this.mediaProgress = mediaProgress;
        this.episode = episode;
        this.rating = rating;
    }

    /**
     * Returns the id of the entry.
     *
     * @return The id.
     */
    @NotNull
    @Override
    public String getId() {
        return id;
    }

    /**
     * Returns the name.
     *
     * @return The name.
     */
    @NotNull
    public String getName() {
        return name;
    }

    /**
     * Returns the amount of episodes. Those don't need to be uploaded.
     *
     * @return The amount of episodes.
     */
    public int getEpisodeCount() {
        return episodeCount;
    }

    /**
     * Returns the medium of the entry.
     *
     * @return The medium.
     */
    @NotNull
    public Medium getMedium() {
        return medium;
    }

    /**
     * Returns the state of the entry.
     *
     * @return The state.
     */
    public MediaState getState() {
        return state;
    }

    /**
     * Returns the id of the associated comment.
     *
     * @return The id of the comment.
     */
    @NotNull
    public String getCommentId() {
        return commentId;
    }

    /**
     * Returns the comment content.
     *
     * @return The comment content.
     */
    @NotNull
    public String getComment() {
        return comment;
    }

    /**
     * Returns the progress the user has made.
     *
     * @return The progress.
     */
    public UserMediaProgress getMediaProgress() {
        return mediaProgress;
    }

    /**
     * Returns the last episode the user has watched.
     *
     * @return The episode.
     */
    public int getEpisode() {
        return episode;
    }

    /**
     * Returns the rating of the user's comment. If the user has no comment this equals to 0.
     *
     * @return The rating.
     */
    public int getRating() {
        return rating;
    }

    @SuppressWarnings("SimplifiableIfStatement")
    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final UserMediaListEntry that = (UserMediaListEntry) o;

        if (episodeCount != that.episodeCount) return false;
        if (episode != that.episode) return false;
        if (rating != that.rating) return false;
        if (!id.equals(that.id)) return false;
        if (!name.equals(that.name)) return false;
        if (medium != that.medium) return false;
        if (state != that.state) return false;
        if (!commentId.equals(that.commentId)) return false;
        if (!comment.equals(that.comment)) return false;
        return mediaProgress == that.mediaProgress;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + episodeCount;
        result = 31 * result + medium.hashCode();
        result = 31 * result + state.hashCode();
        result = 31 * result + commentId.hashCode();
        result = 31 * result + comment.hashCode();
        result = 31 * result + mediaProgress.hashCode();
        result = 31 * result + episode;
        result = 31 * result + rating;
        return result;
    }
}
