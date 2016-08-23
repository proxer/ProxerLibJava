package com.proxerme.library.connection.user.entitiy;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;

import com.proxerme.library.interfaces.IdItem;
import com.proxerme.library.interfaces.ImageItem;
import com.squareup.moshi.Json;

/**
 * Entity holding all basic info of a {@link User}.
 *
 * @author Ruben Gees
 */
public class UserInfo implements Parcelable, IdItem, ImageItem {

    public static final Parcelable.Creator<UserInfo> CREATOR = new Parcelable.Creator<UserInfo>() {
        @Override
        public UserInfo createFromParcel(Parcel source) {
            return new UserInfo(source);
        }

        @Override
        public UserInfo[] newArray(int size) {
            return new UserInfo[size];
        }
    };

    @Json(name = "uid")
    private String id;
    @Json(name = "username")
    private String username;
    @Json(name = "avatar")
    private String imageId;
    @Json(name = "status")
    private String status;
    @Json(name = "status_time")
    private long lastStatusChange;
    @Json(name = "points_uploads")
    private int uploadPoints;
    @Json(name = "points_forum")
    private int forumPoints;
    @Json(name = "points_anime")
    private int animePoints;
    @Json(name = "points_manga")
    private int mangaPoints;
    @Json(name = "points_info")
    private int infoPoints;
    @Json(name = "points_misc")
    private int miscPoints;

    private UserInfo() {

    }

    /**
     * The constructor.
     *
     * @param id               The id.
     * @param username         The username.
     * @param imageId          The id of the image. Can be used with the
     *                         {@link com.proxerme.library.info.ProxerUrlHolder#getUserImageUrl(String)}
     *                         method.
     * @param status           The status.
     * @param lastStatusChange The date of the last status change as an unix timestamp.
     * @param uploadPoints     The rank points for uploads.
     * @param forumPoints      The rank points for forum posts.
     * @param animePoints      The rank points for anime.
     * @param mangaPoints      The rank points for manga.
     * @param infoPoints       The rank points for entries in the info section.
     * @param miscPoints       The rank points awarded for various actions (e.g. donations).
     */
    public UserInfo(@NonNull String id, @NonNull String username, @NonNull String imageId,
                    @NonNull String status, long lastStatusChange,
                    @IntRange(from = 0) int uploadPoints, @IntRange(from = 0) int forumPoints,
                    @IntRange(from = 0) int animePoints, @IntRange(from = 0) int mangaPoints,
                    @IntRange(from = 0) int infoPoints, @IntRange(from = 0) int miscPoints) {
        this.id = id;
        this.username = username;
        this.imageId = imageId;
        this.status = status;
        this.lastStatusChange = lastStatusChange;
        this.uploadPoints = uploadPoints;
        this.forumPoints = forumPoints;
        this.animePoints = animePoints;
        this.mangaPoints = mangaPoints;
        this.infoPoints = infoPoints;
        this.miscPoints = miscPoints;
    }

    protected UserInfo(Parcel in) {
        this.id = in.readString();
        this.username = in.readString();
        this.imageId = in.readString();
        this.status = in.readString();
        this.lastStatusChange = in.readLong();
        this.uploadPoints = in.readInt();
        this.forumPoints = in.readInt();
        this.animePoints = in.readInt();
        this.mangaPoints = in.readInt();
        this.infoPoints = in.readInt();
        this.miscPoints = in.readInt();
    }

    /**
     * Returns the id of the user.
     *
     * @return The id.
     */
    @NonNull
    @Override
    public String getId() {
        return id;
    }

    /**
     * Returns the username.
     *
     * @return The username.
     */
    @NonNull
    public String getUsername() {
        return username;
    }

    /**
     * Returns the id of the avatar image.
     *
     * @return The id.
     */
    @NonNull
    @Override
    public String getImageId() {
        return imageId;
    }

    /**
     * Returns the status of the user. Might be empty if the user did not set a status.
     *
     * @return The status.
     */
    @NonNull
    public String getStatus() {
        return status;
    }

    /**
     * Returns the date of the last status change. This is 0 if the user has no status.
     *
     * @return The date as an unix timestamp.
     */
    public long getLastStatusChange() {
        return lastStatusChange;
    }

    /**
     * Returns the rank points for uploads.
     *
     * @return The points.
     */
    @IntRange(from = 0)
    public int getUploadPoints() {
        return uploadPoints;
    }

    /**
     * Returns the rank points for forum points.
     *
     * @return The points.
     */
    @IntRange(from = 0)
    public int getForumPoints() {
        return forumPoints;
    }

    /**
     * Returns the rank points for anime.
     *
     * @return The points.
     */
    @IntRange(from = 0)
    public int getAnimePoints() {
        return animePoints;
    }

    /**
     * Returns the rank points for manga.
     *
     * @return The points.
     */
    @IntRange(from = 0)
    public int getMangaPoints() {
        return mangaPoints;
    }

    /**
     * Returns the rank points for entries in the info section.
     *
     * @return The points.
     */
    @IntRange(from = 0)
    public int getInfoPoints() {
        return infoPoints;
    }

    /**
     * Returns the rank points awarded for various actions.
     *
     * @return The points.
     */
    @IntRange(from = 0)
    public int getMiscPoints() {
        return miscPoints;
    }

    @SuppressWarnings("SimplifiableIfStatement")
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserInfo userInfo = (UserInfo) o;

        if (lastStatusChange != userInfo.lastStatusChange) return false;
        if (uploadPoints != userInfo.uploadPoints) return false;
        if (forumPoints != userInfo.forumPoints) return false;
        if (animePoints != userInfo.animePoints) return false;
        if (mangaPoints != userInfo.mangaPoints) return false;
        if (infoPoints != userInfo.infoPoints) return false;
        if (miscPoints != userInfo.miscPoints) return false;
        if (!id.equals(userInfo.id)) return false;
        if (!username.equals(userInfo.username)) return false;
        if (!imageId.equals(userInfo.imageId)) return false;
        return status.equals(userInfo.status);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + username.hashCode();
        result = 31 * result + imageId.hashCode();
        result = 31 * result + status.hashCode();
        result = 31 * result + (int) (lastStatusChange ^ (lastStatusChange >>> 32));
        result = 31 * result + uploadPoints;
        result = 31 * result + forumPoints;
        result = 31 * result + animePoints;
        result = 31 * result + mangaPoints;
        result = 31 * result + infoPoints;
        result = 31 * result + miscPoints;
        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.username);
        dest.writeString(this.imageId);
        dest.writeString(this.status);
        dest.writeLong(this.lastStatusChange);
        dest.writeInt(this.uploadPoints);
        dest.writeInt(this.forumPoints);
        dest.writeInt(this.animePoints);
        dest.writeInt(this.mangaPoints);
        dest.writeInt(this.infoPoints);
        dest.writeInt(this.miscPoints);
    }
}