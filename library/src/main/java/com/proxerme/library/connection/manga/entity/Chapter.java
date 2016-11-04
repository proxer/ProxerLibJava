package com.proxerme.library.connection.manga.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.proxerme.library.interfaces.IdItem;
import com.proxerme.library.interfaces.TimeItem;
import com.proxerme.library.util.Utils;
import com.squareup.moshi.Json;

import java.util.Arrays;

/**
 * Entity representing a single chapter of a maga.
 *
 * @author Ruben Gees
 */
public class Chapter implements IdItem, TimeItem, Parcelable {

    public static final Parcelable.Creator<Chapter> CREATOR = new Parcelable.Creator<Chapter>() {
        @Override
        public Chapter createFromParcel(Parcel source) {
            return new Chapter(source);
        }

        @Override
        public Chapter[] newArray(int size) {
            return new Chapter[size];
        }
    };

    @Json(name = "cid")
    private String id;
    @Json(name = "eid")
    private String entryId;
    @Json(name = "title")
    private String title;
    @Json(name = "uploader")
    private String uploaderId;
    @Json(name = "username")
    private String uploader;
    @Json(name = "timestamp")
    private long time;
    @Json(name = "tid")
    private String scangroupId;
    @Json(name = "tname")
    private String scangroup;
    @Json(name = "server")
    private String server;
    @Json(name = "pages")
    private String[][] pages;

    /**
     * The constructor.
     *
     * @param id          The id.
     * @param entryId     The id of the associated entry.
     * @param title       The title.
     * @param uploaderId  The id of the uploader.
     * @param uploader    The username of the uploader.
     * @param time        The time this chapter was uploaded as an unix timestamp.
     * @param scangroupId The id of the scangroup.
     * @param scangroup   The name of the scangroup.
     * @param server      The server this chapter is uploaded to.
     * @param pages       The pages this chapter has.
     */
    public Chapter(@NonNull String id, @NonNull String entryId, @NonNull String title,
                   @NonNull String uploaderId, @NonNull String uploader, long time,
                   @Nullable String scangroupId, @Nullable String scangroup, @NonNull String server,
                   @NonNull String[][] pages) {
        this.id = id;
        this.entryId = entryId;
        this.title = title;
        this.uploaderId = uploaderId;
        this.uploader = uploader;
        this.time = time;
        this.scangroupId = scangroupId;
        this.scangroup = scangroup;
        this.server = server;
        this.pages = pages;
    }

    protected Chapter(Parcel in) {
        this.id = in.readString();
        this.entryId = in.readString();
        this.title = in.readString();
        this.uploaderId = in.readString();
        this.uploader = in.readString();
        this.time = in.readLong();
        this.scangroupId = in.readString();
        this.scangroup = in.readString();
        this.server = in.readString();
        this.pages = Utils.toTwoDimensions(3, in.createStringArray());
    }

    /**
     * Returns the id.
     *
     * @return The id.
     */
    @Override
    @NonNull
    public String getId() {
        return id;
    }

    /**
     * Returns the id of the entry.
     *
     * @return The id of the entry.
     */
    @NonNull
    public String getEntryId() {
        return entryId;
    }

    /**
     * Returns the title.
     *
     * @return The title.
     */
    @NonNull
    public String getTitle() {
        return title;
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
     * Returns the name of the uploader.
     *
     * @return The name of the uploader.
     */
    @NonNull
    public String getUploader() {
        return uploader;
    }

    /**
     * Returns the upload time.
     *
     * @return The upload time.
     */
    @Override
    public long getTime() {
        return time;
    }

    /**
     * Returns the id of the scangroup.
     *
     * @return The id of the scangroup.
     */
    @Nullable
    public String getScangroupId() {
        return scangroupId;
    }

    /**
     * Returns the name of the scangroup.
     *
     * @return The scangroup.
     */
    @Nullable
    public String getScangroup() {
        return scangroup;
    }

    /**
     * Returns the server for this chapter.
     *
     * @return The server.
     */
    @NonNull
    public String getServer() {
        return server;
    }

    /**
     * Returns an array of pages this chapter has.
     *
     * @return The pages.
     */
    @NonNull
    public Page[] getPages() {
        Page[] result = new Page[pages.length];

        for (int i = 0; i < pages.length; i++) {
            String[] page = pages[i];

            result[i] = new Page(page[0], Integer.parseInt(page[1]), Integer.parseInt(page[2]));
        }

        return result;
    }

    @SuppressWarnings("SimplifiableIfStatement")
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Chapter chapter = (Chapter) o;

        if (time != chapter.time) return false;
        if (!id.equals(chapter.id)) return false;
        if (!entryId.equals(chapter.entryId)) return false;
        if (!title.equals(chapter.title)) return false;
        if (!uploaderId.equals(chapter.uploaderId)) return false;
        if (!uploader.equals(chapter.uploader)) return false;
        if (scangroupId != null ? !scangroupId.equals(chapter.scangroupId) : chapter.scangroupId != null)
            return false;
        if (scangroup != null ? !scangroup.equals(chapter.scangroup) : chapter.scangroup != null)
            return false;
        if (!server.equals(chapter.server)) return false;
        return Arrays.deepEquals(pages, chapter.pages);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + entryId.hashCode();
        result = 31 * result + title.hashCode();
        result = 31 * result + uploaderId.hashCode();
        result = 31 * result + uploader.hashCode();
        result = 31 * result + (int) (time ^ (time >>> 32));
        result = 31 * result + (scangroupId != null ? scangroupId.hashCode() : 0);
        result = 31 * result + (scangroup != null ? scangroup.hashCode() : 0);
        result = 31 * result + server.hashCode();
        result = 31 * result + Arrays.deepHashCode(pages);
        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.entryId);
        dest.writeString(this.title);
        dest.writeString(this.uploaderId);
        dest.writeString(this.uploader);
        dest.writeLong(this.time);
        dest.writeString(this.scangroupId);
        dest.writeString(this.scangroup);
        dest.writeString(this.server);
        dest.writeStringArray(Utils.toOneDimension(pages));
    }
}
