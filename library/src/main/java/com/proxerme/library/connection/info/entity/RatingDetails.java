package com.proxerme.library.connection.info.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.IntRange;

import com.squareup.moshi.Json;

/**
 * The value of the single ratings. This ranges from 1 to 5 and is 0 if the user has not rated a
 * specific (or all) section(s).
 *
 * @author Desnoo
 */
public class RatingDetails implements Parcelable {

    public static final Creator<RatingDetails> CREATOR = new Creator<RatingDetails>() {
        @Override
        public RatingDetails createFromParcel(Parcel in) {
            return new RatingDetails(in);
        }

        @Override
        public RatingDetails[] newArray(int size) {
            return new RatingDetails[size];
        }
    };

    @Json(name = "genre")
    private int genre;
    @Json(name = "story")
    private int story;
    @Json(name = "animation")
    private int animation;
    @Json(name = "characters")
    private int characters;
    @Json(name = "music")
    private int music;

    /**
     * Private constructor used by moshi.
     */
    private RatingDetails() {
    }

    /**
     * The constructor.
     *
     * @param genre      The rating of genre.
     * @param story      The rating of story.
     * @param animation  The rating of animation.
     * @param characters The rating of characters.
     * @param music      The rating of music (always 0 for Manga).
     */
    public RatingDetails(@IntRange(from = 0, to = 5) int genre,
                         @IntRange(from = 0, to = 5) int story,
                         @IntRange(from = 0, to = 5) int animation,
                         @IntRange(from = 0, to = 5) int characters,
                         @IntRange(from = 0, to = 5) int music) {
        this.genre = genre;
        this.story = story;
        this.animation = animation;
        this.characters = characters;
        this.music = music;
    }

    /**
     * The constructor to parse from parcel.
     *
     * @param in The parcel to parse.
     */
    protected RatingDetails(Parcel in) {
        genre = in.readInt();
        story = in.readInt();
        animation = in.readInt();
        characters = in.readInt();
        music = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(genre);
        dest.writeInt(story);
        dest.writeInt(animation);
        dest.writeInt(characters);
        dest.writeInt(music);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Returns the genre rating.
     *
     * @return The genre rating.
     **/
    @IntRange(from = 0, to = 5)
    public int getGenre() {
        return genre;
    }

    /**
     * Returns the story rating.
     *
     * @return The story rating.
     **/
    @IntRange(from = 0, to = 5)
    public int getStory() {
        return story;
    }

    /**
     * Returns the animation rating.
     *
     * @return The animation rating.
     **/
    @IntRange(from = 0, to = 5)
    public int getAnimation() {
        return animation;
    }

    /**
     * Returns the characters rating.
     *
     * @return The characters rating.
     **/
    @IntRange(from = 0, to = 5)
    public int getCharacters() {
        return characters;
    }

    /**
     * Returns the music rating.
     *
     * @return The music rating.
     **/
    @IntRange(from = 0, to = 5)
    public int getMusic() {
        return music;
    }

    @SuppressWarnings("SimplifiableIfStatement")
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RatingDetails that = (RatingDetails) o;

        if (genre != that.genre) return false;
        if (story != that.story) return false;
        if (animation != that.animation) return false;
        if (music != that.music) return false;
        return characters == that.characters;
    }

    @Override
    public int hashCode() {
        int result = genre;
        result = 31 * result + story;
        result = 31 * result + animation;
        result = 31 * result + characters;
        result = 31 * result + music;
        return result;
    }
}
