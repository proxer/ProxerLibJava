package com.proxerme.library.connection.ucp.entitiy;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;

import com.proxerme.library.interfaces.IdItem;
import com.proxerme.library.interfaces.TimeItem;
import com.proxerme.library.parameters.LanguageParameter.Language;
import com.proxerme.library.util.Utils;
import com.squareup.moshi.Json;

import static com.proxerme.library.parameters.CategoryParameter.Category;
import static com.proxerme.library.parameters.MediumParameter.Medium;

/**
 * Entity representing a single entry in the watch/read history of the user.
 *
 * @author Ruben Gees
 */
public class HistoryEntry implements IdItem, TimeItem, Parcelable {

    public static final Parcelable.Creator<HistoryEntry> CREATOR = new Parcelable.Creator<HistoryEntry>() {
        @Override
        public HistoryEntry createFromParcel(Parcel source) {
            return new HistoryEntry(source);
        }

        @Override
        public HistoryEntry[] newArray(int size) {
            return new HistoryEntry[size];
        }
    };

    @Json(name = "eid")
    private String id;
    @Json(name = "name")
    private String name;
    @Json(name = "language")
    private String language;
    @Json(name = "medium")
    private String medium;
    @Json(name = "kat")
    private String category;
    @Json(name = "episode")
    private int episode;
    @Json(name = "timestamp")
    private String time;

    /**
     * The constructor.
     *
     * @param id       The id.
     * @param name     The name.
     * @param language The language.
     * @param medium   The medium.
     * @param category The category.
     * @param episode  The last episode the user has read/watched.
     * @param time     The last time the user watched/read an episode as ISO timestamp.
     */
    public HistoryEntry(@NonNull String id, @NonNull String name,
                        @NonNull @Language String language, @NonNull @Medium String medium,
                        @NonNull @Category String category, @IntRange(from = 0) int episode,
                        @NonNull String time) {
        this.id = id;
        this.name = name;
        this.language = language;
        this.medium = medium;
        this.category = category;
        this.episode = episode;
        this.time = time;
    }

    protected HistoryEntry(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.language = in.readString();
        this.medium = in.readString();
        this.category = in.readString();
        this.episode = in.readInt();
        this.time = in.readString();
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
     * Returns the name.
     *
     * @return The name.
     */
    @NonNull
    public String getName() {
        return name;
    }

    /**
     * Returns the language.
     *
     * @return The language.
     */
    @NonNull
    @Language
    public String getLanguage() {
        return language;
    }

    /**
     * Returns the medium.
     *
     * @return The medium.
     */
    @NonNull
    @Medium
    public String getMedium() {
        return medium;
    }

    /**
     * Returns the category.
     *
     * @return The category.
     */
    @NonNull
    @Category
    public String getCategory() {
        return category;
    }

    /**
     * Returns the last episode the user has watched/read.
     *
     * @return The episode.
     */
    @IntRange(from = 0)
    public int getEpisode() {
        return episode;
    }

    /**
     * Returns the last time the user has watched/read an epsiode.
     *
     * @return The time.
     */
    @Override
    public long getTime() {
        return Utils.timestampToUnixTime(time);
    }

    @SuppressWarnings("SimplifiableIfStatement")
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HistoryEntry that = (HistoryEntry) o;

        if (episode != that.episode) return false;
        if (!time.equals(that.time)) return false;
        if (!id.equals(that.id)) return false;
        if (!name.equals(that.name)) return false;
        if (!language.equals(that.language)) return false;
        if (!medium.equals(that.medium)) return false;
        return category.equals(that.category);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + language.hashCode();
        result = 31 * result + medium.hashCode();
        result = 31 * result + category.hashCode();
        result = 31 * result + episode;
        result = 31 * result + time.hashCode();
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
        dest.writeString(this.language);
        dest.writeString(this.medium);
        dest.writeString(this.category);
        dest.writeInt(this.episode);
        dest.writeString(this.time);
    }
}
