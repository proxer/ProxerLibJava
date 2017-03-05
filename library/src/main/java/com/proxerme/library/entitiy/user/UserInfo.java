package com.proxerme.library.entitiy.user;

import com.proxerme.library.interfaces.IdItem;
import com.proxerme.library.interfaces.ImageItem;
import com.squareup.moshi.Json;
import org.jetbrains.annotations.NotNull;

import java.util.Date;

/**
 * Entity holding all basic info of a {@link User}.
 *
 * @author Ruben Gees
 */
public class UserInfo implements IdItem, ImageItem {

    @Json(name = "uid")
    private String id;
    @Json(name = "username")
    private String username;
    @Json(name = "avatar")
    private String imageId;
    @Json(name = "status")
    private String status;
    @Json(name = "status_time")
    private Date lastStatusChange;
    @Json(name = "points_uploads")
    private int uploadPoints;
    @Json(name = "points_forum")
    private int forumPoints;
    @Json(name = "points_anime")
    private int animePoints;
    @Json(name = "points_manga")
    private int mangaPoints;
    @Json(name = "points_info")
    private int infoPoints;
    @Json(name = "points_misc")
    private int miscPoints;

    private UserInfo() {

    }

    /**
     * The constructor.
     *
     * @param id               The id.
     * @param username         The username.
     * @param imageId          The id of the image.
     * @param status           The status.
     * @param lastStatusChange The date of the last status change.
     * @param uploadPoints     The rank points for uploads.
     * @param forumPoints      The rank points for forum posts.
     * @param animePoints      The rank points for anime.
     * @param mangaPoints      The rank points for manga.
     * @param infoPoints       The rank points for entries in the info section.
     * @param miscPoints       The rank points awarded for various actions (e.g. donations).
     */
    public UserInfo(@NotNull final String id, @NotNull final String username, @NotNull String imageId,
                    @NotNull String status, @NotNull Date lastStatusChange, final int uploadPoints,
                    final int forumPoints, final int animePoints, final int mangaPoints, final int infoPoints,
                    final int miscPoints) {
        this.id = id;
        this.username = username;
        this.imageId = imageId;
        this.status = status;
        this.lastStatusChange = lastStatusChange;
        this.uploadPoints = uploadPoints;
        this.forumPoints = forumPoints;
        this.animePoints = animePoints;
        this.mangaPoints = mangaPoints;
        this.infoPoints = infoPoints;
        this.miscPoints = miscPoints;
    }

    /**
     * Returns the id of the user.
     *
     * @return The id.
     */
    @Override
    @NotNull
    public String getId() {
        return id;
    }

    /**
     * Returns the username.
     *
     * @return The username.
     */
    @NotNull
    public String getUsername() {
        return username;
    }

    /**
     * Returns the id of the avatar image.
     *
     * @return The id.
     */
    @Override
    @NotNull
    public String getImageId() {
        return imageId;
    }

    /**
     * Returns the status of the user. Might be empty if the user did not set a status.
     *
     * @return The status.
     */
    @NotNull
    public String getStatus() {
        return status;
    }

    /**
     * Returns the date of the last status change. This is 0 if the user has no status.
     *
     * @return The date.
     */
    @NotNull
    public Date getLastStatusChange() {
        return lastStatusChange;
    }

    /**
     * Returns the rank points for uploads.
     *
     * @return The points.
     */
    public int getUploadPoints() {
        return uploadPoints;
    }

    /**
     * Returns the rank points for forum points.
     *
     * @return The points.
     */
    public int getForumPoints() {
        return forumPoints;
    }

    /**
     * Returns the rank points for anime.
     *
     * @return The points.
     */
    public int getAnimePoints() {
        return animePoints;
    }

    /**
     * Returns the rank points for manga.
     *
     * @return The points.
     */
    public int getMangaPoints() {
        return mangaPoints;
    }

    /**
     * Returns the rank points for entries in the info section.
     *
     * @return The points.
     */
    public int getInfoPoints() {
        return infoPoints;
    }

    /**
     * Returns the rank points awarded for various actions.
     *
     * @return The points.
     */
    public int getMiscPoints() {
        return miscPoints;
    }

    @SuppressWarnings("SimplifiableIfStatement")
    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final UserInfo userInfo = (UserInfo) o;

        if (uploadPoints != userInfo.uploadPoints) return false;
        if (forumPoints != userInfo.forumPoints) return false;
        if (animePoints != userInfo.animePoints) return false;
        if (mangaPoints != userInfo.mangaPoints) return false;
        if (infoPoints != userInfo.infoPoints) return false;
        if (miscPoints != userInfo.miscPoints) return false;
        if (!id.equals(userInfo.id)) return false;
        if (!username.equals(userInfo.username)) return false;
        if (!imageId.equals(userInfo.imageId)) return false;
        if (!status.equals(userInfo.status)) return false;
        return lastStatusChange.equals(userInfo.lastStatusChange);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + username.hashCode();
        result = 31 * result + imageId.hashCode();
        result = 31 * result + status.hashCode();
        result = 31 * result + lastStatusChange.hashCode();
        result = 31 * result + uploadPoints;
        result = 31 * result + forumPoints;
        result = 31 * result + animePoints;
        result = 31 * result + mangaPoints;
        result = 31 * result + infoPoints;
        result = 31 * result + miscPoints;
        return result;
    }
}
