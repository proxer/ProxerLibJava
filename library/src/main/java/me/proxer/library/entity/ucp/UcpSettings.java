package me.proxer.library.entity.ucp;

import com.squareup.moshi.Json;
import lombok.EqualsAndHashCode;
import lombok.Value;
import me.proxer.library.api.NumberBasedBoolean;
import me.proxer.library.enums.UcpSettingConstraint;

import javax.annotation.Nullable;

/**
 * Entity representing the settings of the user.
 *
 * @author Ruben Gees
 */
@Value
@EqualsAndHashCode(onParam = @__({@Nullable}))
public class UcpSettings {

    /**
     * Returns the visibility of the general profile.
     */
    @Json(name = "profil")
    private UcpSettingConstraint profileVisibility;

    /**
     * Returns the visibility of the top ten.
     */
    @Json(name = "profil_topten")
    private UcpSettingConstraint topTenVisibility;

    /**
     * Returns the visibility of the anime list.
     */
    @Json(name = "profil_anime")
    private UcpSettingConstraint animeVisibility;

    /**
     * Returns the visibility of the manga list.
     */
    @Json(name = "profil_manga")
    private UcpSettingConstraint mangaVisibility;

    /**
     * Returns the visibility of the comments.
     */
    @Json(name = "profil_latestcomments")
    private UcpSettingConstraint commentVisibility;

    /**
     * Returns the visibility of the forum entries.
     */
    @Json(name = "profil_forum")
    private UcpSettingConstraint forumVisibility;

    /**
     * Returns the visibility of the friends.
     */
    @Json(name = "profil_connections")
    private UcpSettingConstraint friendVisibility;

    /**
     * Returns the constraint of who can request friendship.
     */
    @Json(name = "profil_connections_new")
    private UcpSettingConstraint friendRequestConstraint;

    /**
     * Returns the visibility of the about page.
     */
    @Json(name = "profil_about")
    private UcpSettingConstraint aboutVisibility;

    /**
     * Returns the visibility of the history.
     */
    @Json(name = "profil_chronik")
    private UcpSettingConstraint historyVisibility;

    /**
     * Returns the visibility of the guest book.
     */
    @Json(name = "profil_board")
    private UcpSettingConstraint guestBookVisibility;

    /**
     * Returns the constraint of who can post new entries in the guest book.
     */
    @Json(name = "profil_board_post")
    private UcpSettingConstraint guestBookEntryConstraint;

    /**
     * Returns the visibility of the gallery activities.
     */
    @Json(name = "profil_gallery")
    private UcpSettingConstraint galleryVisibility;

    /**
     * Returns the visibility of the articles.
     */
    @Json(name = "profil_article")
    private UcpSettingConstraint articleVisibility;

    /**
     * Returns if tags should be shown or not on media detail pages.
     */
    @NumberBasedBoolean
    @Json(name = "hide_tags")
    private boolean hideTags;

    /**
     * Returns if ads in general should be shown.
     */
    @NumberBasedBoolean
    @Json(name = "ads_active")
    private boolean showAds;

    /**
     * Returns the interval in which (video-)ads should be shown.
     * This is a range from 0 to 10. The less the more often, except for 0, which means never.
     */
    @Json(name = "ads_interval")
    private int adInterval;
}
