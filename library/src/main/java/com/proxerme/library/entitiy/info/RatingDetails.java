package com.proxerme.library.entitiy.info;

import com.squareup.moshi.Json;

/**
 * Container for the single ratings. This ranges from 1 to 5 and is 0 if the user has not rated a
 * specific (or all) section(s).
 *
 * @author Desnoo
 */
public final class RatingDetails {

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
    public RatingDetails(final int genre, final int story, final int animation, final int characters, final int music) {
        this.genre = genre;
        this.story = story;
        this.animation = animation;
        this.characters = characters;
        this.music = music;
    }

    /**
     * Returns the genre rating.
     *
     * @return The genre rating.
     **/
    public int getGenre() {
        return genre;
    }

    /**
     * Returns the story rating.
     *
     * @return The story rating.
     **/
    public int getStory() {
        return story;
    }

    /**
     * Returns the animation rating.
     *
     * @return The animation rating.
     **/
    public int getAnimation() {
        return animation;
    }

    /**
     * Returns the characters rating.
     *
     * @return The characters rating.
     **/
    public int getCharacters() {
        return characters;
    }

    /**
     * Returns the music rating.
     *
     * @return The music rating.
     **/
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
