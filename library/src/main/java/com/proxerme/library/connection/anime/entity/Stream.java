package com.proxerme.library.connection.anime.entity;

import android.support.annotation.NonNull;

import com.proxerme.library.interfaces.IdItem;
import com.proxerme.library.interfaces.ImageItem;
import com.proxerme.library.interfaces.TimeItem;
import com.squareup.moshi.Json;

/**
 * TODO: Describe class
 *
 * @author Ruben Gees
 */
public class Stream implements IdItem, ImageItem, TimeItem {

    @Json(name = "id")
    private String id;
    @Json(name = "htype")
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
    @Json(name = "tnime")
    private String subgroup;
    @Json(name = "htype")
    private String hosterType;

    public Stream(@NonNull String id, @NonNull String hoster, @NonNull String hosterName,
                  @NonNull String imageId, @NonNull String uploaderId, @NonNull String uploader,
                  long time, @NonNull String subgroupId, @NonNull String subgroup,
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

    @NonNull
    @Override
    public String getId() {
        return id;
    }

    @NonNull
    public String getHoster() {
        return hoster;
    }

    @NonNull
    public String getHosterName() {
        return hosterName;
    }

    @NonNull
    @Override
    public String getImageId() {
        return imageId;
    }

    @NonNull
    public String getUploaderId() {
        return uploaderId;
    }

    @NonNull
    public String getUploader() {
        return uploader;
    }

    @Override
    public long getTime() {
        return time;
    }

    @NonNull
    public String getSubgroupId() {
        return subgroupId;
    }

    @NonNull
    public String getSubgroup() {
        return subgroup;
    }

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
        if (!subgroupId.equals(stream.subgroupId)) return false;
        if (!subgroup.equals(stream.subgroup)) return false;
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
        result = 31 * result + subgroupId.hashCode();
        result = 31 * result + subgroup.hashCode();
        result = 31 * result + hosterType.hashCode();
        return result;
    }
}
