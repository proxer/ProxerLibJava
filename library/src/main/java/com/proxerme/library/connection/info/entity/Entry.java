package com.proxerme.library.connection.info.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.FloatRange;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;

import com.proxerme.library.interfaces.IdItem;
import com.proxerme.library.parameters.CategoryParameter.Category;
import com.proxerme.library.parameters.FskParameter;
import com.proxerme.library.parameters.GenreParameter;
import com.proxerme.library.parameters.LicenseParameter.License;
import com.proxerme.library.parameters.MediumParameter.Medium;
import com.proxerme.library.parameters.StateParameter.State;
import com.squareup.moshi.Json;

import java.util.Arrays;

/**
 * Entity holding all info of a entry. This is everything what {@link EntryCore} contains and some
 * more which could also be retrieved by their standalone APIs.
 *
 * @author Ruben Gees
 */
public class Entry implements Parcelable, IdItem {

    public static final Parcelable.Creator<Entry> CREATOR = new Parcelable.Creator<Entry>() {
        @Override
        public Entry createFromParcel(Parcel source) {
            return new Entry(source);
        }

        @Override
        public Entry[] newArray(int size) {
            return new Entry[size];
        }
    };

    private static final String DELIMITER = " ";

    @Json(name = "id")
    private String id;
    @Json(name = "name")
    private String name;
    @Json(name = "genre")
    private String genres;
    @Json(name = "fsk")
    private String fsk;
    @Json(name = "description")
    private String description;
    @Json(name = "medium")
    private String medium;
    @Json(name = "count")
    private int episodeAmount;
    @Json(name = "state")
    private int state;
    @Json(name = "rate_sum")
    private int rateSum;
    @Json(name = "rate_count")
    private int rateCount;
    @Json(name = "clicks")
    private int clicks;
    @Json(name = "kat")
    private String category;
    @Json(name = "license")
    private int license;
    @Json(name = "gate")
    private boolean gate;
    @Json(name = "names")
    private Synonym[] synonyms;
    @Json(name = "lang")
    private String[] languages;
    @Json(name = "seasons")
    private EntrySeason[] seasons;
    @Json(name = "groups")
    private Subgroup[] subgroups;
    @Json(name = "publisher")
    private Publisher[] publishers;
    @Json(name = "tags")
    private Tag[] tags;

    private Entry() {

    }

    /**
     * The constructor.
     *
     * @param id            The entry id.
     * @param name          The entry name.
     * @param genres        The genres.
     * @param fsk           The fsk ratings.
     * @param description   The description.
     * @param medium        The medium.
     * @param episodeAmount The number of episodes.
     * @param state         The user view state.
     * @param rateSum       The sum of all ratings.
     * @param rateCount     The amount of ratings.
     * @param clicks        The amount of clicks.
     * @param category      The category name.
     * @param license       The license id.
     * @param gate          If this entry has a gate (18+ check required)
     * @param synonyms      The synonyms.
     * @param languages     The languages.
     * @param seasons       The seasons.
     * @param subgroups     The subgroups.
     * @param publishers    The publishers.
     * @param tags          The tags.
     */
    public Entry(@NonNull String id, @NonNull String name, @NonNull String genres,
                 @NonNull String fsk, @NonNull String description, @NonNull @Medium String medium,
                 @IntRange(from = 0) int episodeAmount, @State int state,
                 @IntRange(from = 0) int rateSum, @IntRange(from = 0) int rateCount,
                 @IntRange(from = 0) int clicks, @Category String category, @License int license,
                 boolean gate, @NonNull Synonym[] synonyms, @NonNull String[] languages,
                 @NonNull EntrySeason[] seasons, @NonNull Subgroup[] subgroups,
                 @NonNull Publisher[] publishers, @NonNull Tag[] tags) {
        this.id = id;
        this.name = name;
        this.genres = genres;
        this.fsk = fsk;
        this.description = description;
        this.medium = medium;
        this.episodeAmount = episodeAmount;
        this.state = state;
        this.rateSum = rateSum;
        this.rateCount = rateCount;
        this.clicks = clicks;
        this.category = category;
        this.license = license;
        this.gate = gate;
        this.synonyms = synonyms;
        this.languages = languages;
        this.seasons = seasons;
        this.subgroups = subgroups;
        this.publishers = publishers;
        this.tags = tags;
    }

