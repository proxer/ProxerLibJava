package me.proxer.library.api.ucp;

import lombok.Setter;
import lombok.experimental.Accessors;
import me.proxer.library.api.Endpoint;
import me.proxer.library.api.ProxerCall;
import me.proxer.library.entity.ucp.UcpSettings;
import me.proxer.library.enums.UcpSettingConstraint;

import javax.annotation.Nullable;

/**
 * Endpoint for updating the settings of the user.
 *
 * @author Ruben Gees
 */
@Accessors(fluent = true)
public class SetSettingsEndpoint implements Endpoint<Void> {

    private final InternalApi internalApi;

    /**
     * Sets the visibility of the top ten.
     */
    @Nullable
    @Setter
    private UcpSettingConstraint profileVisibility;

    /**
     * Sets the visibility of the top ten.
     */
    @Nullable
    @Setter
    private UcpSettingConstraint topTenVisibility;

    /**
     * Sets the visibility of the anime list.
     */
    @Nullable
    @Setter
    private UcpSettingConstraint animeVisibility;

    /**
     * Sets the visibility of the manga list.
     */
    @Nullable
    @Setter
    private UcpSettingConstraint mangaVisibility;

    /**
     * Sets the visibility of the comments.
     */
    @Nullable
    @Setter
    private UcpSettingConstraint commentVisibility;

    /**
     * Sets the visibility of the forum entries.
     */
    @Nullable
    @Setter
    private UcpSettingConstraint forumVisibility;

    /**
     * Sets the visibility of the friends.
     */
    @Nullable
    @Setter
    private UcpSettingConstraint friendVisibility;

    /**
     * Sets the constraint of who can request friendship.
     */
    @Nullable
    @Setter
    private UcpSettingConstraint friendRequestConstraint;

    /**
     * Sets the visibility of the about page.
     */
    @Nullable
    @Setter
    private UcpSettingConstraint aboutVisibility;

    /**
     * Sets the visibility of the history.
     */
    @Nullable
    @Setter
    private UcpSettingConstraint historyVisibility;

    /**
     * Sets the visibility of the guest book.
     */
    @Nullable
    @Setter
    private UcpSettingConstraint guestBookVisibility;

    /**
     * Sets the constraint of who can post new entries in the guest book.
     */
    @Nullable
    @Setter
    private UcpSettingConstraint guestBookEntryConstraint;

    /**
     * Sets the visibility of the gallery activities.
     */
    @Nullable
    @Setter
    private UcpSettingConstraint galleryVisibility;

    /**
     * Sets the visibility of the articles.
     */
    @Nullable
    @Setter
    private UcpSettingConstraint articleVisibility;

    /**
     * Sets if tags should be shown or not on media detail pages.
     */
    @Nullable
    @Setter
    private Boolean hideTags;

    /**
     * Sets if ads in general should be shown.
     */
    @Nullable
    @Setter
    private Boolean showAds;

    /**
     * Sets the interval in which (video-)ads should be shown.
     * This is a range from 0 to 10. The less the more often, except for 0, which means never.
     */
    @Nullable
    @Setter
    private Integer adInterval;

    SetSettingsEndpoint(final InternalApi internalApi, final UcpSettings settings) {
        this.internalApi = internalApi;

        this.profileVisibility = settings.getProfileVisibility();
        this.topTenVisibility = settings.getTopTenVisibility();
        this.animeVisibility = settings.getAnimeVisibility();
        this.mangaVisibility = settings.getMangaVisibility();
        this.commentVisibility = settings.getCommentVisibility();
        this.forumVisibility = settings.getForumVisibility();
        this.friendVisibility = settings.getFriendVisibility();
        this.friendRequestConstraint = settings.getFriendRequestConstraint();
        this.aboutVisibility = settings.getAboutVisibility();
        this.historyVisibility = settings.getHistoryVisibility();
        this.guestBookVisibility = settings.getGuestBookVisibility();
        this.guestBookEntryConstraint = settings.getGuestBookEntryConstraint();
        this.galleryVisibility = settings.getGalleryVisibility();
        this.articleVisibility = settings.getArticleVisibility();
        this.hideTags = settings.isHideTags();
        this.showAds = settings.isShowAds();
        this.adInterval = settings.getAdInterval();
    }

    SetSettingsEndpoint(final InternalApi internalApi) {
        this.internalApi = internalApi;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProxerCall<Void> build() {
        return internalApi.setSettings(profileVisibility, topTenVisibility, animeVisibility, mangaVisibility,
                commentVisibility, forumVisibility, friendVisibility, friendRequestConstraint, aboutVisibility,
                historyVisibility, guestBookVisibility, guestBookEntryConstraint, galleryVisibility, articleVisibility,
                hideTags == null ? null : hideTags ? 1 : 0, showAds == null ? null : showAds ? 1 : 0, adInterval);
    }
}
