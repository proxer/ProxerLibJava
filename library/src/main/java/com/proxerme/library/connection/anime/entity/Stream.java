package com.proxerme.library.connection.anime.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.proxerme.library.interfaces.IdItem;
import com.proxerme.library.interfaces.ImageItem;
import com.proxerme.library.interfaces.TimeItem;
import com.squareup.moshi.Json;

/**
 * Entity representing a single streaming option of an anime.
 *
 * @author Ruben Gees
 */
public class Stream implements IdItem, ImageItem, TimeItem, Parcelable {

    public static final Parcelable.Creator<Stream> CREATOR = new Parcelable.Creator<Stream>() {
        @Override
        public Stream createFromParcel(Parcel source) {
            return new Stream(source);
        }

        @Override
        public Stream[] newArray(int size) {
            return new Stream[size];
        }
    };

    @Json(name = "id")
    private String id;
    @Json(name = "type")
    private String hoster;
    @Json(name = "name")
    private String hosterName;
    @Json(name = "img")
    private String imageId;
    @Json(name = "uploader")
    private String uploaderId;
    @Json(name = "username")
    private String uploader;
    @Json(name = "timestamp")
    private long time;
    @Json(name = "tid")
    private String subgroupId;
    @Json(name = "tname")
    private String subgroup;
    @Json(name = "htype")
    private String hosterType;

    /**
     * The constructor.
     *
     * @param id         The id of the stream.
     * @param hoster     The hoster.
     * @param hosterName The name of the hoster.
     * @param imageId    The image.
     * @param uploaderId THe id of the uploader.
     * @param uploader   The username of the uploader.
     * @param time       The time this streams was linked.
     * @param subgroupId The id of the subgroup if existent
     * @param subgroup   The name of the subgroup if existent.
     * @param hosterType The type of the hoster.
     */
    public Stream(@NonNull String id, @NonNull String hoster, @NonNull String hosterName,
                  @NonNull String imageId, @NonNull String uploaderId, @NonNull String uploader,
                  long time, @Nullable String subgroupId, @Nullable String subgroup,
                  @NonNull String hosterType) {
        this.id = id;
        this.hoster = hoster;
        this.hosterName = hosterName;
        this.imageId = imageId;
        this.uploaderId = uploaderId;
        this.uploader = uploader;
        this.time = time;
        this.subgroupId = subgroupId;
        this.subgroup = subgroup;
        this.hosterType = hosterType;
    }

    protected Stream(Parcel in) {
        this.id = in.readString();
        this.hoster = in.readString();
        this.hosterName = in.readString();
        this.imageId = in.readString();
        this.uploaderId = in.readString();
        this.uploader = in.readString();
        this.time = in.readLong();
        this.subgroupId = in.readString();
        this.subgroup = in.readString();
        this.hosterType = in.readString();
    }

    /**
     * Returns the id.
     *
     * @return The id.
     */
    @NonNull
    @Override
    public String getId() {
        return id;
    }

    /**
     * Returns the hoster.
     *
     * @return The hoster.
     */
    @NonNull
    public String getHoster() {
        return hoster;
    }

    /**
     * Returns the name of the hoster.
     *
     * @return The name of the hoster.
     */
    @NonNull
    public String getHosterName() {
        return hosterName;
    }

    /**
     * Returns the image.
     *
     * @return The image.
     */
    @NonNull
    @Override
    public String getImageId() {
        return imageId;
    }

    /**
     * Returns the id of the uploader.
     *
     * @return The id of the uploader.
     */
    @NonNull
    public String getUploaderId() {
        return uploaderId;
    }

    /**
     * Returns the username of the uploader.
     *
     * @return The username of the uploader.
     */
    @NonNull
    public String getUploader() {
        return uploader;
    }

    /**
     * Returns the time this stream was linked.
     *
     * @return The time.
     */
    @Override
    public long getTime() {
        return time;
    }

    /**
     * Returns the id of the subgroup or null if not present.
     *
     * @return The id.
     */
    @Nullable
    public String getSubgroupId() {
        return subgroupId;
    }

    /**
     * Returns the name of the subgroup or null if not present.
     *
     * @return The name.
     */
    @Nullable
    public String getSubgroup() {
        return subgroup;
    }

    /**
     * Returns the type of the hoster.
     *
     * @return The type of the hoster.
     */
    @NonNull
    public String getHosterType() {
        return hosterType;
    }

    @SuppressWarnings("SimplifiableIfStatement")
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Stream stream = (Stream) o;

        if (time != stream.time) return false;
        if (!id.equals(stream.id)) return false;
        if (!hoster.equals(stream.hoster)) return false;
        if (!hosterName.equals(stream.hosterName)) return false;
        if (!imageId.equals(stream.imageId)) return false;
        if (!uploaderId.equals(stream.uploaderId)) return false;
        if (!uploader.equals(stream.uploader)) return false;
        if (subgroupId != null ? !subgroupId.equals(stream.subgroupId) : stream.subgroupId != null)
            return false;
        if (subgroup != null ? !subgroup.equals(stream.subgroup) : stream.subgroup != null)
            return false;
        return hosterType.equals(stream.hosterType);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + hoster.hashCode();
        result = 31 * result + hosterName.hashCode();
        result = 31 * result + imageId.hashCode();
        result = 31 * result + uploaderId.hashCode();
        result = 31 * result + uploader.hashCode();
        result = 31 * result + (int) (time ^ (time >>> 32));
        result = 31 * result + (subgroupId != null ? subgroupId.hashCode() : 0);
        result = 31 * result + (subgroup != null ? subgroup.hashCode() : 0);
        result = 31 * result + hosterType.hashCode();
        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.hoster);
        dest.writeString(this.hosterName);
        dest.writeString(this.imageId);
        dest.writeString(this.uploaderId);
        dest.writeString(this.uploader);
        dest.writeLong(this.time);
        dest.writeString(this.subgroupId);
        dest.writeString(this.subgroup);
        dest.writeString(this.hosterType);
    }
}
