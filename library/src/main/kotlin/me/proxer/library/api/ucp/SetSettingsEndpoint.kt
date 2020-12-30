package me.proxer.library.api.ucp

import me.proxer.library.ProxerCall
import me.proxer.library.api.Endpoint
import me.proxer.library.entity.ucp.UcpSettings
import me.proxer.library.enums.UcpSettingConstraint
import me.proxer.library.internal.util.toIntOrNull

/**
 * Endpoint for updating the settings of the user.
 *
 * @author Ruben Gees
 */
class SetSettingsEndpoint : Endpoint<List<String>?> {

    private val internalApi: InternalApi

    private var profileVisibility: UcpSettingConstraint? = null
    private var topTenVisibility: UcpSettingConstraint? = null
    private var animeVisibility: UcpSettingConstraint? = null
    private var mangaVisibility: UcpSettingConstraint? = null
    private var commentVisibility: UcpSettingConstraint? = null
    private var forumVisibility: UcpSettingConstraint? = null
    private var friendVisibility: UcpSettingConstraint? = null
    private var friendRequestConstraint: UcpSettingConstraint? = null
    private var aboutVisibility: UcpSettingConstraint? = null
    private var historyVisibility: UcpSettingConstraint? = null
    private var guestBookVisibility: UcpSettingConstraint? = null
    private var guestBookEntryConstraint: UcpSettingConstraint? = null
    private var galleryVisibility: UcpSettingConstraint? = null
    private var articleVisibility: UcpSettingConstraint? = null
    private var hideTags: Boolean? = null
    private var showAds: Boolean? = null
    private var adInterval: Int? = null

    /**
     * Sets the visibility of the top ten.
     */
    fun profileVisibility(profileVisibility: UcpSettingConstraint?) = this.apply {
        this.profileVisibility = profileVisibility
    }

    /**
     * Sets the visibility of the top ten.
     */
    fun topTenVisibility(topTenVisibility: UcpSettingConstraint?) = this.apply {
        this.topTenVisibility = topTenVisibility
    }

    /**
     * Sets the visibility of the anime list.
     */
    fun animeVisibility(animeVisibility: UcpSettingConstraint?) = this.apply {
        this.animeVisibility = animeVisibility
    }

    /**
     * Sets the visibility of the manga list.
     */
    fun mangaVisibility(mangaVisibility: UcpSettingConstraint?) = this.apply {
        this.mangaVisibility = mangaVisibility
    }

    /**
     * Sets the visibility of the comments.
     */
    fun commentVisibility(commentVisibility: UcpSettingConstraint?) = this.apply {
        this.commentVisibility = commentVisibility
    }

    /**
     * Sets the visibility of the forum entries.
     */
    fun forumVisibility(forumVisibility: UcpSettingConstraint?) = this.apply {
        this.forumVisibility = forumVisibility
    }

    /**
     * Sets the visibility of the friends.
     */
    fun friendVisibility(friendVisibility: UcpSettingConstraint?) = this.apply {
        this.friendVisibility = friendVisibility
    }

    /**
     * Sets the constraint of who can request friendship.
     */
    fun friendRequestConstraint(friendRequestConstraint: UcpSettingConstraint?) = this.apply {
        this.friendRequestConstraint = friendRequestConstraint
    }

    /**
     * Sets the visibility of the about page.
     */
    fun aboutVisibility(aboutVisibility: UcpSettingConstraint?) = this.apply {
        this.aboutVisibility = aboutVisibility
    }

    /**
     * Sets the visibility of the history.
     */
    fun historyVisibility(historyVisibility: UcpSettingConstraint?) = this.apply {
        this.historyVisibility = historyVisibility
    }

    /**
     * Sets the visibility of the guest book.
     */
    fun guestBookVisibility(guestBookVisibility: UcpSettingConstraint?) = this.apply {
        this.guestBookVisibility = guestBookVisibility
    }

    /**
     * Sets the constraint of who can post new entries in the guest book.
     */
    fun guestBookEntryConstraint(guestBookEntryConstraint: UcpSettingConstraint?) = this.apply {
        this.guestBookEntryConstraint = guestBookEntryConstraint
    }

    /**
     * Sets the visibility of the gallery activities.
     */
    fun galleryVisibility(galleryVisibility: UcpSettingConstraint?) = this.apply {
        this.galleryVisibility = galleryVisibility
    }

    /**
     * Sets the visibility of the articles.
     */
    fun articleVisibility(articleVisibility: UcpSettingConstraint?) = this.apply {
        this.articleVisibility = articleVisibility
    }

    /**
     * Sets if tags should be shown or not on media detail pages.
     */
    fun hideTags(hideTags: Boolean? = false) = this.apply {
        this.hideTags = hideTags
    }

    /**
     * Sets if ads in general should be shown.
     */
    fun showAds(showAds: Boolean? = false) = this.apply {
        this.showAds = showAds
    }

    /**
     * Sets the interval in which (video-)ads should be shown.
     * This is a range from 0 to 10. The less the more often, except for 0, which means never.
     */
    fun adInterval(adInterval: Int?) = this.apply {
        this.adInterval = adInterval
    }

    internal constructor(internalApi: InternalApi, settings: UcpSettings) {
        this.internalApi = internalApi

        this.profileVisibility = settings.profileVisibility
        this.topTenVisibility = settings.topTenVisibility
        this.animeVisibility = settings.animeVisibility
        this.mangaVisibility = settings.mangaVisibility
        this.commentVisibility = settings.commentVisibility
        this.forumVisibility = settings.forumVisibility
        this.friendVisibility = settings.friendVisibility
        this.friendRequestConstraint = settings.friendRequestConstraint
        this.aboutVisibility = settings.aboutVisibility
        this.historyVisibility = settings.historyVisibility
        this.guestBookVisibility = settings.guestBookVisibility
        this.guestBookEntryConstraint = settings.guestBookEntryConstraint
        this.galleryVisibility = settings.galleryVisibility
        this.articleVisibility = settings.articleVisibility
        this.hideTags = settings.isHideTags
        this.showAds = settings.isShowAds
        this.adInterval = settings.adInterval
    }

    internal constructor(internalApi: InternalApi) {
        this.internalApi = internalApi
    }

    override fun build(): ProxerCall<List<String>?> {
        return internalApi.setSettings(
            profileVisibility, topTenVisibility, animeVisibility, mangaVisibility, commentVisibility, forumVisibility,
            friendVisibility, friendRequestConstraint, aboutVisibility, historyVisibility, guestBookVisibility,
            guestBookEntryConstraint, galleryVisibility, articleVisibility,
            hideTags.toIntOrNull(), showAds.toIntOrNull(), adInterval
        )
    }
}
