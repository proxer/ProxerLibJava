package com.proxerme.library.connection.ucp.entitiy;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;

import com.proxerme.library.interfaces.IdItem;
import com.proxerme.library.parameters.CategoryParameter.Category;
import com.proxerme.library.parameters.LanguageParameter.Language;
import com.proxerme.library.parameters.MediumParameter.Medium;
import com.proxerme.library.parameters.StateParameter.State;
import com.squareup.moshi.Json;

/**
 * A single reminder of the user.
 *
 * @author Ruben Gees
 */
public class Reminder implements IdItem, Parcelable {

    public static final Parcelable.Creator<Reminder> CREATOR = new Parcelable.Creator<Reminder>() {
        @Override
        public Reminder createFromParcel(Parcel source) {
            return new Reminder(source);
        }

        @Override
        public Reminder[] newArray(int size) {
            return new Reminder[size];
        }
    };

    @Json(name = "id")
    private String id;
    @Json(name = "eid")
    private String entryId;
    @Json(name = "kat")
    private String category;
    @Json(name = "name")
    private String name;
    @Json(name = "episode")
    private int episode;
    @Json(name = "language")
    private String language;
    @Json(name = "medium")
    private String medium;
    @Json(name = "state")
    private int state;

    /**
     * The constructor.
     *
     * @param id       The id.
     * @param entryId  The id of the associated entry.
     * @param category The category.
     * @param name     The name.
     * @param episode  The episode this object is a reminder of.
     * @param language The language.
     * @param medium   The medium.
     * @param state    The state of the entry.
     */
    public Reminder(@NonNull String id, @NonNull String entryId, @NonNull @Category String category,
                    @NonNull String name, @IntRange(from = 1) int episode,
                    @Language String language, @Medium String medium, @State int state) {
        this.id = id;
        this.entryId = entryId;
        this.category = category;
        this.name = name;
        this.episode = episode;
        this.language = language;
        this.medium = medium;
        this.state = state;
    }

    protected Reminder(Parcel in) {
        this.id = in.readString();
        this.entryId = in.readString();
        this.category = in.readString();
        this.name = in.readString();
        this.episode = in.readInt();
        this.language = in.readString();
        this.medium = in.readString();
        this.state = in.readInt();
    }

    /**
     * Returns the id of this reminder.
     *
     * @return The id.
     */
    @NonNull
    @Override
    public String getId() {
        return id;
    }

    /**
     * Returns the id of the entry.
     *
     * @return The id.
     */
    @NonNull
    public String getEntryId() {
        return entryId;
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
     * Returns the name.
     *
     * @return The name.
     */
    @NonNull
    public String getName() {
        return name;
    }

    /**
     * Returns the episode.
     *
     * @return The episode.
     */
    @IntRange(from = 1)
    public int getEpisode() {
        return episode;
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
     * Returns the current state.
     *
     * @return The state.
     */
    @State
    public int getState() {
        return state;
    }

    @SuppressWarnings("SimplifiableIfStatement")
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Reminder reminder = (Reminder) o;

        if (episode != reminder.episode) return false;
        if (state != reminder.state) return false;
        if (!id.equals(reminder.id)) return false;
        if (!entryId.equals(reminder.entryId)) return false;
        if (!category.equals(reminder.category)) return false;
        if (!name.equals(reminder.name)) return false;
        if (!language.equals(reminder.language)) return false;
        return medium.equals(reminder.medium);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + entryId.hashCode();
        result = 31 * result + category.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + episode;
        result = 31 * result + language.hashCode();
        result = 31 * result + medium.hashCode();
        result = 31 * result + state;
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
        dest.writeString(this.category);
        dest.writeString(this.name);
        dest.writeInt(this.episode);
        dest.writeString(this.language);
        dest.writeString(this.medium);
        dest.writeInt(this.state);
    }
}
