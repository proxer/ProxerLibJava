package com.proxerme.library.connection.info.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.afollestad.bridge.annotations.Body;

/**
 * Represents the core data of an Entry.
 *
 * @author Desnoo
 */
public class EntryCore implements Parcelable {

    public static final Creator<EntryCore> CREATOR = new Creator<EntryCore>() {
        @Override
        public EntryCore createFromParcel(Parcel in) {
            return new EntryCore(in);
        }

        @Override
        public EntryCore[] newArray(int size) {
            return new EntryCore[size];
        }
    };

    @Body(name = "id")
    int id;

    @Body(name = "name")
    String name;

    @Body(name = "genre")
    String genre;

    @Body(name = "fsk")
    String fsk;

    @Body(name = "description")
    String description;

    @Body(name = "medium")
    String type;

    /**
     * The overall number of episodes/chapters. It does not include not available episodes.
     */
    @Body(name = "count")
    int count;

    @Body(name = "state")
    int state;

    /**
     * The sum of all ratings.
     */
    @Body(name = "rate_sum")
    int rateSum;

    /**
     * The number of ratings.
     */
    @Body(name = "rate_count")
    int rateCount;

    /**
     * The number of views of this entry.
     */
    @Body(name = "clicks")
    int clicks;

    /**
     * The Category of this entry.
     */
    @Body(name = "category")
    String category;

    @Body(name = "license")
    int license;

    /**
     * Parser to retain the instance back from the parcel.
     *
     * @param in the parcel to parse.
     */
    protected EntryCore(Parcel in) {
        id = in.readInt();
        name = in.readString();
        genre = in.readString();
        fsk = in.readString();
        description = in.readString();
        type = in.readString();
        count = in.readInt();
        state = in.readInt();
        rateSum = in.readInt();
        rateCount = in.readInt();
        clicks = in.readInt();
        category = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(genre);
        dest.writeString(fsk);
        dest.writeString(description);
        dest.writeString(type);
        dest.writeInt(count);
        dest.writeInt(state);
        dest.writeInt(rateSum);
        dest.writeInt(rateCount);
        dest.writeInt(clicks);
        dest.writeString(category);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @SuppressWarnings("SimplifiableIfStatement")
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EntryCore entryCore = (EntryCore) o;

        if (id != entryCore.id) return false;
        if (count != entryCore.count) return false;
        if (state != entryCore.state) return false;
        if (rateSum != entryCore.rateSum) return false;
        if (rateCount != entryCore.rateCount) return false;
        if (clicks != entryCore.clicks) return false;
        if (!name.equals(entryCore.name)) return false;
        if (!genre.equals(entryCore.genre)) return false;
        if (!fsk.equals(entryCore.fsk)) return false;
        if (!description.equals(entryCore.description)) return false;
        if (!type.equals(entryCore.type)) return false;
        if (!category.equals(entryCore.category)) return false;
        return license == entryCore.license;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        result = 31 * result + genre.hashCode();
        result = 31 * result + fsk.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + type.hashCode();
        result = 31 * result + count;
        result = 31 * result + state;
        result = 31 * result + rateSum;
        result = 31 * result + rateCount;
        result = 31 * result + clicks;
        result = 31 * result + category.hashCode();
        // uses the object reference, enums are special in this case
        result = 31 * result + license;
        return result;
    }
}
