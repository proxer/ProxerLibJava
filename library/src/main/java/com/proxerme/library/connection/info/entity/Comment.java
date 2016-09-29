package com.proxerme.library.connection.info.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;

import com.proxerme.library.interfaces.IdItem;
import com.proxerme.library.interfaces.ImageItem;
import com.proxerme.library.parameters.CommentStateParameter.CommentState;
import com.squareup.moshi.Json;

/**
 * The complete details of a comment of an entry.
 *
 * @author Desnoo
 */
public class Comment implements Parcelable, IdItem, ImageItem {

    public static final Creator<Comment> CREATOR = new Creator<Comment>() {
        @Override
        public Comment createFromParcel(Parcel in) {
            return new Comment(in);
        }

        @Override
        public Comment[] newArray(int size) {
            return new Comment[size];
        }
    };

    @Json(name = "id")
    private String id;
    @Json(name = "tid")
    private String entryId;
    @Json(name = "uid")
    private String userId;
    @Json(name = "type")
    private String type;
    @Json(name = "state")
    private int state;
    @Json(name = "data")
    private RatingDetails ratingDetails;
    @Json(name = "comment")
    private String comment;
    @Json(name = "rating")
    private int rating;
    @Json(name = "episode")
    private int episode;
    @Json(name = "positive")
    private int helpfulVotes;
    @Json(name = "timestamp")
    private long time;
    @Json(name = "username")
    private String username;
    @Json(name = "avatar")
    private String imageId;

    /**
     * Private constructor used by moshi.
     */
    private Comment() {
    }

    /**
     * The constructor.
     *
     * @param id           The comment id.
     * @param entryId      The entry id.
     * @param userId       The user id.
     * @param type         The comment type.
     * @param state        The comment state type {@link CommentState}
     * @param ratingDetails The rating details.
     * @param comment      The comment text.
     * @param rating       The rating value.
     * @param episode      The highest episode the user watched.
     * @param helpfulVotes The number of users that voted this comment up.
     * @param time         The comment creation time.
     * @param username     The name of the user.
     * @param imageId      The image id.
     */
    public Comment(@NonNull String id, @NonNull String entryId, @NonNull String userId,
                   @NonNull String type, @CommentState int state,
                   @NonNull RatingDetails ratingDetails, @NonNull String comment,
                   @IntRange(from = 0, to = 10) int rating, @IntRange(from = 0) int episode,
                   @IntRange(from = 0) int helpfulVotes, long time, @NonNull String username,
                   @NonNull String imageId) {
        this.id = id;
        this.entryId = entryId;
        this.userId = userId;
        this.type = type;
        this.state = state;
        this.ratingDetails = ratingDetails;
        this.comment = comment;
        this.rating = rating;
        this.episode = episode;
        this.helpfulVotes = helpfulVotes;
        this.time = time;
        this.username = username;
        this.imageId = imageId;
    }

    /**
     * The constructor to read in the parcel.
     *
     * @param in The parcel to parse.
     */
    protected Comment(Parcel in) {
        id = in.readString();
        entryId = in.readString();
        userId = in.readString();
        type = in.readString();
        state = in.readInt();
        ratingDetails = in.readParcelable(RatingDetails.class.getClassLoader());
        comment = in.readString();
        rating = in.readInt();
        episode = in.readInt();
        helpfulVotes = in.readInt();
        time = in.readLong();
        username = in.readString();
        imageId = in.readString();
    }

    @NonNull
    @Override
    public String getId() {
        return id;
    }

    /**
     * Returns the EntryId.
     *
     * @return The EntryId.
     **/
    @NonNull
    public String getEntryId() {
        return entryId;
    }

    /**
     * Returns the UserId.
     *
     * @return The UserId.
     **/
    @NonNull
    public String getUserId() {
        return userId;
    }

    /**
     * Returns the Type.
     *
     * @return The Type.
     **/
    @NonNull
    public String getType() {
        return type;
    }

    /**
     * Returns the State.
     *
     * @return The State.
     **/
    @CommentState
    public int getState() {
        return state;
    }

    /**
     * Returns the RatingDetails.
     *
     * @return The RatingDetails.
     **/
    @NonNull
    public RatingDetails getRatingDetails() {
        return ratingDetails;
    }

    /**
     * Returns the Comment.
     *
     * @return The Comment.
     **/
    @NonNull
    public String getComment() {
        return comment;
    }

    /**
     * Returns the Rating.
     *
     * @return The Rating.
     **/
    @IntRange(from = 0, to = 10)
    public int getRating() {
        return rating;
    }

    /**
     * Returns the Episode.
     *
     * @return The Episode.
     **/
    @IntRange(from = 0)
    public int getEpisode() {
        return episode;
    }

    /**
     * Returns the HelpfulVotes.
     *
     * @return The HelpfulVotes.
     **/
    @IntRange(from = 0)
    public int getHelpfulVotes() {
        return helpfulVotes;
    }

    /**
     * Returns the Time.
     *
     * @return The Time.
     **/
    public long getTime() {
        return time;
    }

    /**
     * Returns the Username.
     *
     * @return The Username.
     **/
    @NonNull
    public String getUsername() {
        return username;
    }

    @NonNull
    @Override
    public String getImageId() {
        return imageId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(entryId);
        parcel.writeString(userId);
        parcel.writeString(type);
        parcel.writeInt(state);
        parcel.writeParcelable(ratingDetails, i);
        parcel.writeString(comment);
        parcel.writeInt(rating);
        parcel.writeInt(episode);
        parcel.writeInt(helpfulVotes);
        parcel.writeLong(time);
        parcel.writeString(username);
        parcel.writeString(imageId);
    }

    @SuppressWarnings("SimplifiableIfStatement")
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Comment comment1 = (Comment) o;

        if (state != comment1.state) return false;
        if (rating != comment1.rating) return false;
        if (episode != comment1.episode) return false;
        if (helpfulVotes != comment1.helpfulVotes) return false;
        if (time != comment1.time) return false;
        if (!id.equals(comment1.id)) return false;
        if (!entryId.equals(comment1.entryId)) return false;
        if (!userId.equals(comment1.userId)) return false;
        if (!type.equals(comment1.type)) return false;
        if (!ratingDetails.equals(comment1.ratingDetails)) return false;
        if (!comment.equals(comment1.comment)) return false;
        if (!username.equals(comment1.username)) return false;
        return imageId.equals(comment1.imageId);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + entryId.hashCode();
        result = 31 * result + userId.hashCode();
        result = 31 * result + type.hashCode();
        result = 31 * result + state;
        result = 31 * result + ratingDetails.hashCode();
        result = 31 * result + comment.hashCode();
        result = 31 * result + rating;
        result = 31 * result + episode;
        result = 31 * result + helpfulVotes;
        result = 31 * result + (int) (time ^ (time >>> 32));
        result = 31 * result + username.hashCode();
        result = 31 * result + imageId.hashCode();
        return result;
    }
}
