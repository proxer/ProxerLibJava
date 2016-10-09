package com.proxerme.library.connection.info.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;

import com.proxerme.library.parameters.CategoryParameter.Category;
import com.proxerme.library.parameters.LanguageParameter.Language;
import com.squareup.moshi.Json;

import java.util.Arrays;

/**
 * Entity holding info about available languages, episodes and the current state of the user of an
 * {@link Entry}.
 *
 * @author Ruben Gees
 */
public class ListInfo implements Parcelable {

    public static final Parcelable.Creator<ListInfo> CREATOR = new Parcelable.Creator<ListInfo>() {
        @Override
        public ListInfo createFromParcel(Parcel source) {
            return new ListInfo(source);
        }

        @Override
        public ListInfo[] newArray(int size) {
            return new ListInfo[size];
        }
    };

    @Json(name = "start")
    private int firstEpisode;
    @Json(name = "end")
    private int lastEpisode;
    @Json(name = "kat")
    private String category;
    @Json(name = "lang")
    private String[] languages;
    @Json(name = "state")
    private int userState;
    @Json(name = "episodes")
    private Episode[] episodes;

    private ListInfo() {
    }

    /**
     * The constructor.
     *
     * @param firstEpisode The first available episode.
     * @param lastEpisode  The last available episode.
     * @param category     The category.
     * @param languages    The languages, this entry is available in.
     * @param userState    The last episode the user has seen. 0 when no user is logged in,
     * @param episodes     The array of episodes.
     */
    public ListInfo(@IntRange(from = 1) int firstEpisode, @IntRange(from = 1) int lastEpisode,
                    @NonNull @Category String category, @NonNull @Language String[] languages,
                    @IntRange(from = 0) int userState, @NonNull Episode[] episodes) {
        this.firstEpisode = firstEpisode;
        this.lastEpisode = lastEpisode;
        this.category = category;
        this.languages = languages;
        this.userState = userState;
        this.episodes = episodes;
    }

    protected ListInfo(Parcel in) {
        this.firstEpisode = in.readInt();
        this.lastEpisode = in.readInt();
        this.category = in.readString();
        this.languages = in.createStringArray();
        this.userState = in.readInt();
        this.episodes = in.createTypedArray(Episode.CREATOR);
    }

    /**
     * Returns the first episode.
     *
     * @return The first episode.
     */
    @IntRange(from = 1)
    public int getFirstEpisode() {
        return firstEpisode;
    }

    /**
     * Returns the last episode.
     *
     * @return The last episode.
     */
    @IntRange(from = 1)
    public int getLastEpisode() {
        return lastEpisode;
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
     * Returns the available languages.
     *
     * @return The languages.
     */
    @NonNull
    @Language
    public String[] getLanguages() {
        return languages;
    }

    /**
     * Returns the last episode the user has seen. 0 when no user is logged in,
     *
     * @return The state.
     */
    @IntRange(from = 0)
    public int getUserState() {
        return userState;
    }

    /**
     * Returns the available episodes.
     *
     * @return The episodes.
     */
    @NonNull
    public Episode[] getEpisodes() {
        return episodes;
    }

    @SuppressWarnings("SimplifiableIfStatement")
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ListInfo listInfo = (ListInfo) o;

        if (firstEpisode != listInfo.firstEpisode) return false;
        if (lastEpisode != listInfo.lastEpisode) return false;
        if (userState != listInfo.userState) return false;
        if (!category.equals(listInfo.category)) return false;
        if (!Arrays.equals(languages, listInfo.languages)) return false;
        return Arrays.equals(episodes, listInfo.episodes);
    }

    @Override
    public int hashCode() {
        int result = firstEpisode;
        result = 31 * result + lastEpisode;
        result = 31 * result + category.hashCode();
        result = 31 * result + Arrays.hashCode(languages);
        result = 31 * result + userState;
        result = 31 * result + Arrays.hashCode(episodes);
        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.firstEpisode);
        dest.writeInt(this.lastEpisode);
        dest.writeString(this.category);
        dest.writeStringArray(this.languages);
        dest.writeInt(this.userState);
        dest.writeTypedArray(this.episodes, flags);
    }
}
