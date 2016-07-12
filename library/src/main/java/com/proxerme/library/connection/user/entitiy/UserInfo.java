package com.proxerme.library.connection.user.entitiy;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;

import com.afollestad.bridge.annotations.Body;
import com.proxerme.library.interfaces.IdItem;
import com.proxerme.library.interfaces.ImageItem;

/**
 * TODO: Describe class
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
    @Body(name = "uid")
    String id;
    @Body(name = "username")
    String username;
    @Body(name = "avatar")
    String imageId;
    @Body(name = "status")
    String status;
    @Body(name = "status_time")
    long lastStatusChange;
    @Body(name = "points_uploads")
    int uploadPoints;
    @Body(name = "points_anime")
    int animePoints;
    @Body(name = "points_manga")
    int mangaPoints;
    @Body(name = "points_info")
    int infoPoints;
    @Body(name = "points_misc")
    int miscPoints;

    UserInfo() {

    }

    public UserInfo(@NonNull String id, @NonNull String username, @NonNull String imageId,
                    @NonNull String status, long lastStatusChange,
                    @IntRange(from = 0) int uploadPoints, @IntRange(from = 0) int animePoints,
                    @IntRange(from = 0) int mangaPoints, @IntRange(from = 0) int infoPoints,
                    @IntRange(from = 0) int miscPoints) {
        this.id = id;
        this.username = username;
        this.imageId = imageId;
        this.status = status;
        this.lastStatusChange = lastStatusChange;
        this.uploadPoints = uploadPoints;
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
        this.animePoints = in.readInt();
        this.mangaPoints = in.readInt();
        this.infoPoints = in.readInt();
        this.miscPoints = in.readInt();
    }

    @NonNull
    @Override
    public String getId() {
        return id;
    }

    @NonNull
    public String getUsername() {
        return username;
    }

    @NonNull
    @Override
    public String getImageId() {
        return imageId;
    }

    @NonNull
    public String getStatus() {
        return status;
    }

    public long getLastStatusChange() {
        return lastStatusChange;
    }

    @IntRange(from = 0)
    public int getUploadPoints() {
        return uploadPoints;
    }

    @IntRange(from = 0)
    public int getAnimePoints() {
        return animePoints;
    }

    @IntRange(from = 0)
    public int getMangaPoints() {
        return mangaPoints;
    }

    @IntRange(from = 0)
    public int getInfoPoints() {
        return infoPoints;
    }

    @IntRange(from = 0)
    public int getMiscPoints() {
        return miscPoints;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserInfo userInfo = (UserInfo) o;

        if (lastStatusChange != userInfo.lastStatusChange) return false;
        if (uploadPoints != userInfo.uploadPoints) return false;
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
        dest.writeInt(this.animePoints);
        dest.writeInt(this.mangaPoints);
        dest.writeInt(this.infoPoints);
        dest.writeInt(this.miscPoints);
    }
}
