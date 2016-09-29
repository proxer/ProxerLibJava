package com.proxerme.library.connection.info.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.proxerme.library.interfaces.IdItem;
import com.proxerme.library.interfaces.TimeItem;
import com.proxerme.library.util.Utils;
import com.squareup.moshi.Json;

/**
 * Entity representing a single tag.
 *
 * @author Ruben Gees
 */
public class Tag implements Parcelable, IdItem, TimeItem {

    public static final Parcelable.Creator<Tag> CREATOR = new Parcelable.Creator<Tag>() {
        @Override
        public Tag createFromParcel(Parcel source) {
            return new Tag(source);
        }

        @Override
        public Tag[] newArray(int size) {
            return new Tag[size];
        }
    };

    @Json(name = "tid")
    private String id;
    @Json(name = "id")
    private String entryId;
    @Json(name = "timestamp")
    private String time;
    @Json(name = "rate_flag")
    private int rateFlag;
    @Json(name = "spoiler_flag")
    private int spoilerFlag;
    @Json(name = "tag")
    private String name;
    @Json(name = "description")
    private String description;

    private Tag() {
    }

    /**
     * The constructor.
     *
     * @param id          The id of the tag.
     * @param entryId     The id of the entry.
     * @param time        The time this tag was associated.
     * @param rateFlag    1 of the entry is fitting, 0 otherwise.
     * @param spoilerFlag 1 if this is a spoiler, 0 otherwise.
     * @param name        The name.
     * @param description The description.
     */
    public Tag(@NonNull String id, @NonNull String entryId, @NonNull String time, int rateFlag,
               int spoilerFlag, @NonNull String name, @NonNull String description) {
        this.id = id;
        this.entryId = entryId;
        this.time = time;
        this.rateFlag = rateFlag;
        this.spoilerFlag = spoilerFlag;
        this.name = name;
        this.description = description;
    }

    protected Tag(Parcel in) {
        this.id = in.readString();
        this.entryId = in.readString();
        this.time = in.readString();
        this.rateFlag = in.readInt();
        this.spoilerFlag = in.readInt();
        this.name = in.readString();
        this.description = in.readString();
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
     * Returns the entry id.
     *
     * @return The entry id.
     */
    @NonNull
    public String getEntryId() {
        return entryId;
    }

    /**
     * Returns the time as a unix timestamp.
     *
     * @return The time.
     */
    @Override
    public long getTime() {
        return Utils.timestampToUnixTime(time);
    }

    /**
     * Returns if this tag is rated.
     *
     * @return True, if it is rated.
     */
    public boolean isRated() {
        return rateFlag == 1;
    }

    /**
     * Returns if this tag is a spoiler.
     *
     * @return True, if it is a spoiler.
     */
    public boolean isSpoiler() {
        return spoilerFlag == 1;
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
     * Returns the description.
     *
     * @return The description.
     */
    @NonNull
    public String getDescription() {
        return description;
    }

    @SuppressWarnings("SimplifiableIfStatement")
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tag tag = (Tag) o;

        if (rateFlag != tag.rateFlag) return false;
        if (spoilerFlag != tag.spoilerFlag) return false;
        if (!id.equals(tag.id)) return false;
        if (!entryId.equals(tag.entryId)) return false;
        if (!time.equals(tag.time)) return false;
        if (!name.equals(tag.name)) return false;
        return description.equals(tag.description);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + entryId.hashCode();
        result = 31 * result + time.hashCode();
        result = 31 * result + rateFlag;
        result = 31 * result + spoilerFlag;
        result = 31 * result + name.hashCode();
        result = 31 * result + description.hashCode();
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
        dest.writeString(this.time);
        dest.writeInt(this.rateFlag);
        dest.writeInt(this.spoilerFlag);
        dest.writeString(this.name);
        dest.writeString(this.description);
    }
}
