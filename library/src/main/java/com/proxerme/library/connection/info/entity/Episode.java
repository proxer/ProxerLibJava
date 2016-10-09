package com.proxerme.library.connection.info.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.proxerme.library.parameters.LanguageParameter.Language;
import com.squareup.moshi.Json;

/**
 * Entity, representing a single episode, in a single language.
 * Note: Anime and Manga have different values. Only anime has hosters, while only manga has a title.
 *
 * @author Ruben Gees
 */
public class Episode implements Parcelable {

    public static final Creator<Episode> CREATOR = new Creator<Episode>() {
        @Override
        public Episode createFromParcel(Parcel source) {
            return new Episode(source);
        }

        @Override
        public Episode[] newArray(int size) {
            return new Episode[size];
        }
    };

    private static final String DELIMITER = ",";

    @Json(name = "no")
    private int number;
    @Json(name = "typ")
    private String language;
    @Json(name = "title")
    private String title;
    @Json(name = "types")
    private String hosters;
    @Json(name = "typeimg")
    private String hosterImages;

    private Episode() {
    }

    /**
     * The constructor.
     *
     * @param number       The number.
     * @param language     The language.
     * @param title        The title (only for mangas).
     * @param hosters      The hoster names.
     * @param hosterImages The image ids of the hosters.
     */
    public Episode(@IntRange(from = 1) int number, @NonNull @Language String language,
                   @Nullable String title, @Nullable String hosters,
                   @Nullable String hosterImages) {
        this.number = number;
        this.language = language;
        this.title = title;
        this.hosters = hosters;
        this.hosterImages = hosterImages;
    }

    protected Episode(Parcel in) {
        this.number = in.readInt();
        this.language = in.readString();
        this.title = in.readString();
        this.hosters = in.readString();
        this.hosterImages = in.readString();
    }

    /**
     * Returns the number.
     *
     * @return The number.
     */
    @IntRange(from = 0)
    public int getNumber() {
        return number;
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
     * Returns the title.
     * Null if not a manga.
     *
     * @return The title.
     */
    @Nullable
    public String getTitle() {
        return title;
    }


    /**
     * Returns an array of hosters.
     * Null if not an anime.
     *
     * @return The array.
     */
    @Nullable
    public Hoster[] getHosters() {
        if (hosters == null || hosterImages == null) {
            return null;
        } else if (hosters.isEmpty()) {
            return new Hoster[0];
        } else {
            String[] hostersSplit = hosters.split(DELIMITER);
            String[] hosterImagesSplit = hosterImages.split(DELIMITER);
            Hoster[] result = new Hoster[hostersSplit.length > hosterImagesSplit.length ?
                    hosterImagesSplit.length : hostersSplit.length];

            for (int i = 0; i < hostersSplit.length || i < hosterImagesSplit.length; i++) {
                result[i] = new Hoster(hostersSplit[i], hosterImagesSplit[i]);
            }

            return result;
        }
    }

    @SuppressWarnings("SimplifiableIfStatement")
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Episode episode = (Episode) o;

        if (number != episode.number) return false;
        if (!language.equals(episode.language)) return false;
        if (title != null ? !title.equals(episode.title) : episode.title != null) return false;
        if (hosters != null ? !hosters.equals(episode.hosters) : episode.hosters != null)
            return false;
        return hosterImages != null ? hosterImages.equals(episode.hosterImages) : episode.hosterImages == null;
    }

    @Override
    public int hashCode() {
        int result = number;
        result = 31 * result + language.hashCode();
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (hosters != null ? hosters.hashCode() : 0);
        result = 31 * result + (hosterImages != null ? hosterImages.hashCode() : 0);
        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.number);
        dest.writeString(this.language);
        dest.writeString(this.title);
        dest.writeString(this.hosters);
        dest.writeString(this.hosterImages);
    }
}