    protected Entry(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.genres = in.readString();
        this.fsk = in.readString();
        this.description = in.readString();
        this.medium = in.readString();
        this.episodeAmount = in.readInt();
        this.state = in.readInt();
        this.rateSum = in.readInt();
        this.rateCount = in.readInt();
        this.clicks = in.readInt();
        this.category = in.readString();
        this.license = in.readInt();
        this.gate = in.readByte() != 0;
        this.synonyms = in.createTypedArray(Synonym.CREATOR);
        this.languages = in.createStringArray();
        this.seasons = in.createTypedArray(EntrySeason.CREATOR);
        this.subgroups = in.createTypedArray(Subgroup.CREATOR);
        this.publishers = in.createTypedArray(Publisher.CREATOR);
        this.tags = in.createTypedArray(Tag.CREATOR);
    }

    /**
     * Returns the id of this entry.
     *
     * @return The id.
     */
    @NonNull
    @Override
    public String getId() {
        return id;
    }

    /**
     * Returns the name of this entry.
     *
     * @return the name.
     */
    @NonNull
    public String getName() {
        return name;
    }

    /**
     * Returns the genres of this entry.
     *
     * @return The genres.
     */
    @SuppressWarnings("WrongConstant")
    @NonNull
    @GenreParameter.Genre
    public String[] getGenres() {
        if (genres.isEmpty()) {
            return new String[0];
        } else {
            return genres.split(DELIMITER);
        }
    }

    /**
     * Returns an array of fsk names of this entry.
     *
     * @return An array of fsk names.
     */
    @SuppressWarnings("WrongConstant")
    @NonNull
    @FskParameter.FskConstraint
    public String[] getFsk() {
        if (fsk.isEmpty()) {
            return new String[0];
        } else {
            return fsk.split(DELIMITER);
        }
    }

    /**
     * Returns the description of this entry.
     *
     * @return The description.
     */
    @NonNull
    public String getDescription() {
        return description;
    }

    /**
     * Returns the medium of this entry.
     *
     * @return The medium.
     */
    @NonNull
    @Medium
    public String getMedium() {
        return medium;
    }

    /**
     * Get the overall amount of episodes/chapters of this entry. It does include not available
     * episodes.
     *
     * @return The amount of episodes/chapters.
     */
    @IntRange(from = 0)
    public int getEpisodeAmount() {
        return episodeAmount;
    }

    /**
     * Returns the view state for the current user of this entry. If no user is set then this is 0.
     *
     * @return The view state for the current user.
     */
    @IntRange(from = 0)
    public int getState() {
        return state;
    }

    /**
     * Returns the sum of all ratings of this entry.
     *
     * @return The sum of ratings.
     */
    @IntRange(from = 0)
    public int getRateSum() {
        return rateSum;
    }

    /**
     * Returns the amount of ratings of this entry.
     *
     * @return The amount of ratings.
     */
    @IntRange(from = 0)
    public int getRateCount() {
        return rateCount;
    }

    /**
     * Returns the sum of all ratings of this entry.
     *
     * @return The sum of all ratings.
     */
    @FloatRange(from = -1.0f)
    public float getRating() {
        if (rateCount <= 0) {
            return -1;
        } else {
            return rateSum / rateCount;
        }
    }

    /**
     * Returns the number of views of this entry. Will be deleted every 3 months.
     *
     * @return The number of views.
     */
    @IntRange(from = 0)
    public int getClicks() {
        return clicks;
    }

    /**
     * Returns the name of the category of this entry.
     *
     * @return The name of the category.
     */
    @NonNull
    @Category
    public String getCategory() {
        return category;
    }

