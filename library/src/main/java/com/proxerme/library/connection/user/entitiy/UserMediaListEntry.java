package com.proxerme.library.connection.user.entitiy;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;

import com.afollestad.bridge.annotations.Body;
import com.proxerme.library.parameters.MediumParameter.Medium;

/**
 * Entity holding all relevant information of an entry in the User's media list (Watched, watching).
 * This also includes comments by the User.
 *
 * @author Ruben Gees
 */

public class UserMediaListEntry implements Parcelable {

    public static final Parcelable.Creator<UserMediaListEntry> CREATOR = new Parcelable.Creator<UserMediaListEntry>() {
        @Override
        public UserMediaListEntry createFromParcel(Parcel source) {
            return new UserMediaListEntry(source);
        }

        @Override
        public UserMediaListEntry[] newArray(int size) {
            return new UserMediaListEntry[size];
        }
    };
    @Body(name = "id")
    String id;
    @Body(name = "name")
    String name;
    @Body(name = "count")
    int episodeCount;
    @Body(name = "medium")
    String medium;
    @Body(name = "estate")
    int state;
    @Body(name = "cid")
    String commentId;
    @Body(name = "comment")
    String comment;
    @Body(name = "state")
    int commentState;
    @Body(name = "episode")
    int commentEpisode;
    @Body(name = "rating")
    int commentRating;

    UserMediaListEntry() {

    }

    /**
     * The constructor.
     *
     * @param id             The id of the entry.
     * @param name           The name.
     * @param episodeCount   The amount of episodes, the media has.
     * @param medium         The medium of the entry.
     * @param state          The state.
     * @param commentId      The id of the comment.
     * @param comment        The content of the comment.
     * @param commentState   The state of the comment.
     * @param commentEpisode The episode the User is at currently. The User does not have to have a
     *                       comment for this field to be set.
     * @param commentRating  The rating of the media. This ranges from 1 to 10. If the value is 0,
     *                       the user haven't rated the media yet.
     */
    public UserMediaListEntry(@NonNull String id, @NonNull String name,
                              @IntRange(from = 0) int episodeCount, @NonNull @Medium String medium,
                              int state, @NonNull String commentId, @NonNull String comment,
                              int commentState, @IntRange(from = 0) int commentEpisode,
                              @IntRange(from = 0, to = 10) int commentRating) {
        this.id = id;
        this.name = name;
        this.episodeCount = episodeCount;
        this.medium = medium;
        this.state = state;
        this.commentId = commentId;
        this.comment = comment;
        this.commentState = commentState;
        this.commentEpisode = commentEpisode;
        this.commentRating = commentRating;
    }

    protected UserMediaListEntry(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.episodeCount = in.readInt();
        this.medium = in.readString();
        this.state = in.readInt();
        this.commentId = in.readString();
        this.comment = in.readString();
        this.commentState = in.readInt();
        this.commentEpisode = in.readInt();
        this.commentRating = in.readInt();
    }

    /**
     * Returns the id of the entry.
     *
     * @return The enty.
     */
    @NonNull
    public String getId() {
        return id;
    }

    /**
     * Returns the name.
     *
     * @return The name.
     */
    @NonNull
    public String getName() {
        return name;
    }

    /**
     * Returns the amount of episodes. Those don't need to be uploaded.
     *
     * @return The amount of episodes.
     */
    @IntRange(from = 0)
    public int getEpisodeCount() {
        return episodeCount;
    }

    /**
     * Returns the medium of the entry. (Animeseries, OVA, ...).
     *
     * @return The medium.
     */
    @NonNull
    @Medium
    public String getMedium() {
        return medium;
    }

    /**
     * Returns the state of the entry.
     *
     * @return The state.
     */
    public int getState() {
        return state;
    }

    /**
     * Returns the id of the associated comment.
     *
     * @return The id of the comment.
     */
    @NonNull
    public String getCommentId() {
        return commentId;
    }

    /**
     * Returns the comment content.
     *
     * @return The comment content.
     */
    @NonNull
    public String getComment() {
        return comment;
    }

    /**
     * Returns the state of the comment.
     *
     * @return The state.
     */
    public int getCommentState() {
        return commentState;
    }

    /**
     * Returns the last episode, the user has watched.
     *
     * @return The episode.
     */
    @IntRange(from = 0)
    public int getCommentEpisode() {
        return commentEpisode;
    }

    /**
     * Returns the rating of the User's comment. If the user has no comment this equals to 0.
     *
     * @return THe rating.
     */
    @IntRange(from = 0, to = 10)
    public int getCommentRating() {
        return commentRating;
    }

    @SuppressWarnings("SimplifiableIfStatement")
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserMediaListEntry that = (UserMediaListEntry) o;

        if (episodeCount != that.episodeCount) return false;
        if (commentEpisode != that.commentEpisode) return false;
        if (commentRating != that.commentRating) return false;
        if (!id.equals(that.id)) return false;
        if (!name.equals(that.name)) return false;
        if (!medium.equals(that.medium)) return false;
        if (state != that.state) return false;
        if (!commentId.equals(that.commentId)) return false;
        if (!comment.equals(that.comment)) return false;
        return commentState != that.commentState;

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + episodeCount;
        result = 31 * result + medium.hashCode();
        result = 31 * result + state;
        result = 31 * result + commentId.hashCode();
        result = 31 * result + comment.hashCode();
        result = 31 * result + commentState;
        result = 31 * result + commentEpisode;
        result = 31 * result + commentRating;
        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeInt(this.episodeCount);
        dest.writeString(this.medium);
        dest.writeInt(this.state);
        dest.writeString(this.commentId);
        dest.writeString(this.comment);
        dest.writeInt(this.commentState);
        dest.writeInt(this.commentEpisode);
        dest.writeInt(this.commentRating);
    }
}