    /**
     * Returns the license state of this entry.
     *
     * @return The license state.
     */
    @License
    public int getLicense() {
        return license;
    }

    /**
     * Returns if this entry has a gate (18+ check needed).
     *
     * @return True, if gated.
     */
    public boolean isGate() {
        return gate;
    }

    /**
     * Returns the synonyms.
     *
     * @return The synonyms.
     */
    @NonNull
    public Synonym[] getSynonyms() {
        return synonyms;
    }

    /**
     * Returns the available languages.
     *
     * @return The languages.
     */
    @NonNull
    public String[] getLanguages() {
        return languages;
    }

    /**
     * Returns the seasons.
     *
     * @return The seasons.
     */
    @NonNull
    public EntrySeason[] getSeasons() {
        return seasons;
    }

    /**
     * Returns the subgroups.
     *
     * @return The subgroups.
     */
    @NonNull
    public Subgroup[] getSubgroups() {
        return subgroups;
    }

    /**
     * Returns the publishers.
     *
     * @return The publishers.
     */
    @NonNull
    public Publisher[] getPublishers() {
        return publishers;
    }

    /**
     * Returns all the tags.
     *
     * @return The tags.
     */
    @NonNull
    public Tag[] getTags() {
        return tags;
    }

    @SuppressWarnings("SimplifiableIfStatement")
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Entry entry = (Entry) o;

        if (episodeAmount != entry.episodeAmount) return false;
        if (state != entry.state) return false;
        if (rateSum != entry.rateSum) return false;
        if (rateCount != entry.rateCount) return false;
        if (clicks != entry.clicks) return false;
        if (license != entry.license) return false;
        if (gate != entry.gate) return false;
        if (!id.equals(entry.id)) return false;
        if (!name.equals(entry.name)) return false;
        if (!genres.equals(entry.genres)) return false;
        if (!fsk.equals(entry.fsk)) return false;
        if (!description.equals(entry.description)) return false;
        if (!medium.equals(entry.medium)) return false;
        if (!category.equals(entry.category)) return false;
        if (!Arrays.equals(synonyms, entry.synonyms)) return false;
        if (!Arrays.equals(languages, entry.languages)) return false;
        if (!Arrays.equals(seasons, entry.seasons)) return false;
        if (!Arrays.equals(subgroups, entry.subgroups)) return false;
        if (!Arrays.equals(publishers, entry.publishers)) return false;
        return Arrays.equals(tags, entry.tags);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + genres.hashCode();
        result = 31 * result + fsk.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + medium.hashCode();
        result = 31 * result + episodeAmount;
        result = 31 * result + state;
        result = 31 * result + rateSum;
        result = 31 * result + rateCount;
        result = 31 * result + clicks;
        result = 31 * result + category.hashCode();
        result = 31 * result + license;
        result = 31 * result + (gate ? 1 : 0);
        result = 31 * result + Arrays.hashCode(synonyms);
        result = 31 * result + Arrays.hashCode(languages);
        result = 31 * result + Arrays.hashCode(seasons);
        result = 31 * result + Arrays.hashCode(subgroups);
        result = 31 * result + Arrays.hashCode(publishers);
        result = 31 * result + Arrays.hashCode(tags);
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
        dest.writeString(this.genres);
        dest.writeString(this.fsk);
        dest.writeString(this.description);
        dest.writeString(this.medium);
        dest.writeInt(this.episodeAmount);
        dest.writeInt(this.state);
        dest.writeInt(this.rateSum);
        dest.writeInt(this.rateCount);
        dest.writeInt(this.clicks);
        dest.writeString(this.category);
        dest.writeInt(this.license);
        dest.writeByte(this.gate ? (byte) 1 : (byte) 0);
        dest.writeTypedArray(this.synonyms, flags);
        dest.writeStringArray(this.languages);
        dest.writeTypedArray(this.seasons, flags);
        dest.writeTypedArray(this.subgroups, flags);
        dest.writeTypedArray(this.publishers, flags);
        dest.writeTypedArray(this.tags, flags);
    }
}
